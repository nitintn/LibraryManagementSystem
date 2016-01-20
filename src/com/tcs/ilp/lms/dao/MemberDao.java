package com.tcs.ilp.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.ilp.lms.bean.MemberBean;

public class MemberDao 
{
	public int register(MemberBean memberBean)
	{
		System.out.println("Connected");
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("select count(userid) from usertable where identityId='"+memberBean.getIdentityId()+"'");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				result = rs.getInt(1);
			}
			System.out.println(result);
			if(result!=1)
			{
				result = 0;
				pstmt = con.prepareStatement("select count(identityid) from usertable where userid="+memberBean.getUserId());
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					result = rs.getInt(1);
				}
				System.out.println(result);
				if(result!=1)
				{
					System.out.println("Inserting");
					pstmt = con.prepareStatement("insert into usertable(userid,password,firstname,lastname,role,contactno,email,address,isfirstlogin,identityid) values("+memberBean.getUserId()+",'"+memberBean.getPassword()+"','"+memberBean.getFirstName()+"','"+memberBean.getLastName()+"','"+memberBean.getRole()+"',"+memberBean.getContact()+",'"+memberBean.getEmail()+"','"+memberBean.getAddress()+"','YES','"+memberBean.getIdentityId()+"')");
					result=pstmt.executeUpdate();
					pstmt = con.prepareStatement("insert into membermapping(userid,membertype) values("+memberBean.getUserId()+",'"+memberBean.getMemberType()+"')");
					result=pstmt.executeUpdate();
					pstmt=con.prepareStatement("SELECT COUNT(*) FROM ADMINCHANGELOG");
					ResultSet rs2 = pstmt.executeQuery();
					rs2.next();
					int count = rs2.getInt(1) + 1;
					System.out.println(count);
					String statement = "A Member with UserID : "+memberBean.getUserId()+" has registered in the system";
					pstmt=con.prepareStatement("INSERT INTO ADMINCHANGELOG VALUES("+count+",'"+statement+"')");
					pstmt.executeUpdate();
				}
				else
				{
					result = 3;
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
	
	public ArrayList<String> getMasterList(int memId)
	{
		ArrayList<String> masterList = new ArrayList<String>();
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("SELECT count(MEMBERTYPE) FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			System.out.println(count);
			if(count!=0)
			{
				System.out.println(count);
				pstmt = con.prepareStatement("SELECT MEMBERTYPE FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+")))");
				rs = pstmt.executeQuery();
				
				while(rs.next())
				{	
					
					System.out.println(rs.getString(1));
					masterList.add(rs.getString(1));
					
				}
			}
			else
			{
				masterList.add("Cannot");
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
		return masterList;
	}
	
	
	public long getFeesOne(String memType, int memberId)
	{
		long result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("SELECT cost FROM MASTERPROFILE WHERE MEMBERTYPE='"+memType+"'");
			ResultSet rs = pstmt.executeQuery();			
			while(rs.next())
			{
				result = rs.getLong(1);				
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
	
	
	
	public long getFeesTwo(String memType,int memberId)
	{
		long result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memberId+")");
			ResultSet rs = pstmt.executeQuery();
			pstmt = con.prepareStatement("Update membermapping set membertype='"+memType+"' where userid="+memberId);
			int rsOne=pstmt.executeUpdate();
			while(rs.next())
			{
				
				 result = rs.getLong(1);
				System.out.println(result);
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
	
	public boolean checkAvailabilityOfUserId(int userId) 
	{
		// TODO Auto-generated method stub
		boolean userIdAvailable=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT COUNT(USERID) FROM USERTABLE WHERE USERID=?");
			pstmt.setInt(1,userId);			
			ResultSet rs=pstmt.executeQuery();
			int c;
			while (rs.next())
			{
				c=rs.getInt(1);
				if(c>0)
				{
						//System.out.println("member type is present");
						userIdAvailable=false;
				}
				else{
					userIdAvailable=true;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();			
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
		return userIdAvailable;
	}

	public boolean checkAvailabilityOfIdentityId(String identityId) 
	{
		// TODO Auto-generated method stub
		boolean identityIdAvailable=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT COUNT(USERID) FROM USERTABLE WHERE IDENTITYID=?");
			pstmt.setString(1,identityId);			
			ResultSet rs=pstmt.executeQuery();
			int c;
			while (rs.next())
			{
				c=rs.getInt(1);
				if(c>0)
				{
						//System.out.println("member type is present");
						identityIdAvailable=false;
				}
				else{
					identityIdAvailable=true;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();			
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
		return identityIdAvailable;
	}
	
}
