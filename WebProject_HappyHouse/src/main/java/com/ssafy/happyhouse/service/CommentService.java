package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.Comment;

public interface CommentService {
	public boolean createComment(Comment comment); // 댓글쓰기
	public List<Comment> selectCommentAll(Comment comment); // 해당글전체댓글조회
	public List<Comment> selectComment(Comment comment); // 해당글전체댓글조회
	public boolean modifyComment(Comment comment); // 댓글수정
	public boolean deleteComment(Comment comment); // 댓글삭제
}
