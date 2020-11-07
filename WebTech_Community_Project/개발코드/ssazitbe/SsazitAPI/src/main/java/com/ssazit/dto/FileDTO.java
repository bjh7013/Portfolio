package com.ssazit.dto;

public class FileDTO {
	private String path;
	private String fileName;
	private String extension;
	
	public FileDTO() {
		super();
	}
	
	public FileDTO(String path, String fileName, String extension) {
		super();
		this.path = path;
		this.fileName = fileName;
		this.extension = extension;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "File [path=" + path + ", fileName=" + fileName + ", extension=" + extension + "]";
	}
	
}
