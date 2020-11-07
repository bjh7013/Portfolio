package com.ssazit.dto;

import java.util.List;

public class ArticleTagCat {
	private Article		 		article;
	private List<Arttag> 		arttag;
	private List<Tmptag> 		tmpTag;
	private Acategory			acategory; //사용자 정의 acategory 등록용
	
	
	public Acategory getAcategory() {
		return acategory;
	}
	public void setAcategory(Acategory acategory) {
		this.acategory = acategory;
	}
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public List<Arttag> getArttag() {
		return arttag;
	}
	public void setArttag(List<Arttag> arttag) {
		this.arttag = arttag;
	}
	public List<Tmptag> getTmpTag() {
		return tmpTag;
	}
	public void setTmpTag(List<Tmptag> tmpTag) {
		this.tmpTag = tmpTag;
	}
	@Override
	public String toString() {
		return "ArticleTagCat [article=" + article + ", arttag=" + arttag + ", tmpTag=" + tmpTag + ", acategory="
				+ acategory + "]";
	}
	
}
