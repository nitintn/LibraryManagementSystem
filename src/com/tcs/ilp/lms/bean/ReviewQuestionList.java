package com.tcs.ilp.lms.bean;

import java.util.List;

public class ReviewQuestionList {
	List <ReviewQuestion> questionListBook;
	List <ReviewQuestion> questionListCd;
	List <ReviewQuestion> questionListMagazine;
	List <ReviewQuestion> questionListJournal;
	
	public ReviewQuestionList() {
		super();
	}
	public ReviewQuestionList(List<ReviewQuestion> questionListBook,
			List<ReviewQuestion> questionListCd,
			List<ReviewQuestion> questionListMagazine,
			List<ReviewQuestion> questionListJournal) {
		super();
		this.questionListBook = questionListBook;
		this.questionListCd = questionListCd;
		this.questionListMagazine = questionListMagazine;
		this.questionListJournal = questionListJournal;
	}
	public List<ReviewQuestion> getQuestionListBook() {
		return questionListBook;
	}
	public void setQuestionListBook(List<ReviewQuestion> questionListBook) {
		this.questionListBook = questionListBook;
	}
	public List<ReviewQuestion> getQuestionListCd() {
		return questionListCd;
	}
	public void setQuestionListCd(List<ReviewQuestion> questionListCd) {
		this.questionListCd = questionListCd;
	}
	public List<ReviewQuestion> getQuestionListMagazine() {
		return questionListMagazine;
	}
	public void setQuestionListMagazine(List<ReviewQuestion> questionListMagazine) {
		this.questionListMagazine = questionListMagazine;
	}
	public List<ReviewQuestion> getQuestionListJournal() {
		return questionListJournal;
	}
	public void setQuestionListJournal(List<ReviewQuestion> questionListJournal) {
		this.questionListJournal = questionListJournal;
	}
	
	
}
