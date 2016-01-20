/**
 * 
 */
package com.tcs.ilp.lms.dao;



import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.ilp.lms.bean.Defaultersbean;
import com.tcs.ilp.lms.bean.FineBean;
import com.tcs.ilp.lms.bean.SearchBean;



public class FineDao {
	 private Connection conn =null;
	/**
	 * Add reason for canceling fine in the fine reason table
	 * @param FineBean
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @return void
	 *
	 */
	public  void addFineReason(FineBean fbean)
	{	
		System.out.println("in addfine reason dao"+fbean.getReason());
		System.out.println(fbean.getFineid());
		
		 try {
			
				Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP","a63d", "a63d");
			PreparedStatement statement =conn.prepareStatement("INSERT INTO FINE_REASON VALUES(?,?,?)");
			statement.setInt(1, fbean.getFineid());
			statement.setString(2, fbean.getReason());
			statement.setInt(3,730702);
			
			statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try{
			if(conn!=null)
			{
				conn.close();
			}
			}catch(SQLException e){e.printStackTrace();}
		}
		
	}
	
	/**
	 *Updates Fine status of a particular transaction id as C in fine details table
	 * @param  transid,status
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @return void
	 *
	 */
	public void updatedefaulterslist(int transid,String status)
	{ 
		System.out.println("in deflist");
	
		try{
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 conn= DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP","a63d", "a63d");
			 PreparedStatement statement = conn.prepareStatement("UPDATE FINE_DETAILSTABLE SET STATUS =? WHERE TRANSACTIONID=?");
			 statement.setString(1, status);
			 statement.setInt(2, transid);
			 statement.executeUpdate();
			
		}catch(SQLException e ){
			e.printStackTrace();
			
		}
		catch(ClassNotFoundException ce )
		{
			ce.printStackTrace();
		}
		finally{
			try{
			if(conn!=null)
			{
				conn.close();
			}
			}catch(SQLException e){e.printStackTrace();}
		}
		
	}
	
	/**
	 * Updates Item status of a particular transaction id in transaction table
	 * @param transid,status
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @return void
	 *
	 */
	public void updateItemStatus(int transid,String status)
	{
		System.out.println("in itemlist");
	
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 conn= DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP","a63d", "a63d");
		
			 PreparedStatement statement = conn.prepareStatement("UPDATE TRANSACTION_DETAILS SET STATUS=?,RETURNDT=sysdate WHERE TRANSID=?");
			 statement.setString(1, status);
			 statement.setInt(2, transid);
			 statement.executeUpdate();
			
		}catch(SQLException e ){
			e.printStackTrace();
			
		}
		catch(ClassNotFoundException ce )
		{
			ce.printStackTrace();
		}
		finally{
			try{
			if(conn!=null)
			{
				conn.close();
			}
			}catch(SQLException e){e.printStackTrace();}
		}
		
		
	}
	/**
	 * Using member id and category this method searches if this member is present in the defaulter list or not
	 * if present gives an  ArrayList of defaulter bean type
	 * @param member id and category
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @return  ArrayList<Defaultersbean> 
	 *
	 */
	
	public  ArrayList<Defaultersbean>  search(int memid,String cat)
	{	
		
			ArrayList<Defaultersbean> defaulterslist = new ArrayList<Defaultersbean>();
			ArrayList<Integer> transidlist = new ArrayList<Integer>();
			ArrayList<Integer> memidlist = new ArrayList<Integer>();
			ArrayList<String> titleList = new ArrayList<String>();
			ArrayList<String> catList = new ArrayList<String>();
			ArrayList<Date> Datelist= new ArrayList<Date>();
	
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				 conn= DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP","a63d", "a63d");
				 PreparedStatement statement = conn.prepareStatement("SELECT t.TRANSID,t.MEMID,t.ITEM_TYPE,i.TITLE,t.DUEDT FROM ITEM i JOIN TRANSACTION_DETAILS t ON i.ITEMID=t.ITEMID WHERE t.MEMID=? AND t.ITEM_TYPE=? AND t.DUEDT<SYSDATE AND t.STATUS NOT IN ('R','L','P','R&P')");
				statement.setInt(1, memid) ;
				statement.setString(2, cat);
			 ResultSet result=statement.executeQuery();
			
			 while(result.next())
			 {
					transidlist.add(result.getInt(1));
					memidlist.add(result.getInt(2));
					titleList.add(result.getString(4));
					catList.add(result.getString(3));
					Datelist.add(result.getDate(5));
			
				 
			}
			 
			 System.out.println("This no of records are stored"+transidlist.size());
			 for(int i=0;i<transidlist.size();i++)
			 {
			 defaulterslist.add(new Defaultersbean());
				 
			 }
			 for(int i=0;i<transidlist.size();i++)
				{
				 defaulterslist.get(i).setTransid(transidlist.get(i));
				 defaulterslist.get(i).setMemid(memidlist.get(i));
				 defaulterslist.get(i).setTitle(titleList.get(i));
				 defaulterslist.get(i).setCat(catList.get(i));
				 defaulterslist.get(i).setDuedate(Datelist.get(i));
				
				 
				}
				for(Defaultersbean deflist:defaulterslist)
				{
					System.out.println("555"+deflist.getTransid()+deflist.getMemid()+deflist.getTitle()+deflist.getCat()+deflist.getDuedate());
				}
			 
			 
			}catch(SQLException e ){
				e.printStackTrace();
				
			}
			catch(ClassNotFoundException ce )
			{
				ce.printStackTrace();
			}
			finally{
				try{
				if(conn!=null)
				{
					conn.close();
				}
				}catch(SQLException e){e.printStackTrace();}
			}
			return defaulterslist;
			
		
		
	}
	public  ArrayList<Defaultersbean>  search1(int memId, String cat, ArrayList<Integer> transIds)
	{
		
		ResultSet result=null;
		Connection con=null;
		PreparedStatement pstmt=null;

		ArrayList<Defaultersbean> defaulterList=new ArrayList<Defaultersbean>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			System.out.println("member id is:"+memId+"and cateegory is:"+cat);
			String query="SELECT B.TITLE,A.DUEDT FROM TRANSACTION_DETAILS A JOIN ITEM B ON B.ITEMID = A.ITEMID WHERE A.MEMID=? AND A.ITEM_TYPE=? AND A.TRANSID=? AND A.DUEDT<SYSDATE AND A.STATUS IN ('I','RN')";		
			String query1="UPDATE  ITEM SET STATUS='AR' WHERE itemid IN(SELECT ITEM.ITEMID FROM ITEM JOIN RESERVATION_DETAILS ON ITEM.ITEMID = RESERVATION_DETAILS.ITEMID JOIN TRANSACTION_DETAILS ON ITEM.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transId=?)";
			String query5="UPDATE  TRANSACTION_DETAILS SET STATUS='R',RETURNDT=SYSDATE WHERE transId=? ";		
			String query2="UPDATE  ITEM SET STATUS='R' WHERE itemid IN(SELECT ITEM.ITEMID FROM ITEM JOIN TRANSACTION_DETAILS ON ITEM.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transid=?)";

			String query3="SELECT ITEMID FROM TRANSACTION_DETAILS WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS WHERE transid=?)";
			String query4="UPDATE RESERVATION_DETAILS SET STATUS=STATUS-1 WHERE ITEMID=? AND STATUS NOT IN (0)";
			
			for(Integer transId:transIds)
			{
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, memId);
				pstmt.setString(2, cat);
				pstmt.setInt(3, transId.intValue());
				result=pstmt.executeQuery();
				while(result.next())
				{
					int i=0;
					Defaultersbean tBean=new Defaultersbean();
					tBean.setTransid(transId.intValue());
					tBean.setMemid(memId);
					tBean.setTitle(result.getString(1));
					tBean.setDuedate(result.getDate(2));
					//tBean.setCat(result.getString(5));
					System.out.println(i++);
					
					defaulterList.add(tBean);								
				}
			}
			for(Defaultersbean defaulter:defaulterList)
			{
				for(int transactionId:transIds)
				{
				if(defaulter.getTransid()!=transactionId)
				{
					
					
					pstmt=con.prepareStatement(query1);
					pstmt.setInt(1, transactionId);
					int result1=pstmt.executeUpdate();
					
			
					pstmt=con.prepareStatement(query5);
					pstmt.setInt(1, transactionId);
					int result2=pstmt.executeUpdate();
					
					
					if(result1==0)
					{
					pstmt=con.prepareStatement(query2);
					pstmt.setInt(1, transactionId);
					result2=pstmt.executeUpdate();
					System.out.println("result2 is"+result2);
					}
					else if(result1==1){
						int itemId=0;
						pstmt=con.prepareStatement(query3);
						pstmt.setInt(1, transactionId);
						ResultSet result3=pstmt.executeQuery();
						while(result3.next())
						{
							itemId=result3.getInt(1);
							System.out.println(itemId);
							
						}
						pstmt=con.prepareStatement(query4);
						pstmt.setInt(1, itemId);
						int result4=pstmt.executeUpdate();
						System.out.println(result4);
					}
				}
				}
			}
			
			System.out.println("size of issued defaulterList:"+defaulterList.size());

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
		return defaulterList;
	}
	
	public  ArrayList<Defaultersbean>  search2(int memId,ArrayList<Integer> transIds)
	{
		
		ResultSet result=null;
		Connection con=null;
		PreparedStatement pstmt=null;

		ArrayList<Defaultersbean> defaulterList=new ArrayList<Defaultersbean>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			System.out.println("member id is:"+memId);
			String query="SELECT B.TITLE,A.DUEDT,A.ITEM_TYPE FROM TRANSACTION_DETAILS A JOIN ITEM B ON B.ITEMID = A.ITEMID WHERE A.MEMID=? AND AND A.TRANSID=? AND A.DUEDT<SYSDATE AND A.STATUS IN ('I','RN')";		
			String query1="UPDATE  ITEM SET STATUS='AR' WHERE itemid IN(SELECT ITEM.ITEMID FROM ITEM JOIN RESERVATION_DETAILS ON ITEM.ITEMID = RESERVATION_DETAILS.ITEMID JOIN TRANSACTION_DETAILS ON ITEM.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transId=?)";
			String query5="UPDATE  TRANSACTION_DETAILS SET STATUS='R',RETURNDT=SYSDATE WHERE transId=? ";		
			String query2="UPDATE  ITEM SET STATUS='R' WHERE itemid IN(SELECT ITEM.ITEMID FROM ITEM JOIN TRANSACTION_DETAILS ON ITEM.ITEMID = TRANSACTION_DETAILS.ITEMID WHERE transid=?)";

			String query3="SELECT ITEMID FROM TRANSACTION_DETAILS WHERE itemid IN(SELECT TRANSACTION_DETAILS.ITEMID FROM TRANSACTION_DETAILS WHERE transid=?)";
			String query4="UPDATE RESERVATION_DETAILS SET STATUS=STATUS-1 WHERE ITEMID=? AND STATUS NOT IN (0)";
			
			for(Integer transId:transIds)
			{
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, memId);
				pstmt.setInt(2, transId.intValue());
				result=pstmt.executeQuery();
				while(result.next())
				{
				
					Defaultersbean tBean=new Defaultersbean();
					tBean.setTransid(transId.intValue());
					tBean.setMemid(memId);
					tBean.setTitle(result.getString(1));
					tBean.setDuedate(result.getDate(2));
					tBean.setCat(result.getString(3));
					defaulterList.add(tBean);								
				}
			}
			for(Defaultersbean defaulter:defaulterList)
			{
				for(int transactionId:transIds)
				{
				if(defaulter.getTransid()!=transactionId)
				{
					
					
					pstmt=con.prepareStatement(query1);
					pstmt.setInt(1, transactionId);
					int result1=pstmt.executeUpdate();
					
			
					pstmt=con.prepareStatement(query5);
					pstmt.setInt(1, transactionId);
					int result2=pstmt.executeUpdate();
					
					
					if(result1==0)
					{
					pstmt=con.prepareStatement(query2);
					pstmt.setInt(1, transactionId);
					result2=pstmt.executeUpdate();
					System.out.println("result2 is"+result2);
					}
					else if(result1==1){
						int itemId=0;
						pstmt=con.prepareStatement(query3);
						pstmt.setInt(1, transactionId);
						ResultSet result3=pstmt.executeQuery();
						while(result3.next())
						{
							itemId=result3.getInt(1);
							System.out.println(itemId);
							
						}
						pstmt=con.prepareStatement(query4);
						pstmt.setInt(1, itemId);
						int result4=pstmt.executeUpdate();
						System.out.println(result4);
					}
				}
				}
			}
			
			System.out.println("size of issued defaulterList:"+defaulterList.size());

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
		return defaulterList;
	}
	/**
	 * Using member id and category this method searches if this member is present in the defaulter list or not
	 * if present gives an  ArrayList of defaulter bean type
	 * @param member id and category
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @return  ArrayList<Defaultersbean> 
	 *
	 */
	
	public  ArrayList<Defaultersbean>  search()
	{	
		
			ArrayList<Defaultersbean> defaulterslist = new ArrayList<Defaultersbean>();
			ArrayList<Integer> transidlist = new ArrayList<Integer>();
			ArrayList<Integer> memidlist = new ArrayList<Integer>();
			ArrayList<String> titleList = new ArrayList<String>();
			ArrayList<String> catList = new ArrayList<String>();
			ArrayList<Date> Datelist= new ArrayList<Date>();
	
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				 conn= DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP","a63d", "a63d");
				 PreparedStatement statement = conn.prepareStatement("SELECT t.TRANSID,t.MEMID,t.ITEM_TYPE,i.TITLE,t.DUEDT FROM ITEM i JOIN TRANSACTION_DETAILS t ON i.ITEMID=t.ITEMID WHERE t.DUEDT<SYSDATE AND t.STATUS NOT IN ('R','L','P','R&P')");
			
			 ResultSet result=statement.executeQuery();
			
			 while(result.next())
			 {
					transidlist.add(result.getInt(1));
					memidlist.add(result.getInt(2));
					titleList.add(result.getString(4));
					catList.add(result.getString(3));
					Datelist.add(result.getDate(5));
			
				 
			}
			 
			 System.out.println("This no of records are stored"+transidlist.size());
			 for(int i=0;i<transidlist.size();i++)
			 {
			 defaulterslist.add(new Defaultersbean());
				 
			 }
			 for(int i=0;i<transidlist.size();i++)
				{
				 defaulterslist.get(i).setTransid(transidlist.get(i));
				 defaulterslist.get(i).setMemid(memidlist.get(i));
				 defaulterslist.get(i).setTitle(titleList.get(i));
				 defaulterslist.get(i).setCat(catList.get(i));
				 defaulterslist.get(i).setDuedate(Datelist.get(i));
				
				 
				}
				for(Defaultersbean deflist:defaulterslist)
				{
					System.out.println("555"+deflist.getTransid()+deflist.getMemid()+deflist.getTitle()+deflist.getCat()+deflist.getDuedate());
				}
			 
			 
			}catch(SQLException e ){
				e.printStackTrace();
				
			}
			catch(ClassNotFoundException ce )
			{
				ce.printStackTrace();
			}
			finally{
				try{
				if(conn!=null)
				{
					conn.close();
				}
				}catch(SQLException e){e.printStackTrace();}
			}
			return defaulterslist;
			
		
		
	}
	/**
	 * Adds Fine details to the fine details page
	 * @param FineBean
	 * @Exception none
	 * @since JDK 1.6.0_22
	 * @return void
	 *
	 */
	public void addFineDetails(FineBean finebean)
	{
	
		try{
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 conn= DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP","a63d", "a63d");
			 PreparedStatement statement = conn.prepareStatement("INSERT INTO FINE_DETAILSTABLE VALUES(?,?,?,?,?)");
			 statement.setInt(1, finebean.getFineid());
			 statement.setInt(2, finebean.getTransid());
			 statement.setDouble(3, finebean.getFineamount());
			 statement.setString(4, finebean.getPaydate());
			 statement.setString(5, "P");
			 statement.executeQuery();
		  }catch(SQLException e )
		  {
			e.printStackTrace();
			
		  }
		 catch(ClassNotFoundException ce )
		 {
			ce.printStackTrace();
		 }
		 finally{
			      try{
			           if(conn!=null)
			           {
			        	   conn.close();
			           }
			      	}catch(SQLException e){e.printStackTrace();}
		       }
		
	}
	
	public   ArrayList<Defaultersbean>  search(SearchBean sbean)
	{	
			StringBuilder query= new StringBuilder();
			ArrayList<Defaultersbean> defaulterslist = new ArrayList<Defaultersbean>();
			ArrayList<Integer> transidlist = new ArrayList<Integer>();
			ArrayList<Integer> memidlist = new ArrayList<Integer>();
			ArrayList<String> titleList = new ArrayList<String>();
			ArrayList<String> catList = new ArrayList<String>();
			ArrayList<Date> Datelist= new ArrayList<Date>();
			Connection conn=null;
			System.out.println("in search");
			query.append("SELECT t.TRANSID,t.MEMID,t.ITEM_TYPE,i.TITLE,t.DUEDT FROM ITEM i JOIN TRANSACTION_DETAILS t ON i.ITEMID=t.ITEMID WHERE t.DUEDT<SYSDATE AND t.STATUS NOT IN ('R','L','P','R&P')");
			try{
				int index=1;
				Class.forName("oracle.jdbc.driver.OracleDriver");
				 conn= DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP","a63d", "a63d");
				 if(sbean.getMemid()!=0 )
				 {
					 query.append("AND t.MEMID=?");
					 System.out.println("member in");
					 
				 }
				 if(sbean.getCategory()!=null)
				 {
				
					 query.append("AND t.ITEM_TYPE=?");
					 System.out.println("IN CAT");
				 }
				 if(sbean.getFromDate()!=null && sbean.getToDate()!=null)
				 {
					 query.append("AND t.DUEDT>to_date(?,'dd-MM-yyyy')AND t.DUEDT< to_date(?,'dd-MM-yyyy')");
					 System.out.println("in date");
				 }
				
				
				 String query1=query.toString();
				 PreparedStatement statement = conn.prepareStatement(query1);
				 if(sbean.getMemid()!=0)
				 { System.out.println(index);
					statement.setInt(index,sbean.getMemid());
					index++;
				 }
				if(sbean.getCategory()!=null)
				 {	System.out.println(index);
					 statement.setString(index,sbean.getCategory() );
					 index++;
				 }
				  if( sbean.getFromDate()!=null && sbean.getToDate()!=null)
					 {System.out.println(index);
						statement.setString(index,sbean.getFromDate());
						statement.setString(++index,sbean.getToDate());
						
						System.out.println(index);
					 }
				
	
				 
				 ResultSet result=statement.executeQuery();
			
			 while(result.next())
			 {
					transidlist.add(result.getInt(1));
					memidlist.add(result.getInt(2));
					titleList.add(result.getString(4));
					catList.add(result.getString(3));
					Datelist.add(result.getDate(5));
			
				 
			}
			 
			 System.out.println("This no of records are stored"+transidlist.size());
			 for(int i=0;i<transidlist.size();i++)
			 {
			 defaulterslist.add(new Defaultersbean());
				 
			 }
			 for(int i=0;i<transidlist.size();i++)
				{
				 defaulterslist.get(i).setTransid(transidlist.get(i));
				 defaulterslist.get(i).setMemid(memidlist.get(i));
				 defaulterslist.get(i).setTitle(titleList.get(i));
				 defaulterslist.get(i).setCat(catList.get(i));
				 defaulterslist.get(i).setDuedate(Datelist.get(i));
				
				 
				}
				for(Defaultersbean deflist:defaulterslist)
				{
					System.out.println("555"+deflist.getTransid()+deflist.getMemid()+deflist.getTitle()+deflist.getCat()+deflist.getDuedate());
				}
			 
			 
			}catch(SQLException e ){
				e.printStackTrace();
				
			}
			catch(ClassNotFoundException ce )
			{
				ce.printStackTrace();
			}
			finally{
				try{
				if(conn!=null)
				{
					conn.close();
				}
				}catch(SQLException e){e.printStackTrace();}
			}
			return defaulterslist;
			
		
		
	}
	

}
