package com.bungle.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Base64;
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

import com.bungle.dto.User;
import com.bungle.dto.WordTestSubmit;
import com.bungle.service.WordTestSubmitService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/wordtestsubmit")
public class WordTestSubmitController {

	@Autowired
	private WordTestSubmitService wordtestsubmitservice;

	@Value("${file.path.wordsubmit}")
	private String wordsubmitPath;
	
	@ApiOperation(value = "단어 제출 생성하기", response = String.class)
	@PostMapping("/wordtestsubmit")
	public ResponseEntity<String> createWordTestSubmit(@RequestBody WordTestSubmit wordtestsubmit, HttpServletRequest request) throws Exception {
		User loginUser = (User) request.getAttribute("userinfo");
		
		
		System.out.println("====================================================");
		System.out.println("controller(WordTestSubmit) | createWordTestSubmit");
		System.out.println(wordtestsubmit.getSubmitAnswer());
		if (wordtestsubmitservice.createWordTestSubmit(wordtestsubmit,loginUser) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		} 
	}

	@ApiOperation(value = "단어 제출 이미지 수정하기", response = String.class)
	@PutMapping("/imgwordsubmit")
	public ResponseEntity<String> updateWordSubmitImg(@RequestParam("file") String  file,
			@RequestParam("studentNo") int studentNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTestSubmit) | updateWordSubmitImg");
//		System.out.println("파일 이름(원본) : " + file.getOriginalFilename());
//		System.out.println("파일 형식(원본) : " + file.getContentType());
//		System.out.println("파일 크기 : " + file.getSize());
		WordTestSubmit imgwordsubmit = new WordTestSubmit();
		imgwordsubmit.setStudentNo(studentNo);

		imgwordsubmit = wordtestsubmitservice.selectOneWordTestSubmitIndex(imgwordsubmit);
		int no = imgwordsubmit.getSubmitNo();
		String temp = String.valueOf(file);
		String[] tempsplit = temp.split(",");
		String[] tmp = tempsplit[0].split(";");
		String[] ttmp = tmp[0].split(":");
		String[] result = ttmp[1].split("/");
		BufferedImage image=null;
		
		byte[] imgbyte = Base64.getDecoder().decode(tempsplit[1]);
		System.out.println(imgbyte);
		String filename = Integer.toString(no) + "_wordsubmit." + result[1].toLowerCase();
		System.out.println("파일 이름(저장) : " + filename);
		String path = wordsubmitPath + "/";
		String dbdata = "/img/wordsubmit/" + filename;
		System.out.println(dbdata);
		File target = new File(path, filename);
		
		File existfile = new File(path + filename);
		if (existfile.exists()) {
			existfile.delete();
		}
		FileCopyUtils.copy(imgbyte, target);
		int success = wordtestsubmitservice.updateWordTestSubmitImg(no, dbdata); // DB수정여부
		if (success > 0) {	
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
	}

	@ApiOperation(value = "단어 제출 수정하기", response = String.class)
	@PutMapping("/wordtestsubmit")
	public ResponseEntity<String> updateWordTestSubmit(@RequestBody WordTestSubmit wordtestsubmit) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTestSubmit) | updateWordTestSubmit");
		if (wordtestsubmitservice.updateWordTestSubmit(wordtestsubmit) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "단어 제출 삭제하기", response = String.class)
	@DeleteMapping("/wordtestsubmit")
	public ResponseEntity<String> deleteWordTestSubmit(@RequestBody WordTestSubmit wordtestsubmit) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTestSubmit) | deleteordTestSubmit");
		if (wordtestsubmitservice.deleteWordTestSubmit(wordtestsubmit) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "단어 제출 하나 보기", response = WordTestSubmit.class)
	@GetMapping("/wordtestsubmit/{submitNo}")
	public ResponseEntity<WordTestSubmit> selectOneWordTestSubmit(@PathVariable int submitNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTestSubmit) | selectOneWordTestSubmit");
		WordTestSubmit wordtestsubmit = new WordTestSubmit();
		wordtestsubmit.setSubmitNo(submitNo);
		wordtestsubmit = wordtestsubmitservice.selectOneWordTestSubmit(wordtestsubmit);
		if (wordtestsubmit != null) {
			return new ResponseEntity<WordTestSubmit>(wordtestsubmit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "단어 제출 전체 보기", response = List.class)
	@GetMapping("/wordtestsubmit")
	public ResponseEntity<List<WordTestSubmit>> selectAllWordTestSubmit() throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTestSubmit) | selectAllWordTestSubmit");
		WordTestSubmit wordtestsubmit = new WordTestSubmit();
		List<WordTestSubmit> wordtestsubmitall = wordtestsubmitservice.selectAllWordTestSubmit(wordtestsubmit);
		if (wordtestsubmitall != null) {
			return new ResponseEntity<List<WordTestSubmit>>(wordtestsubmitall, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@ApiOperation(value = "단어 단어 번호로 전체 보기", response = List.class)
	@GetMapping("/wordtestsubmittestno/{testNo}")
	public ResponseEntity<List<WordTestSubmit>> selectTestNoWordTestSubmit(@PathVariable int testNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTestSubmit) | selectTestNoWordTestSubmit");
		List<WordTestSubmit> wordtestsubmitall = wordtestsubmitservice.selectTestNoWordTestSubmit(testNo);
		if (wordtestsubmitall != null) {
			return new ResponseEntity<List<WordTestSubmit>>(wordtestsubmitall, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
