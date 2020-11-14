package com.ssafy.happyhouse.controller;

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

import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.Comment;
import com.ssafy.happyhouse.dto.FAQ;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.service.FAQService;
import com.ssafy.happyhouse.util.PageNavigation;

@Controller
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/faq")
public class FaqController {
	
	@Autowired
	FAQService faqservice;

	
	@RequestMapping(value = "/faqlist.do", method = RequestMethod.GET)
	String faqlist(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		// --페이지 찾깅
		String pg = request.getParameter("pg"); // 현재페이지
		String title = request.getParameter("word"); // 검색어
		int currentPage = pg == null ? 1 : Integer.parseInt(pg);
		String spp = request.getParameter("spp"); // 한 페이지당 글 갯수
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);
		int totalsize = 1000;
		System.out.println("FAQ controller [search] - cur:" + currentPage + ", spp:" + sizePerPage);
		
		String[] type = request.getParameterValues("type");
		
		String key = "all";
		String word = title;
		List<FAQ> faq = null;
		
		PageNavigation pageNavigation = null;
		searchPaging countPaging = new searchPaging(word);
		totalsize = faqservice.searchcount(countPaging);
		System.out.println("-totalsize:"+totalsize);
		pageNavigation = faqservice.makePageNavigation(currentPage, sizePerPage, totalsize);
		searchPaging searchPaging = new searchPaging((currentPage - 1) * sizePerPage, sizePerPage, 99999, word);
		faq = faqservice.selectAllFAQ(searchPaging);
		System.out.println(faqservice.selectAllFAQ(searchPaging));
		model.addAttribute("navigation", pageNavigation);
		model.addAttribute("faqs",faq);
		model.addAttribute("words",word);
		return "faq/list";
	}
	
	@RequestMapping(value = "/writefaq.do", method = RequestMethod.GET)
	String writenotice(Model model) {
		return "faq/write";
	}
	
	@RequestMapping(value = "/writefaq.do", method = RequestMethod.POST)
	String writenoticereal(FAQ faq,Model model) {
		faqservice.writeFAQ(faq);
		
		return "redirect:/api/faq/faqlist.do";
	}
	
	@RequestMapping(value = "/deletefaq.do/{faqno}", method = RequestMethod.GET)
	String deletefaq(@PathVariable int faqno) {
		faqservice.deleteFAQ(faqno);
		return "redirect:/api/faq/faqlist.do";
	}
	
	@RequestMapping(value = "/detailfaq.do/{faqno}", method = RequestMethod.GET)
	String detailfaq(Model model,@PathVariable int faqno) {
		System.out.println("더로옴");
		FAQ faq = new FAQ();
		faq = faqservice.detailFAQ(faqno);
		model.addAttribute("faq",faq);
		return "faq/detail";
	}
	
	@RequestMapping(value = "/modifyfaq.do/{faqno}", method = RequestMethod.GET)
	String modifynotice(Model model,@PathVariable int faqno) {
		FAQ faq = new FAQ();
		faq.setFaqno(faqno);
		FAQ list = faqservice.detailFAQ(faqno);
		model.addAttribute("faq",list);
		return "faq/modify";
	}
	
	@RequestMapping(value = "/modifyfaq.do", method = RequestMethod.POST)
	String modifynoticereal(Model model, FAQ faq) {
		faqservice.updateFAQ(faq);
		return "redirect:/api/faq/faqlist.do";
	}
	
}
