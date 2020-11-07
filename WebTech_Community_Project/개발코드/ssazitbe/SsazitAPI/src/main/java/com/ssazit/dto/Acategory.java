package com.ssazit.dto;

public class Acategory {				/*알고리즘 문제 분류*/
	private int 	acategoryNo;			/*PK AI 알고리즘 문제 코드*/
	private String 	acategorySource;			/*NN 알고리즘 문제 사이트(문제번호와 묶어 UK)*/
	private int 	acategoryNumber;			/*NN 알고리즘 문제 번호(문제번호와 묶어 UK)*/
	private String 	title;						/*NN 알고리즘 문제명*/
	private String 	link;						/*사이트 링크*/
	private int 	userNo;						/*NN 입력한 사용자 식별자*/
	
	private String 	searchKey;					/*검색 키*/
	private String 	searchValue;				/*검색 값*/
	private String 	orderKey;					/*정렬 키*/
	private String 	orderValue;					/*정렬 값*/
	private int 	limitStart;					/*페이징 시작*/
	private int 	limitEnd;					/*페이징 끝*/
	
	
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getAcategoryNo() {
		return acategoryNo;
	}
	public void setAcategoryNo(int acategoryNo) {
		this.acategoryNo = acategoryNo;
	}
	public String getAcategorySource() {
		return acategorySource;
	}
	public void setAcategorySource(String acategorySource) {
		this.acategorySource = acategorySource;
	}
	public int getAcategoryNumber() {
		return acategoryNumber;
	}
	public void setAcategoryNumber(int acategoryNumber) {
		this.acategoryNumber = acategoryNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
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
		return "Acategory [acategoryNo=" + acategoryNo + ", acategorySource=" + acategorySource + ", acategoryNumber="
				+ acategoryNumber + ", title=" + title + ", link=" + link + ", userNo=" + userNo + ", searchKey="
				+ searchKey + ", searchValue=" + searchValue + ", orderKey=" + orderKey + ", orderValue=" + orderValue
				+ ", limitStart=" + limitStart + ", limitEnd=" + limitEnd + "]";
	}
	
}
