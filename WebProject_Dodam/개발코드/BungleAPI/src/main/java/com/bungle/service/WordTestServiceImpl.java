package com.bungle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bungle.dao.WordTestDAO;
import com.bungle.dto.Homework;
import com.bungle.dto.WordCategory;
import com.bungle.dto.WordSet;
import com.bungle.dto.WordSetContent;
import com.bungle.dto.WordSetReal;
import com.bungle.dto.WordTest;

@Service
public class WordTestServiceImpl implements WordTestService {

	@Autowired
	WordTestDAO wordtestdao;

	@Override
	public int createWordTest(WordTest wordtest) {
		if (wordtestdao.createWordTest(wordtest) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int updateWordTest(WordTest wordtest) {
		if (wordtestdao.updateWordTest(wordtest) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteWordTest(WordTest wordtest) {
		if (wordtestdao.deleteWordTest(wordtest) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public WordTest selectOneWordTest(WordTest wordtest) {
		return wordtestdao.selectOneWordTest(wordtest);
	}

	@Override
	public List<WordTest> selectAllWordTest(WordTest wordtest) {
		return wordtestdao.selectAllWordTest(wordtest);
	}

	@Override
	public int updateWordTestImg(int no, String imgpath) throws Exception {
		WordTest wordtest = new WordTest();
		wordtest.setTestNo(no);
		wordtest.setWordImg(imgpath);
		return wordtestdao.updateWordTestImg(wordtest);
	}

	@Override
	public List<WordCategory> selectWordCategory() throws Exception {
		return wordtestdao.selectWordCategory();
	}

	@Override
	public List<WordTest> selectCategoryWordTest(WordTest wordtest) throws Exception {
		return wordtestdao.selectCategoryWordTest(wordtest);
	}

	@Override
	public int createWordSet(WordSet wordset) {
		return wordtestdao.createWordSet(wordset);
	}

	@Override
	public int createWordSetContent(WordSetReal wordsetreal) {
		return wordtestdao.createWordSetContent(wordsetreal);
	}

	@Override
	public List<WordSet> getWordSet(WordSet wordset) {
		return wordtestdao.getWordSet(wordset);
	}

	@Override
	public List<WordSetReal> getWordSetContent(WordSetReal wordsetcontent) {
		return wordtestdao.getWordSetContent(wordsetcontent);
	}

	@Override
	public int makeWordHomeWork(Homework homework) {
		return wordtestdao.makeWordHomeWork(homework);
	}

	@Override
	public int makeWordHomeWorkContent(WordTest wordtest) {
		return wordtestdao.makeWordHomeWorkContent(wordtest);
	}

	@Override
	public List<WordTest> selectHomeworkWordTest(WordTest wordtest) throws Exception {
		return wordtestdao.selectHomeworkWordTest(wordtest);
	}



}
