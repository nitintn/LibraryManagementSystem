/**
 * 
 */
package com.tcs.ilp.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tcs.ilp.lms.utility.UtilityDB;

/**This will get the due date and return dates from the transaction table
 * @author 730783
 *
 */
public class BookTransactionDao {
	/**
	 * gives the duedate
	 * @param memberId
	 * @return
	 * @throws ParseException 
	 */
	public String getDueDate(String memberId) throws ParseException
	{
		Connection conn=null;
		String duedate=null;
		try{
		UtilityDB util = new UtilityDB();
		conn= util.createConnection();
		PreparedStatement statement=conn.prepareStatement("SELECT dueDt FROM transaction_details where assignedTo=?");
		statement.setString(1, memberId);
	ResultSet result=statement.executeQuery();
	duedate= result.getString(1);
		}catch(SQLException exception)
		{
			exception.printStackTrace();
		}catch(ClassNotFoundException classexception)
		{
			classexception.printStackTrace();
		}finally{
			try{
				if(conn!=null){
					conn.close();
					
				}
			}catch(SQLException exception){
				exception.printStackTrace();
			}
			
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		Date myDate=sdf.parse(duedate);
		System.out.println("date in transaction dao"+myDate);
		return duedate;
	}
	public String getReturnDate(String memberId) 
	{
		Connection conn=null;
		String returndate=null;
		try{
		UtilityDB util = new UtilityDB();
		conn= util.createConnection();
		PreparedStatement statement=conn.prepareStatement("SELECT returndate FROM transaction_details where assignedTo=?");
		statement.setString(1, memberId);
	ResultSet result=statement.executeQuery();
	returndate= result.getString(1);
		}catch(SQLException exception)
		{
			exception.printStackTrace();
		}catch(ClassNotFoundException classexception)
		{
			classexception.printStackTrace();
		}finally{
			try{
				if(conn!=null){
					conn.close();
					
				}
			}catch(SQLException exception){
				exception.printStackTrace();
			}
			
		}
		
		return returndate;
	}
	

}
