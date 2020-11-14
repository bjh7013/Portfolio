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
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.service.BoardService;
import com.ssafy.happyhouse.service.CommentService;
import com.ssafy.happyhouse.util.PageNavigation;

@Controller
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/board")
public class BoardController {
	
	
	@Autowired
	BoardService boardservice;
	
	@Autowired
	CommentService commentservice;
	
	@RequestMapping(value = "/notice.do", method = RequestMethod.GET)
	String noticelist(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		// --페이지 찾깅
		String pg = request.getParameter("pg"); // 현재페이지
		String title = request.getParameter("word"); // 검색어
		int currentPage = pg == null ? 1 : Integer.parseInt(pg);
		String spp = request.getParameter("spp"); // 한 페이지당 글 갯수
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);
		int totalsize = 1000;
		System.out.println("Board controller [search] - cur:" + currentPage + ", spp:" + sizePerPage);
		

		
		String key = "all";
		String word = title;
		List<Board> board = null;
		
		
		
		PageNavigation pageNavigation = null;
		searchPaging countPaging = new searchPaging(word);
		totalsize = boardservice.searchcount(countPaging);
		System.out.println("-totalsize:"+totalsize);
		pageNavigation = boardservice.makePageNavigation(currentPage, sizePerPage, totalsize);
		searchPaging searchPaging = new searchPaging((currentPage - 1) * sizePerPage, sizePerPage, 99999, word);
		board = boardservice.selectBoardAll(searchPaging);
		
		model.addAttribute("navigation", pageNavigation);
		model.addAttribute("notices",board);
		model.addAttribute("words",word);
		return "notice/list";
	}
	
	@RequestMapping(value = "/detailnotice.do/{articleno}", method = RequestMethod.GET)
	String detailnotice(Model model,@PathVariable int articleno) {
		Board board = new Board();
		board.setArticleno(articleno);
		boardservice.clickedBoard(board);
		board = boardservice.detailBoard(board);
		Comment comment = new Comment();
		comment.setArticleno(articleno);
		model.addAttribute("notice",board);
		model.addAttribute("comments",commentservice.selectCommentAll(comment));
		return "notice/detailnotice";
	}
	
	@RequestMapping(value = "/writenotice.do", method = RequestMethod.GET)
	String writenotice(Model model) {
		return "notice/write";
	}
	
	@RequestMapping(value = "/qna.do", method = RequestMethod.GET)
	String goqna(Model model) {
		return "qna/list";
	}
	
	@RequestMapping(value = "/modifynotice.do/{articleno}", method = RequestMethod.GET)
	String modifynotice(Model model,@PathVariable int articleno) {
		System.out.println(articleno);
		Board board = new Board();
		board.setArticleno(articleno);
		List<Board> list = boardservice.selectBoard(board);
		board = list.get(0);
		model.addAttribute("notice",board);
		return "notice/modify";
	}
	
	@RequestMapping(value = "/modifynotice.do", method = RequestMethod.POST)
	String modifynoticereal(Model model, Board board) {
		boardservice.modifyBoard(board);
		return "redirect:/api/board/notice.do";
	}
	
	@RequestMapping(value = "/deletenotice.do/{articleno}", method = RequestMethod.GET)
	String deletenotice(@PathVariable int articleno) {
		Board board = new Board();
		board.setArticleno(articleno);
		boardservice.deleteBoard(board);
		return "redirect:/api/board/notice.do";
	}
	
	@RequestMapping(value = "/writenotice.do", method = RequestMethod.POST)
	String writenoticereal(Board board,Model model) {
		boardservice.createBoard(board);
		return "redirect:/api/board/notice.do";
	}
	
	
	
}
