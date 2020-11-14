package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.News;
import com.ssafy.happyhouse.dto.searchPaging;

@Mapper
public interface NewsDao {
	public boolean createNews(News news); // 뉴스생성
	public List<News> selectNews(News news); // 뉴스제목찾기
	public List<News> selectNewsAll(searchPaging searchPaging); // 전체뉴스찾기
	public boolean modifyNews(News news); // 뉴스수정하기
	public boolean deleteNews(News news); // 뉴스삭제하기
	public int searchcount(searchPaging searchPaging);
}
