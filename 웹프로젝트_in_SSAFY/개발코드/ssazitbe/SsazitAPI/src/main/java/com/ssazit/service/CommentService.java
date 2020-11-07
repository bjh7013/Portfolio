package com.ssazit.service;

import java.util.List;

import com.ssazit.dto.Comment;
import com.ssazit.dto.Commentlike;

public interface CommentService {

	// Comment 테이블 (댓글) ------------------------------------------
	
	// 댓글 생성
	public boolean createComment(Comment comment);
	// 댓글 수정
	public boolean updateComment(Comment comment);
	// 댓글 삭제
	public boolean deleteComment(Comment comment);
	// 댓글 상세조회
	public Comment detailComment(Comment comment);
	// 댓글 검색
	public List<Comment> searchComment(Comment comment);
	// 댓글 갯수 검색
	public int countComment(Comment comment);
	// 댓글 채택
	public boolean pickComment(Comment searchCondition) throws Exception;
	//내 댓글 검색
	public List<Comment> myComment(Comment comment);
	//내 댓글 검색(책)
	public List<Comment> myBookComment(Comment comment);
	//내 댓글 갯수 검색(책)
	public int countMyBookComment(Comment comment);

	// Commentlike 테이블 (댓글 좋아요) ------------------------------------------
	
	// 댓글 좋아요 생성
	public boolean createCommentlike(Commentlike commentlike);
	// 댓글 좋아요 수정
	public boolean updateCommentlike(Commentlike commentlike);
	// 댓글 좋아요 삭제
	public boolean deleteCommentlike(Commentlike commentlike);
	// 댓글 좋아요 클릭
	public boolean hitCommentlike(Commentlike commentlike);
	// 댓글 좋아요 상세조회
	public Commentlike detailCommentlike(Commentlike commentlike);
	// 댓글 좋아요 검색
	public List<Commentlike> searchCommentlike(Commentlike commentlike);
	// 댓글 좋아요 갯수 검색
	public int countCommentlike(Commentlike commentlike);
	public int myCommentCount(Comment comment);
	
}
