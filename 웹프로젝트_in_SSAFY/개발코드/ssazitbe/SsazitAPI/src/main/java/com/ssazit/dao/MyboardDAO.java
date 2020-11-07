package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Article;
import com.ssazit.dto.Myboard;

public interface MyboardDAO {

	// C(게시판 만들기)
	public int insertMyboard(Myboard myboard) throws Exception;
	// U(게시판 수정)
	public int updateMyboard(Myboard myboard) throws Exception;
	// D(게시판 삭제)
	public int deleteMyboard(Myboard myboard) throws Exception;
	
	// R
	// 상세 조회(boardNo)
	public Myboard selectMyboardOne(Myboard myboard);
	// 리스트 조회(boardNo | blogNo | superBoard | boardName | boardType | seqNo)
	public List<Myboard> selectMyboardList(Myboard myboard);
	// 리스트 갯수 조회
	public int selectMyboardCount(Myboard myboard);
	
	// blogNo, groupNo로 seqNo 최대값 반환
	public int selectSeqNoMax(Myboard myboard) throws Exception;
	// blogNo로 depth 최대값 반환
	public int selectDepthMax(Myboard myboard) throws Exception;
	// blogNo로 groupNo 중복없이 주세요
	public List<Integer> selectGroupNoDist(int blogNo) throws Exception;
	// 게시판 getBoardList
	public List<Myboard> getBoardList(Myboard myboard) throws Exception;
	// 상위게시판 순서 조정
	public int updateRootBoard (Myboard myboard) throws Exception;
	//해당 게시판을 소유한 유저의 ID 검색
	public String getUserId(Article searchCondition);
}
