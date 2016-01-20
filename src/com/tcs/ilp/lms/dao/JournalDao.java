package com.tcs.ilp.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tcs.ilp.lms.bean.JournalBean;
import com.tcs.ilp.lms.bean.JournalBean1;

public class JournalDao 
{
	public int addJournal(JournalBean journalBean)
	{
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("insert into item(title,itemid,cost,status,yearofpublication,placeofpublication) values('"+journalBean.getTitle().toUpperCase()+"',SEQ_ITEM_ID.nextval,"+journalBean.getCost()+",'Available',"+journalBean.getYearOfPublication()+",'"+journalBean.getPlaceOfPublication().toUpperCase()+"')");
			result=pstmt.executeUpdate();
			if(result==1)
			{
				pstmt=con.prepareStatement("SELECT ITEMID FROM ITEM WHERE ROWNUM =1 ORDER BY ITEMID DESC ");
				ResultSet rs1=pstmt.executeQuery();
				while(rs1.next())
				{
					result = rs1.getInt(1);
					pstmt = con.prepareStatement("insert into journal(journalid,itemid,isbn,volumeno,subscriptiontype) values("+result+","+result+","+journalBean.getIsbn()+","+journalBean.getVolumeNo()+",'"+journalBean.getSubscriptionType().toUpperCase()+"')");
					pstmt.executeUpdate();
					pstmt=con.prepareStatement("SELECT COUNT(*) FROM ADMINCHANGELOG");
					ResultSet rs2 = pstmt.executeQuery();
					rs2.next();
					int count = rs2.getInt(1) + 1;
					System.out.println(count);
					String statement = "A Journal with Title : "+journalBean.getTitle()+" has been added into the system";
					pstmt=con.prepareStatement("INSERT INTO ADMINCHANGELOG VALUES("+count+",'"+statement+"')");
					pstmt.executeUpdate();
					pstmt=con.prepareStatement("INSERT INTO LIBRARIANCHANGELOG VALUES("+count+",'"+statement+"')");
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

	public int deleteJournal(int itemId) {
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

	public JournalBean getAttribute(JournalBean journalBean, String string) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement(string);
			//pstmt.setString(1, bookBean.getMemType());

			pstmt.setInt(1, journalBean.getItemId());
			ResultSet result=pstmt.executeQuery();

			while(result.next()){
				journalBean.setJournalId(result.getInt(1));
				journalBean.setIsbn(result.getLong(3));
				journalBean.setVolumeNo(result.getInt(4));
				journalBean.setSubscriptionType(result.getString(5));
				//itembean.setStatus(result.getString(6));

				/*masterBean.setNumOfBooks(result.getInt(3));
				masterBean.setNumOfCds(result.getInt(4));
				masterBean.setNumOfMagazines(result.getInt(5));
				masterBean.setNumOfJournals(result.getInt(6));
				masterBean.setAmount(result.getDouble(7));*/
			}
			System.out.println(journalBean.getItemId());
			pstmt=con.prepareStatement("SELECT status FROM ITEM WHERE ITEMID=?");
			pstmt.setInt(1, journalBean.getItemId());
			result=pstmt.executeQuery();

			while(result.next()){
				journalBean.setStatus(result.getString(1));
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
		return journalBean;

	}

	public JournalBean getAttributeJournal(JournalBean journalBean,
			String string) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement(string);
			//pstmt.setString(1, bookBean.getMemType());

			pstmt.setString(1, journalBean.getTitle());
			ResultSet result=pstmt.executeQuery();

			while(result.next()){
				journalBean.setJournalId(result.getInt(1));
				journalBean.setIsbn(result.getLong(3));
				journalBean.setVolumeNo(result.getInt(4));
				journalBean.setSubscriptionType(result.getString(5));
				//itembean.setStatus(result.getString(6));

				/*masterBean.setNumOfBooks(result.getInt(3));
				masterBean.setNumOfCds(result.getInt(4));
				masterBean.setNumOfMagazines(result.getInt(5));
				masterBean.setNumOfJournals(result.getInt(6));
				masterBean.setAmount(result.getDouble(7));*/
			}
			System.out.println(journalBean.getItemId());
			pstmt=con.prepareStatement("SELECT status FROM ITEM WHERE TITLE=?");
			pstmt.setString(1, journalBean.getTitle());
			result=pstmt.executeQuery();

			while(result.next()){
				journalBean.setStatus(result.getString(1));
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
		return journalBean;

	}

	public List<JournalBean1> viewJournalList(int memId)
	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;

		List<JournalBean1> journalList=new ArrayList<JournalBean1>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");


			String query="SELECT journalid,title,issuedt,duedt,cost,transid FROM TRANSACTION_DETAILS JOIN journal ON journal.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN ITEM ON ITEM.ITEMID = journal.ITEMID WHERE MEMID=? AND TRANSACTION_DETAILS.STATUS IN('I','RN') ";		

			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			System.out.println("IN JOUR DAO");
			while(result.next())
			{
				JournalBean1 tBean=new JournalBean1();
				tBean.setJournalId(result.getInt(1));
				tBean.setJournalName(result.getString(2));
				tBean.setIssuedDate(result.getDate(3));
				tBean.setReturnDate(result.getDate(4));
				tBean.setCost(result.getInt(5));
				tBean.setTxnId(result.getInt(6));
				//System.out.println(result.getString(2));
				journalList.add(tBean);


			}
			System.out.println("size of issuedjournalList:"+journalList.size());

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

		return journalList;
	}
	public void updateStatusJournal(int journalId)
	{
		int result;
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		//List<journalBean> journalList=new ArrayList<journalBean>();		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='L' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN journal ON journal.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE journalid=?) ";		
			String query1="UPDATE  item SET STATUS='L' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN journal ON journal.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE journalid=?) ";

			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, journalId);
			pstmt1=con.prepareStatement(query1);
			pstmt1.setInt(1, journalId);
			result=pstmt.executeUpdate();
			int result1=pstmt1.executeUpdate();
			System.out.println("update satus journal "+result);
			System.out.println("update status"+result1);

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
		//List<journalBean> journalList=new ArrayList<journalBean>();		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='P' WHERE itemid=?  ";		
			String query1="UPDATE  item SET STATUS='P' WHERE itemid=? ";

			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, itemId);
			pstmt1=con.prepareStatement(query1);
			pstmt1.setInt(1, itemId);
			result=pstmt.executeUpdate();
			int result1=pstmt1.executeUpdate();
			System.out.println("journal status paid is"+result);
			System.out.println("journal status for paid is"+result1);

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
		//List<journalBean> journalList=new ArrayList<journalBean>();		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			System.out.println("in jour status change");
			String query1="UPDATE  ITEM SET STATUS='AR' WHERE itemid IN(SELECT ITEM.ITEMID FROM ITEM JOIN RESERVATION_DETAILS ON ITEM.ITEMID = RESERVATION_DETAILS.ITEMID JOIN journal ON journal.ITEMID = ITEM.ITEMID JOIN TRANSACTION_DETAILS ON ITEM.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transid=?)";
			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='R',RETURNDT=SYSDATE WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN journal ON journal.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transid=?) ";		
			String query2="UPDATE  ITEM SET STATUS='R' WHERE itemid IN(SELECT ITEM.ITEMID FROM ITEM JOIN TRANSACTION_DETAILS ON ITEM.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN journal ON journal.ITEMID = ITEM.ITEMID WHERE transid=?)";
			String query3="SELECT ITEMID FROM TRANSACTION_DETAILS WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN JOURNAL ON JOURNAL.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transid=?)";
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
	public List<JournalBean1> viewLostJournal(int memId)
	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;

		List<JournalBean1> journalList=new ArrayList<JournalBean1>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");

			String query="SELECT journalid,title,issuedt,duedt,cost FROM TRANSACTION_DETAILS JOIN journal ON journal.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN ITEM ON ITEM.ITEMID = journal.ITEMID WHERE MEMID=? and TRANSACTION_DETAILS.STATUS='L'";		
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			while(result.next())
			{
				JournalBean1 tBean=new JournalBean1();
				tBean.setJournalId(result.getInt(1));
				tBean.setJournalName((result.getString(2)));

				tBean.setIssuedDate(result.getDate(3));
				tBean.setDueDate(result.getDate(4));

				tBean.setCost(result.getInt(5));
				journalList.add(tBean);

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

		return journalList;
	}

	public List<JournalBean1> viewLostLibrarianJournal()
	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;

		List<JournalBean1> journalList=new ArrayList<JournalBean1>();
		//List<MemberBean> memberList=new ArrayList<MemberBean>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");

			String query="SELECT memid,journalid,title,issuedt,duedt,cost,transid,usertable.firstname,itemid FROM TRANSACTION_DETAILS JOIN journal ON journal.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN ITEM ON ITEM.ITEMID = journal.ITEMID usertable on usertable.userid=TRANSACTION_DETAILS.memid WHERE TRANSACTION_DETAILS.STATUS='L'";		
			pstmt=con.prepareStatement(query);
			//pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			while(result.next())
			{
				JournalBean1 tBean=new JournalBean1();
				tBean.setMemId(result.getInt(1));
				tBean.setJournalId((result.getInt(2)));
				tBean.setJournalName(result.getString(3));
				tBean.setIssuedDate(result.getDate(4));
				tBean.setDueDate(result.getDate(5));
				tBean.setCost(result.getInt(6));
				tBean.setTxnId(result.getInt(7));
				tBean.setMemberName(result.getString(8));
				tBean.setItemId(result.getInt(9));
			
				journalList.add(tBean);


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

		return journalList;
	}

	//----------
	public void cancelReportJournal(int journalId)
	{
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		ResultSet resultSet=null;
		String renewalDate=null;
		//List<journalBean> journalList=new ArrayList<journalBean>();		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");

			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='RN' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN journal ON journal.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE journalID=? and duedt>sysdate)";		
			String query1="UPDATE  TRANSACTION_DETAILS SET STATUS='I' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN journal ON journal.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE journalID=? and duedt>sysdate)";

			String queryForItemTable="UPDATE ITEM SET item.STATUS='RN' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN journal ON journal.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE journalID=? and duedt>sysdate)";
			String queryForItemTable1="UPDATE ITEM SET item.STATUS='I' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN journal ON journal.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE journalID=? and duedt>sysdate)";		

			String queryDate="SELECT TRANSACTION_DETAILS.RENEWALDT FROM TRANSACTION_DETAILS JOIN journal ON journal.ITEMID=TRANSACTION_DETAILS.ITEMID WHERE journal.journalID=? ";
			pstmt=con.prepareStatement(queryDate);
			pstmt.setInt(1, journalId);
			resultSet=pstmt.executeQuery();
			while(resultSet.next())
			{
				renewalDate=resultSet.getString(1);
			}
			if(renewalDate!=null)
			{
				pstmt1=con.prepareStatement(queryForItemTable);
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, journalId);
				pstmt1.setInt(1, journalId);
			}
			else
			{
				pstmt=con.prepareStatement(query1);
				pstmt1=con.prepareStatement(queryForItemTable1);
				pstmt.setInt(1, journalId);
				pstmt1.setInt(1, journalId);
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
	public int getJournalCount() {
		// TODO Auto-generated method stub
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT count(ITEMID) FROM JOURNAL");
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

	public ArrayList<JournalBean> getJournalList(int displayStart,
			int displayEnd, String sortDir, int sortCol, int displayLength,
			String query) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		ArrayList<JournalBean> list1=new ArrayList<JournalBean>();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement(query);
			ResultSet result= pstmt.executeQuery();
			while(result.next())
			{
				JournalBean dao=new JournalBean();
				dao.setItemId(result.getInt("ITEMID"));
				dao.setTitle(result.getString("TITLE"));
				dao.setVolumeNo(result.getInt("VOLUMENO"));
				dao.setSubscriptionType(result.getString("SUBSCRIPTIONTYPE"));
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
}

