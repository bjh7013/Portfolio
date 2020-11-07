package com.ssazit.dto;

import java.util.List;

public class Article { /* 게시글 */
	private int articleNo; /* PK AI 게시글 번호 */
	private int userNo; /* FK NN 작정자 번호 */
	private int boardNo; /* FK NN 게시판 번호 */
	private String title; /* NN 제목 */
	private String content; /* NN 내용 */
	private String writeDate; /* NN default now() 작성수정일시 */
	private String openScope; /* NN 공개범위(public:공개 | protected:친구공개 | private:비공개) */
	private String thumbNail; /* NN 썸네일이미지 */
	private String articleType; /* NN 게시글 구분(알고문제:A | 자유글:N) */
	private int sharable; /* NN default 1 공유가능여부(boolean) */
	private int reviewRequest; /* NN default 1 질문요청여부(boolean) */
	private int likeCount; /* NN default 0 추천수(트리거 참조칼럼) */
	private int acategoryNo; /* 알고리즘 카테고리 번호 */
	private int categoryNo; /* 카테고리 번호 */
	private int reference; /* 공유한 글인 경우 참조한 글 번호 */

	private String likeDate; /* 로그인한 계정이 해당 글에 좋아요를 누른 일시 */
	private String favoriteDate; /* 로그인한 게정이 해당 글을 관심글로 등록한 일시 */

	// 검색 조건용 데이터
	private String searchKey; /* 검색 키 */
	private String searchValue; /* 검색 값 */
	private List<String> searchWords;
	private String orderKey; /* 정렬 키 */
	private String orderValue; /* 정렬 값 */
	private int limitStart; /* 페이징 시작 */
	private int limitEnd; /* 페이징 끝 */
	private int tagGroupNo; /* 태그 그룹 번호 (검색용) */
	private int loginUserNo; /* 로그인해서 글을 조회하는 유저 번호 */

	// joindata
	private String nickName; /* 작성자 닉네임 */
	private String userId; /* 작성자 아이디 */
	private String userLevel; /* 작성자 레벨 */
	private String profileImg; /* 작성자 프로필이미지 */

	private int groupNo; /* 그룹번호(boardNo 식별자) */
	private int seqNo; /* 그룹 내 번호(boardNo 식별자) */

	// acategory
	private String acategorySource; /* 문제 출처 */
	private int acategoryNumber; /* 문제 번호 */
	private String acategoryTitle; /* 문제 명 */
	private String link; /* 문제 링크 */
	private int acategoryUserNo; /* 문제 등록자 */

	// category
	private String categoryName; /* 카테고리 이름 */

	
	public Article() {
		super();
		sharable = -1;
		reviewRequest = -1;
	}

	
	
	public List<String> getSearchWords() {
		return searchWords;
	}


	public void setSearchWords(List<String> searchWords) {
		this.searchWords = searchWords;
	}


	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

	public String getFavoriteDate() {
		return favoriteDate;
	}

	public void setFavoriteDate(String favoriteDate) {
		this.favoriteDate = favoriteDate;
	}

	public String getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(String likeDate) {
		this.likeDate = likeDate;
	}

	public int getLoginUserNo() {
		return loginUserNo;
	}

	public void setLoginUserNo(int loginUserNo) {
		this.loginUserNo = loginUserNo;
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

	public int getTagGroupNo() {
		return tagGroupNo;
	}

	public void setTagGroupNo(int tagGroupNo) {
		this.tagGroupNo = tagGroupNo;
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

	public String getAcategoryTitle() {
		return acategoryTitle;
	}

	public void setAcategoryTitle(String acategoryTitle) {
		this.acategoryTitle = acategoryTitle;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getAcategoryUserNo() {
		return acategoryUserNo;
	}

	public void setAcategoryUserNo(int acategoryUserNo) {
		this.acategoryUserNo = acategoryUserNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getAcategoryNo() {
		return acategoryNo;
	}

	public void setAcategoryNo(int acategoryNo) {
		this.acategoryNo = acategoryNo;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
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

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getOpenScope() {
		return openScope;
	}

	public void setOpenScope(String openScope) {
		this.openScope = openScope;
	}

	public String getThumbNail() {
		return thumbNail;
	}

	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public int getSharable() {
		return sharable;
	}

	public void setSharable(int sharable) {
		this.sharable = sharable;
	}

	public int getReviewRequest() {
		return reviewRequest;
	}

	public void setReviewRequest(int reviewRequest) {
		this.reviewRequest = reviewRequest;
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
		return "Article [articleNo=" + articleNo + ", userNo=" + userNo + ", boardNo=" + boardNo + ", title=" + title
				+ ", writeDate=" + writeDate + ", openScope=" + openScope + ", thumbNail="
				+ thumbNail + ", articleType=" + articleType + ", sharable=" + sharable + ", reviewRequest="
				+ reviewRequest + ", likeCount=" + likeCount + ", acategoryNo=" + acategoryNo + ", categoryNo="
				+ categoryNo + ", reference=" + reference + ", likeDate=" + likeDate + ", favoriteDate=" + favoriteDate
				+ ", searchKey=" + searchKey + ", searchValue=" + searchValue + ", orderKey=" + orderKey
				+ ", orderValue=" + orderValue + ", limitStart=" + limitStart + ", limitEnd=" + limitEnd
				+ ", tagGroupNo=" + tagGroupNo + ", loginUserNo=" + loginUserNo + ", nickName=" + nickName + ", userId="
				+ userId + ", userLevel=" + userLevel + ", profileImg=" + profileImg + ", groupNo=" + groupNo
				+ ", seqNo=" + seqNo + ", acategorySource=" + acategorySource + ", acategoryNumber=" + acategoryNumber
				+ ", acategoryTitle=" + acategoryTitle + ", link=" + link + ", acategoryUserNo=" + acategoryUserNo
				+ ", categoryName=" + categoryName + "]";
	}

	

}
