package com.ssazit.dto;

public class User {
	private int 		userNo;			/*PK AI 회원번호*/
	private String 		userId;				/*UK NN 회원 아이디*/
	private String 		userPw;				/*NN 비밀번호*/
	private String 		userName;			/*NN 이름*/
	private String 		nickName;			/*NN 닉네임*/
	private String 		eMail;				/*NN 이메일*/
	private String 		phoneNum;			/*NN 휴대폰 번호*/
	private String 		joinDate;			/*NN 가입 일시*/
	private String 		lastLogin;			/*최근 접속 일시*/
	private String 		withdrawalDate;		/*탈퇴 일시*/
	private boolean 	isAdmin;			/*NN default false 관리자여부(true | false)*/
	private int 		userExp;			/*NN default 0 경험치*/
	private String 		userLevel;			/*NN default '9zit' 레벨(트리거 참조칼럼)*/
	private String		profileImg;			/*NN default 'resource/user_img/default.PNG' 프로필이미지(경로)*/
	
	private String 		searchKey;			/*검색 키*/
	private String 		searchValue;		/*검색 값*/
	private String 		orderKey;			/*정렬 키*/
	private String 		orderValue;			/*정렬 값*/
	private int 		limitStart = -1;			/*페이징 시작*/
	private int 		limitEnd;			/*페이징 끝*/
	
	//join data
	private int blogNo;
	private String blogName;
	private String blogBirth;
	
	
	
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public int getBlogNo() {
		return blogNo;
	}
	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
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
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getWithdrawalDate() {
		return withdrawalDate;
	}
	public void setWithdrawalDate(String withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getUserExp() {
		return userExp;
	}
	public void setUserExp(int userExp) {
		this.userExp = userExp;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
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
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", nickName=" + nickName + ", eMail=" + eMail + ", phoneNum=" + phoneNum + ", joinDate=" + joinDate
				+ ", lastLogin=" + lastLogin + ", withdrawalDate=" + withdrawalDate + ", isAdmin=" + isAdmin
				+ ", userExp=" + userExp + ", userLevel=" + userLevel + ", profileImg=" + profileImg + ", searchKey="
				+ searchKey + ", searchValue=" + searchValue + ", orderKey=" + orderKey + ", orderValue=" + orderValue
				+ ", limitStart=" + limitStart + ", limitEnd=" + limitEnd + ", blogNo=" + blogNo + ", blogName="
				+ blogName + ", blogBirth=" + blogBirth + "]";
	}
	
}

