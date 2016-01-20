/**
 * the DAO class for all transaction related methods
 */
package com.tcs.ilp.lms.dao;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tcs.ilp.lms.bean.*;

/**
 * @author P2-groupE
 *
 */
public class TransactionDAO 
{
	TransactionBean tB= new TransactionBean();
	
	public boolean checkMemId(int memId)
	{

		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;
		boolean flag=false;
		int count=0;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			
			String query="SELECT COUNT(USERID) FROM USERTABLE WHERE USERID=?";		
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			while(result.next())
			{
				count=result.getInt(1);
												
			}
			if(count==1)
			{
				flag=true;
			}
			else
				flag=false;
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
		return flag;
	}
	public List<TransactionBean> getTransactionDetails(int memId)
	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		List<TransactionBean> transactionList=new ArrayList<TransactionBean>();
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT * FROM(SELECT a.*, b.title FROM TRANSACTION_DETAILS a JOIN ITEM b ON b.ITEMID = a.ITEMID) WHERE MEMID=? AND STATUS NOT IN('RN','L', 'R','R&P', 'P')");
			pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			System.out.println("transId \titemId \tmemId \tissueDt \tdueDt \t\treturnDt \trenewalDt \tstatus \tstaffId");
			/*if (result==null)
			{
				
			}*/
			while(result.next())
			{
				TransactionBean tBean= new TransactionBean();
				tBean.setTransactionId(result.getInt(1));
				tBean.setItemId(result.getInt(2));
				tBean.setMemId(result.getInt(3));
				tBean.setIssueDate(result.getString(4));
				tBean.setDueDate(result.getString(5));
				tBean.setReturnDate(result.getString(6));
				tBean.setRenewalDate(result.getString(7));
				tBean.setStatus(result.getString(8));
				tBean.setStaffId(result.getInt(9));
				tBean.setItemType(result.getString(10));
				tBean.setItemTitle(result.getString(11));
				//System.out.println(result.getInt(1)+"\t\t"+result.getInt(2)+"\t"+result.getInt(3)+"\t"+result.getString(4)+"\t"+result.getString(5)+"\t"+result.getString(6)+"\t\t"+result.getString(7)+"\t\t"+result.getString(8)+"\t"+result.getInt(9)+"\t\t"+result.getInt(10)+"\t"+result.getInt(11));	
				transactionList.add(tBean);
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
		return transactionList;
	}	
	
	public TransactionBean updateStatusRenewed(int transId)
	{
		int resultT;
		int resultI;
		int resultDueDt;
		int resultRenDt;
		ResultSet result;
		//String newDueDt;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		TransactionBean tBean= new TransactionBean();
		//List<BookBean> bookList=new ArrayList<BookBean>();		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			
			String queryUpdateDueDt="UPDATE TRANSACTION_DETAILS SET DUEDT=( duedt + interval '15' day )WHERE TRANSID=?";
			pstmt=con.prepareStatement(queryUpdateDueDt);
			pstmt.setInt(1, transId);
			resultDueDt=pstmt.executeUpdate();
			
			String queryUpdateRenewalDt="UPDATE TRANSACTION_DETAILS SET RENEWALDT=SYSDATE WHERE TRANSID=?";
			pstmt=con.prepareStatement(queryUpdateRenewalDt);
			pstmt.setInt(1, transId);
			resultRenDt=pstmt.executeUpdate();
			
			String queryTrans="UPDATE  TRANSACTION_DETAILS SET STATUS='RN' WHERE TRANSID=?";			
			pstmt=con.prepareStatement(queryTrans);
			pstmt.setInt(1, transId);
			resultT=pstmt.executeUpdate();
			
			String queryItem="UPDATE  ITEM SET STATUS='RN' WHERE itemid IN(SELECT itemid FROM TRANSACTION_DETAILS WHERE TRANSID=?)";
			pstmt=con.prepareStatement(queryItem);
			pstmt.setInt(1, transId);
			resultI=pstmt.executeUpdate();
			
			String queryGetDueDt="SELECT duedt FROM TRANSACTION_DETAILS WHERE TRANSID=?";
			pstmt=con.prepareStatement(queryGetDueDt);
			pstmt.setInt(1, transId);
			result=pstmt.executeQuery();
			
			while(result.next())
			{
				tBean.setDueDate(result.getString(1));
				//newDueDt=result.getString(1);
				System.out.println("Dao new Due dt:"+result.getString(1));
				//System.out.println("hi");
			}	
			//System.out.println("result is"+resultT);
			//System.out.println("result is"+resultI);
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
		return tBean;
		//return newDueDt;
	}
			
	public List<TransactionBean> viewList(int memId)
	{
		ResultSet result;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		List<TransactionBean> transactionList=new ArrayList<TransactionBean>();
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT * FROM(SELECT a.*, b.title FROM TRANSACTION_DETAILS a JOIN ITEM b ON b.ITEMID = a.ITEMID) WHERE MEMID=? AND STATUS  IN('RN','I')");
			pstmt.setInt(1, memId);
			result=pstmt.executeQuery();
			
			while(result.next())
			{
				TransactionBean tBean= new TransactionBean();
				tBean.setTransactionId(result.getInt(1));
				tBean.setItemId(result.getInt(2));
				tBean.setMemId(result.getInt(3));
				tBean.setIssueDate(result.getString(4));
				tBean.setDueDate(result.getString(5));
				tBean.setReturnDate(result.getString(6));
				tBean.setRenewalDate(result.getString(7));
				tBean.setStatus(result.getString(8));
				tBean.setStaffId(result.getInt(9));
				tBean.setItemType(result.getString(10));
				tBean.setItemTitle(result.getString(11));
				//System.out.println(result.getInt(1)+"\t\t"+result.getInt(2)+"\t"+result.getInt(3)+"\t"+result.getString(4)+"\t"+result.getString(5)+"\t"+result.getString(6)+"\t\t"+result.getString(7)+"\t\t"+result.getString(8)+"\t"+result.getInt(9)+"\t\t"+result.getInt(10)+"\t"+result.getInt(11));	
				transactionList.add(tBean);
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
		return transactionList;
	}	
	public void updateListStatus(int transId)
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
	
	/**
	 * the DAO class for all transaction related methods
	 */
	
	public int checkItemsPermission(String member_type,String item_type)
	{
int itemPermission=0;
		//int permission = 0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
		
			String s="SELECT "+item_type+"Number FROM masterprofile where upper(membertype)=upper('"+member_type+"')";
	
		pstmt=con.prepareStatement(s);
		ResultSet rst9=pstmt.executeQuery();
		System.out.println("line 1");
        while(rst9.next()){
      
		itemPermission = rst9.getInt(1);	
				System.out.println("permission of "+item_type+" is:  "+itemPermission);	
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
	
	
	
	
	
	return itemPermission;
	
	}
	
	public ArrayList<BookBean1> listWithFine(ArrayList<Integer> idStatus)
	{
		ArrayList<BookBean1> tBeanList= new ArrayList<BookBean1>();
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
		for(Integer id:idStatus){
			String s="SELECT A.MEMID,usertable.firstname,A.ITEMID,item.TITLE,issuedt,duedt,cost FROM TRANSACTION_DETAILS A JOIN ITEM ON ITEM.ITEMID = A.ITEMID JOIN USERTABLE ON USERTABLE.USERID = A.MEMID WHERE A.ITEMID=?";	
			pstmt=con.prepareStatement(s);
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
		
        while(rs.next())
        {
        	BookBean1 tBean= new BookBean1();
      tBean.setMemId(rs.getInt(1));
      tBean.setMemberName(rs.getString(2));
      tBean.setItemId(rs.getInt(3));
      tBean.setTitle(rs.getString(4));
      tBean.setIssuedDate(rs.getDate(5));
      tBean.setDueDate(rs.getDate(6));
      tBean.setCost(rs.getInt(7));
      tBeanList.add(tBean);
		
		}
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
		return  tBeanList;
		
		
		
	}
			
					
	public int getItemsAssigned(int memberID,String item_type)
	{
		
		int itemAssigned=0;
		int items_issued=0;
		int items_renew=0;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			
				
			String isu = "SELECT count(item_type) FROM TRANSACTION_DETAILS WHERE STATUS='I' AND upper(item_type) = upper('"+item_type+"') AND MEMID="+memberID;
		pstmt=con.prepareStatement(isu);
		ResultSet rst9=pstmt.executeQuery();
        while(rst9.next()){
			items_issued = rst9.getInt(1);	
					
		}
		System.out.println("number of items_isued : "+items_issued);
		
		String renew = "SELECT count(item_type) FROM TRANSACTION_DETAILS WHERE STATUS='RN' AND upper(item_type) = upper('"+item_type+"') AND MEMID="+memberID;

		pstmt=con.prepareStatement(renew);
		
		
		
		ResultSet rst8=pstmt.executeQuery();

		
		
		while(rst8.next()){
			items_renew = rst8.getInt(1);	
					
		}
		System.out.println("number of items_renew : "+items_renew);
		
itemAssigned = (items_issued+items_renew);
				
		System.out.println("Total " +item_type+" assigned : "+itemAssigned);		
				
					
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
		
	
	return itemAssigned;
	}
	
	
	
	
	public String checkMemberType(int memberID)
	{
		String member_type="";
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT membertype FROM MEMBERMAPPING WHERE userid="+memberID);
ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				member_type=rs.getString(1);
				
			}
					
			System.out.println("member type is : "+member_type);
			
			
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
				
		return member_type;
	}
	
	
	public String checkItem_type(int itemID)
	{
		String item_type="";
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT itemid FROM book WHERE ITEMID="+itemID);
ResultSet rs = pstmt.executeQuery();
System.out.println("adsad : "+rs);			

while(rs.next())
{
int a = rs.getInt(1);

if(a!=0)
{
item_type="book";	

}
}

pstmt=con.prepareStatement("SELECT itemid FROM cd WHERE ITEMID="+itemID);
ResultSet rs1 = pstmt.executeQuery();
System.out.println("adsad : "+rs1);			

while(rs1.next())
{
int a = rs1.getInt(1);

if(a!=0)
{
item_type="cd";	

}
}


pstmt=con.prepareStatement("SELECT itemid FROM journal WHERE ITEMID="+itemID);
ResultSet rs2 = pstmt.executeQuery();
System.out.println("adsad : "+rs2);			

while(rs2.next())
{
int a = rs2.getInt(1);

if(a!=0)
{
item_type="journal";	

}
}


pstmt=con.prepareStatement("SELECT itemid FROM magazine WHERE ITEMID="+itemID);
ResultSet rs3 = pstmt.executeQuery();
System.out.println("adsad : "+rs3);			

while(rs3.next())
{
int a = rs3.getInt(1);

if(a!=0)
{
item_type="magazine";	

}
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
				
		System.out.println("item_type : "+item_type);
		return item_type;
		
	}
	
	
	

	
	
	public String issueItem(int memberID,int itemID,String item_type)
	{
	String issue="";
			
		Calendar c = Calendar.getInstance();  
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String issue_date=sdf.format(c.getTime());
		System.out.println("issue date is : "+issue_date);
		Calendar c1 = Calendar.getInstance();    
		c1.setTime(c.getTime());
		c1.add(Calendar.DATE, 21);
	    String due_date=sdf.format(c1.getTime());
        System.out.println("due date is : "+due_date);	
		System.out.println("line 1");
		
		
		
			// TODO Auto-generated method stub
			int result=0;
			Connection con=null;
			PreparedStatement pstmt=null;
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
				pstmt=con.prepareStatement("select count(transid) from transaction_details");
				System.out.println("line 2");
				ResultSet rst=pstmt.executeQuery();
				int transactionCount=0;
				while(rst.next()){
					transactionCount = rst.getInt(1);	
							System.out.println("number of elements : "+transactionCount);
				}
				System.out.println("line 3");
				
				int transactionID = (2104+transactionCount);
				pstmt=con.prepareStatement("insert into transaction_details values(?,?,?,?,?,?,?,?,?,?)");
				System.out.println("line 4");
				pstmt.setInt(1,transactionID );
				System.out.println("line 5");
				pstmt.setInt(2, itemID);
				System.out.println("line 6");
				pstmt.setInt(3, memberID);
				System.out.println("line 7");
				pstmt.setString(4, issue_date);
				pstmt.setString(5, due_date);
				pstmt.setString(6,null);
				pstmt.setString(7,null);
				System.out.println("line 8");
				pstmt.setString(8,"I");
				pstmt.setInt(9,730701);
				System.out.println("line 9");
				pstmt.setString(10,item_type);			
				System.out.println("line 10");
				pstmt.executeUpdate();
				System.out.println("line 11");
				issue="issued";
				System.out.println("line 12");
				
			//	if(issue=="issued")
				//{
					
					//pstmt=con.prepareStatement("UPDATE item SET STATUS='I' WHERE ITEMID="+itemID);	
					//pstmt.executeUpdate();
					
				//}
				
							
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
				
				//System.out.println("hello how are : "+item_type +" issued successfully");
					
				return issue;
	
	}

		
	
	public void changeStatus(int itemID)
	{
					// TODO Auto-generated method stub
			int result=0;
			Connection con=null;
			PreparedStatement pstmt=null;
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
					pstmt=con.prepareStatement("UPDATE item SET STATUS='I' WHERE ITEMID="+itemID);	
					pstmt.executeUpdate();
					System.out.println("status changed successfully !!!");
					
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
	
	
	
	
	public String validateMember(int memberID)	
	{
int memID=0;

String validMember="";
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT userid FROM USERTABLE WHERE USERID="+memberID);		
		
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				
	memID=rs.getInt(1);			
				
			}
			
			if(memID>0)
			{
				System.out.println("this is valid member");
		validMember="true";
			}
			
			else
			{
				System.out.println("this is invalid member");
				
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
		
	
		return validMember;
		
				
	}
	
	
	
	public String validateItem(int itemID,String item_type)
	{
		String validItem="";
		int validateItem =0;
		
		
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT itemid FROM "+item_type+" WHERE itemid="+itemID);		
		
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				
	validateItem=rs.getInt(1);			
				
			}
			
			if(validateItem>0)
			{
				System.out.println("this is valid "+item_type);
		validItem="true";
			}
			
			else
			{
				System.out.println("this is invalid "+item_type);
				
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
			System.out.println(validItem);
		
		return validItem;
	}
	
	public static String checkItemStatus(int itemID)
	{
		String itemStatus="";
			Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT status FROM item WHERE ITEMID="+itemID);		
		
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				
	itemStatus = rs.getString(1);			
				
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
			
			
	System.out.println("status is : "+itemStatus);
		
		return itemStatus;
	}

	
	
	
	
	public float checkAmount(int itemID)	
	{
		float amount = 0;
		Connection con=null;
	PreparedStatement pstmt=null;
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
		pstmt=con.prepareStatement("SELECT cost FROM ITEM WHERE ITEMID="+itemID);		
	
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			
amount = rs.getFloat(1);			
			
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
		
		
System.out.println(itemID+" amount is : "+amount);
	
		
		
		return (5*amount)/100;
	}
	
	
	public int checkAdvanceReservation(int memberID,String item_type)
	{		int totalAdvanceRes = 0;
		Connection con=null;
	PreparedStatement pstmt=null;
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
		pstmt=con.prepareStatement("SELECT count(itemid) FROM "+item_type+" WHERE ITEMID IN(SELECT itemid FROM RESERVATION_DETAILS WHERE memid="+memberID+")");		
	
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			
			totalAdvanceRes = rs.getInt(1);			
			
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
		
		
System.out.println("total advance reservation of "+item_type+" is : "+totalAdvanceRes);
	
		
		
		return totalAdvanceRes;
	}
	
	
	public String checkAdvanceReservationOfParticularMember(int memberID,int itemID)
	{
		
		String AdvanceResOfParticularItem = "";
		int advanceResMemID=0;
		int advanceResStatus=12;
		Connection con=null;
	PreparedStatement pstmt=null;
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
		//pstmt=con.prepareStatement("SELECT itemid FROM "+item_type+" WHERE ITEMID IN(SELECT itemid FROM RESERVATION_DETAILS WHERE memid="+memberID+")");		
	
		pstmt=con.prepareStatement("SELECT memid FROM RESERVATION_DETAILS WHERE itemid="+itemID+" AND memid="+memberID);		

		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			
		advanceResMemID = rs.getInt(1);			
			System.out.println("advanceResMemID  "+advanceResMemID);
		}
		
		if(advanceResMemID==memberID)
		{
			pstmt=con.prepareStatement("select status from RESERVATION_DETAILS where itemid="+itemID+" AND memid="+memberID);
			ResultSet rs1 = pstmt.executeQuery();
			while(rs1.next())
			{
				
				advanceResStatus = rs1.getInt(1);			
			}
			
				if(advanceResStatus==0)
				{
					AdvanceResOfParticularItem = "true";
				}
				
				if(advanceResStatus!=0)
				{
					AdvanceResOfParticularItem = "You can't issue itemID "+itemID+" as waiting number is "+advanceResStatus;
					
				}
				
			
			
		}
		
		
		if(advanceResMemID!=memberID)
		{
			
			System.out.println("you don't have advance reservation for "+itemID+" itemID");
		return "you don't have advance reservation for "+itemID+" itemID";	
			
			
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
		
		
		System.out.println(" advance reservation of "+itemID+" is : "+AdvanceResOfParticularItem);	
		return AdvanceResOfParticularItem;
	}
	
	
	public BookBean searchItem(String item_type,int itemID)
	{
		

		BookBean been = new BookBean();
		
		
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT itemid,title,status  FROM item where ITEMID="+itemID);		
		
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				
				
				been.setItemId(rs.getInt(1));
				been.setTitle(rs.getString(2));
				//been.setSubject(rs.getString(3));
				been.setStatus(rs.getString(3));
	
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
	
		System.out.println(been.getTitle());
	return been;
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//public static void main(String as[])
	//{
		//TransactionDAO dao = new TransactionDAO();	
		//dao.checkAdvanceReservation(13,"magazine");
		
		//dao.checkItem_type(115009);
		//System.out.println("bgyufhg: ");
//	System.out.println(dao.searchItem("book"));	
	//System.out.println(dao.searchItem1("cd"));		
		//dao.checkAdvanceReservationOfParticularMember(9, 5009);
		//System.out.println("subString : "+dao.checkAdvanceReservationOfParticularMember(9, 5009).substring(0, 5));
	//	System.out.println("waiting number is : "+AdvanceResOfParticularItem);
//	}
}

	