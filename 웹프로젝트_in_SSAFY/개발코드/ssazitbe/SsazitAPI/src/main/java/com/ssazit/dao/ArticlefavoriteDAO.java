package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Articlefavorite;

public interface ArticlefavoriteDAO {

	// C(글 즐겨찾기 입력)
	public int insertArticlefavorite(Articlefavorite articlefavorite);
	// U(글 즐겨찾기 수정)
	public int updateArticlefavorite(Articlefavorite articlefavorite);
	// D(글 즐겨찾기 삭제)
	public int deleteArticlefavorite(Articlefavorite articlefavorite);
	
	// R
	// 상세조회
	public Articlefavorite selectArticlefavoriteOne(Articlefavorite articlefavorite);
	// 리스트 조회
	public List<Articlefavorite> selectArticlefavoriteList(Articlefavorite articlefavorite);
	// 리스트 갯수 조회
	public int selectArticlefavoriteCount(Articlefavorite articlefavorite);
		
}
