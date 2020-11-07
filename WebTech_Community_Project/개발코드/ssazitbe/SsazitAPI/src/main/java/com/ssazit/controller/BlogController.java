package com.ssazit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssazit.dto.Blog;
import com.ssazit.dto.Myboard;
import com.ssazit.dto.User;
import com.ssazit.service.BlogService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	// Blog 테이블 (블로그) ##############################################################################################################
	
	@ApiOperation(value = "새로운 블로그 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/CUD")
	public ResponseEntity<String> createBlog(Blog blog, HttpServletRequest request){
		System.out.println("controller(Blog) | createBlog");
		System.out.println(blog);
		blog.setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		try {
			if(blogService.createBlog(blog)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "블로그 정보 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/CUD")
	public ResponseEntity<String> updateBlog(Blog blog, HttpServletRequest request){
		System.out.println("controller(Blog) | updateBlog");
		System.out.println(blog);
		blog.setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		try {
			if(blogService.updateBlog(blog)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "블로그 번호에 해당하는 블로그를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/CUD")
	public ResponseEntity<String> deleteBlog(Blog blog, HttpServletRequest request){
		System.out.println("controller(Blog) | deleteBlog");
		System.out.println(blog);
		blog.setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		try {
			if(blogService.deleteBlog(blog)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "블로그 상세보기(blogNo로 검색)", response = Blog.class)
	@GetMapping("/blog")
	public ResponseEntity<Blog> detailBlog(Blog blog){
		System.out.println("controller(Blog) | detailBlog");
		System.out.println(blog);
		try {
			return new ResponseEntity<Blog>(blogService.detailBlog(blog), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "블로그 정보를 검색한다.", response = List.class)
	@GetMapping("/blogs")
	public ResponseEntity<List<Blog>> searchBlog(Blog blog){
		System.out.println("controller(Blog) | searchBlog");
		System.out.println(blog);
		try {
			return new ResponseEntity<List<Blog>>(blogService.searchBlog(blog), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "블로그 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/blogscount")
	public ResponseEntity<Integer> countBlog(Blog blog){
		System.out.println("controller(Blog) | countBlog");
		System.out.println(blog);
		try {
			return new ResponseEntity<Integer>(blogService.countBlog(blog), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "사용자 아이디로 블로그정보를 검색한다.", response = Blog.class)
	@GetMapping("/searchbyuserid")
	public ResponseEntity<Blog> searchbyuserid(String userId){
		try {
			return new ResponseEntity<Blog>(blogService.selectUserBlog(userId), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// Myboard 테이블 (게시판) ##############################################################################################################
	
	@ApiOperation(value = "새로운 게시판 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/myboard/CUD")
	public ResponseEntity<String> createMyboard(Myboard myboard, HttpServletRequest request){
		System.out.println("controller(Blog) | createMyboard");
		System.out.println(myboard);
		try {
			if(blogService.createMyboard(myboard, ((User)request.getAttribute("userinfo")).getUserNo())) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 정보 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/myboard/CUD")
	public ResponseEntity<String> updateMyboard(Myboard myboard, HttpServletRequest request) {
		System.out.println("controller(Blog) | updateMyboard");
		System.out.println(myboard);
		try {
			if(blogService.updateMyboard(myboard, ((User)request.getAttribute("userinfo")).getUserNo()) ) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 번호에 해당하는 게시판을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/myboard/CUD")
	public ResponseEntity<String> deleteMyboard(Myboard myboard, HttpServletRequest request){
		System.out.println("controller(Blog) | deleteMyboard");
		System.out.println(myboard);
		try {
			if(blogService.deleteMyboard(myboard, ((User)request.getAttribute("userinfo")).getUserNo()) ) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 상세보기(boardNo로 검색)", response = Blog.class)
	@GetMapping("/myboard")
	public ResponseEntity<Myboard> detailMyboard(Myboard myboard) throws Exception{
		System.out.println("controller(Blog) | detailMyboard");
		System.out.println(myboard);
		return new ResponseEntity<Myboard>(blogService.detailMyboard(myboard), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 정보를 검색한다.", response = List.class)
	@GetMapping("/myboards")
	public ResponseEntity<List<Myboard>> searchMyboard(Myboard myboard) throws Exception{
		System.out.println("controller(Blog) | searchMyboard");
		System.out.println(myboard);
		return new ResponseEntity<List<Myboard>>(blogService.searchMyboard(myboard), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/myboardscount")
	public ResponseEntity<Integer> countMyboard(Myboard myboard) throws Exception{
		System.out.println("controller(Blog) | countMyboard");
		System.out.println(myboard);
		return new ResponseEntity<Integer>(blogService.countMyboard(myboard), HttpStatus.OK);
	}
	
	@ApiOperation(value = "blogNo, groupNo로 seqNo 최대값 반환", response = Integer.class)
	@GetMapping("/maxseqno")
	public ResponseEntity<Integer> selectSeqNoMax(Myboard myboard){
		try {
			System.out.println("controller(Blog) | selectSeqNoMax");
			System.out.println(myboard);
			return new ResponseEntity<Integer>(blogService.selectSeqNoMax(myboard), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "blogNo로 depth 최대값 반환", response = Integer.class)
	@GetMapping("/maxdepth")
	public ResponseEntity<Integer> selectDepthMax(Myboard myboard){
		try {
			System.out.println("controller(Blog) | selectDepthMax");
			System.out.println(myboard);
			return new ResponseEntity<Integer>(blogService.selectDepthMax(myboard), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 삭제 후보
	@ApiOperation(value = "blogNo로 groupNo 중복없이 반환(삭제후보)", response = List.class)
	@GetMapping("/gndist")
	public ResponseEntity<List<Integer>> selectGroupNoDist(int blogNo){
		try {
			System.out.println("controller(Blog) | selectGroupNoDist");
			System.out.println(blogNo);
			return new ResponseEntity<List<Integer>>(blogService.selectGroupNoDist(blogNo), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "BJH 헌드레드가 특별히 요청한 Myboard의 리스트 정렬 완결판.", response = List.class)
	@GetMapping("/boardlist")
	public ResponseEntity<List<Myboard>> getBoardList(Myboard myboard){
		try {
			System.out.println("controller(Blog) | getBoardList");
			System.out.println(myboard);
			return new ResponseEntity<List<Myboard>>(blogService.getBoardList(myboard), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "상위 게시판을 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/updateRootBoard")
	public ResponseEntity<String> updateRootBoard(Myboard myboard, HttpServletRequest request) {
		System.out.println("controller(Blog) | updateRootBoard");
		System.out.println(myboard);
		try {
			if(blogService.updateRootBoard(myboard, ((User)request.getAttribute("userinfo")).getUserNo()) ) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
}
