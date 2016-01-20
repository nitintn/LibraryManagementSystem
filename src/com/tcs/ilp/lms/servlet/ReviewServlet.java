package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.ilp.lms.bean.Review;
import com.tcs.ilp.lms.dao.ReviewDAO;
import com.tcs.ilp.lms.service.MemberService;

/**
 * Servlet implementation class ReviewServlet
 */
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Review review=new Review();
		MemberService member= new MemberService();
		int memberId=0;
		HttpSession session1=request.getSession(false);
	    int userId=(Integer) session1.getAttribute("userId");
		if(request.getParameter("giveReviewSubmit")!=null){
			int reviewid=0;
			ReviewDAO reviewDao=new ReviewDAO();
			try {
				reviewid=reviewDao.idGen();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			review.setMemberId(userId);
			review.setTitle(request.getParameter("iTitleText"));
			review.setComments(request.getParameter("comment"));
			review.setReviewId(reviewid);
			int[] answers=new int[4];
			answers[0]=Integer.parseInt(request.getParameter("review1"));
			answers[1]=Integer.parseInt(request.getParameter("review2"));
			answers[2]=Integer.parseInt(request.getParameter("review3"));
			answers[3]=Integer.parseInt(request.getParameter("review4"));
			int finalRating=member.calculateRating(answers);
			review.setFinalRating(finalRating);
			int i=0; 
			i=member.addReview(review);//if i=0 display "cannot add" else save the values
			if(i==0){
				System.out.println("Cannot add");
			}
			else{
				System.out.println("can add");
			}
			request.setAttribute("addStatus", i);
			request.getRequestDispatcher("./jsp/GiveReview2.jsp").forward(request,response);
			}
		else if(request.getParameter("searchTitleButton")!=null){
				session1.setAttribute("updateReviewCategory",request.getParameter("category"));
				ArrayList<Review> reviewList=new ArrayList<Review>();
				memberId=userId;
				String title=request.getParameter("iTitleText");
				System.out.println(title);
				String error;
				reviewList=member.viewTitleOnTitle(memberId, title);
				ArrayList<String> titleList=new ArrayList<String>();
				ArrayList<String> commentList=new ArrayList<String>();
				for(Review r:reviewList){
					titleList.add(r.getTitle());
					commentList.add(r.getComments());
				}
				if(reviewList.size()==0){
					error="There are no reviews for the given title";
					request.setAttribute("error",error );
					request.getRequestDispatcher("./jsp/ErrorMessages.jsp").forward(request,response);
				}
				else{
				request.setAttribute("reviewList", reviewList);
				request.getRequestDispatcher("./jsp/UpdateReview2.jsp").forward(request,response);
				}
			}
		else if(request.getParameter("searchTitleButton2")!=null){
			ArrayList<Review> reviewList=new ArrayList<Review>();
			String error;
			memberId=userId;
			String title=request.getParameter("title");
			reviewList=member.viewTitleOnTitle(memberId, title);
			ArrayList<String> titleList=new ArrayList<String>();
			ArrayList<String> commentList=new ArrayList<String>();
			for(Review r:reviewList){
				titleList.add(r.getTitle());
				commentList.add(r.getComments());
			}
			if(reviewList.size()==0){
				error="There are no reviews for the given title";
				request.setAttribute("error",error );
				request.getRequestDispatcher("./jsp/ErrorMessages.jsp").forward(request,response);
			}
			else{
			request.setAttribute("reviewList", reviewList);
			request.getRequestDispatcher("./jsp/deleteReview2.jsp").forward(request,response);
			}
		}
			else if(request.getParameter("searchAuthorButton")!=null){
				session1.setAttribute("updateReviewCategory",request.getParameter("category"));
				ArrayList<Review> reviewList=new ArrayList<Review>();
				System.out.println("hiiii");
				String error;
				memberId=userId;
				String author=request.getParameter("author");
				reviewList=member.viewTitleOnAuthor(memberId, author);
				ArrayList<String> titleList=new ArrayList<String>();
				ArrayList<String> commentList=new ArrayList<String>();
				for(Review r:reviewList){
					titleList.add(r.getTitle());
					commentList.add(r.getComments());
				}
				if(reviewList.size()==0){
					error="There are no reviews for the given author";
					request.setAttribute("error",error );
					request.getRequestDispatcher("./jsp/ErrorMessages.jsp").forward(request,response);
				}
				else{
				request.setAttribute("reviewList", reviewList);
				request.getRequestDispatcher("./jsp/UpdateReview2.jsp").forward(request,response);
				}
			}
			else if(request.getParameter("searchAuthorButton2")!=null){
				ArrayList<Review> reviewList=new ArrayList<Review>();
				System.out.println("hiiii");
				String error="";
				memberId=userId;
				String author=request.getParameter("author");
				System.out.println(author);
				reviewList=member.viewTitleOnAuthor(memberId, author);
				ArrayList<String> titleList=new ArrayList<String>();
				ArrayList<String> commentList=new ArrayList<String>();
				for(Review r:reviewList){
					titleList.add(r.getTitle());
					commentList.add(r.getComments());
				}
				if(reviewList.size()==0){
					error="There are no reviews for the given author";
					request.setAttribute("error",error );
					request.getRequestDispatcher("./jsp/ErrorMessages.jsp").forward(request,response);
				}
				else{
				request.setAttribute("reviewList", reviewList);
				request.getRequestDispatcher("./jsp/deleteReview2.jsp").forward(request,response);
				}
			}
			else if(request.getParameter("updateButton")!=null){
				String selectedTitle=request.getParameter("reviews");
				System.out.println(selectedTitle);
				Review review1=new Review();
				review1.setComments(request.getParameter("comment"));
				System.out.println(review1.getComments());
				int reviewId=member.getReviewId(selectedTitle);
				System.out.println(reviewId);
				review1.setMemberId(memberId);
				review1.setReviewId(reviewId);
				review1.setTitle(selectedTitle);
				int[] answers=new int[4];
				answers[0]=Integer.parseInt(request.getParameter("review1"));
				answers[1]=Integer.parseInt(request.getParameter("review2"));
				answers[2]=Integer.parseInt(request.getParameter("review3"));
				answers[3]=Integer.parseInt(request.getParameter("review4"));
				int finalRating=member.calculateRating(answers);
				review1.setFinalRating(finalRating);
				int flag=0;
				try {
					flag=member.updateReview(review1);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("flag", flag);
				request.getRequestDispatcher("./jsp/UpdateReview2.jsp").forward(request,response);
			}
		else if(request.getParameter("searchViewTitleButton")!=null){
			System.out.println("hello");
			if(request.getParameter("viewSelect").equalsIgnoreCase("all")){
				System.out.println("all");
				String error;
				ArrayList<Review> reviewList=new ArrayList<Review>();
				String title=request.getParameter("title");
				reviewList=member.viewAllReview(title);
				if(reviewList.size()==0){
					error="There are no reviews for the given title";
					request.setAttribute("error",error );
					request.getRequestDispatcher("./jsp/ErrorMessages.jsp").forward(request,response);
				}
				else{
				request.setAttribute("reviewList", reviewList);
				request.getRequestDispatcher("./jsp/viewReview2.jsp").forward(request,response);
				}
			}
			else if(request.getParameter("viewSelect").equalsIgnoreCase("my")){
				System.out.println("my");
				ArrayList<Review> reviewList=new ArrayList<Review>();
				String error;
				String title=request.getParameter("title");
				memberId=userId;
				reviewList=member.viewMyReview(memberId,title);
				if(reviewList.size()==0){
					error="There are no reviews for the given title";
					request.setAttribute("error",error );
					request.getRequestDispatcher("./jsp/ErrorMessages.jsp").forward(request,response);
				}
				else{
				request.setAttribute("reviewList", reviewList);
				request.getRequestDispatcher("./jsp/viewReview2.jsp").forward(request,response);
				}
			}
		}
		else if(request.getParameter("adminSearchViewTitleButton")!=null){
			System.out.println("hello");
				System.out.println("all");
				ArrayList<Review> reviewList=new ArrayList<Review>();
				String title=request.getParameter("title");
				String error;
				reviewList=member.viewAllReview(title);
				if(reviewList.size()==0){
					error="There are no reviews for the given title";
					request.setAttribute("error",error );
					request.getRequestDispatcher("./jsp/ErrorMessages2.jsp").forward(request,response);
				}
				else{
				request.setAttribute("reviewList", reviewList);
				request.getRequestDispatcher("./jsp/viewAdminReview2.jsp").forward(request,response);
				}
		}
		else if(request.getParameter("librarianSearchViewTitleButton")!=null){
			System.out.println("hello");
				System.out.println("all");
				ArrayList<Review> reviewList=new ArrayList<Review>();
				String title=request.getParameter("title");
				String error;
				reviewList=member.viewAllReview(title);
				if(reviewList.size()==0){
					error="There are no reviews for the given title";
					request.setAttribute("error",error );
					request.getRequestDispatcher("./jsp/ErrorMessages3.jsp").forward(request,response);
				}
				else{
				request.setAttribute("reviewList", reviewList);
				request.getRequestDispatcher("./jsp/viewLibrarianReview2.jsp").forward(request,response);
				}
		}
		else if(request.getParameter("deleteButton")!=null){
			String selectedTitle=request.getParameter("reviews");
			String error;
			
			int reviewId=member.getReviewId(selectedTitle);
			int status;
			Review r=new Review();
			r.setMemberId(userId);
			r.setTitle(selectedTitle);
			r.setReviewId(reviewId);
			status=member.deleteReview(r);
			if(selectedTitle==null){
				error="You did not select any reviews to delete!!!";
				request.setAttribute("error",error );
				request.getRequestDispatcher("./jsp/ErrorMessages.jsp").forward(request,response);
			}
			else{
			error="Deleted Successfully";
			request.setAttribute("error",error );
			request.getRequestDispatcher("./jsp/ErrorMessages.jsp").forward(request,response);
			}
			}
		}
	}
