package com.ssafy.happyhouse.dto;

public class News {
	int newsno;
	String newstitle;
	String newscontent;
	String newsregtime;
	String newspublish;

	public News(int newsno, String newstitle, String newscontent, String newsregtime, String newspublish) {
		super();
		this.newsno = newsno;
		this.newstitle = newstitle;
		this.newscontent = newscontent;
		this.newsregtime = newsregtime;
		this.newspublish = newspublish;
	}

	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "News [newsno=" + newsno + ", newstitle=" + newstitle + ", newscontent=" + newscontent + ", newsregtime="
				+ newsregtime + ", newspublish=" + newspublish + "]";
	}

	public int getNewsno() {
		return newsno;
	}

	public void setNewsno(int newsno) {
		this.newsno = newsno;
	}

	public String getNewstitle() {
		return newstitle;
	}

	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}

	public String getNewscontent() {
		return newscontent;
	}

	public void setNewscontent(String newscontent) {
		this.newscontent = newscontent;
	}

	public String getNewsregtime() {
		return newsregtime;
	}

	public void setNewsregtime(String newsregtime) {
		this.newsregtime = newsregtime;
	}

	public String getNewspublish() {
		return newspublish;
	}

	public void setNewspublish(String newspublish) {
		this.newspublish = newspublish;
	}

}
