package com.ssafy.happyhouse.dto;

public class EnvironmentInfo {

	private int no;
	private String name; //환경정보 이름
	private String bizcode;
	private String address; //주소 
	private String dongcode;
	private String dong;
	public EnvironmentInfo() {
		
	}
	public EnvironmentInfo(int no) {
		super();
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBizcode() {
		return bizcode;
	}
	public void setBizcode(String bizcode) {
		this.bizcode = bizcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	@Override
	public String toString() {
		return "EnvironmentInfo [no=" + no + ", name=" + name + ", bizcode=" + bizcode + ", address=" + address
				+ ", dongcode=" + dongcode + ", dong=" + dong + "]";
	}
	
	
}
