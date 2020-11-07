package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Tmptag;

public interface TmptagDAO {

	// C(임시태그 입력)
	public int insertTmptag(Tmptag tmptag);
	// U(임시태그 수정)
	public int updateTmptag(Tmptag tmptag);
	// D(임시태그 삭제)
	public int deleteTmptag(Tmptag tmptag);
	
	// R
	// 상세조회
	public Tmptag selectTmptagOne(Tmptag tmptag);
	// 리스트 조회
	public List<Tmptag> selectTmptagList(Tmptag tmptag);
	// 리스트 갯수 조회
	public int selectTmptagCount(Tmptag tmptag);
	
}
