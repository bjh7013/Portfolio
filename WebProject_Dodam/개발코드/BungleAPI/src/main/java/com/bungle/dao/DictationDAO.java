package com.bungle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bungle.dto.Dictation;
import com.bungle.dto.DictationSet;
import com.bungle.dto.DictationSetContent;

@Mapper
public interface DictationDAO {
	public int createDictation(Dictation dictation);
	public int createDictations(List<Dictation> dictationList);
	public int updateDictation(Dictation dictation);
	public int deleteDictation(Dictation dictation);
	public Dictation selectOneDictation(Dictation dictation); //받아쓰기 1개만 검색
	public List<Dictation> selectAllDictation(Dictation dictation); //받아쓰기 전체 검색
	public List<Dictation> selectDictationTest(Dictation dictation); // 해당 받아쓰기 시험 검색
	public int createProblem(DictationSet dictationset);
	public int createProblems(DictationSetContent dictationset);
	
	public boolean validateUD(Dictation dictation);
	public List<Dictation> selectByHomeworkNo(int homeworkNo);
	public List<Dictation> selectDictationLevel(Dictation dictation);
	public List<DictationSet> selectDictationSet(DictationSet dictation);
	public List<Dictation> selectLevel(Dictation dictation);
	public List<DictationSetContent> selectDictationSetContent(DictationSetContent dictation);
}
