package com.ssazit.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssazit.dao.ArticleDAO;
import com.ssazit.dao.BlogDAO;
import com.ssazit.dao.MyboardDAO;
import com.ssazit.dto.Article;
import com.ssazit.dto.Blog;
import com.ssazit.dto.Myboard;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	MyboardDAO myboardDAO;
	
	@Autowired
	ArticleDAO articleDAO;
	
	// Blog 테이블 (블로그) ------------------------------------------
	
	// 블로그 생성
	@Override
	public boolean createBlog(Blog blog) throws Exception {
		System.out.println("service(Blog) | createBlog");
		System.out.println(blog);
		
		// 사용자의 블로그가 존재하는가 확인
		Blog userck = new Blog();
		userck.setUserNo(blog.getUserNo());
		if(blogDAO.selectBlogCount(userck) != 0) {
			System.out.println("블로그가 존재하는 사용자입니다.");
			return false;
		}
		
		int cnt = blogDAO.insertBlog(blog);
		return cnt==1?true:false;
	}

	// 블로그 수정
	@Override
	public boolean updateBlog(Blog blog) throws Exception {
		System.out.println("service(Blog) | updateBlog");
		System.out.println(blog);
		
		// userNo로 blog있는지 부터 확인하고, 있다면 blogno이용
		Blog userck = new Blog();
		userck.setUserNo(blog.getUserNo());
		List<Blog> list = blogDAO.selectBlogList(userck);
		// user의 블로그가 하나인지 확인
		if(list.size() != 1) {
			System.out.println("user의 blog가 1개가 아닙니다!");
			return false;
		}
		// 수정하려는 블로그가 user의 블로그인지 확인(권한확인)
		if(list.get(0).getBlogNo() != blog.getBlogNo()) {
			System.out.println("user의 blog가 아닙니다!(권한없음)");
			return false;
		}
		
		int cnt = blogDAO.updateBlog(blog);
		return cnt==1?true:false;				
	}

	// 블로그 삭제
	@Override
	public boolean deleteBlog(Blog blog) throws Exception {
		System.out.println("service(Blog) | deleteBlog");
		System.out.println(blog);
		
		// userNo로 blog있는지 부터 확인하고, 있다면 blogno이용
		Blog userck = new Blog();
		userck.setUserNo(blog.getUserNo());
		List<Blog> list = blogDAO.selectBlogList(userck);
		// user의 블로그가 하나인지 확인
		if(list.size() != 1) {
			System.out.println("user의 blog가 1개가 아닙니다!");
			return false;
		}
		// 수정하려는 블로그가 user의 블로그인지 확인(권한확인)
		if(list.get(0).getBlogNo() != blog.getBlogNo()) {
			System.out.println("user의 blog가 아닙니다!(권한없음)");
			return false;
		}
		
		int cnt = blogDAO.deleteBlog(blog);
		return cnt==1?true:false;
	}

	// 블로그 상세조회
	@Override
	public Blog detailBlog(Blog blog) throws Exception {
		System.out.println("service(Blog) | detailBlog");
		System.out.println(blog);
		blog = blogDAO.selectBlogOne(blog);
		return blog;
	}

	// 블로그 검색
	@Override
	public List<Blog> searchBlog(Blog blog) throws Exception {
		System.out.println("service(Blog) | searchBlog");
		System.out.println(blog);
		// .trim()
		// 필수 파라미터 체크 과정 필요
		return blogDAO.selectBlogList(blog);
	}
	
	// 블로그 갯수 검색
	@Override
	public int countBlog(Blog blog) throws Exception {
		System.out.println("service(Blog) | countBlog");
		System.out.println(blog);
		return blogDAO.selectBlogCount(blog);
	}

	// 사용자 아이디로 Blog 정보 가져오기
	@Override
	public Blog selectUserBlog(String userId) throws Exception {
		return blogDAO.selectUserBlog(userId);
	}
	
	// Myboard 테이블 (게시판) ------------------------------------------
	
	// 게시판 생성
	@Override
	public boolean createMyboard(Myboard myboard, int userNo) throws Exception {
		System.out.println("service(Blog) | createMyboard");
		System.out.println(myboard);
		
		// 사용자 권한 확인
		Blog blog = new Blog();
		blog.setBlogNo(myboard.getBlogNo());
		// myboard의 blogno로 blog정보 가져오기
		List<Blog> tmplist = blogDAO.selectBlogList(blog);
		if(tmplist.size() != 1) {
			System.out.println("블로그가 존재하지 않거나 2개이상입니다.");
			return false;
		}
		// blog정보의 userNo가 파라미터 userNo와 같은지 체크
		if(userNo != tmplist.get(0).getUserNo()) {
			System.out.println("접근 권한이 없습니다.");
			return false;
		}
		
		// 로직 처리
		// boardType 처리 (ANL)
		if(!myboard.getBoardType().equals("A") && !myboard.getBoardType().equals("N")
				&& !myboard.getBoardType().equals("L")) {
			System.out.println("유효하지 않은 게시판 타입입니다.");
			return false;
		}
		// depth 최댓값 가져오기
		Myboard tmp = new Myboard();
		tmp.setBlogNo(myboard.getBlogNo());
		//  첫 게시판일 경우
		if(myboardDAO.selectMyboardCount(tmp) == 0) {
			myboard.setDepth(1);
		}
		//  첫 게시판이 아닐 경우
		else {
			myboard.setDepth(myboardDAO.selectDepthMax(myboard) + 1);			
		}
		// 입력 넣기
		int cnt = myboardDAO.insertMyboard(myboard);
		if(cnt != 1) {
			System.out.println("게시판 입력에 문제가 있어요");
			return false;
		}
		
		// board_no 가져와서 groupNo 반영
		List<Myboard> tmplist2 = myboardDAO.selectMyboardList(myboard);
		if(tmplist2.size() != 1) {
			System.out.println("존재하지 않은 게시판입니다.");
			return false;
		}
		myboard = tmplist2.get(0);
		if(myboard.getGroupNo() == 0) {
			myboard.setGroupNo(myboard.getBoardNo());
			cnt = myboardDAO.updateMyboard(myboard);			
		}
		return true;
	}

	// 게시판 수정
	@Override
	public boolean updateMyboard(Myboard myboard, int userNo) throws Exception {
		System.out.println("service(Blog) | updateMyboard");
		System.out.println(myboard);
		
		// 사용자 권한 확인
		Blog blog = new Blog();
		blog.setBlogNo(myboard.getBlogNo());
		
		// boardNo로 blogNo 가져오기
		
		// myboard의 blogno로 blog정보 가져오기
		List<Blog> tmplist = blogDAO.selectBlogList(blog);
		// blog정보의 userNo가 파라미터 userNo와 같은지 체크
		if(userNo != tmplist.get(0).getUserNo()) {
			System.out.println("유효하지 않은 접근입니다.");
			return false;
		}
		
		// 수정 알고리즘 구현부
		
		
		int cnt = myboardDAO.updateMyboard(myboard);
		return cnt==1?true:false;
	}

	// 게시판 삭제
	@Override
	public boolean deleteMyboard(Myboard myboard, int userNo) throws Exception{
		System.out.println("service(Blog) | deleteMyboard");
		System.out.println(myboard);
		
		// 사용자 권한 확인
		Blog blog = new Blog();
		blog.setBlogNo(myboard.getBlogNo());
		// myboard의 blogno로 blog정보 가져오기
		List<Blog> tmplist = blogDAO.selectBlogList(blog);
		// blog정보의 userNo가 파라미터 userNo와 같은지 체크
		if(userNo != tmplist.get(0).getUserNo()) {
			System.out.println(userNo + " | " + tmplist.get(0).getUserNo());
			System.out.println("유효하지 않은 접근입니다.");
			return false;
		}
		
		// 삭제원하는 게시판 groupno, seqno, depth 저장
		myboard = myboardDAO.selectMyboardOne(myboard);
		int groupNo = myboard.getGroupNo();
		int seqNo = myboard.getSeqNo();
		int depth = myboard.getDepth();

		// (하위게시판 삭제인 경우)
		if(myboard.getSeqNo() > 0) {
			System.out.println("하위게시판 삭제");
			// group_no가 같은 seq_no가 큰 것들 1씩 - 
			Myboard tmpboard = new Myboard();
			tmpboard.setGroupNo(groupNo);
			List<Myboard> tmplist2 = myboardDAO.selectMyboardList(tmpboard);
			if(tmplist2 != null) {
				for(int i=0; i<tmplist2.size(); i++) {
					tmpboard = tmplist2.get(i);
					if(tmpboard.getSeqNo() > seqNo) {
						tmpboard.setSeqNo(tmpboard.getSeqNo()-1);
					}
				}
			}
			// 게시판 삭제
			int cnt = myboardDAO.deleteMyboard(myboard);
			return cnt==1?true:false;
		}
		// (상위 게시판 삭제인 경우)
		else if(myboard.getSeqNo() == 0) {
			System.out.println("상위게시판");
			// 하위게시판이 있다면 false 리턴
			Myboard tmpboard = new Myboard();
			tmpboard.setGroupNo(groupNo);
			List<Myboard> tmplist2 = myboardDAO.selectMyboardList(tmpboard);
			if(tmplist2 != null) {
				System.out.println(tmplist2.size());
				if(tmplist2.size() != 1) {
					System.out.println("존재하지 않는 게시판이거나 하위게시판이 존재하는 게시판입니다.");
					return false;
				}
				// 하위게시판이 없다면 depth가 큰것들 -1
				tmpboard.setGroupNo(0);
				tmpboard.setBlogNo(myboard.getBlogNo());
				tmplist2 = myboardDAO.selectMyboardList(tmpboard);
				if(tmplist2 != null) {
					for(int i=0; i<tmplist2.size(); i++) {
						tmpboard = tmplist2.get(i);
						if(tmpboard.getDepth() > depth) {
							tmpboard.setDepth(tmpboard.getDepth()-1);
							myboardDAO.updateMyboard(tmpboard);
						}
					}
				}
				int cnt = myboardDAO.deleteMyboard(myboard);
				return true;
			}
		}
		else {
			System.out.println("유효하지 않은 데이터 삭제 요청");
		}
		return false;
	}

	// 게시판 상세조회
	@Override
	public Myboard detailMyboard(Myboard myboard) {
		System.out.println("service(Blog) | detailMyboard");
		System.out.println(myboard);
		myboard = myboardDAO.selectMyboardOne(myboard);
		return myboard;
	}

	// 게시판 검색
	@Override
	public List<Myboard> searchMyboard(Myboard myboard) {
		System.out.println("service(Blog) | searchMyboard");
		System.out.println(myboard);
		// .trim()
		// 필수 파라미터 체크 과정 필요
		return myboardDAO.selectMyboardList(myboard);
	}

	// 게시판 갯수 검색
	@Override
	public int countMyboard(Myboard myboard) {
		System.out.println("service(Blog) | countMyboard");
		System.out.println(myboard);
		return myboardDAO.selectMyboardCount(myboard);
	}

	
	// blogNo, groupNo로 seqNo 최대값 반환
	public int selectSeqNoMax(Myboard myboard) throws Exception {
		System.out.println("service(Blog) | selectSeqNoMax");
		System.out.println(myboard);
		return myboardDAO.selectSeqNoMax(myboard);
	}
	
	// blogNo로 depth 최대값 반환
	public int selectDepthMax(Myboard myboard) throws Exception {
		System.out.println("service(Blog) | selectDepthMax");
		System.out.println(myboard);
		return myboardDAO.selectDepthMax(myboard);
	}
	
	// blogNo로 groupNo 중복없이 주세요
	public List<Integer> selectGroupNoDist(int blogNo) throws Exception {
		System.out.println("service(Blog) | selectGroupNoDist");
		System.out.println(blogNo);
		return myboardDAO.selectGroupNoDist(blogNo);
	}
	
	// 게시판 getBoardList
	public List<Myboard> getBoardList(Myboard myboard) throws Exception {
		System.out.println("service(Blog) | getBoardList");
		System.out.println(myboard);
		List<Myboard> list = myboardDAO.getBoardList(myboard);
		if(list != null) {
			for(int i=0; i<list.size(); i++) {
				myboard = list.get(i);
				Article article = new Article();
				article.setGroupNo(myboard.getGroupNo());
				article.setSeqNo(myboard.getSeqNo());
				
				if(article.getSearchValue() == null) article.setSearchValue("");
				article.setSearchWords(Arrays.asList(article.getSearchValue().split(" ")));
				myboard.setArticleCount(articleDAO.selectArticleCount(article));
				System.out.println(myboard);
			}
		}
		return list;
	}
	
	// 상위게시판 순서 조정
	public boolean updateRootBoard (Myboard myboard, int userNo) throws Exception{
		System.out.println("service(Blog) | updateRootBoard");
		System.out.println(myboard);
		// myboard의 blogNo로 주인 userNo 가져오고
		// userNo와 비교하여 접근권한 확인
		int cnt = myboardDAO.updateRootBoard(myboard);
		return cnt==1?true:false;
	}

}
