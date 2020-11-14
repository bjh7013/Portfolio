package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.QnA;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.util.PageNavigation;

public interface QnAService {
	//list , detail, update, delete, insert
	List<QnA> retrieveQnA();
	QnA detailQnA(int qnaNo);
	boolean writeQnA(QnA qna);
	boolean updateQnA(QnA qna);
	boolean updateQnAcomment(QnA qna);
	boolean deleteQnA(int qnaNo);
	List<QnA> searchAllQnA(searchPaging searchPaging);
	public int searchcount(searchPaging searchPaging);
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage, int totalSize) ;
}
