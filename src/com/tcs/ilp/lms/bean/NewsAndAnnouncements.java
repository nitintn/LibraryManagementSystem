package com.tcs.ilp.lms.bean;

import java.util.Date;

public class NewsAndAnnouncements {
	int newsId;
	int userId;

	String news;
	String date;
	String title;
	
	public NewsAndAnnouncements() {
		super();
	}
	
	public NewsAndAnnouncements(int newsId, String news, String date,
			String title) {
		super();
		this.newsId = newsId;
		this.news = news;
		this.date = date;
		this.title = title;
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return this.date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
}
