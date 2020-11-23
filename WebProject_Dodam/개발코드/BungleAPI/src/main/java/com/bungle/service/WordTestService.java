package com.bungle.service;

import java.util.List;

import com.bungle.dto.Homework;
import com.bungle.dto.WordCategory;
import com.bungle.dto.WordSet;
import com.bungle.dto.WordSetContent;
import com.bungle.dto.WordSetReal;
import com.bungle.dto.WordTest;

public interface WordTestService {
	public int createWordTest(WordTest wordtest) throws Exception;

	public int updateWordTest(WordTest wordtest) throws Exception;

	public int deleteWordTest(WordTest wordtest) throws Exception;

	public WordTest selectOneWordTest(WordTest wordtest) throws Exception; // 단어 하나만

	public List<WordTest> selectAllWordTest(WordTest wordtest) throws Exception; // 단어 전부 or 몇개

	public int updateWordTestImg(int no, String imgpath) throws Exception;

	public List<WordCategory> selectWordCategory() throws Exception; // 단어 카테고리 불러오기

	public List<WordTest> selectCategoryWordTest(WordTest wordtest) throws Exception; // 해당 카테고리의 모든 단어 불러오기
	
	public int createWordSet(WordSet wordset);
	
	public int createWordSetContent(WordSetReal wordsetreal);
	
	public List<WordSet> getWordSet(WordSet wordset);
	
	public List<WordSetReal> getWordSetContent(WordSetReal wordsetcontent);
	
	public int makeWordHomeWork(Homework homework);
	
	public int makeWordHomeWorkContent(WordTest wordtest);
	
	public List<WordTest> selectHomeworkWordTest(WordTest wordtest) throws Exception; // 해당 홈워크의 모든 단어 불러오기

}
