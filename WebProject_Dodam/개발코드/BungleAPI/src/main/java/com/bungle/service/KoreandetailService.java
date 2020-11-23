package com.bungle.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bungle.dto.Koreandetail;

@Mapper
public interface KoreandetailService {
	public boolean createKoreandetail(Koreandetail koreandetail);
	public boolean updateKoreandetail(Koreandetail koreandetail);
	public boolean deleteKoreandetail(Koreandetail koreandetail);
	public List<Koreandetail> selectUserCorrect(int studentNo);
	public Koreandetail selectOneKoreandetail(Koreandetail koreandetail);
	public int selectUserCorrectCount(int studentNo);

}
