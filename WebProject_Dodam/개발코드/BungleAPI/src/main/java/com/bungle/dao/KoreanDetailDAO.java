package com.bungle.dao;

import java.util.List;

import com.bungle.dto.Koreandetail;

public interface KoreanDetailDAO {
	
	public boolean createKoreandetail(Koreandetail koreandetail);
	public boolean updateKoreandetail(Koreandetail koreandetail);
	public boolean deletekoreandetail(Koreandetail koreandetail);
	public List<Koreandetail> selectUserCorrect(int studentNo);
	public Koreandetail selectOneKoreandetail(Koreandetail koreandetail);
	public int selectUserCorrectCount(int studentNo);
}
