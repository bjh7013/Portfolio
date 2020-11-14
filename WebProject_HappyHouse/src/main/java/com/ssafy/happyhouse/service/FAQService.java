package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.FAQ;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.util.PageNavigation;

public interface FAQService {
	List<FAQ> selectAllFAQ(searchPaging searchPaging);
	FAQ detailFAQ(int qnaNo);
	boolean writeFAQ(FAQ faq);
	boolean updateFAQ(FAQ faq);
	boolean deleteFAQ(int faqno);
	public int searchcount(searchPaging searchPaging);
	public PageNavigation makePageNavigation(int pg, int sizePerPage,int totalSize) ;
	List<FAQ> selectAlltest();
}
