package com.tcs.ilp.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tcs.ilp.lms.bean.MagazineBean;
import com.tcs.ilp.lms.bean.MagazineBean1;

public class MagazineDao 
{
	public int addMagazine(MagazineBean magazineBean)
	{
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("insert into item(title,itemid,cost,status,yearofpublication,placeofpublication) values('"+magazineBean.getTitle().toUpperCase()+"',SEQ_ITEM_ID.nextval,"+magazineBean.getCost()+",'Available',"+magazineBean.getYearOfPublication()+",'"+magazineBean.getPlaceOfPublication().toUpperCase()+"')");
			result=pstmt.executeUpdate();
			if(result==1)
			{
				pstmt=con.prepareStatement("SELECT ITEMID FROM ITEM WHERE ROWNUM =1 ORDER BY ITEMID DESC ");
				ResultSet rs1=pstmt.executeQuery();
				while(rs1.next())
				{
					System.out.println("insert");
					result = rs1.getInt(1);
					pstmt = con.prepareStatement("insert into magazine(magazineid,itemid,isbn,volumeno,subscriptiontype) values("+result+","+result+","+magazineBean.getIsbn()+","+magazineBean.getVolumeNo()+",'"+magazineBean.getSubscriptionType().toUpperCase()+"')");
					pstmt.executeUpdate();
					pstmt=con.prepareStatement("SELECT COUNT(*) FROM ADMINCHANGELOG");
					ResultSet rs2 = pstmt.executeQuery();
					rs2.next();
					int count = rs2.getInt(1) + 1;
					System.out.println(count);
					String statement = "A Magazine with Title : "+magazineBean.getTitle()+" has been added into the system";
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

	public int deleteMagazine(int itemId) {
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

	public MagazineBean getAttribute(MagazineBean magazineBean, String string) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement(string);
			//pstmt.setString(1, bookBean.getMemType());

			pstmt.setInt(1, magazineBean.getItemId());
			ResultSet result=pstmt.executeQuery();

			while(result.next()){
				magazineBean.setMagazineId(result.getInt(1));
				magazineBean.setIsbn(result.getLong(3));
				magazineBean.setVolumeNo(result.getInt(4));
				magazineBean.setSubscriptionType(result.getString(5));
				//itembean.setStatus(result.getString(6));

				/*masterBean.setNumOfBooks(result.getInt(3));
				masterBean.setNumOfCds(result.getInt(4));
				masterBean.setNumOfMagazines(result.getInt(5));
				masterBean.setNumOfJournals(result.getInt(6));
				masterBean.setAmount(result.getDouble(7));*/
			}
			System.out.println(magazineBean.getItemId());
			pstmt=con.prepareStatement("SELECT status FROM ITEM WHERE ITEMID=?");
			pstmt.setInt(1, magazineBean.getItemId());
			result=pstmt.executeQuery();

			while(result.next()){
				magazineBean.setStatus(result.getString(1));
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
		return magazineBean;

	}

	public MagazineBean getAttributeMagazine(MagazineBean magazineBean,
			String string) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement(string);
			//pstmt.setString(1, bookBean.getMemType());

			pstmt.setString(1, magazineBean.getTitle());
			ResultSet result=pstmt.executeQuery();

			while(result.next()){
				magazineBean.setMagazineId(result.getInt(1));
				magazineBean.setIsbn(result.getLong(3));
				magazineBean.setVolumeNo(result.getInt(4));
				magazineBean.setSubscriptionType(result.getString(5));
				//itembean.setStatus(result.getString(6));

				/*masterBean.setNumOfBooks(result.getInt(3));
				masterBean.setNumOfCds(result.getInt(4));
				masterBean.setNumOfMagazines(result.getInt(5));
				masterBean.setNumOfJournals(result.getInt(6));
				masterBean.setAmount(result.getDouble(7));*/
			}
			System.out.println(magazineBean.getItemId());
			pstmt=con.prepareStatement("SELECT status FROM ITEM WHERE TITLE=?");
			pstmt.setString(1, magazineBean.getTitle());
			result=pstmt.executeQuery();

			while(result.next()){
				magazineBean.setStatus(result.getString(1));
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
		return magazineBean;
	}

	public List<MagazineBean1> viewMagazineList(int memId)
	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;

		List<MagazineBean1> magazineList=new ArrayList<MagazineBean1>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");


			String query="SELECT magazineid,title,issuedt,duedt,cost,transid FROM TRANSACTION_DETAILS JOIN MAGAZINE ON MAGAZINE.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN ITEM ON ITEM.ITEMID = MAGAZINE.ITEMID WHERE MEMID=? AND TRANSACTION_DETAILS.STATUS IN('I','RN')";		

			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			while(result.next())
			{
				MagazineBean1 mBean=new MagazineBean1();
				mBean.setMagazineId(result.getInt(1));
				mBean.setMagazineName(result.getString(2));
				mBean.setIssuedDate(result.getDate(3));
				mBean.setDueDate(result.getDate(4));
				mBean.setCost(result.getInt(5));
				mBean.setTxnId(result.getInt(6));
				magazineList.add(mBean);
				System.out.println(result.getString(2));

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

		return magazineList;
	}
	public void updateStatusMag(int magazineId)
	{
		int result;
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		//List<BookBean> bookList=new ArrayList<BookBean>();		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='L' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN magazine ON magazine.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE magazineid=?) ";		
			String query1="UPDATE  item SET STATUS='L' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN magazine ON magazine.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE magazineid=?) ";

			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, magazineId);
			pstmt1=con.prepareStatement(query1);
			pstmt1.setInt(1, magazineId);
			result=pstmt.executeUpdate();
			int result1=pstmt1.executeUpdate();
			System.out.println("update satus magazine "+result);
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
			String query1="UPDATE  item SET STATUS='P' WHERE itemid=? ";

			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, itemId);
			pstmt1=con.prepareStatement(query1);
			pstmt1.setInt(1, itemId);
			result=pstmt.executeUpdate();
			int result1=pstmt1.executeUpdate();
			System.out.println("magazine status paid is"+result);
			System.out.println("magazine status for paid is"+result1);

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
			String query1="UPDATE  ITEM SET STATUS='AR' WHERE itemid IN(SELECT ITEM.ITEMID FROM ITEM JOIN RESERVATION_DETAILS ON ITEM.ITEMID = RESERVATION_DETAILS.ITEMID JOIN magazine ON magazine.ITEMID = ITEM.ITEMID WHERE transid=?)";
			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='R',RETURNDT=SYSDATE WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN magazine ON magazine.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transid=?) ";		
			String query2="UPDATE  ITEM SET STATUS='R' WHERE itemid IN(SELECT ITEM.ITEMID FROM ITEM JOIN TRANSACTION_DETAILS ON ITEM.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN magazine ON magazine.ITEMID = ITEM.ITEMID WHERE transid=?)";
			String query3="SELECT ITEMID FROM TRANSACTION_DETAILS WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN MAGAZINE ON MAGAZINE.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transid=?)";
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
	public List<MagazineBean1> viewLostMag(int memId)
	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;

		List<MagazineBean1> magazineList=new ArrayList<MagazineBean1>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");

			String query="SELECT magazineid,title,issuedt,duedt,cost FROM TRANSACTION_DETAILS JOIN magazine ON magazine.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN ITEM ON ITEM.ITEMID = magazine.ITEMID WHERE MEMID=? and TRANSACTION_DETAILS.STATUS='L'";		
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			while(result.next())
			{
				MagazineBean1 mBean=new MagazineBean1();
				mBean.setMagazineId(result.getInt(1));
				mBean.setMagazineName((result.getString(2)));
				mBean.setIssuedDate(result.getDate(3));
				mBean.setDueDate(result.getDate(4));
				mBean.setCost(result.getInt(5));
				magazineList.add(mBean);

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

		return magazineList;
	}

	public List<MagazineBean1> viewLostLibrarianMag()
	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;

		List<MagazineBean1> magazineList=new ArrayList<MagazineBean1>();
		//List<MemberBean> memberList=new ArrayList<MemberBean>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");

			String query="SELECT memid,magazineid,title,issuedt,duedt,cost,transid,usertable.firstname,itemid FROM TRANSACTION_DETAILS JOIN magazine ON magazine.ITEMID = TRANSACTION_DETAILS.ITEMID JOIN ITEM ON ITEM.ITEMID = magazine.ITEMID usertable on usertable.userid=TRANSACTION_DETAILS.memid WHERE TRANSACTION_DETAILS.STATUS='L'";		
			pstmt=con.prepareStatement(query);
			//pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			while(result.next())
			{
				MagazineBean1 mBean=new MagazineBean1();
				//MemberBean memBean=new MemberBean();
				mBean.setMemId(result.getInt(1));
				mBean.setMagazineId((result.getInt(2)));
				mBean.setMagazineName(result.getString(3));
				mBean.setIssuedDate(result.getDate(4));
				mBean.setDueDate(result.getDate(5));
				mBean.setCost(result.getInt(6));
				mBean.setTxnId(result.getInt(7));
				mBean.setMemberName(result.getString(8));
				mBean.setItemId(result.getInt(9));
				magazineList.add(mBean);


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

		return magazineList;
	}

	//----------
	public void cancelReportMagazine(int magazineId)
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

			String query="UPDATE  TRANSACTION_DETAILS SET STATUS='RN' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN magazine ON magazine.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE magazineid=? and duedt>sysdate)";		
			String query1="UPDATE  TRANSACTION_DETAILS SET STATUS='I' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN magazine ON magazine.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE magazineid=? and duedt>sysdate)";

			String queryForItemTable="UPDATE ITEM SET item.STATUS='RN' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN magazine ON magazine.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE magazineid=? and duedt>sysdate)";
			String queryForItemTable1="UPDATE ITEM SET item.STATUS='I' WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS JOIN magazine ON magazine.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE magazineid=? and duedt>sysdate)";		

			String queryDate="SELECT TRANSACTION_DETAILS.RENEWALDT FROM TRANSACTION_DETAILS JOIN magazine ON magazine.ITEMID=TRANSACTION_DETAILS.ITEMID WHERE magazine.magazineid=? ";
			pstmt=con.prepareStatement(queryDate);
			pstmt.setInt(1, magazineId);
			resultSet=pstmt.executeQuery();
			while(resultSet.next())
			{
				renewalDate=resultSet.getString(1);
			}
			if(renewalDate!=null)
			{
				pstmt1=con.prepareStatement(queryForItemTable);
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, magazineId);
				pstmt1.setInt(1, magazineId);
			}
			else
			{
				pstmt=con.prepareStatement(query1);
				pstmt1=con.prepareStatement(queryForItemTable1);
				pstmt.setInt(1, magazineId);
				pstmt1.setInt(1, magazineId);
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
	public int getMagazineCount() {
		// TODO Auto-generated method stub
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT count(ITEMID) FROM MAGAZINE");
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

	public ArrayList<MagazineBean> getMagazineList(int displayStart,
			int displayEnd, String sortDir, int sortCol, int displayLength,
			String query) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		ArrayList<MagazineBean> list1=new ArrayList<MagazineBean>();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement(query);
			ResultSet result= pstmt.executeQuery();
			while(result.next())
			{
				MagazineBean dao=new MagazineBean();
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
