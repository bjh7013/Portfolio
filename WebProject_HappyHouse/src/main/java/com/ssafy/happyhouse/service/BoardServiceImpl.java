package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.BoardDao;
import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDao dao;

	@Override
	public List<Board> selectBoardAll(searchPaging searchPaging) {
		return dao.selectBoardAll(searchPaging);
	}

	@Override
	public boolean createBoard(Board board) {
		return dao.createBoard(board);
	}

	@Override
	public List<Board> selectBoard(Board board) {
		return dao.selectBoard(board);
	}

	@Override
	public boolean modifyBoard(Board board) {
		return dao.modifyBoard(board);
	}

	@Override
	public boolean deleteBoard(Board board) {
		return dao.deleteBoard(board);
	}

	@Override
	public Board detailBoard(Board board) {
		return dao.detailBoard(board);
	}

	@Override
	public void clickedBoard(Board board) {
		dao.clickedBoard(board);
	}

	@Override
	public int searchcount(searchPaging searchPaging) {
		return dao.searchcount(searchPaging);
	}

	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage, int totalSize) {
		
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10; // 페이지 갯수
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = (totalSize - 1) / sizePerPage + 1; // 총 게시글 수
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1; // 총 페이지 수
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize; // startRange true : 이전 x false : 이전 o
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage; // endRange true : 다음x false : 다음o
		
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	
	
	

	
	

}
