package com.ssazit.service;

import java.util.List;
import com.ssazit.dto.Blog;
import com.ssazit.dto.Myboard;

public interface BlogService {
	
	// Blog 테이블 (블로그) ------------------------------------------
	
	// 블로그 생성
	public boolean createBlog(Blog blog) throws Exception;
	// 블로그 수정
	public boolean updateBlog(Blog blog) throws Exception;
	// 블로그 삭제
	public boolean deleteBlog(Blog blog) throws Exception;
	// 블로그 상세조회
	public Blog detailBlog(Blog blog) throws Exception;
	// 블로그 검색
	public List<Blog> searchBlog(Blog blog) throws Exception;
	// 블로그 갯수 검색
	public int countBlog(Blog blog) throws Exception;
	
	// 사용자 아이디로 Blog 정보 가져오기
	public Blog selectUserBlog(String userId) throws Exception;
	
	// Myboard 테이블 (게시판) ------------------------------------------
	
	// 게시판 생성
	public boolean createMyboard(Myboard myboard, int userNo) throws Exception;
	// 게시판 수정
	public boolean updateMyboard(Myboard myboard, int userNo) throws Exception;
	// 게시판 삭제
	public boolean deleteMyboard(Myboard myboard, int userNo) throws Exception;
	// 게시판 상세조회
	public Myboard detailMyboard(Myboard myboard);
	// 게시판 검색
	public List<Myboard> searchMyboard(Myboard myboard);
	// 게시판 갯수 검색
	public int countMyboard(Myboard myboard);
	
	// blogNo, superBoard로 seqNo 최대값 반환
	public int selectSeqNoMax(Myboard myboard) throws Exception;
	// blogNo로 depth 최대값 반환
	public int selectDepthMax(Myboard myboard) throws Exception;
	// blogNo로 groupNo 중복없이 주세요
	public List<Integer> selectGroupNoDist(int blogNo) throws Exception;
	// 게시판 getBoardList
	public List<Myboard> getBoardList(Myboard myboard) throws Exception;
	// 상위게시판 순서 조정
	public boolean updateRootBoard (Myboard myboard, int userNo) throws Exception;
}
