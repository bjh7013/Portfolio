package com.bungle.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
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
import org.springframework.web.multipart.MultipartFile;

import com.bungle.dto.Dictation;
import com.bungle.dto.DictationSubmit;
import com.bungle.dto.User;
import com.bungle.service.DictationSubmitService;
import com.bungle.service.ImageService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/dictationsubmit")
public class DictationSubmitContoller {

	@Autowired
	private DictationSubmitService dictationsubmitservice;

	@Autowired
	private ImageService imageService;
	
	private String SAVE_PATH = "/src/main/resources/dictation";

	@ApiOperation(value = "받아쓰기 제출 생성", response = String.class)
	@PostMapping("/dictationsubmit")
	public ResponseEntity<String> createDictationSubmit(@RequestBody List<DictationSubmit> dictationsubmitList, HttpServletRequest request) throws Exception {
		User loginUser = (User) request.getAttribute("userinfo");
				
		if (dictationsubmitservice.createDictationSubmit(dictationsubmitList, loginUser) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}
	

	@ApiOperation(value = "받아쓰기 제출 수정", response = String.class)
	@PutMapping("/dictationsubmit")
	public ResponseEntity<String> updateDictationSubmit(@RequestBody DictationSubmit dictationsubmit, HttpServletRequest request) throws Exception {
		User loginUser = (User) request.getAttribute("userinfo");
		
		if (dictationsubmitservice.updateDictationSubmit(dictationsubmit, loginUser) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "받아쓰기 제출 삭제", response = String.class)
	@DeleteMapping("/dictationsubmit")
	public ResponseEntity<String> deleteDictationSubmit(@RequestBody DictationSubmit dictationsubmit, HttpServletRequest request) throws Exception {
		User loginUser = (User) request.getAttribute("userinfo");

		if (dictationsubmitservice.deleteDictationSubmit(dictationsubmit, loginUser) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "받아쓰기 제출 1개 읽기", response = DictationSubmit.class)
	@GetMapping("/dictationsubmit/{submitNo}")
	public ResponseEntity<DictationSubmit> selectOneDictationSubmit(@PathVariable int submitNo) throws Exception {
		DictationSubmit tempdic = null;
		tempdic.setSubmitNo(submitNo);
		if (dictationsubmitservice.selectOneDictationSubmit(tempdic) != null) {
			return new ResponseEntity<DictationSubmit>(dictationsubmitservice.selectOneDictationSubmit(tempdic), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "받아쓰기 제출 전부 읽기", response = List.class)
	@GetMapping("/dictationsubmit")
	public ResponseEntity<List<DictationSubmit>> selectAllDictationSubmit() throws Exception {
		DictationSubmit tempdic = new DictationSubmit();
		if (dictationsubmitservice.selectAllDictationSubmit(tempdic) != null) {
			return new ResponseEntity<List<DictationSubmit>>(dictationsubmitservice.selectAllDictationSubmit(tempdic),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@ApiOperation(value = "받아쓰기 테스트", response = String.class)
	@PostMapping("/dictationsubmittest")
	public String testDictation(@RequestParam MultipartFile file) throws Exception {
//		System.out.println(file.toString());
		String filepath=imageService.saveImage(file,SAVE_PATH);
		System.out.println(filepath);
		

		filepath = filepath.replace("\\","\\\\"); 
		filepath = filepath.replace("/","\\\\"); 

		System.out.println(filepath);
		
		try {
			URL url = new URL("http://localhost:8000/ocrkor/");
		       
		    JSONObject json = new JSONObject();
		    json.put("path",filepath);
		    
	     
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(json.toString().getBytes("UTF-8").length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(json.toString().getBytes("UTF-8")); // POST 호출
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        String inputLine;
	        while((inputLine = in.readLine()) != null) { // response 출력
	            System.out.println(inputLine);
	            break;
	        }
	        in.close();
		}catch (Exception e){
			
		}
		return filepath;

	}
	@ApiOperation(value = "숙제번호로 받아쓰기 받아오기", response = List.class)
	@GetMapping("/dictationSubmitHomework/{homeworkNo}")
	public ResponseEntity<List<DictationSubmit>> selectDictationSubmitByHomeworkNo(@PathVariable int homeworkNo) throws Exception {
		
		return new ResponseEntity<List<DictationSubmit>>(dictationsubmitservice.selectDictationSubmitByHomeworkNo(homeworkNo), HttpStatus.OK);
	}
	@ApiOperation(value = "딕테이션 번호로 받아쓰기 받아오기", response = List.class)
	@GetMapping("/dictationSubmitdictationNo/{dictationNo}")
	public ResponseEntity<List<DictationSubmit>> selectDictationSubmitByDictationNo(@PathVariable int dictationNo) throws Exception {
		System.out.println(dictationNo);
		return new ResponseEntity<List<DictationSubmit>>(dictationsubmitservice.selectDictationSubmitByDictationNo(dictationNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 번호로 받아쓰기 받아오기", response = List.class)
	@GetMapping("/dictationsubmituserno")
	public ResponseEntity<List<DictationSubmit>> selectDictationSubmitByUserNo(HttpServletRequest request) throws Exception {
		User userinfo = (User) request.getAttribute("userinfo"); 
		int userNo=userinfo.getUserNo();
		return new ResponseEntity<List<DictationSubmit>>(dictationsubmitservice.selectDictationSubmitByUserNo(userNo), HttpStatus.OK);
	}
	@ApiOperation(value = "회원 번호로 받아쓰기 받아오기", response = List.class)
	@GetMapping("/dictationsubmitusernobydirect/{userNo}")
	public ResponseEntity<List<DictationSubmit>> dictationsubmitusernoByDirect(@PathVariable int userNo) throws Exception {
		
		return new ResponseEntity<List<DictationSubmit>>(dictationsubmitservice.selectDictationSubmitByUserNo(userNo), HttpStatus.OK);
	}
}
