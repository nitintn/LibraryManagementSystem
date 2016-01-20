package com.tcs.ilp.lms.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.tcs.ilp.lms.bean.AdvancedReservationBean;
import com.tcs.ilp.lms.bean.BookBean;
import com.tcs.ilp.lms.bean.BookBean1;
import com.tcs.ilp.lms.bean.CdBean;
import com.tcs.ilp.lms.bean.CdBean1;
import com.tcs.ilp.lms.bean.JournalBean;
import com.tcs.ilp.lms.bean.JournalBean1;
import com.tcs.ilp.lms.bean.MagazineBean;
import com.tcs.ilp.lms.bean.MagazineBean1;
import com.tcs.ilp.lms.bean.MasterProfileBean;
import com.tcs.ilp.lms.bean.MemberBean;
import com.tcs.ilp.lms.bean.MemberBean1;
import com.tcs.ilp.lms.bean.NewsAndAnnouncements;
import com.tcs.ilp.lms.bean.Review;
import com.tcs.ilp.lms.bean.TransactionBean;
import com.tcs.ilp.lms.dao.AdminDao;
import com.tcs.ilp.lms.dao.AdvancedReservationDao;
import com.tcs.ilp.lms.dao.BookDao;
import com.tcs.ilp.lms.dao.BookTransactionDao;
import com.tcs.ilp.lms.dao.CdDao;
import com.tcs.ilp.lms.dao.JournalDao;
import com.tcs.ilp.lms.dao.MagazineDao;
import com.tcs.ilp.lms.dao.MasterProfileDao;
import com.tcs.ilp.lms.dao.MemberDao;
import com.tcs.ilp.lms.dao.NewsDAO;
import com.tcs.ilp.lms.dao.ReviewDAO;
import com.tcs.ilp.lms.dao.TransactionDAO;

public class MemberService 
{
	private static Logger logger=Logger.getLogger("reviewservlet");
	public int registration(MemberBean memberBean)
	{
		int result = 0;
		MemberDao memberDao = new MemberDao();
		result = memberDao.register(memberBean);
		return result;
	}
	
	public ArrayList<String> getMasterProfileList(int memId)
	{
		ArrayList<String> masterList = new ArrayList<String>();
		MemberDao memberDao = new MemberDao();
		masterList = memberDao.getMasterList(memId);
		return masterList;
	}

	public long getFees(String memType,int memberId) 
	{
		// TODO Auto-generated method stub
		MemberDao memberDao = new MemberDao();
		long feesOne = memberDao.getFeesOne(memType,memberId);
		long feesTwo = memberDao.getFeesTwo(memType,memberId);		
		return (feesOne-feesTwo);
	}
	
	public ArrayList<String> getMasterList()
	{
		ArrayList<String> masterList = new ArrayList<String>();
		MasterProfileDao masterProfileDao = new MasterProfileDao();
		masterList = masterProfileDao.getList();
		return masterList;
	}
	
	public List<TransactionBean> renewBook(MemberBean1 mB)
	{
		TransactionDAO tDao= new TransactionDAO();
		List<TransactionBean> tList= new ArrayList<TransactionBean>();
		tList= tDao.getTransactionDetails(mB.getMemId());
		if(tList!=null)
		{
			Iterator<TransactionBean> itr= tList.iterator();
			while(itr.hasNext())
			{
				System.out.println(itr.next().getDueDate());
			}
			return tList;
		}
		else
		{
			return null;
		}
		
	}
	
	public TransactionBean renewUpdate(int transId)
	{
		TransactionDAO tDao= new TransactionDAO();
		TransactionBean tBn=tDao.updateStatusRenewed(transId);
		return tBn;
	}
	
	public void reportLostStolen( )
	{
		System.out.println("your report has been submitted");
	}
	
	public List<BookBean1> viewLost(int memId)
	{
		List<BookBean1> bookList=new ArrayList<BookBean1>();
		BookDao bk= new BookDao();
		bookList=bk.viewLost(memId);
		System.out.println("size of lost List in service class after the BookDao "+bookList.size());
		//System.out.println(bookList.get(1).getBookName());
		System.out.println("in member service");
		return bookList;
	}
	
	public List<CdBean1> viewLostCD(int memId){
		List<CdBean1> cdList=new ArrayList<CdBean1>();
		CdDao bk= new CdDao();
		cdList=bk.viewLostCD(memId);
		System.out.println("size of lost List in service after the CdDao "+cdList.size());
		//System.out.println(bookList.get(1).getBookName());
		System.out.println("in member service");
		return cdList;
	}
	
	public List<MagazineBean1> viewLostMag(int memId){
		List<MagazineBean1> magList=new ArrayList<MagazineBean1>();
		MagazineDao bk= new MagazineDao();
		magList=bk.viewLostMag(memId);
		System.out.println("size of lost List in service after the magDao "+magList.size());
		//System.out.println(bookList.get(1).getBookName());
		System.out.println("in member service");
		return magList;
	}
	
	public List<JournalBean1> viewLostJournal(int memId){
		List<JournalBean1> journalList=new ArrayList<JournalBean1>();
		JournalDao bk= new JournalDao();
		journalList=bk.viewLostJournal(memId);
		System.out.println("size of lost List in service after the JournalDao "+journalList.size());
		//System.out.println(bookList.get(1).getBookName());
		System.out.println("in member service");
		return journalList;
	}
	
	public void cancelReport(int bookId)
	{
		BookDao bk=new BookDao();
		bk.cancelReport(bookId);
		System.out.println("your report has been cancelled");
	}
	
	public void cancelReportCd(int cdId)
	{
		CdDao bk=new CdDao();
		bk.cancelReportCd(cdId);
		System.out.println("your report has been cancelled");
	}
	
	public void cancelReportMagazine(int magId)
	{
		MagazineDao bk=new MagazineDao();
		bk.cancelReportMagazine(magId);
		System.out.println("your report has been cancelled");
	}
	
	public void cancelReportJournal(int journalId)
	{
		JournalDao bk=new JournalDao();
		bk.cancelReportJournal(journalId);
		System.out.println("your report has been cancelled");
	}
	
	public List<BookBean1> viewBookList(int memId)
	{
		List<BookBean1> bookList=new ArrayList<BookBean1>();
		BookDao bk= new BookDao();
		bookList=bk.viewBookList(memId);
		return bookList;
	}
	
	public List<CdBean1> viewCdList(int memId)
	{
		 List<CdBean1> cdList=new ArrayList<CdBean1>();
		CdDao bk= new CdDao();
		cdList=bk.viewCdList(memId);
		return cdList;
	}
	
	public void updateStatus(int bookId)
	{
		BookDao bk= new BookDao();
		bk.updateStatus(bookId);
	}
	
	public void updateStatusCd(int cdId)
	{
		CdDao bk= new CdDao();
		bk.updateStatusCd(cdId);
	}
	
	public void updateStatusMag(int magId)
	{
		MagazineDao bk= new MagazineDao();
		bk.updateStatusMag(magId);
	}
	
	public void updateStatusJournal(int journalId)
	{
		JournalDao bk= new JournalDao();
		bk.updateStatusJournal(journalId);
	}
	
	public String getDueDate(String memberId) throws ParseException{
		BookTransactionDao  bt=new BookTransactionDao();
		String duedate=bt.getDueDate(memberId);
		return duedate;
	}
	
	public List<JournalBean1> viewJournalList(int id) {
	// TODO Auto-generated method stub
	List<JournalBean1> journalList=new ArrayList<JournalBean1>();
	JournalDao bk= new JournalDao();
	journalList=bk.viewJournalList(id);
	return journalList;
	}

	public List<MagazineBean1> viewMagazineList(int memId) {
	// TODO Auto-generated method stub
	List<MagazineBean1> magList=new ArrayList<MagazineBean1>();
	MagazineDao bk= new MagazineDao();
	magList=bk.viewMagazineList(memId);
	return magList;
	}

	public ArrayList<AdvancedReservationBean> searchItem(AdvancedReservationBean AdvancedReservationBeanObj)
	{
	AdvancedReservationDao AdvancedReservationDaoObj = new AdvancedReservationDao();
	ArrayList<AdvancedReservationBean> advancedReservationSearchList = new ArrayList<AdvancedReservationBean>();
	advancedReservationSearchList = AdvancedReservationDaoObj.searchItem(AdvancedReservationBeanObj);
	return advancedReservationSearchList;	
	}

	public int advancedReservation(AdvancedReservationBean AdvancedReservationBeanObj)
	{
	int reservationNo=0;
	System.out.println(AdvancedReservationBeanObj.getUserId()+" "+AdvancedReservationBeanObj.getItemName());
	AdvancedReservationDao AdvancedReservationDaoObj = new AdvancedReservationDao();
	reservationNo=AdvancedReservationDaoObj.advancedReservation(AdvancedReservationBeanObj);
	return reservationNo;
	}
	
	public ArrayList<NewsAndAnnouncements> viewNews(int memberId) throws ClassNotFoundException, SQLException{
		NewsDAO newsDao=new NewsDAO();
		ArrayList<NewsAndAnnouncements> newsList=new ArrayList<NewsAndAnnouncements>();
		newsList=newsDao.getNewsList(memberId);	
		return newsList;
	}
	
	public int addReview(Review review){
		String title=review.getTitle();
		int memberId=review.getMemberId();
		int status=0;
		int count=0;
		ReviewDAO reviewDao=new ReviewDAO();
		try {
			count=reviewDao.countTransIdFromDb(title, memberId);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(count>0){
			status=1;
			try {
				reviewDao.insertReview(review);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return status;
	}
	public ArrayList<Review> viewReview(){
		ArrayList<Review> reviewList=new ArrayList<Review>();
		ReviewDAO reviewDao=new ReviewDAO();
		try {
			reviewList=reviewDao.getReview();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviewList;
	}
	public ArrayList<Review> viewAllReview(String title){
		System.out.println("all service");
		ArrayList<Review> reviewList=new ArrayList<Review>();
		ArrayList<Review> newReviewList=new ArrayList<Review>();
		ReviewDAO reviewDao=new ReviewDAO();
		try {
			reviewList=reviewDao.getReview();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Review r:reviewList){
			if(r.getTitle().equalsIgnoreCase(title)){
				newReviewList.add(r);
			}
		}
		return newReviewList;
	}
	public ArrayList<Review> viewMyReview(int memberId,String title){
		ArrayList<Review> reviewList=new ArrayList<Review>();
		ArrayList<Review> myReviewList=new ArrayList<Review>();
		ReviewDAO reviewDao=new ReviewDAO();
		try {
			reviewList=reviewDao.getReview();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Review r:reviewList){
			if((r.getMemberId()==memberId)&&(r.getTitle().equalsIgnoreCase(title))){
				myReviewList.add(r);
			}
		}
		return myReviewList;
	}
	public int updateReview(Review review) throws ClassNotFoundException, SQLException{
		ReviewDAO reviewDao=new ReviewDAO();
		int reviewId=review.getReviewId();
		System.out.println(reviewId);
		String comments=review.getComments();
		System.out.println(comments);
		System.out.println("service");
		int i=reviewDao.updateReview(review);
		return i;
	}
	public ArrayList<Review> viewTitleOnTitle(int memberId, String title){
		ReviewDAO reviewDao=new ReviewDAO();
		ArrayList<Review> reviewList=new ArrayList<Review>();
		try {
			reviewList=reviewDao.getListUsingTitleAndMemberId(title, memberId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviewList;
	}
	public ArrayList<Review> viewTitleOnAuthor(int memberId, String author){
		ReviewDAO reviewDao=new ReviewDAO();
		ArrayList<Review> reviewList=new ArrayList<Review>();
		try {
			reviewList=reviewDao.getListUsingAuthor(author, memberId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviewList;
	}
	public int deleteReview(Review review){
		ReviewDAO reviewDao=new ReviewDAO();
		int status=0;
		try {
			status=reviewDao.deleteReview(review.getReviewId());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	public int calculateRating(int[] answers){
		int finalRating=0,sum=0;
		for(int i=0;i<4;i++){
			sum+=answers[i];
		}
		finalRating=(sum*5)/16;
		return finalRating;
	}
	public int getReviewId(String title){
		ReviewDAO reviewDao=new ReviewDAO();
		ArrayList<Review> reviewList=new ArrayList<Review>();
		int reviewId=0;
		try {
			reviewList=reviewDao.getReview();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Review r:reviewList){
			if(r.getTitle().equalsIgnoreCase(title)){
				reviewId=r.getReviewId();
				break;
			}
		}
		return reviewId;
	}

	public boolean checkAvailabilityOfUserId(int userIdInt) 
	{
		// TODO Auto-generated method stub
		boolean userIdAvailable = false;
		MemberDao memberDao = new MemberDao();
		userIdAvailable = memberDao.checkAvailabilityOfUserId(userIdInt);
		return userIdAvailable;
	}
	
	public boolean checkAvailabilityOfIdentityId(String identityId) 
	{
		// TODO Auto-generated method stub
		boolean userIdAvailable = false;
		MemberDao memberDao = new MemberDao();
		userIdAvailable = memberDao.checkAvailabilityOfIdentityId(identityId);
		return userIdAvailable;
	}
	
	public ArrayList<BookBean> getBookList(int displayStart, String sortDir,
			int sortCol, int displayLength) {
		// TODO Auto-generated method stub
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<BookBean> BookList= new ArrayList<BookBean>();
		BookDao bookDao = new BookDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY BOOK.ITEMID ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;

			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.TITLE ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY BOOK.AUTHOR ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT ITEM.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY BOOK.SUBJECT ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY BOOK.EDITION ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==5)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.COST ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY BOOK.ITEMID DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.TITLE DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY BOOK.AUTHOR DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY BOOK.SUBJECT DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY BOOK.EDITION DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==5)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.COST DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
						}
		}
		BookList = bookDao.getBookList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		return BookList;	
	}

	public int getBookCount() {
		// TODO Auto-generated method stub
		int result = 0;
		BookDao bookDao = new BookDao();
		result = bookDao.getBookCount();
		return result;
	}

	public ArrayList<BookBean> getBookListSearch(int displayStart,
			String sortDir, int sortCol, int displayLength, String search) {
		// TODO Auto-generated method stub
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<BookBean> BookList= new ArrayList<BookBean>();
		BookDao bookDao = new BookDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (BOOK.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR BOOK.AUTHOR LIKE '%"+search+"%' OR BOOK.SUBJECT LIKE '%"+search+"%' OR BOOK.EDITION LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY BOOK.ITEMID ASC)m) where r between "+displayStart+" and "+displayEnd;

			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (BOOK.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR BOOK.AUTHOR LIKE '%"+search+"%' OR BOOK.SUBJECT LIKE '%"+search+"%' OR BOOK.EDITION LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.TITLE ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (BOOK.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR BOOK.AUTHOR LIKE '%"+search+"%' OR BOOK.SUBJECT LIKE '%"+search+"%' OR BOOK.EDITION LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY BOOK.AUTHOR ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (BOOK.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR BOOK.AUTHOR LIKE '%"+search+"%' OR BOOK.SUBJECT LIKE '%"+search+"%' OR BOOK.EDITION LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY BOOK.SUBJECT ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (BOOK.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR BOOK.AUTHOR LIKE '%"+search+"%' OR BOOK.SUBJECT LIKE '%"+search+"%' OR BOOK.EDITION LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY BOOK.EDITION ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==5)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (BOOK.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR BOOK.AUTHOR LIKE '%"+search+"%' OR BOOK.SUBJECT LIKE '%"+search+"%' OR BOOK.EDITION LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.COST ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (BOOK.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR BOOK.AUTHOR LIKE '%"+search+"%' OR BOOK.SUBJECT LIKE '%"+search+"%' OR BOOK.EDITION LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY BOOK.ITEMID DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (BOOK.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR BOOK.AUTHOR LIKE '%"+search+"%' OR BOOK.SUBJECT LIKE '%"+search+"%' OR BOOK.EDITION LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.TITLE DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (BOOK.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR BOOK.AUTHOR LIKE '%"+search+"%' OR BOOK.SUBJECT LIKE '%"+search+"%' OR BOOK.EDITION LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY BOOK.AUTHOR DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (BOOK.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR BOOK.AUTHOR LIKE '%"+search+"%' OR BOOK.SUBJECT LIKE '%"+search+"%' OR BOOK.EDITION LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY BOOK.SUBJECT DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (BOOK.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR BOOK.AUTHOR LIKE '%"+search+"%' OR BOOK.SUBJECT LIKE '%"+search+"%' OR BOOK.EDITION LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY BOOK.EDITION DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==5)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT BOOK.ITEMID,ITEM.TITLE,BOOK.AUTHOR,BOOK.SUBJECT,BOOK.EDITION,ITEM.COST FROM BOOK,ITEM WHERE BOOK.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (BOOK.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR BOOK.AUTHOR LIKE '%"+search+"%' OR BOOK.SUBJECT LIKE '%"+search+"%' OR BOOK.EDITION LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.COST DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
		}
		BookList = bookDao.getBookList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		System.out.println(query);
		return BookList;
	}

	public ArrayList<CdBean> getCdList(int displayStart, String sortDir,
			int sortCol, int displayLength) {
		// TODO Auto-generated method stub
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<CdBean> CdList= new ArrayList<CdBean>();
		CdDao cdDao = new CdDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY CD.ITEMID ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;

			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.TITLE ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY CD.SUBJECT ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY CD.PUBLISHER ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.COST ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY CD.ITEMID DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.TITLE DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY CD.SUBJECT DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY CD.PUBLISHER DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.COST DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			
		}
		CdList = cdDao.getCdList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		return CdList;		
	}

	public int getCdCount() {
		// TODO Auto-generated method stub
		int result = 0;
		CdDao cdDao = new CdDao();
		result = cdDao.getCdCount();
		return result;
	}

	public ArrayList<CdBean> getCdListSearch(int displayStart, String sortDir,
			int sortCol, int displayLength, String search) {
		// TODO Auto-generated method stub
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<CdBean> CdList= new ArrayList<CdBean>();
		CdDao cdDao = new CdDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (CD.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR CD.SUBJECT LIKE '%"+search+"%' OR CD.PUBLISHER LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY CD.ITEMID ASC)m) where r between "+displayStart+" and "+displayEnd;

			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (CD.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR CD.SUBJECT LIKE '%"+search+"%' OR CD.PUBLISHER LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.TITLE ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (CD.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR CD.SUBJECT LIKE '%"+search+"%' OR CD.PUBLISHER LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY CD.SUBJECT ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (CD.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR CD.SUBJECT LIKE '%"+search+"%' OR CD.PUBLISHER LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY CD.PUBLISHER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (CD.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR CD.SUBJECT LIKE '%"+search+"%' OR CD.PUBLISHER LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.COST ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (CD.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR CD.SUBJECT LIKE '%"+search+"%' OR CD.PUBLISHER LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY CD.ITEMID ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (CD.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR CD.SUBJECT LIKE '%"+search+"%' OR CD.PUBLISHER LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.TITLE ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (CD.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR CD.SUBJECT LIKE '%"+search+"%' OR CD.PUBLISHER LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY CD.SUBJECT ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (CD.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR CD.SUBJECT LIKE '%"+search+"%' OR CD.PUBLISHER LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY CD.PUBLISHER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT CD.ITEMID,ITEM.TITLE,CD.SUBJECT,CD.PUBLISHER,ITEM.COST FROM CD,ITEM WHERE CD.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (CD.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR CD.SUBJECT LIKE '%"+search+"%' OR CD.PUBLISHER LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.COST ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			
		}
		CdList = cdDao.getCdList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		System.out.println(query);
		return CdList;
	}

	public ArrayList<JournalBean> getJournalList(int displayStart,
			String sortDir, int sortCol, int displayLength) {
		// TODO Auto-generated method stub
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<JournalBean> journalList= new ArrayList<JournalBean>();
		JournalDao journalDao = new JournalDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY JOURNAL.ITEMID ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;

			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.TITLE ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY JOURNAL.VOLUMENO ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY JOURNAL.SUBSCRIPTIONTYPE ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.COST ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY JOURNAL.ITEMID DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.TITLE DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY JOURNAL.VOLUMENO DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY JOURNAL.SUBSCRIPTIONTYPE DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.COST DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			
		}
		journalList = journalDao.getJournalList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		return journalList;	
	}

	public int getJournalCount() {
		// TODO Auto-generated method stub
		int result = 0;
		JournalDao journalDao = new JournalDao();
		result = journalDao.getJournalCount();
		return result;
	}

	public ArrayList<JournalBean> getJournalListSearch(int displayStart,
			String sortDir, int sortCol, int displayLength, String search) {
		// TODO Auto-generated method stub
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<JournalBean> journalList= new ArrayList<JournalBean>();
		JournalDao journalDao = new JournalDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED' AND (JOURNAL.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR JOURNAL.VOLUMENO LIKE '%"+search+"%' OR JOURNAL.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY JOURNAL.ITEMID ASC)m) where r between "+displayStart+" and "+displayEnd;

			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (JOURNAL.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR JOURNAL.VOLUMENO LIKE '%"+search+"%' OR JOURNAL.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.TITLE ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (JOURNAL.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR JOURNAL.VOLUMENO LIKE '%"+search+"%' OR JOURNAL.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY JOURNAL.VOLUMENO ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHEAND (JOURNAL.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR JOURNAL.VOLUMENO LIKE '%"+search+"%' OR JOURNAL.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY JOURNAL.SUBSCRIPTIONTYPE ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (JOURNAL.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR JOURNAL.VOLUMENO LIKE '%"+search+"%' OR JOURNAL.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.COST ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (JOURNAL.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR JOURNAL.VOLUMENO LIKE '%"+search+"%' OR JOURNAL.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY JOURNAL.ITEMID DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (JOURNAL.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR JOURNAL.VOLUMENO LIKE '%"+search+"%' OR JOURNAL.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.TITLE DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID ORDER BY JOURNAL.ITEMID AND (JOURNAL.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR JOURNAL.VOLUMENO LIKE '%"+search+"%' OR JOURNAL.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY JOURNAL.VOLUMENO DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (JOURNAL.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR JOURNAL.VOLUMENO LIKE '%"+search+"%' OR JOURNAL.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY JOURNAL.SUBSCRIPTIONTYPE DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT JOURNAL.ITEMID,ITEM.TITLE,JOURNAL.VOLUMENO,JOURNAL.SUBSCRIPTIONTYPE,ITEM.COST FROM JOURNAL,ITEM WHERE JOURNAL.ITEMID = ITEM.ITEMID AND (JOURNAL.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR JOURNAL.VOLUMENO LIKE '%"+search+"%' OR JOURNAL.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.COST DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			
		}
		journalList = journalDao.getJournalList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		System.out.println(query);
		return journalList;
	}

	public ArrayList<MagazineBean> getMagazineList(int displayStart,
			String sortDir, int sortCol, int displayLength) {
		// TODO Auto-generated method stub
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<MagazineBean> MagList= new ArrayList<MagazineBean>();
		MagazineDao magazineDao = new MagazineDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY MAGAZINE.ITEMID ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.ITEMID ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY MAGAZINE.VOLUMENO ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY MAGAZINE.SUBSCRIPTIONTYPE ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.COST ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY MAGAZINE.ITEMID DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.ITEMID DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY MAGAZINE.VOLUMENO DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY MAGAZINE.SUBSCRIPTIONTYPE DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') ORDER BY ITEM.COST DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			
		}
		MagList = magazineDao.getMagazineList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		return MagList;		
	}

	public int getMagazineCount() {
		// TODO Auto-generated method stub
		int result = 0;
		MagazineDao magazineDao = new MagazineDao();
		result = magazineDao.getMagazineCount();
		return result;
	}

	public ArrayList<MagazineBean> getMagazineListSearch(int displayStart,
			String sortDir, int sortCol, int displayLength, String search) {
		// TODO Auto-generated method stub
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<MagazineBean> MagList= new ArrayList<MagazineBean>();
		MagazineDao magazineDao = new MagazineDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (MAGAZINE.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR MAGAZINE.VOLUMENO LIKE '%"+search+"%' OR MAGAZINE.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY MAGAZINE.ITEMID ASC)m) where r between "+displayStart+" and "+displayEnd;

			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (MAGAZINE.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR MAGAZINE.VOLUMENO LIKE '%"+search+"%' OR MAGAZINE.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.TITLE ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID  AND (ITEM.STATUS <> 'DELETED') AND (MAGAZINE.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR MAGAZINE.VOLUMENO LIKE '%"+search+"%' OR MAGAZINE.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY MAGAZINE.VOLUMENO ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (MAGAZINE.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR MAGAZINE.VOLUMENO LIKE '%"+search+"%' OR MAGAZINE.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY MAGAZINE.SUBSCRIPTIONTYPE ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (MAGAZINE.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR MAGAZINE.VOLUMENO LIKE '%"+search+"%' OR MAGAZINE.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.COST ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (MAGAZINE.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR MAGAZINE.VOLUMENO LIKE '%"+search+"%' OR MAGAZINE.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY MAGAZINE.ITEMID DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (MAGAZINE.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR MAGAZINE.VOLUMENO LIKE '%"+search+"%' OR MAGAZINE.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.TITLE DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (MAGAZINE.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR MAGAZINE.VOLUMENO LIKE '%"+search+"%' OR MAGAZINE.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY MAGAZINE.VOLUMENO DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (ITEM.STATUS <> 'DELETED') AND (MAGAZINE.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR MAGAZINE.VOLUMENO LIKE '%"+search+"%' OR MAGAZINE.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY MAGAZINE.SUBSCRIPTIONTYPE DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT MAGAZINE.ITEMID,ITEM.TITLE,MAGAZINE.VOLUMENO,MAGAZINE.SUBSCRIPTIONTYPE,ITEM.COST FROM MAGAZINE,ITEM WHERE MAGAZINE.ITEMID = ITEM.ITEMID AND (MAGAZINE.ITEMID LIKE '%"+search+"%' OR ITEM.TITLE LIKE '%"+search+"%' OR MAGAZINE.VOLUMENO LIKE '%"+search+"%' OR MAGAZINE.SUBSCRIPTIONTYPE LIKE '%"+search+"%' OR ITEM.COST LIKE '%"+search+"%')ORDER BY ITEM.COST DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			
		}
		MagList = magazineDao.getMagazineList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		System.out.println(query);
		return MagList;
	}

	public ArrayList<MemberBean> getMemberList(int displayStart,
			String sortDir, int sortCol, int displayLength) {
		// TODO Auto-generated method stub
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<MemberBean> memList= new ArrayList<MemberBean>();
		AdminDao adminDao = new AdminDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTACTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' ORDER BY USERID ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTACTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' ORDER BY FIRSTNAME ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTACTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' ORDER BY IDENTITYID ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTACTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' ORDER BY CONTACTNO ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTACTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' ORDER BY EMAIL ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTACTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' ORDER BY USERID DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTACTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' ORDER BY FIRSTNAME DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTACTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' ORDER BY IDENTITYID DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTACTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' ORDER BY CONTACTNO DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTACTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' ORDER BY EMAIL DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
		}
		memList = adminDao.getMemberList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		return memList;		
	}

	public int getMemberCount() {
		// TODO Auto-generated method stub
		int result = 0;
		AdminDao adminDao = new AdminDao();
		result = adminDao.getMemberCount();
		return result;
	}

	public ArrayList<MemberBean> getMemberListSearch(int displayStart,
			String sortDir, int sortCol, int displayLength, String search) {
		// TODO Auto-generated method stub
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<MemberBean> memList= new ArrayList<MemberBean>();
		AdminDao adminDao = new AdminDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTATCTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%' OR CONTACTNO LIKE '%"+search+"%' OR EMAIL LIKE '%"+search+"%')ORDER BY USERID ASC)m) where r between "+displayStart+" and "+displayEnd;

			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTATCTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%' OR CONTACTNO LIKE '%"+search+"%' OR EMAIL LIKE '%"+search+"%')ORDER BY FIRSTNAME ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTATCTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%' OR CONTACTNO LIKE '%"+search+"%' OR EMAIL LIKE '%"+search+"%')ORDER BY IDENTITYID ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTATCTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%' OR CONTACTNO LIKE '%"+search+"%' OR EMAIL LIKE '%"+search+"%')ORDER BY CONTACTNO ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTATCTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%' OR CONTACTNO LIKE '%"+search+"%' OR EMAIL LIKE '%"+search+"%')ORDER BY EMAIL ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTATCTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%' OR CONTACTNO LIKE '%"+search+"%' OR EMAIL LIKE '%"+search+"%')ORDER BY USERID DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTATCTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%' OR CONTACTNO LIKE '%"+search+"%' OR EMAIL LIKE '%"+search+"%')ORDER BY FIRSTNAME DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTATCTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%' OR CONTACTNO LIKE '%"+search+"%' OR EMAIL LIKE '%"+search+"%')ORDER BY IDENTITYID DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTATCTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%' OR CONTACTNO LIKE '%"+search+"%' OR EMAIL LIKE '%"+search+"%')ORDER BY CONTACTNO DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID,CONTATCTNO,EMAIL FROM USERTABLE WHERE ROLE='Member' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%' OR CONTACTNO LIKE '%"+search+"%' OR EMAIL LIKE '%"+search+"%')ORDER BY EMAIL DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
		}
		memList = adminDao.getMemberList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		System.out.println(query);
		return memList;
	}
}
