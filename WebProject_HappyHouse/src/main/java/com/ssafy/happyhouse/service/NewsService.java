package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.News;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.util.PageNavigation;

public interface NewsService {
	public boolean createNews(News news); // 뉴스생성
	public List<News> selectNews(News news); // 뉴스제목찾기
	public List<News> selectNewsAll(searchPaging searchPaging); // 전체뉴스찾기
	public boolean modifyNews(News news); // 뉴스수정하기
	public boolean deleteNews(News news); // 뉴스삭제하기
	public int searchcount(searchPaging searchPaging);
	public PageNavigation makePageNavigation(int pg, int sizePerPage,int totalSize) ;
}
