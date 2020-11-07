package com.ssazit.dto;

public class Level {			/*레벨*/
	private String 	level;			/*PK 레벨이름*/
	private int 	levelStart;			/*NN 경험치 범위시작(이상)*/
	private int 	levelEnd;			/*NN 경험치 범위 끝(미만)*/
	private String	levelImg;			/*NN 레벨 이미지(경로)*/
	private String	levelColor;			/*NN 레벨 색(RGB)*/

	private String 	searchKey;			/*검색 키*/
	private String 	searchValue;		/*검색 값*/
	private String 	orderKey;			/*정렬 키*/
	private String 	orderValue;			/*정렬 값*/
	private int 	limitStart;			/*페이징 시작*/
	private int 	limitEnd;			/*페이징 끝*/

	
	public String getLevelImg() {
		return levelImg;
	}

	public void setLevelImg(String levelImg) {
		this.levelImg = levelImg;
	}

	public String getLevelColor() {
		return levelColor;
	}

	public void setLevelColor(String levelColor) {
		this.levelColor = levelColor;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getLevelStart() {
		return levelStart;
	}

	public void setLevelStart(int levelStart) {
		this.levelStart = levelStart;
	}

	public int getLevelEnd() {
		return levelEnd;
	}

	public void setLevelEnd(int levelEnd) {
		this.levelEnd = levelEnd;
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
		return "Level [level=" + level + ", levelStart=" + levelStart + ", levelEnd=" + levelEnd + ", levelImg="
				+ levelImg + ", levelColor=" + levelColor + ", searchKey=" + searchKey + ", searchValue=" + searchValue
				+ ", orderKey=" + orderKey + ", orderValue=" + orderValue + ", limitStart=" + limitStart + ", limitEnd="
				+ limitEnd + "]";
	}
	
	
	
}
