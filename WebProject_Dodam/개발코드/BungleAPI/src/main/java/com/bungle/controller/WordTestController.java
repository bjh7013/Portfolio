package com.bungle.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;
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

import com.bungle.dto.Homework;
import com.bungle.dto.User;
import com.bungle.dto.WordCategory;
import com.bungle.dto.WordProblem;
import com.bungle.dto.WordSet;
import com.bungle.dto.WordSetContent;
import com.bungle.dto.WordSetReal;
import com.bungle.dto.WordTest;
import com.bungle.service.WordTestService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/wordtest")
public class WordTestController {

	@Autowired
	private WordTestService wordtestservice;

	@Value("${file.path.word}")
	private String wordPath;

	@Value("${file.path.wordset}")
	private String wordsetPath;

	@ApiOperation(value = "단어 생성하기", response = String.class)
	@PostMapping("/wordtest")
	public ResponseEntity<String> createWordTest(@RequestBody WordTest wordtest) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTest) | createWordTest");
		if (wordtestservice.createWordTest(wordtest) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "단어 이미지 수정하기", response = String.class)
	@PutMapping("/imgword")
	public ResponseEntity<String> updateWordImg(@RequestParam("file") MultipartFile file,
			@RequestParam("testNo") int testNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTest) | updateWordImg");
		System.out.println("파일 이름(원본) : " + file.getOriginalFilename());
		System.out.println("파일 형식(원본) : " + file.getContentType());
		System.out.println("파일 크기 : " + file.getSize());
		WordTest imgword = new WordTest();
		imgword.setTestNo(testNo);
		imgword = wordtestservice.selectOneWordTest(imgword);
		int no = imgword.getTestNo();

		String[] tmp = file.getContentType().split("/");
		String filename = Integer.toString(no) + "_word." + tmp[tmp.length - 1].toUpperCase();
		System.out.println("파일 이름(저장) : " + filename);
		String path = wordPath + "/";
		String dbdata = "/img/word/" + filename;
		System.out.println(dbdata);
		File target = new File(path, filename);
		FileCopyUtils.copy(file.getBytes(), target);

		int success = wordtestservice.updateWordTestImg(no, dbdata); // DB수정여부
		if (success > 0) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
	}

	@ApiOperation(value = "단어 수정하기", response = String.class)
	@PutMapping("/wordtest")
	public ResponseEntity<String> updateWordTest(@RequestBody WordTest wordtest) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTest) | updateWordTest");
		if (wordtestservice.updateWordTest(wordtest) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "단어 삭제하기", response = String.class)
	@DeleteMapping("/wordtest")
	public ResponseEntity<String> deleteWordTest(@RequestBody WordTest wordtest) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTest) | deleteordTest");
		if (wordtestservice.deleteWordTest(wordtest) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "단어 하나 보기", response = WordTest.class)
	@GetMapping("/wordtest/{testNo}")
	public ResponseEntity<WordTest> selectOneWordTest(@PathVariable int testNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTest) | selectOneWordTest");
		WordTest wordtest = new WordTest();
		wordtest.setTestNo(testNo);
		wordtest = wordtestservice.selectOneWordTest(wordtest);
		if (wordtest != null) {
			return new ResponseEntity<WordTest>(wordtest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "해당 과제의 단어 전체 보기", response = List.class)
	@GetMapping("/homeworkwordtest")
	public ResponseEntity<List<WordTest>> selectHomeworkWordTest(WordTest wordtests) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTest) | selectHomeworkWordTest");
		List<WordTest> wordtest = wordtestservice.selectAllWordTest(wordtests);

		if (wordtest != null) {
			return new ResponseEntity<List<WordTest>>(wordtest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "해당 카테고리의 단어 전체 보기", response = List.class)
	@GetMapping("/categorywordtest/{categoryNo}")
	public ResponseEntity<List<WordTest>> selectCategoryWordTest(@PathVariable("categoryNo") int categoryNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTest) | selectCategoryWordTest");
		WordTest wordtests = new WordTest();
		wordtests.setCategoryNo(categoryNo);
		List<WordTest> wordtest = wordtestservice.selectCategoryWordTest(wordtests);
		if (wordtest != null) {
			return new ResponseEntity<List<WordTest>>(wordtest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	

	@ApiOperation(value = "단어카테고리 불러오기", response = List.class)
	@GetMapping("/category")
	public ResponseEntity<List<WordCategory>> selectWordCategory() throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTest) | selectWordCategory");
		List<WordCategory> list = wordtestservice.selectWordCategory();
		if (list != null) {
			return new ResponseEntity<List<WordCategory>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "유저 단어 문제 만들기", response = String.class)
	@PostMapping("/userword")
	public ResponseEntity<String> userWord(HttpServletRequest request, @RequestBody WordSetContent wordSetContent)
			throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTest) | userWord");
		User user = (User) request.getAttribute("userinfo");
		WordSet wordset = new WordSet();
		wordset.setUserNo(user.getUserNo());
		wordset.setWordsetTitle(wordSetContent.getWordsetTitle());
		wordtestservice.createWordSet(wordset);
		int wordsetNo = wordset.getWordsetNo();
		if (wordsetNo == 0) {
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		for (int i = 1; i < wordSetContent.getWordImgData().length; i++) {
			String filename = wordsetNo + "_" + i + "_wordset.png";
			String dbdata = "/img/word/" + filename;
			try (OutputStream out = new FileOutputStream(wordPath + '/' + filename)) {
				Decoder decoder = Base64.getDecoder();
				byte[] decodedByte = decoder.decode(wordSetContent.getWordImgData()[i].split(",")[1]);
				out.write(decodedByte);
				WordSetReal wordsetreal = new WordSetReal();
				wordsetreal.setWordsetNo(wordsetNo);
				wordsetreal.setProblemNo(i);
				wordsetreal.setProblemName(wordSetContent.getProblemName()[i]);
				wordsetreal.setProblemImg(dbdata);
				wordtestservice.createWordSetContent(wordsetreal);
//				System.out.println("완료");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@ApiOperation(value = "단어셋 목록 불러오기", response = List.class)
	@GetMapping("/wordset")
	public ResponseEntity<List<WordSet>> getWordSet(HttpServletRequest request) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTest) | getWordSet");
		User user = (User) request.getAttribute("userinfo");
		WordSet wordset = new WordSet();
		wordset.setUserNo(user.getUserNo());
		List<WordSet> list = wordtestservice.getWordSet(wordset);
		if (list != null) {
			return new ResponseEntity<List<WordSet>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "단어셋 내용 목록 불러오기", response = List.class)
	@GetMapping("/wordcontent/{wordsetNo}")
	public ResponseEntity<List<WordSetReal>> getWordSetContent(@PathVariable("wordsetNo") int wordsetNo)
			throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTest) | getWordSetContent");
		WordSetReal wordsetcontent = new WordSetReal();
		wordsetcontent.setWordsetNo(wordsetNo);
		List<WordSetReal> list = wordtestservice.getWordSetContent(wordsetcontent);
		if (list != null) {
			return new ResponseEntity<List<WordSetReal>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "단어과제 생성하기", response = String.class)
	@PostMapping("/makehomework")
	public ResponseEntity<String> makeWordHomeWork(@RequestBody Homework homework) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTest) | makeWordHomeWork");
		int val = wordtestservice.makeWordHomeWork(homework);
		if (val == 0) {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		System.out.println(homework.getWordImgData().length);
		for(int i=0;i<homework.getProblems().length;i++) {
			WordTest wordtest = new WordTest();
			wordtest.setWord(homework.getProblems()[i].getWordSentence());
			wordtest.setHomeworkNo(homework.getHomeworkNo());
			wordtest.setCategoryNo(0);
			if(homework.getCheckData()[i]==0) {
				wordtest.setWordImg(homework.getProblems()[i].getWordimg());								
			}else {
				String filename = homework.getHomeworkNo() + "_" + i + "_homeworkword.png";
				String dbdata = "/img/word/" + filename;
				try (OutputStream out = new FileOutputStream(wordPath + '/' + filename)) {
					Decoder decoder = Base64.getDecoder();
					byte[] decodedByte = decoder.decode(homework.getWordImgData()[i].split(",")[1]);
					out.write(decodedByte);
					wordtest.setWordImg(dbdata);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			if(wordtestservice.makeWordHomeWorkContent(wordtest)==0) {
				return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			}
		}
		
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "해당 과제의의 단어 전체 보기", response = List.class)
	@GetMapping("/homeworkwordtest/{homeworkNo}")
	public ResponseEntity<List<WordTest>> selectHomeworkWordTest(@PathVariable("homeworkNo") int homeworkNo) throws Exception {
		System.out.println("====================================================");
		System.out.println("controller(WordTest) | selectHomeworkWordTest");
		WordTest wordtests = new WordTest();
		wordtests.setHomeworkNo(homeworkNo);
		List<WordTest> wordtest = wordtestservice.selectHomeworkWordTest(wordtests);
		if (wordtest != null) {
			return new ResponseEntity<List<WordTest>>(wordtest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
