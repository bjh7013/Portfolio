package com.ssazit.dto;

public class Myboard {			/*게시판*/
	private int 	boardNo;		/*PK AI 게시판번호*/
	private int 	blogNo;				/*FK NN 블로그번호*/
	private int 	groupNo;			/*NN default 0 그룹번호*/
	private int 	seqNo;				/*NN default 0 게시판 순서(그룹 내 순서)*/
	private int		depth;				/*NN default 0 계층*/
	private String 	boardName;			/*NN 게시판명*/
	private String 	boardType;			/*NN 게시글구분(A:알고|N:게시글|L:구분선)*/
	
	private String 	searchKey;			/*검색 키*/
	private String 	searchValue;		/*검색 값*/
	private String 	orderKey;			/*정렬 키*/
	private String 	orderValue;			/*정렬 값*/
	private int 	limitStart;			/*페이징 시작*/
	private int 	limitEnd;			/*페이징 끝*/
	
	// join data
	private int		articleCount;		/*게시판의 게시글 수*/
	
	
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getBlogNo() {
		return blogNo;
	}
	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
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
		return "Myboard [boardNo=" + boardNo + ", blogNo=" + blogNo + ", groupNo=" + groupNo + ", seqNo=" + seqNo
				+ ", depth=" + depth + ", boardName=" + boardName + ", boardType=" + boardType + ", searchKey="
				+ searchKey + ", searchValue=" + searchValue + ", orderKey=" + orderKey + ", orderValue=" + orderValue
				+ ", limitStart=" + limitStart + ", limitEnd=" + limitEnd + ", articleCount=" + articleCount + "]";
	}

	
}
