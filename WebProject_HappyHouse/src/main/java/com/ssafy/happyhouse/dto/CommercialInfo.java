package com.ssafy.happyhouse.dto;

import java.io.Serializable;

/**
no			int primary key auto_increment,
shopname	varchar(30) NOT NULL,				--
code		varchar(30) NOT NULL,				--
codename	varchar(30) NOT NULL,				--
city		varchar(30) NOT NULL,				--
gu			varchar(30) NOT NULL,				--
dong		varchar(30) NOT NULL,				--
address		varchar(50),						--
lat			varchar(30),						--
lng			varchar(30)--
*/
public class CommercialInfo implements Serializable, Comparable<CommercialInfo> {

	/**상권 정보를 식별할 번호*/
	private int    no;
	/** */
	private String shopname;
	/** */
	private String code;
	/** */
	private String codename;
	/** */
	private String city;
	/** */
	private String gu;
	/** */
	private String dong;
	/** */
	private String address;
	/** */
	private String lat;
	/** */
	private String lng;
		
	
	public CommercialInfo() {
		super();
	}


	
	public CommercialInfo(int no) {
		super();
		this.no = no;
	}

	

	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}



	public String getShopname() {
		return shopname;
	}



	public void setShopname(String shopname) {
		this.shopname = shopname;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getCodename() {
		return codename;
	}



	public void setCodename(String codename) {
		this.codename = codename;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getGu() {
		return gu;
	}



	public void setGu(String gu) {
		this.gu = gu;
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



	public String getLat() {
		return lat;
	}



	public void setLat(String lat) {
		this.lat = lat;
	}



	public String getLng() {
		return lng;
	}



	public void setLng(String lng) {
		this.lng = lng;
	}



	@Override
	public String toString() {
		return "CommercialInfo [no=" + no + ", shopname=" + shopname + ", code=" + code + ", codename=" + codename
				+ ", city=" + city + ", gu=" + gu + ", dong=" + dong + ", address=" + address + ", lat=" + lat
				+ ", lng=" + lng + "]";
	}


	@Override
	public int compareTo(CommercialInfo arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
