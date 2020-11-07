package com.ssazit.dto;

public class Comment {			/*게시글 댓글*/
	private int 	commentNo;		/*PK AI 댓글번호*/
	private int 	articleNo;			/*FK 게시글 번호*/
	private int 	bookNo;				/*FK 책 번호*/
	private int 	userNo;				/*FK NN 작성자*/
	private String 	content;			/*NN 내용*/
	private String 	writeDate;			/*NN default now() 작성일자*/
	private int		isPick;				/*NN default false 채택여부(boolean)*/
	private int 	likeCount;			/*NN default 0 추천수(트리거 참조칼럼)*/
	
	//join data
	private String	blogName;			/*댓글을 단 블로그명*/
	private String	articleTitle;		/*댓글을 단 게시글 이름*/
	private String	bookTitle;			/*댓글을 단 책 이름*/
	
	private String	profileImg;			/*작성자 프로필 이미지*/
	private String 	nickName;			/*작성자 닉네임*/
	private String 	userId;				/*작성자 아이디*/
	private String	userLevel;			/*작성자 레벨*/
	private int		loginUserNo;		/*로그인한 유저넘버*/
	private String	likeDate;			/*로그인된 계정이 이 댓글에 좋아요를 누른 일시*/
	
	private String 	searchKey;			/*검색 키*/
	private String 	searchValue;		/*검색 값*/
	private String 	orderKey;			/*정렬 키*/
	private String 	orderValue;			/*정렬 값*/
	private int 	limitStart;			/*페이징 시작*/
	private int 	limitEnd;			/*페이징 끝*/
	
	
	
	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Comment() {
		super();
		this.isPick = -1;
	}
	
	public String getBlogName() {
		return blogName;
	}


	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}


	public String getArticleTitle() {
		return articleTitle;
	}


	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}


	public int getLoginUserNo() {
		return loginUserNo;
	}

	public void setLoginUserNo(int loginUserNo) {
		this.loginUserNo = loginUserNo;
	}

	public String getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(String likeDate) {
		this.likeDate = likeDate;
	}


	public String getProfileImg() {
		return profileImg;
	}


	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
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


	public void setIsPick(int isPick) {
		this.isPick = isPick;
	}


	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
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

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public int getIsPick() {
		return isPick;
	}

	public void setPick(int isPick) {
		this.isPick = isPick;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
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

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", articleNo=" + articleNo + ", bookNo=" + bookNo + ", userNo="
				+ userNo + ", content=" + content + ", writeDate=" + writeDate + ", isPick=" + isPick + ", likeCount="
				+ likeCount + ", blogName=" + blogName + ", articleTitle=" + articleTitle + ", bookTitle=" + bookTitle
				+ ", profileImg=" + profileImg + ", nickName=" + nickName + ", userId=" + userId + ", userLevel="
				+ userLevel + ", loginUserNo=" + loginUserNo + ", likeDate=" + likeDate + ", searchKey=" + searchKey
				+ ", searchValue=" + searchValue + ", orderKey=" + orderKey + ", orderValue=" + orderValue
				+ ", limitStart=" + limitStart + ", limitEnd=" + limitEnd + "]";
	}
	
	
}
