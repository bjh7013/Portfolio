package com.ssazit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.ssazit.dao.AcategoryDAO;
import com.ssazit.dao.ArticleDAO;
import com.ssazit.dao.ArticlefavoriteDAO;
import com.ssazit.dao.ArticlelikeDAO;
import com.ssazit.dao.ArticleshareDAO;
import com.ssazit.dao.ArttagDAO;
import com.ssazit.dao.CategoryDAO;
import com.ssazit.dao.MyboardDAO;
import com.ssazit.dao.NotiDAO;
import com.ssazit.dao.TmptagDAO;
import com.ssazit.dao.UserDAO;
import com.ssazit.dto.Acategory;
import com.ssazit.dto.Article;
import com.ssazit.dto.ArticleTagCat;
import com.ssazit.dto.Articlefavorite;
import com.ssazit.dto.Articlelike;
import com.ssazit.dto.Articleshare;
import com.ssazit.dto.Arttag;
import com.ssazit.dto.Category;
import com.ssazit.dto.Follow;
import com.ssazit.dto.Myboard;
import com.ssazit.dto.Noti;
import com.ssazit.dto.Tmptag;
import com.ssazit.dto.User;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleDAO articleDAO;

	@Autowired
	ArttagDAO arttagDAO;

	@Autowired
	AcategoryDAO acategoryDAO;

	@Autowired
	TmptagDAO tmptagDAO;

	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	ArticlelikeDAO articlelikeDAO;

	@Autowired
	ArticleshareDAO articleshareDAO;

	@Autowired
	ArticlefavoriteDAO articlefavoriteDAO;

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	MyboardDAO myboardDAO;
	
	@Autowired
	NotiDAO notiDAO;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	// Article 테이블 (게시글) ------------------------------------------

	// 게시글 생성
	@Override
	public boolean createArticle(ArticleTagCat artTagCat) throws Exception {
		System.out.println("service(Article) | createArticle");
		System.out.println(artTagCat);

		Article article = artTagCat.getArticle();
		Myboard myboard = articleDAO.validateBoard(article);
		if (myboard == null) // 해당 사용자가 게시판에 게시글을 쓸 수 있는지 검사
			throw new Exception();

		String boardType = myboard.getBoardType();
		Acategory acategory = artTagCat.getAcategory();
		
		if (boardType.equals("A")) {
			article.setArticleType("A");
			if(article.getAcategoryNo() == 0 && (acategory == null || acategory.getAcategoryNumber() == 0
					|| acategory.getAcategorySource() == null || acategory.getTitle() == null 
					|| acategory.getLink() == null)) { //알고 게시글은 기정의된 카테고리와 매핑되어있지 않으면서, 사용자 카테고리 정의도 하지 않은 경우 예외 발생
				throw new Exception();
			}
				
		} else if (boardType.equals("N")) {
			article.setArticleType("N");
			if(article.getCategoryNo() == 0) { //일반 게시글은 카테고리 번호 지정안되어있으면 예외 발생
				throw new Exception();
			}
		} else {
			throw new Exception();// 잘못된 보드타입
		}

		/**
		 * 일반 게시글 - 기정의된 카테고리만 이용가능
		 * 알고 게시글 - 기정의된 카테고리 + 사용자 생성 카테고리 이용가능 -> 사용자 생성 카테고리 이용 시 article생성 전 카테고리 생성이 선행되어야함
		 */
		
		//알고리즘 게시글이면서, 사용자 정의 카테고리를 정의해 사용한 경우
		if(article.getArticleType().equals("A") && article.getAcategoryNo() == 0) { 
			acategory.setUserNo(article.getUserNo());
			acategoryDAO.insertAcategory(acategory); //acategory테이블에 레코드 추가
			
			article.setAcategoryNo((acategoryDAO.getAcategoryNo(acategory)));
		}
		
		if (articleDAO.insertArticle(article) != 1) // 게시글 작성
			return false;

		if(article.getReference() != 0) {
			Article origin = new Article();
			User user = new User();
			
			origin.setArticleNo(article.getReference());			
			origin = articleDAO.selectArticleOne(origin); //퍼간 글의 원본 정보 획득
			
			user.setUserNo(article.getUserNo());			
			user = userDAO.selectUserOne(user); //글을 퍼간 사용자 정보 획득
			
			Noti noti = new Noti();
			
			noti.setSender(user.getUserNo());
			noti.setReceiver(origin.getUserNo());
			noti.setNotiType("share");
			noti.setArticleNo(origin.getArticleNo());
			
			notiDAO.insertNoti(noti);
			
			noti.setNickName(user.getNickName());
			noti.setNotiMessage(origin.getTitle() + "글을 " + user.getNickName() + "님이 퍼갔습니다.");
			
			simpMessagingTemplate.convertAndSend("/topic/pushnoti/" + origin.getUserNo(), noti);
		}
			
		int articleNo = articleDAO.getArticleNo(article.getUserNo());// 게시글 번호 획득

		System.out.println(articleNo);
		
		
		System.out.println(artTagCat.getArttag());		

		 
		for (Arttag arttag : artTagCat.getArttag()) { // 게시글에 태그 등록
			arttag.setArticleNo(articleNo);
			arttagDAO.insertArttag(arttag);
		}

		for (Tmptag tmptag : artTagCat.getTmpTag()) { // 게시글에 임시 태그 등록
			System.out.println(tmptag);
			tmptag.setArticleNo(articleNo);
			tmptagDAO.insertTmptag(tmptag);
		}

//		if(article.getArticleType().equals("N")) {
//			artTagCat.getArtCategory().setArticleNo(articleNo);
//			artcategoryDAO.insertArtcategory(artTagCat.getArtCategory());			
//		} else if(article.getArticleType().equals("A")) {
//			if(artTagCat.getArtAlgo().getAcategoryNo() == 0) { //사용자가 목록에 없는 기타 문제를 입력한 경우
//				Acategory acategory = artTagCat.getAcategory();
//				acategory.setUserNo(article.getUserNo());
//				acategoryDAO.insertAcategory(acategory); //acategory테이블에 레코드 추가
//				
//				artTagCat.getArtAlgo().setAcategoryNo((acategoryDAO.getAcategoryNo(acategory)));
//			}
//			
//			artTagCat.getArtAlgo().setArticleNo(articleNo);
//			artalgoDAO.insertArtalgo(artTagCat.getArtAlgo());			
//		}


		return true;
		/*
		 */
	}

	// 게시글 수정
	@Override
	public boolean updateArticle(ArticleTagCat artTagCat) throws Exception {
		System.out.println("service(Article) | updateArticle");
		System.out.println(artTagCat);

		Article article = artTagCat.getArticle();

		// 해당 사용자가 게시판에 게시글을 쓸 수 있는지 검사
		Myboard myboard = articleDAO.validateBoard(article);
		if (myboard == null)
			throw new Exception();

		// 해당 게시글을 사용자가 수정할 수 있는지 검사
		if(articleDAO.validateArticle(article) == null)
			throw new Exception();
						
		
		String boardType = myboard.getBoardType();
		Acategory acategory = artTagCat.getAcategory();
		
		if (boardType.equals("A")) {
			article.setArticleType("A");
			System.out.println(article);
			if(article.getAcategoryNo() == 0 && (acategory == null || acategory.getAcategoryNumber() == 0
					|| acategory.getAcategorySource() == null || acategory.getTitle() == null 
					|| acategory.getLink() == null)) { //알고 게시글은 기정의된 카테고리와 매핑되어있지 않으면서, 사용자 카테고리 정의도 하지 않은 경우 예외 발생
				throw new Exception();
			}
		} else if (boardType.equals("N")) {
			article.setArticleType("N");
			if(article.getCategoryNo() == 0) { //일반 게시글은 카테고리 번호 지정안되어있으면 예외 발생
				throw new Exception();
			}
		} else {
			throw new Exception();// 잘못된 보드타입
		}
		
		
		
		/**
		 * 일반 게시글 - 기정의된 카테고리만 이용가능
		 * 알고 게시글 - 기정의된 카테고리 + 사용자 생성 카테고리 이용가능 -> 사용자 생성 카테고리 이용 시 article생성 전 카테고리 생성이 선행되어야함
		 */
		
		
		
		if(article.getArticleType().equals("A") && article.getAcategoryNo() == 0) { 
			acategory.setUserNo(article.getUserNo());
			acategoryDAO.insertAcategory(acategory); //acategory테이블에 레코드 추가
			
			article.setAcategoryNo((acategoryDAO.getAcategoryNo(acategory)));
		}
	
		if (articleDAO.updateArticle(article) != 1) // 게시글 수정
			return false;

		
				
		
		List<Arttag> oldArtTags = new ArrayList<>(); //이전 태그 목록
		Set<Integer> toBeDeletedSet = new HashSet<>(); //이전 태그 중 삭제되어야할 태그 목록
		Arttag deleteTag = new Arttag(); //삭제할 태그
		
		//원래 글에 연결돼있던 태그들 불러오기
		Arttag forSearch = new Arttag();
		forSearch.setArticleNo(article.getArticleNo());
		oldArtTags = arttagDAO.selectArttagList(forSearch);
		
		//삭제할 태그들의 목록 초기화
		for(Arttag oldArtTag : oldArtTags) {
			toBeDeletedSet.add(oldArtTag.getTagNo());
		}
		
		for (Arttag newArttag : artTagCat.getArttag()) { // 게시글에 태그 등록(등록안된 태그만 추가해주도록 수정해야함)
			//새로운 태그와 비교
			if(toBeDeletedSet.remove(newArttag.getTagNo())) {
				//새로운 태그가 원래 태그 목록에 포함되어 있었다면 다음 태그 검사
				continue;
			}else {
				//새로운 태그가 원래 태그 목록에 없었다면, 해당 태그 insert
				newArttag.setArticleNo(article.getArticleNo());
				System.out.println(newArttag);
				arttagDAO.insertArttag(newArttag);
			}			
		}
		System.out.println("새 태그 추가 완료===========");
		
		//toBeDeleteSet에 남은 원소들 -> 새로운 태그엔 없지만 기존 태그에 있던 항목들 -> 삭제되어야함
		for(int tagNo : toBeDeletedSet) {
			System.out.println(tagNo);
			deleteTag.setTagNo(tagNo);
			deleteTag.setArticleNo(article.getArticleNo());
			arttagDAO.deleteArttag(deleteTag);
		}
		System.out.println("기존 태그 삭제 완료===========");
		
		
		List<Tmptag> oldTmpTags = new ArrayList<>();//기존 tmp태그 리스트
		toBeDeletedSet = new HashSet<>();//삭제 되어야할 tmp태그 리스트
		Tmptag deleteTmpTag = new Tmptag(); //삭제할 태그
	
		
		//원래 글에 연결돼있던 임시태그들 불러오기
		Tmptag forSearchTmpTag = new Tmptag();
		forSearchTmpTag.setArticleNo(article.getArticleNo());
		oldTmpTags = tmptagDAO.selectTmptagList(forSearchTmpTag);
		
		//삭제할 태그들의 목록 초기화
		for(Tmptag oldTmptag : oldTmpTags) {
			toBeDeletedSet.add(oldTmptag.getTmptagNo());
		}
		
		for (Tmptag newTmpTag : artTagCat.getTmpTag()) { // 게시글에 태그 등록(등록안된 태그만 추가해주도록 수정해야함)
			//새로운 태그와 비교
			if(toBeDeletedSet.remove(newTmpTag.getTmptagNo())) {
				//새로운 태그가 원래 태그 목록에 포함되어 있었다면 다음 태그 검사
				continue;
			}else {
				//새로운 태그가 원래 태그 목록에 없었다면, 해당 태그 insert
				newTmpTag.setArticleNo(article.getArticleNo());
				tmptagDAO.insertTmptag(newTmpTag);
			}			
		}
		System.out.println("새 임시 태그 추가 완료===========");
		
		//toBeDeleteSet에 남은 원소들 -> 새로운 태그엔 없지만 기존 태그에 있던 항목들 -> 삭제되어야함
		for(int tagNo : toBeDeletedSet) {
			System.out.println(tagNo);
			deleteTmpTag.setTmptagNo(tagNo);
			deleteTmpTag.setArticleNo(article.getArticleNo());
			tmptagDAO.deleteTmptag(deleteTmpTag);
		}
		System.out.println("기존 임시 태그 삭제 완료===========");
		
		 
//		if(article.getArticleType().equals("N")) {
//			artTagCat.getArtCategory().setArticleNo(article.getArticleNo());
//			System.out.println(artTagCat.getArtCategory());
//			if(artcategoryDAO.updateArtcategory(artTagCat.getArtCategory()) != 1){
//				artcategoryDAO.insertArtcategory(artTagCat.getArtCategory());
//			}
//		} else if(article.getArticleType().equals("A")) {
//			artTagCat.getArtAlgo().setArticleNo(article.getArticleNo());
//			if(artTagCat.getArtAlgo().getAcategoryNo() == 0) { //사용자가 목록에 없는 기타 문제를 입력한 경우
//				Acategory acategory = artTagCat.getAcategory();
//				acategory.setUserNo(article.getUserNo());
//				acategoryDAO.insertAcategory(acategory); //acategory테이블에 레코드 추가
//				
//				artTagCat.getArtAlgo().setAcategoryNo((acategoryDAO.getAcategoryNo(acategory)));
//				artalgoDAO.insertArtalgo(artTagCat.getArtAlgo());			
//			}
//			System.out.println(artTagCat.getArtAlgo());
//			artalgoDAO.updateArtalgo(artTagCat.getArtAlgo());			
//			
//		}
		return true;
	}

	// 게시글 삭제
	@Override
	public boolean deleteArticle(Article article) {
		System.out.println("service(Article) | deleteArticle");
		System.out.println(article);
		int cnt = articleDAO.deleteArticle(article);
		return cnt == 1 ? true : false;
	}

	// 게시글 상세조회
	@Override
	public ArticleTagCat detailArticle(Article article) {
		System.out.println("service(Article) | detailArticle");
		System.out.println(article);
		
		ArticleTagCat artTagCat = new ArticleTagCat();
		artTagCat.setArticle(articleDAO.selectArticleOne(article));
		
		
		Arttag arttag = new Arttag();
		Tmptag tmptag = new Tmptag();


		arttag.setArticleNo(artTagCat.getArticle().getArticleNo());
		artTagCat.setArttag(arttagDAO.selectArttagList(arttag));// 태그 리스트 셋
		System.out.println("======태그 리스트 세팅====");

		tmptag.setArticleNo(artTagCat.getArticle().getArticleNo());
		artTagCat.setTmpTag(tmptagDAO.selectTmptagList(tmptag));// 임시태그 리스트 셋
		System.out.println("======임시태그 세팅====");
			
			
		
		System.out.println(artTagCat);
		
		return artTagCat;
	}

	// 게시글 검색
	@Override
	public List<ArticleTagCat> searchArticle(Article searchCondition) {
		System.out.println("service(Article) | searchArticle");
		
		searchCondition.setOpenScope("public"); //default openscope
		
		if(searchCondition.getUserId() == null) {
			if(searchCondition.getGroupNo() != 0) {//특정 게시판 조회일 경우
				searchCondition.setUserId(myboardDAO.getUserId(searchCondition));							
			}
		}//openScope 설정 (로그인한 아이디와 접속한 블로그 주인의 아이디 비교)
		
		User user = new User();
		
		//특정 게시판 조회 or 특정 블로그 조회일 경우
		user.setUserId(searchCondition.getUserId());
		if(user.getUserId() != null) { 
			searchCondition.setUserNo(userDAO.selectUserOne(user).getUserNo());			
		}
		
		if(searchCondition.getLoginUserNo() != 0 && searchCondition.getLoginUserNo() == searchCondition.getUserNo()) {
			// 로그인 유저 == 블로그 주인이면 public, protected, private 게시글 접근 가능
			searchCondition.setOpenScope("private");
		} else if(userDAO.isF4F(searchCondition) != null) { //맞팔이면 public, protected게시글 접근 가능
				searchCondition.setOpenScope("protected");
		}
		// 공개 범위에 따른 조회 가능 여부 설정
		
		//검색어들을 어절 단위로 분할해서 searchWords리스트에 추가
		searchCondition.setSearchWords(Arrays.asList(searchCondition.getSearchValue().split(" ")));
		System.out.println(searchCondition);
		
		//검색 결과 list준비
		List<ArticleTagCat> result = new ArrayList<ArticleTagCat>();
		List<Article> articleList;

		articleList = articleDAO.selectArticleList(searchCondition);
		System.out.println(articleList.size());
		
		Arttag arttag = new Arttag();
		Tmptag tmptag = new Tmptag();

		int articleNo;

		//조회결과에 태그 추가
		for (Article art : articleList) {
			ArticleTagCat articleTagCat = new ArticleTagCat();// 게시글-태그리스트-카테고리 리스트 wrapper class

			articleTagCat.setArticle(art); // 게시글 셋
			System.out.println("======게시글 세팅====");
			System.out.println(art);

			articleNo = art.getArticleNo();

			arttag.setArticleNo(articleNo);
			articleTagCat.setArttag(arttagDAO.selectArttagList(arttag));// 태그 리스트 셋
			System.out.println("======태그 리스트 세팅====");

			tmptag.setArticleNo(articleNo);
			articleTagCat.setTmpTag(tmptagDAO.selectTmptagList(tmptag));// 임시태그 리스트 셋
			System.out.println("======임시태그 세팅====");
					
			System.out.println(articleTagCat);

			result.add(articleTagCat);
		}

		return result;
	}

	// 게시글 갯수 검색
	@Override
	public int countArticle(Article article) {
		System.out.println("service(Article) | countArticle");
		System.out.println(article);
		
		if(article.getSearchValue() == null)
			article.setSearchValue("");
		
		article.setSearchWords(Arrays.asList(article.getSearchValue().split(" ")));
		return articleDAO.selectArticleCount(article);
	}
	// Acategory 테이블 (알고리즘문제 카테고리) --------------------------------

	// 알고리즘문제 카테고리 생성
	@Override
	public boolean createAcategory(Acategory acategory) {
		System.out.println("service(Article) | createAcategory");
		System.out.println(acategory);
		int cnt = acategoryDAO.insertAcategory(acategory);
		return cnt == 1 ? true : false;
	}

	// 알고리즘문제 카테고리 수정
	@Override
	public boolean updateAcategory(Acategory acategory) {
		System.out.println("service(Article) | updateAcategory");
		System.out.println(acategory);
		int cnt = acategoryDAO.updateAcategory(acategory);
		return cnt == 1 ? true : false;
	}

	// 알고리즘문제 카테고리 삭제
	@Override
	public boolean deleteAcategory(Acategory acategory) {
		System.out.println("service(Article) | deleteAcategory");
		System.out.println(acategory);
		int cnt = acategoryDAO.deleteAcategory(acategory);
		return cnt == 1 ? true : false;
	}

	// 알고리즘문제 카테고리 상세조회
	@Override
	public Acategory detailAcategory(Acategory acategory) {
		System.out.println("service(Article) | detailAcategory");
		System.out.println(acategory);
		return acategoryDAO.selectAcategoryOne(acategory);
	}

	// 알고리즘문제 카테고리 검색
	@Override
	public List<Acategory> searchAcategory(Acategory acategory) {
		System.out.println("service(Article) | searchAcategory");
		System.out.println(acategory);
		return acategoryDAO.selectAcategoryList(acategory);
	}

	// 알고리즘문제 카테고리 갯수 검색
	@Override
	public int countAcategory(Acategory acategory) {
		System.out.println("service(Article) | countAcategory");
		System.out.println(acategory);
		return acategoryDAO.selectAcategoryCount(acategory);
	}

	// category 테이블 (글 카테고리) ------------------------------

	// 글 카테고리 생성
	@Override
	public boolean createCategory(Category category) {
		System.out.println("service(Article) | createCategory");
		System.out.println(category);
		int cnt = categoryDAO.insertCategory(category);
		return cnt == 1 ? true : false;
	}

	// 글 카테고리 수정
	@Override
	public boolean updateCategory(Category category) {
		System.out.println("service(Article) | updateCategory");
		System.out.println(category);
		int cnt = categoryDAO.updateCategory(category);
		return cnt == 1 ? true : false;
	}

	// 글 카테고리 삭제
	@Override
	public boolean deleteCategory(Category category) {
		System.out.println("service(Article) | deleteCategory");
		System.out.println(category);
		int cnt = categoryDAO.deleteCategory(category);
		return cnt == 1 ? true : false;
	}

	// 글 카테고리 상세조회
	@Override
	public Category detailCategory(Category category) {
		System.out.println("service(Article) | detailCategory");
		System.out.println(category);
		return categoryDAO.selectCategoryOne(category);
	}

	// 글 카테고리 검색
	@Override
	public List<Category> searchCategory(Category category) {
		System.out.println("service(Article) | searchCategory");
		System.out.println(category);
		return categoryDAO.selectCategoryList(category);
	}

	// 글 카테고리 갯수 검색
	@Override
	public int countCategory(Category category) {
		System.out.println("service(Article) | countCategory");
		System.out.println(category);
		return categoryDAO.selectCategoryCount(category);
	}

	// Articlelike 테이블 (글 좋아요) ------------------------------


	// 글 좋아요 상세조회
	@Override
	public Articlelike detailArticlelike(Articlelike articlelike) {
		System.out.println("service(Article) | detailArticlelike");
		System.out.println(articlelike);
		return articlelikeDAO.selectArticlelikeOne(articlelike);
	}

	// 글 좋아요 검색
	@Override
	public List<Articlelike> searchArticlelike(Articlelike articlelike) {
		System.out.println("service(Article) | searchArticlelike");
		System.out.println(articlelike);
		return articlelikeDAO.selectArticlelikeList(articlelike);
	}

	// 글 좋아요 갯수 검색
	@Override
	public int countArticlelike(Articlelike articlelike) {
		System.out.println("service(Article) | countArticlelike");
		System.out.println(articlelike);
		return articlelikeDAO.selectArticlelikeCount(articlelike);
	}

	// Articleshare 테이블 (글 공유) ------------------------------------

	// 글 공유 생성
	@Override
	public boolean createArticleshare(Articleshare articleshare) {
		System.out.println("service(Article) | createArticleshare");
		System.out.println(articleshare);
		int cnt = articleshareDAO.insertArticleshare(articleshare);
		return cnt == 1 ? true : false;
	}

	// 글 공유 수정
	@Override
	public boolean updateArticleshare(Articleshare articleshare) {
		System.out.println("service(Article) | updateArticleshare");
		System.out.println(articleshare);
		int cnt = articleshareDAO.updateArticleshare(articleshare);
		return cnt == 1 ? true : false;
	}

	// 글 공유 삭제
	@Override
	public boolean deleteArticleshare(Articleshare articleshare) {
		System.out.println("service(Article) | updateArticleshare");
		System.out.println(articleshare);
		int cnt = articleshareDAO.deleteArticleshare(articleshare);
		return cnt == 1 ? true : false;
	}

	// 글 공유 상세조회
	@Override
	public Articleshare detailArticleshare(Articleshare articleshare) {
		System.out.println("service(Article) | detailArticleshare");
		System.out.println(articleshare);
		return articleshareDAO.selectArticleshareOne(articleshare);
	}

	// 글 공유 검색
	@Override
	public List<Articleshare> searchArticleshare(Articleshare articleshare) {
		System.out.println("service(Article) | searchArticleshare");
		System.out.println(articleshare);
		return articleshareDAO.selectArticleshareList(articleshare);
	}

	// 글 공유 갯수 검색
	@Override
	public int countArticleshare(Articleshare articleshare) {
		System.out.println("service(Article) | countArticleshare");
		System.out.println(articleshare);
		return articleshareDAO.selectArticleshareCount(articleshare);
	}

	// Articlefavorite 테이블 (글 즐겨찾기) ------------------------------

	// 글 즐겨찾기 상세조회
	@Override
	public Articlefavorite detailArticlefavorite(Articlefavorite articlefavorite) {
		System.out.println("service(Article) | detailArticlefavorite");
		System.out.println(articlefavorite);
		return articlefavoriteDAO.selectArticlefavoriteOne(articlefavorite);
	}

	// 글 즐겨찾기 검색
	@Override
	public List<Articlefavorite> searchArticlefavorite(Articlefavorite articlefavorite) {
		System.out.println("service(Article) | detailArticlefavorite");
		System.out.println(articlefavorite);
		return articlefavoriteDAO.selectArticlefavoriteList(articlefavorite);
	}

	// 글 즐겨찾기 갯수 검색
	@Override
	public int countArticlefavorite(Articlefavorite articlefavorite) {
		System.out.println("service(Article) | detailArticlefavorite");
		System.out.println(articlefavorite);
		return articlefavoriteDAO.selectArticlefavoriteCount(articlefavorite);
	}

	@Override
	public List<String> selectSiteList() {
		// TODO Auto-generated method stub
		return acategoryDAO.selectSiteList();
	}

	@Override
	public boolean hitArticlelike(Articlelike articlelike) {
		// TODO Auto-generated method stub
		System.out.println("service(Article) | hitArticlelike");
		System.out.println(articlelike);
		
		if(articlelikeDAO.selectArticlelikeOne(articlelike) == null) {
			
			if(articlelikeDAO.insertArticlelike(articlelike) == 1) {
				Article article = new Article();
				User user = new User();
				Follow follow = new Follow();
				
				article.setArticleNo(articlelike.getArticleNo());			
				article = articleDAO.selectArticleOne(article);
				
				follow.setFollower(article.getUserNo());
				follow.setFollowing(articlelike.getUserNo());
				
				user = userDAO.isFollowing(follow);
				System.out.println("1111111111111111111111111111111fffffffffffffffffffffffffffffffffffffffffffffffffffffffff===========================dasd0asdasd");
				if(user != null) {
					
					Noti noti = new Noti();
					
					noti.setSender(follow.getFollowing());
					noti.setReceiver(follow.getFollower());
					noti.setNotiType("like");
					noti.setArticleNo(article.getArticleNo());
					
					notiDAO.insertNoti(noti);
					
					noti.setNickName(user.getNickName());
					noti.setNotiMessage(article.getTitle() + "글에 " + user.getNickName() + "님이 좋아요를 눌렀습니다.");
					
					
					simpMessagingTemplate.convertAndSend("/topic/pushnoti/" + article.getUserNo(), noti);
				}

				return true;
			}
			return false;
		}else {
			return articlelikeDAO.deleteArticlelike(articlelike) == 1? true : false;			
		}	
	}

	@Override
	public String hitArticlefavorite(Articlefavorite articlefavorite) {
		// TODO Auto-generated method stub
		System.out.println("service(Article) | hitArticlefavorite");
		System.out.println(articlefavorite);
		
		if(articlefavoriteDAO.selectArticlefavoriteOne(articlefavorite) == null) {
			
			if(articlefavoriteDAO.insertArticlefavorite(articlefavorite) == 1) {
				Article article = new Article();
				User user = new User();
				Follow follow = new Follow();
				
				article.setArticleNo(articlefavorite.getArticleNo());			
				article = articleDAO.selectArticleOne(article);
				
				follow.setFollower(article.getUserNo());
				follow.setFollowing(articlefavorite.getUserNo());
				
				user = userDAO.isFollowing(follow);
				System.out.println(follow);
				if(user != null) {
					Noti noti = new Noti();
					
					noti.setSender(follow.getFollowing());
					noti.setReceiver(follow.getFollower());
					noti.setNotiType("favorite");
					noti.setArticleNo(article.getArticleNo());
					
					notiDAO.insertNoti(noti);
					
					noti.setNickName(user.getNickName());
					noti.setNotiMessage(article.getTitle() + "글을 " + user.getNickName() + "님이 관심글로 들록했습니다.");
										
					simpMessagingTemplate.convertAndSend("/topic/pushnoti/" + article.getUserNo(), noti);					
				}	
				return  "insert";
			}
			
			return "fail";
		}else {
			return articlefavoriteDAO.deleteArticlefavorite(articlefavorite) == 1? "delete" : "fail";			
		}	
		
	}

	@Override
	public Article getReferenceArticle(Article article) {
		// TODO Auto-generated method stub
		System.out.println("service(Article) | getReferenceArticle");
		System.out.println(article);
		
		return articleDAO.getReferenceArticle(article);
	}

}
