package com.ssafy.happyhouse.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssafy.happyhouse.dto.CommercialInfo;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.SidoCode;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.service.HouseService;
import com.ssafy.happyhouse.service.MapService;
import com.ssafy.happyhouse.util.PageNavigation;

@Controller
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/map")
public class MapController {
	@Autowired
	MapService service;
	@Autowired
	HouseService houseservice;
	
	@RequestMapping(value = "/mapshow", method = RequestMethod.GET)
	String showmap() {
		return "index";
	}
	
	
	@RequestMapping(value = "/mapsearch", method = RequestMethod.GET , produces = "application/json; charset=UTF-8")
	void searchmap(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException ,NullPointerException{
		request.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		System.out.println("Map controller [search] : "+command);
		
		if (command.equals("sido")) {
			
			List<SidoCode> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = service.selectSido();
				
				for (SidoCode dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("sido_code", dto.getSido_code());
					obj.put("sido_name", dto.getSido_name());
					arr.add(obj);
					
					 System.out.println(dto.getSido_name());
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				response.setCharacterEncoding("UTF-8"); 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print(arr.toJSONString());
				out.close();
			}
		} // sido
		else if (command.equals("gugun")) {
			String sido = request.getParameter("sido");
			List<SidoCode> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = service.selectGugun(sido);
				for (SidoCode dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("gugun_code", dto.getGugun_code());
					obj.put("gugun_name", dto.getGugun_name());
					arr.add(obj);
					System.out.println(dto.getGugun_name());
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				response.setCharacterEncoding("UTF-8"); 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print(arr.toJSONString());
				out.close();
			}
		} // gugun
		else if (command.equals("dong")) {
			String gugun = request.getParameter("gugun");
			List<HouseInfo> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = service.selectDong(gugun);
				for (HouseInfo dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("code", dto.getCode());
					obj.put("dong", dto.getDong());
					System.out.println(dto.getDong());
					arr.add(obj);
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				response.setCharacterEncoding("UTF-8"); 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print(arr.toJSONString());
				out.close();
			}
		} // dong
		else if(command.equals("comm")) {
			String dong = request.getParameter("dong");
			List<CommercialInfo> list = null;
			List<HouseInfo> list2 = null;
			JSONArray arr = new JSONArray();
			try {
				list = service.selectCommercial(dong);
				for(CommercialInfo dto : list) {
					JSONObject obj = new JSONObject();
					
					obj.put("dong", dto.getDong());
					obj.put("shopname", dto.getShopname());
					obj.put("codename", dto.getCodename());
					obj.put("address", dto.getAddress());
					obj.put("lat", dto.getLat());
					obj.put("lng", dto.getLng());
					arr.add(obj);
				}
				list2 = houseservice.searchDongformap(dong);
				System.out.println(list2);
				for(HouseInfo dto: list2) {
					JSONObject obj = new JSONObject();
					String aptaddress = dto.getDong()+dto.getJibun();
					obj.put("dong",dto.getDong());
					obj.put("lat", dto.getLat());
					obj.put("lng", dto.getLng());
					obj.put("codename", "아파트");
					obj.put("address", aptaddress);
					obj.put("shopname", dto.getAptName());
					arr.add(obj);
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				response.setCharacterEncoding("UTF-8"); 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print(arr.toJSONString());
				out.close();
			}
		}//commercial
	
	}

}
