package com.tcs.ilp.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.ilp.lms.bean.BookBean;
import com.tcs.ilp.lms.bean.MasterProfileBean;
import com.tcs.ilp.lms.bean.MemberBean;
import com.tcs.ilp.lms.bean.UserBean;

public class AdminDao 
{
	public int addAdmin(UserBean userBean)
	{
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("select count(userid) from usertable where identityId='"+userBean.getIdentityId()+"'");
			System.out.println(userBean.getIdentityId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				result = rs.getInt(1);
			}
			System.out.println(result);
			if(result!=1)
			{
				System.out.println(userBean.getFirstName());
				result = 0;
				pstmt = con.prepareStatement("select count(identityid) from usertable where userid="+userBean.getUserId());
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					result = rs.getInt(1);
				}
				if(result!=1)
				{
					System.out.println(userBean.getFirstName());
					System.out.println(userBean.getFirstName());
					pstmt = con.prepareStatement("insert into usertable(userid,password,firstname,lastname,role,contactno,email,address,isfirstlogin,identityid) values("+userBean.getUserId()+",'"+userBean.getPassword()+"','"+userBean.getFirstName()+"','"+userBean.getLastName()+"','"+userBean.getRole()+"',"+userBean.getContact()+",'"+userBean.getEmail()+"','"+userBean.getAddress()+"','YES','"+userBean.getIdentityId()+"')");
					result=pstmt.executeUpdate();
					pstmt=con.prepareStatement("SELECT COUNT(*) FROM ADMINCHANGELOG");
					ResultSet rs2 = pstmt.executeQuery();
					rs2.next();
					int count = rs2.getInt(1) + 1;
					System.out.println(count);
					String statement = "An Admin with UserID : "+userBean.getUserId()+" has been added to the system";
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
	
	public ArrayList<MasterProfileBean> getMasterList()
	{
		ArrayList<MasterProfileBean> masterList = new ArrayList<MasterProfileBean>();
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("select * from masterprofile");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				String memberType = rs.getString(1);
				int book = rs.getInt(2);
				int cd = rs.getInt(3);
				int magazine = rs.getInt(4);
				int journal = rs.getInt(5);
				int cost = rs.getInt(6);
				MasterProfileBean master = new MasterProfileBean(memberType,book,cd,magazine,journal,cost);
				masterList.add(master);
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
	
	public UserBean getUser(int userId, String query)
	{
		UserBean userBean = new UserBean();
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");			
			pstmt = con.prepareStatement("Select count(userid) from usertable where userid="+userId);
			ResultSet result = pstmt.executeQuery();
			result.next();
			int count = result.getInt(1);
			if(count==1)
			{
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, userId);
				result=pstmt.executeQuery();			
				while(result.next())
				{
					userBean.setUserId(userId);
					System.out.println(userId);
					userBean.setFirstName(result.getString(4));
					System.out.println(result.getString(4));
					userBean.setLastName(result.getString(5));
					System.out.println(result.getString(5));
					userBean.setContact(result.getLong(7));
					System.out.println(result.getLong(7));
					userBean.setIdentityId(result.getString(10));
					System.out.println(result.getString(10));
					userBean.setEmail(result.getString(8));
					System.out.println(result.getString(8));
					userBean.setAddress(result.getString(9));
					System.out.println(result.getString(9));
					userBean.setRole(result.getString(6));
					System.out.println(result.getString(6));
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
		return userBean;
	}
	
	public int deleteLibrarian(int userId)
	{
		int check = 0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");			
			pstmt = con.prepareStatement("delete from usertable where userid="+userId);
			check = pstmt.executeUpdate();
			pstmt=con.prepareStatement("SELECT COUNT(*) FROM ADMINCHANGELOG");
			ResultSet rs2 = pstmt.executeQuery();
			rs2.next();
			int count = rs2.getInt(1) + 1;
			System.out.println(count);
			String statement = "The Librarian with UserID : "+userId+" has been deleted from the system";
			pstmt=con.prepareStatement("INSERT INTO ADMINCHANGELOG VALUES("+count+",'"+statement+"')");
			pstmt.executeUpdate();
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
		return check;
	}
	
	public int deleteMember(int userId)
	{
		int check = 0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");			
			pstmt = con.prepareStatement("delete from usertable where userid="+userId);
			check = pstmt.executeUpdate();
			pstmt=con.prepareStatement("SELECT COUNT(*) FROM ADMINCHANGELOG");
			ResultSet rs2 = pstmt.executeQuery();
			rs2.next();
			int count = rs2.getInt(1) + 1;
			System.out.println(count);
			String statement = "The Member with UserID : "+userId+" has been deleted from the system";
			pstmt=con.prepareStatement("INSERT INTO ADMINCHANGELOG VALUES("+count+",'"+statement+"')");
			pstmt.executeUpdate();
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
		return check;
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

	public int getItemCount(int cost) 
	{
		// TODO Auto-generated method stub
		int count = 0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT COUNT(MEMBERTYPE) FROM MASTERPROFILE WHERE COST=?");
			pstmt.setInt(1,cost);			
			ResultSet rs=pstmt.executeQuery();
			while (rs.next())
			{
				count = rs.getInt(1);
			}
			if(count>0)
			{
				count = 0;
				pstmt=con.prepareStatement("SELECT * FROM MASTERPROFILE WHERE COST=? AND ROWNUM=1");
				pstmt.setInt(1,cost);
				rs=pstmt.executeQuery();
				while (rs.next())
				{
					count = rs.getInt(2) + rs.getInt(3) + rs.getInt(4) + rs.getInt(5);
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
		return count;
	}
	
	public int getMinItemCount(int cost) 
	{
		// TODO Auto-generated method stub
		int count = 0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("select * from (select * from masterprofile where cost<? order by cost desc) where rownum=1");
			pstmt.setInt(1,cost);			
			ResultSet rs=pstmt.executeQuery();
			while (rs.next())
			{
				count = rs.getInt(2) + rs.getInt(3) + rs.getInt(4) + rs.getInt(5);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();			
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
		return count;
	}
	
	public int getMaxItemCount(int cost) 
	{
		// TODO Auto-generated method stub
		int count = 0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("select * from (select * from masterprofile where cost>? order by cost asc) where rownum=1");
			pstmt.setInt(1,cost);			
			ResultSet rs=pstmt.executeQuery();
			while (rs.next())
			{
				count = rs.getInt(2) + rs.getInt(3) + rs.getInt(4) + rs.getInt(5);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();			
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
		if(count == 0)
		{
			count = 36;
		}
		return count;
	}

	public ArrayList<MemberBean> getStaffList(int displayStart, int displayEnd,
			String sortDir, int sortCol, int displayLength, String query) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		ArrayList<MemberBean> list1=new ArrayList<MemberBean>();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement(query);
			ResultSet result= pstmt.executeQuery();
			while(result.next())
			{
				MemberBean dao=new MemberBean();
				dao.setUserId(result.getInt("USERID"));
				dao.setFirstName(result.getString("FIRSTNAME"));
				dao.setIdentityId(result.getString("IDENTITYID"));
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

	public int getStaffCount() {
		// TODO Auto-generated method stub
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT count(USERID) FROM USERTABLE WHERE ROLE = 'Librarian' ");
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

	public int deleteStaff(int userId) {
		// TODO Auto-generated method stub
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("UPDATE USERTABLE SET ROLE = 'DELETED' WHERE USERID =? ");
			pstmt.setInt(1,userId);
			result=pstmt.executeUpdate();
			pstmt=con.prepareStatement("SELECT COUNT(*) FROM ADMINCHANGELOG");
			ResultSet rs2 = pstmt.executeQuery();
			rs2.next();
			int count = rs2.getInt(1) + 1;
			System.out.println(count);
			String statement = "The Staff with UserID : "+userId+" has been deleted from the system";
			pstmt=con.prepareStatement("INSERT INTO ADMINCHANGELOG VALUES("+count+",'"+statement+"')");
			pstmt.executeUpdate();
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
			finally
			{
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
	
	public int getMemberCount() {
		// TODO Auto-generated method stub
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT count(USERID) FROM USERTABLE WHERE ROLE = 'Member' ");
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

	

	public ArrayList<MemberBean> getMemberList(int displayStart, int displayEnd, String sortDir, int sortCol, int displayLength,
			String query) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		ArrayList<MemberBean> list1=new ArrayList<MemberBean>();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement(query);
			ResultSet result= pstmt.executeQuery();
			while(result.next())
			{
				MemberBean dao=new MemberBean();
				dao.setUserId(result.getInt("USERID"));
				dao.setFirstName(result.getString("FIRSTNAME"));
				dao.setIdentityId(result.getString("IDENTITYID"));
				dao.setContact(result.getLong("CONTACTNO"));
				dao.setEmail(result.getString("EMAIL"));
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

	public ArrayList<String> getChangeLog() 
	{
		// TODO Auto-generated method stub
		ArrayList<String> change = new ArrayList<String>();
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("SELECT * FROM (SELECT * FROM ADMINCHANGELOG ORDER BY SLNO DESC) WHERE ROWNUM<20");
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


