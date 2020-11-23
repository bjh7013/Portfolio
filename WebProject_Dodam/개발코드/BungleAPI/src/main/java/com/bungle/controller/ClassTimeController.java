package com.bungle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.bungle.dto.ClassTime;
import com.bungle.dto.User;
import com.bungle.service.ClassTimeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/classtime")
public class ClassTimeController {

	@Autowired
	ClassTimeService classtimeservice;

	@ApiOperation(value = "클래스 타임 생성하기", response = String.class)
	@PostMapping("/createclasstime")
	public ResponseEntity<String> createClassTime(@RequestBody ClassTime classtime) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(ClassTime) | createClassTime");

		if (classtimeservice.createClassTime(classtime) == 1) {

		} else {

		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@ApiOperation(value = "클래스타임 수정하기", response = String.class)
	@PutMapping("/updateclasstime")
	public ResponseEntity<String> updateClassTime(@RequestBody ClassTime classTime) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(ClassTime) | updateClassTime");

		if (classtimeservice.updateClassTime(classTime) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "클래스타임 하나 삭제하기", response = String.class)
	@DeleteMapping("/deleteoneclasstime/{timeNo}")
	public ResponseEntity<String> deleteOneClassTime(@PathVariable("timeNo") int timeNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(ClassTime) | deleteOneClassTime");
		ClassTime classTime = new ClassTime();
		classTime.setTimeNo(timeNo);
		if (classtimeservice.deleteOneClassTime(classTime) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "클래스의 모든 클래스타임 삭제하기", response = String.class)
	@DeleteMapping("/deleteuserclasstime")
	public ResponseEntity<String> deleteUserClassTime(@RequestBody ClassTime classTime) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(ClassTime) | deleteUserClassTime");

		if (classtimeservice.deleteUserClassTime(classTime) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "클래스타임 하나 검색하기", response = ClassTime.class)
	@GetMapping("/selectoneclasstime")
	public ResponseEntity<ClassTime> selectOneClassTime(@RequestBody ClassTime classTime) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(ClassTime) | selectOneClassTime");
		ClassTime selectclasstime = null;
		selectclasstime = classtimeservice.selectOneClassTime(classTime);

		if (selectclasstime != null) {
			return new ResponseEntity<ClassTime>(selectclasstime, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "클래스 클래스타임 검색하기", response = List.class)
	@GetMapping("/searchclasstime/{classNo}")
	public ResponseEntity<List<ClassTime>> selectUserClassTime(@PathVariable("classNo") int classNo,
			HttpServletRequest request) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(ClassTime) | selectUserClassTime");
		User user = (User) request.getAttribute("userinfo");
		if (user.getUserNo() == 0) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		ClassTime classTime = new ClassTime();
		classTime.setClassNo(classNo);
		List<ClassTime> selectclasstime = null;
		selectclasstime = classtimeservice.selectUserClassTime(classTime);

		if (selectclasstime != null&&selectclasstime.size()!=0) {
			System.out.println(selectclasstime.get(0).getUserNo());
			System.out.println(selectclasstime.get(0).toString());
			if (selectclasstime.get(0).getUserNo() == user.getUserNo()) {
				for (int i = 0; i < selectclasstime.size(); i++) {
					selectclasstime.get(i).setCheckOwner(true);
				}
			} else {
				for (int i = 0; i < selectclasstime.size(); i++) {
					selectclasstime.get(i).setCheckOwner(false);
				}
			}
			return new ResponseEntity<List<ClassTime>>(selectclasstime, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
