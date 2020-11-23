package com.bungle.service;

import java.util.List;

import com.bungle.dto.DictationCount;
import com.bungle.dto.DictationSubmit;
import com.bungle.dto.User;

public interface DictationSubmitService {
	public int createDictationSubmit(List<DictationSubmit> dictationsubmit, User loginUser) throws Exception;

	public int updateDictationSubmit(DictationSubmit dictationsubmit, User loginUser) throws Exception;
	public int deleteDictationSubmit(DictationSubmit dictationsubmit, User loginUser) throws Exception;
	public DictationSubmit selectOneDictationSubmit(DictationSubmit dictationsubmit) throws Exception; // 받아쓰기 제출1개만 검색
	public List<DictationSubmit> selectAllDictationSubmit(DictationSubmit dictationsubmit) throws Exception; // 받아쓰기 제출
																												// 전체 검색
	public List<DictationSubmit> selectDictationSubmitByHomeworkNo(int homeworkNo) throws Exception;
	
	public List<DictationSubmit> selectDictationSubmitByDictationNo(int dictationNo) throws Exception;
	public List<DictationSubmit> selectDictationSubmitByUserNo(int userNo) throws Exception;

	public int getSubmitCount(int usreNo);
	public List<DictationCount> getSubmitGroupLevel(int userNo);
}
