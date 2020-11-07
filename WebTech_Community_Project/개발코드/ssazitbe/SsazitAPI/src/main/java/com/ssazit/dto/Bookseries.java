package com.ssazit.dto;

public class Bookseries {		/*책 시리즈 연결*/
	private int 	bookNo;			/*PK FK 책 번호*/
	private int 	seriesNo;		/*PK FK 시리즈 번호*/
	
	private String 	orderKey;			/*정렬 키*/
	private String 	orderValue;			/*정렬 값*/
	private int 	limitStart;			/*페이징 시작*/
	private int 	limitEnd;			/*페이징 끝*/
	
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public int getSeriesNo() {
		return seriesNo;
	}
	public void setSeriesNo(int seriesNo) {
		this.seriesNo = seriesNo;
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
		return "Bookseries [bookNo=" + bookNo + ", seriesNo=" + seriesNo + ", orderKey=" + orderKey + ", orderValue="
				+ orderValue + ", limitStart=" + limitStart + ", limitEnd=" + limitEnd + "]";
	}
	
}
