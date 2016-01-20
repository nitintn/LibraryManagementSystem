package com.tcs.ilp.lms.bean;

public class ItemsBean 
{
	int itemId;
	String title;
	String placeOfPublication;
	int yearOfPublication;
	float cost;
	String status;	
	
	public ItemsBean()
	{
		
	}
	
	public ItemsBean(int itemId,String title,float cost,String status,String placeOfPublication,int yearOfPublication)
	{
		this.itemId = itemId;
		this.title = title;
		this.cost = cost;
		this.status = status;
		this.placeOfPublication = placeOfPublication;
		this.yearOfPublication = yearOfPublication;
	}

	public int getItemId() 
	{
		return itemId;
	}

	public void setItemId(int itemId) 
	{
		this.itemId = itemId;
	}

	public String getPlaceOfPublication() {
		return placeOfPublication;
	}

	public void setPlaceOfPublication(String placeOfPublication) {
		this.placeOfPublication = placeOfPublication;
	}

	public int getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(int yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
