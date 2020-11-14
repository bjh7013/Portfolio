package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.QnADAO;
import com.ssafy.happyhouse.dto.QnA;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class QnAServiceImpl implements QnAService {

	@Autowired
	private QnADAO dao;
	
	@Override
	public List<QnA> retrieveQnA() {
		return dao.selectQnA();
	}

	@Override
	public QnA detailQnA(int qnaNo) {
		return dao.selectOne(qnaNo);
	}

	@Override
	public boolean writeQnA(QnA qna) {
		return dao.insertQnA(qna)==1;
	}

	@Override
	public boolean updateQnA(QnA qna) {
		// TODO Auto-generated method stub
		return dao.updateQnA(qna) ==1;
	}

	@Override
	public boolean deleteQnA(int qnaNo) {
		return dao.deleteQnA(qnaNo)==1;
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

	@Override
	public List<QnA> searchAllQnA(searchPaging searchPaging) {
		// TODO Auto-generated method stub
		return dao.searchAllQnA(searchPaging);
	}

	@Override
	public boolean updateQnAcomment(QnA qna) {
		return dao.updateQnAcomment(qna);
	}

}
