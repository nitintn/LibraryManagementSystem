package com.tcs.ilp.lms.bean;

import java.util.Date;

public class Defaultersbean {
	
	private int transid;
	private int memid;
	private String title;
	private String cat;
	private Date duedate;
	private double fineamount;
	/**
	 * @return the transid
	 */
	public int getTransid() {
		return transid;
	}
	/**
	 * @param transid the transid to set
	 */
	public void setTransid(int transid) {
		this.transid = transid;
	}
	/**
	 * @return the memid
	 */
	public int getMemid() {
		return memid;
	}
	/**
	 * @param memid the memid to set
	 */
	public void setMemid(int memid) {
		this.memid = memid;
	}
	/**
	 * @return the cat
	 */
	public String getCat() {
		return cat;
	}
	/**
	 * @param cat the cat to set
	 */
	public void setCat(String cat) {
		this.cat = cat;
	}
	/**
	 * @return the duedate
	 */
	public Date getDuedate() {
		return duedate;
	}
	/**
	 * @param duedate the duedate to set
	 */
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	/**
	 * @return the fineamount
	 */
	public double getFineamount() {
		return fineamount;
	}
	/**
	 * @param fineamount the fineamount to set
	 */
	public void setFineamount(double fineamount) {
		this.fineamount = fineamount;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	

}
