package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.FAQ;
import com.ssafy.happyhouse.dto.searchPaging;

@Mapper
public interface FAQDao {
	List<FAQ> selectAllFAQ(searchPaging searchPaging);
	FAQ detailFAQ(int qnaNo);
	boolean writeFAQ(FAQ faq);
	boolean updateFAQ(FAQ faq);
	boolean deleteFAQ(int faqno);
	public int searchcount(searchPaging searchPaging);
	List<FAQ> selectAlltest();
}
