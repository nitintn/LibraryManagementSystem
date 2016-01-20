package com.tcs.ilp.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tcs.ilp.lms.bean.LoginBean;

public class LoginDao 
{
	public int validate(LoginBean loginBean)
	{
		int check = 0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("select count(userid) from usertable where userid="+loginBean.getUserName()+" and password='"+loginBean.getPassword()+"'");
			ResultSet result=pstmt.executeQuery();
			while(result.next())
			{
				check = result.getInt(1);
			}
			if(check==1)
			{
				String isfirst = "";
				pstmt=con.prepareStatement("select isfirstlogin from usertable where userid="+loginBean.getUserName()+" and password='"+loginBean.getPassword()+"'");
				result=pstmt.executeQuery();
				while(result.next())
				{
					isfirst = result.getString(1);
				}
				if(isfirst.equalsIgnoreCase("YES"))
				{
					check = 1;
				}
				else
				{
					check = 2;
				}
			}
			else
			{
				check = 3;
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
		return check;
	}
	
	public String getRole(LoginBean loginBean)
	{
		String role = "";
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("select role from usertable where userid="+loginBean.getUserName()+" and password='"+loginBean.getPassword()+"'");
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				role = result.getString(1);
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
		return role;
	}
	
	public int changePassword(String password, int userId)
	{
		int check = 0;
		String pass = "";
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("select password from usertable where userid="+userId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				pass = rs.getString(1);
			}
			if(pass.equalsIgnoreCase(password))
			{
				check = 3;
			}
			else
			{
				pstmt=con.prepareStatement("update usertable set isfirstlogin='NO' where userid="+userId);
				check = pstmt.executeUpdate();
				pstmt=con.prepareStatement("update usertable set password='"+password+"' where userid="+userId);
				check = pstmt.executeUpdate();
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
		return check;
	}
	
}
