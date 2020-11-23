package com.bungle.service;

import java.util.List;

import com.bungle.dto.User;
import com.bungle.dto.WordCategoryCount;
import com.bungle.dto.WordTestSubmit;

public interface WordTestSubmitService {
	public int createWordTestSubmit(WordTestSubmit wordtest, User loginUser) throws Exception;

	public int updateWordTestSubmit(WordTestSubmit wordtest) throws Exception;

	public int deleteWordTestSubmit(WordTestSubmit wordtest) throws Exception;

	public WordTestSubmit selectOneWordTestSubmit(WordTestSubmit wordtest) throws Exception; // 단어 과제제출 중 하나만(submit_no)

	public WordTestSubmit selectOneWordTestSubmitIndex(WordTestSubmit wordtest) throws Exception; // 단어 과제제출 중 하나만(해당유저의 마지막제출)

	public List<WordTestSubmit> selectAllWordTestSubmit(WordTestSubmit wordtest) throws Exception; // 단어 유저 과제제출 전부

	public int updateWordTestSubmitImg(int submitNo,String ImgWordTestSubmit) throws Exception;
	
	public List<WordTestSubmit> selectTestNoWordTestSubmit(int testNo) throws Exception; // 테스트 번호로 받아오기
	public List<WordCategoryCount> getSubmitGroupCategory(int userNo) throws Exception;
	public int getSubmitCount(int userNo) throws Exception;
	

}
