package com.tcs.ilp.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.ilp.lms.bean.MasterProfileBean;

public class MasterProfileDao 
{
	public int addMasterProfile(MasterProfileBean masterProfileBean)
	{
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT COUNT(MEMBERTYPE) FROM MASTERPROFILE WHERE MEMBERTYPE='"+masterProfileBean.getMemberType().toUpperCase()+"'");
			ResultSet rs=pstmt.executeQuery();
			int c;
			while (rs.next())
			{
				c=rs.getInt(1);
				if(c>0)
				{
						System.out.println("member type is present");
				}
				else{
	
					pstmt=con.prepareStatement("insert into masterprofile(membertype,booknumber,cdnumber,magazinenumber,journalnumber,cost) values('"+masterProfileBean.getMemberType().toUpperCase()+"',"+masterProfileBean.getNumberOfBook()+","+masterProfileBean.getNumberOfCd()+","+masterProfileBean.getNumberOfMagazine()+","+masterProfileBean.getNumberOfJournal()+","+masterProfileBean.getCost()+")");			
					result = pstmt.executeUpdate();
					System.out.println("new masterprofile added successfully");
					pstmt=con.prepareStatement("SELECT COUNT(*) FROM ADMINCHANGELOG");
					ResultSet rs2 = pstmt.executeQuery();
					rs2.next();
					int count = rs2.getInt(1) + 1;
					System.out.println(count);
					String statement = "A new MasterProfile with Name : "+masterProfileBean.getMemberType()+" has been added into the system";
					pstmt=con.prepareStatement("INSERT INTO ADMINCHANGELOG VALUES("+count+",'"+statement+"')");
					pstmt.executeUpdate();	
					pstmt=con.prepareStatement("INSERT INTO LIBRARIANCHANGELOG VALUES("+count+",'"+statement+"')");
					pstmt.executeUpdate();
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
		return result;
	}

	public ArrayList<String> getList()
	{
		ArrayList<String> masterList = new ArrayList();
		System.out.println("Connected");
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement("select membertype from masterprofile");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				masterList.add(rs.getString(1));
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


	public ArrayList<MasterProfileBean> getDetails(String memType)
	{
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ArrayList<MasterProfileBean> list=new ArrayList<MasterProfileBean>();
		System.out.println(memType);
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT * FROM MASTERPROFILE where MEMBERTYPE=?");
			pstmt.setString(1, memType);
			ResultSet te=pstmt.executeQuery();
			while(te.next()){

				MasterProfileBean dao=new MasterProfileBean();
				dao.setNumberOfBook(te.getInt("BOOKNUMBER"));
				dao.setNumberOfCd(te.getInt("CDNUMBER"));
				dao.setNumberOfMagazine(te.getInt("MAGAZINENUMBER"));
				dao.setNumberOfJournal(te.getInt("JOURNALNUMBER"));
				dao.setCost(te.getFloat("COST"));
				System.out.println(te.getString("MEMBERTYPE"));
				System.out.println(te.getString("BOOKNUMBER"));
				System.out.println(te.getString("CDNUMBER"));
				System.out.println(te.getString("MAGAZINENUMBER"));
				System.out.println(te.getString("JOURNALNUMBER"));
				System.out.println(te.getString("COST"));
				list.add(dao);

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
			} catch (SQLException e){
				e.printStackTrace();
			}

		}
		return list; 
	}

	public int updatedMasterProfile(MasterProfileBean masterProfileBean) {

		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;

		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			System.out.println(masterProfileBean.getNumberOfBook());
			System.out.println(masterProfileBean.getNumberOfCd());
			System.out.println(masterProfileBean.getNumberOfMagazine());
			System.out.println(masterProfileBean.getNumberOfJournal());
			System.out.println(masterProfileBean.getCost());

			pstmt=con.prepareStatement("UPDATE MASTERPROFILE SET BOOKNUMBER=?,CDNUMBER=?,MAGAZINENUMBER=?,JOURNALNUMBER=?,COST=? WHERE MEMBERTYPE=?"); 
			System.out.println(masterProfileBean.getMemberType());
			pstmt.setInt(1,masterProfileBean.getNumberOfBook());
			pstmt.setInt(2,masterProfileBean.getNumberOfCd());
			pstmt.setInt(3,masterProfileBean.getNumberOfMagazine());
			pstmt.setInt(4,masterProfileBean.getNumberOfJournal());
			pstmt.setFloat(5,masterProfileBean.getCost());
			pstmt.setString(6,masterProfileBean.getMemberType());
			result = pstmt.executeUpdate();
			System.out.println(result);

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
	public ArrayList<MasterProfileBean> retrieveMasterProfile(MasterProfileBean mp) {
		// TODO Auto-generated method stub
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ArrayList<MasterProfileBean> list=new ArrayList<MasterProfileBean>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			//pstmt=con.prepareStatement("insert into MASTERPROFILE values(?,?)");
			//pstmt.setString(1,mp.getUserId());
			//pstmt.setString(2,mp.getUsername());
			//result =pstmt.executeUpdate();
			//System.out.println(result);
			pstmt=con.prepareStatement("SELECT * FROM MASTERPROFILE");
			ResultSet te=pstmt.executeQuery();
			while(te.next())
			{
				MasterProfileBean dao=new MasterProfileBean();
				dao.setMemberType(te.getString("MEMBERTYPE"));
				dao.setNumberOfBook(te.getInt("BOOKNUMBER"));
				dao.setNumberOfCd(te.getInt("CDNUMBER"));
				dao.setNumberOfMagazine(te.getInt("MAGAZINENUMBER"));
				dao.setNumberOfJournal(te.getInt("JOURNALNUMBER"));
				dao.setCost(te.getFloat("COST"));
				System.out.println(te.getString("MEMBERTYPE"));
				System.out.println(te.getString("BOOKNUMBER"));
				System.out.println(te.getString("CDNUMBER"));
				System.out.println(te.getString("MAGAZINENUMBER"));
				System.out.println(te.getString("JOURNALNUMBER"));
				System.out.println(te.getString("COST"));
				list.add(dao);				
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
		return list; 
	}
		
	///to check availability of category
	public boolean checkAvailabilityOfCategory(MasterProfileBean masterProfileBean)
	{
		boolean categoryAvailable=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT COUNT(MEMBERTYPE) FROM MASTERPROFILE WHERE MEMBERTYPE=?");
			pstmt.setString(1,masterProfileBean.getMemberType().toUpperCase());
			
			ResultSet rs=pstmt.executeQuery();
			int c;
			while (rs.next())
			{
				c=rs.getInt(1);
				if(c>0)
				{
						//System.out.println("member type is present");
						categoryAvailable=false;
				}
				else{
					categoryAvailable=true;
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
		return categoryAvailable;
	}
	
	public int getMasterCount() 
	{
		// TODO Auto-generated method stub
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT count(membertype) FROM MASTERPROFILE");
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

	public ArrayList<MasterProfileBean> getMasterList(int displayStart,int displayEnd,String sortDir,int sortCol,int displayLength,String query) 
	{
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		ArrayList<MasterProfileBean> list=new ArrayList<MasterProfileBean>();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement(query);
			ResultSet result= pstmt.executeQuery();
			while(result.next())
			{
				MasterProfileBean dao=new MasterProfileBean();
				dao.setMemberType(result.getString("MEMBERTYPE"));
				dao.setNumberOfBook(result.getInt("BOOKNUMBER"));
				dao.setNumberOfCd(result.getInt("CDNUMBER"));
				dao.setNumberOfMagazine(result.getInt("MAGAZINENUMBER"));
				dao.setNumberOfJournal(result.getInt("JOURNALNUMBER"));
				dao.setCost(result.getFloat("COST"));
				list.add(dao);				
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
		return list; 
	}
	public ArrayList<MasterProfileBean> getMemberTypeList(int displayStart,int displayEnd,String sortDir,int sortCol,int displayLength,String query) 
	{
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		ArrayList<MasterProfileBean> list=new ArrayList<MasterProfileBean>();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt = con.prepareStatement(query);
			ResultSet result= pstmt.executeQuery();
			while(result.next())
			{
				MasterProfileBean dao=new MasterProfileBean();
				dao.setMemberType(result.getString("MEMBERTYPE"));
				dao.setNumberOfBook(result.getInt("BOOKNUMBER"));
				dao.setNumberOfCd(result.getInt("CDNUMBER"));
				dao.setNumberOfMagazine(result.getInt("MAGAZINENUMBER"));
				dao.setNumberOfJournal(result.getInt("JOURNALNUMBER"));
				dao.setCost(result.getFloat("COST"));
				list.add(dao);				
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
		return list; 
	}
	public int getMemberTypeCount(int memId) 
	{
		// TODO Auto-generated method stub
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT count (membertype) from(SELECT * FROM MASTERPROFILE WHERE (COST>(SELECT COST FROM MASTERPROFILE WHERE MEMBERTYPE=(SELECT MEMBERTYPE FROM MEMBERMAPPING WHERE USERID="+memId+"))))");
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
