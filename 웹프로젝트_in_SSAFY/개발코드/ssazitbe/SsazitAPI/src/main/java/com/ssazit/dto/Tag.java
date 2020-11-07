package com.ssazit.dto;

public class Tag {			/*대표 태그*/
	private int 	tagNo;		/*PK AI 대표태그 번호*/
	private String 	tagName;		/*NN 태그 명*/
	private int 	groupNo;		/*NN 그룹번호(같은 그룹 번호 = 같은 의미의 태그)*/
	
	private String 	searchKey;		/*검색 키*/
	private String 	searchValue;	/*검색 값*/
	private String 	orderKey;		/*정렬 키*/
	private String 	orderValue;		/*정렬 값*/
	private int 	limitStart;		/*페이징 시작*/
	private int 	limitEnd;		/*페이징 끝*/
	
	


	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getTagNo() {
		return tagNo;
	}

	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
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
		return "Tag [tagNo=" + tagNo + ", tagName=" + tagName + ", groupNo=" + groupNo + ", searchKey=" + searchKey
				+ ", searchValue=" + searchValue + ", orderKey=" + orderKey + ", orderValue=" + orderValue
				+ ", limitStart=" + limitStart + ", limitEnd=" + limitEnd + "]";
	}
	
}
