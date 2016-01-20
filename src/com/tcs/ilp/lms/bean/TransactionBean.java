/**
 * to store transaction details
 */
package com.tcs.ilp.lms.bean;

/**
 * @author 737978
 *
 */
public class TransactionBean 
{
	private int transactionId;
	private int itemId;
	private int memId;
	private String issueDate;
	private String returnDate;
	private String renewalDate;
	private String dueDate;
	private String status;
	private int staffId;
	private String itemTitle;
	private String itemType;
	private String memberName;
	
	
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getItemId() {
		return itemId;
	}

	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	/**
	 * @return the itemTitle
	 */
	public String getItemTitle() {
		return itemTitle;
	}

	/**
	 * @param itemTitle the itemTitle to set
	 */
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	/**
	 * @return the staffId
	 */
	public int getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the isbn
	 */
	

	/**
	 * default constructor
	 */
	public TransactionBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the transactionId
	 */
	public int getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the bookId
	 */
	public int getitemId() {
		return itemId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setItemId(int bookId) {
		this.itemId = bookId;
	}

	/**
	 * @return the memId
	 */
	public int getMemId() {
		return memId;
	}

	/**
	 * @param memId the memId to set
	 */
	public void setMemId(int memId) {
		this.memId = memId;
	}

	/**
	 * @return the issueDate
	 */
	public String getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @return the returnDate
	 */
	public String getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * @return the renewalDate
	 */
	public String getRenewalDate() {
		return renewalDate;
	}

	/**
	 * @param renewalDate the renewalDate to set
	 */
	public void setRenewalDate(String renewalDate) {
		this.renewalDate = renewalDate;
	}

	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
