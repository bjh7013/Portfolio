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

import com.bungle.dto.Homework;
import com.bungle.dto.HomeworkSubmit;
import com.bungle.dto.User;
import com.bungle.service.HomeworkService;

import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/homework")
public class HomeworkController {
	@Autowired
	private HomeworkService homeworkService;

	@ApiOperation(value = "과제를 생성한다. 생성에 성공할시 상태메시지 200 OK를 반환", response = String.class)
	@PostMapping("")
	public ResponseEntity<HttpStatus> createHomework(@RequestBody Homework homework){
		//jwt에서 로그인한 userNo 꺼냄 ->  homework의 teacherNo에 저장 -> service로직 호출(service에서 teacherNo를 이용해 권한 검사)
		User loginUser = User.builder()
							.userNo(1)
							.userType("teacher")
							.build();
				
		if(!homeworkService.createHomework(homework, loginUser)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);			
	}
	
	@ApiOperation(value = "과제를 수정한다. 수정에 성공할시 상태메시지 200 OK를 반환", response = String.class)
	@PutMapping("/{homeworkNo}")
	public ResponseEntity<HttpStatus> updateHomework(@RequestBody Homework homework, @PathVariable int homeworkNo){
		//jwt에서 로그인한 userNo 획득 -> homework의  teacherNo에 저장 -> service단에서 권한 검사
		User loginUser = User.builder()
				.userNo(1)
				.userType("teacher")
				.build();
		
		if(!homeworkService.updateHomework(homework, loginUser)) {			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "과제를 삭제한다. 삭제에 성공할시 상태메시지 200 OK를 반환", response = String.class)
	@DeleteMapping("/{homeworkNo}")
	public ResponseEntity<HttpStatus> deleteHomework(Homework homework, @PathVariable int homeworkNo){
		//jwt에서 로그인한 userNo 획득 -> homework의  teacherNo에 저장 -> service단에서 권한 검사
		User loginUser = User.builder()
				.userNo(1)
				.userType("teacher")
				.build();
		
		if(!homeworkService.deleteHomework(homework, loginUser)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	@ApiOperation(value = "과제를 검색한다.", response = List.class)
	@GetMapping("")
	public ResponseEntity<HttpStatus> searchHomework(Homework homework){
		User loginUser = User.builder()
				.userNo(1)
				.userType("teacher")
				.build();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "과제 번호를 이용하여 과제 상세 정보를 조회한다.", response = Homework.class)
	@GetMapping("/{homeworkNo}")
	public ResponseEntity<HttpStatus> getHomework(Homework homework, @PathVariable int homeworkNo){
		User loginUser = User.builder()
				.userNo(1)
				.userType("teacher")
				.build();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	/**
	 * homework_submit
	 */
	@ApiOperation(value = "과제를 제출한다.", response = String.class)
	@PostMapping("/submit")
	public ResponseEntity<HttpStatus> createHomeworkSubmit(@RequestBody HomeworkSubmit homeworkSubmit){
		User loginUser = User.builder()
				.userNo(2)
				.userType("student")
				.build();
		
		if(!homeworkService.createHomeworkSubmit(homeworkSubmit, loginUser)){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "제출한 과제를 수정한다.", response = String.class)
	@PutMapping("/submit/{submitNo}")
	public ResponseEntity<HttpStatus> updateHomeworkSubmit(@PathVariable int submitNo, @RequestBody HomeworkSubmit homeworkSubmit){
		User loginUser = User.builder()
				.userNo(2)
				.userType("student")
				.build();
		
		homeworkSubmit.setSubmitNo(submitNo);
		
		if(!homeworkService.updateHomeworkSubmit(homeworkSubmit, loginUser)){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "제출한 과제를 삭제한다.", response = String.class)
	@DeleteMapping("/submit/{submitNo}")
	public ResponseEntity<HttpStatus> updateHomeworkSubmit(@PathVariable int submitNo){
		User loginUser = User.builder()
				.userNo(2)
				.userType("student")
				.build();
		
		HomeworkSubmit homeworkSubmit = HomeworkSubmit.builder().submitNo(submitNo).build();
		
		if(!homeworkService.deleteHomeworkSubmit(homeworkSubmit, loginUser)){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//////////////////////////////////////////////////////////////////////////
	@ApiOperation(value = "제출한 과제를 검색한다.", response = List.class)
	@GetMapping("/submit")
	public ResponseEntity<HttpStatus> searchHomeworkSubmit(HomeworkSubmit homeworkSubmit){
		User loginUser = User.builder()
				.userNo(1)
				.userType("teacher")
				.build();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "과제 제출 번호를 이용하여 과제 제출 상세 정보를 조회한다.", response = Homework.class)
	@GetMapping("/submit/{submitNo}")
	public ResponseEntity<HttpStatus> getHomeworkSubmit(HomeworkSubmit homeworkSubmit, @PathVariable int submitNo){
		User loginUser = User.builder()
				.userNo(1)
				.userType("teacher")
				.build();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	/**
	 * 과제평가
	 */
	@ApiOperation(value = "제출된 과제를 평가한다.", response = Homework.class)
	@PostMapping("/eval/{submitNo}")
	public ResponseEntity<HttpStatus> homeworkEval(@RequestBody HomeworkSubmit homeworkSubmit, @PathVariable int submitNo){
		User loginUser = User.builder()
				.userNo(1)
				.userType("teacher")
				.build();
		
		homeworkSubmit.setSubmitNo(submitNo);
		if(!homeworkService.homeworkEval(homeworkSubmit, loginUser)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "과제 평가를 삭제한다.", response = Homework.class)
	@DeleteMapping("/eval/{submitNo}")
	public ResponseEntity<HttpStatus> deleteHomeworkEval(@PathVariable int submitNo){
		User loginUser = User.builder()
				.userNo(1)
				.userType("teacher")
				.build();
		
		HomeworkSubmit homeworkSubmit = HomeworkSubmit.builder().submitNo(submitNo).build();
		
		if(!homeworkService.deleteHomeworkEval(homeworkSubmit, loginUser)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "클래스 번호를 이용하여 과제를 모두 불러온다.", response = Homework.class)
	@GetMapping("/select/{classNo}")
	public ResponseEntity<List<Homework>> seelctHomeworkByClassNo(@PathVariable int classNo){
		User loginUser = User.builder()
				.userNo(1)
				.userType("teacher")
				.build();
		Homework homework = Homework.builder().classNo(classNo).build();
		System.out.println(homework.toString());
		
		return new ResponseEntity<List<Homework>>(homeworkService.searchHomeworkClass(homework),HttpStatus.OK);
	}
	
	
	
}
