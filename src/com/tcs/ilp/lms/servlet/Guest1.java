package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;

import com.tcs.ilp.lms.bean.BookBean1;
import com.tcs.ilp.lms.bean.CdBean1;
import com.tcs.ilp.lms.bean.JournalBean1;
import com.tcs.ilp.lms.bean.LoginBean;
import com.tcs.ilp.lms.bean.MagazineBean1;
import com.tcs.ilp.lms.bean.MasterProfileBean;
import com.tcs.ilp.lms.bean.NewsAndAnnouncements;
import com.tcs.ilp.lms.dao.ReviewDAO;
import com.tcs.ilp.lms.service.AdminService;
import com.tcs.ilp.lms.service.LibrarianService;
import com.tcs.ilp.lms.service.LoginService;
import com.tcs.ilp.lms.service.MemberService;

/**
 * Servlet implementation class Guest1
 */
public class Guest1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Guest1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		System.out.println(page);

		if (page.equalsIgnoreCase("SignOutMember"))
		{
			ArrayList<Integer> newsList1=new ArrayList<Integer>();
			HttpSession sessionNews=request.getSession(false);
			newsList1=(ArrayList<Integer>) sessionNews.getAttribute("newsList");
			if(newsList1.size()!=0)
			{
				System.out.println(newsList1.size());
				AdminService adminService=new AdminService();
				for(int newsId:newsList1)
				{
					adminService.deleteNews(newsId);
				}
			}
			sessionNews.invalidate();
			System.out.println("after invalidate1");
			HttpSession session = request.getSession(false);
			System.out.println("after session");
			try
			{
				session.invalidate();
			}
			catch(Exception e)
			{
				response.sendRedirect("GuestHome.html");
			}
		}

		if (page.equalsIgnoreCase("SignOut"))
		{
			HttpSession session=request.getSession(false);
			session.invalidate();
			response.sendRedirect("GuestHome.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		System.out.println(page);
		if((page.equalsIgnoreCase("GuestAbout"))||(page.equalsIgnoreCase("GuestContactUs"))||(page.equalsIgnoreCase("GuestGallery"))||(page.equalsIgnoreCase("GuestMembershipPolicies"))||(page.equalsIgnoreCase("GuestRegistration"))||(page.equalsIgnoreCase("GuestHome"))||(page.equalsIgnoreCase("GuestSignUp")))
		{
			String button = request.getParameter("submit");
			if(button.equalsIgnoreCase("SignIn"))
			{
				try
				{
					Integer userId = Integer.parseInt(request.getParameter("userId"));
					String password = request.getParameter("password");
					HttpSession session = request.getSession();
					session.setAttribute("userId", userId);
					session.setMaxInactiveInterval(300);
					int check = 0;
					LoginBean loginBean = new LoginBean(userId,password);
					LoginService loginService = new LoginService();
					check = loginService.validate(loginBean);
					MemberService memService1 = new MemberService();
					if(check==2)
					{
						String role = loginService.getRole(loginBean);
						if(role.equalsIgnoreCase("Admin"))
						{
							session.setAttribute("role", "Admin");
							AdminService adminService = new AdminService();
							ArrayList<String> changes = new ArrayList<String>();
							changes = adminService.getChangeLog();
							request.setAttribute("changes", changes);
							response.setContentType("text/html");
							request.getRequestDispatcher("html/AdminHome.jsp").forward(request,response);
						}
						else if(role.equalsIgnoreCase("Member"))
						{
							System.out.println("MemberLogin");
							session.setAttribute("role", "Member");
							ArrayList<Integer> newsList=new ArrayList<Integer>();
							List<BookBean1> books=new ArrayList<BookBean1>();
							List<CdBean1> cds=new ArrayList<CdBean1>();
							List<MagazineBean1> magazines=new ArrayList<MagazineBean1>();
							List<JournalBean1> journals=new ArrayList<JournalBean1>();
							books=memService1.viewBookList(userId);
							cds=memService1.viewCdList(userId);
							magazines=memService1.viewMagazineList(userId);
							journals=memService1.viewJournalList(userId);
							AdminService admin1=new AdminService();
							int result=0;
							for(BookBean1 book:books)
							{
								long oneday=(24*60*60*1000);
								Date currentdate = new Date();
								long difference=(book.getDueDate().getTime()-currentdate.getTime());
								int noOfDays =(int)(difference/oneday);
								System.out.println("no of days"+noOfDays);
								if(noOfDays<=3 && noOfDays>=0)
								{
									System.out.println("inside if loop");
									Date date=new Date();
									NewsAndAnnouncements news=new NewsAndAnnouncements();
									ReviewDAO reviewDao=new ReviewDAO();
									int newsId=reviewDao.idGen();
									newsList.add(newsId);
									news.setNewsId(newsId);
									news.setUserId(userId);
									news.setTitle("Book Return Reminder");
									DateFormat formatter = DateFormat.getDateInstance();
									String date1 = formatter.format(date);
									news.setDate(date1);
									news.setNews("Return Book "+book.getBookName()+ " by "+book.getDueDate());
									result= admin1.addNews(news);
									System.out.println("book reminder");
								}
							}
							for(CdBean1 cd:cds)
							{
								long oneday=(24*60*60*1000);
								Date currentdate = new Date();
								long difference=(cd.getDueDate().getTime()-currentdate.getTime());
								int noOfDays =(int)(difference/oneday);
								if(noOfDays<=3 && noOfDays>=0)
								{
									Date date=new Date();
									NewsAndAnnouncements news=new NewsAndAnnouncements();
									ReviewDAO reviewDao=new ReviewDAO();
									int newsId=reviewDao.idGen();
									newsList.add(newsId);
									news.setNewsId(newsId);
									news.setUserId(userId);
									news.setTitle("CD Return Reminder");
									DateFormat formatter = DateFormat.getDateInstance();
									String date1 = formatter.format(date);
									news.setDate(date1);
									news.setNews("Return CD "+cd.getCdName()+ " by "+cd.getDueDate());
									result=admin1.addNews(news);
									System.out.println("cd reminder");
								}
							}
							for(MagazineBean1 magazine:magazines)
							{
								long oneday=(24*60*60*1000);
								Date currentdate = new Date();
								long difference=(magazine.getDueDate().getTime()-currentdate.getTime());
								int noOfDays =(int)(difference/oneday);
								if(noOfDays<=3 && noOfDays>=0)
								{
									Date date=new Date();
									NewsAndAnnouncements news=new NewsAndAnnouncements();
									ReviewDAO reviewDao=new ReviewDAO();
									int newsId=reviewDao.idGen();
									newsList.add(newsId);
									System.out.println(newsId);
									news.setNewsId(newsId);
									news.setUserId(userId);
									news.setTitle("Magazine Return Reminder");
									DateFormat formatter = DateFormat.getDateInstance();
									String date1 = formatter.format(date);
									news.setDate(date1);
									news.setNews("Return magazine "+magazine.getMagazineName()+ " by "+magazine.getDueDate());
									result= admin1.addNews(news);
									System.out.println("magazine reminder");
									System.out.println(magazine.getDueDate());
								}
							}

							for(JournalBean1 journal:journals)
							{
								long oneday=(24*60*60*1000);
								Date currentdate = new Date();
								long difference=(journal.getDueDate().getTime()-currentdate.getTime());
								int noOfDays =(int)(difference/oneday);
								if(noOfDays<=3 && noOfDays>=0)
								{
									Date date=new Date();
									NewsAndAnnouncements news=new NewsAndAnnouncements();
									ReviewDAO reviewDao=new ReviewDAO();
									int newsId=reviewDao.idGen();
									newsList.add(newsId);
									news.setNewsId(newsId);

									news.setUserId(userId);
									news.setTitle("journal Return Reminder");
									DateFormat formatter = DateFormat.getDateInstance();
									String date1 = formatter.format(date);
									news.setDate(date1);
									news.setNews("Return journal "+journal.getJournalName()+ " by "+journal.getDueDate());
									result=admin1.addNews(news);
									System.out.println("journal reminder");
								}
							}
							HttpSession sessionNews=request.getSession(true);
							sessionNews.setAttribute("newsList", newsList);
							response.setContentType("text/html");
							response.sendRedirect("jsp/MemberHome.jsp");
						}
						else if(role.equalsIgnoreCase("Librarian"))
						{
							session.setAttribute("role", "Librarian");
							LibrarianService librarianService = new LibrarianService();
							ArrayList<String> changes = new ArrayList<String>();
							changes = librarianService.getChangeLog();
							request.setAttribute("changes", changes);
							response.setContentType("text/html");
							request.getRequestDispatcher("html/LibrarianHome.jsp").forward(request,response);
						}
						else
						{
							String confirm = "Login credentials are wrong! Please register to access the website!";
							System.out.println(confirm);
							request.setAttribute("confirm", confirm);
							ArrayList<String> masterList = new ArrayList<String>();
							MemberService memberService = new MemberService();
							masterList = memberService.getMasterList();
							request.setAttribute("masterList", masterList);
							for(String master: masterList)
							{
								System.out.println(master);
							}
							request.getRequestDispatcher("html/GuestRegistrationError.jsp").forward(request,response);
						}
					}
					else if(check==1)
					{
						response.setContentType("text/html");
						response.sendRedirect("html/ChangePassword.jsp");
					}
					else if(check==3)
					{
						String confirm = "Login credentials are wrong! Please register to access the website!";
						System.out.println(confirm);
						request.setAttribute("confirm", confirm);
						ArrayList<String> masterList = new ArrayList<String>();
						MemberService memberService = new MemberService();
						masterList = memberService.getMasterList();
						request.setAttribute("masterList", masterList);
						for(String master: masterList)
						{
							System.out.println(master);
						}
						request.getRequestDispatcher("html/GuestRegistrationError.jsp").forward(request,response);			
					}
				}
				catch(Exception e)
				{
					String confirm = "Login credentials are wrong! Please register to access the website!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					ArrayList<String> masterList = new ArrayList<String>();
					MemberService memberService = new MemberService();
					masterList = memberService.getMasterList();
					request.setAttribute("masterList", masterList);
					for(String master: masterList)
					{
						System.out.println(master);
					}
					request.getRequestDispatcher("html/GuestRegistrationError.jsp").forward(request,response);			
				}
			}
			else if(button.equalsIgnoreCase("Register"))
			{
				ArrayList<String> masterList = new ArrayList<String>();
				MemberService memberService = new MemberService();
				masterList = memberService.getMasterList();
				request.setAttribute("masterList", masterList);
				for(String master: masterList)
				{
					System.out.println(master);
				}
				request.getRequestDispatcher("html/GuestSignUp.jsp").forward(request,response);
			}
		}
		else if(page.equalsIgnoreCase("ChangePassword"))
		{
			HttpSession session = request.getSession(false);
			int userId = (Integer)session.getAttribute("userId");
			String password = request.getParameter("password");
			String cpassword = request.getParameter("cpassword");
			System.out.println(cpassword);
			LoginService loginService = new LoginService();
			int check = 0;
			check = loginService.changePassword(password,cpassword,userId);
			if(check==1)
			{
				response.setContentType("text/html");
				response.sendRedirect("GuestHome.html");
			}
			else if(check==2)
			{
				String error = "Please enter the same Password";
				request.setAttribute("error", error);
				request.getRequestDispatcher("html/ChangePasswordError.jsp").forward(request,response);
			}
			else if(check==3)
			{
				String error = "Please enter a different Password";
				request.setAttribute("error", error);
				request.getRequestDispatcher("html/ChangePasswordError.jsp").forward(request,response);
			}	
		}
	}
}
