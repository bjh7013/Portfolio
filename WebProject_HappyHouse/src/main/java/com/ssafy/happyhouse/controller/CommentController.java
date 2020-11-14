package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssafy.happyhouse.dto.Comment;
import com.ssafy.happyhouse.service.CommentService;

@Controller
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	CommentService commentservice;

	
	@RequestMapping(value = "/createcomment.do", method = RequestMethod.POST)
	String createcomment(Model model, Comment comment) {
		commentservice.createComment(comment);
		int no = comment.getArticleno();
		return "redirect:/api/board/detailnotice.do/"+no;
	}
	
	@RequestMapping(value = "/modifycomment.do", method = RequestMethod.GET)
	String modifycomment(Model model, Comment comment) {
		commentservice.createComment(comment);
		int no = comment.getArticleno();
		return "redirect:/api/board/detailnotice.do/"+no;
	}
	
	@RequestMapping(value = "/deletecomment.do/{articleno}/{commentno}", method = RequestMethod.GET)
	String deletecomment(Model model, @PathVariable int articleno, @PathVariable int commentno) {
		Comment comment = new Comment();
		comment.setCommentno(commentno);
		comment.setArticleno(articleno);
		commentservice.deleteComment(comment);
		int no = comment.getArticleno();
		return "redirect:/api/board/detailnotice.do/"+no;
	}
}
