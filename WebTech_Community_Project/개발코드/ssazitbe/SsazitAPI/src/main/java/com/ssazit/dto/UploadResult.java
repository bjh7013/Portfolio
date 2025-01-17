package com.ssazit.dto;

public class UploadResult {
	private int uploaded;
	private String fileName;
	private String url;
	
	public int getUploaded() {
		return uploaded;
	}
	public void setUploaded(int uploaded) {
		this.uploaded = uploaded;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "UploadResult [uploaded=" + uploaded + ", fileName=" + fileName + ", url=" + url + "]";
	}
	
	
}
