package com.ssazit.dao;

import java.util.List;
import com.ssazit.dto.Arttag;

public interface ArttagDAO {

	// C(글 대표태그 연결 입력)
	public int insertArttag(Arttag arttag);
	// U(글 대표태그 연결 수정)
	public int updateArttag(Arttag arttag);
	// D(글 대표태그 연결 삭제)
	public int deleteArttag(Arttag arttag);
	
	// R
	// 상세조회
	public Arttag selectArttagOne(Arttag arttag);
	// 리스트 조회
	public List<Arttag> selectArttagList(Arttag arttag);
	// 리스트 갯수 조회
	public int selectArttagCount(Arttag arttag);
	
}
