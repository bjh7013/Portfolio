package com.ssazit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssazit.dto.Arttag;
import com.ssazit.dto.Booktag;
import com.ssazit.dto.Tag;
import com.ssazit.dto.Tmptag;
import com.ssazit.dto.User;
import com.ssazit.service.TagService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/tag")
public class TagController {

	@Autowired
	private TagService tagService;
	
	
	// Tag 테이블 (대표태그) #########################################################################################################
	
	
	@ApiOperation(value = "대표태그를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/CUD")
	public ResponseEntity<String> createTag(Tag tag){
		System.out.println("controller(Tag) | createTag");
		System.out.println(tag);
		if(tagService.createTag(tag)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "대표태그를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/CUD")
	public ResponseEntity<String> updateTag(Tag tag){
		System.out.println("controller(Tag) | updateTag");
		System.out.println(tag);
		if(tagService.updateTag(tag)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "대표태그를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/CUD")
	public ResponseEntity<String> deleteTag(Tag tag){
		System.out.println("controller(Tag) | deleteTag");
		System.out.println(tag);
		if(tagService.deleteTag(tag)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "대표태그 상세보기", response = Tag.class)
	@GetMapping("/tag")
	public ResponseEntity<Tag> detailTag(Tag tag) throws Exception{
		System.out.println("controller(Tag) | detailTag");
		System.out.println(tag);
		return new ResponseEntity<Tag>(tagService.detailTag(tag), HttpStatus.OK);
	}
	
	@ApiOperation(value = "대표태그를 검색한다.", response = List.class)
	@GetMapping("/tags")
	public ResponseEntity<List<Tag>> searchTag(Tag tag) throws Exception{
		System.out.println("controller(Tag) | searchTag");
		System.out.println(tag);
		return new ResponseEntity<List<Tag>>(tagService.searchTag(tag), HttpStatus.OK);
	}
	
	@ApiOperation(value = "대표태그 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/tagscount")
	public ResponseEntity<Integer> countTag(Tag tag) throws Exception{
		System.out.println("controller(Tag) | countTag");
		System.out.println(tag);
		return new ResponseEntity<Integer>(tagService.countTag(tag), HttpStatus.OK);
	}
	
	
	// Arttag 테이블 (글 대표태그 연결) #########################################################################################################
	
	
	@ApiOperation(value = "글 대표태그 연결을 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/arttag/CUD")
	public ResponseEntity<String> createArttag(Arttag arttag){
		System.out.println("controller(Tag) | createArttag");
		System.out.println(arttag);
		if(tagService.createArttag(arttag)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글 대표태그 연결을 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/arttag/CUD")
	public ResponseEntity<String> updateArttag(Arttag arttag){
		System.out.println("controller(Tag) | updateArttag");
		System.out.println(arttag);
		if(tagService.updateArttag(arttag)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글 대표태그 연결을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/arttag/CUD")
	public ResponseEntity<String> deleteArttag(Arttag arttag){
		System.out.println("controller(Tag) | deleteArttag");
		System.out.println(arttag);
		if(tagService.deleteArttag(arttag)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글 대표태그 연결 상세보기", response = Arttag.class)
	@GetMapping("/arttag")
	public ResponseEntity<Arttag> detailArttag(Arttag arttag) throws Exception{
		System.out.println("controller(Tag) | detailArttag");
		System.out.println(arttag);
		return new ResponseEntity<Arttag>(tagService.detailArttag(arttag), HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 대표태그 연결을 검색한다.", response = List.class)
	@GetMapping("/arttags")
	public ResponseEntity<List<Arttag>> searchArttag(Arttag arttag) throws Exception{
		System.out.println("controller(Tag) | searchArttag");
		System.out.println(arttag);
		return new ResponseEntity<List<Arttag>>(tagService.searchArttag(arttag), HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 대표태그 연결 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/arttagscount")
	public ResponseEntity<Integer> countArttag(Arttag arttag) throws Exception{
		System.out.println("controller(Tag) | countArttag");
		System.out.println(arttag);
		return new ResponseEntity<Integer>(tagService.countArttag(arttag), HttpStatus.OK);
	}
	
	
	// Booktag 테이블 (책 태그 연결) ##############################################################################################################

	
	@ApiOperation(value = "책 태그 연결 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/booktag/CUD")
	public ResponseEntity<String> createBooktag(@RequestBody Booktag booktag){
		System.out.println("controller(Book) | createBooktag");
		System.out.println(booktag);
		if(tagService.createBooktag(booktag)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "책 태그 연결을 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/booktag/CUD")
	public ResponseEntity<String> updateBooktag(Booktag booktag){
		System.out.println("controller(Book) | updateBooktag");
		System.out.println(booktag);
		if(tagService.updateBooktag(booktag)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "책 태그 연결을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/booktag/CUD")
	public ResponseEntity<String> deleteBooktag(Booktag booktag){
		System.out.println("controller(Book) | deleteBooktag");
		System.out.println(booktag);
		if(tagService.deleteBooktag(booktag)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "책 태그 연결 상세보기", response = Booktag.class)
	@GetMapping("/booktag")
	public ResponseEntity<Booktag> detailBooktag(Booktag booktag) throws Exception{
		System.out.println("controller(Book) | detailBooktag");
		System.out.println(booktag);
		return new ResponseEntity<Booktag>(tagService.detailBooktag(booktag), HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 태그 연결 정보를 검색한다.", response = List.class)
	@GetMapping("/booktags")
	public ResponseEntity<List<Booktag>> searchBooktag(Booktag booktag) throws Exception{
		System.out.println("controller(Book) | searchBooktag");
		System.out.println(booktag);
		return new ResponseEntity<List<Booktag>>(tagService.searchBooktag(booktag), HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 태그 연결 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/booktagscount")
	public ResponseEntity<Integer> countBooktag(Booktag booktag) throws Exception{
		System.out.println("controller(Book) | countBooktag");
		System.out.println(booktag);
		return new ResponseEntity<Integer>(tagService.countBooktag(booktag), HttpStatus.OK);
	}
	
	
	// Tmptag 테이블 (임시태그) #########################################################################################################
	
	
	@ApiOperation(value = "임시태그를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/tmptag/CUD")
	public ResponseEntity<String> createTmptag(@RequestBody Tmptag tmptag){
		System.out.println("controller(Tag) | createTmptag");
		System.out.println(tmptag);
		if(tagService.createTmptag(tmptag)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "임시태그를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/tmptag/CUD")
	public ResponseEntity<String> updateTmptag(Tmptag tmptag){
		System.out.println("controller(Tag) | updateTmptag");
		System.out.println(tmptag);
		if(tagService.updateTmptag(tmptag)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "임시태그를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success'또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/tmptag/CUD")
	public ResponseEntity<String> deleteTmptag(Tmptag tmptag){
		System.out.println("controller(Tag) | deleteTmptag");
		System.out.println(tmptag);
		if(tagService.deleteTmptag(tmptag)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "임시태그 상세보기", response = Tmptag.class)
	@GetMapping("/tmptag")
	public ResponseEntity<Tmptag> detailTmptag(Tmptag tmptag) throws Exception{
		System.out.println("controller(Tag) | detailTmptag");
		System.out.println(tmptag);
		return new ResponseEntity<Tmptag>(tagService.detailTmptag(tmptag), HttpStatus.OK);
	}
	
	@ApiOperation(value = "임시태그를 검색한다.", response = List.class)
	@GetMapping("/tmptags")
	public ResponseEntity<List<Tmptag>> searchTmptag(Tmptag tmptag) throws Exception{
		System.out.println("controller(Tag) | searchTmptag");
		System.out.println(tmptag);
		return new ResponseEntity<List<Tmptag>>(tagService.searchTmptag(tmptag), HttpStatus.OK);
	}
	
	@ApiOperation(value = "임시태그 갯수를 검색한다.", response = Integer.class)
	@GetMapping("/tmptagscount")
	public ResponseEntity<Integer> countTmptag(Tmptag tmptag) throws Exception{
		System.out.println("controller(Tag) | countTmptag");
		System.out.println(tmptag);
		return new ResponseEntity<Integer>(tagService.countTmptag(tmptag), HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@ApiOperation(value = "임시태그를 입력한다(대표태그로 수정). 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/tmptotag")
	public ResponseEntity<String> tmpToTag(@RequestBody List<Tmptag> newTags, HttpServletRequest request){
		System.out.println("controller(Tag) | tmpToTag");
		System.out.println(newTags);
		
		int userNo = ((User)request.getAttribute("userinfo")).getUserNo();
		if(tagService.tmpToTag(newTags, userNo)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "대표태그의 그룹을 수정한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/updatetaggroup")
	public ResponseEntity<String> updateTagGroup(@RequestBody List<Tag> tags, HttpServletRequest request){
		System.out.println("controller(Tag) | updateTagGroup");
		System.out.println(tags);
		
		int userNo = ((User)request.getAttribute("userinfo")).getUserNo();
		if(tagService.updateTagGroup(tags, userNo)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
}
