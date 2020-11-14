package com.ssafy.happyhouse.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.happyhouse.dao.HouseDao;
import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.EnvironmentInfo;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.housecart;
import com.ssafy.happyhouse.dto.rsInfo;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.service.BoardService;
import com.ssafy.happyhouse.service.EnvironmentService;
import com.ssafy.happyhouse.service.HouseService;
import com.ssafy.happyhouse.service.rsInfoService;
import com.ssafy.happyhouse.util.PageNavigation;

@Controller
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/house")
public class HouseController {

	@Autowired
	HouseService houseService;
	@Autowired
	EnvironmentService environmentservice;
	@Autowired
	rsInfoService rsInfoService;

	// 검색 & 리스트 표시
	@RequestMapping(value = "/houselist/search", method = RequestMethod.GET)
	String searchHouseList(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		// --페이지 찾깅
		String pg = request.getParameter("pg"); // 현재페이지
		int currentPage = pg == null ? 1 : Integer.parseInt(pg);
		String spp = request.getParameter("spp"); // 한 페이지당 글 갯수
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);
		int totalsize = 1000;
		System.out.println("House controller [search] - cur:" + currentPage + ", spp:" + sizePerPage);
		// --검색 체크박스
		String[] type = request.getParameterValues("type");
		boolean[] sendtype = { false, false, false, false, false }; // 거래번호 sql에서 1~4
		// --검색 단어
		String key = request.getParameter("key");
		String word = request.getParameter("word");
		boolean[] keylist = { false, false, false }; // 전체 , 동 , 아파트

		if (key == null) {
			key = "all";
			word = "";
		}

		List<HouseDeal> house = null;

		if (type != null) {
			for (int i = 0; i < type.length; i++) {
				sendtype[Integer.parseInt(type[i])] = true;
			}
		} else if (type == null) {
			for (int i = 0; i < sendtype.length; i++) {
				sendtype[i] = true;
			}
			type = new String[3];
			type[0] = "1";
			type[1] = "2";
			type[2] = "3";
		}
		System.out.println("SENDTYPE" + sendtype[0] + sendtype[1] + sendtype[2] + sendtype[3] + sendtype[4]);
		PageNavigation pageNavigation = null;
		searchPaging countPaging = new searchPaging(word, type);
		totalsize = houseService.searchcount(countPaging);
		System.out.println("-totalsize:" + totalsize);
		pageNavigation = houseService.makePageNavigation(currentPage, sizePerPage, totalsize);
		searchPaging searchPaging = new searchPaging((currentPage - 1) * sizePerPage, sizePerPage, 99999, word, type);

		if (key.equals("dong")) {
			System.out.println("-dong");
			keylist[1] = true;
			house = houseService.searchDong(searchPaging);
		} else if (key.equals("aptName")) {
			System.out.println("-aptname");
			keylist[2] = true;
			house = houseService.searchAptname(searchPaging);
		} else if (key.equals("all")) {
			System.out.println("-all");
			keylist[0] = true;
			house = houseService.searchAll(searchPaging);
		}

		model.addAttribute("info", sendtype);
		model.addAttribute("houses", house);
		model.addAttribute("navigation", pageNavigation);
		model.addAttribute("keys", keylist);
		model.addAttribute("words", word);
		return "house/list";
	}

	// 아파트 거래번호로 디테일 표시 + 해당 디테일의 동으로 환경정보 검색해서 리스트 출력
	@RequestMapping(value = "/housedetail/{no}", method = RequestMethod.GET)
	String houseDetailNo(Model model, @PathVariable int no) {
		HouseDeal house = new HouseDeal();
		house = houseService.search(no);

		String wheredong = house.getDong();
		wheredong = wheredong.trim();
		System.out.println("House controller [detail] - dong :" + wheredong);

		searchPaging searchPaging = new searchPaging(0, 10, 99999, wheredong);
		List<HouseDeal> houses = houseService.searchAll(searchPaging);
//		System.out.println(houses.size());

		int totalamount = 0;
		List<String> amountlist = null;
		for (int i = 0; i < houses.size(); i++) {
			String onedealamount = houses.get(i).getDealAmount();
			onedealamount = onedealamount.replace(",", "").replace(" ", "");
			int intamount = Integer.parseInt(onedealamount);
			totalamount += intamount;
		}
		totalamount = totalamount / houses.size();

		String thisamount = house.getDealAmount();
		thisamount = thisamount.replace(",", "").replace(" ", "");
		int oneamount = Integer.parseInt(thisamount);

		String msg = "저렴해요!";
		if (oneamount > totalamount)
			msg = "비싸요!";

//		System.out.println("TOTAL:"+totalamount);
		model.addAttribute("msg", msg);
		model.addAttribute("house", house);
		model.addAttribute("totalamount", totalamount);

		return "house/detail";
	}

	// cart 추가
	@RequestMapping(value = "/cart.do", method = RequestMethod.GET)
	String insertCart(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String msg = "로그인이 필요합니다!";
		String command = request.getParameter("command");
		System.out.println("Map controller [search] : " + command);
		String userid = request.getParameter("cartid");
		String aptno = request.getParameter("aptno");
		String dong = request.getParameter("dong");
		System.out.println(userid + "," + aptno + ',' + dong);
		if (userid == null || userid == "") {
			model.addAttribute("msg", msg);
			return "user/userlogin";
		}
		int count = houseService.cartcount(userid);
		System.out.println(count);

		List<rsInfo> list = new ArrayList<rsInfo>();
		List<String> donglist = houseService.distinctdong(userid);
		System.out.println("donglist" + donglist);
		for (int i = 0; i < donglist.size(); i++) {
			String tt = donglist.get(i).replace(" ", "");
			List<rsInfo> temp = rsInfoService.searchAll(tt);

			for (int j = 0; j < temp.size(); j++) {
				list.add(temp.get(j));
			}
		}

		if (count >= 3) {
			msg = "3개 이상 찜할 수 없어요 !";
			List<Integer> aptnolist = houseService.showcart(userid);
			List<HouseDeal> deallist = new ArrayList<HouseDeal>();
			for (int i = 0; i < aptnolist.size(); i++) {
				System.out.println(aptnolist.get(i));
				HouseDeal temp = houseService.search(aptnolist.get(i));
				System.out.println(temp);
				deallist.add(temp);
			}
			model.addAttribute("msg", msg);
			model.addAttribute("rsinfos", list);
			model.addAttribute("cartapts", deallist);
			return "house/cart";
		} else {
			String dd = "'" + dong + "'";

			housecart tempcart = new housecart(userid, Integer.parseInt(aptno), dong);
			int temp = houseService.findaptNo(tempcart);
			if (temp == 0) {
				housecart housecart = new housecart(userid, Integer.parseInt(aptno), dd);
				houseService.insertcart(housecart);
				donglist = houseService.distinctdong(userid);
				System.out.println("donglist" + donglist);
				for (int i = 0; i < donglist.size(); i++) {
					String tt = donglist.get(i).replace(" ", "");
					List<rsInfo> temp1 = rsInfoService.searchAll(tt);

					for (int j = 0; j < temp1.size(); j++) {
						list.add(temp1.get(j));
					}
				}
			} else {

				msg = "이미 등록된 아파트 입니다!";
				List<Integer> aptnolist = houseService.showcart(userid);
				List<HouseDeal> deallist = new ArrayList<HouseDeal>();
				for (int i = 0; i < aptnolist.size(); i++) {
					System.out.println(aptnolist.get(i));
					HouseDeal temp1 = houseService.search(aptnolist.get(i));
					System.out.println(temp1);
					deallist.add(temp1);
				}
				model.addAttribute("rsinfos", list);
				model.addAttribute("cartapts", deallist);
				model.addAttribute("msg", msg);
				return "house/cart";
			}
		}
		List<Integer> aptnolist = houseService.showcart(userid);
		List<HouseDeal> deallist = new ArrayList<HouseDeal>();
		for (int i = 0; i < aptnolist.size(); i++) {
			System.out.println(aptnolist.get(i));
			HouseDeal temp = houseService.search(aptnolist.get(i));
			System.out.println(temp);
			deallist.add(temp);
		}
		model.addAttribute("cartapts", deallist);
		model.addAttribute("rsinfos", list);
		return "house/cart";
	}

	// cart 추가
	@RequestMapping(value = "/gocart/{userid}", method = RequestMethod.GET)
	String showcart(Model model, @PathVariable String userid) {

		System.out.println(userid);
		List<rsInfo> list = new ArrayList<rsInfo>();
		List<String> donglist = houseService.distinctdong(userid);
		System.out.println("donglist" + donglist);
		for (int i = 0; i < donglist.size(); i++) {
			System.out.println(donglist.get(i));
			String tt = donglist.get(i).replace(" ", "");
			List<rsInfo> temp = rsInfoService.searchAll(tt);
			for (int j = 0; j < temp.size(); j++) {
				list.add(temp.get(j));
			}
		}
		List<Integer> aptnolist = houseService.showcart(userid);
		List<HouseDeal> deallist = new ArrayList<HouseDeal>();
		for (int i = 0; i < aptnolist.size(); i++) {
			System.out.println(aptnolist.get(i));
			HouseDeal temp = houseService.search(aptnolist.get(i));
			System.out.println(temp);
			deallist.add(temp);
		}
		model.addAttribute("cartapts", deallist);
		model.addAttribute("rsinfos", list);
		return "house/cart";
	}

	@RequestMapping(value = "/cart/del", method = RequestMethod.GET)
	String delcart(Model model, HttpServletRequest request) {
		String[] type = request.getParameterValues("del");
		String dd = request.getParameter("cartid");
		String userid = "'" + dd + "'";
		if (type == null) {
			return "house/list";
		}
		for (int i = 0; i < type.length; i++) {
			housecart cart = new housecart(userid, Integer.parseInt(type[i]));
			houseService.deletecart(cart);
		}

		List<rsInfo> list = new ArrayList<rsInfo>();
		List<String> donglist = houseService.distinctdong(dd);
		System.out.println("donglist" + donglist);
		for (int i = 0; i < donglist.size(); i++) {
			String tt = donglist.get(i).replace(" ", "");
			System.out.println(tt);
			List<rsInfo> temp = rsInfoService.searchAll(tt);

			for (int j = 0; j < temp.size(); j++) {
				list.add(temp.get(j));
			}
		}
		List<Integer> aptnolist = houseService.showcart(dd);
		List<HouseDeal> deallist = new ArrayList<HouseDeal>();
		for (int i = 0; i < aptnolist.size(); i++) {
			System.out.println(aptnolist.get(i));
			HouseDeal temp = houseService.search(aptnolist.get(i));
			System.out.println(temp);
			deallist.add(temp);
		}
		model.addAttribute("msg", "삭제 했어요!");
		model.addAttribute("cartapts", deallist);
		model.addAttribute("rsinfos", list);

		return "house/cart";

	}

}
