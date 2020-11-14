package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.searchPaging;

@Mapper
public interface BoardDao {
	public boolean createBoard(Board board); // 공지생성
	public List<Board> selectBoard(Board board); // 공지글제목찾기
	public Board detailBoard(Board board); // 글보기
	public List<Board> selectBoardAll(searchPaging searchPaging); // 전체공지글찾기
	public boolean modifyBoard(Board board); // 공지글수정하기
	public boolean deleteBoard(Board board); // 공지글삭제하기
	public void clickedBoard(Board board); // 공지글조회
	public int searchcount(searchPaging searchPaging);
}
