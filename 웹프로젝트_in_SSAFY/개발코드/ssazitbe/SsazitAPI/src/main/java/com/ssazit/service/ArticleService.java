package com.ssazit.service;

import java.util.List;

import com.ssazit.dto.Acategory;
import com.ssazit.dto.Article;
import com.ssazit.dto.ArticleTagCat;
import com.ssazit.dto.Articlefavorite;
import com.ssazit.dto.Articlelike;
import com.ssazit.dto.Articleshare;
import com.ssazit.dto.Category;
import com.ssazit.dto.Commentlike;

public interface ArticleService {
	
	// Article 테이블 (게시글) ------------------------------------------
	// 게시글 생성
	public boolean createArticle(ArticleTagCat artTagCat) throws Exception;
	// 게시글 수정
	public boolean updateArticle(ArticleTagCat articleTagCat) throws Exception;
	// 게시글 삭제
	public boolean deleteArticle(Article article);
	// 게시글 상세조회
	public ArticleTagCat detailArticle(Article article);
	// 게시글 검색
	public List<ArticleTagCat> searchArticle(Article searchCondition);
	// 게시글 갯수 검색
	public int countArticle(Article article);
	// 참조글 정보 가져오기
	public Article getReferenceArticle(Article article);
	
	// Acategory 테이블 (알고리즘문제 카테고리) ------------------------------
	
	// 알고리즘문제 카테고리 생성
	public boolean createAcategory(Acategory acategory);
	// 알고리즘문제 카테고리 수정
	public boolean updateAcategory(Acategory acategory);
	// 알고리즘문제 카테고리 삭제
	public boolean deleteAcategory(Acategory acategory);
	// 알고리즘문제 카테고리 상세조회
	public Acategory detailAcategory(Acategory acategory);
	// 알고리즘문제 카테고리 검색
	public List<Acategory> searchAcategory(Acategory acategory);
	// 알고리즘문제 카테고리 갯수 검색
	public int countAcategory(Acategory acategory);
	// 알고리즘 문제 사이트 목록 가져오기
	public List<String> selectSiteList();
	
	// Category 테이블 (글 카테고리) ------------------------------
	
	// 글 카테고리 생성
	public boolean createCategory(Category category);
	// 글 카테고리 수정
	public boolean updateCategory(Category category);
	// 글 카테고리 삭제
	public boolean deleteCategory(Category category);
	// 글 카테고리 상세조회
	public Category detailCategory(Category category);
	// 글 카테고리 검색
	public List<Category> searchCategory(Category category);
	// 글 카테고리 갯수 검색
	public int countCategory(Category category);
	
	// Articlelike 테이블 (글 좋아요) ------------------------------
	
	// 글 좋아요 클릭
	public boolean hitArticlelike(Articlelike articlelike);
	// 글 좋아요 상세조회
	public Articlelike detailArticlelike(Articlelike articlelike);
	// 글 좋아요 검색
	public List<Articlelike> searchArticlelike(Articlelike articlelike);
	// 글 좋아요 갯수 검색
	public int countArticlelike(Articlelike articlelike);
	
	// Articleshare 테이블 (글 공유) ------------------------------
	
	// 글 공유 생성
	public boolean createArticleshare(Articleshare articleshare);
	// 글 공유 수정
	public boolean updateArticleshare(Articleshare articleshare);
	// 글 공유 삭제
	public boolean deleteArticleshare(Articleshare articleshare);
	// 글 공유 상세조회
	public Articleshare detailArticleshare(Articleshare articleshare);
	// 글 공유 검색
	public List<Articleshare> searchArticleshare(Articleshare articleshare);
	// 글 공유 갯수 검색
	public int countArticleshare(Articleshare articleshare);
	
	// Articlefavorite 테이블 (글 즐겨찾기) ------------------------------

	// 글 즐겨찾기 클릭
	public String hitArticlefavorite(Articlefavorite articlefavorite);
	// 글 즐겨찾기 상세조회
	public Articlefavorite detailArticlefavorite(Articlefavorite articlefavorite);
	// 글 즐겨찾기 검색
	public List<Articlefavorite> searchArticlefavorite(Articlefavorite articlefavorite);
	// 글 즐겨찾기 갯수 검색
	public int countArticlefavorite(Articlefavorite articlefavorite);
	
	
}
