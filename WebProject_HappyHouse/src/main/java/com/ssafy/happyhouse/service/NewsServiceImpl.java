package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.NewsDao;
import com.ssafy.happyhouse.dto.News;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	NewsDao dao;
	
	@Override
	public boolean createNews(News news) {
		return dao.createNews(news);
	}

	@Override
	public List<News> selectNews(News news) {
		return dao.selectNews(news);
	}

	@Override
	public List<News> selectNewsAll(searchPaging searchPaging) {
		return dao.selectNewsAll(searchPaging);
	}

	@Override
	public boolean modifyNews(News news) {
		return dao.modifyNews(news);
	}

	@Override
	public boolean deleteNews(News news) {
		return dao.deleteNews(news);
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
