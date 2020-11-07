package com.ssazit.dto;

public class Message { /* 쪽지 */
	private int messageNo; /* PK AI 쪽지 번호 */
	private int messageTo; /* FK NN 쪽지 받는사람 */
	private int messageFrom; /* FK NN 쪽지 보낸사람 */
	private String content; /* NN 쪽지 내용 */
	private String sendDate; /* NN default now() 전송 일시 */
	private boolean isRead; /* NN default false 읽음여부(true|false) */
	private boolean sendDelete; /* NN default false 보낸사람이)보낸메세지 삭제 여부 */
	private boolean receiveDelete; /* NN default false 받은사람이)받은메세지 삭제 여부 */

	private String searchKey; /* 검색 키 */
	private String searchValue; /* 검색 값 */
	private String orderKey; /* 정렬 키 */ 
	private String orderValue; /* 정렬 값 */
	private int limitStart; /* 페이징 시작 */
	private int limitEnd; /* 페이징 끝 */

	// join data
	private String userName;
	private String nickName;
	private String userLevel;
	private int page; 

	public Message(int messageNo, int messageTo, int messageFrom, String content, String sendDate, boolean isRead,
			boolean sendDelete, boolean receiveDelete, String searchKey, String searchValue, String orderKey,
			String orderValue, int limitStart, int limitEnd, String userName, String nickName, String userLevel,
			int page) {
		super();
		this.messageNo = messageNo;
		this.messageTo = messageTo;
		this.messageFrom = messageFrom;
		this.content = content;
		this.sendDate = sendDate;
		this.isRead = isRead;
		this.sendDelete = sendDelete;
		this.receiveDelete = receiveDelete;
		this.searchKey = searchKey;
		this.searchValue = searchValue;
		this.orderKey = orderKey;
		this.orderValue = orderValue;
		this.limitStart = limitStart;
		this.limitEnd = limitEnd;
		this.userName = userName;
		this.nickName = nickName;
		this.userLevel = userLevel;
		this.page = page;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Message [messageNo=" + messageNo + ", messageTo=" + messageTo + ", messageFrom=" + messageFrom
				+ ", content=" + content + ", sendDate=" + sendDate + ", isRead=" + isRead + ", sendDelete="
				+ sendDelete + ", receiveDelete=" + receiveDelete + ", searchKey=" + searchKey + ", searchValue="
				+ searchValue + ", orderKey=" + orderKey + ", orderValue=" + orderValue + ", limitStart=" + limitStart
				+ ", limitEnd=" + limitEnd + ", userName=" + userName + ", nickName=" + nickName + ", userLevel="
				+ userLevel + ", page=" + page + "]";
	}

	public int getMessageNo() {
		return messageNo;
	}

	public void setMessageNo(int messageNo) {
		this.messageNo = messageNo;
	}

	public int getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(int messageTo) {
		this.messageTo = messageTo;
	}

	public int getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(int messageFrom) {
		this.messageFrom = messageFrom;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public boolean isSendDelete() {
		return sendDelete;
	}

	public void setSendDelete(boolean sendDelete) {
		this.sendDelete = sendDelete;
	}

	public boolean isReceiveDelete() {
		return receiveDelete;
	}

	public void setReceiveDelete(boolean receiveDelete) {
		this.receiveDelete = receiveDelete;
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

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
