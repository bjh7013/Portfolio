package com.ssazit.dao;

import java.util.List;
import com.ssazit.dto.Bookcategory;

public interface BookcategoryDAO {

	// C(책 카테고리 연결 만들기)
	public int insertBookcategory(Bookcategory bookcategory);
	// U(책 카테고리 연결 수정)
	public int updateBookcategory(Bookcategory bookcategory);
	// D(책 카테고리 연결 삭제)
	public int deleteBookcategory(Bookcategory bookcategory);
	
	// R
	// 상세 조회
	public Bookcategory selectBookcategoryOne(Bookcategory bookcategory);
	// 리스트 조회
	public List<Bookcategory> selectBookcategoryList(Bookcategory bookcategory);
	// 리스트 갯수 조회
	public int selectBookcategoryCount(Bookcategory bookcategory);
	
}
