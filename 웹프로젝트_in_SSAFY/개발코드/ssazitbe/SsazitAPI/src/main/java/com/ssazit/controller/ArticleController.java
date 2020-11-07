package com.ssazit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssazit.dto.Acategory;
import com.ssazit.dto.Article;
import com.ssazit.dto.ArticleTagCat;
import com.ssazit.dto.Articlefavorite;
import com.ssazit.dto.Articlelike;
import com.ssazit.dto.Articleshare;
import com.ssazit.dto.Category;
import com.ssazit.dto.User;
import com.ssazit.service.ArticleService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired 
	private ArticleService articleService;
	
	
	// Article 테이블 (게시글) #########################################################################################################
	
	
	@ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/CUD")
	public ResponseEntity<String> createArticle(@RequestBody ArticleTagCat articleTagCat, HttpServletRequest request){
		System.out.println("controller(Article) | createArticle");
		System.out.println(articleTagCat);
		
		articleTagCat.getArticle().setUserNo(((User)request.getAttribute("userinfo")).getUserNo());;
		
		try {
			if(articleService.createArticle(articleTagCat)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "게시글 정보 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/CUD")
	public ResponseEntity<String> updateArticle(@RequestBody ArticleTagCat articleTagCat, HttpServletRequest request){
		
		System.out.println("controller(Article) | updateArticle");
		System.out.println(articleTagCat);
		articleTagCat.getArticle().setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		
		try {
			if(articleService.updateArticle(articleTagCat)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "게시글 번호에 해당하는 블로그를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/CUD")
	public ResponseEntity<String> deleteArticle(Article article, HttpServletRequest request){
		System.out.println("controller(Article) | deleteArticle");
		article.setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		System.out.println(article);
		
		if(articleService.deleteArticle(article)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시글 상세보기", response = Article.class)
	@GetMapping("/article/{articleNo}")
	public ResponseEntity<ArticleTagCat> detailArticle(@PathVariable("articleNo") int articleNo, @RequestParam(value="userNo", defaultValue="0") int userNo) throws Exception{
		System.out.println("====================================================");
		System.out.println("controller(Article) | detailArticle");
		Article article = new Article();
		article.setArticleNo(articleNo);
		article.setLoginUserNo(userNo);
		
		System.out.println(article);
		
		return new ResponseEntity<>(articleService.detailArticle(article), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글 정보를 검색한다.", response = List.class)
	@GetMapping("/articles")
	public ResponseEntity<List<ArticleTagCat>> searchArticle(Article article, HttpServletRequest request){
		System.out.println("controller(Article) | searchArticle");
		System.out.println(article);
		
		User user = (User)request.getAttribute("userinfo");
		
		if(user != null) {
			article.setLoginUserNo(user.getUserNo());
			System.out.println("로그인한 유저에 의한 조회~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		
		if(article.getSearchValue() == null)
			article.setSearchValue("");

		return new ResponseEntity<List<ArticleTagCat>>(articleService.searchArticle(article), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/articlescount")
	public ResponseEntity<Integer> countArticle(Article article) throws Exception{
		System.out.println("controller(Article) | countArticle");
		System.out.println(article);
		return new ResponseEntity<Integer>(articleService.countArticle(article), HttpStatus.OK);
	}
	
	@GetMapping("/reference")
	public ResponseEntity<Article> getReferenceArticle(Article article){
		
		return new ResponseEntity<Article>(articleService.getReferenceArticle(article), HttpStatus.OK);
	}
	
	// Acategory 테이블 (알고리즘문제 카테고리) #########################################################################################################
	
	
	@ApiOperation(value = "알고리즘문제 카테고리를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/acategory/CUD")
	public ResponseEntity<String> createAcategory(Acategory acategory){
		System.out.println("controller(Article) | createAcategory");
		System.out.println(acategory);
		if(articleService.createAcategory(acategory)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "알고리즘문제 카테고리를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/acategory/CUD")
	public ResponseEntity<String> updateAcategory(Acategory acategory){
		System.out.println("controller(Article) | updateAcategory");
		System.out.println(acategory);
		if(articleService.updateAcategory(acategory)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "알고리즘문제 카테고리를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/acategory/CUD")
	public ResponseEntity<String> deleteAcategory(Acategory acategory){
		System.out.println("controller(Article) | deleteAcategory");
		System.out.println(acategory);
		if(articleService.deleteAcategory(acategory)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "알고리즘문제 카테고리 상세보기", response = Acategory.class)
	@GetMapping("/acategory")
	public ResponseEntity<Acategory> detailAcategory(Acategory acategory) throws Exception{
		System.out.println("controller(Article) | detailAcategory");
		System.out.println(acategory);
		return new ResponseEntity<Acategory>(articleService.detailAcategory(acategory), HttpStatus.OK);
	}
	
	@ApiOperation(value = "알고리즘문제 카테고리 정보를 검색한다.", response = List.class)
	@GetMapping("/acategorys")
	public ResponseEntity<List<Acategory>> searchAcategory(Acategory acategory) throws Exception{
		System.out.println("controller(Article) | searchAcategory");
		System.out.println(acategory);
		return new ResponseEntity<List<Acategory>>(articleService.searchAcategory(acategory), HttpStatus.OK);
	}
	
	@GetMapping("/acategorys/site")
	public ResponseEntity<List<String>> selectSiteList(){
		return new ResponseEntity<List<String>>(articleService.selectSiteList(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "알고리즘문제 카테고리 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/acategoryscount")
	public ResponseEntity<Integer> countAcategory(Acategory acategory) throws Exception{
		System.out.println("controller(Article) | countAcategory");
		System.out.println(acategory);
		return new ResponseEntity<Integer>(articleService.countAcategory(acategory), HttpStatus.OK);
	}
	
	
	// Category 테이블 (글 카테고리) #########################################################################################################
	
	
	@ApiOperation(value = "글 카테고리를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/category/CUD")
	public ResponseEntity<String> createCategory(Category category){
		System.out.println("controller(Article) | createCategory");
		System.out.println(category);
		if(articleService.createCategory(category)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글 카테고리를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/category/CUD")
	public ResponseEntity<String> updateCategory(Category category){
		System.out.println("controller(Article) | updateCategory");
		System.out.println(category);
		if(articleService.updateCategory(category)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글 카테고리를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/category/CUD")
	public ResponseEntity<String> deleteCategory(Category category){
		System.out.println("controller(Article) | deleteCategory");
		System.out.println(category);
		if(articleService.deleteCategory(category)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글 카테고리 상세보기", response = Category.class)
	@GetMapping("/category")
	public ResponseEntity<Category> detailCategory(Category category) throws Exception{
		System.out.println("controller(Article) | detailCategory");
		System.out.println(category);
		return new ResponseEntity<Category>(articleService.detailCategory(category), HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 카테고리 정보를 검색한다.", response = List.class)
	@GetMapping("/categorys")
	public ResponseEntity<List<Category>> searchCategory(Category category) throws Exception{
		System.out.println("controller(Article) | searchCategory");
		System.out.println(category);
		return new ResponseEntity<List<Category>>(articleService.searchCategory(category), HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 카테고리 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/categoryscount")
	public ResponseEntity<Integer> countCategory(Category category) throws Exception{
		System.out.println("controller(Article) | countCategory");
		System.out.println(category);
		return new ResponseEntity<Integer>(articleService.countCategory(category), HttpStatus.OK);
	}
	
	
	// Articlelike 테이블 (글 좋아요) #########################################################################################################
	
	
	@ApiOperation(value = "글 좋아요를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/articlelike/CUD")
	public ResponseEntity<String> createArticlelike(@RequestBody Articlelike articlelike, HttpServletRequest request){
		System.out.println("controller(Article) | createArticlelike");
		articlelike.setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		System.out.println(articlelike);
		
		if(articleService.hitArticlelike(articlelike)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.BAD_GATEWAY);
	}
	
	
	@ApiOperation(value = "글 좋아요 상세보기", response = Articlelike.class)
	@GetMapping("/articlelike")
	public ResponseEntity<Articlelike> detailArticlelike(Articlelike articlelike) throws Exception{
		System.out.println("controller(Article) | detailArticlelike");
		System.out.println(articlelike);
		return new ResponseEntity<Articlelike>(articleService.detailArticlelike(articlelike), HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 좋아요 정보를 검색한다.", response = List.class)
	@GetMapping("/articlelikes")
	public ResponseEntity<List<Articlelike>> searchArticlelike(Articlelike articlelike) throws Exception{
		System.out.println("controller(Article) | searchArticlelike");
		System.out.println(articlelike);
		return new ResponseEntity<List<Articlelike>>(articleService.searchArticlelike(articlelike), HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 좋아요 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/articlelikescount")
	public ResponseEntity<Integer> countArticlelike(Articlelike articlelike) throws Exception{
		System.out.println("controller(Article) | countArticlelike");
		System.out.println(articlelike);
		return new ResponseEntity<Integer>(articleService.countArticlelike(articlelike), HttpStatus.OK);
	}
	
	
	// Articleshare 테이블 (글 공유) #########################################################################################################
	
	
	@ApiOperation(value = "글 공유를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/articleshare/CUD")
	public ResponseEntity<String> createArticleshare(Articleshare articleshare){
		System.out.println("controller(Article) | createArticleshare");
		System.out.println(articleshare);
		if(articleService.createArticleshare(articleshare)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글 공유를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/articleshare/CUD")
	public ResponseEntity<String> updateArticleshare(Articleshare articleshare){
		System.out.println("controller(Article) | updateArticleshare");
		System.out.println(articleshare);
		if(articleService.updateArticleshare(articleshare)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글 공유를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/articleshare/CUD")
	public ResponseEntity<String> deleteArticleshare(Articleshare articleshare){
		System.out.println("controller(Article) | deleteArticleshare");
		System.out.println(articleshare);
		if(articleService.deleteArticleshare(articleshare)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글 공유 상세보기", response = Articleshare.class)
	@GetMapping("/articleshare")
	public ResponseEntity<Articleshare> detailArticleshare(Articleshare articleshare) throws Exception{
		System.out.println("controller(Article) | detailArticleshare");
		System.out.println(articleshare);
		return new ResponseEntity<Articleshare>(articleService.detailArticleshare(articleshare), HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 공유 정보를 검색한다.", response = List.class)
	@GetMapping("/articleshares")
	public ResponseEntity<List<Articleshare>> searchArticleshare(Articleshare articleshare) throws Exception{
		System.out.println("controller(Article) | searchArticleshare");
		System.out.println(articleshare);
		return new ResponseEntity<List<Articleshare>>(articleService.searchArticleshare(articleshare), HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 공유 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/articlesharescount")
	public ResponseEntity<Integer> countArticleshare(Articleshare articleshare) throws Exception{
		System.out.println("controller(Article) | countArticleshare");
		System.out.println(articleshare);
		return new ResponseEntity<Integer>(articleService.countArticleshare(articleshare), HttpStatus.OK);
	}
	
	
	// Articlefavorite 테이블 (글 즐겨찾기) #########################################################################################################
	
	
	@ApiOperation(value = "글 즐겨찾기를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/articlefavorite/CUD")
	public ResponseEntity<String> createArticlefavorite(@RequestBody Articlefavorite articlefavorite, HttpServletRequest request){
		System.out.println("controller(Article) | createArticlefavorite");
		articlefavorite.setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		
		System.out.println(articlefavorite);
		
		String result = articleService.hitArticlefavorite(articlefavorite);
		if(result.equals("insert")) {
			return new ResponseEntity<String>("insert", HttpStatus.OK);
		}else if(result.equals("delete")) {
			return new ResponseEntity<String>("delete", HttpStatus.OK);			
		}
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	
	@ApiOperation(value = "글 즐겨찾기 상세보기", response = Articlefavorite.class)
	@GetMapping("/articlefavorite")
	public ResponseEntity<Articlefavorite> detailArticlefavorite(Articlefavorite articlefavorite) throws Exception{
		System.out.println("controller(Article) | detailArticlefavorite");
		System.out.println(articlefavorite);
		return new ResponseEntity<Articlefavorite>(articleService.detailArticlefavorite(articlefavorite), HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 즐겨찾기 정보를 검색한다.", response = List.class)
	@GetMapping("/articlefavorites")
	public ResponseEntity<List<Articlefavorite>> searchArticlefavorite(Articlefavorite articlefavorite, HttpServletRequest request) throws Exception{
		System.out.println("controller(Article) | searchArticlefavorite");
		articlefavorite.setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		System.out.println(articlefavorite);
		
		return new ResponseEntity<List<Articlefavorite>>(articleService.searchArticlefavorite(articlefavorite), HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 즐겨찾기 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/articlefavoritescount")
	public ResponseEntity<Integer> countArticlefavorite(Articlefavorite articlefavorite) throws Exception{
		System.out.println("controller(Article) | countArticlefavorite");
		System.out.println(articlefavorite);
		return new ResponseEntity<Integer>(articleService.countArticlefavorite(articlefavorite), HttpStatus.OK);
	}
	
	
}
