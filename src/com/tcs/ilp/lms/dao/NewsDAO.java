package com.tcs.ilp.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Logger;

import com.tcs.ilp.lms.bean.NewsAndAnnouncements;
import com.tcs.ilp.lms.bean.Review;
import com.tcs.ilp.lms.utility.UtilityDB;

public class NewsDAO {
	
	public int insertNews(NewsAndAnnouncements news) throws SQLException, ClassNotFoundException{
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		PreparedStatement ptmt=null;
		String insertQuery="insert into NEWS_ANNOUNCEMENTSTABLE values(?,?,?,?,?)";
		ptmt = con.prepareStatement(insertQuery); 
		ptmt.setInt(1,news.getNewsId());
		ptmt.setInt(2,news.getUserId());
		ptmt.setString(3,news.getNews()); 
		ptmt.setString(4,  news.getDate());  
		ptmt.setString(5,news.getTitle()); 
		int i=0;
		try {
			i =1; 
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally{
			con.close();
			ptmt.close();
		}
		return i;
	}
	
	public int insertNews1(NewsAndAnnouncements news) throws SQLException, ClassNotFoundException{
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		PreparedStatement ptmt=null;
		String insertQuery="insert into NEWS_ANNOUNCEMENTSTABLE(NEWSID,NEWS,NEWSDATE,NEWSTITLE) values(?,?,?,?)";
		ptmt = con.prepareStatement(insertQuery); 
		ptmt.setInt(1,news.getNewsId());
		ptmt.setString(2,news.getNews()); 
		ptmt.setString(3,  news.getDate());  
		ptmt.setString(4,news.getTitle()); 
		int i=ptmt.executeUpdate(); 
		con.close();
		ptmt.close();
		return i;
	}
	
	public void deleteNews(int newsid) throws ClassNotFoundException, SQLException{
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		PreparedStatement ptmt=null;
		String deleteQuery="delete from NEWS_ANNOUNCEMENTSTABLE where NEWSID=?";
		ptmt = con.prepareStatement(deleteQuery); 
		ptmt.setInt(1,newsid);
		ptmt.executeUpdate();
		con.close();
		ptmt.close();
	}
	public ArrayList<NewsAndAnnouncements> getNews(String title) throws ClassNotFoundException, SQLException{

		ArrayList<NewsAndAnnouncements>  newsList=new ArrayList<NewsAndAnnouncements> ();
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		PreparedStatement ptmt=null;
		String selectQuery="select * from NEWS_ANNOUNCEMENTSTABLE where lower(NEWSTITLE)=lower(?)";
		ptmt = con.prepareStatement(selectQuery); 
		ptmt.setString(1, title);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			NewsAndAnnouncements news=new NewsAndAnnouncements();
			news.setNewsId(rs.getInt(1));
			news.setUserId(rs.getInt(2));
			news.setNews(rs.getString(3));
			news.setDate(rs.getString(4));
			news.setTitle(rs.getString(5));
			newsList.add(news);
		}
		con.close();
		ptmt.close();
		return newsList;
	}
	public ArrayList<NewsAndAnnouncements> getNewsList(int memberId) throws ClassNotFoundException, SQLException{
		ArrayList<NewsAndAnnouncements> newsList=new ArrayList<NewsAndAnnouncements>();
		
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		PreparedStatement ptmt=null;
		String selectQuery="select * from NEWS_ANNOUNCEMENTSTABLE WHERE USERID IS NULL OR USERID=?";
		ptmt = con.prepareStatement(selectQuery); 
		ptmt.setInt(1,memberId);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			NewsAndAnnouncements news=new NewsAndAnnouncements();
			news.setNewsId(rs.getInt(1));
			news.setUserId(rs.getInt(2));
			news.setNews(rs.getString(3));
			news.setDate(rs.getString(4));
			news.setTitle(rs.getString(5));
			newsList.add(news);
		}
		con.close();
		ptmt.close();
		return newsList;
	}
}
