
package com.tcs.ilp.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.tcs.ilp.lms.bean.AdvancedReservationBean;
import com.tcs.ilp.lms.bean.BookBean;
import com.tcs.ilp.lms.bean.CdBean;
import com.tcs.ilp.lms.bean.JournalBean;
import com.tcs.ilp.lms.bean.MagazineBean;
import com.tcs.ilp.lms.bean.MasterProfileBean;
import com.tcs.ilp.lms.bean.MemberBean;
import com.tcs.ilp.lms.bean.MemberBean1;
import com.tcs.ilp.lms.bean.NewsAndAnnouncements;
import com.tcs.ilp.lms.bean.Review;
import com.tcs.ilp.lms.bean.TransactionBean;
import com.tcs.ilp.lms.bean.UserBean;
import com.tcs.ilp.lms.dao.AdminDao;
import com.tcs.ilp.lms.dao.AdvancedReservationDao;
import com.tcs.ilp.lms.dao.BookDao;
import com.tcs.ilp.lms.dao.CdDao;
import com.tcs.ilp.lms.dao.JournalDao;
import com.tcs.ilp.lms.dao.LibrarianDao;
import com.tcs.ilp.lms.dao.MagazineDao;
import com.tcs.ilp.lms.dao.MasterProfileDao;
import com.tcs.ilp.lms.dao.MemberDao;
import com.tcs.ilp.lms.dao.NewsDAO;
import com.tcs.ilp.lms.dao.ReviewDAO;
import com.tcs.ilp.lms.dao.TransactionDAO;

public class AdminService 
{	
	private static Logger logger=Logger.getLogger("reviewservlet");
	public int addAdmin(UserBean userBean)
	{
		int result = 0;
		AdminDao adminDao=new AdminDao();
		result = adminDao.addAdmin(userBean);
		return result;
	}	
	
	public int addLibrarian(UserBean userBean)
	{
		int result = 0;
		LibrarianDao librarianDao=new LibrarianDao();
		result = librarianDao.addLibrarian(userBean);
		return result;
	}
	
	public UserBean searchUser(int userId)
	{
		UserBean userBean = new UserBean();
		AdminDao adminDao = new AdminDao();
		userBean=adminDao.getUser(userId, "SELECT * FROM USERTABLE WHERE USERID=?");
		return userBean;
	}
	
	public int deleteLibrarian(int userId)
	{
		int check = 0;
		AdminDao adminDao = new AdminDao();
		check = adminDao.deleteLibrarian(userId);
		return check;
	}
	
	public int deleteMember(int userId)
	{
		int check = 0;
		AdminDao adminDao = new AdminDao();
		check = adminDao.deleteMember(userId);
		return check;
	}
		
	public int addBook(BookBean bookBean)
	{
		int result = 0;
		BookDao bookDao = new BookDao();
		result = bookDao.addBook(bookBean);
		return result;
	}
	
	public int addCd(CdBean cdBean)
	{
		int result = 0;
		CdDao cdDao = new CdDao();
		result = cdDao.addCd(cdBean);
		return result;
	}
	
	public int addMagazine(MagazineBean magazineBean)
	{
		int result = 0;
		MagazineDao magazineDao = new MagazineDao();
		result = magazineDao.addMagazine(magazineBean);
		return result;
	}
	
	public int addJournal(JournalBean journalBean)
	{
		int result = 0;
		JournalDao journalDao = new JournalDao();
		result = journalDao.addJournal(journalBean);
		return result;
	}
	
	public ArrayList<MasterProfileBean> getMasterProfileList()
	{
		//ArrayList<String> masterList = new ArrayList<String>();
		ArrayList<MasterProfileBean> masterList = new ArrayList<MasterProfileBean>();
		AdminDao adminDao = new AdminDao();
		masterList = adminDao.getMasterList();
		return masterList;
	}
	
	public BookBean searchBook(BookBean bookBean)
	{
		BookDao bookDao=new BookDao();
    	bookBean=bookDao.getAttribute(bookBean, "SELECT * FROM BOOK WHERE ITEMID=?");
    	return bookBean;
	}
	
	public CdBean searchCd(CdBean cdBean)
	{
		CdDao cdDao=new CdDao();
    	cdBean=cdDao.getAttribute(cdBean, "SELECT * FROM CD WHERE ITEMID=?");
    	return cdBean;
	}
	
	public MagazineBean searchMagazine(MagazineBean magazineBean)
	{
		MagazineDao magDao=new MagazineDao();
		magazineBean=magDao.getAttribute(magazineBean, "SELECT * FROM MAGAZINE WHERE ITEMID=?");
    	return magazineBean;
	}
	
	public JournalBean searchJournal(JournalBean journalBean)
	{
		JournalDao journalDao=new JournalDao();
		journalBean=journalDao.getAttribute(journalBean, "SELECT * FROM JOURNAL WHERE ITEMID=?");
    	return journalBean;
	}
	public int deleteBook(int itemId) {
		// TODO Auto-generated method stub
		int result=0;
		BookDao bookDao=new BookDao();	
		result= bookDao.deleteBook(itemId);
		return result;
	}

	public int deleteJournal(int itemId) {
		// TODO Auto-generated method stub
		int result=0;
		JournalDao journalDao=new JournalDao();
	
		result=journalDao.deleteJournal(itemId);
		return result;
	}

	public int deleteMagazine(int itemId) {
		// TODO Auto-generated method stub
		int result=0;
		MagazineDao magazineDao=new MagazineDao();
	
		result=magazineDao.deleteMagazine(itemId);
		return result;	
	}

	public int deleteCd(int itemId) {
		// TODO Auto-generated method stub
		int result=0;
		CdDao cdDao=new CdDao();
	
		result=cdDao.deleteCd(itemId);
		return result;
	}

	public BookBean searchBookOnly(BookBean bookBean)
	{
		BookDao bookDao=new BookDao();
    	bookBean=bookDao.getAttribute1(bookBean, "SELECT * FROM BOOK WHERE ITEMID IN (SELECT itemid FROM item WHERE title=?)");
    	return bookBean;
	}

	public CdBean searchCdOnly(CdBean cdBean) {
		// TODO Auto-generated method stub
		CdDao cdDao=new CdDao();
    	cdBean=cdDao.getAttributeCD(cdBean, "SELECT * FROM CD WHERE ITEMID IN (SELECT itemid FROM item WHERE title=?)");
    	return cdBean;
	}

	public MagazineBean searchMagazineOnly(MagazineBean magazineBean) {
		// TODO Auto-generated method stub
		MagazineDao magDao=new MagazineDao();
    	magazineBean=magDao.getAttributeMagazine(magazineBean, "SELECT * FROM MAGAZINE WHERE ITEMID IN (SELECT itemid FROM item WHERE title=?)");
    	return magazineBean;
	}

	public JournalBean searchJournalOnly(JournalBean journalBean) {
		// TODO Auto-generated method stub
		JournalDao jouDao=new JournalDao();
    	journalBean=jouDao.getAttributeJournal(journalBean, "SELECT * FROM JOURNAL WHERE ITEMID IN (SELECT itemid FROM item WHERE title=?)");
    	return journalBean;
	}
	
	public ArrayList<MasterProfileBean> itemDetails(String memType) 
	{
		// TODO Auto-generated method stub
		ArrayList<MasterProfileBean> list=new ArrayList<MasterProfileBean>();
		MasterProfileDao masterProfileDao = new MasterProfileDao();
		list= masterProfileDao.getDetails(memType);
		return list;	
	}

	public int updatedMasterProfile(MasterProfileBean masterProfileBean) {
		int result =0;
		//System.out.println("hii admin service here");
		MasterProfileDao masterProfileDao = new MasterProfileDao();
		result= masterProfileDao.updatedMasterProfile(masterProfileBean);
		return result;
		// TODO Auto-generated method stub
	
	}
	public ArrayList<MasterProfileBean> retrieveMasterProfile(MasterProfileBean mp) 
	{

		ArrayList<MasterProfileBean> retrieve= new ArrayList<MasterProfileBean>();
		MasterProfileDao masterProfileDaoo = new MasterProfileDao();
		retrieve= masterProfileDaoo.retrieveMasterProfile(mp);
		return retrieve;
	}
	
	public int addMasterProfile(MasterProfileBean masterProfileBean) 
	{
		int result = 0;
		MasterProfileDao masterProfileDao = new MasterProfileDao();
		result = masterProfileDao.addMasterProfile(masterProfileBean);
		return result;
	}
	
	public boolean checkAvailabilityOfCategory(MasterProfileBean masterProfileBean) 
	{
		boolean categoryAvailable = false;
		MasterProfileDao masterProfileDao = new MasterProfileDao();
		categoryAvailable = masterProfileDao.checkAvailabilityOfCategory(masterProfileBean);
		return categoryAvailable;
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
	
	public int addNews(NewsAndAnnouncements news){
		String newsId=null;
		NewsDAO newsDao=new NewsDAO();
		int i=0;
		logger.info("Add news");
		try {
			i=newsDao.insertNews(news);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public int addNews1(NewsAndAnnouncements news){
		String newsId=null;
		NewsDAO newsDao=new NewsDAO();
		int i=0;
		logger.info("Add news");
		try {
			i=newsDao.insertNews1(news);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public void deleteNews(int newsid){
		NewsDAO newsDao=new NewsDAO();
		
		try {
			newsDao.deleteNews(newsid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  ArrayList<NewsAndAnnouncements> viewNews(String title){
		String newsId=null;
		NewsDAO dao=new NewsDAO();
		ArrayList<NewsAndAnnouncements> newsList=new ArrayList<NewsAndAnnouncements>();
		try {
			newsList=dao.getNews(title);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newsList;
	}
	
	public  ArrayList<NewsAndAnnouncements> viewNewsUser(int userId){
		ArrayList<NewsAndAnnouncements> newList=new ArrayList<NewsAndAnnouncements>();
		ArrayList<NewsAndAnnouncements> new1List=new ArrayList<NewsAndAnnouncements>();
		String newsId=null;
		NewsDAO dao=new NewsDAO();
		ArrayList<NewsAndAnnouncements> newsList=new ArrayList<NewsAndAnnouncements>();
		try {
			newsList=dao.getNewsList(userId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(NewsAndAnnouncements n:newsList){
			if(n.getUserId()==userId){
				newList.add(n);
			}
		}
		return newList;
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
	public ArrayList<Review> viewReviewTitle(String title){
		ArrayList<Review> reviewList=new ArrayList<Review>();
		ArrayList<Review> reviewListTitle=new ArrayList<Review>();
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
				reviewListTitle.add(r);
			}
		}
		return reviewListTitle;
	}
	public ArrayList<Review> viewReviewMember(int id){
		ArrayList<Review> reviewList=new ArrayList<Review>();
		ArrayList<Review> reviewListTitle=new ArrayList<Review>();
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
			if(r.getMemberId()==id){
				reviewListTitle.add(r);
			}
		}
		return reviewListTitle;
	}
	
	public int getMasterCount()
	{
		int result = 0;
		MasterProfileDao masterProfileDao = new MasterProfileDao();
		result = masterProfileDao.getMasterCount();
		return result;
	}
	public int getMemberTypeCount(int memId)
	{
		int result = 0;
		MasterProfileDao masterProfileDao = new MasterProfileDao();
		result = masterProfileDao.getMemberTypeCount(memId);
		return result;
	}
	public ArrayList<MemberBean> getStaffList(int displayStart,String sortDir,int sortCol,int displayLength)
	{		
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<MemberBean> staffList= new ArrayList<MemberBean>();
		AdminDao adminDao = new AdminDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID FROM USERTABLE WHERE ROLE='Librarian' ORDER BY USERID ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID FROM USERTABLE WHERE ROLE='Librarian' ORDER BY FIRSTNAME ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID FROM USERTABLE WHERE ROLE='Librarian' ORDER BY IDENTITYID ASC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID FROM USERTABLE WHERE ROLE='Librarian' ORDER BY USERID DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID FROM USERTABLE WHERE ROLE='Librarian' ORDER BY FIRSTNAME DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID FROM USERTABLE WHERE ROLE='Librarian' ORDER BY IDENTITYID DESC)m) where r BETWEEN "+displayStart+" and "+displayEnd;
			}
			
		}
		staffList = adminDao.getStaffList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		return staffList;		
	}
	
	public ArrayList<MasterProfileBean> getMasterList(int displayStart,String sortDir,int sortCol,int displayLength)
	{		
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<MasterProfileBean> masterList= new ArrayList<MasterProfileBean>();
		MasterProfileDao masterProfileDao = new MasterProfileDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY MEMBERTYPE ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY BOOKNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY CDNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY MAGAZINENUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY JOURNALNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==5)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY COST ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY MEMBERTYPE DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY BOOKNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY CDNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY MAGAZINENUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY JOURNALNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==5)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY COST DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
		}
		masterList = masterProfileDao.getMasterList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		return masterList;		
	}

	public ArrayList<MasterProfileBean> getMasterListSearch(int displayStart, String sortDir, int sortCol, int displayLength, String search) 
	{
		// TODO Auto-generated method stub
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<MasterProfileBean> masterList= new ArrayList<MasterProfileBean>();
		MasterProfileDao masterProfileDao = new MasterProfileDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%') ORDER BY MEMBERTYPE ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%') ORDER BY BOOKNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%') ORDER BY CDNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%') ORDER BY MAGAZINENUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%') ORDER BY JOURNALNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==5)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%') ORDER BY COST ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%') ORDER BY MEMBERTYPE DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%') ORDER BY BOOKNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%') ORDER BY CDNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%') ORDER BY MAGAZINENUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%') ORDER BY JOURNALNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==5)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%') ORDER BY COST DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
		}
		masterList = masterProfileDao.getMasterList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		System.out.println(query);
		return masterList;
	}
	
	//member type upgrade
	
	public ArrayList<MasterProfileBean> getMemberTypeList(int displayStart,String sortDir,int sortCol,int displayLength, int memId)
	{		
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<MasterProfileBean> masterList= new ArrayList<MasterProfileBean>();
		MasterProfileDao masterProfileDao = new MasterProfileDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY MEMBERTYPE ASC)m) where r between "+displayStart+" and "+displayEnd;
				//query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY MEMBERTYPE ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY BOOKNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
				//query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY BOOKNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY CDNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
				//query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY CDNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY MAGAZINENUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
				//query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY MAGAZINENUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY JOURNALNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
				//query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY JOURNALNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==5)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY COST ASC)m) where r between "+displayStart+" and "+displayEnd;
				//query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY COST ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY MEMBERTYPE DESC)m) where r between "+displayStart+" and "+displayEnd;
				//query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY MEMBERTYPE DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY BOOKNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
				//query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY BOOKNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY CDNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
				//query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY CDNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==3)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY MAGAZINENUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
				//query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY MAGAZINENUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==4)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY JOURNALNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
				//query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY JOURNALNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==5)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY COST DESC)m) where r between "+displayStart+" and "+displayEnd;
				//query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE ORDER BY COST DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
		}
		masterList = masterProfileDao.getMemberTypeList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		return masterList;		
	}

	public ArrayList<MasterProfileBean> getMemberTypeListSearch(int displayStart, String sortDir, int sortCol, int displayLength, String search, int memId) 
	{
		// TODO Auto-generated method stub
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<MasterProfileBean> masterList= new ArrayList<MasterProfileBean>();
		MasterProfileDao masterProfileDao = new MasterProfileDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM(SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY MEMBERTYPE ASC)m) where r between "+displayStart+" and "+displayEnd+")WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%')";

			}
			if(sortCol==1)
			{
				query = "SELECT * FROM(SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY BOOKNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd+")WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%')";

			}
			if(sortCol==2)
			{
				query = "SELECT * FROM(SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY CDNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd+")WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%')";

			}
			if(sortCol==3)
			{
				query = "SELECT * FROM(SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY MAGAZINENUMBER ASC)m) where r between "+displayStart+" and "+displayEnd+")WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%')";

			}
			if(sortCol==4)
			{
				query = "SELECT * FROM(SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY JOURNALNUMBER ASC)m) where r between "+displayStart+" and "+displayEnd+")WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%')";

			}
			if(sortCol==5)
			{
				query = "SELECT * FROM(SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY COST ASC)m) where r between "+displayStart+" and "+displayEnd+")WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%')";

			}
		}
		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM(SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY MEMBERTYPE DESC)m) where r between "+displayStart+" and "+displayEnd+")WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%')";

			}
			if(sortCol==1)
			{
				query = "SELECT * FROM(SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY BOOKNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd+")WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%')";

			}
			if(sortCol==2)
			{
				query = "SELECT * FROM(SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY CDNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd+")WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%')";

			}
			if(sortCol==3)
			{
				query = "SELECT * FROM(SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY MAGAZINENUMBER DESC)m) where r between "+displayStart+" and "+displayEnd+")WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%')";

			}
			if(sortCol==4)
			{
				query = "SELECT * FROM(SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY JOURNALNUMBER DESC)m) where r between "+displayStart+" and "+displayEnd+")WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%')";

			}
			if(sortCol==5)
			{
				query = "SELECT * FROM(SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))ORDER BY COST DESC)m) where r between "+displayStart+" and "+displayEnd+")WHERE (MEMBERTYPE LIKE '%"+search+"%' OR COST LIKE '%"+search+"%' OR BOOKNUMBER LIKE '%"+search+"%' OR CDNUMBER LIKE '%"+search+"%' OR MAGAZINENUMBER LIKE '%"+search+"%' OR JOURNALNUMBER LIKE '%"+search+"%')";

			}
		}
		masterList = masterProfileDao.getMemberTypeList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		System.out.println(query);
		return masterList;
	}
	public int getBookCount()
	{
		int result = 0;
		BookDao bookDao = new BookDao();
		result = bookDao.getBookCount();
		return result;
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

	public int getCdCount()
	{
		int result = 0;
		CdDao cdDao = new CdDao();
		result = cdDao.getCdCount();
		return result;
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

	public int getJournalCount()
	{
		int result = 0;
		JournalDao journalDao = new JournalDao();
		result = journalDao.getJournalCount();
		return result;
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
	

	public boolean checkAvailabilityOfAdminIdentityId(String identityId) 
	{
		// TODO Auto-generated method stub
		boolean identityIdAvailable = false;
		AdminDao adminDao = new AdminDao();
		identityIdAvailable = adminDao.checkAvailabilityOfIdentityId(identityId);
		return identityIdAvailable;
	}
	
	public boolean checkAvailabilityOfAdminUserId(int userIdInt) 
	{
		System.out.println(userIdInt);
		// TODO Auto-generated method stub
		boolean userIdAvailable = false;
		AdminDao adminDao = new AdminDao();
		userIdAvailable = adminDao.checkAvailabilityOfUserId(userIdInt);
		return userIdAvailable;
	}

	public int getItemCount(int cost) 
	{
		// TODO Auto-generated method stub
		AdminDao adminDao = new AdminDao();
		int count = adminDao.getItemCount(cost);		
		return count;
	}

	public int getMinCount(int cost) 
	{
		// TODO Auto-generated method stub
		AdminDao adminDao = new AdminDao();
		int count = adminDao.getMinItemCount(cost);		
		return count;
	}

	public int getMaxCount(int cost) 
	{
		// TODO Auto-generated method stub
		AdminDao adminDao = new AdminDao();
		int count = adminDao.getMaxItemCount(cost);		
		return count;
	}

	public ArrayList<MemberBean> getStaffListSearch(int displayStart,
			String sortDir, int sortCol, int displayLength, String search) {
		// TODO Auto-generated method stub
		int displayEnd = displayStart + displayLength - 1;
		ArrayList<MemberBean> staffList= new ArrayList<MemberBean>();
		AdminDao adminDao = new AdminDao();
		String query = "";
		if(sortDir.equalsIgnoreCase("asc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID FROM USERTABLE WHERE ROLE='Librarian' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%')ORDER BY USERID ASC)m) where r between "+displayStart+" and "+displayEnd;

			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID FROM USERTABLE WHERE ROLE='Librarian' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%')ORDER BY FIRSTNAME ASC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID FROM USERTABLE WHERE ROLE='Librarian' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%')ORDER BY IDENTITYID ASC)m) where r between "+displayStart+" and "+displayEnd;
			}			
		}		
		else if(sortDir.equalsIgnoreCase("desc"))
		{
			if(sortCol==0)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID FROM USERTABLE WHERE ROLE='Librarian' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%')ORDER BY USERID DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==1)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID FROM USERTABLE WHERE ROLE='Librarian' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%')ORDER BY FIRSTNAME DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			if(sortCol==2)
			{
				query = "SELECT * FROM (SELECT m.*,ROWNUM r FROM (SELECT USERID,FIRSTNAME,IDENTITYID FROM USERTABLE WHERE ROLE='Librarian' AND (USERID LIKE '%"+search+"%' OR FIRSTNAME LIKE '%"+search+"%' OR IDENTITYID LIKE '%"+search+"%')ORDER BY IDENTITYID DESC)m) where r between "+displayStart+" and "+displayEnd;
			}
			
			
		}
		staffList = adminDao.getStaffList(displayStart,displayEnd,sortDir,sortCol,displayLength,query);
		System.out.println(query);
		return staffList;
	}

	public int getStaffCount() 
	{
		// TODO Auto-generated method stub
		int result = 0;
		AdminDao adminDao = new AdminDao();
		result = adminDao.getStaffCount();
		return result;
	}

	public int deleteStaff(int userId) 
	{
		// TODO Auto-generated method stub
		int result=0;
		AdminDao adminDao=new AdminDao();	
		result=adminDao.deleteStaff(userId);
		return result;
	}
	
	public ArrayList<MemberBean> getMemberList(int displayStart, String sortDir, int sortCol, int displayLength) 
	{		
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

	public int getMemberCount() 
	{
		// TODO Auto-generated method stub
		int result = 0;
		AdminDao adminDao = new AdminDao();
		result = adminDao.getMemberCount();
		return result;
	}
	
	public ArrayList<MemberBean> getMemberListSearch(int displayStart,String sortDir, int sortCol, int displayLength, String search) 
	{
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
	
	public ArrayList<String> getChangeLog()
	{
		ArrayList<String> change = new ArrayList<String>();
		AdminDao adminDao = new AdminDao();
		change = adminDao.getChangeLog();
		return change; 
	}
	
}
