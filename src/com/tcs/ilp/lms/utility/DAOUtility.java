package com.tcs.ilp.lms.utility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 739366
 *
 */
public class DAOUtility {

	int result=0;
	Connection con=null;
	PreparedStatement pstmt=null;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public PreparedStatement getPstmt() {
		return pstmt;
	}

	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}
	/**
	 * creating a Database connection using oracle jdbc 
	 */
	public void createCon(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		}


	}
}

