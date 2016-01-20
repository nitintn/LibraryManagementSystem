/**
 * 
 */
package com.tcs.ilp.lms.utility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Statement;


/**
 * @author 739366
 *
 */
public class UtilityDB {
	/**
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection createConnection() throws ClassNotFoundException, SQLException
	{
			Connection conn=null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP","a63d","a63d");
			return conn;
		
	}

	
}
