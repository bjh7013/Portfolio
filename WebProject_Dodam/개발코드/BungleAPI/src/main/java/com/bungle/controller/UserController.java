package com.bungle.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bungle.dto.DictationCount;
import com.bungle.dto.Parent;
import com.bungle.dto.User;
import com.bungle.service.DictationSubmitService;
import com.bungle.service.KoreandetailService;
import com.bungle.service.UserServiceImpl;
import com.bungle.service.WordTestSubmitService;
import com.bungle.util.JWTUtil;

import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImpl userService;

	@Autowired
	JWTUtil jwtUtil;
	
	@Autowired
	KoreandetailService koreandetailService;
	
	@Autowired
	DictationSubmitService dictationSubmitService;
	
	@Autowired
	WordTestSubmitService wordTestSubmitService;

	@ApiOperation(value = "jwt토큰의 정보를 이용하여 회원정보를 삭제한다.", response = String.class)
	@GetMapping("/delete")
	public Object deleteUser(@RequestParam String userId, @RequestParam String userPw) {

		try {
			userService.deleteUser(userId, userPw);
			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "새로고침시 회원정보를 리로드한다.", response = User.class)
	@GetMapping("/reget")
	public ResponseEntity<User> reloadUser(HttpServletRequest request) {

		User userinfo = (User) request.getAttribute("userinfo");

		User result = userService.getUserbyIdandNo(userinfo);
		System.out.println("자동 재로그인");
		if (result != null) {
			return new ResponseEntity<User>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(result, HttpStatus.UNAUTHORIZED);
		}

	}

	@ApiOperation(value = "회원 가입.", response = String.class)
	@PostMapping("/sign")
	public Object sign(@RequestBody User user) {
		if(userService.selectUserById(user)==1) {
			return new ResponseEntity<String>("same", HttpStatus.BAD_REQUEST);
		}
		ResponseEntity response = null;
		user.setProfileImg("/img/user/default.jpg");
		try {
			System.out.println("회원가입 시도중");
			userService.createUser(user);
			response = new ResponseEntity<String>("success", HttpStatus.OK);
			System.out.println("회원가입완료");
		} catch (Exception e) {
			response = new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@ApiOperation(value = "회원 정보 수정.", response = String.class)
	@PutMapping("/update")
	public Object updateUser(@RequestBody User user) {
		ResponseEntity response = null;
		try {
			userService.updateUser(user);
			response = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@ApiOperation(value = "회원 로그인 로그인시 jwt 토큰 생성하여 리턴.", response = String.class)
	@PostMapping("/login")
	public Object defaultLogin(@RequestBody HashMap<String, String> loginInfo) {
		ResponseEntity response = null;
		HashMap<String, Object> map = new HashMap<>();

		try {
			User user = userService.getUserbyIdandPwd(loginInfo.get("userId"), loginInfo.get("userPw"));

//			map.put("jwtToken",
//					jwtTokenProvider.createToken(user.getUserId(), Collections.singletonList("USER")));
//			map.put("userId", user.getUserId());
//			map.put("userNo", user.getUserNo());
//			map.put("message", "success");

//			System.out.println(response.toString());

			HttpHeaders headers = new HttpHeaders();
			List<String> exposeHeaders = new ArrayList<>();

//			jwt토큰 발행
			headers.setBearerAuth(jwtUtil.createToken(user));

			exposeHeaders.add("Authorization");

			headers.setAccessControlExposeHeaders(exposeHeaders);

			response = new ResponseEntity<>(user, headers, HttpStatus.OK);

		} catch (Exception e) {
			map.put("message", "fail");
			response = new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;

	}

	@ApiOperation(value = "회원정보를 보낸다.", response = User.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<User> infoUser(HttpServletRequest request, @PathVariable("userId") String userId) {
		User userinfo = new User();
		userinfo.setUserId(userId);

		User result = userService.getUserInfo(userinfo);
		if (result != null) {
			return new ResponseEntity<User>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(result, HttpStatus.UNAUTHORIZED);
		}
	}

	@ApiOperation(value = "회원정보를 보낸다.(메인용)", response = User.class)
	@GetMapping("/info")
	public ResponseEntity<User> infoUserMain(HttpServletRequest request) {
		User userinfo = (User) request.getAttribute("userinfo");
		System.out.println("유저정보/"+userinfo.toString());
		User result = userService.getUserInfo(userinfo);
		
		if (result != null) {
			return new ResponseEntity<User>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(result, HttpStatus.UNAUTHORIZED);
		}
	}

	@ApiOperation(value = "부모님 신청", response = String.class)
	@GetMapping("/makeparent/{userNo}")
	public ResponseEntity<String> makeParent(HttpServletRequest request, @PathVariable("userNo") int userNo) {
		User userinfo = (User) request.getAttribute("userinfo");
		Parent parent = new Parent();
		parent.setUserNo(userNo);
		parent.setChildNo(userinfo.getUserNo());
		Parent temp = null;
		temp = userService.checkParent(parent);
		if(temp!=null) {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);	
		}
		int result = userService.makeParent(parent);
		if(result==1) {			
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);			
		}
	}
	
	@ApiOperation(value = "아이 정보 가져오기", response = List.class)
	@GetMapping("/childreninfo")
	public ResponseEntity<List<User>> childrenInfo(HttpServletRequest request) {
		User userinfo = (User) request.getAttribute("userinfo");
		Parent parent = new Parent();
		parent.setUserNo(userinfo.getUserNo());
		List<User> result = userService.childrenInfo(parent);
		if(result!=null) {
			return new ResponseEntity<List<User>>(result, HttpStatus.OK);
		}else {			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "아이 삭제하기", response = String.class)
	@DeleteMapping("/child/{childNo}")
	public ResponseEntity<String> deleteChild(HttpServletRequest request,@PathVariable("childNo") int childNo) {
		User userinfo = (User) request.getAttribute("userinfo");
		Parent parent = new Parent();
		parent.setUserNo(userinfo.getUserNo());
		parent.setChildNo(childNo);
		int result = userService.deleteChild(parent);
		if(result==1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}else {			
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "자기 정보 가져오기", response = User.class)
	@GetMapping("/myinfo")
	public ResponseEntity<User> myInfo(HttpServletRequest request) {
		User userinfo = (User) request.getAttribute("userinfo");
		User result = null;
		result = userService.myInfo(userinfo);
		if(result!=null) {
			return new ResponseEntity<User>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@ApiOperation(value = "자기 정보 가져오기", response = User.class)
	@GetMapping("/userstatus")
	public ResponseEntity<Map<String,Object>> userStatus(HttpServletRequest request) throws Exception {
		User userinfo = (User) request.getAttribute("userinfo"); 
		int userNo=userinfo.getUserNo();
		Map<String, Object> map=new HashMap<String,Object>();		
		map.put("korean",koreandetailService.selectUserCorrectCount(userNo));
		map.put("dictation",dictationSubmitService.getSubmitCount(userNo));
		map.put("dictationLevel",dictationSubmitService.getSubmitGroupLevel(userNo));
		map.put("wordtest", wordTestSubmitService.getSubmitCount(userNo));
		map.put("wordtestCate", wordTestSubmitService.getSubmitGroupCategory(userNo));
		
		List<DictationCount> list=dictationSubmitService.getSubmitGroupLevel(userNo);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).getDictationCount()+" "+list.get(i).getDictationLevel());
		}

		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
	@ApiOperation(value = "자기 정보 가져오기", response = User.class)
	@GetMapping("/userstatusbyuserno/{userNo}")
	public ResponseEntity<Map<String,Object>> userStatusByuserNo(@PathVariable int userNo) throws Exception {

		Map<String, Object> map=new HashMap<String,Object>();		
		map.put("korean",koreandetailService.selectUserCorrectCount(userNo));
		map.put("dictation",dictationSubmitService.getSubmitCount(userNo));
		map.put("dictationLevel",dictationSubmitService.getSubmitGroupLevel(userNo));
		map.put("wordtest", wordTestSubmitService.getSubmitCount(userNo));
		map.put("wordtestCate", wordTestSubmitService.getSubmitGroupCategory(userNo));
		
		List<DictationCount> list=dictationSubmitService.getSubmitGroupLevel(userNo);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).getDictationCount()+" "+list.get(i).getDictationLevel());
		}

		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
	
	

}
