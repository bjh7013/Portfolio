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

import com.ssazit.dto.Bcontent;
import com.ssazit.dto.Book;
import com.ssazit.dto.BookTagCat;
import com.ssazit.dto.Bookcategory;
import com.ssazit.dto.Bookfavorite;
import com.ssazit.dto.Booklike;
import com.ssazit.dto.Bookseries;
import com.ssazit.dto.Series;
import com.ssazit.dto.User;
import com.ssazit.service.BookService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	
	// Book 테이블 (책) ##############################################################################################################

	
	@ApiOperation(value = "책 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/CUD")
	public ResponseEntity<String> createBook(
										@RequestBody BookTagCat booktagcat,
										HttpServletRequest request){
		System.out.println("controller(Book) | createBook");
		System.out.println(booktagcat);
		
		User userinfo = (User) request.getAttribute("userinfo");
		int userNo = userinfo.getUserNo();

		try {
			if(bookService.createBook(booktagcat, userNo)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "책 정보 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/CUD")
	public ResponseEntity<String> updateBook(
										@RequestBody BookTagCat booktagcat, 
										HttpServletRequest request){
		System.out.println("controller(Book) | updateBook");
		System.out.println(booktagcat);
		
		User userinfo = (User) request.getAttribute("userinfo");
		int userNo = userinfo.getUserNo();
		
		try {
			if(bookService.updateBook(booktagcat, userNo)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "책을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/CUD")
	public ResponseEntity<String> deleteBook(Book book, HttpServletRequest request){
		System.out.println("controller(Book) | deleteBook");
		System.out.println(book);
		
		User userinfo = (User) request.getAttribute("userinfo");
		int userNo = userinfo.getUserNo();
		
		try {
			if(bookService.deleteBook(book,userNo)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "책 상세보기", response = Book.class)
	@GetMapping("/book/{bookNo}")
	public ResponseEntity<BookTagCat> detailBook(@PathVariable("bookNo") int bookNo, HttpServletRequest request){
		System.out.println("controller(Book) | detailBook");
		User loginUser = ((User)request.getAttribute("userinfo"));
		Book book = new Book();
		book.setBookNo(bookNo);
		if(loginUser == null) {
			book.setLoginUserNo(0);
		}else {
			book.setLoginUserNo(loginUser.getUserNo());			
		}
		
		
		System.out.println(book);
		try {
			return new ResponseEntity<BookTagCat>(bookService.detailBook(book), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<BookTagCat>(new BookTagCat(), HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "책 정보를 검색한다.", response = List.class)
	@GetMapping("/books")
	public ResponseEntity<List<BookTagCat>> searchBook(Book book) throws Exception{
		System.out.println("controller(Book) | searchBook");
		System.out.println(book);
		return new ResponseEntity<List<BookTagCat>>(bookService.searchBook(book), HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/bookscount")
	public ResponseEntity<Integer> countBook(Book book) throws Exception{
		System.out.println("controller(Book) | countBook");
		System.out.println(book);
		return new ResponseEntity<Integer>(bookService.countBook(book), HttpStatus.OK);
	}
	
	
	// Bcontent 테이블 (책 내용) ##############################################################################################################
	
	
	@ApiOperation(value = "책 내용 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/bcontent/CUD")
	public ResponseEntity<String> createBcontent(@RequestBody Bcontent bcontent){
		System.out.println("controller(Book) | createBcontent");
		System.out.println(bcontent);
		if(bookService.createBcontent(bcontent)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "책 내용 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/bcontent/CUD")
	public ResponseEntity<String> updateBcontent(Bcontent bcontent){
		System.out.println("controller(Book) | updateBcontent");
		System.out.println(bcontent);
		if(bookService.updateBcontent(bcontent)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "책 내용을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/bcontent/CUD")
	public ResponseEntity<String> deleteBcontent(Bcontent bcontent){
		System.out.println("controller(Book) | deleteBcontent");
		System.out.println(bcontent);
		if(bookService.deleteBcontent(bcontent)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "책 내용 상세보기", response = Bcontent.class)
	@GetMapping("/bcontent")
	public ResponseEntity<Bcontent> detailBcontent(Bcontent bcontent) throws Exception{
		System.out.println("controller(Book) | detailBcontent");
		System.out.println(bcontent);
		return new ResponseEntity<Bcontent>(bookService.detailBcontent(bcontent), HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 내용 정보를 검색한다.", response = List.class)
	@GetMapping("/bcontents")
	public ResponseEntity<List<Bcontent>> searchBcontent(Bcontent bcontent) throws Exception{
		System.out.println("controller(Book) | searchBcontent");
		System.out.println(bcontent);
		return new ResponseEntity<List<Bcontent>>(bookService.searchBcontent(bcontent), HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 내용 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/bcontentsscount")
	public ResponseEntity<Integer> countBcontent(Bcontent bcontent) throws Exception{
		System.out.println("controller(Book) | countBcontent");
		System.out.println(bcontent);
		return new ResponseEntity<Integer>(bookService.countBcontent(bcontent), HttpStatus.OK);
	}
	
	
	// Series 테이블 (시리즈) ##############################################################################################################
	
	
	@ApiOperation(value = "시리즈 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/series/CUD")
	public ResponseEntity<String> createSeries(Series series){
		System.out.println("controller(Book) | createSeries");
		System.out.println(series);
		if(bookService.createSeries(series)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "시리즈 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/series/CUD")
	public ResponseEntity<String> updateSeries(Series series){
		System.out.println("controller(Book) | updateSeries");
		System.out.println(series);
		if(bookService.updateSeries(series)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "시리즈를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/series/CUD")
	public ResponseEntity<String> deleteSeries(Series series){
		System.out.println("controller(Book) | deleteSeries");
		System.out.println(series);
		if(bookService.deleteSeries(series)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "시리즈 상세보기", response = Series.class)
	@GetMapping("/series")
	public ResponseEntity<Series> detailSeries(Series series) throws Exception{
		System.out.println("controller(Book) | detailSeries");
		System.out.println(series);
		return new ResponseEntity<Series>(bookService.detailSeries(series), HttpStatus.OK);
	}
	
	@ApiOperation(value = "시리즈 정보를 검색한다.", response = List.class)
	@GetMapping("/seriess")
	public ResponseEntity<List<Series>> searchSeries(Series series) throws Exception{
		System.out.println("controller(Book) | searchSeries");
		System.out.println(series);
		return new ResponseEntity<List<Series>>(bookService.searchSeries(series), HttpStatus.OK);
	}
	
	@ApiOperation(value = "시리즈 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/seriesscount")
	public ResponseEntity<Integer> countSeries(Series series) throws Exception{
		System.out.println("controller(Book) | countSeries");
		System.out.println(series);
		return new ResponseEntity<Integer>(bookService.countSeries(series), HttpStatus.OK);
	}
	
	
	// Bookseries 테이블 (책 시리즈 연결) ##############################################################################################################

	
	@ApiOperation(value = "책 시리즈 연결 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/bookseries/CUD")
	public ResponseEntity<String> createBookseries(Bookseries bookseries){
		System.out.println("controller(Book) | createBookseries");
		System.out.println(bookseries);
		if(bookService.createBookseries(bookseries)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "책 시리즈 연결 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/bookseries/CUD")
	public ResponseEntity<String> updateBookseries(Bookseries bookseries){
		System.out.println("controller(Book) | updateBookseries");
		System.out.println(bookseries);
		if(bookService.updateBookseries(bookseries)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "책 시리즈 연결을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/bookseries/CUD")
	public ResponseEntity<String> deleteBookseries(Bookseries bookseries){
		System.out.println("controller(Book) | deleteBookseries");
		System.out.println(bookseries);
		if(bookService.deleteBookseries(bookseries)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "책 시리즈 연결 상세보기", response = Bookseries.class)
	@GetMapping("/bookseries")
	public ResponseEntity<Bookseries> detailBookseries(Bookseries bookseries) throws Exception{
		System.out.println("controller(Book) | detailBookseries");
		System.out.println(bookseries);
		return new ResponseEntity<Bookseries>(bookService.detailBookseries(bookseries), HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 시리즈 연결 정보를 검색한다.", response = List.class)
	@GetMapping("/bookseriess")
	public ResponseEntity<List<Bookseries>> searchBookseries(Bookseries bookseries) throws Exception{
		System.out.println("controller(Book) | searchBookseries");
		System.out.println(bookseries);
		return new ResponseEntity<List<Bookseries>>(bookService.searchBookseries(bookseries), HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 시리즈 연결 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/bookseriesscount")
	public ResponseEntity<Integer> countBookseries(Bookseries bookseries) throws Exception{
		System.out.println("controller(Book) | countBookseries");
		System.out.println(bookseries);
		return new ResponseEntity<Integer>(bookService.countBookseries(bookseries), HttpStatus.OK);
	}
	
	
	// Booklike 테이블 (책 좋아요) ##############################################################################################################
	
	
	@ApiOperation(value = "책 좋아요 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/booklike/CUD")
	public ResponseEntity<String> createBooklike(@RequestBody Booklike booklike, HttpServletRequest request){
		System.out.println("controller(Book) | createBooklike");
		System.out.println(booklike);
		booklike.setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		System.out.println(booklike);
		
		if(bookService.hitBooklike(booklike)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
//	@ApiOperation(value = "책 좋아요를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@PutMapping("/booklike/CUD")
//	public ResponseEntity<String> updateBooklike(Booklike booklike){
//		System.out.println("controller(Book) | updateBooklike");
//		System.out.println(booklike);
//		if(bookService.updateBooklike(booklike)) {
//			return new ResponseEntity<String>("success", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
//	}
//	
//	@ApiOperation(value = "책 좋아요를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
//	@DeleteMapping("/booklike/CUD")
//	public ResponseEntity<String> deleteBooklike(Booklike booklike){
//		System.out.println("controller(Book) | deleteBooklike");
//		System.out.println(booklike);
//		if(bookService.deleteBooklike(booklike)) {
//			return new ResponseEntity<String>("success", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
//	}
	
	@ApiOperation(value = "책 좋아요 상세보기", response = Bookseries.class)
	@GetMapping("/booklike")
	public ResponseEntity<Booklike> detailBooklike(Booklike booklike) throws Exception{
		System.out.println("controller(Book) | detailBooklike");
		System.out.println(booklike);
		return new ResponseEntity<Booklike>(bookService.detailBooklike(booklike), HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 좋아요 정보를 검색한다.", response = List.class)
	@GetMapping("/booklikes")
	public ResponseEntity<List<Booklike>> searchBooklike(Booklike booklike) throws Exception{
		System.out.println("controller(Book) | searchBooklike");
		System.out.println(booklike);
		return new ResponseEntity<List<Booklike>>(bookService.searchBooklike(booklike), HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 좋아요 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/booklikescount")
	public ResponseEntity<Integer> countBooklike(Booklike booklike) throws Exception{
		System.out.println("controller(Book) | countBooklike");
		System.out.println(booklike);
		return new ResponseEntity<Integer>(bookService.countBooklike(booklike), HttpStatus.OK);
	}
	
	
	// Bookfavorite 테이블 (책 즐겨찾기) ##############################################################################################################
	
	
	@ApiOperation(value = "책 즐겨찾기 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/bookfavorite/CUD")
	public ResponseEntity<String> createBookfavorite(@RequestBody Bookfavorite bookfavorite, HttpServletRequest request){
		System.out.println("controller(Book) | createBookfavorite");
		bookfavorite.setUserNo(((User)request.getAttribute("userinfo")).getUserNo());
		System.out.println(bookfavorite);
		
		String result = bookService.hitBookfavorite(bookfavorite);
		System.out.println(result);
		if(result.equals("insert")) {
			return new ResponseEntity<String>("insert", HttpStatus.OK);
		}else if(result.equals("delete")) {
			return new ResponseEntity<String>("delete", HttpStatus.OK);			
		}
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
//	@ApiOperation(value = "책 즐겨찾기를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@PutMapping("/bookfavorite/CUD")
//	public ResponseEntity<String> updateBookfavorite(Bookfavorite bookfavorite){
//		System.out.println("controller(Book) | updateBookfavorite");
//		System.out.println(bookfavorite);
//		if(bookService.updateBookfavorite(bookfavorite)) {
//			return new ResponseEntity<String>("success", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
//	}
//	
//	@ApiOperation(value = "책 즐겨찾기를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
//	@DeleteMapping("/bookfavorite/CUD")
//	public ResponseEntity<String> deleteBookfavorite(Bookfavorite bookfavorite){
//		System.out.println("controller(Book) | deleteBookfavorite");
//		System.out.println(bookfavorite);
//		if(bookService.deleteBookfavorite(bookfavorite)) {
//			return new ResponseEntity<String>("success", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
//	}
	
	@ApiOperation(value = "책 즐겨찾기 상세보기", response = Bookfavorite.class)
	@GetMapping("/bookfavorite")
	public ResponseEntity<Bookfavorite> detailBookfavorite(Bookfavorite bookfavorite) throws Exception{
		System.out.println("controller(Book) | detailBookfavorite");
		System.out.println(bookfavorite);
		return new ResponseEntity<Bookfavorite>(bookService.detailBookfavorite(bookfavorite), HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 즐겨찾기 정보를 검색한다.", response = List.class)
	@GetMapping("/bookfavorites")
	public ResponseEntity<List<Bookfavorite>> searchBookfavorite(Bookfavorite bookfavorite) throws Exception{
		System.out.println("controller(Book) | searchBookfavorite");
		System.out.println(bookfavorite);
		return new ResponseEntity<List<Bookfavorite>>(bookService.searchBookfavorite(bookfavorite), HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 즐겨찾기 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/bookfavoritescount")
	public ResponseEntity<Integer> countBookfavorite(Bookfavorite bookfavorite) throws Exception{
		System.out.println("controller(Book) | countBookfavorite");
		System.out.println(bookfavorite);
		return new ResponseEntity<Integer>(bookService.countBookfavorite(bookfavorite), HttpStatus.OK);
	}
	
	
	// Bookcategory 테이블 (책 카테고리 연결) ##############################################################################################################
	
	
	@ApiOperation(value = "책 카테고리 연결 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/bookcategory/CUD")
	public ResponseEntity<String> createBookcategory(Bookcategory bookcategory){
		System.out.println("controller(Book) | createBookcategory");
		System.out.println(bookcategory);
		if(bookService.createBookcategory(bookcategory)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "책 카테고리 연결을 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/bookcategory/CUD")
	public ResponseEntity<String> updateBookcategory(Bookcategory bookcategory){
		System.out.println("controller(Book) | updateBookcategory");
		System.out.println(bookcategory);
		if(bookService.updateBookcategory(bookcategory)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "책 카테고리 연결을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/bookcategory/CUD")
	public ResponseEntity<String> deleteBookcategory(Bookcategory bookcategory){
		System.out.println("controller(Book) | deleteBookcategory");
		System.out.println(bookcategory);
		if(bookService.deleteBookcategory(bookcategory)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "책 카테고리 연결 상세보기", response = Bookcategory.class)
	@GetMapping("/bookcategory")
	public ResponseEntity<Bookcategory> detailBookcategory(Bookcategory bookcategory) throws Exception{
		System.out.println("controller(Book) | detailBookcategory");
		System.out.println(bookcategory);
		return new ResponseEntity<Bookcategory>(bookService.detailBookcategory(bookcategory), HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 카테고리 연결 정보를 검색한다.", response = List.class)
	@GetMapping("/bookcategorys")
	public ResponseEntity<List<Bookcategory>> searchBookcategory(Bookcategory bookcategory) throws Exception{
		System.out.println("controller(Book) | searchBookcategory");
		System.out.println(bookcategory);
		return new ResponseEntity<List<Bookcategory>>(bookService.searchBookcategory(bookcategory), HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 카테고리 연결 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/bookcategoryscount")
	public ResponseEntity<Integer> countBookcategory(Bookcategory bookcategory) throws Exception{
		System.out.println("controller(Book) | countBookcategory");
		System.out.println(bookcategory);
		return new ResponseEntity<Integer>(bookService.countBookcategory(bookcategory), HttpStatus.OK);
	}

}
