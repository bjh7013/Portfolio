package com.ssazit.dto;

public class Book {				/*책*/
	private int 	bookNo;			/*PK AI 책번호*/
	private int		writer;				/*FK NN 작성자 번호*/
	private String 	bookTitle;			/*NN 책 제목*/
	private String	bookIndex;			/*NN 책 목차*/
	private String	bookContent;		/*NN 책 내용*/
	private String	bookReference;		/*NN 출처*/
	private int		version;			/*NN default 1 버전*/
	private String  writeDate;			/*NN default now() 작성 일시*/
	private String  updateDate;			/*수정 일시*/
	private String	openScope;			/*NN 공개범위(공개(public)/친구공개(protected)/비공개(private)*/
	private String 	coverFront;			/*NN 표지(앞)*/
	private String 	coverMiddle;		/*NN 표지(중)*/
	private String 	coverBack;			/*NN 표지(뒤)*/
	private String 	likeCount;			/*NN default 0 추천수(트리거 참조칼럼)*/
	private int		categoryNo;			/*default null 일반카테고리*/
	
	private String 	searchKey;			/*검색 키*/
	private String 	searchValue;		/*검색 값*/
	private String 	orderKey;			/*정렬 키*/
	private String 	orderValue;			/*정렬 값*/
	private int 	limitStart;			/*페이징 시작*/
	private int 	limitEnd;			/*페이징 끝*/
	private int		loginUserNo;		/*로그인해서 글을 조회하는 유저 번호*/
	private int		tagGroupNo;			/*태그 그룹 번호 (검색용)*/
	
	//join data
	private String 	nickName;			/*작성자 닉네임*/
	private String 	userId;				/*작성자 아이디*/
	private String	userLevel;			/*작성자 레벨*/
	private String	profileImg;			/*작성자 프로필이미지*/
	private int		groupNo;				/*태그 그룹넘버*/
	
	private String	likeDate;			/*책 좋아요 날짜*/
	private String	favoriteDate;		/*책 즐겨찾기 날짜*/
	
	
	private int 	seriesNo;			/*시리즈 번호*/
	private String 	seriesName;			/*시리즈 명*/
	private String 	seriesDesc;			/*시리즈 설명*/
	private String 	categoryName;		/*카테고리 명*/
	
	

	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getTagGroupNo() {
		return tagGroupNo;
	}
	public void setTagGroupNo(int tagGroupNo) {
		this.tagGroupNo = tagGroupNo;
	}
	public String getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(String likeDate) {
		this.likeDate = likeDate;
	}

	public String getFavoriteDate() {
		return favoriteDate;
	}
	public void setFavoriteDate(String favoriteDate) {
		this.favoriteDate = favoriteDate;
	}
	public int getLoginUserNo() {
		return loginUserNo;
	}
	public void setLoginUserNo(int loginUserNo) {
		this.loginUserNo = loginUserNo;
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
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getBookIndex() {
		return bookIndex;
	}
	public void setBookIndex(String bookIndex) {
		this.bookIndex = bookIndex;
	}
	public String getBookContent() {
		return bookContent;
	}
	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}
	public String getBookReference() {
		return bookReference;
	}
	public void setBookReference(String bookReference) {
		this.bookReference = bookReference;
	}
	public String getOpenScope() {
		return openScope;
	}
	public void setOpenScope(String openScope) {
		this.openScope = openScope;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getCoverFront() {
		return coverFront;
	}
	public void setCoverFront(String coverFront) {
		this.coverFront = coverFront;
	}
	public String getCoverMiddle() {
		return coverMiddle;
	}
	public void setCoverMiddle(String coverMiddle) {
		this.coverMiddle = coverMiddle;
	}
	public String getCoverBack() {
		return coverBack;
	}
	public void setCoverBack(String coverBack) {
		this.coverBack = coverBack;
	}
	public String getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
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
	public int getSeriesNo() {
		return seriesNo;
	}
	public void setSeriesNo(int seriesNo) {
		this.seriesNo = seriesNo;
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	public String getSeriesDesc() {
		return seriesDesc;
	}
	public void setSeriesDesc(String seriesDesc) {
		this.seriesDesc = seriesDesc;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", writer=" + writer + ", bookTitle=" + bookTitle + ", bookIndex=" + bookIndex
				+ ", bookContent=" + bookContent + ", bookReference=" + bookReference + ", version=" + version
				+ ", writeDate=" + writeDate + ", updateDate=" + updateDate + ", openScope=" + openScope
				+ ", coverFront=" + coverFront + ", coverMiddle=" + coverMiddle + ", coverBack=" + coverBack
				+ ", likeCount=" + likeCount + ", categoryNo=" + categoryNo + ", searchKey=" + searchKey
				+ ", searchValue=" + searchValue + ", orderKey=" + orderKey + ", orderValue=" + orderValue
				+ ", limitStart=" + limitStart + ", limitEnd=" + limitEnd + ", loginUserNo=" + loginUserNo
				+ ", tagGroupNo=" + tagGroupNo + ", nickName=" + nickName + ", userId=" + userId + ", userLevel="
				+ userLevel + ", profileImg=" + profileImg + ", groupNo=" + groupNo + ", likeDate=" + likeDate
				+ ", favoriteDate=" + favoriteDate + ", seriesNo=" + seriesNo + ", seriesName=" + seriesName
				+ ", seriesDesc=" + seriesDesc + ", categoryName=" + categoryName + "]";
	}


	
}
