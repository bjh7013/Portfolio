package com.ssazit.dto;

public class Booklike {		/*책 좋아요*/
	private int 	bookNo;		/*PK FK 책 번호*/
	private int 	userNo;		/*PK FK 책 추천자*/
	private String 	likeDate;		/*NN default now() 좋아요 일시*/
	
	private String 	searchKey;		/*검색 키*/
	private String 	searchValue;	/*검색 값*/
	private String 	orderKey;		/*정렬 키*/
	private String 	orderValue;		/*정렬 값*/
	private int 	limitStart;		/*페이징 시작*/
	private int 	limitEnd;		/*페이징 끝*/
	


	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(String likeDate) {
		this.likeDate = likeDate;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}

	public String getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(String orderValue) {
		this.orderValue = orderValue;
	}

	public int getLimitStart() {
		return limitStart;
	}

	public void setLimitStart(int limitStart) {
		this.limitStart = limitStart;
	}

	public int getLimitEnd() {
		return limitEnd;
	}

	public void setLimitEnd(int limitEnd) {
		this.limitEnd = limitEnd;
	}

	@Override
	public String toString() {
		return "Booklike [bookNo=" + bookNo + ", userNo=" + userNo + ", likeDate=" + likeDate + ", searchKey="
				+ searchKey + ", searchValue=" + searchValue + ", orderKey=" + orderKey + ", orderValue=" + orderValue
				+ ", limitStart=" + limitStart + ", limitEnd=" + limitEnd + "]";
	}
	
}
