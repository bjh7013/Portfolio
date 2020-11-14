package com.ssafy.happyhouse.dto;

public class FAQ {
	int faqno;
	String faqtitle;
	String faqcontent;
	String faqtype;
	@Override
	public String toString() {
		return "FAQ [faqno=" + faqno + ", faqtitle=" + faqtitle + ", faqcontent=" + faqcontent + ", faqtype=" + faqtype
				+ "]";
	}
	public FAQ() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FAQ(int faqno, String faqtitle, String faqcontent, String faqtype) {
		super();
		this.faqno = faqno;
		this.faqtitle = faqtitle;
		this.faqcontent = faqcontent;
		this.faqtype = faqtype;
	}
	public int getFaqno() {
		return faqno;
	}
	public void setFaqno(int faqno) {
		this.faqno = faqno;
	}
	public String getFaqtitle() {
		return faqtitle;
	}
	public void setFaqtitle(String faqtitle) {
		this.faqtitle = faqtitle;
	}
	public String getFaqcontent() {
		return faqcontent;
	}
	public void setFaqcontent(String faqcontent) {
		this.faqcontent = faqcontent;
	}
	public String getFaqtype() {
		return faqtype;
	}
	public void setFaqtype(String faqtype) {
		this.faqtype = faqtype;
	}

	

}
