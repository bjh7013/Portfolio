package com.ssazit.service;

import java.util.List;

import com.ssazit.dto.Arttag;
import com.ssazit.dto.Booktag;
import com.ssazit.dto.Tag;
import com.ssazit.dto.Tmptag;

public interface TagService {

	// Tag 테이블 (대표태그) ------------------------------------------
	
	// 대표태그 생성
	public boolean createTag(Tag tag);
	// 대표태그 수정
	public boolean updateTag(Tag tag);
	// 대표태그 삭제
	public boolean deleteTag(Tag tag);
	// 대표태그 상세조회
	public Tag detailTag(Tag tag);
	// 대표태그 검색
	public List<Tag> searchTag(Tag tag);
	// 대표태그 갯수 검색
	public int countTag(Tag tag);
	// 대표태그 그룹 변경
	public boolean updateTagGroup(List<Tag> tags, int userNo);
	
	// Arttag 테이블 (글 대표태그 연결) ------------------------------------------
	
	// 글 대표태그 연결 생성
	public boolean createArttag(Arttag arttag);
	// 글 대표태그 연결 수정
	public boolean updateArttag(Arttag arttag);
	// 글 대표태그 연결 삭제
	public boolean deleteArttag(Arttag arttag);
	// 글 대표태그 연결 상세조회
	public Arttag detailArttag(Arttag arttag);
	// 글 대표태그 연결 검색
	public List<Arttag> searchArttag(Arttag arttag);
	// 글 대표태그 연결 갯수 검색
	public int countArttag(Arttag arttag);
	
	// Booktag 테이블 (책 태그 연결) ------------------------------------------
	
	// 책 태그 연결 생성
	public boolean createBooktag(Booktag booktag);
	// 책 태그 연결 수정
	public boolean updateBooktag(Booktag booktag);
	// 책 태그 연결 삭제
	public boolean deleteBooktag(Booktag booktag);
	// 책 태그 연결 상세조회
	public Booktag detailBooktag(Booktag booktag);
	// 책 태그 연결 검색
	public List<Booktag> searchBooktag(Booktag booktag);
	// 책 태그 연결 갯수 검색
	public int countBooktag(Booktag booktag);
	
	
	// Tmptag 테이블 (임시태그) ------------------------------------------
	
	// 임시태그 생성
	public boolean createTmptag(Tmptag tmptag);
	// 임시태그 수정
	public boolean updateTmptag(Tmptag tmptag);
	// 임시태그 삭제
	public boolean deleteTmptag(Tmptag tmptag);
	// 임시태그 상세조회
	public Tmptag detailTmptag(Tmptag tmptag);
	// 임시태그 검색
	public List<Tmptag> searchTmptag(Tmptag tmptag);
	// 임시태그 갯수 검색
	public int countTmptag(Tmptag tmptag);
	
	
	// 임시태그 -> 정식태그
	public boolean tmpToTag (List<Tmptag> tmptags, int userNo);
	
}
