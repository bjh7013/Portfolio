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
import com.ssafy.happyhouse.dto.News;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.service.NewsService;
import com.ssafy.happyhouse.util.PageNavigation;

@Controller
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/news")
public class NewsController {
	
	@Autowired
	NewsService newsservice;
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	String newslist(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		// --페이지 찾깅
		String pg = request.getParameter("pg"); // 현재페이지
		String title = request.getParameter("word"); // 검색어
		int currentPage = pg == null ? 1 : Integer.parseInt(pg);
		String spp = request.getParameter("spp"); // 한 페이지당 글 갯수
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);
		int totalsize = 1000;
		System.out.println("news controller [search] - cur:" + currentPage + ", spp:" + sizePerPage);
		
		String[] type = request.getParameterValues("type");
		
		String key = "all";
		String word = title;
		List<News> news = null;
		
		PageNavigation pageNavigation = null;
		searchPaging countPaging = new searchPaging(word);
		totalsize = newsservice.searchcount(countPaging);
		System.out.println("-totalsize:"+totalsize);
		pageNavigation = newsservice.makePageNavigation(currentPage, sizePerPage, totalsize);
		searchPaging searchPaging = new searchPaging((currentPage - 1) * sizePerPage, sizePerPage, 99999, word);
		news = newsservice.selectNewsAll(searchPaging);
		
		model.addAttribute("navigation", pageNavigation);
		model.addAttribute("news",news);
		model.addAttribute("words",word);
		return "news/list";
	}
	
	@RequestMapping(value = "/writenews.do", method = RequestMethod.GET)
	String writenews(Model model) {
		return "news/write";
	}
	
	@RequestMapping(value = "/deletenews.do/{newsno}", method = RequestMethod.GET)
	String deletenews(Model model, @PathVariable int newsno) {
		News news = new News();
		news.setNewsno(newsno);
		newsservice.deleteNews(news);
		return "redirect:/api/news/list.do";
	}

	@RequestMapping(value = "/writenews.do", method = RequestMethod.POST)
	String writenewsreal(News news,Model model) {
		newsservice.createNews(news);
		return "redirect:/api/news/list.do";
	}
}
