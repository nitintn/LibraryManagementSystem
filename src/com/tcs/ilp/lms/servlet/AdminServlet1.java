package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.ilp.lms.bean.BookBean;
import com.tcs.ilp.lms.bean.CdBean;
import com.tcs.ilp.lms.bean.JournalBean;
import com.tcs.ilp.lms.bean.MagazineBean;
import com.tcs.ilp.lms.bean.MasterProfileBean;
import com.tcs.ilp.lms.bean.MemberBean;
import com.tcs.ilp.lms.bean.UserBean;
import com.tcs.ilp.lms.service.AdminService;
import com.tcs.ilp.lms.service.MemberService;
import com.tcs.ilp.lms.service.RackService;

/**
 * Servlet implementation class AdminServlet1
 */
public class AdminServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet1() {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter("Page");
		System.out.println(page);
		int userId = 0;
		String role = "";
		HttpSession session = request.getSession(false);
		try
		{
			userId = (Integer)session.getAttribute("userId");
/*			role = (String)session.getAttribute("role");
			if(!(role.equalsIgnoreCase("Admin")))
			{
				request.getRequestDispatcher("GuestHome.jsp").forward(request,response);
			}*/
			String menu = request.getParameter("Button");
			String button = request.getParameter("Submit");
			if(page.equalsIgnoreCase("Menu"))
			{
				if(menu.equalsIgnoreCase("UpgradeMemberType"))
				{
					ArrayList<String> masterList = new ArrayList<String>();
					MemberService memberService=new MemberService();	
					System.out.println(button);
					masterList = memberService.getMasterProfileList(userId);	
					int flag=0;
					for(String s : masterList)
					{
						System.out.println(s);
						if(s.equalsIgnoreCase("Cannot"))
						{
							flag=1;
							System.out.println(s);
							request.getRequestDispatcher("html/upgradationPage.jsp").forward(request,response);
							break;
						}
					}	
					if(flag==0)
					{
						request.setAttribute("masterList", masterList);
						request.getRequestDispatcher("html/MemberTypeUpgrade.jsp").forward(request,response);
					}
				}
			}
			if(page.equalsIgnoreCase("UpgradeTwo"))
			{
				System.out.println(button);
				if("Upgrade".equalsIgnoreCase(button))
				{
					MemberService memberService=new MemberService();
					System.out.println("upgrade");
					String drop = request.getParameter("memberType");				
					long fees = memberService.getFees(drop,userId);
					System.out.println(fees);
					request.setAttribute("fees", fees);
					request.getRequestDispatcher("html/fees.jsp").forward(request,response);
				}
			}
			else if(page.equalsIgnoreCase("MasterProfileUpdate"))
			{
				//ArrayList<String> masterList = new ArrayList<String>();
				ArrayList<MasterProfileBean> masterList = new ArrayList<MasterProfileBean>();
				AdminService adminService=new AdminService();
				button = request.getParameter("Submit");
				String button1 = request.getParameter("Update");
				System.out.println(button);
				System.out.println(button1);
				masterList = adminService.getMasterProfileList();
				request.setAttribute("masterList", masterList);
				String isFirst = request.getParameter("isFirst");
				if(isFirst.equalsIgnoreCase("first"))
				{	
					//request.getRequestDispatcher("html/MasterProfileUpdate1.jsp").forward(request,response);	
					request.getRequestDispatcher("html/UpdateMaster.jsp").forward(request,response);
				}
				if("Update".equalsIgnoreCase(button))
				{
					int result = 0;				
					int numberOfBook =Integer.parseInt(request.getParameter("NumberOfBook"));
					int numberOfCd= Integer.parseInt(request.getParameter("NumberOfCd"));
					int numberOfMagazine=Integer.parseInt(request.getParameter("NumberOfMagazine"));
					int numberOfJournal=Integer.parseInt(request.getParameter("NumberOfJournal"));
					String memberType = request.getParameter("memberType");

					float cost=Float.parseFloat(request.getParameter("Cost"));

					MasterProfileBean masterProfileBean = new MasterProfileBean();
					masterProfileBean.setNumberOfBook(numberOfBook);
					masterProfileBean.setNumberOfCd(numberOfCd);
					masterProfileBean.setNumberOfMagazine(numberOfMagazine);
					masterProfileBean.setNumberOfJournal(numberOfJournal);
					masterProfileBean.setCost(cost);
					masterProfileBean.setMemberType(memberType);

					AdminService adminService1 = new AdminService();
					result=adminService1.updatedMasterProfile(masterProfileBean);
					if(result == 1)
					{
						ArrayList <MasterProfileBean> listOne=new ArrayList<MasterProfileBean>();
						AdminService adservice= new AdminService();
						MasterProfileBean mp=new MasterProfileBean();
						listOne=adservice.retrieveMasterProfile(mp);
						request.setAttribute("xy",listOne );
						request.getRequestDispatcher("html/viewListOne.jsp").forward(request,response);
					}
				}
			}
			else if("DeleteUser".equalsIgnoreCase(page))
			{
				int userId1 = Integer.parseInt(request.getParameter("userId"));
				AdminService adminService = new AdminService();
				response.setContentType("text/html");
				UserBean userBean=new UserBean();
				userBean = adminService.searchUser(userId1);
				response.getWriter().write("<form method='post' action=../AdminServlet1>");
				response.getWriter().write("<table class=BookTable style='text-align:left'>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td>User ID : </td>");
				response.getWriter().write("<td><input type=text id=books name=BookId value="+userBean.getUserId()+"></td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td>First Name : </td>");
				response.getWriter().write("<td><input type=text name=Author value="+userBean.getFirstName()+"></td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td>Last Name : </td>");
				response.getWriter().write("<td><input type=text name=ISBN value="+userBean.getLastName()+"></td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td>Identity ID : </td>");
				response.getWriter().write("<td><input type=text name=Publisher value="+userBean.getIdentityId()+"></td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td>Role : </td>");
				response.getWriter().write("<td><input type=text name=Status value="+userBean.getRole()+"></td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td>Contact No : </td>");
				response.getWriter().write("<td><input type=text name=Status value="+userBean.getContact()+"></td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td>Email : </td>");
				response.getWriter().write("<td><input type=text name=Status value="+userBean.getEmail()+"></td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td>Address : </td>");
				response.getWriter().write("<td><input type=text name=Status value="+userBean.getAddress()+"></td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td> ");
				response.getWriter().write("</td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("<tr>");
				response.getWriter().write("<td colspan=2><input type=submit value=Delete></td>");
				response.getWriter().write("</tr>");
				response.getWriter().write("</table>");
				response.getWriter().write("<input type=hidden name=page value=DeleteUser>");
				response.getWriter().write("<input type=hidden name=userId value="+userBean.getUserId()+">");
				response.getWriter().write("<input type=hidden name=role value="+userBean.getRole()+">");
				response.getWriter().write("</form>");		
			}
			else if("DeleteItem".equalsIgnoreCase(page))
			{
				String title = request.getParameter("Type");
				int itemId = Integer.parseInt(request.getParameter("Id"));
				System.out.println(itemId);
				if(title.equalsIgnoreCase("book"))
				{

					AdminService adminService = new AdminService();
					response.setContentType("text/html");
					BookBean bookBean=new BookBean();
					bookBean.setItemId(itemId);
					bookBean=adminService.searchBook(bookBean);

					response.getWriter().write("<form method=post action=../AdminServlet1>");
					response.getWriter().write("<table class=BookTable>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>BookId</td>");
					response.getWriter().write("<td><input type=text id=books name=BookId value="+bookBean.getBookId()+" ></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Author</td>");
					response.getWriter().write("<td><input type=text name=Author value="+bookBean.getAuthor()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>ISBN</td>");
					response.getWriter().write("<td><input type=text name=ISBN value="+bookBean.getIsbn()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Publisher</td>");
					response.getWriter().write("<td><input type=text name=Publisher value="+bookBean.getPublisher()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Status</td>");
					response.getWriter().write("<td><input type=text name=Status value="+bookBean.getStatus()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td colspan=2><input type=submit value=Delete></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("</table>");
					response.getWriter().write("<input type=hidden name=page value=DeleteItem>");
					response.getWriter().write("<input type=hidden name=ItemId value="+itemId+">");
					response.getWriter().write("<input type=hidden name=ItemType value="+title+">");
					response.getWriter().write("</form>");

				}
				else if(title.equalsIgnoreCase("cd"))
				{
					AdminService adminService = new AdminService();
					response.setContentType("text/html");
					CdBean cdBean=new CdBean();
					cdBean.setItemId(itemId);
					cdBean=adminService.searchCd(cdBean);

					response.getWriter().write("<form method=post action=../AdminServlet1>");
					response.getWriter().write("<table class=CdTable>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>CdId</td>");
					response.getWriter().write("<td><input type=text id=cds name=cdId value="+cdBean.getCdId()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Subject</td>");
					response.getWriter().write("<td><input type=text name=Subject value="+cdBean.getSubject()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Publisher</td>");
					response.getWriter().write("<td><input type=text name=Publisher value="+cdBean.getPublisher()+"></td>");
					response.getWriter().write("</tr>");
					/*	response.getWriter().write("<tr>");
				response.getWriter().write("<td>Publisher</td>");
				response.getWriter().write("<td><input type=text name=Publisher value="+bookBean.getPublisher()+"></td>");
				response.getWriter().write("</tr>");*/
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Status</td>");
					response.getWriter().write("<td><input type=text name=Status value="+cdBean.getStatus()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td colspan=2><input type=submit value=Delete></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("</table>");
					response.getWriter().write("<input type=hidden name=page value=DeleteItem>");
					response.getWriter().write("<input type=hidden name=ItemId value="+itemId+">");
					response.getWriter().write("<input type=hidden name=ItemType value="+title+">");
					response.getWriter().write("</form>");

				}
				else if(title.equalsIgnoreCase("magazine"))
				{
					AdminService adminService = new AdminService();
					response.setContentType("text/html");
					MagazineBean magazineBean=new MagazineBean();
					magazineBean.setItemId(itemId);
					magazineBean=adminService.searchMagazine(magazineBean);

					response.getWriter().write("<form method=post action=../AdminServlet1>");
					response.getWriter().write("<table class=MagazineTable>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>BookId</td>");
					response.getWriter().write("<td><input type=text id=magazines name=MagazineId value="+magazineBean.getMagazineId()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>ISBN</td>");
					response.getWriter().write("<td><input type=text name=ISBN value="+magazineBean.getIsbn()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>VolumeNo</td>");
					response.getWriter().write("<td><input type=text name=VolumeNo value="+magazineBean.getVolumeNo()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Subscription Type</td>");
					response.getWriter().write("<td><input type=text name=Subtype value="+magazineBean.getSubscriptionType()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td colspan=2><input type=submit value=Delete></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("</table>");
					response.getWriter().write("<input type=hidden name=page value=DeleteItem>");
					response.getWriter().write("<input type=hidden name=ItemId value="+itemId+">");
					response.getWriter().write("<input type=hidden name=ItemType value="+title+">");
					response.getWriter().write("</form>");

				}
				else if(title.equalsIgnoreCase("journal"))
				{
					AdminService adminService = new AdminService();
					response.setContentType("text/html");
					JournalBean journalBean=new JournalBean();
					journalBean.setItemId(itemId);
					journalBean=adminService.searchJournal(journalBean);

					response.getWriter().write("<form method=post action=../AdminServlet1>");
					response.getWriter().write("<table class=JournalTable>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>BookId</td>");
					response.getWriter().write("<td><input type=text id=journals name=JournalId value="+journalBean.getJournalId()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>ISBN</td>");
					response.getWriter().write("<td><input type=text name=ISBN value="+journalBean.getIsbn()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>VolumeNo</td>");
					response.getWriter().write("<td><input type=text name=VolumeNo value="+journalBean.getVolumeNo()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Subscription Type</td>");
					response.getWriter().write("<td><input type=text name=Subtype value="+journalBean.getSubscriptionType()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<td>Status</td>");
					response.getWriter().write("<td><input type=text name=Status value="+journalBean.getStatus()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td colspan=2><input type=submit value=Delete></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("</table>");
					response.getWriter().write("<input type=hidden name=page value=DeleteItem>");
					response.getWriter().write("<input type=hidden name=ItemId value="+itemId+">");
					response.getWriter().write("<input type=hidden name=ItemType value="+title+">");
					response.getWriter().write("</form>");

				}
			}

			//search part

			else if("SearchItem".equalsIgnoreCase(page))
			{
				System.out.println("searching");
				String title1 = request.getParameter("Type");

				String title=request.getParameter("Id");

				if(title1.equalsIgnoreCase("book"))
				{

					AdminService adminService = new AdminService();
					response.setContentType("text/html");
					BookBean bookBean=new BookBean();
					bookBean.setTitle(title);

					bookBean=adminService.searchBookOnly(bookBean);

					response.getWriter().write("<form >");
					response.getWriter().write("<table class=BookTable>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>BookId</td>");
					response.getWriter().write("<td><input type=text id=books name=BookId value="+bookBean.getBookId()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Author</td>");
					response.getWriter().write("<td><input type=text name=Author value="+bookBean.getAuthor()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>ISBN</td>");
					response.getWriter().write("<td><input type=text name=ISBN value="+bookBean.getIsbn()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Publisher</td>");
					response.getWriter().write("<td><input type=text name=Publisher value="+bookBean.getPublisher()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Status</td>");
					response.getWriter().write("<td><input type=text name=Status value="+bookBean.getStatus()+"></td>");
					response.getWriter().write("</tr>");

					response.getWriter().write("</table>");
					response.getWriter().write("<input type=hidden name=page value=SearchItem>");
					response.getWriter().write("<input type=hidden name=isbn value="+title+">");
					response.getWriter().write("<input type=hidden name=ItemType1 value="+title1+">");
					response.getWriter().write("</form>");

				}
				else if(title1.equalsIgnoreCase("cd"))
				{
					/*
					 * AdminService adminService = new AdminService();
					response.setContentType("text/html");
					BookBean bookBean=new BookBean();
					bookBean.setTitle(title);
					bookBean=adminService.searchBookOnly(bookBean);
					 */
					AdminService adminService = new AdminService();
					response.setContentType("text/html");
					CdBean cdBean=new CdBean();
					cdBean.setTitle(title);
					cdBean=adminService.searchCdOnly(cdBean);

					response.getWriter().write("<form>");
					response.getWriter().write("<table class=CdTable>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>CdId</td>");
					response.getWriter().write("<td><input type=text id=cds name=cdId value="+cdBean.getCdId()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Subject</td>");
					response.getWriter().write("<td><input type=text name=Subject value="+cdBean.getSubject()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Publisher</td>");
					response.getWriter().write("<td><input type=text name=Publisher value="+cdBean.getPublisher()+"></td>");
					response.getWriter().write("</tr>");
					/*	response.getWriter().write("<tr>");
					response.getWriter().write("<td>Publisher</td>");
					response.getWriter().write("<td><input type=text name=Publisher value="+bookBean.getPublisher()+"></td>");
					response.getWriter().write("</tr>");*/
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Status</td>");
					response.getWriter().write("<td><input type=text name=Status value="+cdBean.getStatus()+"></td>");
					response.getWriter().write("</tr>");

					response.getWriter().write("</table>");
					response.getWriter().write("<input type=hidden name=page value=SearchItem>");
					response.getWriter().write("<input type=hidden name=ItemId value="+title+">");
					response.getWriter().write("<input type=hidden name=ItemType value="+title1+">");
					response.getWriter().write("</form>");
				}
				else if(title1.equalsIgnoreCase("magazine"))
				{
					AdminService adminService = new AdminService();
					response.setContentType("text/html");
					MagazineBean magazineBean=new MagazineBean();
					magazineBean.setTitle(title);
					magazineBean=adminService.searchMagazineOnly(magazineBean);

					response.getWriter().write("<form>");
					response.getWriter().write("<table class=MagazineTable>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Magazine Id</td>");
					response.getWriter().write("<td><input type=text id=magazines name=MagazineId value="+magazineBean.getMagazineId()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>ISBN</td>");
					response.getWriter().write("<td><input type=text name=ISBN value="+magazineBean.getIsbn()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>VolumeNo</td>");
					response.getWriter().write("<td><input type=text name=VolumeNo value="+magazineBean.getVolumeNo()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Subscription Type</td>");
					response.getWriter().write("<td><input type=text name=Subtype value="+magazineBean.getSubscriptionType()+"></td>");
					response.getWriter().write("</tr>");

					response.getWriter().write("</table>");
					response.getWriter().write("<input type=hidden name=page value=SearchItem>");
					response.getWriter().write("<input type=hidden name=ItemId value="+title+">");
					response.getWriter().write("<input type=hidden name=ItemType value="+title1+">");
					response.getWriter().write("</form>");

				}
				else if(title1.equalsIgnoreCase("journal"))
				{

					AdminService adminService = new AdminService();
					response.setContentType("text/html");
					JournalBean journalBean=new JournalBean();
					journalBean.setTitle(title);
					journalBean=adminService.searchJournalOnly(journalBean);

					response.getWriter().write("<form>");
					response.getWriter().write("<table class=JournalTable>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Journal Id</td>");
					response.getWriter().write("<td><input type=text id=journals name=JournalId value="+journalBean.getJournalId()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>ISBN</td>");
					response.getWriter().write("<td><input type=text name=ISBN value="+journalBean.getIsbn()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>VolumeNo</td>");
					response.getWriter().write("<td><input type=text name=VolumeNo value="+journalBean.getVolumeNo()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>Subscription Type</td>");
					response.getWriter().write("<td><input type=text name=Subtype value="+journalBean.getSubscriptionType()+"></td>");
					response.getWriter().write("</tr>");
					response.getWriter().write("<td>Status</td>");
					response.getWriter().write("<td><input type=text name=Status value="+journalBean.getStatus()+"></td>");
					response.getWriter().write("</tr>");

					response.getWriter().write("</table>");
					response.getWriter().write("<input type=hidden name=page value=SelectItem>");
					response.getWriter().write("<input type=hidden name=ItemId value="+title+">");
					response.getWriter().write("<input type=hidden name=ItemType value="+title1+">");
					response.getWriter().write("</form>");

				}
			}

			//DataTable
			else if("memberTypePage".equalsIgnoreCase(page))
			{
				System.out.println("secho:"+request.getParameter("sEcho"));
				System.out.println("iDisplayStart:"+request.getParameter("iDisplayStart"));
				System.out.println("sSortDir_0:"+request.getParameter("sSortDir_0"));
				System.out.println("iSortCol_0:"+request.getParameter("iSortCol_0"));
				System.out.println("iDisplayLength:"+request.getParameter("iDisplayLength"));
				System.out.println("i am in datatable::"+request.getParameter("sSearch"));
				System.out.println("iSortingCols:"+request.getParameter("iSortingCols"));

				int displayStart = Integer.parseInt(request.getParameter("iDisplayStart"))+1;
				String sortDir = request.getParameter("sSortDir_0");
				int sortCol = Integer.parseInt(request.getParameter("iSortCol_0"));
				int displayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
				String search =request.getParameter("sSearch");
				String secho =request.getParameter("sEcho");

				PrintWriter out = response.getWriter();
				//"+"\"sEcho\": "+secho+",
				//\"sEcho\": 2,

				if(search==null || search.equalsIgnoreCase(""))
				{

					ArrayList<MasterProfileBean> masterList = new ArrayList<MasterProfileBean>();
					AdminService adminService = new AdminService();
					masterList = adminService.getMemberTypeList(displayStart,sortDir,sortCol,displayLength,userId);
					int count = masterList.size();
					String data = "";
					int totalCount = adminService.getMemberTypeCount(userId);
					int size = 0;
					for(MasterProfileBean list: masterList)
					{
						String category = list.getMemberType();
						int books = list.getNumberOfBook();
						int cds = list.getNumberOfCd();
						int magazines = list.getNumberOfMagazine();
						int journals = list.getNumberOfJournal();
						float cost = list.getCost();
						if(size == masterList.size()-1)
						{
							data = data+"{ \"category\": \""+category+"\", \"books\": \""+books+"\", \"cds\": \""+cds+"\", \"magazines\": \""+magazines+"\", \"journals\": \""+journals+"\", \"cost\": \""+cost+"\" } ";
						}
						else
						{
							data = data+"{ \"category\": \""+category+"\", \"books\": \""+books+"\", \"cds\": \""+cds+"\", \"magazines\": \""+magazines+"\", \"journals\": \""+journals+"\", \"cost\": \""+cost+"\" }, ";	
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+totalCount+", \"aaData\": ["+data+"]}" +"");
				}
				else if(search!=null && !search.equalsIgnoreCase(""))
				{
					search = search.toUpperCase();
					ArrayList<MasterProfileBean> masterList = new ArrayList<MasterProfileBean>();
					AdminService adminService = new AdminService();
					masterList = adminService.getMemberTypeListSearch(displayStart,sortDir,sortCol,displayLength,search,userId);
					int count = masterList.size();
					String data = "";
					int totalCount = adminService.getMemberTypeCount(userId);
					int size = 0;
					for(MasterProfileBean list: masterList)
					{
						String category = list.getMemberType();
						int books = list.getNumberOfBook();
						int cds = list.getNumberOfCd();
						int magazines = list.getNumberOfMagazine();
						int journals = list.getNumberOfJournal();
						float cost = list.getCost();
						if(size == masterList.size()-1)
						{
							data = data+"{ \"category\": \""+category+"\", \"books\": \""+books+"\", \"cds\": \""+cds+"\", \"magazines\": \""+magazines+"\", \"journals\": \""+journals+"\", \"cost\": \""+cost+"\" } ";
						}
						else
						{
							data = data+"{ \"category\": \""+category+"\", \"books\": \""+books+"\", \"cds\": \""+cds+"\", \"magazines\": \""+magazines+"\", \"journals\": \""+journals+"\", \"cost\": \""+cost+"\" }, ";	
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+count+", \"aaData\": ["+data+"]}" +"");

				}
			}
			else if("datatable".equalsIgnoreCase(page))
			{
				System.out.println("secho:"+request.getParameter("sEcho"));
				System.out.println("iDisplayStart:"+request.getParameter("iDisplayStart"));
				System.out.println("sSortDir_0:"+request.getParameter("sSortDir_0"));
				System.out.println("iSortCol_0:"+request.getParameter("iSortCol_0"));
				System.out.println("iDisplayLength:"+request.getParameter("iDisplayLength"));
				System.out.println("i am in datatable::"+request.getParameter("sSearch"));
				System.out.println("iSortingCols:"+request.getParameter("iSortingCols"));

				int displayStart = Integer.parseInt(request.getParameter("iDisplayStart"))+1;
				String sortDir = request.getParameter("sSortDir_0");
				int sortCol = Integer.parseInt(request.getParameter("iSortCol_0"));
				int displayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
				String search =request.getParameter("sSearch");
				String secho =request.getParameter("sEcho");

				PrintWriter out = response.getWriter();
				//"+"\"sEcho\": "+secho+",
				//\"sEcho\": 2,

				if(search==null || search.equalsIgnoreCase(""))
				{

					ArrayList<MasterProfileBean> masterList = new ArrayList<MasterProfileBean>();
					AdminService adminService = new AdminService();
					masterList = adminService.getMasterList(displayStart,sortDir,sortCol,displayLength);
					int count = masterList.size();
					String data = "";
					int totalCount = adminService.getMasterCount();
					int size = 0;
					for(MasterProfileBean list: masterList)
					{
						String category = list.getMemberType();
						int books = list.getNumberOfBook();
						int cds = list.getNumberOfCd();
						int magazines = list.getNumberOfMagazine();
						int journals = list.getNumberOfJournal();
						float cost = list.getCost();
						if(size == masterList.size()-1)
						{
							data = data+"{ \"category\": \""+category+"\", \"books\": \""+books+"\", \"cds\": \""+cds+"\", \"magazines\": \""+magazines+"\", \"journals\": \""+journals+"\", \"cost\": \""+cost+"\" } ";
						}
						else
						{
							data = data+"{ \"category\": \""+category+"\", \"books\": \""+books+"\", \"cds\": \""+cds+"\", \"magazines\": \""+magazines+"\", \"journals\": \""+journals+"\", \"cost\": \""+cost+"\" }, ";	
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+totalCount+", \"aaData\": ["+data+"]}" +"");
				}
				else if(search!=null && !search.equalsIgnoreCase(""))
				{
					search = search.toUpperCase();
					ArrayList<MasterProfileBean> masterList = new ArrayList<MasterProfileBean>();
					AdminService adminService = new AdminService();
					masterList = adminService.getMasterListSearch(displayStart,sortDir,sortCol,displayLength,search);
					int count = masterList.size();
					String data = "";
					int totalCount = adminService.getMasterCount();
					int size = 0;
					for(MasterProfileBean list: masterList)
					{
						String category = list.getMemberType();
						int books = list.getNumberOfBook();
						int cds = list.getNumberOfCd();
						int magazines = list.getNumberOfMagazine();
						int journals = list.getNumberOfJournal();
						float cost = list.getCost();
						if(size == masterList.size()-1)
						{
							data = data+"{ \"category\": \""+category+"\", \"books\": \""+books+"\", \"cds\": \""+cds+"\", \"magazines\": \""+magazines+"\", \"journals\": \""+journals+"\", \"cost\": \""+cost+"\" } ";
						}
						else
						{
							data = data+"{ \"category\": \""+category+"\", \"books\": \""+books+"\", \"cds\": \""+cds+"\", \"magazines\": \""+magazines+"\", \"journals\": \""+journals+"\", \"cost\": \""+cost+"\" }, ";	
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+count+", \"aaData\": ["+data+"]}" +"");

				}
			}
			else if("datatable1".equalsIgnoreCase(page))
			{
				System.out.println("secho:"+request.getParameter("sEcho"));
				System.out.println("iDisplayStart:"+request.getParameter("iDisplayStart"));
				System.out.println("sSortDir_0:"+request.getParameter("sSortDir_0"));
				System.out.println("iSortCol_0:"+request.getParameter("iSortCol_0"));
				System.out.println("iDisplayLength:"+request.getParameter("iDisplayLength"));
				System.out.println("i am in datatable::"+request.getParameter("sSearch"));
				System.out.println("iSortingCols:"+request.getParameter("iSortingCols"));

				int displayStart = Integer.parseInt(request.getParameter("iDisplayStart"))+1;
				String sortDir = request.getParameter("sSortDir_0");
				int sortCol = Integer.parseInt(request.getParameter("iSortCol_0"));
				int displayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
				String search =request.getParameter("sSearch");
				String secho =request.getParameter("sEcho");

				PrintWriter out = response.getWriter();
				//"+"\"sEcho\": "+secho+",
				//\"sEcho\": 2,

				if(search==null || search.equalsIgnoreCase(""))
				{

					ArrayList<BookBean> BookList = new ArrayList<BookBean>();
					AdminService adminService = new AdminService();
					BookList = adminService.getBookList(displayStart,sortDir,sortCol,displayLength);
					int count = BookList.size();
					String data = "";
					int totalCount = adminService.getBookCount();
					int size = 0;
					for(BookBean list: BookList)
					{
						int ItemID = list.getItemId();
						String title = list.getTitle();
						String author = list.getAuthor();
						String Sub = list.getSubject();
						int edition = list.getEdition();
						float cost = list.getCost();
						if(size == BookList.size()-1)
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"Author\": \""+author+"\", \"Subject\": \""+Sub+"\", \"Edition\": \""+edition+"\", \"cost\": \""+cost+"\" } ";
						}
						else
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"Author\": \""+author+"\", \"Subject\": \""+Sub+"\", \"Edition\": \""+edition+"\", \"cost\": \""+cost+"\" }, ";
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+totalCount+", \"aaData\": ["+data+"]}" +"");
				}
				else if(search!=null && !search.equalsIgnoreCase(""))
				{
					search = search.toUpperCase();
					ArrayList<BookBean> BookList = new ArrayList<BookBean>();
					AdminService adminService = new AdminService();
					BookList = adminService.getBookListSearch(displayStart,sortDir,sortCol,displayLength,search);
					int count = BookList.size();
					String data = "";
					int totalCount = adminService.getBookCount();
					int size = 0;
					for(BookBean list: BookList)
					{
						int ItemID = list.getItemId();
						String title = list.getTitle();
						String author = list.getAuthor();
						String Sub = list.getSubject();
						int edition = list.getEdition();
						float cost = list.getCost();
						if(size == BookList.size()-1)
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"Author\": \""+author+"\", \"Subject\": \""+Sub+"\", \"Edition\": \""+edition+"\", \"cost\": \""+cost+"\" } ";
						}
						else
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"Author\": \""+author+"\", \"Subject\": \""+Sub+"\", \"Edition\": \""+edition+"\", \"cost\": \""+cost+"\" }, ";
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+count+", \"aaData\": ["+data+"]}" +"");

				}
			}

			else if("datatable2".equalsIgnoreCase(page))
			{
				System.out.println("secho:"+request.getParameter("sEcho"));
				System.out.println("iDisplayStart:"+request.getParameter("iDisplayStart"));
				System.out.println("sSortDir_0:"+request.getParameter("sSortDir_0"));
				System.out.println("iSortCol_0:"+request.getParameter("iSortCol_0"));
				System.out.println("iDisplayLength:"+request.getParameter("iDisplayLength"));
				System.out.println("i am in datatable::"+request.getParameter("sSearch"));
				System.out.println("iSortingCols:"+request.getParameter("iSortingCols"));

				int displayStart = Integer.parseInt(request.getParameter("iDisplayStart"))+1;
				String sortDir = request.getParameter("sSortDir_0");
				int sortCol = Integer.parseInt(request.getParameter("iSortCol_0"));
				int displayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
				String search =request.getParameter("sSearch");
				String secho =request.getParameter("sEcho");

				PrintWriter out = response.getWriter();
				//"+"\"sEcho\": "+secho+",
				//\"sEcho\": 2,

				if(search==null || search.equalsIgnoreCase(""))
				{

					ArrayList<CdBean> CdList = new ArrayList<CdBean>();
					AdminService adminService = new AdminService();
					CdList = adminService.getCdList(displayStart,sortDir,sortCol,displayLength);
					int count = CdList.size();
					String data = "";
					int totalCount = adminService.getCdCount();
					int size = 0;
					for(CdBean list: CdList)
					{
						int ItemID = list.getItemId();
						String title = list.getTitle();
						String Sub = list.getSubject();
						String pub = list.getPublisher();
						float cost = list.getCost();
						if(size == CdList.size()-1)
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"Subject\": \""+Sub+"\", \"Publisher\": \""+pub+"\", \"cost\": \""+cost+"\" } ";
						}
						else
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"Subject\": \""+Sub+"\", \"Publisher\": \""+pub+"\", \"cost\": \""+cost+"\" }, ";
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+totalCount+", \"aaData\": ["+data+"]}" +"");
				}
				else if(search!=null && !search.equalsIgnoreCase(""))
				{
					search = search.toUpperCase();
					ArrayList<CdBean> CdList = new ArrayList<CdBean>();
					AdminService adminService = new AdminService();
					CdList = adminService.getCdListSearch(displayStart,sortDir,sortCol,displayLength,search);
					int count = CdList.size();
					String data = "";
					int totalCount = adminService.getCdCount();
					int size = 0;
					for(CdBean list: CdList)
					{
						int ItemID = list.getItemId();
						String title = list.getTitle();
						String Sub = list.getSubject();
						String pub = list.getPublisher();
						float cost = list.getCost();
						if(size == CdList.size()-1)
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"Subject\": \""+Sub+"\", \"Publisher\": \""+pub+"\", \"cost\": \""+cost+"\" } ";
						}
						else
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"Subject\": \""+Sub+"\", \"Publisher\": \""+pub+"\", \"cost\": \""+cost+"\" }, ";
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+count+", \"aaData\": ["+data+"]}" +"");

				}
			}
			else if("datatable3".equalsIgnoreCase(page))
			{
				System.out.println("secho:"+request.getParameter("sEcho"));
				System.out.println("iDisplayStart:"+request.getParameter("iDisplayStart"));
				System.out.println("sSortDir_0:"+request.getParameter("sSortDir_0"));
				System.out.println("iSortCol_0:"+request.getParameter("iSortCol_0"));
				System.out.println("iDisplayLength:"+request.getParameter("iDisplayLength"));
				System.out.println("i am in datatable::"+request.getParameter("sSearch"));
				System.out.println("iSortingCols:"+request.getParameter("iSortingCols"));

				int displayStart = Integer.parseInt(request.getParameter("iDisplayStart"))+1;
				String sortDir = request.getParameter("sSortDir_0");
				int sortCol = Integer.parseInt(request.getParameter("iSortCol_0"));
				int displayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
				String search =request.getParameter("sSearch");
				String secho =request.getParameter("sEcho");

				PrintWriter out = response.getWriter();
				//"+"\"sEcho\": "+secho+",
				//\"sEcho\": 2,

				if(search==null || search.equalsIgnoreCase(""))
				{

					ArrayList<JournalBean> journalList = new ArrayList<JournalBean>();
					AdminService adminService = new AdminService();
					journalList = adminService.getJournalList(displayStart,sortDir,sortCol,displayLength);
					int count = journalList.size();
					String data = "";
					int totalCount = adminService.getJournalCount();
					int size = 0;
					for(JournalBean list: journalList)
					{
						int ItemID = list.getItemId();
						String title = list.getTitle();
						int volno = list.getVolumeNo();
						String subtype = list.getSubscriptionType();
						float cost = list.getCost();
						if(size == journalList.size()-1)
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"VolumeNo\": \""+volno+"\", \"SubscriptionType\": \""+subtype+"\", \"cost\": \""+cost+"\" } ";
						}
						else
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"VolumeNo\": \""+volno+"\", \"SubscriptionType\": \""+subtype+"\", \"cost\": \""+cost+"\" }, ";
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+totalCount+", \"aaData\": ["+data+"]}" +"");
				}
				else if(search!=null && !search.equalsIgnoreCase(""))
				{
					search = search.toUpperCase();
					ArrayList<JournalBean> journalList = new ArrayList<JournalBean>();
					AdminService adminService = new AdminService();
					journalList = adminService.getJournalListSearch(displayStart,sortDir,sortCol,displayLength,search);
					int count = journalList.size();
					String data = "";
					int totalCount = adminService.getJournalCount();
					int size = 0;
					for(JournalBean list: journalList)
					{
						int ItemID = list.getItemId();
						String title = list.getTitle();
						int volno = list.getVolumeNo();
						String subtype = list.getSubscriptionType();
						float cost = list.getCost();
						if(size == journalList.size()-1)
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"VolumeNo\": \""+volno+"\", \"SubscriptionType\": \""+subtype+"\", \"cost\": \""+cost+"\" } ";
						}
						else
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"VolumeNo\": \""+volno+"\", \"SubscriptionType\": \""+subtype+"\", \"cost\": \""+cost+"\" }, ";
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+count+", \"aaData\": ["+data+"]}" +"");

				}
			}

			else if("datatable4".equalsIgnoreCase(page))
			{
				System.out.println("secho:"+request.getParameter("sEcho"));
				System.out.println("iDisplayStart:"+request.getParameter("iDisplayStart"));
				System.out.println("sSortDir_0:"+request.getParameter("sSortDir_0"));
				System.out.println("iSortCol_0:"+request.getParameter("iSortCol_0"));
				System.out.println("iDisplayLength:"+request.getParameter("iDisplayLength"));
				System.out.println("i am in datatable::"+request.getParameter("sSearch"));
				System.out.println("iSortingCols:"+request.getParameter("iSortingCols"));

				int displayStart = Integer.parseInt(request.getParameter("iDisplayStart"))+1;
				String sortDir = request.getParameter("sSortDir_0");
				int sortCol = Integer.parseInt(request.getParameter("iSortCol_0"));
				int displayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
				String search =request.getParameter("sSearch");
				String secho =request.getParameter("sEcho");

				PrintWriter out = response.getWriter();
				//"+"\"sEcho\": "+secho+",
				//\"sEcho\": 2,

				if(search==null || search.equalsIgnoreCase(""))
				{

					ArrayList<MagazineBean> MagList = new ArrayList<MagazineBean>();
					AdminService adminService = new AdminService();
					MagList = adminService.getMagazineList(displayStart,sortDir,sortCol,displayLength);
					int count = MagList.size();
					String data = "";
					int totalCount = adminService.getMagazineCount();
					int size = 0;
					for(MagazineBean list: MagList)
					{
						int ItemID = list.getItemId();
						String title = list.getTitle();
						int volno = list.getVolumeNo();
						String subtype = list.getSubscriptionType();
						float cost = list.getCost();
						if(size == MagList.size()-1)
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"VolumeNo\": \""+volno+"\", \"SubscriptionType\": \""+subtype+"\", \"cost\": \""+cost+"\" } ";
						}
						else
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"VolumeNo\": \""+volno+"\", \"SubscriptionType\": \""+subtype+"\", \"cost\": \""+cost+"\" }, ";
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+totalCount+", \"aaData\": ["+data+"]}" +"");
				}
				else if(search!=null && !search.equalsIgnoreCase(""))
				{
					search = search.toUpperCase();
					ArrayList<MagazineBean> MagList = new ArrayList<MagazineBean>();
					AdminService adminService = new AdminService();
					MagList = adminService.getMagazineListSearch(displayStart,sortDir,sortCol,displayLength,search);
					int count = MagList.size();
					String data = "";
					int totalCount = adminService.getJournalCount();
					int size = 0;
					for(MagazineBean list: MagList)
					{
						int ItemID = list.getItemId();
						String title = list.getTitle();
						int volno = list.getVolumeNo();
						String subtype = list.getSubscriptionType();
						float cost = list.getCost();
						if(size == MagList.size()-1)
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"VolumeNo\": \""+volno+"\", \"Publisher\": \""+subtype+"\", \"cost\": \""+cost+"\" } ";
						}
						else
						{
							data = data+"{ \"ItemId\": \""+ItemID+"\", \"Title\": \""+title+"\", \"VolumeNo\": \""+volno+"\", \"Publisher\": \""+subtype+"\", \"cost\": \""+cost+"\" } ,";
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+count+", \"aaData\": ["+data+"]}" +"");

				}
			}
			else if("usertable".equalsIgnoreCase(page))
			{
				System.out.println("secho:"+request.getParameter("sEcho"));
				System.out.println("iDisplayStart:"+request.getParameter("iDisplayStart"));
				System.out.println("sSortDir_0:"+request.getParameter("sSortDir_0"));
				System.out.println("iSortCol_0:"+request.getParameter("iSortCol_0"));
				System.out.println("iDisplayLength:"+request.getParameter("iDisplayLength"));
				System.out.println("i am in datatable::"+request.getParameter("sSearch"));
				System.out.println("iSortingCols:"+request.getParameter("iSortingCols"));

				int displayStart = Integer.parseInt(request.getParameter("iDisplayStart"))+1;
				String sortDir = request.getParameter("sSortDir_0");
				int sortCol = Integer.parseInt(request.getParameter("iSortCol_0"));
				int displayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
				String search =request.getParameter("sSearch");
				String secho =request.getParameter("sEcho");

				PrintWriter out = response.getWriter();
				//"+"\"sEcho\": "+secho+",
				//\"sEcho\": 2,

				if(search==null || search.equalsIgnoreCase(""))
				{

					ArrayList<MemberBean> staffList = new ArrayList<MemberBean>();
					AdminService adminService = new AdminService();
					staffList = adminService.getStaffList(displayStart,sortDir,sortCol,displayLength);
					int count = staffList.size();
					String data = "";
					int totalCount = adminService.getStaffCount();
					int size = 0;
					for(MemberBean list: staffList)
					{
						int userId1 = list.getUserId();
						String fname = list.getFirstName();
						String Id = list.getIdentityId();

						if(size == staffList.size()-1)
						{
							data = data+"{ \"UserId\": \""+userId1+"\", \"FirstName\": \""+fname+"\", \"IdentityId\": \""+Id+"\"} ";
						}
						else
						{
							data = data+"{ \"UserId\": \""+userId1+"\", \"FirstName\": \""+fname+"\", \"IdentityId\": \""+Id+"\" }, ";
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+totalCount+", \"aaData\": ["+data+"]}" +"");
				}
				else if(search!=null && !search.equalsIgnoreCase(""))
				{
					search = search.toUpperCase();
					ArrayList<MemberBean> staffList = new ArrayList<MemberBean>();
					AdminService adminService = new AdminService();
					staffList = adminService.getStaffListSearch(displayStart,sortDir,sortCol,displayLength,search);
					int count = staffList.size();
					String data = "";
					int totalCount = adminService.getStaffCount();
					int size = 0;
					for(MemberBean list: staffList)
					{

						int userId1 = list.getUserId();
						String fname = list.getFirstName();
						String Id = list.getIdentityId();

						if(size == staffList.size()-1)
						{
							data = data+"{ \"UserId\": \""+userId1+"\", \"FirstName\": \""+fname+"\", \"IdentityId\": \""+Id+"\" } ";
						}
						else
						{
							data = data+"{ \"UserId\": \""+userId1+"\", \"FirstName\": \""+fname+"\", \"IdentityId\": \""+Id+"\" }, ";
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+count+", \"aaData\": ["+data+"]}" +"");

				}
			}
			else if("membertable".equalsIgnoreCase(page))
			{
				System.out.println("secho:"+request.getParameter("sEcho"));
				System.out.println("iDisplayStart:"+request.getParameter("iDisplayStart"));
				System.out.println("sSortDir_0:"+request.getParameter("sSortDir_0"));
				System.out.println("iSortCol_0:"+request.getParameter("iSortCol_0"));
				System.out.println("iDisplayLength:"+request.getParameter("iDisplayLength"));
				System.out.println("i am in datatable::"+request.getParameter("sSearch"));
				System.out.println("iSortingCols:"+request.getParameter("iSortingCols"));

				int displayStart = Integer.parseInt(request.getParameter("iDisplayStart"))+1;
				String sortDir = request.getParameter("sSortDir_0");
				int sortCol = Integer.parseInt(request.getParameter("iSortCol_0"));
				int displayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
				String search =request.getParameter("sSearch");
				String secho =request.getParameter("sEcho");

				PrintWriter out = response.getWriter();
				//"+"\"sEcho\": "+secho+",
				//\"sEcho\": 2,

				if(search==null || search.equalsIgnoreCase(""))
				{

					ArrayList<MemberBean> memList = new ArrayList<MemberBean>();
					AdminService adminService = new AdminService();
					memList = adminService.getMemberList(displayStart,sortDir,sortCol,displayLength);
					int count = memList.size();
					String data = "";
					int totalCount = adminService.getMemberCount();
					int size = 0;
					for(MemberBean list: memList)
					{
						int userId1 = list.getUserId();
						String fname = list.getFirstName();
						String Id = list.getIdentityId();
						long Contact = list.getContact();
						String email = list.getEmail();

						if(size == memList.size()-1)
						{
							data = data+"{ \"UserId\": \""+userId1+"\", \"FirstName\": \""+fname+"\", \"IdentityId\": \""+Id+"\", \"ContactNo\": \""+Contact+"\", \"Email\": \""+email+"\"} ";
						}
						else
						{
							data = data+"{ \"UserId\": \""+userId1+"\", \"FirstName\": \""+fname+"\", \"IdentityId\": \""+Id+"\",\"ContactNo\": \""+Contact+"\", \"Email\": \""+email+"\"}, ";
						}
						size = size + 1;
					}
					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+totalCount+", \"aaData\": ["+data+"]}" +"");
				}
				else if(search!=null && !search.equalsIgnoreCase(""))
				{
					search = search.toUpperCase();
					ArrayList<MemberBean> memList = new ArrayList<MemberBean>();
					AdminService adminService = new AdminService();
					memList = adminService.getMemberListSearch(displayStart,sortDir,sortCol,displayLength,search);
					int count = memList.size();
					String data = "";
					int totalCount = adminService.getMemberCount();
					int size = 0;
					for(MemberBean list: memList)
					{
						int userId1 = list.getUserId();
						String fname = list.getFirstName();
						String Id = list.getIdentityId();
						long Contact = list.getContact();
						String email = list.getEmail();

						if(size == memList.size()-1)
						{
							data = data+"{ \"UserId\": \""+userId1+"\", \"FirstName\": \""+fname+"\", \"IdentityId\": \""+Id+"\",\"ContactNo\": \""+Contact+"\", \"Email\": \""+email+"\"} ";
						}
						else
						{
							data = data+"{ \"UserId\": \""+userId1+"\", \"FirstName\": \""+fname+"\", \"IdentityId\": \""+Id+"\",\"ContactNo\": \""+Contact+"\", \"Email\": \""+email+"\"}, ";
						}
						size = size + 1;
					}

					System.out.println(data);
					out.print(
							"{\"iTotalRecords\":"+totalCount+", \"iTotalDisplayRecords\": "+count+", \"aaData\": ["+data+"]}" +"");
				}
			}
			else if("changelog".equalsIgnoreCase(page))
			{
				AdminService adminService = new AdminService();
				ArrayList<String> changeLog = new ArrayList<String>();
				changeLog = adminService.getChangeLog();
				request.setAttribute("changes", changeLog);
				request.getRequestDispatcher("html/AdminHome.jsp").forward(request,response);			
			}
		}
		catch(Exception e)
		{
			response.sendRedirect("GuestHome.html");	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		response.setContentType("text/html");
		System.out.println(page);		
		int userId = 0;
		String role ="";
		HttpSession session = request.getSession(false);
		try
		{
			userId = (Integer) session.getAttribute("userId");
			role = (String)session.getAttribute("role");
			if(!(role.equalsIgnoreCase("Admin")))
			{
				request.getRequestDispatcher("GuestHome.jsp").forward(request,response);
			}
			if("AddAdmin".equalsIgnoreCase(page))
			{
				int result = 0;			
				int userId1 = Integer.parseInt(request.getParameter("userId"));
				String password = request.getParameter("password");
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				long contactNo = Long.parseLong(request.getParameter("contactNo"));
				String identityId = request.getParameter("identityId");
				String email = request.getParameter("email");
				String address = request.getParameter("address");
				UserBean userBean = new UserBean(userId1, password, firstName, lastName, email, contactNo, address, "Admin", identityId);
				System.out.println(userBean.getIdentityId());
				System.out.println(userBean.getFirstName());
				AdminService adminService = new AdminService();
				result = adminService.addAdmin(userBean);
				if(result==1)
				{
					String confirm = "Admin Has been successfully added!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/AddAdminConfirm.jsp").forward(request,response);
				}
				else if(result==3)
				{
					String confirm = "UserId has already been taken!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/AddAdminConfirm.jsp").forward(request,response);
				}
				else if(result==2)
				{			
					String confirm = "A User with the same Identity Id already exsists!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/AddAdminConfirm.jsp").forward(request,response);
				}
			}

			if("AddLibrarian".equalsIgnoreCase(page))
			{
				int result = 0;			
				int userId1 = Integer.parseInt(request.getParameter("userId"));
				String password = request.getParameter("password");
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				long contactNo = Long.parseLong(request.getParameter("contactNo"));
				String identityId = request.getParameter("identityId");
				String email = request.getParameter("email");
				String address = request.getParameter("address");
				UserBean userBean = new UserBean(userId1, password, firstName, lastName, email, contactNo, address, "Librarian", identityId);
				AdminService adminService = new AdminService();
				result = adminService.addLibrarian(userBean);
				if(result==1)
				{
					String confirm = "Librarian Has been successfully added!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/AddLibrarianConfirm.jsp").forward(request,response);
				}
				else if(result==3)
				{
					String confirm = "UserId has already been taken!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/AddLibrarianConfirm.jsp").forward(request,response);
				}
				else if(result==2)
				{			
					String confirm = "A User with the same Identity Id already exsists!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/AddLibrarianConfirm.jsp").forward(request,response);
				}
			}

			if("AddBookAdmin".equalsIgnoreCase(page))
			{
				int result = 0;
				String title = request.getParameter("Title");
				int bookId=0;
				//int bookId = Integer.parseInt(request.getParameter("BookId"));
				String author = request.getParameter("Author");
				long isbn = Long.parseLong(request.getParameter("Isbn"));
				String subject = request.getParameter("Subject");
				int edition = Integer.parseInt(request.getParameter("Edition"));
				String editor = request.getParameter("Editor");
				String placeOfPublication = request.getParameter("PlaceOfPublication");
				String publisher = request.getParameter("Publisher");
				int yearOfPublication = Integer.parseInt(request.getParameter("PublicationYear"));
				int cost = Integer.parseInt(request.getParameter("Cost"));
				BookBean bookBean = new BookBean(bookId,title,cost,"AVAILABLE",subject,edition,author,isbn,placeOfPublication,publisher,yearOfPublication,editor);
				AdminService adminService = new AdminService();
				result = adminService.addBook(bookBean);
				//String confirm = "Book Has been successfully added!";
				//System.out.println(confirm);
				//request.setAttribute("confirm", confirm);
				request.setAttribute("ItemID", result);
				request.setAttribute("item", "book");
				request.getRequestDispatcher("jsp/rackHomeAdd.jsp").forward(request,response);
				/*}
				else if(result==2)
				{			
					String confirm = "A Book with the same Id already exsists!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/AddBookAdminConfirm.jsp").forward(request,response);
				}*/
			}

			else if("AddCdAdmin".equalsIgnoreCase(page))
			{
				int result = 0;
				String title = request.getParameter("Title");
				int cdId=0;
				//int cdId = Integer.parseInt(request.getParameter("CdId"));
				String subject = request.getParameter("Subject");
				String placeOfPublication = request.getParameter("PlaceOfPublication");
				String publisher = request.getParameter("Publisher");
				int yearOfPublication = Integer.parseInt(request.getParameter("PublicationYear"));
				int cost = Integer.parseInt(request.getParameter("Cost"));
				CdBean cdBean = new CdBean(cdId,title,cost,"AVAILABLE",subject,placeOfPublication,publisher,yearOfPublication);
				AdminService adminService = new AdminService();
				result = adminService.addCd(cdBean);
				request.setAttribute("ItemID", result);
				request.setAttribute("item", "CD");
				request.getRequestDispatcher("jsp/rackHomeAdd.jsp").forward(request,response);
			}

			else if("AddJournalAdmin".equalsIgnoreCase(page))
			{
				int result = 0;
				String title = request.getParameter("Title");
				int volumeNo = Integer.parseInt(request.getParameter("VolumeNo"));
				int journalId=0;
				long isbn = Long.parseLong(request.getParameter("Isbn"));
				String subscriptionType = request.getParameter("SubscriptionType");
				String placeOfPublication = request.getParameter("PlaceOfPublication");
				int yearOfPublication = Integer.parseInt(request.getParameter("PublicationYear"));
				int cost = Integer.parseInt(request.getParameter("Cost"));
				JournalBean journalBean = new JournalBean(journalId,title,cost,"AVAILABLE",placeOfPublication,yearOfPublication,volumeNo,isbn,subscriptionType);
				AdminService adminService = new AdminService();
				result = adminService.addJournal(journalBean);
				request.setAttribute("ItemID", result);
				request.setAttribute("item", "Magazine");
				request.getRequestDispatcher("jsp/rackHomeAdd.jsp").forward(request,response);
			}

			else if("AddMagazineAdmin".equalsIgnoreCase(page))
			{
				int result = 0;
				String title = request.getParameter("Title");
				int volumeNo = Integer.parseInt(request.getParameter("VolumeNo"));
				int magazineId=0;
				long isbn = Long.parseLong(request.getParameter("Isbn"));
				String subscriptionType = request.getParameter("SubscriptionType");
				String placeOfPublication = request.getParameter("PlaceOfPublication");
				int yearOfPublication = Integer.parseInt(request.getParameter("PublicationYear"));
				int cost = Integer.parseInt(request.getParameter("Cost"));
				MagazineBean magazineBean = new MagazineBean(magazineId,title,cost,"AVAILABLE",placeOfPublication,yearOfPublication,volumeNo,isbn,subscriptionType);
				AdminService adminService = new AdminService();
				result = adminService.addMagazine(magazineBean);
				request.setAttribute("ItemID", result);
				request.setAttribute("item", "Journal");
				request.getRequestDispatcher("jsp/rackHomeAdd.jsp").forward(request,response);
			}

			else if("DeleteUser".equalsIgnoreCase(page))
			{	
				String role1 = request.getParameter("role");
				int userId1 = Integer.parseInt(request.getParameter("userId"));			
				AdminService adminService = new AdminService();
				if(role1.equalsIgnoreCase("Librarian"))
				{
					int check = 0;
					check = adminService.deleteLibrarian(userId1);
					if(check==1)
					{
						String confirm = "The Librarian has been deleted!";
						System.out.println(confirm);
						request.setAttribute("confirm", confirm);
						request.getRequestDispatcher("html/AddAdminConfirm.jsp").forward(request,response);			
					}
				}
				else if(role1.equalsIgnoreCase("Member"))
				{
					String confirm = "Site is under Construction! The Member cannot be deleted at the moment!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/AddAdminConfirm.jsp").forward(request,response);						
				}
				else if(role1.equalsIgnoreCase("Admin"))
				{
					String confirm = "Admin cannot be deleted!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/AddAdminConfirm.jsp").forward(request,response);						
				}
			}

			else if("AddMasterProfile".equalsIgnoreCase(page))
			{
				int result = 0;
				String memberType = request.getParameter("Category");
				int numberOfBook =Integer.parseInt(request.getParameter("NumberOfBook"));
				int numberOfCd= Integer.parseInt(request.getParameter("NumberOfCd"));
				int numberOfMagazine=Integer.parseInt(request.getParameter("NumberOfMagazine"));
				int numberOfJournal=Integer.parseInt(request.getParameter("NumberOfJournal"));
				float cost=Float.parseFloat(request.getParameter("Cost"));
				System.out.println(numberOfBook);
				System.out.println(numberOfCd);
				System.out.println(numberOfMagazine);
				System.out.println(numberOfJournal);
				System.out.println(cost);
				MasterProfileBean masterProfileBean = new MasterProfileBean(memberType,numberOfBook,numberOfCd,numberOfMagazine,numberOfJournal,cost);
				AdminService adminService = new AdminService();
				result = adminService.addMasterProfile(masterProfileBean);
				System.out.println(result);
				if(result == 1)
				{
					ArrayList <MasterProfileBean> listOne=new ArrayList<MasterProfileBean>();
					AdminService adservice= new AdminService();
					MasterProfileBean mp=new MasterProfileBean();
					listOne=adservice.retrieveMasterProfile(mp);
					request.setAttribute("xy",listOne );
					request.getRequestDispatcher("html/viewListOne.jsp").forward(request,response);
				}
			}
			else if("DeleteItem".equalsIgnoreCase(page))
			{
				int check = 0;
				String title = request.getParameter("ItemType");
				int itemId = Integer.parseInt(request.getParameter("ItemId"));			
				if(title.equalsIgnoreCase("book"))
				{
					AdminService adminService = new AdminService();
					check = adminService.deleteBook(itemId);
					if(check==1)
					{
						String confirm = "The Book has been deleted!";
						System.out.println(confirm);
						RackService rackService = new RackService();
						rackService.deleteItem(itemId,response);
						request.setAttribute("confirm", confirm);
						request.getRequestDispatcher("html/UpdatedBookSearch.jsp").forward(request,response);			
					}
				}
				else if(title.equalsIgnoreCase("cd"))
				{
					AdminService adminService = new AdminService();
					check = adminService.deleteCd(itemId);
					if(check==1)
					{
						String confirm = "The Cd has been deleted!";
						System.out.println(confirm);
						RackService rackService = new RackService();
						rackService.deleteItem(itemId,response);
						request.setAttribute("confirm", confirm);
						request.getRequestDispatcher("html/UpdatedCdSearch.jsp").forward(request,response);			
					}
				}
				else if(title.equalsIgnoreCase("magazine"))
				{
					AdminService adminService = new AdminService();
					check = adminService.deleteMagazine(itemId);
					if(check==1)
					{
						String confirm = "The Magazine has been deleted!";
						System.out.println(confirm);
						RackService rackService = new RackService();
						rackService.deleteItem(itemId,response);
						request.setAttribute("confirm", confirm);
						request.getRequestDispatcher("html/UpdatedMagazineSearch.jsp").forward(request,response);			
					}
				}
				else if(title.equalsIgnoreCase("journal"))
				{
					AdminService adminService = new AdminService();
					check = adminService.deleteJournal(itemId);
					if(check==1)
					{
						String confirm = "The Journal has been deleted!";
						System.out.println(confirm);
						RackService rackService = new RackService();
						rackService.deleteItem(itemId,response);
						request.setAttribute("confirm", confirm);
						request.getRequestDispatcher("html/UpdatedJournalSearch.jsp").forward(request,response);			
					}
				}
			}
			else if("DeleteStaff".equalsIgnoreCase(page))
			{
				int check = 0;
				String title = request.getParameter("User");
				int userId1 = Integer.parseInt(request.getParameter("UserId"));			
				if(title.equalsIgnoreCase("Staff"))
				{
					System.out.println("title");
					AdminService adminService = new AdminService();
					check = adminService.deleteStaff(userId);
					if(check==1)
					{
						String confirm = "The Staff has been deleted!";
						System.out.println(confirm);
						request.setAttribute("confirm", confirm);
						request.getRequestDispatcher("html/DeleteStaff1.jsp").forward(request,response);			
					}
				}
			}

			else if("ChekAvailablityForMasterProfile".equalsIgnoreCase(page))
			{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				boolean categoryAvailable = false;
				String memberType = request.getParameter("Category");
				String numberOfBook=request.getParameter("NumberOfBook");
				int numberOfBookInt=0;
				String numberOfCd=request.getParameter("NumberOfCd");
				int numberOfCdInt=0;
				String numberOfMagazine=request.getParameter("NumberOfMagazine");
				int numberOfMagazineInt=0;
				String numberOfJournal=request.getParameter("NumberOfJournal");
				int numberOfJournalInt=0;
				String cost=request.getParameter("Cost");
				float costFloat=0f;
				if(numberOfBook!=null)
				{
					numberOfBookInt =Integer.parseInt(numberOfBook);
				}
				if(numberOfCd!=null)
				{
					numberOfCdInt= Integer.parseInt(numberOfCd);
				}
				if(numberOfMagazine!=null)
				{
					numberOfMagazineInt=Integer.parseInt(numberOfMagazine);
				}
				if(numberOfJournal!=null)
				{
					numberOfJournalInt=Integer.parseInt(numberOfJournal);
				}
				if(cost!=null)
				{
					costFloat=Float.parseFloat(cost);
				}

				MasterProfileBean masterProfileBean = new MasterProfileBean(memberType,numberOfBookInt,numberOfCdInt,numberOfMagazineInt,numberOfJournalInt,costFloat);
				AdminService adminService = new AdminService();
				categoryAvailable = adminService.checkAvailabilityOfCategory(masterProfileBean);
				if(categoryAvailable)
				{
					out.println("Category Available");								
				}
				else
				{
					out.println("Category already present,please enter different category");
				}	
			}
			else if("CheckAvailablityForAdminIdentityId".equalsIgnoreCase(page))
			{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				boolean userIdAvailable = false;
				String identityId = request.getParameter("identityId");	
				AdminService adminService = new AdminService();
				System.out.println(identityId);
				userIdAvailable = adminService.checkAvailabilityOfAdminIdentityId(identityId);
				if(userIdAvailable)
				{
					out.println("Identity ID Available");								
				}
				else
				{
					out.println("A User with the same Identity ID is already present in the system");
				}
			}
			else if("CheckAvailablityForAdminUserId".equalsIgnoreCase(page))
			{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				boolean userIdAvailable = false;
				String userId1 = request.getParameter("userId");
				int userIdInt=0;
				if(userId1!=null)
				{
					userIdInt = Integer.parseInt(userId1);
				}		
				AdminService adminService = new AdminService();
				userIdAvailable = adminService.checkAvailabilityOfAdminUserId(userIdInt);
				if(userIdAvailable)
				{
					out.println("User ID Available");								
				}
				else
				{
					out.println("User ID already present. Please enter a different User ID");
				}	
			}
			else if("CheckItemAvailablity".equalsIgnoreCase(page))
			{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				boolean userIdAvailable = false;
				int cost = Integer.parseInt(request.getParameter("Cost"));
				int book = Integer.parseInt(request.getParameter("Book"));
				int cd = Integer.parseInt(request.getParameter("Cd"));
				int magazine = Integer.parseInt(request.getParameter("Magazine"));
				int journal = Integer.parseInt(request.getParameter("Journal"));
				int totalCount = book+cd+magazine+journal;
				AdminService adminService = new AdminService();
				int count = adminService.getItemCount(cost);
				if(count!=0)
				{
					if(totalCount>count)
					{
						out.println("Number of Items Exceeded! Enter less than "+count+" items");
					}
					else if(totalCount<count)
					{
						out.println("Enter More Items (Expected Number of Items : "+count+")");
					}
					else
					{
						out.println("Number of Items Accepted");
					}
				}
				else
				{
					int maxCount = adminService.getMaxCount(cost);
					int minCount = adminService.getMinCount(cost);
					if(totalCount>maxCount)
					{
						out.println("Number of Items Exceeded! Enter less than "+maxCount+" items");
					}
					else if(totalCount<minCount)
					{
						out.println("Enter More Items (Min Limit : "+minCount+")");
					}
					else
					{
						out.println("Number of Items Accepted");
					}
				}
			}
		}
		catch(Exception e)
		{
			response.sendRedirect("GuestHome.html");
		}
	}
}