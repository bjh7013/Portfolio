package com.bungle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bungle.dto.Clazz;
import com.bungle.dto.Post;
import com.bungle.service.PostService;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@ApiOperation(value = "포스트 생성하기", response = String.class)
	@PostMapping("/post")
	public ResponseEntity<String> createPost(@RequestBody Post post) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(post) | createPost");
		if (postService.createPost(post) == true) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}
	@ApiOperation(value = "포스트 수정하기", response = String.class)
	@PutMapping("/post")
	public ResponseEntity<String> updatePost(@RequestBody Post post) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(post) | updatePost");
		if (postService.updatePost(post) == true) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "포스트 삭제하기", response = String.class)
	@DeleteMapping("/post")
	public ResponseEntity<String> deletePost(@RequestBody Post post) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(post) | deletePost");
		if (postService.deletePost(post) == true) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "포스트 하나 선택", response = Post.class)
	@GetMapping("/post/{postNo}")
	public ResponseEntity<Post> selectOnePost(@PathVariable int postNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(post) | selectOnePost");
		Post post=postService.selectOnePost(postNo);
		if (post!=null) {
			return new ResponseEntity<Post>(post, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@ApiOperation(value = "포스트 전체 선택", response = List.class)
	@GetMapping("/post")
	public ResponseEntity<List<Post>> selectAllPost(@PathVariable int postNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(post) | selectAllPost");
		List<Post> list=postService.selectAllPost();
		if (list!=null) {
			return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@ApiOperation(value = "유저 포스트 검색하기", response = List.class)
	@GetMapping("/selectuserpost")
	public ResponseEntity<List<Post>> selectUserPost(HttpServletRequest request) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(post) | selectUserPost");
		int writerNo = 1;
		List<Post> list=postService.selectUserPost(writerNo);
		if (list != null) {
			return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
