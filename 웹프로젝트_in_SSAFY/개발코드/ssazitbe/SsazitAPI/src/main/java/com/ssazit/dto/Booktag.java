package com.ssazit.dto;

public class Booktag {				/*책 태그 연결*/
	private int 	bookNo;				/*PK FK 책 번호*/
	private int 	tagNo;				/*PK FK 태그 번호*/
	
	private String 	orderKey;				/*정렬 키*/
	private String 	orderValue;				/*정렬 값*/
	private int 	limitStart;				/*페이징 시작*/
	private int 	limitEnd;				/*페이징 끝*/
	
	// join data
	private String	tagName;				/*태그 이름*/
	private int		groupNo;				/*태그 그룹*/
	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public int getTagNo() {
		return tagNo;
	}
	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
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
		return "Booktag [bookNo=" + bookNo + ", tagNo=" + tagNo + ", orderKey=" + orderKey + ", orderValue="
				+ orderValue + ", limitStart=" + limitStart + ", limitEnd=" + limitEnd + ", tagName=" + tagName
				+ ", groupNo=" + groupNo + "]";
	}

	
}
