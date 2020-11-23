package com.bungle.controller;

import java.util.List;

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

import com.bungle.dto.ClassSession;
import com.bungle.service.ClassSessionService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/classsession")
public class ClassSessionController {

	@Autowired
	ClassSessionService classsessionservice;

	@ApiOperation(value = "클래스 세션 생성하기", response = String.class)
	@PostMapping("/classsession")
	public ResponseEntity<String> createClassSession(@RequestBody ClassSession classsession) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(ClassSession) | createClassSession");

		if (classsessionservice.createClassSession(classsession) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "클래스 세션 수정하기", response = String.class)
	@PutMapping("/classsession")
	public ResponseEntity<String> updateClassSession(@RequestBody ClassSession classsession) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(ClassSession) | updateClassSession");

		if (classsessionservice.updateClassSession(classsession) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "클래스세션 하나 삭제하기", response = String.class)
	@DeleteMapping("/oneclasssession/{sessionNo}")
	public ResponseEntity<String> deleteOneClassSession(@PathVariable("sessionNo") int sessionNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(ClassSession) | deleteOneClassSession");
		ClassSession classsession = new ClassSession();
		classsession.setSessionNo(sessionNo);
		if (classsessionservice.deleteOneClassSession(classsession) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "모든 클래스세션 삭제하기", response = String.class)
	@DeleteMapping("/classsession")
	public ResponseEntity<String> deleteClassSession(@RequestBody ClassSession classsession) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(ClassSession) | deleteClassTime");

		if (classsessionservice.deleteClassSession(classsession) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "클래스타임 하나 검색하기", response = List.class)
	@GetMapping("/selectclasssession/{classNo}")
	public ResponseEntity<List<ClassSession>> selectClassSession(@PathVariable("classNo") int classNo)
			throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(ClassSession) | selectClassSession");
		ClassSession classsession = new ClassSession();
		classsession.setClassNo(classNo);
		List<ClassSession> selectclasssession = null;
		selectclasssession = classsessionservice.selectClassSession(classsession);

		if (selectclasssession != null) {
			return new ResponseEntity<List<ClassSession>>(selectclasssession, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "모든 클래스섹션 검색하기", response = List.class)
	@GetMapping("/selectallclasssession")
	public ResponseEntity<List<ClassSession>> selectAllClassSession(@RequestBody ClassSession classsession)
			throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(ClassSession) | selectAllClassSession");
		List<ClassSession> selectclasssession = null;
		selectclasssession = classsessionservice.selectAllClassSession(classsession);

		if (selectclasssession != null) {
			return new ResponseEntity<List<ClassSession>>(selectclasssession, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
