package com.tcs.ilp.lms.bean;

import java.util.List;
import java.util.logging.Logger;

public class Review {
	private int reviewId;
	private int memberId;
	private String title;
	private String comments;
	private int finalRating;
	private int[] answerList;
	public int[] getAnswerList() {
		return answerList;
	}

	public void setAnswerList(int[] answerList) {
		this.answerList = answerList;
	}
	private static Logger logger=Logger.getLogger("reviewservlet");
	public Review() {
		super();
		this.answerList=new int[4];
	}
	
	public Review(int reviewId, int memberId, String title,
			String comments, int finalRating,
			ReviewQuestionList reviewQuestions, List<String> answerList) {
		super();
		this.reviewId = reviewId;
		this.memberId = memberId;
		this.title = title;
		this.comments = comments;
		this.finalRating = finalRating;
	}

	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getFinalRating() {
		return finalRating;
	}
	public void setFinalRating(int finalRating) {
		this.finalRating = finalRating;
	}
	
}
