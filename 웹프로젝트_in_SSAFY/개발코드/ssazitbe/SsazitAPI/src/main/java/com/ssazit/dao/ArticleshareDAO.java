package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Articleshare;

public interface ArticleshareDAO {
	
	// C(글 공유 입력)
	public int insertArticleshare(Articleshare articleshare);
	// U(글 공유 수정)
	public int updateArticleshare(Articleshare articleshare);
	// D(글 공유 삭제)
	public int deleteArticleshare(Articleshare articleshare);
	
	// R
	// 상세조회
	public Articleshare selectArticleshareOne(Articleshare articleshare);
	// 리스트 조회
	public List<Articleshare> selectArticleshareList(Articleshare articleshare);
	// 리스트 갯수 조회
	public int selectArticleshareCount(Articleshare articleshare);
	
}