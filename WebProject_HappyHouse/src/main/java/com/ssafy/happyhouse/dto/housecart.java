package com.ssafy.happyhouse.dto;

public class housecart {
	private String userid;
	private int aptno;
	private String dong;
	public housecart() {
		 
	}
	public housecart(String userid,int aptno) {
		super();
		this.userid = userid;
		this.aptno = aptno;
	}
	public housecart(String userid, int aptno,String dong) {
		super();
		this.userid = userid;
		this.aptno = aptno;
		this.dong = dong;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getAptno() {
		return aptno;
	}
	public void setAptno(int aptno) {
		this.aptno = aptno;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}


}
