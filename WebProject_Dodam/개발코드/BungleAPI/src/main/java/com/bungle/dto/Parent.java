package com.bungle.dto;

public class Parent {
	private int parentNo;
	private int userNo;
	private int childNo;

	public Parent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Parent(int parentNo, int userNo, int childNo) {
		super();
		this.parentNo = parentNo;
		this.userNo = userNo;
		this.childNo = childNo;
	}

	public int getParentNo() {
		return parentNo;
	}

	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getChildNo() {
		return childNo;
	}

	public void setChildNo(int childNo) {
		this.childNo = childNo;
	}

}
