package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.util.PageNavigation;

public interface BoardService {
	public boolean createBoard(Board board); // 공지생성
	public List<Board> selectBoard(Board board); // 공지글제목찾기
	public List<Board> selectBoardAll(searchPaging searchPaging); // 전체공지글찾기
	public Board detailBoard(Board board); // 글보기
	public boolean modifyBoard(Board board); // 공지글수정하기
	public boolean deleteBoard(Board board); // 공지글삭제하기
	public void clickedBoard(Board board); // 공지글조회수
	public int searchcount(searchPaging searchPaging);
	public PageNavigation makePageNavigation(int pg, int sizePerPage,int totalSize) ;
}
