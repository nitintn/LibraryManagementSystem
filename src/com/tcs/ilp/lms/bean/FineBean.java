/**
 * 
 */
package com.tcs.ilp.lms.bean;



import java.util.ArrayList;
import java.util.Date;

public class FineBean {
	private int fineid;
	private int transid;
	private Date duedate;
	private double fineamount;
	private String reason;
	private ArrayList<Integer> transList;
	private ArrayList<Date> duedateList;
	private String paydate;
	
	public FineBean()
	{
		this.fineid=(int)(Math.random()*10000);
		
	}
	/**
	 * @return the transList
	 */
	public ArrayList<Integer> getTransList() {
		return transList;
	}
	/**
	 * @param transList the transList to set
	 */
	public void setTransList(ArrayList<Integer> transList) {
		this.transList = transList;
	}
	/**
	 * @return the duedateList
	 */
	public ArrayList<Date> getDuedateList() {
		return duedateList;
	}
	/**
	 * @param duedateList the duedateList to set
	 */
	public void setDuedateList(ArrayList<Date> duedateList) {
		this.duedateList = duedateList;
	}
	/**
	 * @return the fineid
	 */
	public int getFineid() {
		return fineid;
	}
	/**
	 * @param fineid the fineid to set
	 */
	public void setFineid(int fineid) {
		this.fineid = fineid;
	}
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
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the paydate
	 */
	public String getPaydate() {
		return paydate;
	}
	/**
	 * @param paydate the paydate to set
	 */
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public Date getDuedate() {
		return duedate;
	}
	
	

}
