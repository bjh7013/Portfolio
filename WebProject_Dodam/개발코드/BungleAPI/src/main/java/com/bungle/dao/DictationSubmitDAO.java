package com.bungle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bungle.dto.DictationCount;
import com.bungle.dto.DictationSubmit;
import com.bungle.dto.Homework;

@Mapper
public interface DictationSubmitDAO {
	public int createDictationSubmit(DictationSubmit dictationsubmit);

	public int updateDictationSubmit(DictationSubmit dictationsubmit);

	public int deleteDictationSubmit(DictationSubmit dictationsubmit);

	public DictationSubmit selectOneDictationSubmit(DictationSubmit dictationsubmit); // 받아쓰기 제출1개만 검색

	public List<DictationSubmit> selectAllDictationSubmit(DictationSubmit dictationsubmit); // 받아쓰기 제출 전체 검색
	
	public List<DictationSubmit> selectDictationSubmitByHomeworkNo(int homeworkNo);
	
	public List<DictationSubmit> selectDictationSubmitByDictationNo(int dictationNo);
	public List<DictationSubmit> selectDictationSubmitByUserNo(int userNo) throws Exception;


	public boolean validateC(DictationSubmit dictationSubmit);
	public boolean validateUD(DictationSubmit dictationSubmit);
	public Homework getHomework(int dictationNo);
	
	public int getSubmitCount(int usreNo);
	public List<DictationCount> getSubmitGroupLevel(int userNo);
	public DictationSubmit checkDictationSubmit(DictationSubmit dictationSubmit);
}
