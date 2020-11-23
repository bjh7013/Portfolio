package com.bungle.service;

import java.util.List;

import com.bungle.dto.Dictation;
import com.bungle.dto.DictationSet;
import com.bungle.dto.DictationSetContent;
import com.bungle.dto.Homework;
import com.bungle.dto.User;

public interface DictationService {
	public int createDictation(Homework homework, User loginUser) throws Exception;

	public int updateDictation(Dictation dictation, User loginUser) throws Exception;

	public int deleteDictation(Dictation dictation, User loginUser) throws Exception;

	public Dictation selectOneDictation(Dictation dictation) throws Exception; // 받아쓰기 1개만 검색

	public List<Dictation> selectAllDictation(Dictation dictation) throws Exception; // 받아쓰기 전체 검색
	
	public List<Dictation> selectDictationTest(Dictation dictation) throws Exception; // 해당 받아쓰기 시험 검색
	
	public int createProblem(DictationSet dictationset);
	
	public int createProblems(DictationSetContent dictationset);
	
	public List<Dictation> selectByHomeworkNo(int homeworkNo);
	
	public List<Dictation> selectDictationLevel(Dictation dictation);
	
	public List<DictationSet> selectDictationSet(DictationSet dictation);
	
	public List<Dictation> selectLevel(Dictation dictation);
	
	public List<DictationSetContent> selectDictationSetContent(DictationSetContent dictation);

}
