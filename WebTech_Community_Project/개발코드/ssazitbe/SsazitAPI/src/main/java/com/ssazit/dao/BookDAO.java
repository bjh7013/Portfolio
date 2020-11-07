package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Book;

public interface BookDAO {

	// C(책 만들기)
	public int insertBook(Book book);
	// U(책 수정)
	public int updateBook(Book book);
	// D(책 삭제)
	public int deleteBook(Book book);
	
	// R
	// 상세 조회
	public Book selectBookOne(Book book);
	// 리스트 조회
	public List<Book> selectBookList(Book book);
	// 리스트 갯수 조회
	public int selectBookCount(Book book);
	
	public Book validateBook(Book book);
	
}
