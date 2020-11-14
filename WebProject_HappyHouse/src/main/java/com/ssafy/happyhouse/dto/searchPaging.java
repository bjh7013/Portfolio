package com.ssafy.happyhouse.dto;

public class searchPaging {
	private int currentPage;
	private int sizePerPage;
	private int totalSize;
	private String word;
	private String [] type;
	
	public searchPaging() {}
	public searchPaging(String word) {
		this.word = word;
	
	}
	public searchPaging(String word, String [] type) {
		this.word = word;
		this.type = type;
	}
	public searchPaging(int currentPage, int sizePerPage, int totalSize, String word) {
		super();
		this.currentPage = currentPage;
		this.sizePerPage = sizePerPage;
		this.totalSize = totalSize;
		this.word = word;
	}
	public searchPaging(int currentPage, int sizePerPage, int totalSize, String word, String[] type) {
		super();
		this.currentPage = currentPage;
		this.sizePerPage = sizePerPage;
		this.totalSize = totalSize;
		this.word = word;
		this.type = type;
	}
	public int getCurrentPage() {
		
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage =(currentPage-1)*getSizePerPage();
	}
	public int getSizePerPage() {
		return sizePerPage;
	}
	public void setSizePerPage(int sizePerPage) {
		this.sizePerPage = sizePerPage;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String[] getType() {
		return type;
	}
	public void setType(String[] type) {
		this.type = type;
	}
	
}
