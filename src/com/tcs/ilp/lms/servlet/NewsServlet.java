package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcs.ilp.lms.bean.NewsAndAnnouncements;
import com.tcs.ilp.lms.dao.ReviewDAO;
import com.tcs.ilp.lms.service.AdminService;
import com.tcs.ilp.lms.service.MemberService;

/**
 * Servlet implementation class NewsServlet
 */
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewsServlet() {
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
		NewsAndAnnouncements latestNews =new NewsAndAnnouncements();
		NewsAndAnnouncements news1 =new NewsAndAnnouncements();
		ReviewDAO reviewDao=new ReviewDAO();
		int nid=0;
		try {
			nid=reviewDao.idGen();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<NewsAndAnnouncements> getNews=new ArrayList<NewsAndAnnouncements>();
		ArrayList<NewsAndAnnouncements> getNews1=new ArrayList<NewsAndAnnouncements>();
		AdminService admin=new AdminService();
		MemberService member=new MemberService();
		if(request.getParameter("newsSubmit")!=null){
			if(request.getParameter("sel").equalsIgnoreCase("All"))
			{


				
				latestNews.setTitle(request.getParameter("title"));
				latestNews.setNews(request.getParameter("news"));
				latestNews.setNewsId(nid);
				latestNews.setDate(request.getParameter("date"));
				admin.addNews1(latestNews);
				int i=0;
				request.setAttribute("obj1", i);
				request.getRequestDispatcher("./jsp/AddNews2.jsp").forward(request,response);
			}
			else 
			{
				
			latestNews.setTitle(request.getParameter("title"));
			latestNews.setNews(request.getParameter("news"));
			latestNews.setNewsId(nid);
			latestNews.setUserId(Integer.parseInt(request.getParameter("uid")));
			latestNews.setDate(request.getParameter("date"));

			admin.addNews(latestNews);
			int i=0;
			request.setAttribute("obj1", i);
			request.getRequestDispatcher("./jsp/AddNews2.jsp").forward(request,response);
			}

		}
  

		if(request.getParameter("deleteNews1")!=null)
		{
			String temp=request.getParameter("title");
			getNews1=admin.viewNews(temp);
			request.setAttribute("obj", getNews1);
			request.getRequestDispatcher("./jsp/DeleteNews2.jsp").forward(request,response);
			

		}
			
		else if(request.getParameter("deleteNews2")!=null)
		{
		
			int temp1=Integer.parseInt(request.getParameter("select"));
			System.out.println(temp1);
			admin.deleteNews(temp1);
			int i=0;
			request.setAttribute("obj1", i);
			request.getRequestDispatcher("./jsp/DeleteNews3.jsp").forward(request,response);

		}
		
		if(request.getParameter("viewNews")!=null)
		{
			
			int temp2=(Integer.parseInt(request.getParameter("memId")));
			try {
				getNews=member.viewNews(temp2);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("view", getNews);
			request.getRequestDispatcher("./jsp/viewNews2.jsp").forward(request, response);
		}
	}

}
