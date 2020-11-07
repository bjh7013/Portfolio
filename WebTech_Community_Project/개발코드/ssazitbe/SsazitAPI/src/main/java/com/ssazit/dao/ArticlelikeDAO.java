package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Articlelike;

public interface ArticlelikeDAO {
	
	// C(글 좋아요 입력)
	public int insertArticlelike(Articlelike articlelike);
	// U(글 좋아요 수정)
	public int updateArticlelike(Articlelike articlelike);
	// D(글 좋아요 삭제)
	public int deleteArticlelike(Articlelike articlelike);
	
	// R
	// 상세조회
	public Articlelike selectArticlelikeOne(Articlelike articlelike);
	// 리스트 조회
	public List<Articlelike> selectArticlelikeList(Articlelike articlelike);
	// 리스트 갯수 조회
	public int selectArticlelikeCount(Articlelike articlelike);
	
}
