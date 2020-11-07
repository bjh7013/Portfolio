package com.ssazit.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssazit.dto.Bcontent;
import com.ssazit.dto.Book;
import com.ssazit.dto.BookTagCat;
import com.ssazit.dto.Bookcategory;
import com.ssazit.dto.Bookfavorite;
import com.ssazit.dto.Booklike;
import com.ssazit.dto.Bookseries;
import com.ssazit.dto.Series;

public interface BookService {

	// Book 테이블 (책) ------------------------------------------
	
	// 책 생성
	public boolean createBook(BookTagCat booktagcat, int userNo) throws Exception;
	// 책 수정
	public boolean updateBook(BookTagCat booktagcat, int userNo) throws Exception;
	// 책 삭제
	public boolean deleteBook(Book book, int userNo) throws Exception;
	// 책 상세조회
	public BookTagCat detailBook(Book book) throws Exception;
	// 책 검색
	public List<BookTagCat> searchBook(Book book) throws Exception;
	// 책 갯수 검색
	public int countBook(Book book) throws Exception;
	
	// Bcontent 테이블 (책 내용) ------------------------------------------
	
	// 책 내용 생성
	public boolean createBcontent(Bcontent bcontent);
	// 책 내용 수정
	public boolean updateBcontent(Bcontent bcontent);
	// 책 내용 삭제
	public boolean deleteBcontent(Bcontent bcontent);
	// 책 내용 상세조회
	public Bcontent detailBcontent(Bcontent bcontent);
	// 책 내용 검색
	public List<Bcontent> searchBcontent(Bcontent bcontent);
	// 책 내용 갯수 검색
	public int countBcontent(Bcontent bcontent);
	
	// Series 테이블 (시리즈) ------------------------------------------
	
	// 시리즈 생성
	public boolean createSeries(Series series);
	// 시리즈 수정
	public boolean updateSeries(Series series);
	// 시리즈 삭제
	public boolean deleteSeries(Series series);
	// 시리즈 상세조회
	public Series detailSeries(Series series);
	// 시리즈 검색
	public List<Series> searchSeries(Series series);
	// 시리즈 갯수 검색
	public int countSeries(Series series);
	
	// Bookseries 테이블 (책 시리즈 연결) ------------------------------------------
	
	// 책 시리즈 연결 생성
	public boolean createBookseries(Bookseries bookseries);
	// 책 시리즈 연결 수정
	public boolean updateBookseries(Bookseries bookseries);
	// 책 시리즈 연결 삭제
	public boolean deleteBookseries(Bookseries bookseries);
	// 책 시리즈 연결 상세조회
	public Bookseries detailBookseries(Bookseries bookseries);
	// 책 시리즈 연결 검색
	public List<Bookseries> searchBookseries(Bookseries bookseries);
	// 책 시리즈 연결 갯수 검색
	public int countBookseries(Bookseries bookseries);
	
	// Booklike 테이블 (책 좋아요) ------------------------------------------
	
	// 책 좋아요 생성
	public boolean createBooklike(Booklike booklike);
	// 책 좋아요 수정
	public boolean updateBooklike(Booklike booklike);
	// 책 좋아요 삭제
	public boolean deleteBooklike(Booklike booklike);
	// 책 좋아요 상세조회
	public Booklike detailBooklike(Booklike booklike);
	// 책 좋아요 검색
	public List<Booklike> searchBooklike(Booklike booklike);
	// 책 좋아요 갯수 검색
	public int countBooklike(Booklike booklike);
	
	// Bookfavorite 테이블 (책 즐겨찾기) ------------------------------------------
	
	// 책 즐겨찾기 생성
	public boolean createBookfavorite(Bookfavorite bookfavorite);
	// 책 즐겨찾기 수정
	public boolean updateBookfavorite(Bookfavorite bookfavorite);
	// 책 즐겨찾기 삭제
	public boolean deleteBookfavorite(Bookfavorite bookfavorite);
	// 책 즐겨찾기 상세조회
	public Bookfavorite detailBookfavorite(Bookfavorite bookfavorite);
	// 책 즐겨찾기 검색
	public List<Bookfavorite> searchBookfavorite(Bookfavorite bookfavorite);
	// 책 즐겨찾기 갯수 검색
	public int countBookfavorite(Bookfavorite bookfavorite);
	
	// Bookcategory 테이블 (책 카테고리 연결) ------------------------------------------
	
	// 책 카테고리 연결 생성
	public boolean createBookcategory(Bookcategory bookcategory);
	// 책 카테고리 연결 수정
	public boolean updateBookcategory(Bookcategory bookcategory);
	// 책 카테고리 연결 삭제
	public boolean deleteBookcategory(Bookcategory bookcategory);
	// 책 카테고리 연결 상세조회
	public Bookcategory detailBookcategory(Bookcategory bookcategory);
	// 책 카테고리 연결 검색
	public List<Bookcategory> searchBookcategory(Bookcategory bookcategory);
	// 책 카테고리 연결 갯수 검색
	public int countBookcategory(Bookcategory bookcategory);

	
	public boolean hitBooklike(Booklike booklike);
	public String hitBookfavorite(Bookfavorite bookfavorite);
}
