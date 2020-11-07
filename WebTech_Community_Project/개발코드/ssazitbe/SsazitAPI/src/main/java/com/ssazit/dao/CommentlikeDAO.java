package com.ssazit.dao;

import java.util.List;
import com.ssazit.dto.Commentlike;

public interface CommentlikeDAO {

	// C(댓글 좋아요 입력)
	public int insertCommentlike(Commentlike commentlike);
	// U(댓글 좋아요 수정)
	public int updateCommentlike(Commentlike commentlike);
	// D(댓글 좋아요 삭제)
	public int deleteCommentlike(Commentlike commentlike);
	
	// R
	// 상세조회
	public Commentlike selectCommentlikeOne(Commentlike commentlike);
	// 리스트 조회
	public List<Commentlike> selectCommentlikeList(Commentlike commentlike);
	// 리스트 갯수 조회
	public int selectCommentlikeCount(Commentlike commentlike);
	
}
