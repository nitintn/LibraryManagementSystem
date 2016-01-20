package com.tcs.ilp.lms.bean;

public class CdBean extends ItemsBean 
{
	int cdId;
	String subject;
	String publisher;
	
	public CdBean(int itemId,String title,int cost,String status,String subject,String placeOfPublication,String publisher,int yearOfPublication)
	{
		super(itemId,title,cost,status,placeOfPublication,yearOfPublication);
		this.subject = subject;
		this.publisher = publisher;
	}
	
	public CdBean() {
		// TODO Auto-generated constructor stub
	}

	public int getCdId()
	{
		return cdId;
	}
	
	public void setCdId(int cdId)
	{
		this.cdId = cdId;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}	
}
