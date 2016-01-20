package com.tcs.ilp.lms.bean;

public class JournalBean extends ItemsBean
{
	int journalId;
	int volumeNo;
	long isbn;
	String subscriptionType;
	
	public JournalBean(int itemId,String title,int cost,String status,String placeOfPublication,int yearOfPublication, int volumeNo, long isbn, String subscriptionType)
	{
		super(itemId,title,cost,status,placeOfPublication,yearOfPublication);
		this.volumeNo = volumeNo;
		this.isbn = isbn;
		this.subscriptionType = subscriptionType;
	}

	public JournalBean() {
		// TODO Auto-generated constructor stub
	}

	public int getVolumeNo() {
		return volumeNo;
	}

	public void setVolumeNo(int volumeNo) {
		this.volumeNo = volumeNo;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public int getJournalId() {
		return journalId;
	}

	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
}
