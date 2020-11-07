package com.ssazit.dao;

import java.util.List;
import com.ssazit.dto.Comment;

public interface CommentDAO {
	
	// C(댓글 입력)
	public int insertComment(Comment comment);
	// U(댓글 수정)
	public int updateComment(Comment comment);
	// D(댓글 삭제)
	public int deleteComment(Comment comment);
	// 댓글 채택
	public int pickComment(Comment comment);
	
	// R
	// 상세조회
	public Comment selectCommentOne(Comment comment);
	// 리스트 조회
	public List<Comment> selectCommentList(Comment comment);
	// 리스트 갯수 조회
	public int selectCommentCount(Comment comment);
	// 내 댓글 검색
	public List<Comment> selectMyComment(Comment comment);
	// 내 책 댓글 검색
	public List<Comment> selectMyBookComment(Comment comment);
	// 내 책 댓글 갯수 검색
	public int selectMyBookCommentCount(Comment comment);
	public int selectMyCommentCount(Comment comment);
}
