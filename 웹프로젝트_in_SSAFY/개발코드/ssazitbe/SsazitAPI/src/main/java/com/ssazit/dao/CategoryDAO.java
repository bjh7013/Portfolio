package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Category;

public interface CategoryDAO {
	
	// C(글 카테고리 입력)
	public int insertCategory(Category category);
	// U(글 카테고리 수정)
	public int updateCategory(Category category);
	// D(글 카테고리 삭제)
	public int deleteCategory(Category category);
	
	// R
	// 상세조회
	public Category selectCategoryOne(Category category);
	// 리스트 조회
	public List<Category> selectCategoryList(Category category);
	// 리스트 갯수 조회
	public int selectCategoryCount(Category category);
	
}
