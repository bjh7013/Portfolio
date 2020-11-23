package com.bungle.dto;

public class ClassSignUp {
	private int classNo;
	private int studentNo;
	private String isApproved; //true / false / null 구분 가능해야 함.(int or String) 추후 데이터 타입 수정 
	private String requestDate;
	private String signupDate;
		
	
	public ClassSignUp() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getClassNo() {
		return classNo;
	}


	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}


	public int getStudentNo() {
		return studentNo;
	}


	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}


	public String getIsApproved() {
		return isApproved;
	}


	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}


	public String getRequestDate() {
		return requestDate;
	}


	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}


	public String getSignupDate() {
		return signupDate;
	}


	public void setSignupDate(String signupDate) {
		this.signupDate = signupDate;
	}


	@Override
	public String toString() {
		return "ClassSignUp [classNo=" + classNo + ", studentNo=" + studentNo + ", isApproved=" + isApproved
				+ ", requestDate=" + requestDate + ", signupDate=" + signupDate + "]";
	}

}
