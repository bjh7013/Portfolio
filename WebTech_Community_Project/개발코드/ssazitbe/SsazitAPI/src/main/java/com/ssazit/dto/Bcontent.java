package com.ssazit.dto;

public class Bcontent {		/*책 속지*/
	private int 	bookNo;		/*PK FK 책 번호*/
	private int 	seqNo;		/*PK 순서*/
	private int 	articleNo;		/*FK NN 게시글 번호(출처 추적용)*/
	private String 	chapter;		/*NN 소제목*/
	private String 	content;		/*NN 내용*/

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

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return "Bcontent [bookNo=" + bookNo + ", seqNo=" + seqNo + ", articleNo=" + articleNo + ", chapter=" + chapter
				+ ", content=" + content + ", searchKey=" + searchKey + ", searchValue=" + searchValue + ", orderKey="
				+ orderKey + ", orderValue=" + orderValue + ", limitStart=" + limitStart + ", limitEnd=" + limitEnd
				+ "]";
	}
	
}
