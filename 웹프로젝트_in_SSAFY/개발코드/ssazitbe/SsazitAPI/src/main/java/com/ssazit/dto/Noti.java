package com.ssazit.dto;

public class Noti {
	private int notiNo;
	private int receiver;
	private String notiType;
	private String notiDate;
	private int sender;
	private int articleNo;
	private int bookNo;
	
	//join
	private String articleTitle;
	private String nickName;
	private String userId;
	private String bookTitle;
	private String profileImg;
	
	private String notiMessage;
	
	private String messageContent;
	
	
	
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getNotiMessage() {
		return notiMessage;
	}
	public void setNotiMessage(String notiMessage) {
		this.notiMessage = notiMessage;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public int getNotiNo() {
		return notiNo;
	}
	public void setNotiNo(int notiNo) {
		this.notiNo = notiNo;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public String getNotiType() {
		return notiType;
	}
	public void setNotiType(String notiType) {
		this.notiType = notiType;
	}
	public String getNotiDate() {
		return notiDate;
	}
	public void setNotiDate(String notiDate) {
		this.notiDate = notiDate;
	}
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	@Override
	public String toString() {
		return "Noti [notiNo=" + notiNo + ", receiver=" + receiver + ", notiType=" + notiType + ", notiDate=" + notiDate
				+ ", sender=" + sender + ", articleNo=" + articleNo + ", bookNo=" + bookNo + ", articleTitle="
				+ articleTitle + ", nickName=" + nickName + ", bookTitle=" + bookTitle + "]";
	}
	
	
	
}
