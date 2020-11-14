package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.QnA;
import com.ssafy.happyhouse.service.QnAService;


import io.swagger.annotations.ApiOperation;

//http://localhost:9999/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/qna")
public class QnAController {

	private static final Logger logger = LoggerFactory.getLogger(QnAController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnAService qnaService;
	
    @ApiOperation(value = "모든 질문 게시판의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<QnA>> retrieveBoard() throws Exception {
		logger.debug("retrieveQnA - 호출");
		return new ResponseEntity<List<QnA>>(qnaService.retrieveQnA(), HttpStatus.OK);
	}
    
    @ApiOperation(value = "질문 번호에 해당하는 질문 detail!!! ", response = QnA.class)    
	@GetMapping("{qnaNo}")
	public ResponseEntity<QnA> detailBoard(@PathVariable int qnaNo) {
		logger.debug("detailQnA - 호출");
		return new ResponseEntity<QnA>(qnaService.detailQnA(qnaNo), HttpStatus.OK);
	}
    
    @ApiOperation(value = "질문 등록! success OR fail.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody QnA qnA) {
		logger.debug("writeQnA - 호출");
		if (qnaService.writeQnA(qnA)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "질문 수정 & 댓글 달기! success OR fail", response = String.class)
	@PutMapping("{qnaNo}")
	public ResponseEntity<String> updateBoard(@RequestBody QnA qnA) {
		logger.debug("updateQnA - 호출");
		logger.debug("" + qnA);
		
		if (qnaService.updateQnA(qnA)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "글 삭제! success OR fail", response = String.class)
	@DeleteMapping("{qnaNo}")
	public ResponseEntity<String> deleteBoard(@PathVariable int qnaNo) {
		logger.debug("deleteQnA - 호출");
		if (qnaService.deleteQnA(qnaNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
}