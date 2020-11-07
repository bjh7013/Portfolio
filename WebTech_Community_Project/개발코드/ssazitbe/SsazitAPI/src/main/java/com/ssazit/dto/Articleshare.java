package com.ssazit.dto;

public class Articleshare {		/*게시글 공유*/
	private int 	articleNo;		/*PK FK 게시글 번호*/
	private int 	userNo;			/*PK FK 게시글 공유자*/
	private String 	shareDate;			/*NN default now() 공유 일시*/
	
	private String 	searchKey;			/*검색 키*/
	private String 	searchValue;		/*검색 값*/
	private String 	orderKey;			/*정렬 키*/
	private String 	orderValue;			/*정렬 값*/
	private int 	limitStart;			/*페이징 시작*/
	private int 	limitEnd;			/*페이징 끝*/
	


	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getShareDate() {
		return shareDate;
	}

	public void setShareDate(String shareDate) {
		this.shareDate = shareDate;
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
		return "Articleshare [articleNo=" + articleNo + ", userNo=" + userNo + ", shareDate=" + shareDate
				+ ", searchKey=" + searchKey + ", searchValue=" + searchValue + ", orderKey=" + orderKey
				+ ", orderValue=" + orderValue + ", limitStart=" + limitStart + ", limitEnd=" + limitEnd + "]";
	}
	
}
