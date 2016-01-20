package com.tcs.ilp.lms.bean;

public class BookBean extends ItemsBean
{
	private int bookId;
	private String author;
	private long isbn;
	private String subject;
	private int edition;
	private String editor;
	private String publisher;
	
	public BookBean(int itemId,String title,int cost,String status,String subject,int edition,String author,long isbn,String placeOfPublication,String publisher,int yearOfPublication,String editor)
	{
		super(itemId,title,cost,status,placeOfPublication,yearOfPublication);
		this.subject = subject;
		this.editor = editor;
		this.edition = edition;
		this.author = author;
		this.isbn = isbn;
		this.publisher = publisher;	
	}

	public BookBean() {
		// TODO Auto-generated constructor stub
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
