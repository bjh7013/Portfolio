package com.ssafy.happyhouse.dto;

public class Comment {
	private int commentno;
	private String userid;
	private String username;
	private String comment;
	private String commenttime;
	private int articleno;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Comment [commentno=" + commentno + ", userid=" + userid + ", username=" + username + ", comment="
				+ comment + ", commenttime=" + commenttime + ", articleno=" + articleno + "]";
	}

	public Comment(int commentno, String userid, String username, String comment, String commenttime, int articleno) {
		super();
		this.commentno = commentno;
		this.userid = userid;
		this.username = username;
		this.comment = comment;
		this.commenttime = commenttime;
		this.articleno = articleno;
	}

	public int getCommentno() {
		return commentno;
	}

	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}

	public int getArticleno() {
		return articleno;
	}

	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}

}
