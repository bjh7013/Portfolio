package com.ssazit.dto;

public class Block {		/*차단*/
	private int 	blocker;	/*PK FK 차단한 사람*/
	private int 	blocking;	/*PK FK 차단 당한 사람*/
	private String 	blockDate;		/*NN 차단 일시*/
	
	private String 	searchKey;		/*검색 키*/
	private String 	searchValue;	/*검색 값*/
	private String 	orderKey;		/*정렬 키*/
	private String 	orderValue;		/*정렬 값*/
	private int 	limitStart;		/*페이징 시작*/
	private int 	limitEnd;		/*페이징 끝*/
	
	//join data
	private String userName;
	private String nickName;
	private String userId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBlocker() {
		return blocker;
	}

	public void setBlocker(int blocker) {
		this.blocker = blocker;
	}

	public int getBlocking() {
		return blocking;
	}

	public void setBlocking(int blocking) {
		this.blocking = blocking;
	}

	public String getBlockDate() {
		return blockDate;
	}

	public void setBlockDate(String blockDate) {
		this.blockDate = blockDate;
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
		return "Block [blocker=" + blocker + ", blocking=" + blocking + ", blockDate=" + blockDate + ", searchKey="
				+ searchKey + ", searchValue=" + searchValue + ", orderKey=" + orderKey + ", orderValue=" + orderValue
				+ ", limitStart=" + limitStart + ", limitEnd=" + limitEnd + "]";
	}	
	
}
