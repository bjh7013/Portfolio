package com.bungle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bungle.dto.Homework;
import com.bungle.dto.WordCategory;
import com.bungle.dto.WordSet;
import com.bungle.dto.WordSetReal;
import com.bungle.dto.WordTest;

@Mapper
public interface WordTestDAO {
	public int createWordTest(WordTest wordtest);

	public int updateWordTest(WordTest wordtest);

	public int deleteWordTest(WordTest wordtest);

	public WordTest selectOneWordTest(WordTest wordtest); // 단어 하나만

	public List<WordTest> selectAllWordTest(WordTest wordtest); // 단어 전부 or 몇개

	public int updateWordTestImg(WordTest wordtest);
	
	public List<WordCategory> selectWordCategory(); // 단어 카테고리 불러오기
	
	public List<WordTest> selectCategoryWordTest(WordTest wordtest); // 해당 카테고리의 모든 단어 불러오기
	
	public int createWordSet(WordSet wordset);
	
	public int createWordSetContent(WordSetReal wordsetreal);
	
	public List<WordSet> getWordSet(WordSet wordset);
	
	public List<WordSetReal> getWordSetContent(WordSetReal wordsetcontent);
	
	public int makeWordHomeWork(Homework homework);
	
	public int makeWordHomeWorkContent(WordTest wordtest);
	
	public List<WordTest> selectHomeworkWordTest(WordTest wordtest); // 해당 홈워크의 모든 단어 불러오기
	
}
