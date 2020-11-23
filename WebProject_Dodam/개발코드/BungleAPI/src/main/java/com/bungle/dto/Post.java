package com.bungle.dto;

public class Post {
	private int postNo;
	private String postType;
	private String writeDate;
	private String title;
	private String content;
	private int classNo;
	private int writerNo;
	private int questionNo;
	
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getClassNo() {
		return classNo;
	}
	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}
	public int getWriterNo() {
		return writerNo;
	}
	public void setWriterNo(int writerNo) {
		this.writerNo = writerNo;
	}
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	@Override
	public String toString() {
		return "Post [postNo=" + postNo + ", postType=" + postType + ", writeDate=" + writeDate + ", title=" + title
				+ ", content=" + content + ", classNo=" + classNo + ", writerNo=" + writerNo + ", questionNo="
				+ questionNo + "]";
	}
}
