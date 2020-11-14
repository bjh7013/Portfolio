package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.CommentDao;
import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao dao;
	
	@Override
	public boolean createComment(Comment comment) {
		return dao.createComment(comment);
	}

	@Override
	public List<Comment> selectCommentAll(Comment comment) {
		return dao.selectCommentAll(comment);
	}

	@Override
	public List<Comment> selectComment(Comment comment) {
		return dao.selectComment(comment);
	}

	@Override
	public boolean modifyComment(Comment comment) {
		return dao.modifyComment(comment);
	}

	@Override
	public boolean deleteComment(Comment comment) {
		return dao.deleteComment(comment);
	}

	

}
