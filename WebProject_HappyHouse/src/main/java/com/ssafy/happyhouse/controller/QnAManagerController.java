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
import com.ssafy.happyhouse.dto.QnA;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.service.QnAService;
import com.ssafy.happyhouse.util.PageNavigation;

@Controller
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/qnam")
public class QnAManagerController {
	
	@Autowired
	QnAService qnaservice;
	
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	String qnamain(Model model) {
		return "qna/main";
	}
	
	@RequestMapping(value = "/totallist.do", method = RequestMethod.GET)
	String qnatotallist(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		// --페이지 찾깅
		String pg = request.getParameter("pg"); // 현재페이지
		String title = request.getParameter("word"); // 검색어
		int currentPage = pg == null ? 1 : Integer.parseInt(pg);
		String spp = request.getParameter("spp"); // 한 페이지당 글 갯수
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);
		int totalsize = 1000;
		System.out.println("qnaManager controller [search] - cur:" + currentPage + ", spp:" + sizePerPage);
		
		String[] type = request.getParameterValues("type");
		
		String key = "all";
		String word = title;
		List<QnA> qna = null;
		
		PageNavigation pageNavigation = null;
		searchPaging countPaging = new searchPaging(word);
		totalsize = qnaservice.searchcount(countPaging);
		System.out.println("-totalsize:"+totalsize);
		pageNavigation = qnaservice.makePageNavigation(currentPage, sizePerPage, totalsize);
		searchPaging searchPaging = new searchPaging((currentPage - 1) * sizePerPage, sizePerPage, 99999, word);
		qna = qnaservice.searchAllQnA(searchPaging);
		
		model.addAttribute("navigation", pageNavigation);
		model.addAttribute("qnas",qna);
		model.addAttribute("words",word);
		return "qna/totalqna";
	}
	
	@RequestMapping(value = "/nolist.do", method = RequestMethod.GET)
	String qnanolist(Model model) {
		return "qna/noanswerqna";
	}
	
	@RequestMapping(value = "/detailqna.do/{qnaNo}", method = RequestMethod.GET)
	String detailnotice(Model model,@PathVariable int qnaNo) {
		QnA qna = new QnA();
		qna = qnaservice.detailQnA(qnaNo);
		model.addAttribute("qna",qna);
		return "qna/detailqna";
	}
	
	@RequestMapping(value = "/modifycomment.do", method = RequestMethod.POST)
	String modifynoticereal(Model model, QnA qna) {
		qna.setReplyCheck("답변완료");
		System.out.println(qna.getReplyContent());
		System.out.println(qna.getQnaNo());
		qnaservice.updateQnAcomment(qna);
		return "redirect:/api/qnam/detailqna.do/"+qna.getQnaNo();
	}
	
	@RequestMapping(value = "/deleteqna.do/{qnaNo}", method = RequestMethod.GET)
	String deletenotice(@PathVariable int qnaNo) {
		qnaservice.deleteQnA(qnaNo);
		return "redirect:/api/qnam/totallist.do";
	}
}
