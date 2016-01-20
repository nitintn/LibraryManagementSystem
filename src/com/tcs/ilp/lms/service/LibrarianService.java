package com.tcs.ilp.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.tcs.ilp.lms.bean.AdvancedReservationBean;
import com.tcs.ilp.lms.bean.BookBean;
import com.tcs.ilp.lms.bean.BookBean1;
import com.tcs.ilp.lms.bean.CdBean;
import com.tcs.ilp.lms.bean.CdBean1;
import com.tcs.ilp.lms.bean.FineBean;
import com.tcs.ilp.lms.bean.JournalBean;
import com.tcs.ilp.lms.bean.JournalBean1;
import com.tcs.ilp.lms.bean.MagazineBean;
import com.tcs.ilp.lms.bean.MagazineBean1;
import com.tcs.ilp.lms.bean.MemberBean;
import com.tcs.ilp.lms.bean.MemberBean1;
import com.tcs.ilp.lms.bean.NewsAndAnnouncements;
import com.tcs.ilp.lms.bean.Review;
import com.tcs.ilp.lms.bean.TransactionBean;
import com.tcs.ilp.lms.dao.AdminDao;
import com.tcs.ilp.lms.dao.AdvancedReservationDao;
import com.tcs.ilp.lms.dao.BookDao;
import com.tcs.ilp.lms.dao.CdDao;
import com.tcs.ilp.lms.dao.FineDao;
import com.tcs.ilp.lms.dao.JournalDao;
import com.tcs.ilp.lms.dao.LibrarianDao;
import com.tcs.ilp.lms.dao.MagazineDao;
import com.tcs.ilp.lms.dao.NewsDAO;
import com.tcs.ilp.lms.dao.ReviewDAO;
import com.tcs.ilp.lms.dao.TransactionDAO;

public class LibrarianService 
{
	private static Logger logger=Logger.getLogger("reviewservlet");
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
	{/*
		FineService fineService= new FineService();
		fineService.calculateFine(transid, duedate)
		
		pass this transID and retrieve due date
		pass this to calculateFine()
		*/
		//call the calculateFine() of staff activities module
		/*int fine=obj.calculateFine
		if(fine!=null)
		{
			System.out.println("you can't renew this entity!! You have to pay fine and return the item.");
		}
		else
		{
			//the code below
		}*/
		TransactionDAO tDao= new TransactionDAO();
		TransactionBean tBn=tDao.updateStatusRenewed(transId);
		return tBn;
	}
	
	public List<BookBean1> viewLostLibrarian()
	{
		List<BookBean1> bookList= new ArrayList<BookBean1>();
		BookDao bk=new BookDao();
		bookList=bk.viewLostLibrarian();
		return bookList;
	}
	
	public void statusP(int id)
	{
		BookDao bk=new BookDao();
		CdDao cd=new CdDao();
		MagazineDao  mag=new MagazineDao();
		JournalDao jd=new JournalDao();	
		bk.statusP(id);
		cd.statusP(id);
		mag.statusP(id);
		jd.statusP(id);
		
	}
	public boolean checkMemId(int memId)
	{
		boolean flag=false;
		TransactionDAO transDao=new TransactionDAO();
		flag=transDao.checkMemId(memId);
		return flag;	
	}

	public List<BookBean1> viewBookList(int memId)
	{
		List<BookBean1> bookList=new ArrayList<BookBean1>();
		BookDao bk= new BookDao();
		bookList=bk.viewBookList(memId);
		for(BookBean1 book:bookList)
		{
			book.setMemId(memId);
			
		}
		return bookList;
	}
	public List<CdBean1> viewCdList(int memId)
	{
		List<CdBean1> cdList=new ArrayList<CdBean1>();
		CdDao cdObj= new CdDao();
		cdList=cdObj.viewCdList(memId);
		for(CdBean1 cd:cdList)
		{
			cd.setMemId(memId);
		}
		return cdList;
	}
	public List<MagazineBean1> viewMagazineList(int memId)
	{
		List<MagazineBean1> magList=new ArrayList<MagazineBean1>();
		MagazineDao magDaoObj= new MagazineDao();
		magList=magDaoObj.viewMagazineList(memId);
		for(MagazineBean1 magBean:magList)
		{
			magBean.setMemId(memId);
		}
		return magList;
	}
	public List<JournalBean1> viewJournalList(int memId)
	{  
		List<JournalBean1> journalList=new ArrayList<JournalBean1>();
		JournalDao jourDaoObj= new JournalDao();
		journalList=jourDaoObj.viewJournalList(memId);
		for(JournalBean1 journalBean:journalList )
		{
			journalBean.setMemId(memId);
		}
		{}
		return journalList;
	}
	public List<TransactionBean> viewList(MemberBean1 mB)
	{
		List<TransactionBean> tList= new ArrayList<TransactionBean>();
		TransactionDAO tDao1= new TransactionDAO();
		tList= tDao1.viewList(mB.getMemId());
		return tList;
	
		
	}
	public void updateListStatus(int transId)
	{
		TransactionDAO tDao2= new TransactionDAO();
		tDao2.updateListStatus(transId);
		 System.out.println("in update list status in service");
	}
	public void updateStatus(int bookId)
	{
		BookDao bk= new BookDao();
		bk.updateReturnStatus(bookId);
	}
	public void updateCdStatus(int cdId)
	{
		CdDao bk= new CdDao();
		bk.updateReturnStatus(cdId);
	}
	public void updateMagazineStatus(int magId)
	{
		MagazineDao mag= new MagazineDao();
		mag.updateReturnStatus(magId);
	}
	public void updateJournalStatus(int jourId)
	{
		JournalDao jour= new JournalDao();
		jour.updateReturnStatus(jourId);
	}
	
	
	public List<CdBean1> viewLostLibrarianCd()
	{
		List<CdBean1> cdList=new ArrayList<CdBean1>();
		CdDao cdObj= new CdDao();
		cdList=cdObj.viewLostLibrarianCd();
		return cdList;
	}
	public List<MagazineBean1> viewLostLibrarianMag()
	{
		List<MagazineBean1> magList=new ArrayList<MagazineBean1>();
		MagazineDao magObj= new MagazineDao();
		magList=magObj.viewLostLibrarianMag();
		return magList;
	}
	public List<JournalBean1> viewLostLibrarianJournal()
	{
		List<JournalBean1> journalList=new ArrayList<JournalBean1>();
		JournalDao journalObj= new JournalDao();
		journalList=journalObj.viewLostLibrarianJournal();
		return journalList;
	}
	
	public ArrayList<BookBean1> listWithFine(ArrayList<Integer> idStatus)
	{
		TransactionDAO tDao= new TransactionDAO();
		ArrayList<BookBean1> tBeanList=new ArrayList<BookBean1>();
		tBeanList=tDao.listWithFine(idStatus);
		return tBeanList;
	}
	
	
	


	//cancel Advance Reservation
	public void cancelReservationUpdate(int memId, int resNo)
	{
		AdvancedReservationDao advResDao= new AdvancedReservationDao();
		//called from the servlet passing the inputed/session memId& transId as parameter/object
		String act;
		act=advResDao.cancelReservationUpdate(memId, resNo);
		return; 
	}
	
	//view advance reservation list for a member
	public ArrayList<AdvancedReservationBean> viewReservationList(int memId)
	{
		AdvancedReservationDao advResDao= new AdvancedReservationDao();
		ArrayList<AdvancedReservationBean> reservationList= new ArrayList<AdvancedReservationBean>();
		reservationList=advResDao.viewReservationList(memId);
		return reservationList;
	}
	

	public String issueItem(String item_type,String member_type,int memberID,int itemID)
	{
	TransactionDAO Dao2 = new TransactionDAO();	
	int permission = Dao2.checkItemsPermission(member_type, item_type);
	int assign = Dao2.getItemsAssigned(memberID, item_type);
	int advanceReservation = Dao2.checkAdvanceReservation(memberID,item_type);
	System.out.println("permission : "+permission);
	System.out.println("assign : "+assign);
	System.out.println("advance reservation : "+advanceReservation);
	String retrn = "";


	if((assign+advanceReservation)<permission)
	{
		System.out.println("you can issue");
	String status = Dao2.issueItem(memberID, itemID, item_type);
		
		if(status=="issued")
		{
			
			retrn = item_type+" issued successfully !!!";
		}

		else
		{
			
		retrn = "error occured while issuing !!!"+ "\n Enter correct ItemID !!!";	
			
		}
		
	}

	if((assign+advanceReservation)>=permission)
	{
		
	System.out.println("you can not issue");

	retrn=  item_type+" can not be issued as you already have "+assign+"  "+item_type+" and maximum permisible are "+permission +"and advance reservation is : "+advanceReservation;

	}


	return retrn;
	}
		
		
		
public float checkAmount(int itemID)
{
float amount=	new TransactionDAO().checkAmount(itemID);
System.out.println("amount to be payed "+amount);
return amount;
}
	
	

public String issueItemByPay(String item_type,String member_type,int memberID,int itemID)
{
	String retrn="";

	TransactionDAO Dao2 = new TransactionDAO();	
	int permission = Dao2.checkItemsPermission(member_type, item_type);
	int assign = Dao2.getItemsAssigned(memberID, item_type);
	int advanceReservation = Dao2.checkAdvanceReservation(memberID,item_type);
	System.out.println("permission : "+permission);
	System.out.println("assign : "+assign);


	if((assign+advanceReservation)>=permission)
	{
		System.out.println("you can issue");
	//String status = Dao2.issueItem(memberID, itemID, item_type);
		
		String status ="issued";
		
		if(status=="issued")
		{
			
			retrn = item_type+" issued successfully !!!";
		}

		else
		{
			
		retrn = "error occured while issuing !!!"+ "\n Enter correct ItemID !!!";	
			
		}
		
	}

	if((assign+advanceReservation)<permission)
	{
		
	System.out.println("you can not issue");

	retrn=  item_type+" can not be issued by paying as you already have "+assign+"  "+item_type +" and advance reservation is of "+advanceReservation+" "+item_type+" and maximum permisible are "+permission ;

	}

return retrn;
}



public String issueAdvancedReservedItem(int memberID,int itemID)
{
	//String sendBack="";
	
String sendBack=new TransactionDAO().checkAdvanceReservationOfParticularMember(memberID, itemID);

return sendBack;
}



public    ArrayList<AdvancedReservationBean>  advResList(int memberID)
{
	   ArrayList<AdvancedReservationBean> ar1 = new AdvancedReservationDao().itemResInAdv(memberID);


return ar1;

}


public int checkStatus(int itemID,int memberID)
{
int status=0;	

status= new AdvancedReservationDao().checkStatus(itemID, memberID);

return status;

}


public String checkItem_type(int itemID)
{
	String item_type="";
	item_type = new TransactionDAO().checkItem_type(itemID);
	System.out.println(item_type);
	
return item_type;
}



public String deleteRowFromResTable(int itemID,int memberID,String item_type)
{
String retrn="";	
String retrn1="";

 retrn = new AdvancedReservationDao().deleteRowFromResTable(itemID, memberID);
//retrn="deleted";
System.out.println(retrn);
retrn1=new TransactionDAO().issueItem(memberID, itemID, item_type);
//retrn1= "issued";
System.out.println(retrn1);

System.out.println((retrn+retrn1));
return (retrn+retrn1);
}

//copy paste by NKR

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

public ArrayList<NewsAndAnnouncements>  viewNews(String title){
	String newsId=null;

	NewsDAO dao=new NewsDAO();
	ArrayList<NewsAndAnnouncements> newsList=new ArrayList<NewsAndAnnouncements>();

	NewsDAO newsDao=new NewsDAO();
//	NewsAndAnnouncements newsList=new NewsAndAnnouncements();

	try {

		newsList=dao.getNews(title);

		//newsList=newsDao.getNews(title);

	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return newsList;
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
public void calculateFine(FineBean finebean)
{
	FineDao finedao = new FineDao();
	
	
}

//to check for memId to be registered
	
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

public ArrayList<String> getChangeLog() 
{
	ArrayList<String> change = new ArrayList<String>();
	LibrarianDao librarianDao = new LibrarianDao();
	change = librarianDao.getChangeLog();
	return change; 
}

}
