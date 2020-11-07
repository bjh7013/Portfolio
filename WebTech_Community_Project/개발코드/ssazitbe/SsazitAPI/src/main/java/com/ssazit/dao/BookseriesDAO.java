package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Bookseries;

public interface BookseriesDAO {

	// C(책 시리즈 연결 만들기)
	public int insertBookseries(Bookseries bookseries);
	// U(책 시리즈 연결 수정)
	public int updateBookseries(Bookseries bookseries);
	// D(책 시리즈 연결 삭제)
	public int deleteBookseries(Bookseries bookseries);
	
	// R
	// 상세 조회
	public Bookseries selectBookseriesOne(Bookseries bookseries);
	// 리스트 조회
	public List<Bookseries> selectBookseriesList(Bookseries bookseries);
	// 리스트 갯수 조회
	public int selectBookseriesCount(Bookseries bookseries);
	
}
