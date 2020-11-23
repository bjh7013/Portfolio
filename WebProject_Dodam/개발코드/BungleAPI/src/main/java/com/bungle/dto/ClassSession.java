package com.bungle.dto;

public class ClassSession {
	private int sessionNo; // 세션 번호
	private String sessionName; // 세션 이름
	private String sessionDesc; // 세션 설명
	private int classNo; // 등록 클래스 번호

	public ClassSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSessionNo() {
		return sessionNo;
	}

	public void setSessionNo(int sessionNo) {
		this.sessionNo = sessionNo;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getSessionDesc() {
		return sessionDesc;
	}

	public void setSessionDesc(String sessionDesc) {
		this.sessionDesc = sessionDesc;
	}

	public int getClassNo() {
		return classNo;
	}

	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}

	@Override
	public String toString() {
		return "ClassSession [sessionNo=" + sessionNo + ", sessionName=" + sessionName + ", sessionDesc=" + sessionDesc
				+ ", classNo=" + classNo + "]";
	}

}
