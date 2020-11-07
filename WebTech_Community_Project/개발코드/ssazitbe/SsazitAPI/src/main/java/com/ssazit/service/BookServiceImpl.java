package com.ssazit.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssazit.dao.BcontentDAO;
import com.ssazit.dao.BookDAO;
import com.ssazit.dao.BookcategoryDAO;
import com.ssazit.dao.BookfavoriteDAO;
import com.ssazit.dao.BooklikeDAO;
import com.ssazit.dao.BookseriesDAO;
import com.ssazit.dao.BooktagDAO;
import com.ssazit.dao.CommentDAO;
import com.ssazit.dao.SeriesDAO;
import com.ssazit.dao.TmptagDAO;
import com.ssazit.dao.UserDAO;
import com.ssazit.dto.Bcontent;
import com.ssazit.dto.Block;
import com.ssazit.dto.Book;
import com.ssazit.dto.BookTagCat;
import com.ssazit.dto.Bookcategory;
import com.ssazit.dto.Bookfavorite;
import com.ssazit.dto.Booklike;
import com.ssazit.dto.Bookseries;
import com.ssazit.dto.Booktag;
import com.ssazit.dto.Comment;
import com.ssazit.dto.FileDTO;
import com.ssazit.dto.Follow;
import com.ssazit.dto.Series;
import com.ssazit.dto.Tmptag;
import com.ssazit.dto.User;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDAO bookDAO;

	@Autowired
	BcontentDAO bcontentDAO;
	
	@Autowired
	SeriesDAO seriesDAO;
	
	@Autowired
	BookseriesDAO bookseriesDAO;
	
	@Autowired
	BooklikeDAO booklikeDAO;
	
	@Autowired
	BookfavoriteDAO bookfavoriteDAO;
	
	@Autowired
	BookcategoryDAO bookcategoryDAO;
	
	@Autowired
	BooktagDAO booktagDAO;
	
	@Autowired
	TmptagDAO tmptagDAO;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	CommentDAO commentDAO;
	
	@Autowired
	UserDAO userDAO;
	
	// Book 테이블 (책) ------------------------------------------
	
	// 책 생성
	@Override
	public boolean createBook(BookTagCat booktagcat, int userNo) throws Exception {
		System.out.println("service(Book) | createBook");
		booktagcat.getBook().setWriter(userNo);
		System.out.println(booktagcat.getBook());
		System.out.println(booktagcat.getBooktag());
		System.out.println(booktagcat.getTmptag());
		System.out.println(booktagcat.getFileinfo());
		System.out.println(userNo);
		
		// 1)책 작성 -> 2)만든 책 번호받아오기 -> 3-1)temp폴더에서 책 폴더로 cover이미지 이동 -> 3-2)temp폴더 원본이미지 제거 -> 3-3)커버이미지 DB에 수정 -> 4)Booktag 연결 -> 5)Tmptag 연결
		
		// 1)책 작성
		if(booktagcat != null && booktagcat.getBook() != null) {
			if(bookDAO.insertBook(booktagcat.getBook()) != 1) {
				System.out.println("1) 책 작성 실패!");
				return false;
			}
		} else {
			System.out.println("작성하려는 책의 정보가 존재하지 않습니다!");
			return false;
		}
		System.out.println("1) 책 작성 성공!");
		
		// 2)책 번호 받아오기
		Book book = booktagcat.getBook();
		book.setOrderValue("DESC");
		book.setOrderKey("writeDate");
		List<Book> list = bookDAO.selectBookList(book);
		if(list == null || list.size() < 1) {
			System.out.println("2) 작성한 책 불러오기 실패!");
			return false;
		}
		System.out.println("2) 작성한 책 불러오기 성공!");
		
		// 3)책 커버 이미지처리
		book = list.get(0);
		int bookNo = book.getBookNo();
		if(booktagcat == null || booktagcat.getFileinfo() == null || booktagcat.getFileinfo().size() < 1) {
			System.out.println("3) 이미지를 입력하지 않은 책입니다!");
		} else {	
			System.out.println("3) 이미지를 입력한 책입니다!");
			
			// 3-1)책 이미지 복사
			List<FileDTO> fileInfo = fileService.copyBookCover(booktagcat.getFileinfo(), bookNo);
			if(fileInfo == null || fileInfo.size() < 1) {
				System.out.println("3-1) 파일 복사 실패!");
				return false;
			}			
			System.out.println("3-1) 파일 복사 성공!");
			
			// 3-2)원본 이미지 삭제
			if(!fileService.deleteTemp(booktagcat.getFileinfo())) {
				System.out.println("3-2) temp 이미지 삭제 실패!");
				return false;
			}
			System.out.println("3-2) temp 이미지 삭제 성공!");
			
			// 3-3)이미지 DB에 반영
			String dbpath = "resource/book_img/" + Integer.toString(bookNo) + "/";
			for(FileDTO fileinfo : fileInfo) {
				if(fileinfo.getFileName().contains("front")) {
					book.setCoverFront(dbpath + fileinfo.getFileName() + "." + fileinfo.getExtension());
					System.out.println("3-3) front파일 db 매핑");
				} else if(fileinfo.getFileName().contains("middle")) {
					book.setCoverMiddle(dbpath + fileinfo.getFileName() + "." + fileinfo.getExtension());
					System.out.println("3-3) middle파일 db 매핑");
				} else if(fileinfo.getFileName().contains("back")) {
					book.setCoverBack(dbpath + fileinfo.getFileName() + "." + fileinfo.getExtension());
					System.out.println("3-3) back파일 db 매핑");
				} else {
					System.out.println("3-3)파일 유형을 알 수 없습니다.");
					return false;
				}
			}
			bookDAO.updateBook(book);
		}
		
		// 4)booktag 작성
		if(booktagcat.getBooktag() != null) {
			for(Booktag booktag : booktagcat.getBooktag()) {
				booktag.setBookNo(bookNo);
				if(booktagDAO.insertBooktag(booktag) == 1) {
					System.out.println("4) 책태그 작성 성공!");
					continue;
				}
				System.out.println("4) 책태그 작성 실패!");
				return false;
			} 
		} else {
			System.out.println("4) 책 태그가 존재하지 않습니다!");
		}
		
		// 5)tmptag 작성
		if(booktagcat.getTmptag() != null) {
			for(Tmptag tmptag : booktagcat.getTmptag()) {
				tmptag.setBookNo(bookNo);
				if(tmptagDAO.insertTmptag(tmptag) == 1) {
					System.out.println("5) 임시태그 작성 성공!");
					continue;
				}
				System.out.println("5) 임시태그 작성 실패!");
				return false;					
			}
		} else {
			System.out.println("5) 임시태그가 존재하지 않습니다!");
		}
		return true;

		
//		// bcontent 작성
//		if(booktagcat.getBcontent() != null) {
//			for(int i=0; i<booktagcat.getBcontent().size(); i++) {
//				booktagcat.getBcontent().get(i).setBookNo(bookNo);
//				cnt = bcontentDAO.insertBcontent(booktagcat.getBcontent().get(i));
//				if(cnt != 1) {
//					System.out.println("6) 책 컨텐츠 작성 실패!");
//					return false;
//				} else {
//					System.out.println("6) 책 컨텐츠 작성 성공!");			
//				}
//			}			
//		} else {
//			System.out.println("6) 책 컨텐츠가 존재하지 않습니다!");
//			return false;
//		}
		
	}

	// 책 수정
	@Override
	public boolean updateBook(BookTagCat booktagcat, int userNo) throws Exception {
		System.out.println("service(Book) | updateBook");
		System.out.println(booktagcat);
		System.out.println(booktagcat.getBook());
		System.out.println(booktagcat.getBooktag());
		System.out.println(booktagcat.getTmptag());
		System.out.println(booktagcat.getFileinfo());
		
		// 0) 데이터 검증 -> 1) 사용자 검증 -> 2-1) 업로드한 파일 책 폴더안으로 복사 -> 2-2) temp이미지 삭제 -> 2-3) 수정 이미지 매핑 -> 3) 책 수정 -> 4) Booktag 수정 -> 5) Tmptag 수정
		
		// 0) 데이터 검증
		if(booktagcat == null || booktagcat.getBook() == null || userNo < 1) {
			System.out.println("2) 유효하지 않은 접근입니다.");
			return false;
		}
		
		// 1) 사용자 검증
		if(userNo != booktagcat.getBook().getWriter()) {
			System.out.println("1) 유효하지 않은 사용자 입니다.");
			return false;
		}
		
		// 2) 파일 처리
		Book book = booktagcat.getBook();
		if(booktagcat.getFileinfo() == null || booktagcat.getFileinfo().size() < 1) {
			System.out.println("2) 파일을 업로드하지 않았습니다!");
		} else {
			System.out.println("2) 파일을 업로드 하였습니다!");
			
			// 2-1) 업로드한 파일 책 폴더안으로 복사
			List<FileDTO> fileInfo = fileService.copyBookCover(booktagcat.getFileinfo(), book.getBookNo());
			if(fileInfo == null || fileInfo.size() < 1) {
				System.out.println("2-1) 파일 복사 실패!");
				return false;
			}
			System.out.println("2-1) 파일 복사 성공!");
			
			// 2-2) temp 이미지 삭제
			if(!fileService.deleteTemp(booktagcat.getFileinfo())) {
				System.out.println("2-2) temp 이미지 삭제 실패!");
				return false;
			}
			System.out.println("2-2) temp 이미지 삭제 성공!");
			
			// 2-3) 수정 이미지 매핑
			String dbpath = "resource/book_img/" + Integer.toString(book.getBookNo()) + "/";
			for(FileDTO fileinfo : fileInfo) {
				if(fileinfo.getFileName().contains("front")) {
					book.setCoverFront(dbpath + fileinfo.getFileName() + "." + fileinfo.getExtension());
					System.out.println("2-3) front파일 db 매핑");
				} else if(fileinfo.getFileName().contains("middle")) {
					book.setCoverMiddle(dbpath + fileinfo.getFileName() + "." + fileinfo.getExtension());
					System.out.println("2-3) middle파일 db 매핑");
				} else if(fileinfo.getFileName().contains("back")) {
					book.setCoverBack(dbpath + fileinfo.getFileName() + "." + fileinfo.getExtension());
					System.out.println("2-3) back파일 db 매핑");
				} else {
					System.out.println("2-3)파일 유형을 알 수 없습니다.");
					return false;
				}
			}
			
		}
		
		// 3) 책 수정
		if(bookDAO.updateBook(book) < 1) {
			System.out.println("3) 책 수정 실패!");
			return false;
		}
		
		// 4) Booktag 수정
		List<Booktag> oldBookTags = new ArrayList<>(); //이전 태그 목록
		Set<Integer> toBeDeletedSet = new HashSet<>(); //이전 태그 중 삭제되어야할 태그 목록
		Booktag deleteTag = new Booktag(); //삭제할 태그
		
		//원래 글에 연결돼있던 태그들 불러오기
		Booktag forSearch = new Booktag();
		forSearch.setBookNo(book.getBookNo());
		oldBookTags = booktagDAO.selectBooktagList(forSearch);
		
		//삭제할 태그들의 목록 초기화
		for(Booktag oldBookTag : oldBookTags) {
			toBeDeletedSet.add(oldBookTag.getTagNo());
		}
		
		for (Booktag newBooktag : booktagcat.getBooktag()) { // 게시글에 태그 등록
			//새로운 태그와 비교
			if(toBeDeletedSet.remove(newBooktag.getTagNo())) {
				//새로운 태그가 원래 태그 목록에 포함되어 있었다면 다음 태그 검사
				continue;
			}else {
				//새로운 태그가 원래 태그 목록에 없었다면, 해당 태그 insert
				newBooktag.setBookNo(book.getBookNo());
				System.out.println(newBooktag);
				booktagDAO.insertBooktag(newBooktag);
			}			
		}
		System.out.println("새 태그 추가 완료===========");
		
		//toBeDeleteSet에 남은 원소들 -> 새로운 태그엔 없지만 기존 태그에 있던 항목들 -> 삭제되어야함
		for(int tagNo : toBeDeletedSet) {
			System.out.println(tagNo);
			deleteTag.setTagNo(tagNo);
			deleteTag.setBookNo(book.getBookNo());
			booktagDAO.deleteBooktag(deleteTag);
		}
		System.out.println("기존 태그 삭제 완료==========="); 
		
		
		// 5) Tmptag 수정
		List<Tmptag> oldTmpTags = new ArrayList<>();	//기존 tmp태그 리스트
		toBeDeletedSet = new HashSet<>();				//삭제 되어야할 tmp태그 리스트
		Tmptag deleteTmpTag = new Tmptag(); 			//삭제할 태그
	
		//원래 글에 연결돼있던 임시태그들 불러오기
		Tmptag forSearchTmpTag = new Tmptag();
		forSearchTmpTag.setBookNo(book.getBookNo());
		oldTmpTags = tmptagDAO.selectTmptagList(forSearchTmpTag);
		
		//삭제할 태그들의 목록 초기화
		for(Tmptag oldTmptag : oldTmpTags) {
			toBeDeletedSet.add(oldTmptag.getTmptagNo());
		}
		
		for(Tmptag newTmpTag : booktagcat.getTmptag()) { // 게시글에 태그 등록
			//새로운 태그와 비교
			if(toBeDeletedSet.remove(newTmpTag.getTmptagNo())) {
				//새로운 태그가 원래 태그 목록에 포함되어 있었다면 다음 태그 검사
				continue;
			}else {
				//새로운 태그가 원래 태그 목록에 없었다면, 해당 태그 insert
				newTmpTag.setBookNo(book.getBookNo());
				tmptagDAO.insertTmptag(newTmpTag);
			}			
		}
		System.out.println("새 임시 태그 추가 완료===========");
		
		//toBeDeleteSet에 남은 원소들 -> 새로운 태그엔 없지만 기존 태그에 있던 항목들 -> 삭제되어야함
		for(int tagNo : toBeDeletedSet) {
			System.out.println(tagNo);
			deleteTmpTag.setTmptagNo(tagNo);
			deleteTmpTag.setBookNo(book.getBookNo());
			tmptagDAO.deleteTmptag(deleteTmpTag);
		}
		System.out.println("기존 임시 태그 삭제 완료===========");
		
		return true;
		
	}

	// 책 삭제
	@Override
	public boolean deleteBook(Book book, int userNo) throws Exception {
		System.out.println("service(Book) | deleteBook");
		System.out.println(book);
		
		// 1) 사용자 검증 -> 2) Tmptag 삭제 -> 3) Booktag 삭제 -> 4) Book 삭제 -> 5) 이미지 삭제
		
		// 사용자 검증
		if(userNo != bookDAO.selectBookOne(book).getWriter()) {
			System.out.println("유효하지 않은 사용자 입니다.");
			return false;
		}
		
		// tmptag 삭제
		Tmptag tmptag = new Tmptag();
		tmptag.setBookNo(book.getBookNo());
		int cnt = tmptagDAO.deleteTmptag(tmptag);
		if(cnt < 0) {
			System.out.println("tmptag 삭제에 문제가 있습니다.");
			return false;
		}
		System.out.println("tmptag : " + cnt);
		
		// booktag 삭제
		Booktag booktag = new Booktag();
		booktag.setBookNo(book.getBookNo());
		cnt = booktagDAO.deleteBooktag(booktag);
		if(cnt < 0) {
			System.out.println("booktag 삭제에 문제가 있습니다.");
			return false;
		}
		System.out.println("booktag : " + cnt);
		
		// book 삭제
		cnt = bookDAO.deleteBook(book);
		if(cnt != 1) {
			System.out.println("book 삭제에 문제가 있습니다.");
			return false;
		}
		System.out.println("book : " + cnt);
		
		// 이미지 삭제
		if(!fileService.deleteBookCover(book.getBookNo())) {
			System.out.println(book.getBookNo() + " 이미지 파일 삭제 실패!");
			return false;
		}
		return true;
	}

	// 책 상세조회
	@Override
	public BookTagCat detailBook(Book book) throws Exception {
		System.out.println("service(Book) | detailBook");
		System.out.println(book);
		
		
		// 1) bookNo로 조회할 책 조회 -> 2) bookNo로 Booktag 조회 -> 3) bookNo로 Tmptag 조회 -> BookTagCat에 저장 (리턴값)
		// 1) writer정보 저장 -> 2) loginUserNo 저장
		// 1) writer == loginUserNo -> private
		// 2) writer -> loginUserNo 팔로우 -> protected
		// 3) writer -X> loginUserNo 팔로우X -> public
		// 4) writer -> loginUserNo 블락 -> block
		
		// 1) 책 조회
		BookTagCat booktagcat = new BookTagCat();
		// book
		booktagcat.setBook(bookDAO.selectBookOne(book));
		// booktag
		Booktag booktag = new Booktag();
		booktag.setBookNo(book.getBookNo());
		booktagcat.setBooktag(booktagDAO.selectBooktagList(booktag));
		// tmptag
		Tmptag tmptag = new Tmptag();
		tmptag.setBookNo(book.getBookNo());
		booktagcat.setTmptag(tmptagDAO.selectTmptagList(tmptag));
		System.out.println(booktagcat);
		
		// 2) openScope 범위 확인
		User writer = new User();
		User loginUser = new User();
		writer.setUserNo(booktagcat.getBook().getWriter());
		loginUser.setUserNo(book.getLoginUserNo());
		System.out.println("writer : " + writer);
		System.out.println("loginUser : " + loginUser);
		
		// 2-1) public -> protected -> private -> block
		String openScope = "public";
		String bookOpenScope = booktagcat.getBook().getOpenScope();
		
		// follower인가?
		try {
			List<Follow> followList = userDAO.selectFollowerList(loginUser);
			if(followList != null) {
				for(Follow follow : followList) {
					System.out.println(follow);
					if(follow.getFollower() == writer.getUserNo()) {
						openScope = "protected";
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("followList 조회 중 에러");
		}
		
		// 책 주인인가?
		if(writer.getUserNo() == loginUser.getUserNo()) {
			openScope = "private";
		}
		
		// block을 당했는가?
		try {
			List<Block> blockList = userDAO.selectBlockList(writer);
			if(blockList != null) {
				for(Block block : blockList) {
					if(block.getBlocking() == loginUser.getUserNo()) {
						openScope = "block";
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("blockList 조회 중 에러");
		}
		
		// 3) 책과 사용자의 openScope 비교
		System.out.println(openScope + " | " + bookOpenScope);
		if(openScope.equals("block")) {
			System.out.println("열람 권한이 없습니다.");
			throw new Exception();
		} else if(openScope.equals("private")) {
		} else if(openScope.equals("protected")) {
			if(bookOpenScope.equals("private")) {
				System.out.println("열람 권한이 없습니다.");
				throw new Exception();
			}
		} else if(openScope.equals("public")) {
			if(!bookOpenScope.equals("public")) {
				System.out.println("열람 권한이 없습니다.");
				throw new Exception();
			}
		} else {
			System.out.println("열람 권한이 없습니다.");
			throw new Exception();
		}
		
		return booktagcat;
	}

	// 책 검색
	@Override
	public List<BookTagCat> searchBook(Book book) throws Exception {
		System.out.println("service(Book) | searchBook");
		System.out.println(book);
		
		// 1) 작성자 정보, loginUserNo 정보 저장
		// 2) 작성자 정보, loginUserNo 정보 비교하여 openScope 추출
		// 3) 검색에 openScope 넣어서 검색
		// 4) 결과 리턴
		
		if(book == null) {
			System.out.println("잘못된 접근입니다.");
			throw new Exception();
		}
		
		if(book.getWriter() > 0 && book.getLoginUserNo() > 0) {
			
			// 1) openScope 범위 확인
			User writer = new User();
			User loginUser = new User();
			writer.setUserNo(book.getWriter());
			loginUser.setUserNo(book.getLoginUserNo());
			System.out.println("writer : " + writer);
			System.out.println("loginUser : " + loginUser);
			
			// 2) public -> protected -> private -> block
			String openScope = "public";
			
			// follower인가?
			try {
				List<Follow> followList = userDAO.selectFollowerList(loginUser);
				if(followList != null) {
					for(Follow follow : followList) {
						System.out.println(follow);
						if(follow.getFollower() == writer.getUserNo()) {
							openScope = "protected";
							break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("followList 조회 중 에러");
			}
			
			// 책 주인인가?
			if(writer.getUserNo() == loginUser.getUserNo()) {
				openScope = "private";
			}
			
			// block을 당했는가?
			try {
				List<Block> blockList = userDAO.selectBlockList(writer);
				if(blockList != null) {
					for(Block block : blockList) {
						if(block.getBlocking() == loginUser.getUserNo()) {
							openScope = "block";
							break;
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("blockList 조회 중 에러");
			}

			book.setOpenScope(openScope);
		} else {
			book.setOpenScope("public");
		}
		
		System.out.println("search book : " + book);
		List<Book> bookList = bookDAO.selectBookList(book);
		
		List<BookTagCat> btcList = new ArrayList();
		for(Book B : bookList) {
			System.out.println("----Book----");
			BookTagCat btc = new BookTagCat();
			btc.setBook(B);
		
			System.out.println("----Booktag----");
			Booktag booktag = new Booktag();
			booktag.setBookNo(B.getBookNo());
			btc.setBooktag(booktagDAO.selectBooktagList(booktag));

			System.out.println("----Tmptag----");
			Tmptag tmptag = new Tmptag();
			tmptag.setBookNo(B.getBookNo());
			btc.setTmptag(tmptagDAO.selectTmptagList(tmptag));
			
			System.out.println("----comment----");
			Comment comment = new Comment();
			comment.setBookNo(B.getBookNo());
			btc.setCommentCount(commentDAO.selectCommentCount(comment));
			
			btcList.add(btc);		
		}
		return btcList;
	}

	// 책 갯수 검색
	@Override
	public int countBook(Book book) throws Exception {
		System.out.println("service(Book) | countBook");
		System.out.println(book);
		
		// 1) 작성자 정보, loginUserNo 정보 저장
		// 2) 작성자 정보, loginUserNo 정보 비교하여 openScope 추출
		// 3) 검색에 openScope 넣어서 검색
		// 4) 결과 리턴
		
		if(book == null) {
			System.out.println("잘못된 접근입니다.");
			throw new Exception();
		}
		
		if(book.getWriter() > 0 && book.getLoginUserNo() > 0) {
			
			// 1) openScope 범위 확인
			User writer = new User();
			User loginUser = new User();
			writer.setUserNo(book.getWriter());
			loginUser.setUserNo(book.getLoginUserNo());
			System.out.println("writer : " + writer);
			System.out.println("loginUser : " + loginUser);
			
			// 2) public -> protected -> private -> block
			String openScope = "public";
			
			// follower인가?
			try {
				List<Follow> followList = userDAO.selectFollowerList(loginUser);
				if(followList != null) {
					for(Follow follow : followList) {
						System.out.println(follow);
						if(follow.getFollower() == writer.getUserNo()) {
							openScope = "protected";
							break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("followList 조회 중 에러");
			}
			
			// 책 주인인가?
			if(writer.getUserNo() == loginUser.getUserNo()) {
				openScope = "private";
			}
			
			// block을 당했는가?
			try {
				List<Block> blockList = userDAO.selectBlockList(writer);
				if(blockList != null) {
					for(Block block : blockList) {
						if(block.getBlocking() == loginUser.getUserNo()) {
							openScope = "block";
							break;
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("blockList 조회 중 에러");
			}

			book.setOpenScope(openScope);
		} else {
			book.setOpenScope("public");
		}
		
		return bookDAO.selectBookCount(book);
	}

	
	// Bcontent 테이블 (책 내용) ------------------------------------------
	
	// 책 내용 생성
	@Override
	public boolean createBcontent(Bcontent bcontent) {
		System.out.println("service(Book) | createBcontent");
		System.out.println(bcontent);
		int cnt = bcontentDAO.insertBcontent(bcontent);
		return cnt==1?true:false;
	}

	// 책 내용 수정
	@Override
	public boolean updateBcontent(Bcontent bcontent) {
		System.out.println("service(Book) | updateBcontent");
		System.out.println(bcontent);
		int cnt = bcontentDAO.updateBcontent(bcontent);
		return cnt==1?true:false;
	}

	// 책 내용 삭제
	@Override
	public boolean deleteBcontent(Bcontent bcontent) {
		System.out.println("service(Book) | deleteBcontent");
		System.out.println(bcontent);
		int cnt = bcontentDAO.deleteBcontent(bcontent);
		return cnt==1?true:false;
	}

	// 책 내용 상세조회
	@Override
	public Bcontent detailBcontent(Bcontent bcontent) {
		System.out.println("service(Book) | detailBcontent");
		System.out.println(bcontent);
		return bcontentDAO.selectBcontentOne(bcontent);
	}

	// 책 내용 검색
	@Override
	public List<Bcontent> searchBcontent(Bcontent bcontent) {
		System.out.println("service(Book) | searchBcontent");
		System.out.println(bcontent);
		return bcontentDAO.selectBcontentList(bcontent);
	}

	// 책 내용 갯수 검색
	@Override
	public int countBcontent(Bcontent bcontent) {
		System.out.println("service(Book) | countBcontent");
		System.out.println(bcontent);
		return bcontentDAO.selectBcontentCount(bcontent);
	}
	
	// Series 테이블 (시리즈) ------------------------------------------
	
	// 시리즈 생성
	@Override
	public boolean createSeries(Series series) {
		System.out.println("service(Book) | createSeries");
		System.out.println(series);
		int cnt = seriesDAO.insertSeries(series);
		return cnt==1?true:false;
	}

	// 시리즈 수정
	@Override
	public boolean updateSeries(Series series) {
		System.out.println("service(Book) | updateSeries");
		System.out.println(series);
		int cnt = seriesDAO.updateSeries(series);
		return cnt==1?true:false;
	}

	// 시리즈 삭제
	@Override
	public boolean deleteSeries(Series series) {
		System.out.println("service(Book) | deleteSeries");
		System.out.println(series);
		int cnt = seriesDAO.deleteSeries(series);
		return cnt==1?true:false;
	}

	// 시리즈 상세조회
	@Override
	public Series detailSeries(Series series) {
		System.out.println("service(Book) | detailSeries");
		System.out.println(series);
		return seriesDAO.selectSeriesOne(series);
	}

	// 시리즈 검색
	@Override
	public List<Series> searchSeries(Series series) {
		System.out.println("service(Book) | searchSeries");
		System.out.println(series);
		return seriesDAO.selectSeriesList(series);
	}

	// 시리즈 갯수 검색
	@Override
	public int countSeries(Series series) {
		System.out.println("service(Book) | countSeries");
		System.out.println(series);
		return seriesDAO.selectSeriesCount(series);
	}

	// Bookseries 테이블 (책 시리즈 연결) ------------------------------------------
	
	// 책 시리즈 연결 생성
	@Override
	public boolean createBookseries(Bookseries bookseries) {
		System.out.println("service(Book) | createBookseries");
		System.out.println(bookseries);
		int cnt = bookseriesDAO.insertBookseries(bookseries);
		return cnt==1?true:false;
	}
	
	// 책 시리즈 연결 수정
	@Override
	public boolean updateBookseries(Bookseries bookseries) {
		System.out.println("service(Book) | updateBookseries");
		System.out.println(bookseries);
		int cnt = bookseriesDAO.updateBookseries(bookseries);
		return cnt==1?true:false;
	}
	
	// 책 시리즈 연결 삭제
	@Override
	public boolean deleteBookseries(Bookseries bookseries) {
		System.out.println("service(Book) | deleteBookseries");
		System.out.println(bookseries);
		int cnt = bookseriesDAO.deleteBookseries(bookseries);
		return cnt==1?true:false;
	}
	
	// 책 시리즈 연결 상세조회
	@Override
	public Bookseries detailBookseries(Bookseries bookseries) {
		System.out.println("service(Book) | detailBookseries");
		System.out.println(bookseries);
		return bookseriesDAO.selectBookseriesOne(bookseries);
	}
	
	// 책 시리즈 연결 검색
	@Override
	public List<Bookseries> searchBookseries(Bookseries bookseries) {
		System.out.println("service(Book) | searchBookseries");
		System.out.println(bookseries);
		return bookseriesDAO.selectBookseriesList(bookseries);
	}
	
	// 책 시리즈 연결 갯수 검색
	@Override
	public int countBookseries(Bookseries bookseries) {
		System.out.println("service(Book) | countBookseries");
		System.out.println(bookseries);
		return bookseriesDAO.selectBookseriesCount(bookseries);
	}
	
	// Booklike 테이블 (책 좋아요) ------------------------------------------
	
	// 책 좋아요 생성
	@Override
	public boolean createBooklike(Booklike booklike) {
		System.out.println("service(Book) | createBooklike");
		System.out.println(booklike);
		int cnt = booklikeDAO.insertBooklike(booklike);
		return cnt==1?true:false;
	}

	// 책 좋아요 수정
	@Override
	public boolean updateBooklike(Booklike booklike) {
		System.out.println("service(Book) | updateBooklike");
		System.out.println(booklike);
		int cnt = booklikeDAO.updateBooklike(booklike);
		return cnt==1?true:false;
	}

	// 책 좋아요 삭제
	@Override
	public boolean deleteBooklike(Booklike booklike) {
		System.out.println("service(Book) | deleteBooklike");
		System.out.println(booklike);
		int cnt = booklikeDAO.deleteBooklike(booklike);
		return cnt==1?true:false;
	}

	// 책 좋아요 상세조회
	@Override
	public Booklike detailBooklike(Booklike booklike) {
		System.out.println("service(Book) | detailBooklike");
		System.out.println(booklike);
		return booklikeDAO.selectBooklikeOne(booklike);
	}

	// 책 좋아요 검색
	@Override
	public List<Booklike> searchBooklike(Booklike booklike) {
		System.out.println("service(Book) | searchBooklike");
		System.out.println(booklike);
		return booklikeDAO.selectBooklikeList(booklike);
	}

	// 책 좋아요 갯수 검색
	@Override
	public int countBooklike(Booklike booklike) {
		System.out.println("service(Book) | countBooklike");
		System.out.println(booklike);
		return booklikeDAO.selectBooklikeCount(booklike);
	}
	
	// Bookfavorite 테이블 (책 즐겨찾기) ------------------------------------------
	
	// 책 즐겨찾기 생성
	@Override
	public boolean createBookfavorite(Bookfavorite bookfavorite) {
		System.out.println("service(Book) | createBookfavorite");
		System.out.println(bookfavorite);
		int cnt = bookfavoriteDAO.insertBookfavorite(bookfavorite);
		return cnt==1?true:false;
	}

	// 책 즐겨찾기 수정
	@Override
	public boolean updateBookfavorite(Bookfavorite bookfavorite) {
		System.out.println("service(Book) | updateBookfavorite");
		System.out.println(bookfavorite);
		int cnt = bookfavoriteDAO.updateBookfavorite(bookfavorite);
		return cnt==1?true:false;
	}

	// 책 즐겨찾기 삭제
	@Override
	public boolean deleteBookfavorite(Bookfavorite bookfavorite) {
		System.out.println("service(Book) | deleteBookfavorite");
		System.out.println(bookfavorite);
		int cnt = bookfavoriteDAO.deleteBookfavorite(bookfavorite);
		return cnt==1?true:false;
	}

	// 책 즐겨찾기 상세조회
	@Override
	public Bookfavorite detailBookfavorite(Bookfavorite bookfavorite) {
		System.out.println("service(Book) | detailBookfavorite");
		System.out.println(bookfavorite);
		return bookfavoriteDAO.selectBookfavoriteOne(bookfavorite);
	}

	// 책 즐겨찾기 검색
	@Override
	public List<Bookfavorite> searchBookfavorite(Bookfavorite bookfavorite) {
		System.out.println("service(Book) | searchBookfavorite");
		System.out.println(bookfavorite);
		return bookfavoriteDAO.selectBookfavoriteList(bookfavorite);
	}

	// 책 즐겨찾기 갯수 검색
	@Override
	public int countBookfavorite(Bookfavorite bookfavorite) {
		System.out.println("service(Book) | countBookfavorite");
		System.out.println(bookfavorite);
		return bookfavoriteDAO.selectBookfavoriteCount(bookfavorite);
	}
	
	// Bookcategory 테이블 (책 카테고리 연결) ------------------------------------------
	
	// 책 카테고리 연결 생성
	@Override
	public boolean createBookcategory(Bookcategory bookcategory) {
		System.out.println("service(Book) | createBookcategory");
		System.out.println(bookcategory);
		int cnt = bookcategoryDAO.insertBookcategory(bookcategory);
		return cnt==1?true:false;
	}

	// 책 카테고리 연결 수정
	@Override
	public boolean updateBookcategory(Bookcategory bookcategory) {
		System.out.println("service(Book) | updateBookcategory");
		System.out.println(bookcategory);
		int cnt = bookcategoryDAO.updateBookcategory(bookcategory);
		return cnt==1?true:false;
	}

	@Override
	public boolean deleteBookcategory(Bookcategory bookcategory) {
		System.out.println("service(Book) | deleteBookcategory");
		System.out.println(bookcategory);
		int cnt = bookcategoryDAO.deleteBookcategory(bookcategory);
		return cnt==1?true:false;
	}

	@Override
	public Bookcategory detailBookcategory(Bookcategory bookcategory) {
		System.out.println("service(Book) | detailBookcategory");
		System.out.println(bookcategory);
		return bookcategoryDAO.selectBookcategoryOne(bookcategory);
	}

	@Override
	public List<Bookcategory> searchBookcategory(Bookcategory bookcategory) {
		System.out.println("service(Book) | searchBookcategory");
		System.out.println(bookcategory);
		return bookcategoryDAO.selectBookcategoryList(bookcategory);
	}

	@Override
	public int countBookcategory(Bookcategory bookcategory) {
		System.out.println("service(Book) | countBookcategory");
		System.out.println(bookcategory);
		return bookcategoryDAO.selectBookcategoryCount(bookcategory);
	}

	@Override
	public boolean hitBooklike(Booklike booklike) {
		System.out.println("service(Book) | hitBooklike");
		System.out.println(booklike);
		
		if(booklikeDAO.selectBooklikeOne(booklike) == null) {
			return booklikeDAO.insertBooklike(booklike) == 1? true : false;
		} else {
			return booklikeDAO.deleteBooklike(booklike) == 1? true : false;
		}
	}

	@Override
	public String hitBookfavorite(Bookfavorite bookfavorite) {
		System.out.println("service(Book) | hitBookfavorite");
		System.out.println(bookfavorite);
		
		
		if(bookfavoriteDAO.selectBookfavoriteOne(bookfavorite) == null || bookfavoriteDAO.selectBookfavoriteOne(bookfavorite).getFavoriteNo() == 0) {
			return bookfavoriteDAO.insertBookfavorite(bookfavorite) == 1 ? "insert" : "fail";
		} else {
			return bookfavoriteDAO.deleteBookfavorite(bookfavorite) == 1 ? "delete" : "fail";
		}
	}
	
}
