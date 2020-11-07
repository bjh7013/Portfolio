package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Acategory;

public interface AcategoryDAO {

	// C(알고리즘문제 카테고리 만들기)
	public int insertAcategory(Acategory acategory);
	// U(알고리즘문제 카테고리 수정)
	public int updateAcategory(Acategory acategory);
	// D(알고리즘문제 카테고리 삭제)
	public int deleteAcategory(Acategory acategory);
	
	// R
	// 상세조회
	public Acategory selectAcategoryOne(Acategory acategory);
	// 리스트 조회
	public List<Acategory> selectAcategoryList(Acategory acategory);
	// 리스트 갯수 조회
	public int selectAcategoryCount(Acategory acategory);
	// 사이트 목록 조회
	public List<String> selectSiteList();
	// acategory번호 조회
	public int getAcategoryNo(Acategory acategory);
}
