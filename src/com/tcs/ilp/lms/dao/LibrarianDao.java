package com.tcs.ilp.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.ilp.lms.bean.UserBean;

public class LibrarianDao 
{
	public int addLibrarian(UserBean userBean)
	{
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("select count(userid) from usertable where identityId='"+userBean.getIdentityId()+"'");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				result = rs.getInt(1);
			}
			System.out.println(result);
			if(result!=1)
			{
				pstmt = con.prepareStatement("select count(identityid) from usertable where userid="+userBean.getUserId());
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					result = rs.getInt(1);
				}
				if(result!=1)
				{
					pstmt = con.prepareStatement("insert into usertable(userid,password,firstname,lastname,role,contactno,email,address,isfirstlogin,identityid) values("+userBean.getUserId()+",'"+userBean.getPassword()+"','"+userBean.getFirstName()+"','"+userBean.getLastName()+"','"+userBean.getRole()+"',"+userBean.getContact()+",'"+userBean.getEmail()+"','"+userBean.getAddress()+"','YES','"+userBean.getIdentityId()+"')");
					result=pstmt.executeUpdate();
					pstmt=con.prepareStatement("SELECT COUNT(*) FROM ADMINCHANGELOG");
					ResultSet rs2 = pstmt.executeQuery();
					rs2.next();
					int count = rs2.getInt(1) + 1;
					System.out.println(count);
					String statement = "An Librarian with UserID : "+userBean.getUserId()+" has been added to the system";
					pstmt=con.prepareStatement("INSERT INTO ADMINCHANGELOG VALUES("+count+",'"+statement+"')");
					pstmt.executeUpdate();
				}
				else
				{
					result= 3;
				}
			}
			else
			{
				result = 2;
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

	public ArrayList<String> getChangeLog() {
		// TODO Auto-generated method stub
		ArrayList<String> change = new ArrayList<String>();
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("SELECT * FROM (SELECT * FROM LIBRARIANCHANGELOG ORDER BY SLNO DESC) WHERE ROWNUM<20");
			ResultSet result= pstmt.executeQuery();
			while(result.next())
			{
				String change1 = result.getString(2);
				change.add(change1);				
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
		return change; 
	}
}
