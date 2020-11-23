package com.bungle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bungle.dao.WordTestSubmitDAO;
import com.bungle.dto.Homework;
import com.bungle.dto.HomeworkSubmit;
import com.bungle.dto.Image;
import com.bungle.dto.User;
import com.bungle.dto.WordCategoryCount;
import com.bungle.dto.WordTestSubmit;

@Service
public class WordTestSubmitServiceImpl implements WordTestSubmitService {

	@Autowired
	WordTestSubmitDAO wordtestsubmitdao;
	@Autowired
	ImageService imageService;
	@Value("${resource.path.wordsubmit}")
	private String wordSubmitResourcePath;
	@Autowired
	HomeworkService homeworkService;

	@Override
	public int createWordTestSubmit(WordTestSubmit wordtest, User loginUser) throws Exception {

		Homework homework = wordtestsubmitdao.getHomework(wordtest);

		if (homework != null) {// homework에 속한 dictation일시 homeworkSubmit insert
			HomeworkSubmit homeworkSubmit = HomeworkSubmit.builder().homeworkNo(homework.getHomeworkNo()).build();
			if (!homeworkService.createHomeworkSubmitToWord(homeworkSubmit, loginUser)) {
				System.out.println("과제 제출중 오류 발생");
//				return 0;
			}
		}
		
		wordtest.setStudentNo(loginUser.getUserNo());
//		if(wordtestsubmitdao.isAlreadySubmitted(wordtest)) {
//			System.out.println("이미 제출한 문제");
//			return 0;
//		}
		WordTestSubmit tmp=wordtestsubmitdao.checkSubmit(wordtest);
		if(tmp!=null) {
			if(tmp.isCorrect()) { 
				System.out.println("맞은문제 통과");
				return 1;
			}
			System.out.println("이미 제출. 틀린문제 수정");
			wordtest.setSubmitNo(tmp.getSubmitNo());
			System.out.println(wordtest.isCorrect());
			System.out.println(loginUser);
			
			wordtest.setSubmitImg(wordSubmitResourcePath + "/" + wordtest.getSubmitNo() + ".png");

			Image img = new Image();
			img.setImgData(wordtest.getSubmitImgData());
			img.setFileName(wordtest.getSubmitNo() + ".png");
			img.setType("word");
			imageService.saveImage(img);

			wordtestsubmitdao.updateWordTestSubmit(wordtest);
			return 1;
			
		}else if (wordtestsubmitdao.createWordTestSubmit(wordtest) == 1) {
			System.out.println(wordtest.isCorrect());

			System.out.println(loginUser);

			wordtest.setSubmitImg(wordSubmitResourcePath + "/" + wordtest.getSubmitNo() + ".png");

			Image img = new Image();
			img.setImgData(wordtest.getSubmitImgData());
			img.setFileName(wordtest.getSubmitNo() + ".png");
			img.setType("word");
			imageService.saveImage(img);

			wordtestsubmitdao.updateWordTestSubmit(wordtest);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int updateWordTestSubmit(WordTestSubmit wordtest) throws Exception {
		if (wordtestsubmitdao.updateWordTestSubmit(wordtest) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteWordTestSubmit(WordTestSubmit wordtest) throws Exception {
		if (wordtestsubmitdao.deleteWordTestSubmit(wordtest) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public WordTestSubmit selectOneWordTestSubmit(WordTestSubmit wordtest) throws Exception {
		return wordtestsubmitdao.selectOneWordTestSubmit(wordtest);
	}

	@Override
	public List<WordTestSubmit> selectAllWordTestSubmit(WordTestSubmit wordtest) throws Exception {
		return wordtestsubmitdao.selectAllWordTestSubmit(wordtest);
	}

	@Override
	public WordTestSubmit selectOneWordTestSubmitIndex(WordTestSubmit wordtest) throws Exception {
		return wordtestsubmitdao.selectOneWordTestSubmitIndex(wordtest);
	}

	@Override
	public int updateWordTestSubmitImg(int submitNo, String ImgWordTestSubmit) throws Exception {
		WordTestSubmit wordtestsubmit = new WordTestSubmit();
		wordtestsubmit.setSubmitNo(submitNo);
		wordtestsubmit.setSubmitImg(ImgWordTestSubmit);
		return wordtestsubmitdao.updateWordTestSubmitImg(wordtestsubmit);
	}

	@Override
	public List<WordTestSubmit> selectTestNoWordTestSubmit(int testNo) throws Exception {
		return wordtestsubmitdao.selectTestNoWordTestSubmit(testNo);
	}

	@Override
	public List<WordCategoryCount> getSubmitGroupCategory(int userNo) throws Exception {
		// TODO Auto-generated method stub
		return wordtestsubmitdao.getSubmitGroupCategory(userNo);
	}

	@Override
	public int getSubmitCount(int userNo) throws Exception {
		// TODO Auto-generated method stub
		return wordtestsubmitdao.getSubmitCount(userNo);
	}

}
