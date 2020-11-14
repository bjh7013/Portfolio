package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.FAQDao;
import com.ssafy.happyhouse.dto.FAQ;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class FAQServiceImpl implements FAQService {

	@Autowired
	FAQDao dao;

	@Override
	public List<FAQ> selectAllFAQ(searchPaging searchPaging) {
		return dao.selectAllFAQ(searchPaging);
	}

	@Override
	public FAQ detailFAQ(int qnaNo) {
		return dao.detailFAQ(qnaNo);
	}

	@Override
	public boolean writeFAQ(FAQ faq) {
		return dao.writeFAQ(faq);
	}

	@Override
	public boolean updateFAQ(FAQ faq) {
		return dao.updateFAQ(faq);
	}

	@Override
	public boolean deleteFAQ(int faqno) {
		return dao.deleteFAQ(faqno);
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
	public List<FAQ> selectAlltest() {
		return dao.selectAlltest();
	}

}
