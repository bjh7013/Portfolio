package com.ssafy.happyhouse.dto;

public class rsInfo {
	private String systemno;
	private String dongcode;
	private String dong;
	private String address;
	private String rsid;
	private String rsname;
	private String rsshopname;
	private String tel;
	private String state;
	
	public rsInfo() {
		
	}
	public rsInfo(String systemno, String dongcode, String dong, String address, String rsid, String rsname,
			String rsshopname, String tel, String state) {
		super();
		this.systemno = systemno;
		this.dongcode = dongcode;
		this.dong = dong;
		this.address = address;
		this.rsid = rsid;
		this.rsname = rsname;
		this.rsshopname = rsshopname;
		this.tel = tel;
		this.state = state;
	}
	public String getSystemno() {
		return systemno;
	}
	public void setSystemno(String systemno) {
		this.systemno = systemno;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRsid() {
		return rsid;
	}
	public void setRsid(String rsid) {
		this.rsid = rsid;
	}
	public String getRsname() {
		return rsname;
	}
	public void setRsname(String rsname) {
		this.rsname = rsname;
	}
	public String getRsshopname() {
		return rsshopname;
	}
	public void setRsshopname(String rsshopname) {
		this.rsshopname = rsshopname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "rsInfo [dong=" + dong + ", address=" + address + ", rsshopname=" + rsshopname + "]";
	}
	
}
