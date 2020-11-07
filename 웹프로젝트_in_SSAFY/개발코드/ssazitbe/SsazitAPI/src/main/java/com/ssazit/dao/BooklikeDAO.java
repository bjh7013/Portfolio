package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Booklike;

public interface BooklikeDAO {

	// C(책 좋아요 만들기)
	public int insertBooklike(Booklike booklike);
	// U(책 좋아요 수정)
	public int updateBooklike(Booklike booklike);
	// D(책 좋아요 삭제)
	public int deleteBooklike(Booklike booklike);
	
	// R
	// 상세 조회
	public Booklike selectBooklikeOne(Booklike booklike);
	// 리스트 조회
	public List<Booklike> selectBooklikeList(Booklike booklike);
	// 리스트 갯수 조회
	public int selectBooklikeCount(Booklike booklike);
	
}
