package com.ssazit.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BookTagCat {
	private Book			book;		
	private List<Booktag>	booktag;
	private List<Tmptag>	tmptag;
	
	private List<FileDTO>	fileinfo;
	private int				commentCount;

	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public List<FileDTO> getFileinfo() {
		return fileinfo;
	}
	public void setFileinfo(List<FileDTO> fileinfo) {
		this.fileinfo = fileinfo;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public List<Booktag> getBooktag() {
		return booktag;
	}
	public void setBooktag(List<Booktag> booktag) {
		this.booktag = booktag;
	}
	public List<Tmptag> getTmptag() {
		return tmptag;
	}
	public void setTmptag(List<Tmptag> tmptag) {
		this.tmptag = tmptag;
	}
	@Override
	public String toString() {
		return "BookTagCat [book=" + book + ", booktag=" + booktag + ", tmptag=" + tmptag + ", fileinfo=" + fileinfo
				+ ", commentCount=" + commentCount + "]";
	}

}
