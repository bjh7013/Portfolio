package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Tag;

public interface TagDAO {

	// C(대표태그 입력)
	public int insertTag(Tag tag);
	// U(대표태그 수정)
	public int updateTag(Tag tag);
	// D(대표태그 삭제)
	public int deleteTag(Tag tag);
	
	// R
	// 상세조회
	public Tag selectTagOne(Tag tag);
	// 리스트 조회
	public List<Tag> selectTagList(Tag tag);
	// 리스트 갯수 조회
	public int selectTagCount(Tag tag);
	
}
