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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bungle.dto.Dictation;
import com.bungle.dto.DictationSet;
import com.bungle.dto.DictationSetContent;
import com.bungle.dto.Homework;
import com.bungle.dto.User;
import com.bungle.service.DictationService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/dictation")
public class DictationController {

	@Autowired
	private DictationService dictationservice;

	@ApiOperation(value = "받아쓰기 생성", response = String.class)
	@PostMapping("/dictation")
	
	public ResponseEntity<String> createDictation(@RequestBody Homework homework, HttpServletRequest request) throws Exception {
		User loginUser = (User) request.getAttribute("userinfo");
		
		System.out.println("controller");
		
		if (dictationservice.createDictation(homework, loginUser) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "받아쓰기 수정", response = String.class)
	@PutMapping("/dictation")
	public ResponseEntity<String> updateDictation(@RequestBody Dictation dictation, HttpServletRequest request) throws Exception {
		User loginUser = (User) request.getAttribute("userinfo");

		if (dictationservice.updateDictation(dictation, loginUser) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "받아쓰기 삭제", response = String.class)
	@DeleteMapping("/dictation")
	public ResponseEntity<String> deleteDictation(@RequestBody Dictation dictation, HttpServletRequest request) throws Exception {
		User loginUser = (User) request.getAttribute("userinfo");

		if (dictationservice.deleteDictation(dictation, loginUser) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "받아쓰기 1개 읽기", response = Dictation.class)
	@GetMapping("/dictation/{dictationNo}")
	public ResponseEntity<Dictation> selectOneDictation(@PathVariable int dictationNo) throws Exception {
		Dictation tempdic = null;
		tempdic.setDictationNo(dictationNo);
		if (dictationservice.selectOneDictation(tempdic) != null) {
			return new ResponseEntity<Dictation>(dictationservice.selectOneDictation(tempdic), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "받아쓰기 전부 읽기", response = List.class)
	@GetMapping("/dictation")
	public ResponseEntity<List<Dictation>> selectAllDictation() throws Exception {
		Dictation tempdic = new Dictation();
		if (dictationservice.selectAllDictation(tempdic) != null) {
			return new ResponseEntity<List<Dictation>>(dictationservice.selectAllDictation(tempdic), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "받아쓰기 해당 테스트 번호 읽기", response = List.class)
	@GetMapping("/{dictationLevel}")
	public ResponseEntity<List<Dictation>> selectDictationTest(@PathVariable int dictationLevel) throws Exception {
		Dictation dictation = new Dictation();
		dictation.setDictationLevel(dictationLevel);
		;
		List<Dictation> dictest = null;
		dictest = dictationservice.selectDictationTest(dictation);
		if (dictest != null) {
			return new ResponseEntity<List<Dictation>>(dictest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "받아쓰기 생성", response = String.class)
	@PostMapping("/createproblem")
	public ResponseEntity<String> createProblem(HttpServletRequest request,
			@RequestBody DictationSet dictationset) throws Exception {
		
		User userinfo = (User) request.getAttribute("userinfo");
		dictationset.setUserNo(userinfo.getUserNo());
		
		System.out.println("받아쓰기생성");
		System.out.println(dictationset);
		
		int val = dictationservice.createProblem(dictationset);
		if(val == 0)
			return new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		
		System.out.println(val);
		System.out.println("===================");
		int i = 1;
		for(DictationSetContent dictationSetContent : dictationset.getDictationSetContentList()) {
			dictationSetContent.setDictationsetNo(val);
			dictationSetContent.setDictationNumber(i++);
			
			if (dictationSetContent.getDictationProblem() == null) {
				dictationSetContent.setDictationProblem("");
			}
			if(dictationservice.createProblems(dictationSetContent)!=1) {
				return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			}
			
		}
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "받아쓰기 숙제번호로 받아오기", response = List.class)
	@GetMapping("/dictationhomework/{homeworkNo}")
	public ResponseEntity<List<Dictation>> selectByHomeworkNo(@PathVariable int homeworkNo){
		
		
		return new ResponseEntity<List<Dictation>>(dictationservice.selectByHomeworkNo(homeworkNo),HttpStatus.OK);
	}
	
	@ApiOperation(value = "받아쓰기레벨 전부 읽기", response = List.class)
	@GetMapping("/level")
	public ResponseEntity<List<Dictation>> selectDictationLevel() throws Exception {
		Dictation tempdic = new Dictation();
		if (dictationservice.selectDictationLevel(tempdic) != null) {
			return new ResponseEntity<List<Dictation>>(dictationservice.selectDictationLevel(tempdic), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "유저 받아쓰기세트 전부 읽기", response = List.class)
	@GetMapping("/dictationset")
	public ResponseEntity<List<DictationSet>> selectDictationSet(HttpServletRequest request) throws Exception {
		User userinfo = (User) request.getAttribute("userinfo");
		DictationSet tempdic = new DictationSet();
		tempdic.setUserNo(userinfo.getUserNo());
		List<DictationSet> dictationset = dictationservice.selectDictationSet(tempdic);
		return new ResponseEntity<List<DictationSet>>(dictationset, HttpStatus.OK);
	}
	
	@ApiOperation(value = "받아쓰기 선택레벨 읽기", response = List.class)
	@GetMapping("/dictationlevel/{dictationLevel}")
	public ResponseEntity<List<Dictation>> selectLevel(@PathVariable("dictationLevel") int dictationLevel) throws Exception {
		
		Dictation tempdic = new Dictation();
		tempdic.setDictationLevel(dictationLevel);
		List<Dictation> dictationset = dictationservice.selectLevel(tempdic);
		System.out.println("==============================");
		System.out.println(dictationset);
		System.out.println(dictationLevel);
		return new ResponseEntity<List<Dictation>>(dictationset, HttpStatus.OK);
	}
	
	@ApiOperation(value = "받아쓰기 선택레벨 읽기", response = List.class)
	@GetMapping("/dictationcustom/{dictationsetNo}")
	public ResponseEntity<List<DictationSetContent>> selectDictationSetContent(@PathVariable("dictationsetNo") int dictationsetNo) throws Exception {
		DictationSetContent tempdic = new DictationSetContent();
		tempdic.setDictationsetNo(dictationsetNo);
		List<DictationSetContent> dictationset = dictationservice.selectDictationSetContent(tempdic);
		return new ResponseEntity<List<DictationSetContent>>(dictationset, HttpStatus.OK);
	}
}
