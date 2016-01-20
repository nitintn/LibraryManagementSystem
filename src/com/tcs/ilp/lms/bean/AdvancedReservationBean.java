package com.tcs.ilp.lms.bean;

public class AdvancedReservationBean {
	int userId;
	String itemName;
	String authorName;
	String category;
	String userName;
	//String subCategory;
	String reservationDate ;
	int reservationStatus;
	int reservationNumber;
	String Subject;
	int itemId;
	
	
	String itemStatus;
	String publication;
	int volumeNumber;
	long isbn;
	long upc;
	
	/**
	 * @return the reservationStatus
	 */
	public int getReservationStatus() {
		return reservationStatus;
	}
	/**
	 * @param reservationStatus the reservationStatus to set
	 */
	public void setReservationStatus(int reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	/**
	 * @return the itemStatus
	 */
	public String getItemStatus() {
		return itemStatus;
	}
	/**
	 * @param itemStatus the itemStatus to set
	 */
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public int getReservationNumber() {
		return reservationNumber;
	}
	public void setReservationNumber(int reservationNumber) {
		this.reservationNumber = reservationNumber;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	/*public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}*/
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	/**
	 * @return the publication
	 */
	public String getPublication() {
		return publication;
	}
	/**
	 * @param publication the publication to set
	 */
	public void setPublication(String publication) {
		this.publication = publication;
	}
	/**
	 * @return the volumeNumber
	 */
	public int getVolumeNumber() {
		return volumeNumber;
	}
	/**
	 * @param volumeNumber the volumeNumber to set
	 */
	public void setVolumeNumber(int volumeNumber) {
		this.volumeNumber = volumeNumber;
	}
	/**
	 * @return the isbn
	 */
	public long getIsbn() {
		return isbn;
	}
	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	/**
	 * @return the upc
	 */
	public long getUpc() {
		return upc;
	}
	/**
	 * @param upc the upc to set
	 */
	public void setUpc(long upc) {
		this.upc = upc;
	}	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}