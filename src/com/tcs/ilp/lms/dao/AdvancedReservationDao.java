package com.tcs.ilp.lms.dao;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.tcs.ilp.lms.bean.AdvancedReservationBean;
import com.tcs.ilp.lms.bean.TransactionBean;

public class AdvancedReservationDao {
	//AdvancedReservationBean advBean = new AdvancedReservationBean();
	public ArrayList<AdvancedReservationBean> searchItem(AdvancedReservationBean AdvancedReservationBeanObj) 
	{
		System.out.println("program starts................"+AdvancedReservationBeanObj.getCategory());
		Connection connectionObject = null;
		PreparedStatement preparedstatementObject = null;
		ResultSet result = null;
		String dataBaseUrl = "jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP";
		String dataBaseUserName = "a63d";
		String dataBasePassword = "a63d";
		ArrayList<AdvancedReservationBean> advancedReservationSearchList = new ArrayList<AdvancedReservationBean>();
		/*advBean.setUserId(AdvancedReservationBeanObj.getUserId());
		advBean.setItemName(AdvancedReservationBeanObj.getItemName());
		advBean.setAuthorName(AdvancedReservationBeanObj.getAuthorName());*/
		
		//step 1 - load driver....
		
		try{
			System.out.println("loading database driver.....");
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // reflection statement
			System.out.println("database driver loaded successfully");
		}
		catch(ClassNotFoundException exception){
			System.out.println("failed to load driver");
	}
		
	// step 2 - open database connection
		
		try{
			connectionObject = DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBasePassword);
			if (connectionObject != null){
				System.out.println("connectionObject is : "+connectionObject);
				System.out.println("database connection success");
			}
		   else{
				System.out.println("database connection failed");
			}
		
	// step 3 - create statement object and step 4 - execute query 
			
	/*preparedstatementObject = connectionObject.prepareStatement("SELECT BOOK.SUBJECT,BOOK.AUTHOR,BOOK.ITEMID,ITEM.TITLE,Item.Status FROM BOOK INNER JOIN ITEM ON ITEM.ITEMID = BOOK.ITEMID WHERE ITEM.TITLE =? or BOOK.AUTHOR =? AND ITEM.STATUS NOT IN ('L','P')" );
	preparedstatementObject.setString(1,AdvancedReservationBeanObj.getItemName());
	preparedstatementObject.setString(2,AdvancedReservationBeanObj.getAuthorName());*/
			
	if(AdvancedReservationBeanObj.getCategory().equalsIgnoreCase("BOOK"))
	{	
		System.out.println(" inside BOOK");
		
	    preparedstatementObject = connectionObject.prepareStatement("SELECT BOOK.SUBJECT,BOOK.ISBN,BOOK.AUTHOR,BOOK.ITEMID,ITEM.TITLE,ITEM.Status FROM BOOK INNER JOIN ITEM ON ITEM.ITEMID = BOOK.ITEMID WHERE ITEM.TITLE like ? or BOOK.AUTHOR like ? OR BOOK.SUBJECT like ? OR BOOK.PUBLISHER like ? AND ITEM.STATUS NOT IN ('L','P')" );
		
	    //preparedstatementObject = connectionObject.prepareStatement("SELECT BOOK.SUBJECT,BOOK.AUTHOR,BOOK.ITEMID,ITEM.TITLE,ITEM.Status FROM BOOK INNER JOIN ITEM ON ITEM.ITEMID = BOOK.ITEMID WHERE ITEM.TITLE LIKE ? or BOOK.AUTHOR LIKE ? AND ITEM.STATUS NOT IN ('L','P')");
	    //ITEM.TITLE =? or BOOK.AUTHOR =? OR BOOK.SUBJECT=? OR BOOK.PUBLICATION=?
		System.out.println(AdvancedReservationBeanObj.getItemName()+AdvancedReservationBeanObj.getAuthorName());
		
		preparedstatementObject.setString(1,AdvancedReservationBeanObj.getItemName());
		preparedstatementObject.setString(2,AdvancedReservationBeanObj.getAuthorName());
		preparedstatementObject.setString(3,AdvancedReservationBeanObj.getSubject());
		preparedstatementObject.setString(4,AdvancedReservationBeanObj.getPublication());

		//preparedstatementObject.setString(1,"%"+AdvancedReservationBeanObj.getItemName()+"%");
		//preparedstatementObject.setString(2,"%"+AdvancedReservationBeanObj.getAuthorName()+"%");
			
	    result = preparedstatementObject.executeQuery();
	   
	    while(result.next())
		{
			System.out.println("helooooooooo");
			
			AdvancedReservationBean AdvancedReservationBeanObj1 = new AdvancedReservationBean();
			////BOOK.SUBJECT,BOOK.ISBN,BOOK.AUTHOR,BOOK.ITEMID,ITEM.TITLE,ITEM.Status
			AdvancedReservationBeanObj1.setSubject(result.getString(1));
			AdvancedReservationBeanObj1.setIsbn(result.getLong(2));
			AdvancedReservationBeanObj1.setAuthorName(result.getString(3));
			AdvancedReservationBeanObj1.setItemId(result.getInt(4));
			AdvancedReservationBeanObj1.setItemName(result.getString(5));
			AdvancedReservationBeanObj1.setItemStatus(result.getString(6));
			AdvancedReservationBeanObj1.setCategory("BOOK");
			System.out.println(AdvancedReservationBeanObj1.getItemId());
			advancedReservationSearchList.add(AdvancedReservationBeanObj1);
		}
	
	}
	else if(AdvancedReservationBeanObj.getCategory().equalsIgnoreCase("CD"))
	{
		System.out.println(" inside CD");
		String item=AdvancedReservationBeanObj.getItemName();
		System.out.println("item name is:"+item);
		preparedstatementObject = connectionObject.prepareStatement("SELECT CD.ITEMID,CD.UPC,CD.SUBJECT,CD.PUBLISHER,ITEM.TITLE,ITEM.Status FROM CD INNER JOIN ITEM ON ITEM.ITEMID = CD.ITEMID WHERE ITEM.TITLE =? OR CD.SUBJECT=? OR CD.PUBLISHER=? AND ITEM.STATUS NOT IN ('L','P')" );
		preparedstatementObject.setString(1,item);
		preparedstatementObject.setString(2,AdvancedReservationBeanObj.getSubject());
		preparedstatementObject.setString(3,AdvancedReservationBeanObj.getPublication());
	    result = preparedstatementObject.executeQuery();
	    System.out.println("result is "+result);
	    
	    while(result.next())
		{
			System.out.println("helooooooooo");
			//CD.ITEMID,CD.UPC,CD.SUBJECT,CD.PUBLICATION,ITEM.TITLE,ITEM.Status
			
			AdvancedReservationBean AdvancedReservationBeanObj1 = new AdvancedReservationBean();
			AdvancedReservationBeanObj1.setItemId(result.getInt(1));
			AdvancedReservationBeanObj1.setIsbn(result.getLong(2));
			AdvancedReservationBeanObj1.setSubject(result.getString(2));
			AdvancedReservationBeanObj1.setPublication(result.getString(3));
			AdvancedReservationBeanObj1.setItemName(result.getString(4));
			AdvancedReservationBeanObj1.setItemStatus(result.getString(5));
			AdvancedReservationBeanObj1.setCategory("CD");
			System.out.println(AdvancedReservationBeanObj1.getItemId());
			advancedReservationSearchList.add(AdvancedReservationBeanObj1);
	}
	}
	
	else if(AdvancedReservationBeanObj.getCategory().equalsIgnoreCase("MAGAZINE"))
	{
		System.out.println(" inside MAGAZINE");
		preparedstatementObject = connectionObject.prepareStatement("SELECT MAGAZINE.ITEMID,MAGAZINE.ISBN,MAGAZINE.VOLUMENO,ITEM.TITLE,ITEM.STATUS FROM MAGAZINE INNER JOIN ITEM  ON ITEM.ITEMID = MAGAZINE.ITEMID WHERE ITEM.TITLE =? OR MAGAZINE.VOLUMENO=? AND ITEM.STATUS NOT IN ('L','P')" );
		preparedstatementObject.setString(1,AdvancedReservationBeanObj.getItemName());
		preparedstatementObject.setInt(2,AdvancedReservationBeanObj.getVolumeNumber());	
	    result = preparedstatementObject.executeQuery();
	    System.out.println("before MAGAZINE result.next");
	    while(result.next())
		{
			System.out.println("helooooooooo");
			 //MAGAZINE.ITEMID,MAGAZINE.ISBN,MAGAZINE.VOLUMENO,ITEM.TITLE,ITEM.STATUS
			
			AdvancedReservationBean AdvancedReservationBeanObj1 = new AdvancedReservationBean();
			AdvancedReservationBeanObj1.setItemId(result.getInt(1));
			AdvancedReservationBeanObj1.setIsbn(result.getLong(2));
			AdvancedReservationBeanObj1.setVolumeNumber(result.getInt(3));
			AdvancedReservationBeanObj1.setItemName(result.getString(4));
			AdvancedReservationBeanObj1.setItemStatus(result.getString(5));
			AdvancedReservationBeanObj1.setCategory("MAGAZINE");
			System.out.println(AdvancedReservationBeanObj1.getItemId());
			advancedReservationSearchList.add(AdvancedReservationBeanObj1);
	}
	}
	
	else if (AdvancedReservationBeanObj.getCategory().equalsIgnoreCase("JOURNAL"))
	{
		System.out.println("inside journal");
     	preparedstatementObject = connectionObject.prepareStatement("SELECT JOURNAL.ITEMID,JOURNAL.ISBN,JOURNAL.VOLUMENO,ITEM.TITLE,ITEM.STATUS FROM JOURNAL INNER JOIN ITEM  ON ITEM.ITEMID = JOURNAL.ITEMID WHERE ITEM.TITLE=? OR JOURNAL.VOLUMENO=? AND ITEM.STATUS NOT IN ('L','P')" );
		preparedstatementObject.setString(1,AdvancedReservationBeanObj.getItemName());
		preparedstatementObject.setInt(2,AdvancedReservationBeanObj.getVolumeNumber());
			
	    result = preparedstatementObject.executeQuery();
	    
	    System.out.println("neel kamal"+result);
	    
	    while(result.next())
		{
			System.out.println("helooooooooo");
			//JOURNAL.ITEMID,JOURNAL.ISBN,JOURNAL.VOLUMENO,ITEM.TITLE,ITEM.STATUS 
			
			AdvancedReservationBean AdvancedReservationBeanObj1 = new AdvancedReservationBean();
			AdvancedReservationBeanObj1.setItemId(result.getInt(1));
			AdvancedReservationBeanObj1.setIsbn(result.getLong(2));
			AdvancedReservationBeanObj1.setVolumeNumber(result.getInt(3));
			AdvancedReservationBeanObj1.setItemName(result.getString(4));
			AdvancedReservationBeanObj1.setItemStatus(result.getString(5));
			AdvancedReservationBeanObj1.setCategory("JOURNAL");
			System.out.println(AdvancedReservationBeanObj1.getItemId());
			advancedReservationSearchList.add(AdvancedReservationBeanObj1);
	}
		
	}
	
	
	System.out.println("helo after result"+result);
	
	
		
		/*Iterator<AdvancedReservationBean> itr= advancedReservationSearchList.iterator();
		while(itr.hasNext())
		{
			System.out.println("helooooooooo");
			System.out.println(itr.next().getAuthorName());
		}
		System.out.println(result);
		*/
		}
		    catch (SQLException exception){
			  exception.printStackTrace(); 
			}	
		// close database connection
	finally{
	try {
		connectionObject.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}	
		System.out.println("program ends................");

		// TODO Auto-generated method stub
		return advancedReservationSearchList;	
	}
	
	
	//make the advance reservation
public int advancedReservation(AdvancedReservationBean AdvancedReservationBeanObj)
	{
		System.out.println("advancedReservation program starts................");
		System.out.println(AdvancedReservationBeanObj.getItemName());
		Connection connectionObject = null;
		PreparedStatement preparedstatementObject = null;
		String dataBaseUrl = "jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP";
		String dataBaseUserName = "a63d";
		String dataBasePassword = "a63d";
		
		int rNumber =0; //can use "=(int)(Math.random()*50000);" also but duplicates might be possible
		
		//step 1 - load driver....
		
		try
		{
			System.out.println("loading database driver.....");
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // reflection statement
			System.out.println("database driver loaded successfully");
		}
		catch(ClassNotFoundException exception){
			System.out.println("failed to load driver");
	}
		
	// step 2 - open database connection
		
		try{
			connectionObject = DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBasePassword);
			if (connectionObject != null){
				System.out.println("connectionObject is : "+connectionObject);
				System.out.println("database connection success");
			}
		   else{
				System.out.println("database connection failed");
			}
		
	// step 3 - create statement object and step 4 - execute query 
			preparedstatementObject = connectionObject.prepareStatement("SELECT PASSWORD FROM USERTABLE WHERE USERID = ?" );
			preparedstatementObject.setInt(1,AdvancedReservationBeanObj.getUserId());
			ResultSet result = preparedstatementObject.executeQuery();
			
			//for restricting the user to reserve for maximum 3 items.
/*			preparedstatementObject = connectionObject.prepareStatement("SELECT PASSWORD FROM USERTABLE WHERE USERID = ?" );
			preparedstatementObject.setInt(1,AdvancedReservationBeanObj.getUserId());
			ResultSet result = preparedstatementObject.executeQuery();*/
			
			
			//for restricting the maximum waiting list number on an item to be 10.
			/*			preparedstatementObject = connectionObject.prepareStatement("SELECT PASSWORD FROM USERTABLE WHERE USERID = ?" );
						preparedstatementObject.setInt(1,AdvancedReservationBeanObj.getUserId());
						ResultSet result = preparedstatementObject.executeQuery();*/
			
			//for restricting the reservation of already reserved item.
			/*			preparedstatementObject = connectionObject.prepareStatement("SELECT PASSWORD FROM USERTABLE WHERE USERID = ?" );
						preparedstatementObject.setInt(1,AdvancedReservationBeanObj.getUserId());
						ResultSet result = preparedstatementObject.executeQuery();*/
			
			/*preparedstatementObject = connectionObject.prepareStatement("SELECT BOOKID FROM BOOK WHERE ITEMID = ?" );
			preparedstatementObject.setInt(1,AdvancedReservationBeanObj.getItemId());
			ResultSet result2 = preparedstatementObject.executeQuery();*/
			
			/*ResultSet result2=null;
			if(AdvancedReservationBeanObj.getCategory().equalsIgnoreCase("BOOK"))
			{
			PreparedStatement preparedstatementObject1 = connectionObject.prepareStatement("SELECT BOOK.ISBN FROM BOOK INNER JOIN RESERVATION_DETAILS ON BOOK.ITEMID = RESERVATION_DETAILS.ITEMID WHERE RESERVATION_DETAILS.MEMID=?" );
			preparedstatementObject1.setInt(1,AdvancedReservationBeanObj.getUserId());
			result2 = preparedstatementObject.executeQuery();
			}*/
			
			System.out.println(result);
			if(!result.isBeforeFirst())
			{
				System.out.println("invalid userId ");
			}
			/*else if(!result2.isBeforeFirst())
			{
				System.out.println("item already reserved");
			}*/
			else
			{
				if(AdvancedReservationBeanObj.getCategory().equalsIgnoreCase("BOOK"))
				{
				PreparedStatement preparedstatementObject5 = connectionObject.prepareStatement("SELECT ITEM.ITEMID  FROM BOOK INNER JOIN ITEM ON ITEM.ITEMID = BOOK.ITEMID WHERE BOOK.ISBN =? AND ROWNUM<2" );
				preparedstatementObject5.setLong(1,AdvancedReservationBeanObj.getIsbn());
				ResultSet result8 = preparedstatementObject5.executeQuery();
			if(result8.next())
					{
			preparedstatementObject = connectionObject.prepareStatement("SELECT COUNT(*) FROM RESERVATION_DETAILS WHERE ITEMID = ? AND STATUS<>0");
			preparedstatementObject.setInt(1,result8.getInt(1));
			result = preparedstatementObject.executeQuery();
			int count = 0;
			while(result.next())
			{
				count = result.getInt(1);
			}
					
			
			
	//to select max reservation number and assign further reservation numbers to avoid duplicates
			preparedstatementObject = connectionObject.prepareStatement("SELECT max(reservation_no) FROM RESERVATION_DETAILS");
			result = preparedstatementObject.executeQuery();
			
			while(result.next())
			{
				rNumber = result.getInt(1);
			
			}
			
	
			/*DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			System.out.println("current date");
			System.out.println(dateFormat.format(date));*/
			
			Calendar c = Calendar.getInstance();  
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			String res_date=sdf.format(c.getTime());
			System.out.println("reservation  date is : "+res_date);
			
			    PreparedStatement preparedstatementObject1 = connectionObject.prepareStatement("SELECT ITEM.TITLE,ITEM.ITEMID  FROM BOOK INNER JOIN ITEM ON ITEM.ITEMID = BOOK.ITEMID WHERE BOOK.ISBN =? AND STATUS Not IN ('L','P')" );
				preparedstatementObject1.setLong(1,AdvancedReservationBeanObj.getIsbn());
				ResultSet result5 = preparedstatementObject1.executeQuery();
				if(result5.next())
				{
						System.out.println("INTO if part of RESERVATION TABLE");
					
					preparedstatementObject = connectionObject.prepareStatement("INSERT INTO RESERVATION_DETAILS(Reservation_no, memid,itemid, title, status, reservationdt) VALUES (?,?,?,?,?,?)");
					//AdvancedReservationBean AdvancedReservationBeanObj = new AdvancedReservationBean();
					preparedstatementObject.setInt(1,rNumber+1);
					preparedstatementObject.setInt(2,AdvancedReservationBeanObj.getUserId());
					preparedstatementObject.setInt(3,result5.getInt(2));
					preparedstatementObject.setString(4,result5.getString(1));
					preparedstatementObject.setInt(5, count+1);
					preparedstatementObject.setString(6,res_date);
			
			
					int result1 = preparedstatementObject.executeUpdate();
					
					preparedstatementObject = connectionObject.prepareStatement("UPDATE ITEM SET Status='AR' WHERE ITEMID=? AND status NOT IN ('I','L','P','RN')");
					preparedstatementObject.setInt(1,result5.getInt(2));
					System.out.println(AdvancedReservationBeanObj.getItemName()+"after update before insert");
					preparedstatementObject.executeUpdate();
           
       
				}
				else{
					
                         System.out.println("INTO else part of RESERVATION TABLE");
                            
                    PreparedStatement preparedstatementObject2 = connectionObject.prepareStatement("SELECT ITEM.TITLE FROM BOOK INNER JOIN ITEM ON ITEM.ITEMID = BOOK.ITEMID WHERE BOOK.ISBN =? AND ROWNUM<2" );
         			preparedstatementObject2.setLong(1,AdvancedReservationBeanObj.getIsbn());
         			ResultSet result7 = preparedstatementObject2.executeQuery();
         			System.out.println("i am your hero");
         			System.out.println(result7);
         			//System.out.println(result7.getString(1));
         			if(result7.next())
         			{
      
         				System.out.println(result7.getString(1));
         				System.out.println("i am  hero");
    					preparedstatementObject = connectionObject.prepareStatement("INSERT INTO RESERVATION_DETAILS(Reservation_no, memid, title, status, reservationdt) VALUES (?,?,?,?,?)");
    					//AdvancedReservationBean AdvancedReservationBeanObj = new AdvancedReservationBean();
    					System.out.println("i am hero");
    					preparedstatementObject.setInt(1,rNumber+1);
    					preparedstatementObject.setInt(2,AdvancedReservationBeanObj.getUserId());
    					//preparedstatementObject.setInt(3,result5.getInt(2));
    					preparedstatementObject.setString(3,result7.getString(1));
    					preparedstatementObject.setInt(4, count+1);
    					preparedstatementObject.setString(5,res_date);
    					
    					int result1 = preparedstatementObject.executeUpdate();
         			}
         			
					
				}
		}
		
		}
else if(AdvancedReservationBeanObj.getCategory().equalsIgnoreCase("CD"))
				{
				PreparedStatement preparedstatementObject5 = connectionObject.prepareStatement("SELECT ITEM.ITEMID  FROM CD INNER JOIN ITEM ON ITEM.ITEMID = CD.ITEMID WHERE CD.UPC =? AND ROWNUM<2" );
				preparedstatementObject5.setLong(1,AdvancedReservationBeanObj.getIsbn());
				ResultSet result8 = preparedstatementObject5.executeQuery();
			if(result8.next())
					{
			preparedstatementObject = connectionObject.prepareStatement("SELECT COUNT(*) FROM RESERVATION_DETAILS WHERE ITEMID = ? AND STATUS<>0");
			preparedstatementObject.setInt(1,result8.getInt(1));
			result = preparedstatementObject.executeQuery();
			int count = 0;
			while(result.next())
			{
				count = result.getInt(1);
			}
					
			
			
	//to select max reservation number and assign further reservation numbers to avoid duplicates
			preparedstatementObject = connectionObject.prepareStatement("SELECT max(reservation_no) FROM RESERVATION_DETAILS");
			result = preparedstatementObject.executeQuery();
			
			while(result.next())
			{
				rNumber = result.getInt(1);
			
			}
			
	
			/*DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			System.out.println("current date");
			System.out.println(dateFormat.format(date));*/
			
			Calendar c = Calendar.getInstance();  
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			String res_date=sdf.format(c.getTime());
			System.out.println("reservation  date is : "+res_date);
			
			    PreparedStatement preparedstatementObject1 = connectionObject.prepareStatement("SELECT ITEM.TITLE,ITEM.ITEMID  FROM CD INNER JOIN ITEM ON ITEM.ITEMID = CD.ITEMID WHERE CD.UPC =? AND STATUS Not IN ('L','P')" );
				preparedstatementObject1.setLong(1,AdvancedReservationBeanObj.getIsbn());
				ResultSet result5 = preparedstatementObject1.executeQuery();
				if(result5.next())
				{
						System.out.println("INTO if part of RESERVATION TABLE");
					
					preparedstatementObject = connectionObject.prepareStatement("INSERT INTO RESERVATION_DETAILS(Reservation_no, memid,itemid, title, status, reservationdt) VALUES (?,?,?,?,?,?)");
					//AdvancedReservationBean AdvancedReservationBeanObj = new AdvancedReservationBean();
					preparedstatementObject.setInt(1,rNumber+1);
					preparedstatementObject.setInt(2,AdvancedReservationBeanObj.getUserId());
					preparedstatementObject.setInt(3,result5.getInt(2));
					preparedstatementObject.setString(4,result5.getString(1));
					preparedstatementObject.setInt(5, count+1);
					preparedstatementObject.setString(6,res_date);
			
			
					int result1 = preparedstatementObject.executeUpdate();
					
					preparedstatementObject = connectionObject.prepareStatement("UPDATE ITEM SET Status='AR' WHERE ITEMID=? AND status NOT IN ('I','L','P','RN')");
					preparedstatementObject.setInt(1,result5.getInt(2));
					System.out.println(AdvancedReservationBeanObj.getItemName()+"after update before insert");
					preparedstatementObject.executeUpdate();
           
       
				}
				else{
					
                    System.out.println("INTO else part of CD RESERVATION TABLE");
                            
                    PreparedStatement preparedstatementObject2 = connectionObject.prepareStatement("SELECT ITEM.TITLE FROM CD INNER JOIN ITEM ON ITEM.ITEMID = CD.ITEMID WHERE CD.UPC =? AND ROWNUM<2" );
         			preparedstatementObject2.setLong(1,AdvancedReservationBeanObj.getIsbn());
         			ResultSet result7 = preparedstatementObject2.executeQuery();
         			System.out.println("i am your CD ");
         			System.out.println(result7);
         			//System.out.println(result7.getString(1));
         			if(result7.next())
         			{
      
         				System.out.println(result7.getString(1));
         				System.out.println("i am CD hero");
    					preparedstatementObject = connectionObject.prepareStatement("INSERT INTO RESERVATION_DETAILS(Reservation_no, memid, title, status, reservationdt) VALUES (?,?,?,?,?)");
    					//AdvancedReservationBean AdvancedReservationBeanObj = new AdvancedReservationBean();
    					System.out.println("i am CD");
    					preparedstatementObject.setInt(1,rNumber+1);
    					preparedstatementObject.setInt(2,AdvancedReservationBeanObj.getUserId());
    					//preparedstatementObject.setInt(3,result5.getInt(2));
    					preparedstatementObject.setString(3,result7.getString(1));
    					preparedstatementObject.setInt(4, count+1);
    					preparedstatementObject.setString(5,res_date);
    					
    					int result1 = preparedstatementObject.executeUpdate();
         			}
         			
					
				}
		}
		
			
			
					
				}
else if(AdvancedReservationBeanObj.getCategory().equalsIgnoreCase("MAGAZINE"))
{
PreparedStatement preparedstatementObject5 = connectionObject.prepareStatement("SELECT ITEM.ITEMID  FROM MAGAZINE INNER JOIN ITEM ON ITEM.ITEMID = MAGAZINE.ITEMID WHERE MAGAZINE.ISBN =? AND ROWNUM<2" );
preparedstatementObject5.setLong(1,AdvancedReservationBeanObj.getIsbn());
ResultSet result8 = preparedstatementObject5.executeQuery();
if(result8.next())
	{
preparedstatementObject = connectionObject.prepareStatement("SELECT COUNT(*) FROM RESERVATION_DETAILS WHERE ITEMID = ? AND STATUS<>0");
preparedstatementObject.setInt(1,result8.getInt(1));
result = preparedstatementObject.executeQuery();
int count = 0;
while(result.next())
{
count = result.getInt(1);
}
	


//to select max reservation number and assign further reservation numbers to avoid duplicates
preparedstatementObject = connectionObject.prepareStatement("SELECT max(reservation_no) FROM RESERVATION_DETAILS");
result = preparedstatementObject.executeQuery();

while(result.next())
{
rNumber = result.getInt(1);

}


/*DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
Date date = new Date();
System.out.println("current date");
System.out.println(dateFormat.format(date));*/

Calendar c = Calendar.getInstance();  
SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
String res_date=sdf.format(c.getTime());
System.out.println("reservation  date is : "+res_date);

PreparedStatement preparedstatementObject1 = connectionObject.prepareStatement("SELECT ITEM.TITLE,ITEM.ITEMID  FROM MAGAZINE INNER JOIN ITEM ON ITEM.ITEMID = MAGAZINE.ITEMID WHERE MAGAZINE.ISBN =? AND STATUS Not IN ('L','P')" );
preparedstatementObject1.setLong(1,AdvancedReservationBeanObj.getIsbn());
ResultSet result5 = preparedstatementObject1.executeQuery();
if(result5.next())
{
		System.out.println("INTO if part of MAGAZINE RESERVATION TABLE");
	
	preparedstatementObject = connectionObject.prepareStatement("INSERT INTO RESERVATION_DETAILS(Reservation_no, memid,itemid, title, status, reservationdt) VALUES (?,?,?,?,?,?)");
	//AdvancedReservationBean AdvancedReservationBeanObj = new AdvancedReservationBean();
	preparedstatementObject.setInt(1,rNumber+1);
	preparedstatementObject.setInt(2,AdvancedReservationBeanObj.getUserId());
	preparedstatementObject.setInt(3,result5.getInt(2));
	preparedstatementObject.setString(4,result5.getString(1));
	preparedstatementObject.setInt(5, count+1);
	preparedstatementObject.setString(6,res_date);


	int result1 = preparedstatementObject.executeUpdate();
	
	preparedstatementObject = connectionObject.prepareStatement("UPDATE ITEM SET Status='AR' WHERE ITEMID=? AND status NOT IN ('I','L','P','RN')");
	preparedstatementObject.setInt(1,result5.getInt(2));
	System.out.println(AdvancedReservationBeanObj.getItemName()+"after update before insert");
	preparedstatementObject.executeUpdate();


}
else{
	
    System.out.println("INTO else part of MAGAZINE RESERVATION TABLE");
            
    PreparedStatement preparedstatementObject2 = connectionObject.prepareStatement("SELECT ITEM.TITLE FROM MAGAZINE INNER JOIN ITEM ON ITEM.ITEMID = MAGAZINE.ITEMID WHERE MAGAZINE.ISBN =? AND ROWNUM<2" );
		preparedstatementObject2.setLong(1,AdvancedReservationBeanObj.getIsbn());
		ResultSet result7 = preparedstatementObject2.executeQuery();
		System.out.println("i am your MAGAZINE");
		System.out.println(result7);
		//System.out.println(result7.getString(1));
		if(result7.next())
		{

			System.out.println(result7.getString(1));
			System.out.println("i am  MAGAZINE HERO");
		preparedstatementObject = connectionObject.prepareStatement("INSERT INTO RESERVATION_DETAILS(Reservation_no, memid, title, status, reservationdt) VALUES (?,?,?,?,?)");
		//AdvancedReservationBean AdvancedReservationBeanObj = new AdvancedReservationBean();
		System.out.println("i am MAGAZINE");
		preparedstatementObject.setInt(1,rNumber+1);
		preparedstatementObject.setInt(2,AdvancedReservationBeanObj.getUserId());
		//preparedstatementObject.setInt(3,result5.getInt(2));
		preparedstatementObject.setString(3,result7.getString(1));
		preparedstatementObject.setInt(4, count+1);
		preparedstatementObject.setString(5,res_date);
		
		int result1 = preparedstatementObject.executeUpdate();
		}
		
	
}
}



	
}
else if(AdvancedReservationBeanObj.getCategory().equalsIgnoreCase("JOURNAL"))
{
PreparedStatement preparedstatementObject5 = connectionObject.prepareStatement("SELECT ITEM.ITEMID  FROM JOURNAL INNER JOIN ITEM ON ITEM.ITEMID = JOURNAL.ITEMID WHERE JOURNAL.ISBN =? AND ROWNUM<2" );
preparedstatementObject5.setLong(1,AdvancedReservationBeanObj.getIsbn());
ResultSet result8 = preparedstatementObject5.executeQuery();
if(result8.next())
	{
preparedstatementObject = connectionObject.prepareStatement("SELECT COUNT(*) FROM RESERVATION_DETAILS WHERE ITEMID = ? AND STATUS<>0");
preparedstatementObject.setInt(1,result8.getInt(1));
result = preparedstatementObject.executeQuery();
int count = 0;
while(result.next())
{
count = result.getInt(1);
}
	


//to select max reservation number and assign further reservation numbers to avoid duplicates
preparedstatementObject = connectionObject.prepareStatement("SELECT max(reservation_no) FROM RESERVATION_DETAILS");
result = preparedstatementObject.executeQuery();

while(result.next())
{
rNumber = result.getInt(1);

}


/*DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
Date date = new Date();
System.out.println("current date");
System.out.println(dateFormat.format(date));*/

Calendar c = Calendar.getInstance();  
SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
String res_date=sdf.format(c.getTime());
System.out.println("reservation  date is : "+res_date);

PreparedStatement preparedstatementObject1 = connectionObject.prepareStatement("SELECT ITEM.TITLE,ITEM.ITEMID  FROM JOURNAL INNER JOIN ITEM ON ITEM.ITEMID = JOURNAL.ITEMID WHERE JOURNAL.ISBN =? AND STATUS Not IN ('L','P')" );
preparedstatementObject1.setLong(1,AdvancedReservationBeanObj.getIsbn());
ResultSet result5 = preparedstatementObject1.executeQuery();
if(result5.next())
{
		System.out.println("INTO if part of JOURNAL RESERVATION TABLE");
	
	preparedstatementObject = connectionObject.prepareStatement("INSERT INTO RESERVATION_DETAILS(Reservation_no, memid,itemid, title, status, reservationdt) VALUES (?,?,?,?,?,?)");
	//AdvancedReservationBean AdvancedReservationBeanObj = new AdvancedReservationBean();
	preparedstatementObject.setInt(1,rNumber+1);
	preparedstatementObject.setInt(2,AdvancedReservationBeanObj.getUserId());
	preparedstatementObject.setInt(3,result5.getInt(2));
	preparedstatementObject.setString(4,result5.getString(1));
	preparedstatementObject.setInt(5, count+1);
	preparedstatementObject.setString(6,res_date);


	int result1 = preparedstatementObject.executeUpdate();
	
	preparedstatementObject = connectionObject.prepareStatement("UPDATE ITEM SET Status='AR' WHERE ITEMID=? AND status NOT IN ('I','L','P','RN')");
	preparedstatementObject.setInt(1,result5.getInt(2));
	System.out.println(AdvancedReservationBeanObj.getItemName()+"after update before insert");
	preparedstatementObject.executeUpdate();


}
else{
	
    System.out.println("INTO else part of JOURNAL RESERVATION TABLE");
            
    PreparedStatement preparedstatementObject2 = connectionObject.prepareStatement("SELECT ITEM.TITLE FROM JOURNAL INNER JOIN ITEM ON ITEM.ITEMID = JOURNAL.ITEMID WHERE JOURNAL.ISBN =? AND ROWNUM<2" );
		preparedstatementObject2.setLong(1,AdvancedReservationBeanObj.getIsbn());
		ResultSet result7 = preparedstatementObject2.executeQuery();
		System.out.println("i am your JOURNAL");
		System.out.println(result7);
		//System.out.println(result7.getString(1));
		if(result7.next())
		{

			System.out.println(result7.getString(1));
			System.out.println("i am  JOURNAL");
		preparedstatementObject = connectionObject.prepareStatement("INSERT INTO RESERVATION_DETAILS(Reservation_no, memid, title, status, reservationdt) VALUES (?,?,?,?,?)");
		//AdvancedReservationBean AdvancedReservationBeanObj = new AdvancedReservationBean();
		System.out.println("i am JOURNAL");
		preparedstatementObject.setInt(1,rNumber+1);
		preparedstatementObject.setInt(2,AdvancedReservationBeanObj.getUserId());
		//preparedstatementObject.setInt(3,result5.getInt(2));
		preparedstatementObject.setString(3,result7.getString(1));
		preparedstatementObject.setInt(4, count+1);
		preparedstatementObject.setString(5,res_date);
		
		int result1 = preparedstatementObject.executeUpdate();
		}
}
}	
}
		}
			
		}
		
		    catch (SQLException exception){
			  exception.printStackTrace(); 
			}	
		// close database connection
	finally{
	try {
		connectionObject.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}	
		
		
		System.out.println("program ends................");

		// TODO Auto-generated method stub
		return rNumber+1;
}


//cancel Advance Reservation Updation
public String cancelReservationUpdate(int memId, int resNo)
{
	ResultSet resultS;
	ResultSet resultT;
	int resultCancelRes;
	int resultI;
	//ResultSet result;
	Connection con=null;
	PreparedStatement pstmt=null;
	int status=0;
	String title=null;
	
	//AdvancedReservationBean arBean= new AdvancedReservationBean();
	//List<BookBean> bookList=new ArrayList<BookBean>();
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
		
		//to select the details of said reservation and store status & title for comparison
		String querySelect="SELECT * FROM RESERVATION_DETAILS WHERE MEMID=? AND RESERVATION_NO=?";			
		pstmt=con.prepareStatement(querySelect);
		pstmt.setInt(1, memId);
		pstmt.setInt(2, resNo);
		resultS=pstmt.executeQuery();
		while(resultS.next())
		{
			title=resultS.getString(4); //store the status of deleted reservation
			status=resultS.getInt(5);	//store the title of deleted reservation
			break;
		}
				
		//to delete a particular reservation entry from the database
		String queryDelReservation="DELETE FROM RESERVATION_DETAILS WHERE MEMID=? AND RESERVATION_NO=?";
		pstmt=con.prepareStatement(queryDelReservation);
		pstmt.setInt(1, memId);
		pstmt.setInt(2, resNo);
		resultCancelRes=pstmt.executeUpdate();
		
		System.out.println(status+" status");
		if(status==0)
		{
			//to update the WList for remaining reservations on same title
			String queryUpdateWL="UPDATE RESERVATION_DETAILS SET STATUS=status-1 WHERE TITLE=?";		//AND STATUS<>0 
			pstmt=con.prepareStatement(queryUpdateWL);
			pstmt.setString(1, title);
			resultT=pstmt.executeQuery();
		}
		else
		{
			//to update the WList for remaining reservations on same title with status>deleted status
			String queryUpdateWL="UPDATE RESERVATION_DETAILS SET STATUS=status-1 WHERE TITLE=? AND STATUS>?";		//AND STATUS<>0 
			pstmt=con.prepareStatement(queryUpdateWL);
			pstmt.setString(1, title);
			pstmt.setInt(2, status);
			resultT=pstmt.executeQuery();
		}
		
		System.out.println(title+" title" );
		//to select the details of remaining reservation on that title if present
		String querySelectRes="SELECT * FROM RESERVATION_DETAILS WHERE TITLE=?";			
		pstmt=con.prepareStatement(querySelectRes);
		pstmt.setString(1, title);
		resultS=pstmt.executeQuery();
		
		ArrayList<AdvancedReservationBean> reservationList= new ArrayList<AdvancedReservationBean>();
		
		while(resultS.next())
		{
			AdvancedReservationBean advResBn= new AdvancedReservationBean();
			advResBn.setReservationNumber(resultS.getInt(1));
			advResBn.setUserId(resultS.getInt(2));
			advResBn.setItemName(resultS.getString(3));
			reservationList.add(advResBn);
		}
		
		if (reservationList.isEmpty())
		{
			String queryItem="UPDATE  ITEM SET STATUS='R' WHERE title=? AND status='AR'";
			pstmt=con.prepareStatement(queryItem);
			pstmt.setString(1, title);
			resultI=pstmt.executeUpdate();
			System.out.println("No more reservations for this title");
			return "No more Reservations";
		}
		else
		{
			return "Reservation cancelled & WL updated succesfully";
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
		return "Proceed";
	}

	//view advance reservation list for a member
	public ArrayList<AdvancedReservationBean> viewReservationList(int memId)
	{
		ResultSet resultS;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		ArrayList<AdvancedReservationBean> reservationList=new ArrayList<AdvancedReservationBean>();
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
			
			pstmt=con.prepareStatement("SELECT * FROM RESERVATION_DETAILS WHERE MEMID=?");
			pstmt.setInt(1, memId);
			resultS=pstmt.executeQuery();
			System.out.println("bye bye");
			System.out.println(memId);
			
			while(resultS.next())
			{
				AdvancedReservationBean advResBean= new AdvancedReservationBean();
				advResBean.setReservationNumber(resultS.getInt(1));
				advResBean.setUserId(resultS.getInt(2));
				advResBean.setItemId(resultS.getInt(3));
				advResBean.setItemName(resultS.getString(4));
				advResBean.setReservationStatus(resultS.getInt(5));
				advResBean.setReservationDate(resultS.getString(6));
				System.out.println(resultS.getInt(2));
				//System.out.println(result.getInt(1)+"\t\t"+result.getInt(2)+"\t"+result.getInt(3)+"\t"+result.getString(4)+"\t"+result.getString(5)+"\t"+result.getString(6)+"\t\t"+result.getString(7)+"\t\t"+result.getString(8)+"\t"+result.getInt(9)+"\t\t"+result.getInt(10)+"\t"+result.getInt(11));	
				reservationList.add(advResBean);
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
		return reservationList;
	}

   
    public    ArrayList<AdvancedReservationBean> itemResInAdv(int memberID )
    {
    	   ArrayList<AdvancedReservationBean> ar = new ArrayList<AdvancedReservationBean>();

		// TODO Auto-generated method stub
Connection con=null;
PreparedStatement pstmt=null;
try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
		pstmt=con.prepareStatement("SELECT  * FROM (SELECT USERTABLE.firstname, RESERVATION_DETAILS.memid, RESERVATION_DETAILS.itemid, RESERVATION_DETAILS.title, RESERVATION_DETAILS.status, RESERVATION_DETAILS.reservationdt FROM RESERVATION_DETAILS  INNER JOIN USERtable  ON RESERVATION_DETAILS.MEMID = USERTABLE.USERID) WHERE memid="+memberID);	
ResultSet rs=pstmt.executeQuery();
	
System.out.println("name   id  item   itname   status     date");

  


    while(rs.next())
    {
    	
    	AdvancedReservationBean been =	new AdvancedReservationBean();
    	been.setUserName(rs.getString(1));
    	been.setUserId(rs.getInt(2));
    	been.setItemId(rs.getInt(3));
    	been.setItemName(rs.getString(4));
    	been.setReservationStatus(rs.getInt(5));
    	been.setReservationDate(rs.getString(6));
    	
    	ar.add(been);
    }


    
    for(int i=0;i<ar.size();i++)
    {
    	
    	System.out.println(ar.get(i).getUserName()+"  "+ar.get(i).getUserId()+"  "+ar.get(i).getItemId()+"  "+ar.get(i).getItemName()+"  "+ar.get(i).getReservationStatus()+"  "+ar.get(i).getReservationDate());
    	
    }

   
    
   // return ar;
    
    
    
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
return ar;
		
    	
    }

    
    public int checkStatus(int itemID,int memberID)
    {
    	int status=0;
    	
		// TODO Auto-generated method stub
    	Connection con=null;
    	PreparedStatement pstmt=null;
    	try{
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
    			pstmt=con.prepareStatement("SELECT status FROM RESERVATION_DETAILS WHERE MEMID="+memberID+" AND ITEMID="+itemID);	
    	ResultSet rs=pstmt.executeQuery();
    		
    	
    	  


    	    while(rs.next())
    	    {
    	    	
    	status=rs.getInt(1);    	
    	    	
    	    }


    	   
    	   
    	    
    	   // return ar;
    	    
    	    
    	    
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
    	
    	
System.out.println("bbbbb : "+status);    	
    	return status;
    }
    

    
    
    
    
   public String deleteRowFromResTable(int itemID,int memberID)
   {
	   
    	
		// TODO Auto-generated method stub
    	Connection con=null;
    	PreparedStatement pstmt=null;
    	try{
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		con=DriverManager.getConnection("jdbc:oracle:thin:@172.26.132.40:1521:ORCLILP", "a63d", "a63d");
    			pstmt=con.prepareStatement("delete from reservation_details where itemID="+itemID+" and memid="+memberID);	
  
    			System.out.println("AdvResDAO :line1");
    			pstmt.executeUpdate();
    			   
    			System.out.println("Adv REs DAo: line2");
    	    
    	   // return ar;
    	    
    	    
    	    
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
System.out.println("deleted");
    					return "deleted";
   }
    
    
    
//public static void main(String s[])
//{
	
//new AdvancedReservationDao().deleteRowFromResTable(5009, 9);

//}
    
    
   
    
    
    
}
