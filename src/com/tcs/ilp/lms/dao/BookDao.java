package com.tcs.ilp.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tcs.ilp.lms.bean.BookBean;
import com.tcs.ilp.lms.bean.BookBean1;

public class BookDao 
{
	public int addBook(BookBean bookBean)
	{
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("insert into item(title,itemid,cost,status,yearofpublication,placeofpublication) values('"+bookBean.getTitle().toUpperCase()+"',SEQ_ITEM_ID.nextval,"+bookBean.getCost()+",'Available',"+bookBean.getYearOfPublication()+",'"+bookBean.getPlaceOfPublication().toUpperCase()+"')");
			result=pstmt.executeUpdate();
			if(result==1)
			{
				pstmt=con.prepareStatement("SELECT ITEMID FROM ITEM WHERE ROWNUM =1 ORDER BY ITEMID DESC ");
				ResultSet rs1=pstmt.executeQuery();
				while(rs1.next())
				{
					result = rs1.getInt(1);
					pstmt = con.prepareStatement("insert into book(bookid,itemid,author,isbn,subject,edition,editor,publisher) values("+result+","+result+",'"+bookBean.getAuthor().toUpperCase()+"',"+bookBean.getIsbn()+",'"+bookBean.getSubject().toUpperCase()+"',"+bookBean.getEdition()+",'"+bookBean.getEditor().toUpperCase()+"','"+bookBean.getPublisher().toUpperCase()+"')");
					pstmt.executeUpdate();
					System.out.println("Executing");
					pstmt=con.prepareStatement("SELECT COUNT(*) FROM ADMINCHANGELOG");
					ResultSet rs2 = pstmt.executeQuery();
					rs2.next();
					int count = rs2.getInt(1) + 1;
					System.out.println(count);
					String statement = "A Book with Title : "+bookBean.getTitle()+" has been added into the system";
					pstmt=con.prepareStatement("INSERT INTO ADMINCHANGELOG VALUES("+count+",'"+statement+"')");
					pstmt.executeUpdate();
					pstmt=con.prepareStatement("INSERT INTO LIBRARIANCHANGELOG VALUES('"+statement+"',"+count+")");
					pstmt.executeUpdate();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try 
			{
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		return result;
	}

	public int deleteBook(int itemId) {
		// TODO Auto-generated method stub

		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("UPDATE ITEM SET STATUS='DELETED' WHERE ITEMID=? ");
			pstmt.setInt(1,itemId);

			result=pstmt.executeUpdate();
			System.out.println(result);




		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}

	// delete
	public BookBean getAttribute(BookBean bookBean, String string) 
	{
		// TODO Auto-generated method stub		
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement(string);
			//pstmt.setString(1, bookBean.getMemType());

			pstmt.setInt(1, bookBean.getItemId());
			ResultSet result=pstmt.executeQuery();

			while(result.next())
			{
				bookBean.setBookId(result.getInt(1));
				bookBean.setAuthor(result.getString(3));
				bookBean.setIsbn(result.getLong(4));
				//itembean.setStatus(result.getString(6));
				bookBean.setPublisher(result.getString(8));
			}
			System.out.println(bookBean.getItemId());
			pstmt=con.prepareStatement("SELECT status FROM ITEM WHERE ITEMID=?");
			pstmt.setInt(1,bookBean.getItemId());
			result=pstmt.executeQuery();
			System.out.println(bookBean.getStatus());
			while(result.next())
			{
				bookBean.setStatus(result.getString(1));
			}
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return bookBean;
	}

	//only search part

	public BookBean getAttribute1(BookBean bookBean, String string) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement(string);
			//pstmt.setString(1, bookBean.getMemType());

			//pstmt.setLong(1, bookBean.getIsbn());
			pstmt.setString(1, bookBean.getTitle());

			ResultSet result=pstmt.executeQuery();

			while(result.next()){
				bookBean.setBookId(result.getInt(1));
				bookBean.setAuthor(result.getString(3));
				bookBean.setIsbn(result.getLong(4));
				//itembean.setStatus(result.getString(6));
				bookBean.setPublisher(result.getString(8));

				/*masterBean.setNumOfBooks(result.getInt(3));
				masterBean.setNumOfCds(result.getInt(4));
				masterBean.setNumOfMagazines(result.getInt(5));
				masterBean.setNumOfJournals(result.getInt(6));
				masterBean.setAmount(result.getDouble(7));*/
			}
			System.out.println(bookBean.getTitle());
			pstmt=con.prepareStatement("SELECT status FROM ITEM WHERE TITLE=?");
			//pstmt.setInt(1, bookBean.getItemId());
			pstmt.setString(1,bookBean.getTitle());
			result=pstmt.executeQuery();

			while(result.next()){ 
				bookBean.setStatus(result.getString(1));
			}



		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return bookBean;
	}

	public List<BookBean1> viewBookList(int memId)
	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;

		List<BookBean1> bookList=new ArrayList<BookBean1>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");

			String query="SELECT bookid,title,issuedt,duedt,cost,transid FROM TRANSACTION_DETAILS JOIN book ON book.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN ITEM ON ITEM.ITEMID = BOOK.ITEMID WHERE MEMID=? AND TRANSACTION_DETAILS.STATUS IN('I','RN') ";		
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			while(result.next())
			{
				BookBean1 tBean=new BookBean1();
				tBean.setBookId(result.getInt(1));
				tBean.setBookName(result.getString(2));
				tBean.setIssuedDate(result.getDate(3));
				tBean.setDueDate(result.getDate(4));
				tBean.setCost(result.getInt(5));
				tBean.setTxnId(result.getInt(6));
				//System.out.println(result.getString(2));
				bookList.add(tBean);								
			}
			System.out.println("size of issued bookList:"+bookList.size());

		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}		
		return bookList;
	}


	public void updateStatus(int bookId)
	{
		int result;
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		//List<BookBean> bookList=new ArrayList<BookBean>();		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='L' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN BOOK ON book.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE BOOKID=?) ";		
			String query1="UPDATE  item SET STATUS='L' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN BOOK ON book.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE BOOKID=?) ";

			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, bookId);
			pstmt1=con.prepareStatement(query1);
			pstmt1.setInt(1, bookId);
			result=pstmt.executeUpdate();
			int result1=pstmt1.executeUpdate();
			System.out.println("result is"+result);
			System.out.println("result is"+result1);

		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void statusP(int itemId)
	{
		int result;
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		//List<BookBean> bookList=new ArrayList<BookBean>();		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='P' WHERE itemid=? ";		
			String query1="UPDATE  item SET STATUS='P' WHERE itemid=?";

			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, itemId);
			pstmt1=con.prepareStatement(query1);
			pstmt1.setInt(1, itemId);
			result=pstmt.executeUpdate();
			int result1=pstmt1.executeUpdate();
			System.out.println("result is"+result);
			System.out.println("result is"+result1);

		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}



	//------------
	public void updateReturnStatus(int transId)
	{
		int result=0;
		int result1=0;
		int result2=0;
		int result4=0;
		ResultSet result3=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		//List<BookBean> bookList=new ArrayList<BookBean>();		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			String query1="UPDATE  ITEM SET STATUS='AR' WHERE itemid IN(SELECT ITEM.ITEMID FROM ITEM JOIN RESERVATION_DETAILS ON ITEM.ITEMID = RESERVATION_DETAILS.ITEMID JOIN TRANSACTION_DETAILS ON ITEM.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transId=?)";
			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='R',RETURNDT=SYSDATE WHERE transId=? ";		
			String query2="UPDATE  ITEM SET STATUS='R' WHERE itemid IN(SELECT ITEM.ITEMID FROM ITEM JOIN TRANSACTION_DETAILS ON ITEM.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transid=?)";

			String query3="SELECT ITEMID FROM TRANSACTION_DETAILS WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS WHERE transid=?)";
			String query4="UPDATE RESERVATION_DETAILS SET STATUS=STATUS-1 WHERE ITEMID=? AND STATUS NOT IN (0)";

			pstmt=con.prepareStatement(query1);
			pstmt.setInt(1, transId);
			result1=pstmt.executeUpdate();
			System.out.println("result1:"+result1);

			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, transId);
			result=pstmt.executeUpdate();
			System.out.println("result is"+result);

			if(result1==0)
			{
				pstmt=con.prepareStatement(query2);
				pstmt.setInt(1, transId);
				result2=pstmt.executeUpdate();
				System.out.println("result2 is"+result2);
			}
			else if(result1==1){
				int itemId=0;
				pstmt=con.prepareStatement(query3);
				pstmt.setInt(1, transId);
				result3=pstmt.executeQuery();
				while(result3.next())
				{
					itemId=result3.getInt(1);
					System.out.println(itemId);

				}
				pstmt=con.prepareStatement(query4);
				pstmt.setInt(1, itemId);
				result4=pstmt.executeUpdate();
				System.out.println(result4);
			}

		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	public List<BookBean1> viewLost(int memId)
	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;

		List<BookBean1> bookList=new ArrayList<BookBean1>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");

			String query="SELECT bookid,title,issuedt,duedt,cost FROM TRANSACTION_DETAILS JOIN book ON book.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN ITEM ON ITEM.ITEMID = BOOK.ITEMID WHERE MEMID=? and TRANSACTION_DETAILS.STATUS='L'";		
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			while(result.next())
			{
				BookBean1 tBean=new BookBean1();
				tBean.setBookId(result.getInt(1));
				tBean.setBookName(result.getString(2));
				tBean.setIssuedDate(result.getDate(3));
				tBean.setDueDate(result.getDate(4));
				tBean.setCost(result.getInt(5));
				bookList.add(tBean);

			}

		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return bookList;
	}

	public List<BookBean1> viewLostLibrarian()
	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;

		List<BookBean1> bookList=new ArrayList<BookBean1>();
		//List<MemberBean> memberList=new ArrayList<MemberBean>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");

			String query="SELECT memid,bookid,title,issuedt,duedt,cost,transid,usertable.firstname,itemid FROM TRANSACTION_DETAILS JOIN book ON book.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN ITEM ON ITEM.ITEMID = BOOK.ITEMID join usertable on usertable.userid=TRANSACTION_DETAILS.memid WHERE TRANSACTION_DETAILS.STATUS='L'";		
			pstmt=con.prepareStatement(query);
			//pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			while(result.next())
			{
				BookBean1 tBean=new BookBean1();
				//MemberBean memBean=new MemberBean();
				tBean.setMemId(result.getInt(1));
				tBean.setBookId(result.getInt(2));
				tBean.setBookName(result.getString(3));
				tBean.setIssuedDate(result.getDate(4));
				tBean.setDueDate(result.getDate(5));
				tBean.setCost(result.getInt(6));
				tBean.setTxnId(result.getInt(7));
				tBean.setMemberName(result.getString(8));
				tBean.setItemId(result.getInt(9));
				System.out.println("in Book dao");
				bookList.add(tBean);

			}

		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return bookList;
	}

	//----------
	public void cancelReport(int bookId)
	{
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		ResultSet resultSet=null;
		String renewalDate=null;
		//List<BookBean> bookList=new ArrayList<BookBean>();		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");

			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='RN' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN BOOK ON book.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE BOOKID=? and duedt>sysdate)";		
			String query1="UPDATE  TRANSACTION_DETAILS SET STATUS='I' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN BOOK ON book.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE BOOKID=? and duedt>sysdate)";

			String queryForItemTable="UPDATE ITEM SET item.STATUS='RN' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN BOOK ON book.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE BOOKID=? and duedt>sysdate)";
			String queryForItemTable1="UPDATE ITEM SET item.STATUS='I' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN BOOK ON book.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE BOOKID=? and duedt>sysdate)";		

			String queryDate="SELECT TRANSACTION_DETAILS.RENEWALDT FROM TRANSACTION_DETAILS JOIN BOOK ON book.ITEMID=TRANSACTION_DETAILS.ITEMID WHERE BOOK.BOOKID=? ";
			pstmt=con.prepareStatement(queryDate);
			pstmt.setInt(1, bookId);
			resultSet=pstmt.executeQuery();
			while(resultSet.next())
			{
				renewalDate=resultSet.getString(1);
			}
			if(renewalDate!=null)
			{
				pstmt1=con.prepareStatement(queryForItemTable);
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, bookId);
				pstmt1.setInt(1, bookId);
			}
			else
			{
				pstmt=con.prepareStatement(query1);
				pstmt1=con.prepareStatement(queryForItemTable1);
				pstmt.setInt(1, bookId);
				pstmt1.setInt(1, bookId);
			}
			result=pstmt.executeUpdate();
			int result1=pstmt1.executeUpdate();


		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public ArrayList<BookBean> getBookList(int displayStart,
			int displayEnd, String sortDir, int sortCol, int displayLength,
			String query) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		ArrayList<BookBean> list1=new ArrayList<BookBean>();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement(query);
			ResultSet result= pstmt.executeQuery();
			while(result.next())
			{
				BookBean dao=new BookBean();
				dao.setItemId(result.getInt("ITEMID"));
				dao.setTitle(result.getString("TITLE"));
				dao.setAuthor(result.getString("AUTHOR"));
				dao.setSubject(result.getString("SUBJECT"));
				dao.setEdition(result.getInt("EDITION"));
				dao.setCost(result.getFloat("COST"));
				list1.add(dao);				
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		finally	
		{
			try 
			{
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return list1; 

	}

	public int getBookCount() {
		// TODO Auto-generated method stub
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT count(ITEMID) FROM BOOK");
			ResultSet te=pstmt.executeQuery();
			while(te.next())
			{
				result = te.getInt(1);			
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try 
			{
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}	
		}
		return result;		 
	}

}
