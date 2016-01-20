package com.tcs.ilp.lms.dao;
import java.sql.*;
import java.util.ArrayList;
import com.tcs.ilp.lms.bean.Review;
import com.tcs.ilp.lms.utility.UtilityDB;
public class ReviewDAO {
	public int insertReview(Review review) throws SQLException, ClassNotFoundException{
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		PreparedStatement ptmt=null;
		String insertQuery="insert into REVIEW_DETAILSTABLE values(?,?,?,?,?)";
		ptmt = con.prepareStatement(insertQuery); 
		ptmt.setInt(1,review.getReviewId());
		ptmt.setString(2,review.getComments());
		ptmt.setString(3,review.getTitle());  
		ptmt.setInt(4,review.getMemberId()); 
		ptmt.setInt(5,review.getFinalRating()); 
		int i=ptmt.executeUpdate(); 
		System.out.println(i);
		con.close();
		ptmt.close();
		return i;
	}
	public int updateReview(Review review) throws ClassNotFoundException, SQLException{
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		PreparedStatement ptmt=null;
		String updateQuery="update REVIEW_DETAILSTABLE set comments=?,finalrating=? where reviewid=?";
		ptmt = con.prepareStatement(updateQuery); 
		ptmt.setString(1,review.getComments());
		ptmt.setInt(2, review.getFinalRating());
		ptmt.setInt(3,review.getReviewId());
		int i=ptmt.executeUpdate(); 
		System.out.println(i);
		con.close();
		ptmt.close();
		return i;
	}
	public int deleteReview(int reviewId) throws ClassNotFoundException, SQLException{
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		PreparedStatement ptmt=null;
		int status=1;
		String selectQuery="select reviewid from REVIEW_DETAILSTABLE where reviewid=?";
		ptmt = con.prepareStatement(selectQuery); 
		ptmt.setInt(1,reviewId);
		ResultSet rs=ptmt.executeQuery();
		if(rs==null){
			status=0;
		}
		String deleteQuery="delete from REVIEW_DETAILSTABLE where reviewid=?";
		ptmt = con.prepareStatement(deleteQuery); 
		ptmt.setInt(1,reviewId);
		ptmt.executeUpdate();
		con.close();
		ptmt.close();
		return status;
	}
	public ArrayList<Review> getReview() throws ClassNotFoundException, SQLException{
		ArrayList<Review> reviewList=new ArrayList<Review>();
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		Statement st=con.createStatement();
		System.out.println("all dao ");
		String selectQuery="select * from REVIEW_DETAILSTABLE";
		ResultSet rs=st.executeQuery(selectQuery);
		while(rs.next()){
			Review review=new Review();
			review.setReviewId(rs.getInt(1));
			review.setComments(rs.getString(2));
			review.setTitle(rs.getString(3));
			review.setMemberId(rs.getInt(4));
			review.setFinalRating(rs.getInt(5));
			reviewList.add(review);
		}
		con.close();
		return reviewList;
	}
	
	public int countTitleFromDb(String title,int memberId) throws ClassNotFoundException, SQLException{
		int count=0;
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		PreparedStatement ptmt=null;
		String selectQuery="select count(reviewid) from REVIEW_DETAILSTABLE where upper(title)=upper(?) and memberid=?";
		ptmt = con.prepareStatement(selectQuery); 
		ptmt.setString(1,title);
		ptmt.setInt(2,memberId);
		System.out.println("hello");
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			count=rs.getInt(1);
			System.out.println(count);
		}
		con.close();
		ptmt.close();
		return count;
	}
	public int idGen() throws ClassNotFoundException, SQLException{
		int id=0;
		int getId=0;
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		PreparedStatement ptmt=null;
		String selectQuery="select value from IDGEN where id=1";
		ptmt = con.prepareStatement(selectQuery); 
		System.out.println("hello");
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			getId=rs.getInt(1);
			System.out.println(getId);
		}
		id=getId+1;
		String updateQuery="update IDGEN set value=? where id=1";
		ptmt = con.prepareStatement(updateQuery); 
		ptmt.setInt(1, id);
		ptmt.executeUpdate();
		con.close();
		ptmt.close();
		return id;
	}
	public int countTransIdFromDb(String title,int memberId) throws ClassNotFoundException, SQLException{
		int count=1;
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		System.out.println(title);
		System.out.println(memberId);
		PreparedStatement ptmt=null;
		String selectItemId="select ITEMID from ITEM where upper(TITLE)=upper(?)";
		ptmt=con.prepareStatement(selectItemId);
		ptmt.setString(1,title);
		ResultSet rs=ptmt.executeQuery();
		ArrayList<Integer> items=new ArrayList<Integer>();
		ArrayList<Integer> trans=new ArrayList<Integer>();
		while(rs.next()){
			items.add(rs.getInt(1));
		}
		for(Integer i:items){
		String select="select TRANSID from TRANSACTION_DETAILS where ITEMID=? and MEMID=? and STATUS in ('R','R&P')";
		ptmt=con.prepareStatement(select);
		ptmt.setInt(1, i);
		ptmt.setInt(2,memberId);
		ResultSet rs2=ptmt.executeQuery();
		while(rs2.next()){
			trans.add(rs2.getInt(1));
		}
		}
		count=trans.size();
		con.close();
		ptmt.close();
		System.out.println(count);
		return count;
	}
	public ArrayList<Review> getListUsingTitleAndMemberId(String title,int memberId) throws SQLException, ClassNotFoundException{
		ArrayList<Review> reviewList=new ArrayList<Review>();
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		PreparedStatement ptmt=null;
		String selectQuery="select title,comments,finalrating from REVIEW_DETAILSTABLE where upper(title)=upper(?) and memberid=?";
		ptmt = con.prepareStatement(selectQuery); 
		ptmt.setString(1,title);
		ptmt.setInt(2,memberId);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			Review review=new Review();
			review.setTitle(rs.getString(1));
			review.setComments(rs.getString(2));
			review.setFinalRating(rs.getInt(3));
			reviewList.add(review);
		}
		con.close();
		ptmt.close();
		return reviewList;
	}
	public ArrayList<Review> getListUsingAuthor(String author,int memberId) throws SQLException, ClassNotFoundException{
		ArrayList<Review> reviewList=new ArrayList<Review>();
		UtilityDB db= new UtilityDB();
		Connection con=db.createConnection();
		PreparedStatement ptmt=null;
		System.out.println("hello author");
		System.out.println(memberId);
		System.out.println(author);
		ArrayList<Integer> items=new ArrayList<Integer>();
		ArrayList<Integer> trans=new ArrayList<Integer>();
		ArrayList<String> titles=new ArrayList<String>();
		String selectId="select ITEMID from book where author=?";
		ptmt=con.prepareStatement(selectId);
		ptmt.setString(1, author);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			System.out.println(rs.getInt(1));
			items.add(rs.getInt(1));
		}
		rs.close();
		for(Integer i:items){
		String selectTitle="select TITLE from ITEM where ITEMID=?";
		ptmt=con.prepareStatement(selectTitle);
		ptmt.setInt(1, i.intValue());
		ResultSet rs2=ptmt.executeQuery();
		while(rs2.next()){
			System.out.println(rs2.getString(1));
			titles.add(rs2.getString(1));
		}
		rs2.close();
		}
		ResultSet rs3;
		for(String t:titles){
		String selectReview="select title,comments,finalrating from REVIEW_DETAILSTABLE where upper(TITLE)=upper(?) and MEMBERID=?";
		ptmt=con.prepareStatement(selectReview);
		ptmt.setString(1, t);
		ptmt.setInt(2, memberId);
		rs3=ptmt.executeQuery();
		if(rs3!=null){
		while(rs3.next()){
			Review review=new Review();
			review.setTitle(rs3.getString(1));
			review.setComments(rs3.getString(2));
			review.setFinalRating(rs3.getInt(3));
			reviewList.add(review);
		}
		}
		}
		con.close();
		ptmt.close();
		return reviewList;
	}
}

