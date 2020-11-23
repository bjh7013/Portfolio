package com.bungle.dto;

public class LearningContent {
	private int contentNo;
	private String contentType;
	private String contentDesc;
	
	public int getContentNo() {
		return contentNo;
	}
	public void setContentNo(int contentNo) {
		this.contentNo = contentNo;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	@Override
	public String toString() {
		return "LearningContent [contentNo=" + contentNo + ", contentType=" + contentType + ", contentDesc="
				+ contentDesc + "]";
	}
	
	
}
