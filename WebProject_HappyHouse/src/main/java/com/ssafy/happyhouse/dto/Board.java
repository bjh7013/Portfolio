package com.ssafy.happyhouse.dto;

public class Board {
	private int articleno;
	private String userid;
	private String username;
	private String usertype;
	private String subject;
	private String content;
	private int clicked;
	private String regtime;

	public Board(int articleno, String userid, String username, String usertype, String subject, String content,
			int clicked, String regtime) {
		super();
		this.articleno = articleno;
		this.userid = userid;
		this.username = username;
		this.usertype = usertype;
		this.subject = subject;
		this.content = content;
		this.clicked = clicked;
		this.regtime = regtime;
	}

	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Board [articleno=" + articleno + ", userid=" + userid + ", username=" + username + ", usertype="
				+ usertype + ", subject=" + subject + ", content=" + content + ", clicked=" + clicked + ", regtime="
				+ regtime + "]";
	}

	public int getArticleno() {
		return articleno;
	}

	public void setArticleno(int articleno) {
		this.articleno = articleno;
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

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getClicked() {
		return clicked;
	}

	public void setClicked(int clicked) {
		this.clicked = clicked;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

}
