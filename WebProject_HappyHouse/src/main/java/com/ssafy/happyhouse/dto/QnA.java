package com.ssafy.happyhouse.dto;

import java.sql.Date;

public class QnA {
	private int qnaNo;
	private String qnaTitle;
	private String qnaContent;
	private String qnaPassword;
	private Date qnaDatetime;
	private String replyContent;
	private String replyCheck;
	private Date replyDatetime;
	private String replyUserid;

	@Override
	public String toString() {
		return "QnA [qnaNo=" + qnaNo + ", qnaTitle=" + qnaTitle + ", qnaContent=" + qnaContent + ", qnaPassword="
				+ qnaPassword + ", qnaDatetime=" + qnaDatetime + ", replyContent=" + replyContent + ", replyCheck="
				+ replyCheck + ", replyDatetime=" + replyDatetime + ", replyUserid=" + replyUserid + "]";
	}

	public QnA() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QnA(int qnaNo, String qnaTitle, String qnaContent, String qnaPassword, Date qnaDatetime, String replyContent,
			String replyCheck, Date replyDatetime, String replyUserid) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qnaPassword = qnaPassword;
		this.qnaDatetime = qnaDatetime;
		this.replyContent = replyContent;
		this.replyCheck = replyCheck;
		this.replyDatetime = replyDatetime;
		this.replyUserid = replyUserid;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public String getQnaPassword() {
		return qnaPassword;
	}

	public void setQnaPassword(String qnaPassword) {
		this.qnaPassword = qnaPassword;
	}

	public Date getQnaDatetime() {
		return qnaDatetime;
	}

	public void setQnaDatetime(Date qnaDatetime) {
		this.qnaDatetime = qnaDatetime;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyCheck() {
		return replyCheck;
	}

	public void setReplyCheck(String replyCheck) {
		this.replyCheck = replyCheck;
	}

	public Date getReplyDatetime() {
		return replyDatetime;
	}

	public void setReplyDatetime(Date replyDatetime) {
		this.replyDatetime = replyDatetime;
	}

	public String getReplyUserid() {
		return replyUserid;
	}

	public void setReplyUserid(String replyUserid) {
		this.replyUserid = replyUserid;
	}

}
