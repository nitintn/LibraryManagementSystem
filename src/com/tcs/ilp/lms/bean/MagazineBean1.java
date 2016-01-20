package com.tcs.ilp.lms.bean;

import java.util.Date;





public class MagazineBean1 {
private int magazineId;
private int memId;
private Date dueDate;
private String magazineName;
private Date issuedDate;
private Date returnDate;
private int cost;
private int itemId;
public int getItemId() {
	return itemId;
}
public void setItemId(int itemId) {
	this.itemId = itemId;
}
private String memberName;
private int txnId;
public String getMemberName() {
	return memberName;
}
public void setMemberName(String memberName) {
	this.memberName = memberName;
}
public int getTxnId() {
	return txnId;
}
public void setTxnId(int txnId) {
	this.txnId = txnId;
}
public int getMagazineId() {
	return magazineId;
}
public void setMagazineId(int magazineId) {
	this.magazineId = magazineId;
}
public int getMemId() {
	return memId;
}
public void setMemId(int memId) {
	this.memId = memId;
}
public Date getDueDate() {
	return dueDate;
}
public void setDueDate(Date dueDate) {
	this.dueDate = dueDate;
}
public String getMagazineName() {
	return magazineName;
}
public void setMagazineName(String magazineName) {
	this.magazineName = magazineName;
}
public Date getIssuedDate() {
	return issuedDate;
}
public void setIssuedDate(Date issuedDate) {
	this.issuedDate = issuedDate;
}
public Date getReturnDate() {
	return returnDate;
}
public void setReturnDate(Date returnDate) {
	this.returnDate = returnDate;
}
public int getCost() {
	return cost;
}
public void setCost(int cost) {
	this.cost = cost;
}


}
