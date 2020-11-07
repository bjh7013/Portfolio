package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Article;
import com.ssazit.dto.ArticleTagCat;
import com.ssazit.dto.Myboard;

public interface ArticleDAO {

	// 게시글 번호 가져오기
	public int getArticleNo(int userNo);
	// C(게시글 입력)
	public int insertArticle(Article article);
	// U(게시글 수정)
	public int updateArticle(Article article);
	// D(게시글 삭제)
	public int deleteArticle(Article article);
	// 참조글 정보 가져오기(공유글인 경우)
	public Article getReferenceArticle(Article article);
	
	// R
	// 상세조회
	public Article selectArticleOne(Article article);
	// 리스트 조회
	public List<Article> selectArticleList(Article searchCondition);
	// 유저가 쓴 글 보기
	public List<Article> selectArticleListByUser(Article searchCondition);
	// 리스트 갯수 조회
	public int selectArticleCount(Article article);
	
	//해당 게시판에 글 쓸 수 있는지 검증
	public Myboard validateBoard(Article article);
	//해당 게시글을 업데이트할 수 있는지 검증
	public Article validateArticle(Article article);
	
}
