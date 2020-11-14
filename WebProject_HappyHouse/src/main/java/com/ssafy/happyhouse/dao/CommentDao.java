package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.Comment;

@Mapper
public interface CommentDao {
	public boolean createComment(Comment comment); // 댓글쓰기
	public List<Comment> selectCommentAll(Comment comment); // 해당글전체댓글조회
	public List<Comment> selectComment(Comment comment); // 해당 글 해당 유저 댓글 조회
	public boolean modifyComment(Comment comment); // 댓글수정
	public boolean deleteComment(Comment comment); // 댓글삭제
}
