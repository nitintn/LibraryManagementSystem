package com.tcs.ilp.lms.bean;

public class ReviewQuestion {
	String questionId;
	String question;
	String weightage;
	String rating;
	
	public ReviewQuestion(String questionId, String question, String weightage,
			String rating) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.weightage = weightage;
		this.rating = rating;
	}
	
	public ReviewQuestion() {
		super();
	}

	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getWeightage() {
		return weightage;
	}
	public void setWeightage(String weightage) {
		this.weightage = weightage;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
}
