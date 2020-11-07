package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Blog;

public interface BlogDAO {
	
	// C(내 블로그 만들기)
	public int insertBlog(Blog blog) throws Exception;
	// U(내 블로그 수정)
	public int updateBlog(Blog blog) throws Exception;
	// D(내 블로그 삭제)
	public int deleteBlog(Blog blog) throws Exception;
	
	// R
	// 상세 조회(blogNo)
	public Blog selectBlogOne(Blog blog) throws Exception;
	// 리스트 조회(blogNo | userNo | blogName | blogBirth)
	public List<Blog> selectBlogList(Blog blog) throws Exception;
	// 리스트 갯수 조회
	public int selectBlogCount(Blog blog) throws Exception;

	
	// 사용자 아이디로 Blog 정보 가져오기
	public Blog selectUserBlog(String userId) throws Exception;
	
}
