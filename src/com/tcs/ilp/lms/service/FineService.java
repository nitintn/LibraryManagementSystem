package com.tcs.ilp.lms.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.tcs.ilp.lms.bean.Defaultersbean;
import com.tcs.ilp.lms.bean.FineBean;

import com.tcs.ilp.lms.bean.SearchBean;
import com.tcs.ilp.lms.dao.BookDao;
import com.tcs.ilp.lms.dao.FineDao;
import com.tcs.ilp.lms.dao.TransactionDAO;
import com.tcs.ilp.lms.utility.Constants;

/**
 * This class have all the CRUD operation method implementations 
 * like addFineReason()
 * searchDefaulters()
 * Payment()
 * updateItemStatus()
 * updateDefaultersList()
 * calculateFine()
 */

public class FineService {
	
	/**
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @param reasonbean
	 * @return void
	 */
	public void addFineReason(FineBean fbean )
	{ 	String status=Constants.Paid;
		System.out.println("in addfine reason"+fbean.getReason());
		FineDao finedao = new FineDao();
		finedao.addFineReason(fbean);
		updateItemStatus(fbean.getTransid(),status);
	
		
	}
	/**
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @param memberid,category
	 * @return ArrayList<Defaultersbean>
	 */
	
	public	ArrayList<Defaultersbean> searchDefaulters()
	{
		FineDao dao = new FineDao();
	
		ArrayList<Defaultersbean> defaulterlist= dao.search();
		for(Defaultersbean dbean:defaulterlist)
		{
			double fineamount=calculateFine(dbean.getTransid(), dbean.getDuedate());
			dbean.setFineamount(fineamount);
		}
		
		System.out.println(defaulterlist.size());
		
		return defaulterlist;
	}
	/**
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @param memberid,category
	 * @return ArrayList<Defaultersbean>
	 */
	
	public	ArrayList<Defaultersbean> searchDefaulters(int memId,String cat)
	{
		FineDao dao = new FineDao();
		System.out.println("memberid going in"+memId);
		System.out.println("memberid going in"+cat);
		ArrayList<Defaultersbean> defaulterlist= dao.search(memId,cat);
		for(Defaultersbean dbean:defaulterlist)
		{
			double fineamount=calculateFine(dbean.getTransid(), dbean.getDuedate());
			dbean.setFineamount(fineamount);
		}
		
		System.out.println(defaulterlist.size());
		
		return defaulterlist;
	}
	
	public	ArrayList<Defaultersbean> searchDefaulters1(int memId,String cat,ArrayList<Integer> transIds)
	{
		System.out.println("member id is "+memId);
		System.out.println("category"+cat);
		FineDao dao = new FineDao();
		ArrayList<Defaultersbean> defaulterlist= dao.search1(memId, cat, transIds);
		for(Defaultersbean dbean:defaulterlist)
		{
			double fineamount=calculateFine(dbean.getTransid(), dbean.getDuedate());
			dbean.setFineamount(fineamount);
		}
		
		System.out.println(defaulterlist.size());
		
		return defaulterlist;
	}
	public	ArrayList<Defaultersbean> searchDefaulters2(int memId,ArrayList<Integer> transIds)
	{
		System.out.println("member id is "+memId);
		FineDao dao = new FineDao();
		ArrayList<Defaultersbean> defaulterlist= dao.search2(memId,transIds);
		for(Defaultersbean dbean:defaulterlist)
		{
			double fineamount=calculateFine(dbean.getTransid(), dbean.getDuedate());
			dbean.setFineamount(fineamount);
		}
		
		System.out.println(defaulterlist.size());
		
		return defaulterlist;
	}
	/**
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @param transaction id
	 * @return void
	 */
	
	public void Payment(int transid)
	
	{	
		System.out.println("in payment");
		String itemstatus= Constants.RP; String defaulterstatus=Constants.Paid;
		LibrarianService libService=new LibrarianService();
		libService.updateStatus(transid);
		updateItemStatus( transid,itemstatus);
		updateDefaultersList(transid, defaulterstatus);
	
	}
	/**
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @param transaction id
	 * @return void
	 */
	
	
	public void cancelFine(int transid)
	{
		System.out.println("in cancel");
		String itemstatus= Constants.RP; 
		String defaulterstatus=Constants.CANCEL;
		LibrarianService libService=new LibrarianService();
		libService.updateStatus(transid);
		updateItemStatus( transid,itemstatus);
		updateDefaultersList(transid, defaulterstatus);	
	
	}
	/**
	 * @Exception none
	 * @sinceJDK 1.6.0_22
	 * @param transaction id and status
	 * @return void
	 */
	public void updateItemStatus(int transid,String status)
	{
		FineDao dao = new FineDao();
		dao.updateItemStatus(transid, status);
		
	}
	/**
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @param transaction id and status
	 * @return void
	 */
	public void updateDefaultersList(int transid,String status)
	{
		FineDao dao = new FineDao();
		dao.updatedefaulterslist( transid, status);
	}
	/**
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @param Fine bean object
	 * @return void
	 */
	
	public void addFineDetails(FineBean finebean)
	{  java.util.Date date = new Date();
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		String dateString=df.format(date);
		finebean.setPaydate(dateString);
		System.out.println("Fineid:"+finebean.getFineid());
		System.out.println("FineAmount"+finebean.getFineamount());
		System.out.println("TransactionId"+finebean.getTransid());
		System.out.println("paydate"+finebean.getPaydate());
		FineDao dao= new FineDao();
		dao.addFineDetails(finebean);
	}
	/**
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @param transaction id, due date
	 * @return double
	 */
	
	public double calculateFine(int transid,Date duedate )
	{ 
		
			long oneday=(24*60*60*1000);
		double fineamount=50;
		Date currentdate = new Date();
		long difference=(currentdate.getTime()-duedate.getTime());
		int noOfDays =(int)(difference/oneday);
		System.out.println("no of days:"+noOfDays+" "+transid);
		if(noOfDays==1)
		{
			return fineamount;
		}else if(noOfDays>0&&noOfDays<15)
		{
			fineamount+=(noOfDays*10);
			return fineamount;
		}else if(noOfDays>=15)
		{ int c=1;int diff=0;
		while(noOfDays>15){
			noOfDays= noOfDays-15;
				++c;
			}
			
			fineamount+=((c*100)+(noOfDays*10));
			return fineamount;
		}else{return 0;}
		
	
	}
	/**
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @param memberid,category
	 * @return ArrayList<Defaultersbean>
	 */
	
	public	ArrayList<Defaultersbean> searchDefaulters(SearchBean sbean)
	{
		FineDao dao = new FineDao();
	
		ArrayList<Defaultersbean> defaulterlist= dao.search(sbean);
		for(Defaultersbean dbean:defaulterlist)
		{
			double fineamount=calculateFine(dbean.getTransid(), dbean.getDuedate());
			dbean.setFineamount(fineamount);
		}
		
		System.out.println(defaulterlist.size());
		
		return defaulterlist;
	}

}
