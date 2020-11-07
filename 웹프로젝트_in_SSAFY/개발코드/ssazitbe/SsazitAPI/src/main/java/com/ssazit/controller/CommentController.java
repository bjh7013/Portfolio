package com.ssazit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssazit.dto.Comment;
import com.ssazit.dto.Commentlike;
import com.ssazit.dto.User;
import com.ssazit.service.CommentService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	// Comment 테이블 (댓글)
	// #########################################################################################################

	@ApiOperation(value = "댓글을 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/CUD")
	public ResponseEntity<String> createComment(@RequestBody Comment comment, HttpServletRequest request) {
		System.out.println("controller(Comment) | createComment");
		comment.setUserNo(((User) request.getAttribute("userinfo")).getUserNo());
		System.out.println(comment);
		if (commentService.createComment(comment)) {
			System.out.println("sendsendsendsendsendsendsendsendsendsendsendsendsendsendsendsendsend");
		
			
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "댓글을 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/CUD")
	public ResponseEntity<String> updateComment(@RequestBody Comment comment, HttpServletRequest request) {
		System.out.println("controller(Comment) | updateComment");
		comment.setUserNo(((User) request.getAttribute("userinfo")).getUserNo());
		System.out.println(comment);
		if (commentService.updateComment(comment)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	@ApiOperation(value = "댓글을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/CUD")
	public ResponseEntity<String> deleteComment(Comment comment) {
		System.out.println("controller(Comment) | deleteComment");
		System.out.println(comment);
		if (commentService.deleteComment(comment)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "댓글 상세보기", response = Comment.class)
	@GetMapping("/comment")
	public ResponseEntity<Comment> detailComment(Comment comment) throws Exception {
		System.out.println("controller(Comment) | detailComment");
		System.out.println(comment);
		return new ResponseEntity<Comment>(commentService.detailComment(comment), HttpStatus.OK);
	}

	@ApiOperation(value = "댓글을 검색한다.", response = List.class)
	@GetMapping("/comments")
	public ResponseEntity<List<Comment>> searchComment(Comment comment) throws Exception {
		System.out.println("controller(Comment) | searchComment");
		System.out.println(comment);
		return new ResponseEntity<List<Comment>>(commentService.searchComment(comment), HttpStatus.OK);
	}

	@GetMapping("/mycomments")
	public ResponseEntity<List<Comment>> getMyComments(HttpServletRequest request) {
		System.out.println("controller(Comment) | myComment");
		Comment comment = new Comment();
		comment.setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		return new ResponseEntity<List<Comment>>(commentService.myComment(comment), HttpStatus.OK);
	}
	@GetMapping("/mycommentscount")
	public ResponseEntity<Integer> getMyCommentsCount(HttpServletRequest request) {
		System.out.println("controller(Comment) | myCommentsCount");
		Comment comment = new Comment();
		comment.setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		return new ResponseEntity<>(commentService.myCommentCount(comment), HttpStatus.OK);
	}
	
	@GetMapping("/mybookcomments")
	public ResponseEntity<List<Comment>> getMyBookComments(HttpServletRequest request) {
		System.out.println("controller(Comment) | getMyBookComments");
		Comment comment = new Comment();
		comment.setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		return new ResponseEntity<List<Comment>>(commentService.myBookComment(comment), HttpStatus.OK);
	}

	@ApiOperation(value = "댓글 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/commentscount")
	public ResponseEntity<Integer> countComment(Comment comment) throws Exception {
		System.out.println("controller(Comment) | countComment");
		System.out.println(comment);
		return new ResponseEntity<Integer>(commentService.countComment(comment), HttpStatus.OK);
	}

	@ApiOperation(value = "내가 쓴 책 댓글 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/mybookcommentscount")
	public ResponseEntity<Integer> countMyBookComment(HttpServletRequest request) throws Exception {
		System.out.println("controller(Comment) | countMyBookComment");
		Comment comment = new Comment();
		comment.setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		return new ResponseEntity<Integer>(commentService.countMyBookComment(comment), HttpStatus.OK);
	}
	
	// Commentlike 테이블 (댓글 좋아요)

	@ApiOperation(value = "댓글  좋아요를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/commentlike/CUD")
	public ResponseEntity<String> createCommentlike(@RequestBody Commentlike commentlike, HttpServletRequest request) {
		System.out.println("controller(Comment) | createCommentlike");
		commentlike.setUserNo(((User) request.getAttribute("userinfo")).getUserNo());
		System.out.println(commentlike);
		if (commentService.hitCommentlike(commentlike)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "댓글 좋아요를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/commentlike/CUD")
	public ResponseEntity<String> updateCommentlike(Commentlike commentlike) {
		System.out.println("controller(Comment) | updateCommentlike");
		System.out.println(commentlike);
		if (commentService.updateCommentlike(commentlike)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "댓글 좋아요를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/commentlike/CUD")
	public ResponseEntity<String> deleteCommentlike(Commentlike commentlike) {
		System.out.println("controller(Comment) | deleteCommentlike");
		System.out.println(commentlike);
		if (commentService.deleteCommentlike(commentlike)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "댓글 좋아요 상세보기", response = Commentlike.class)
	@GetMapping("/commentlike")
	public ResponseEntity<Commentlike> detailCommentlike(Commentlike commentlike) throws Exception {
		System.out.println("controller(Comment) | detailCommentlike");
		System.out.println(commentlike);
		return new ResponseEntity<Commentlike>(commentService.detailCommentlike(commentlike), HttpStatus.OK);
	}

	@ApiOperation(value = "댓글 좋아요를 검색한다.", response = List.class)
	@GetMapping("/commentlikes")
	public ResponseEntity<List<Commentlike>> searchCommentlike(Commentlike commentlike) throws Exception {
		System.out.println("controller(Comment) | searchCommentlike");
		System.out.println(commentlike);
		return new ResponseEntity<List<Commentlike>>(commentService.searchCommentlike(commentlike), HttpStatus.OK);
	}

	@ApiOperation(value = "댓글 좋아요 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/commentlikescount")
	public ResponseEntity<Integer> countCommentlike(Commentlike commentlike) throws Exception {
		System.out.println("controller(Comment) | countCommentlike");
		System.out.println(commentlike);
		return new ResponseEntity<Integer>(commentService.countCommentlike(commentlike), HttpStatus.OK);
	}

	@PutMapping("/pick")
	public ResponseEntity<String> pickComment(@RequestBody Comment comment, HttpServletRequest request) {
		comment.setUserNo(((User) request.getAttribute("userinfo")).getUserNo());
		try {
			System.out.println("======================================================");
			System.out.println(comment);
			if (commentService.pickComment(comment)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
