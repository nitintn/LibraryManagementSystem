package com.tcs.ilp.lms.dao;
import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.LSTORE;
import com.tcs.ilp.lms.utility.DAOUtility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.tcs.ilp.lms.bean.RackBean;
/**
 * 
 * @author 739366
 *
 */
public class RackDao  {
	//logger for log4j
	private static Logger staffLogger=Logger.getLogger("RackDao.class");
	private String rackShelf;


	/**
	 * adding book to rack using update of dao class
	 * @param obj
	 * @param response
	 * @throws IOException 
	 */
	public void update(RackBean obj, HttpServletResponse response) throws IOException{
		staffLogger.info("RACK DAO update method");
		//sending response to client as JSON
		response.setContentType("text/json");
		PrintWriter pr=response.getWriter();
		//database initialization
		Connection con=null;
		Statement stmt=null;
		//creating connection
		DAOUtility ob=new DAOUtility();
		ob.createCon();
		//selecting the rsid,itemid to check whether the item id filled or not
		try{
			staffLogger.info("RACK DAO update method: in try block");
			con=ob.getCon();
			//Retrieve by column name
			//if update take place then no need to check
			staffLogger.info("RACK DAO update method: in try block in TorF block");
			con=ob.getCon();
			String rack="R"+obj.getRackNo();
			String shelf="S"+obj.getShelfNo();
			String item="I"+obj.getItemNo();
			String rsiID=rack+shelf+item;
			String itemID=obj.getItemID();
			String sql1 = "UPDATE RACK_SHELF_ITEMTABLE SET ITEM_ID= ? WHERE RACK_SHELF_ITEM_ID = ?";
			PreparedStatement selectStmt1=con.prepareStatement(sql1);
			selectStmt1.setString(1,itemID);
			selectStmt1.setString(2, rsiID);
			int resultInsert = selectStmt1.executeUpdate();
			JSONObject result = new JSONObject();
			if(resultInsert>0)
				result.put("message","Item Added Successfully");
			else
				result.put("message","There is a something goes wrong.\n Please contact ur administrator");	
			String jsonResult = JSONObject.toJSONString(result);
			pr.println(jsonResult);
		}
		catch(Exception e){
			System.out.println(e.toString());
			System.out.println("333");
		}
		finally{
			try {
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 

	}

	public void delete(RackBean obj,HttpServletResponse response) throws IOException{
		//db delete,truncate queries
		staffLogger.info("RACK DAO delete method");
		response.setContentType("text/json");
		PrintWriter pr=response.getWriter();
		Connection con=null;
		Statement stmt=null;
		DAOUtility ob=new DAOUtility();
		ob.createCon();
		try{
			staffLogger.info("RACK DAO delete method: try block");
			con=ob.getCon();
			String sql="select RACK_SHELF_ITEM_ID from RACK_SHELF_ITEMTABLE  where ITEM_ID=?";
			PreparedStatement selectStmt = con.prepareStatement(sql);
			selectStmt.setString(1,obj.getItemID());
			ResultSet rs = selectStmt.executeQuery(); 
			//STEP 5: Extract data from result set
			String rsiID=rs.getString(1);
			con=ob.getCon();
			String sql1 = "UPDATE RACK_SHELF_ITEMTABLE SET ITEM_ID=0 WHERE RACK_SHELF_ITEM_ID = ?";
			PreparedStatement selectStmt1=con.prepareStatement(sql1);
			selectStmt1.setString(1,rsiID);
			int res= selectStmt1.executeUpdate();
			if(res>0)
				pr.println("Rack item entry for the corresponding item cleared successfully");
			else
				pr.println("There is something went wrong. Please contact your administrator");
			rs.close();
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

	public void populateListDao(String rackNo, HttpServletResponse response, int i) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json");
		PrintWriter pr=response.getWriter();
		Connection con=null;
		Statement stmt=null;
		DAOUtility ob=new DAOUtility();
		ob.createCon();
		try{
			staffLogger.info("RACK DAO populate method: try block");
			con=ob.getCon();
			String sql;
			if(i==1)
				sql="select RACK_SHELF_ITEM_ID,ITEM_ID from RACK_SHELF_ITEMTABLE where RACK_SHELF_ITEM_ID like ? and ITEM_ID='0'";
			else
				sql="select RACK_SHELF_ITEM_ID,ITEM_ID from RACK_SHELF_ITEMTABLE where RACK_SHELF_ITEM_ID like ? and ITEM_ID='0'";
			PreparedStatement selectStmt = con.prepareStatement(sql);
			selectStmt.setString(1,"%"+rackNo+"%");
			ResultSet rs = selectStmt.executeQuery(); 
			//STEP 5: Extract data from result set
			Gson gson = new Gson();
			JSONObject result = new JSONObject();
			TreeSet<Character> shelfN=new TreeSet<Character>();
			TreeSet<Character> itemN=new TreeSet<Character>();
			if (rs != null){
				while(rs.next()){
					if(i==1)
						shelfN.add(rs.getString(1).charAt(3));
					else
						itemN.add(rs.getString(1).charAt(5));
				}
				result.put("message","");
			}
			else{
				result.put("message","There is a something goes wrong.\n Please contact ur administrator");
			}
			String jsonResult;
			if(i==1)
				jsonResult = gson.toJson(shelfN);
			else
				jsonResult = gson.toJson(itemN);
			//	String jsonResult = JSONObject.toJSONString(result);
			pr.println(jsonResult);
			rs.close();
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 

	}

	public List<RackBean> viewRackContents(String param, int startPageIndex, int numRecordsPerPage) {
		// TODO Auto-generated method stub
		List<RackBean> rsiList = new ArrayList<RackBean>();
		String sqlQueryParam = null,sqlQuery=null;
		Connection con=null;
		Statement stmt=null;
		DAOUtility ob=new DAOUtility();
		ob.createCon();
		try {
			con=ob.getCon();
			if(param.contains("iID")){
				sqlQueryParam=param.substring(7,param.length());
				sqlQuery="select * from RACK_SHELF_ITEMTABLE where ITEM_ID = ? ";
				PreparedStatement selectStmt = con.prepareStatement(sqlQuery);
				selectStmt.setString(1,sqlQueryParam);
				ResultSet rs = selectStmt.executeQuery();
				while(rs.next()){
					RackBean obj=new RackBean();
					obj.setRsiID(rs.getString(1));
					obj.setItemID(rs.getString(2));
					rsiList.add(obj);
				}
			}
			else if(param.contains("iTitle")){
				sqlQueryParam=param.substring(10,param.length()-8);
				sqlQuery="SELECT * FROM RACK_SHELF_ITEMTABLE WHERE item_id IN(SELECT itemid FROM ITEM WHERE lower(TITLE)=lower(?))";
				PreparedStatement selectStmt = con.prepareStatement(sqlQuery);
				selectStmt.setString(1,sqlQueryParam);
				ResultSet rs = selectStmt.executeQuery();
				while(rs.next()){
					RackBean obj=new RackBean();
					obj.setRsiID(rs.getString(1));
					obj.setItemID(rs.getString(2));
					rsiList.add(obj);
				}
			}
			else if(param.contains("iRack")){
				sqlQueryParam=param.substring(9);
				rsiList.clear();
				sqlQuery="SELECT * FROM (SELECT a.*,rownum rnum FROM (select * from RACK_SHELF_ITEMTABLE WHERE RACK_SHELF_ITEM_ID LIKE ? ORDER BY lpad(RACK_SHELF_ITEM_id,10)) a WHERE rownum<="+(startPageIndex+numRecordsPerPage)+" ) where rnum>"+startPageIndex+"";
				PreparedStatement selectStmt = con.prepareStatement(sqlQuery);
				selectStmt.setString(1,"%"+sqlQueryParam+"%");
				ResultSet rs = selectStmt.executeQuery();
				while(rs.next()){
					RackBean obj=new RackBean();
					obj.setRsiID(rs.getString(1));
					obj.setItemID(rs.getString(2));
					rsiList.add(obj);
				}
			}
			else
				staffLogger.info("Something went wrong in rack dao. Please contact adminsitrator");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rsiList;
	}

	public int getRackCountDB(String param) {
		// TODO Auto-generated method stub
		String sqlQueryParam = null,sqlQuery=null;
		int rowCount = 0; 
		Connection con=null;
		DAOUtility ob=new DAOUtility();
		ob.createCon();
		try {
			con=ob.getCon();
			if(param.contains("iID")){
				sqlQueryParam=param.substring(7,param.length());
				sqlQuery="select count(*) from RACK_SHELF_ITEMTABLE where ITEM_ID = ? ";
				PreparedStatement selectStmt = con.prepareStatement(sqlQuery);
				selectStmt.setString(1,sqlQueryParam);
				ResultSet rs = selectStmt.executeQuery();
				while(rs.next())
					rowCount=rs.getInt(1);
			}
			else if(param.contains("iTitle")){
				sqlQueryParam=param.substring(10,param.length());
				sqlQuery="select RACK_SHELF_ITEM_ID,ITEM_ID from RACK_SHELF_ITEMTABLE where RACK_SHELF_ITEM_ID like ? ";
				PreparedStatement selectStmt = con.prepareStatement(sqlQuery);
				selectStmt.setString(1,"%"+sqlQueryParam+"%");
				ResultSet rs = selectStmt.executeQuery();
				while(rs.next())
					rowCount=rs.getInt(1);
			}
			else if(param.contains("iRack")){
				sqlQueryParam=param.substring(9);
				sqlQuery="SELECT count(*) FROM(SELECT * FROM RACK_SHELF_ITEMTABLE where RACK_SHELF_ITEM_ID LIKE ?)";
				PreparedStatement selectStmt = con.prepareStatement(sqlQuery);
				selectStmt.setString(1,"%"+sqlQueryParam+"%");
				ResultSet rs = selectStmt.executeQuery();
				while(rs.next())
					rowCount=rs.getInt(1);
			}
			else
				staffLogger.info("Something went wrong in rack dao. Please contact adminsitrator");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rowCount;
	}

	public void rackAjaxAutoDB(String param, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement st=null;
		DAOUtility ob=new DAOUtility();
		ob.createCon();
		con=ob.getCon();
		String buffer;
		st=con.createStatement();
		ResultSet rs=st.executeQuery("select title,itemid from item where lower(title) like lower('"+param+"%')");
		while(rs.next()){
			response.setContentType("text");
			buffer=rs.getString("title")+"("+rs.getString(2)+")";
			response.getWriter().println(buffer);
		}
	}

	public void rackDeleteItem(int param, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		staffLogger.info("RACK DAO delete method");
		response.setContentType("text/HTML");
		PrintWriter pr=response.getWriter();
		Connection con=null;
		Statement stmt=null;
		DAOUtility ob=new DAOUtility();
		ob.createCon();
		try{
			con=ob.getCon();
			String sql="select RACK_SHELF_ITEM_ID from RACK_SHELF_ITEMTABLE  where ITEM_ID=?";
			PreparedStatement selectStmt = con.prepareStatement(sql);
			selectStmt.setString(1,""+param+"");
			ResultSet rs = selectStmt.executeQuery(); 
			//STEP 5: Extract data from result set
			rs.next();
			String rsiID=rs.getString(1);
			con=ob.getCon();
			String sql1 = "UPDATE RACK_SHELF_ITEMTABLE SET ITEM_ID=0 WHERE RACK_SHELF_ITEM_ID = ?";
			PreparedStatement selectStmt1=con.prepareStatement(sql1);

			selectStmt1.setString(1,rsiID);
			int res= selectStmt1.executeUpdate();
			if(res>0)
				pr.println("Rack item entry for the corresponding item cleared successfully");
			else
				pr.println("There is something went wrong. Please contact your administrator");
			rs.close();
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean rackAjaxCategoryDB(String userId1) {
		// TODO Auto-generated method stub
		boolean userIdAvailable=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			pstmt=con.prepareStatement("SELECT COUNT(CATEGORY) FROM RACK_CATEGORY WHERE lower(CATEGORY)=lower(?)");
			System.out.println(userId1);
			pstmt.setString(1,userId1);			
			ResultSet rs=pstmt.executeQuery();
			int c;
			while (rs.next()){
				c=rs.getInt(1);
				if(c>0){
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
}