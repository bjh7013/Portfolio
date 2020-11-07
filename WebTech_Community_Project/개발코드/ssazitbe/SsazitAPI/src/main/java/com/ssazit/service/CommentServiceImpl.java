package com.ssazit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.ssazit.dao.ArticleDAO;
import com.ssazit.dao.BookDAO;
import com.ssazit.dao.CommentDAO;
import com.ssazit.dao.CommentlikeDAO;
import com.ssazit.dao.NotiDAO;
import com.ssazit.dao.UserDAO;
import com.ssazit.dto.Article;
import com.ssazit.dto.Book;
import com.ssazit.dto.Comment;
import com.ssazit.dto.Commentlike;
import com.ssazit.dto.Noti;
import com.ssazit.dto.User;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentDAO commentDAO;
	
	@Autowired
	CommentlikeDAO commentlikeDAO;
	
	@Autowired
	ArticleDAO articleDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired 
	BookDAO bookDAO;
	
	@Autowired
	NotiDAO notiDAO;
	// Comment 테이블 (댓글) ------------------------------------------
	
	
	// 댓글 생성
	@Override
	public boolean createComment(Comment comment) {
		System.out.println("service(Comment) | createComment");
		System.out.println(comment);
		
		
		int cnt = commentDAO.insertComment(comment);
		
		
		if(cnt == 1) {
			if(comment.getArticleNo() > 0) {
				Article article = new Article();
				User user = new User();
				
				article.setArticleNo(comment.getArticleNo());
				user.setUserNo(comment.getUserNo());
				
				article = articleDAO.selectArticleOne(article);
				user = userDAO.selectUserOne(user);
				
				//알림 등록
				Noti noti = new Noti();
				
				noti.setSender(comment.getUserNo());
				noti.setReceiver(article.getUserNo());
				noti.setNotiType("comment");
				noti.setArticleNo(article.getArticleNo());
				
				notiDAO.insertNoti(noti);
				
				noti.setNickName(user.getNickName());
				noti.setNotiMessage(article.getTitle() + "글에 " + user.getNickName() + "님이 댓글을 달았습니다.");
				
				
				simpMessagingTemplate.convertAndSend("/topic/pushnoti/" + article.getUserNo(), noti);
				
				return true;
			}
			else if(comment.getBookNo() > 0) {
				Book book = new Book();
				User user = new User();
				
				book.setBookNo(comment.getBookNo());
				user.setUserNo(comment.getUserNo());
				
				book = bookDAO.selectBookOne(book);
				user = userDAO.selectUserOne(user);
				
				
				//알림 등록
				Noti noti = new Noti();
				
				noti.setSender(comment.getUserNo());
				noti.setReceiver(book.getWriter());
				noti.setNotiType("comment");
				noti.setBookNo(book.getBookNo());
				
				notiDAO.insertNoti(noti);
				
				noti.setNickName(user.getNickName());
				noti.setNotiMessage(book.getBookTitle() + "책에 " + user.getNickName() + "님이 댓글을 달았습니다.");
				
				simpMessagingTemplate.convertAndSend("/topic/pushnoti/" + book.getWriter(), noti);
				
				return true;
			}
			else {
				return false;
			}
			
		}else {
			return false;
		}
		
	}

	// 댓글 수정
	@Override
	public boolean updateComment(Comment comment) {
		System.out.println("service(Comment) | updateComment");
		System.out.println(comment);
		int cnt = commentDAO.updateComment(comment);
		return cnt==1?true:false;
	}

	// 댓글 삭제
	@Override
	public boolean deleteComment(Comment comment) {
		System.out.println("service(Comment) | deleteComment");
		System.out.println(comment);
		int cnt = commentDAO.deleteComment(comment);
		return cnt==1?true:false;
	}

	// 댓글 상세조회
	@Override
	public Comment detailComment(Comment comment) {
		System.out.println("service(Comment) | detailComment");
		System.out.println(comment);
		return commentDAO.selectCommentOne(comment);
	}

	// 댓글 검색
	@Override
	public List<Comment> searchComment(Comment comment) {
		System.out.println("service(Comment) | searchComment");
		System.out.println(comment);
		return commentDAO.selectCommentList(comment);
	}


	// 댓글 갯수 검색
	@Override
	public int countComment(Comment comment) {
		System.out.println("service(Comment) | countComment");
		System.out.println(comment);
		return commentDAO.selectCommentCount(comment);
	}
	// Commentlike 테이블 (댓글 좋아요) ------------------------------------------
	
	
	// 댓글 좋아요 생성
	
	@Override
	public boolean createCommentlike(Commentlike commentlike) {
		System.out.println("service(Comment) | createCommentlike");
		System.out.println(commentlike);
		int cnt = commentlikeDAO.insertCommentlike(commentlike);
		return cnt==1?true:false;
	}
	// 댓글 좋아요 삭제
	@Override
	public boolean deleteCommentlike(Commentlike commentlike) {
		System.out.println("service(Comment) | deleteCommentlike");
		System.out.println(commentlike);
		int cnt = commentlikeDAO.deleteCommentlike(commentlike);
		return cnt==1?true:false;
	}

	// 댓글 좋아요 수정
	@Override
	public boolean updateCommentlike(Commentlike commentlike) {
		System.out.println("service(Comment) | updateCommentlike");
		System.out.println(commentlike);
		int cnt = commentlikeDAO.updateCommentlike(commentlike);
		return cnt==1?true:false;
	}


	// 댓글 좋아요 상세조회
	@Override
	public Commentlike detailCommentlike(Commentlike commentlike) {
		System.out.println("service(Comment) | detailCommentlike");
		System.out.println(commentlike);
		return commentlikeDAO.selectCommentlikeOne(commentlike);
	}

	// 댓글 좋아요 검색
	@Override
	public List<Commentlike> searchCommentlike(Commentlike commentlike) {
		System.out.println("service(Comment) | searchCommentlike");
		System.out.println(commentlike);
		return commentlikeDAO.selectCommentlikeList(commentlike);
	}

	// 댓글 좋아요 갯수 검색
	@Override
	public int countCommentlike(Commentlike commentlike) {
		System.out.println("service(Comment) | countCommentlike");
		System.out.println(commentlike);
		return commentlikeDAO.selectCommentlikeCount(commentlike);
	}

	@Override
	public boolean hitCommentlike(Commentlike commentlike) {
		// TODO Auto-generated method stub
		System.out.println("service(Comment) | hitCommentlike");
		System.out.println(commentlike);
		
		if(commentlikeDAO.selectCommentlikeOne(commentlike) == null) {
			return commentlikeDAO.insertCommentlike(commentlike) == 1? true : false;
		}else {
			return commentlikeDAO.deleteCommentlike(commentlike) == 1? true : false;			
		}			
	}

	@Override
	public boolean pickComment(Comment searchCondition) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("service(Comment) | pickComment");
		System.out.println(searchCondition);
		
		// 댓글 번호로 해당 댓글 정보 조회
		Comment comment = commentDAO.selectCommentOne(searchCondition);	
		if(comment == null)
			return false;
		
		// 해당 사용자가 이 댓글을 채택할 수 있는 권한이 있는지 검사(댓글이 속한 게시글을 요청자가 작성한 것인지 검사)
		Article article = new Article();
		article.setArticleNo(comment.getArticleNo());
		article.setUserNo(searchCondition.getUserNo());
		
		
		Book book = new Book();
		book.setBookNo(comment.getBookNo());
		book.setWriter(searchCondition.getUserNo());
		
		System.out.println(comment);
		System.out.println(book);
		
		if(articleDAO.validateArticle(article) == null && bookDAO.validateBook(book) == null)
			throw new Exception();
		
		
		System.out.println(comment);
		if(comment.getIsPick() == 1) {
			//채택된 댓글일 경우 채택 해제
			comment.setIsPick(0);
		}else {
			//채택 안된 댓글일 경우 채택
			comment.setIsPick(1);
		}
		
		
		
		if(commentDAO.pickComment(comment) == 1) {
			
			if(comment.getIsPick() == 1) {
				
				User user = new User();//댓글을 단 유저 정보
				user.setUserNo(comment.getUserNo());
				user = userDAO.selectUserOne(user);
				
				//알림 등록
				Noti noti = new Noti();
				
				noti.setReceiver(comment.getUserNo());
				noti.setNotiType("pick");
				
				
				String notiString = "";
				if(comment.getArticleNo() > 0) {
					Article art = articleDAO.selectArticleOne(article);
					
					noti.setSender(art.getUserNo());
					noti.setArticleNo(art.getArticleNo());
					
					notiString = art.getTitle() + "글에 달린 내 댓글이 채택되었습니다.";
				}else if(comment.getBookNo() > 0) {
					Book b = bookDAO.selectBookOne(book);	
		
					noti.setSender(b.getWriter());
					noti.setBookNo(b.getBookNo());
	
					notiString = b.getBookTitle() + "책에 달린 내 댓글이 채택되었습니다.";
				}				
				notiDAO.insertNoti(noti);
				
				noti.setNickName(user.getNickName());
				noti.setNotiMessage(notiString);
				
				
				simpMessagingTemplate.convertAndSend("/topic/pushnoti/" + comment.getUserNo(), noti);
			}
		
			return true;
		}
		
		
		return false;
	}

	@Override
	public List<Comment> myComment(Comment comment) {
		// TODO Auto-generated method stub
		System.out.println("service(Comment) | myComment");
		System.out.println(comment);
		return commentDAO.selectMyComment(comment);
	}

	// 내가 쓴 책 댓글 검색
	@Override
	public List<Comment> myBookComment(Comment comment) {
		// TODO Auto-generated method stub
		System.out.println("service(Comment) | myBookComment");
		System.out.println(comment);
		return commentDAO.selectMyBookComment(comment);
	}

	// 내가 쓴 책 댓글 갯수 검색
	@Override
	public int countMyBookComment(Comment comment) {
		System.out.println("service(Comment) | countMyBookComment");
		System.out.println(comment);
		return commentDAO.selectMyBookCommentCount(comment);
	}

	@Override
	public int myCommentCount(Comment comment) {
		// TODO Auto-generated method stub
		return commentDAO.selectMyCommentCount(comment);
	}
	
	
}
