package com.bungle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bungle.dto.Homework;
import com.bungle.dto.HomeworkSubmit;
import com.bungle.dto.WordCategoryCount;
import com.bungle.dto.WordTestSubmit;

@Mapper
public interface WordTestSubmitDAO {
	public int createWordTestSubmit(WordTestSubmit wordtest);

	public int updateWordTestSubmit(WordTestSubmit wordtest);

	public int deleteWordTestSubmit(WordTestSubmit wordtest);

	public WordTestSubmit selectOneWordTestSubmit(WordTestSubmit wordtest); // 단어 과제제출 중 하나만

	public List<WordTestSubmit> selectAllWordTestSubmit(WordTestSubmit wordtest); // 단어 과제제출 전부
	
	public WordTestSubmit selectOneWordTestSubmitIndex(WordTestSubmit wordtest); // 단어 과제제출 중 하나만(해당유저의 마지막제출)

	public int updateWordTestSubmitImg(WordTestSubmit wordtest);
	
	public Homework getHomework(WordTestSubmit wordTestSubmit);
	
	public List<WordTestSubmit> selectTestNoWordTestSubmit(int testNo) throws Exception; // 테스트 번호로 받아오기
	public boolean isAlreadySubmitted(WordTestSubmit wordTestSubmit);
	public List<WordCategoryCount> getSubmitGroupCategory(int userNo) throws Exception;
	public int getSubmitCount(int userNo) throws Exception;
	public WordTestSubmit checkSubmit(WordTestSubmit wordTestSubmit) throws Exception;
}
