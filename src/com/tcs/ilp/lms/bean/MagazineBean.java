package com.tcs.ilp.lms.bean;

public class MagazineBean extends ItemsBean
{
	int magazineId;
	int volumeNo;
	long isbn;
	String subscriptionType;
	
	public MagazineBean(int itemId,String title,int cost,String status,String placeOfPublication,int yearOfPublication, int volumeNo, long isbn, String subscriptionType)
	{
		super(itemId,title,cost,status,placeOfPublication,yearOfPublication);
		this.volumeNo = volumeNo;
		this.isbn = isbn;
		this.subscriptionType = subscriptionType;
	}

	public MagazineBean() {
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

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public int getMagazineId() {
		return magazineId;
	}

	public void setMagazineId(int magazineId) {
		this.magazineId = magazineId;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
}
