package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Booktag;

public interface BooktagDAO {

	// C(책 태그 연결 만들기)
	public int insertBooktag(Booktag booktag);
	// U(책 태그 연결 수정)
	public int updateBooktag(Booktag booktag);
	// D(책 태그 연결 삭제)
	public int deleteBooktag(Booktag booktag);
	
	// R
	// 상세 조회
	public Booktag selectBooktagOne(Booktag booktag);
	// 리스트 조회
	public List<Booktag> selectBooktagList(Booktag booktag);
	// 리스트 갯수 조회
	public int selectBooktagCount(Booktag booktag);
	
}
