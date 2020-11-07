package com.ssazit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssazit.dao.ArttagDAO;
import com.ssazit.dao.BooktagDAO;
import com.ssazit.dao.TagDAO;
import com.ssazit.dao.TmptagDAO;
import com.ssazit.dao.UserDAO;
import com.ssazit.dto.Arttag;
import com.ssazit.dto.Booktag;
import com.ssazit.dto.Tag;
import com.ssazit.dto.Tmptag;
import com.ssazit.dto.User;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	TagDAO tagDAO;
	
	@Autowired
	ArttagDAO arttagDAO;
	
	@Autowired
	BooktagDAO booktagDAO;
	
	@Autowired
	TmptagDAO tmptagDAO;
	
	@Autowired
	UserDAO userDAO;
	
	
	// Tag 테이블 (대표태그) ------------------------------------------
	
	
	// 대표태그 생성
	@Override
	public boolean createTag(Tag tag) {
		System.out.println("service(Tag) | createTag");
		System.out.println(tag);
		int cnt = tagDAO.insertTag(tag);
		return cnt==1?true:false;
	}

	// 대표태그 수정
	@Override
	public boolean updateTag(Tag tag) {
		System.out.println("service(Tag) | updateTag");
		System.out.println(tag);
		int cnt = tagDAO.updateTag(tag);
		return cnt==1?true:false;
	}

	// 대표태그 삭제
	@Override
	public boolean deleteTag(Tag tag) {
		System.out.println("service(Tag) | deleteTag");
		System.out.println(tag);
		int cnt = tagDAO.deleteTag(tag);
		return cnt==1?true:false;
	}

	// 대표태그 상세조회
	@Override
	public Tag detailTag(Tag tag) {
		System.out.println("service(Tag) | detailTag");
		System.out.println(tag);
		return tagDAO.selectTagOne(tag);
	}

	// 대표태그 검색
	@Override
	public List<Tag> searchTag(Tag tag) {
		System.out.println("service(Tag) | searchTag");
		System.out.println(tag);
		return tagDAO.selectTagList(tag);
	}

	// 대표태그 갯수 검색
	@Override
	public int countTag(Tag tag) {
		System.out.println("service(Tag) | countTag");
		System.out.println(tag);
		return tagDAO.selectTagCount(tag);
	}
	
	// 대표태그 그룹 변경
	@Override
	public boolean updateTagGroup(List<Tag> tags, int userNo) {
		System.out.println("service(Tag) | updateTagGroup");
		for(Tag tag : tags) {
			System.out.println(tag);
			if(tagDAO.updateTag(tag)!=1) {
				System.out.println("태그 그룹 변경 실패!" + tag);
				return false;
			}
		}
		return true;
	}
	
	// Arttag 테이블 (글 대표태그 연결) ------------------------------------------
	
	
	// 글 대표태그 연결 생성
	@Override
	public boolean createArttag(Arttag arttag) {
		System.out.println("service(Tag) | createArttag");
		System.out.println(arttag);
		int cnt = arttagDAO.insertArttag(arttag);
		return cnt==1?true:false;
	}

	// 글 대표태그 연결 수정
	@Override
	public boolean updateArttag(Arttag arttag) {
		System.out.println("service(Tag) | updateArttag");
		System.out.println(arttag);
		int cnt = arttagDAO.updateArttag(arttag);
		return cnt==1?true:false;
	}

	// 글 대표태그 연결 삭제
	@Override
	public boolean deleteArttag(Arttag arttag) {
		System.out.println("service(Tag) | deleteArttag");
		System.out.println(arttag);
		int cnt = arttagDAO.deleteArttag(arttag);
		return cnt==1?true:false;
	}

	// 글 대표태그 연결 상세조회
	@Override
	public Arttag detailArttag(Arttag arttag) {
		System.out.println("service(Tag) | detailArttag");
		System.out.println(arttag);
		return arttagDAO.selectArttagOne(arttag);
	}

	// 글 대표태그 연결 검색
	@Override
	public List<Arttag> searchArttag(Arttag arttag) {
		System.out.println("service(Tag) | searchArttag");
		System.out.println(arttag);
		return arttagDAO.selectArttagList(arttag);
	}

	// 글 대표태그 연결 갯수 검색
	@Override
	public int countArttag(Arttag arttag) {
		System.out.println("service(Tag) | countArttag");
		System.out.println(arttag);
		return arttagDAO.selectArttagCount(arttag);
	}
	
	
	// Booktag 테이블 (책 태그 연결) ------------------------------------------
	
	
	// 책 태그 연결 생성
	@Override
	public boolean createBooktag(Booktag booktag) {
		System.out.println("service(Book) | createBooktag");
		System.out.println(booktag);
		int cnt = booktagDAO.insertBooktag(booktag);
		return cnt==1?true:false;
	}

	// 책 태그 연결 수정
	@Override
	public boolean updateBooktag(Booktag booktag) {
		System.out.println("service(Book) | updateBooktag");
		System.out.println(booktag);
		int cnt = booktagDAO.updateBooktag(booktag);
		return cnt==1?true:false;
	}

	// 책 태그 연결 삭제
	@Override
	public boolean deleteBooktag(Booktag booktag) {
		System.out.println("service(Book) | deleteBooktag");
		System.out.println(booktag);
		int cnt = booktagDAO.deleteBooktag(booktag);
		return cnt==1?true:false;
	}

	// 책 태그 연결 상세조회
	@Override
	public Booktag detailBooktag(Booktag booktag) {
		System.out.println("service(Book) | detailBooktag");
		System.out.println(booktag);
		return booktagDAO.selectBooktagOne(booktag);
	}

	// 책 태그 연결 검색
	@Override
	public List<Booktag> searchBooktag(Booktag booktag) {
		System.out.println("service(Book) | searchBooktag");
		System.out.println(booktag);
		return booktagDAO.selectBooktagList(booktag);
	}

	// 책 태그 연결 갯수 검색
	@Override
	public int countBooktag(Booktag booktag) {
		System.out.println("service(Book) | countBooktag");
		System.out.println(booktag);
		return booktagDAO.selectBooktagCount(booktag);
	}
	
	
	
	// Tmptag 테이블 (임시태그) ------------------------------------------
	
	
	// 임시태그 생성
	@Override
	public boolean createTmptag(Tmptag tmptag) {
		System.out.println("service(Tag) | createTmptag");
		System.out.println(tmptag);
		int cnt = tmptagDAO.insertTmptag(tmptag);
		return cnt==1?true:false;
	}

	// 임시태그 수정
	@Override
	public boolean updateTmptag(Tmptag tmptag) {
		System.out.println("service(Tag) | updateTmptag");
		System.out.println(tmptag);
		int cnt = tmptagDAO.updateTmptag(tmptag);
		return cnt==1?true:false;
	}

	// 임시태그 삭제
	@Override
	public boolean deleteTmptag(Tmptag tmptag) {
		System.out.println("service(Tag) | deleteTmptag");
		System.out.println(tmptag);
		int cnt = tmptagDAO.deleteTmptag(tmptag);
		return cnt==1?true:false;
	}

	// 임시태그 상세조회
	@Override
	public Tmptag detailTmptag(Tmptag tmptag) {
		System.out.println("service(Tag) | detailTmptag");
		System.out.println(tmptag);
		return tmptagDAO.selectTmptagOne(tmptag);
	}

	// 임시태그 검색
	@Override
	public List<Tmptag> searchTmptag(Tmptag tmptag) {
		System.out.println("service(Tag) | searchTmptag");
		System.out.println(tmptag);
		return tmptagDAO.selectTmptagList(tmptag);
	}

	// 임시태그 갯수 검색
	@Override
	public int countTmptag(Tmptag tmptag) {
		System.out.println("service(Tag) | countTmptag");
		System.out.println(tmptag);
		return tmptagDAO.selectTmptagCount(tmptag);
	}

	// 임시태그 -> 정식태그
	@Override
	public boolean tmpToTag(List<Tmptag> tmptags, int userNo) {
		// TODO Auto-generated method stub
		System.out.println("service(Tag) | tmpToTag");
		
		// 사용자 == 관리자인지 확인
		User user = new User();
		user.setUserNo(userNo);
		System.out.println(user);
		if(!userDAO.selectUserOne(user).isAdmin()) {
			System.out.println("관리자가 아닙니다, 권한이 없습니다!");
			System.out.println(userDAO.selectUserOne(user).isAdmin());
			return false;
		}
		
		// tmptags tmptag에서 삭제, tag추가
		for(Tmptag tmptag : tmptags) {
			System.out.println(tmptag);
			Tag tag = new Tag();
			tag.setTagName(tmptag.getTmptagName());

			// 태그 작성
			if(tagDAO.insertTag(tag) != 1) {
				System.out.println("1-1) 태그 작성 중 문제 발생!");
				return false;
			}
			System.out.println("1-1) 태그 작성 완료");
			
			tag = tagDAO.selectTagOne(tag);
			if(tag == null) {
				System.out.println("1-2) 태그 조회 중 문제 발생!");
				return false;
			}
			System.out.println("1-2) 태그 조회 완료");
			
			tag.setGroupNo(tag.getTagNo());
			if(tagDAO.updateTag(tag) != 1) {
				System.out.println("1-3) 태그 그룹화 중 문제 발생!");
				return false;
			}
			System.out.println("1-3) 태그 그룹화 완료");
			
			// 태그 - 책|게시글 연결
			// 게시글일때 Arttag
			if(tmptag.getArticleNo() > 0) {
				Arttag arttag = new Arttag();
				arttag.setArticleNo(tmptag.getArticleNo());
				arttag.setTagNo(tag.getTagNo());
				arttagDAO.insertArttag(arttag);
				System.out.println("2) Arttag 연결 완료");
			}
			// 책일때 Booktag
			else if(tmptag.getBookNo() > 0) {
				Booktag booktag = new Booktag();
				booktag.setBookNo(tmptag.getBookNo());
				booktag.setTagNo(tagDAO.selectTagOne(tag).getTagNo());
				booktagDAO.insertBooktag(booktag);
				System.out.println("2) Booktag 연결 완료");
			}
			else {
				System.out.println("2) 태그 연결 실패");
				return false;
			}
			
			// 임시태그 삭제
			if(tmptagDAO.deleteTmptag(tmptag) != 1) {
				System.out.println("3) 임시태그 삭제 실패");
				return false;
			}
			System.out.println("3) 임시태그 삭제 완료");
		}
		return true;
	}
	
	
}
