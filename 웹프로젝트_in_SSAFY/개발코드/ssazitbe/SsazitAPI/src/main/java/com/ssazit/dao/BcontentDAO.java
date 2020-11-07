package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Bcontent;

public interface BcontentDAO {

	// C(책 내용 만들기)
	public int insertBcontent(Bcontent bcontent);
	// U(책 내용 수정)
	public int updateBcontent(Bcontent bcontent);
	// D(책 내용 삭제)
	public int deleteBcontent(Bcontent bcontent);
	
	// R
	// 상세 조회
	public Bcontent selectBcontentOne(Bcontent bcontent);
	// 리스트 조회
	public List<Bcontent> selectBcontentList(Bcontent bcontent);
	// 리스트 갯수 조회
	public int selectBcontentCount(Bcontent bcontent);
	
}
