package com.tcs.ilp.lms.bean;

public class RackBean {
	String rackNo;
	String shelfNo;
	String itemNo;
	String itemID;
	String category;
	String subCategory;
	String rsiID;
	
	public String getRsiID() {
		return rsiID;
	}
	public void setRsiID(String rsiID) {
		this.rsiID = rsiID;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRackNo() {
		return rackNo;
	}
	public void setRackNo(String rackNo) {
		this.rackNo = rackNo;
	}
	public String getShelfNo() {
		return shelfNo;
	}
	public void setShelfNo(String shelfNo) {
		this.shelfNo = shelfNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	@Override
	  public String toString() {
	   return "RackBean [rsiID=" + rsiID + ", itemID=" + itemID +"]";
	  }
	  


}
