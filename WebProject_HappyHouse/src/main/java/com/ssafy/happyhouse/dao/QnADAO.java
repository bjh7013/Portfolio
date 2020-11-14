package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.QnA;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.util.PageNavigation;

@Mapper
public interface QnADAO {
	List<QnA> selectQnA();
	QnA selectOne(int qnaNo);
	int insertQnA(QnA qna);
	int updateQnA(QnA qna);
	int deleteQnA(int qnaNo);
	List<QnA> searchAllQnA(searchPaging searchPaging);
	public int searchcount(searchPaging searchPaging);
	boolean updateQnAcomment(QnA qna);
}
