package com.tcs.ilp.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tcs.ilp.lms.bean.CdBean;
import com.tcs.ilp.lms.bean.CdBean1;

public class CdDao 
{
	public int addCd(CdBean cdBean)
	{
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("insert into item(title,itemid,cost,status,yearofpublication,placeofpublication) values('"+cdBean.getTitle().toUpperCase()+"',SEQ_ITEM_ID.nextval,"+cdBean.getCost()+",'Available',"+cdBean.getYearOfPublication()+",'"+cdBean.getPlaceOfPublication().toUpperCase()+"')");
			result=pstmt.executeUpdate();
			if(result==1)
			{
				System.out.println("entered");
				pstmt=con.prepareStatement("SELECT ITEMID FROM ITEM WHERE ROWNUM =1 ORDER BY ITEMID DESC ");
				ResultSet rs1=pstmt.executeQuery();
				while(rs1.next())
				{
					result = rs1.getInt(1);
					pstmt = con.prepareStatement("insert into cd(cdid,itemid,subject,publisher) values("+result+","+result+",'"+cdBean.getSubject()+"','"+cdBean.getPublisher().toUpperCase()+"')");
					result=pstmt.executeUpdate();
					pstmt=con.prepareStatement("SELECT COUNT(*) FROM ADMINCHANGELOG");
					ResultSet rs2 = pstmt.executeQuery();
					rs2.next();
					int count = rs2.getInt(1) + 1;
					System.out.println(count);
					String statement = "A CD with Title : "+cdBean.getTitle()+" has been added into the system";
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

	public int deleteCd(int itemId) {
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

	public CdBean getAttribute(CdBean cdBean, String string) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement(string);
			//pstmt.setString(1, bookBean.getMemType());

			pstmt.setInt(1, cdBean.getItemId());
			ResultSet result=pstmt.executeQuery();

			while(result.next()){
				cdBean.setCdId(result.getInt(1));
				cdBean.setSubject(result.getString(3));
				cdBean.setPublisher(result.getString(4));
				//itembean.setStatus(result.getString(6));

				/*masterBean.setNumOfBooks(result.getInt(3));
				masterBean.setNumOfCds(result.getInt(4));
				masterBean.setNumOfMagazines(result.getInt(5));
				masterBean.setNumOfJournals(result.getInt(6));
				masterBean.setAmount(result.getDouble(7));*/
			}
			System.out.println(cdBean.getItemId());
			pstmt=con.prepareStatement("SELECT status FROM ITEM WHERE ITEMID=?");
			pstmt.setInt(1, cdBean.getItemId());
			result=pstmt.executeQuery();

			while(result.next()){
				cdBean.setStatus(result.getString(1));
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
		return cdBean;
	}

	//search part

	public CdBean getAttributeCD(CdBean cdBean, String string) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement(string);
			//pstmt.setString(1, bookBean.getMemType());

			//pstmt.setLong(1, bookBean.getIsbn());
			pstmt.setString(1, cdBean.getTitle());

			ResultSet result=pstmt.executeQuery();

			while(result.next()){
				cdBean.setCdId(result.getInt(1));
				cdBean.setSubject(result.getString(3));
				cdBean.setPublisher(result.getString(4));
				//itembean.setStatus(result.getString(6));

				/*masterBean.setNumOfBooks(result.getInt(3));
				masterBean.setNumOfCds(result.getInt(4));
				masterBean.setNumOfMagazines(result.getInt(5));
				masterBean.setNumOfJournals(result.getInt(6));
				masterBean.setAmount(result.getDouble(7));*/
			}
			System.out.println(cdBean.getTitle());
			pstmt=con.prepareStatement("SELECT status FROM ITEM WHERE TITLE=?");
			//pstmt.setInt(1, bookBean.getItemId());
			pstmt.setString(1,cdBean.getTitle());
			result=pstmt.executeQuery();

			while(result.next()){ 
				cdBean.setStatus(result.getString(1));
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
		return cdBean;
	}

	public List<CdBean1> viewCdList(int memId)
	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;

		List<CdBean1> cdList=new ArrayList<CdBean1>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");


			String query="SELECT cdid,title,issuedt,duedt,cost,transid FROM TRANSACTION_DETAILS JOIN CD ON CD.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN ITEM ON ITEM.ITEMID = CD.ITEMID WHERE MEMID=? AND TRANSACTION_DETAILS.STATUS IN('I','RN')";		

			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			while(result.next())
			{
				CdBean1 tBean=new CdBean1();
				tBean.setCdId(result.getInt(1));
				tBean.setCdName(result.getString(2));
				tBean.setIssuedDate(result.getDate(3));
				tBean.setDueDate(result.getDate(4));
				tBean.setCost(result.getInt(5));
				tBean.setTxnId(result.getInt(6));
				cdList.add(tBean);


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

		return cdList;
	}
	public void updateStatusCd(int cdId)
	{
		int result;
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		//List<BookBean> bookList=new ArrayList<BookBean>();		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='L' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN cd ON cd.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE cdid=?) ";		
			String query1="UPDATE  item SET STATUS='L' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN cd ON cd.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE cdid=?) ";

			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, cdId);
			pstmt1=con.prepareStatement(query1);
			pstmt1.setInt(1, cdId);
			result=pstmt.executeUpdate();
			int result1=pstmt1.executeUpdate();
			System.out.println("update satus cd "+result);
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
			System.out.println("cd status paid is"+result);
			System.out.println("cd status for paid is"+result1);

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
			String query1="UPDATE  ITEM SET STATUS='AR' WHERE itemid IN(SELECT ITEM.ITEMID FROM ITEM JOIN RESERVATION_DETAILS ON ITEM.ITEMID = RESERVATION_DETAILS.ITEMID JOIN CD ON CD.ITEMID = ITEM.ITEMID JOIN TRANSACTION_DETAILS ON ITEM.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transId=?)";
			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='R',RETURNDT=SYSDATE WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN CD ON CD.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transid=?) ";		
			String query2="UPDATE  ITEM SET STATUS='R' WHERE itemid IN(SELECT ITEM.ITEMID FROM ITEM JOIN TRANSACTION_DETAILS ON ITEM.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN CD ON CD.ITEMID = ITEM.ITEMID WHERE transid=?)";
			String query3="SELECT ITEMID FROM TRANSACTION_DETAILS WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN CD ON cd.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transid=?)";
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

	public List<CdBean1> viewLostCD(int memId)

	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;

		List<CdBean1> cdList=new ArrayList<CdBean1>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");

			String query="SELECT cdid,title,issuedt,duedt,cost FROM TRANSACTION_DETAILS JOIN CD ON CD.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN ITEM ON ITEM.ITEMID = CD.ITEMID WHERE MEMID=? and TRANSACTION_DETAILS.STATUS='L'";		
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			while(result.next())
			{
				CdBean1 tBean=new CdBean1();
				tBean.setCdId(result.getInt(1));
				tBean.setCdName((result.getString(2)));
				tBean.setIssuedDate(result.getDate(3));
				tBean.setDueDate(result.getDate(4));
				tBean.setCost(result.getInt(5));
				cdList.add(tBean);

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

		return cdList;
	}

	public List<CdBean1> viewLostLibrarianCd()
	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;

		List<CdBean1> cdList=new ArrayList<CdBean1>();
		//List<MemberBean> memberList=new ArrayList<MemberBean>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");

			String query="SELECT memid,cdid,title,issuedt,duedt,cost,transid,usertable.firstname,itemid FROM TRANSACTION_DETAILS JOIN cd ON cd.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN ITEM ON ITEM.ITEMID = cd.ITEMID join usertable on usertable.userid=TRANSACTION_DETAILS.memid WHERE TRANSACTION_DETAILS.STATUS='L'";		
			pstmt=con.prepareStatement(query);
			//pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			while(result.next())
			{
				CdBean1 tBean=new CdBean1();
				//MemberBean memBean=new MemberBean();
				tBean.setMemId(result.getInt(1));
				tBean.setCdId((result.getInt(2)));
				tBean.setCdName(result.getString(3));
				tBean.setIssuedDate(result.getDate(4));
				tBean.setDueDate(result.getDate(5));
				tBean.setCost(result.getInt(6));
				tBean.setTxnId(result.getInt(7));
				tBean.setMemberName(result.getString(8));
				tBean.setItemId(result.getInt(9));
			
				cdList.add(tBean);


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

		return cdList;
	}

	//----------
	public void cancelReportCd(int cdId)
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

			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='RN' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN cd ON cd.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE CDID=? and duedt>sysdate)";		
			String query1="UPDATE  TRANSACTION_DETAILS SET STATUS='I' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN cd ON cd.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE CDID=? and duedt>sysdate)";

			String queryForItemTable="UPDATE ITEM SET item.STATUS='RN' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN cd ON cd.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE CDID=? and duedt>sysdate)";
			String queryForItemTable1="UPDATE ITEM SET item.STATUS='I' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN cd ON cd.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE CDID=? and duedt>sysdate)";		

			String queryDate="SELECT TRANSACTION_DETAILS.RENEWALDT FROM TRANSACTION_DETAILS JOIN cd ON cd.ITEMID=TRANSACTION_DETAILS.ITEMID WHERE cd.CDID=? ";
			pstmt=con.prepareStatement(queryDate);
			pstmt.setInt(1, cdId);
			resultSet=pstmt.executeQuery();
			while(resultSet.next())
			{
				renewalDate=resultSet.getString(1);
			}
			if(renewalDate!=null)
			{
				pstmt1=con.prepareStatement(queryForItemTable);
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, cdId);
				pstmt1.setInt(1, cdId);
			}
			else
			{
				pstmt=con.prepareStatement(query1);
				pstmt1=con.prepareStatement(queryForItemTable1);
				pstmt.setInt(1, cdId);
				pstmt1.setInt(1, cdId);
			}
			result=pstmt.executeUpdate();
			int result1=pstmt1.executeUpdate();
			System.out.println("in canecl cd report and updating status to previous"+result);
			System.out.println("in cancel cd report and updating status to previous"+result1);


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

	public ArrayList<CdBean> getCdList(int displayStart, int displayEnd,
			String sortDir, int sortCol, int displayLength, String query) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		ArrayList<CdBean> list1=new ArrayList<CdBean>();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement(query);
			ResultSet result= pstmt.executeQuery();
			while(result.next())
			{
				CdBean dao=new CdBean();
				dao.setItemId(result.getInt("ITEMID"));
				dao.setTitle(result.getString("TITLE"));
				dao.setSubject(result.getString("SUBJECT"));
				dao.setPublisher(result.getString("PUBLISHER"));
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

	public int getCdCount() {
		// TODO Auto-generated method stub
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT count(ITEMID) FROM CD");
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
