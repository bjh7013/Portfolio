package com.bungle.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
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
import org.springframework.web.multipart.MultipartFile;

import com.bungle.dto.ClassJoinList;
import com.bungle.dto.ClassSignUp;
import com.bungle.dto.Clazz;
import com.bungle.dto.User;
import com.bungle.service.ClassService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/class")
public class ClassController {

	@Autowired
	private ClassService classservice;

	// @Value("${file.path.class}")
	// private String UPLOAD_PATH;
	@Value("${static_path}")
	private String staticPath;
	
	@ApiOperation(value = "클래스 생성하기", response = String.class)
	@PostMapping("/class")
	public ResponseEntity<String> createClass(HttpServletRequest request, @RequestBody Clazz clazz) throws Exception {
		User user = (User) request.getAttribute("userinfo");
		clazz.setUserNo(user.getUserNo());
		System.out.println("====================================================");
		System.out.println("controller(Class) | createClass");
		if (classservice.createClass(clazz) > 0) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "클래스 이미지 수정하기", response = String.class)
	@PutMapping("/imgclass")
	public ResponseEntity<String> createClassImg(@RequestParam(value = "file", defaultValue = "0") MultipartFile file,
			@RequestParam(value = "classNo", defaultValue = "0") int classNo, HttpServletRequest request)
			throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(Class) | create or update ClassImg");
		if(file!=null) {
			System.out.println("파일 이름(원본) : " + file.getOriginalFilename());
			System.out.println("파일 형식(원본) : " + file.getContentType());
			System.out.println("파일 크기 : " + file.getSize());
		}
		User user = (User) request.getAttribute("userinfo");
		if (user.getUserNo() == 0) {
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		Clazz imgclass = new Clazz();
		int no = 0;
		if (classNo > 0) { // 지정된 클래스의 이미지 수정
			no = classNo;
		} else {
			imgclass.setUserNo(user.getUserNo());
			no = classservice.selectUserOneClass(imgclass);
		}

		System.out.println(no);
		if(file!=null) {
			String[] tmp = file.getContentType().split("/");
			String filename = Integer.toString(no) + "_class." + tmp[tmp.length - 1].toLowerCase();
			System.out.println("파일 이름(저장) : " + filename);
			String path = staticPath;
			String dbdata = "/img/class/" + filename;
			System.out.println(file);
			File existfile = new File(path + dbdata);
			if (existfile.exists()) {
				existfile.delete();
			}
			File target = new File(path, dbdata);
			FileCopyUtils.copy(file.getBytes(), target);
	
			int success = classservice.updateClassImg(no, dbdata); // DB수정여부
			if (success > 0) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
			}
		}
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@ApiOperation(value = "클래스 수정하기", response = String.class)
	@PutMapping("/class")
	public ResponseEntity<String> updateClass(@RequestBody Clazz clazz, HttpServletRequest request) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(Class) | updateClass");
		User user = (User) request.getAttribute("userinfo");
		if (user.getUserNo() == 0) {
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		clazz.setUserNo(user.getUserNo());
		if (classservice.updateClass(clazz) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "클래스 하나 삭제하기", response = String.class)
	@DeleteMapping("/oneclass/{classNo}")
	public ResponseEntity<String> deleteOneClass(@PathVariable("classNo") int classNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(Class) | deleteOneClass");
		System.out.println(classNo);
		Clazz clazz = new Clazz();
		clazz.setClassNo(classNo);
		if (classservice.deleteOneClass(clazz) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "유저의 모든 클래스 삭제하기", response = String.class)
	@DeleteMapping("/userclass")
	public ResponseEntity<String> deleteUserClass(@RequestBody Clazz clazz) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(Class) | deleteUserClass");

		if (classservice.deleteUserClass(clazz) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "클래스 하나 검색하기", response = Clazz.class)
	@GetMapping("/oneclass/{classNo}")
	public ResponseEntity<Clazz> selectOneClass(@PathVariable("classNo") int classNo, HttpServletRequest request) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(Class) | selectOneClass");
		User user = (User) request.getAttribute("userinfo");
		if (user.getUserNo() == 0) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		Clazz selectclass = null;
		Clazz clazz = new Clazz();
		clazz.setClassNo(classNo);
		selectclass = classservice.selectOneClass(clazz);
		if(selectclass.getUserNo()==user.getUserNo()) {
			selectclass.setCheckOwner(true);
		}
		System.out.println(selectclass.toString());
		if (selectclass != null) {
			return new ResponseEntity<Clazz>(selectclass, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "유저 클래스 검색하기", response = List.class)
	@GetMapping("/userclass")
	public ResponseEntity<List<Clazz>> selectUserClass(HttpServletRequest request) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(Class) | selectUserClass");
		Clazz clazz = new Clazz();
		User user = (User) request.getAttribute("userinfo");
	
		System.out.println(user.getUserType() + "=================================================");
		List<Clazz> selectclass = null;
		if(user.getUserType().equals("teacher")) {
			clazz.setUserNo(user.getUserNo());
			selectclass = classservice.selectUserClass(clazz);			
		}else if(user.getUserType().equals("student")) {
			selectclass = classservice.selectStudentClass(user);
		}else {
			System.out.println("유효하지 않은 타입");
		}
		
		if (selectclass != null) {
			return new ResponseEntity<List<Clazz>>(selectclass, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "모든 클래스 검색하기(public만)", response = List.class)
	@GetMapping("/allclass")
	public ResponseEntity<List<Clazz>> selectAllClass(HttpServletRequest request) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(Class) | selectAllClass");

		User user = (User) request.getAttribute("userinfo");
		List<Clazz> selectclass = null;
		Clazz clazz = new Clazz();
		clazz.setUserNo(user.getUserNo());
		selectclass = classservice.selectAllClass(clazz);

		if (selectclass != null) {
			return new ResponseEntity<List<Clazz>>(selectclass, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "클래스 가입신청하기", response = String.class)
	@PostMapping("/joincall")
	public ResponseEntity<String> joinClassCall(@RequestBody ClassSignUp classsignup, HttpServletRequest request) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(Class) | joinClassCall");
		User user = (User) request.getAttribute("userinfo");
		classsignup.setStudentNo(user.getUserNo());
		int success = classservice.joinClassCall(classsignup);

		if (success != 0) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "클래스 가입 유무 확인하기", response = Integer.class)
	@PostMapping("/checkjoin")
	public ResponseEntity<Integer> checkJoinClass(HttpServletRequest request, @RequestBody ClassSignUp classsignup) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(Class) | checkJoinClass");
		User user = (User) request.getAttribute("userinfo");
		classsignup.setStudentNo(user.getUserNo());
		ClassSignUp success = null;
		success = classservice.checkJoinClass(classsignup);
		int result = 0;
		if (success == null) { // 데이터가 없다
			result = 1;
		} else if (success.getIsApproved() == null) {
			result = 3; // 가입대기중
		} else if (Integer.parseInt(success.getIsApproved()) == 0) {
			result = 4; // 가입거절
		} else {
			result = 2; // 가입된 상태
		}
		if (result != 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "해당 클래스 가입대기 확인", response = List.class)
	@GetMapping("/callwaitclass/{classNo}")
	public ResponseEntity<List<ClassJoinList>> callWaitClass(@PathVariable("classNo") int classNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(Class) | callWaitClass");
		ClassSignUp classsignup = new ClassSignUp();
		List<ClassJoinList> selectclass = null;
		classsignup.setClassNo(classNo);
		selectclass = classservice.callWaitClass(classsignup);
		if (selectclass != null) {
			return new ResponseEntity<List<ClassJoinList>>(selectclass, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "해당 클래스 가입여부 수정하기", response = String.class)
	@PutMapping("/classjoin")
	public ResponseEntity<String> updateClassJoin(@RequestBody ClassSignUp classsignup, HttpServletRequest request)
			throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(Class) | updateClassJoin");
		User user = (User) request.getAttribute("userinfo");
		if (user.getUserNo() == 0) {
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		if (classservice.updateClassJoin(classsignup) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "해당 클래스 가입대기 확인", response = List.class)
	@GetMapping("/memberlist/{classNo}")
	public ResponseEntity<List<User>> ClassmemberList(@PathVariable("classNo") int classNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(Class) | ClassmemberList");
		ClassSignUp classsignup = new ClassSignUp();
		List<User> selectclass = null;
		classsignup.setClassNo(classNo);
		selectclass = classservice.ClassmemberList(classsignup);

		if (selectclass != null) {
			return new ResponseEntity<List<User>>(selectclass, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "클래스 탈퇴하기", response = String.class)
	@DeleteMapping("/outclass/{classNo}")
	public ResponseEntity<String> outClass(@PathVariable("classNo") int classNo, HttpServletRequest request) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(Class) | deleteOneClass");
		System.out.println(classNo);
		User user = (User) request.getAttribute("userinfo");
		if (user.getUserNo() == 0) {
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		ClassSignUp classsignup = new ClassSignUp();
		classsignup.setClassNo(classNo);
		classsignup.setStudentNo(user.getUserNo());
		if (classservice.outClass(classsignup) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
	}


}
