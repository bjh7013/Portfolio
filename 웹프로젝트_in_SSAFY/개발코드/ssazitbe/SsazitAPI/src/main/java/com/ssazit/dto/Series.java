package com.ssazit.dto;

public class Series {			/*시리즈*/
	private int 	seriesNo;		/*PK AI 시리즈 번호*/
	private String 	seriesName;			/*NN 시리즈 명*/
	private String 	seriesDesc;			/*시리즈 설명*/
	
	private String 	orderKey;			/*정렬 키*/
	private String 	orderValue;			/*정렬 값*/
	private int 	limitStart;			/*페이징 시작*/
	private int 	limitEnd;			/*페이징 끝*/
	
	public int getSeriesNo() {
		return seriesNo;
	}
	public void setSeriesNo(int seriesNo) {
		this.seriesNo = seriesNo;
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	public String getSeriesDesc() {
		return seriesDesc;
	}
	public void setSeriesDesc(String seriesDesc) {
		this.seriesDesc = seriesDesc;
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
		return "Series [seriesNo=" + seriesNo + ", seriesName=" + seriesName + ", seriesDesc=" + seriesDesc
				+ ", orderKey=" + orderKey + ", orderValue=" + orderValue + ", limitStart=" + limitStart + ", limitEnd="
				+ limitEnd + "]";
	}
	
	
}
