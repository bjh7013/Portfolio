package com.ssazit.dto;

public class Blog {				/*블로그*/
	private int 	blogNo;			/*PK AI 블로그번호*/
	private int 	userNo;				/*FK NN 블로그주인번호*/
	private String 	blogName;			/*NN 블로그명*/
	private String	blogBirth;			/*NN 블로그 생성 일시*/
	
	private String 	searchKey;			/*검색 키*/
	private String 	searchValue;		/*검색 값*/
	private String 	orderKey;			/*정렬 키*/
	private String 	orderValue;			/*정렬 값*/
	private int 	limitStart;			/*페이징 시작*/
	private int 	limitEnd;			/*페이징 끝*/
	
	//join data
	private String	userLevel;			/*사용자 레벨*/
	private String	profileImg;			/*사용자 프로필이미지*/
	private String	userId;				/*사용자 아이디*/
	private String	nickName;			/*사용자 닉네임*/
	
	
	
	
	public Blog() {
		super();
		// TODO Auto-generated constructor stub
		this.limitStart = -1;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public int getBlogNo() {
		return blogNo;
	}
	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getBlogBirth() {
		return blogBirth;
	}
	public void setBlogBirth(String blogBirth) {
		this.blogBirth = blogBirth;
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
		return "Blog [blogNo=" + blogNo + ", userNo=" + userNo + ", blogName=" + blogName + ", blogBirth=" + blogBirth
				+ ", searchKey=" + searchKey + ", searchValue=" + searchValue + ", orderKey=" + orderKey
				+ ", orderValue=" + orderValue + ", limitStart=" + limitStart + ", limitEnd=" + limitEnd
				+ ", userLevel=" + userLevel + ", profileImg=" + profileImg + ", userId=" + userId + ", nickName="
				+ nickName + "]";
	}

}
