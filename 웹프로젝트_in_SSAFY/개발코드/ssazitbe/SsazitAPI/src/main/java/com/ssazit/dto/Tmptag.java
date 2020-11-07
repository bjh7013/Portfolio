package com.ssazit.dto;

public class Tmptag {		/*임시태그*/
	private int 	tmptagNo;	/*PK AI 임시태그 번호*/
	private int 	articleNo;		/*FK 글 번호*/
	private int 	bookNo;			/*FK 책 번호*/
	private String 	tmptagName;		/*NN 임시 태그명*/
	
	private String 	searchKey;		/*검색 키*/
	private String 	searchValue;	/*검색 값*/
	private String 	orderKey;		/*정렬 키*/
	private String 	orderValue;		/*정렬 값*/
	private int 	limitStart;		/*페이징 시작*/
	private int 	limitEnd;		/*페이징 끝*/

	public int getTmptagNo() {
		return tmptagNo;
	}

	public void setTmptagNo(int tmptagNo) {
		this.tmptagNo = tmptagNo;
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

	public String getTmptagName() {
		return tmptagName;
	}

	public void setTmptagName(String tmptagName) {
		this.tmptagName = tmptagName;
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
		return "Tmptag [tmptagNo=" + tmptagNo + ", articleNo=" + articleNo + ", bookNo=" + bookNo + ", tmptagName="
				+ tmptagName + ", searchKey=" + searchKey + ", searchValue=" + searchValue + ", orderKey=" + orderKey
				+ ", orderValue=" + orderValue + ", limitStart=" + limitStart + ", limitEnd=" + limitEnd + "]";
	}
	
}
