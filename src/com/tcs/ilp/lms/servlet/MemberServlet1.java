package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
import com.tcs.ilp.lms.service.AdminService;
import com.tcs.ilp.lms.service.MemberService;

/**
 * Servlet implementation class MemberServlet1
 */
public class MemberServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberServlet1() {
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
		HttpSession session = request.getSession(false);
		try
		{
			userId = (Integer)session.getAttribute("userId");
			String menu = request.getParameter("Button");
			String button = request.getParameter("Submit");
			if("membertable".equalsIgnoreCase(page))
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
					MemberService memberService =new MemberService();
					memList = memberService.getMemberList(displayStart,sortDir,sortCol,displayLength);
					int count = memList.size();
					String data = "";
					int totalCount = memberService.getMemberCount();
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
					MemberService memberService =new MemberService();
					memList = memberService.getMemberListSearch(displayStart,sortDir,sortCol,displayLength,search);
					int count = memList.size();
					String data = "";
					int totalCount = memberService.getMemberCount();
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
					MemberService memberService =new MemberService();
					BookList = memberService.getBookList(displayStart,sortDir,sortCol,displayLength);
					int count = BookList.size();
					String data = "";
					int totalCount = memberService.getBookCount();
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
					MemberService memberService =new MemberService();
					BookList = memberService.getBookListSearch(displayStart,sortDir,sortCol,displayLength,search);
					int count = BookList.size();
					String data = "";
					int totalCount = memberService.getBookCount();
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
					MemberService memberService =new MemberService();
					CdList = memberService.getCdList(displayStart,sortDir,sortCol,displayLength);
					int count = CdList.size();
					String data = "";
					int totalCount = memberService.getCdCount();
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
					MemberService memberService =new MemberService();
					CdList = memberService.getCdListSearch(displayStart,sortDir,sortCol,displayLength,search);
					int count = CdList.size();
					String data = "";
					int totalCount = memberService.getCdCount();
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
					MemberService memberService =new MemberService();
					journalList = memberService.getJournalList(displayStart,sortDir,sortCol,displayLength);
					int count = journalList.size();
					String data = "";
					int totalCount = memberService.getJournalCount();
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
					MemberService memberService =new MemberService();
					journalList = memberService.getJournalListSearch(displayStart,sortDir,sortCol,displayLength,search);
					int count = journalList.size();
					String data = "";
					int totalCount = memberService.getJournalCount();
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
					MemberService memberService =new MemberService();
					MagList = memberService.getMagazineList(displayStart,sortDir,sortCol,displayLength);
					int count = MagList.size();
					String data = "";
					int totalCount = memberService.getMagazineCount();
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
					MemberService memberService =new MemberService();
					MagList = memberService.getMagazineListSearch(displayStart,sortDir,sortCol,displayLength,search);
					int count = MagList.size();
					String data = "";
					int totalCount = memberService.getJournalCount();
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
		//response.setContentType("text/html");
		System.out.println(page);
		int result = 0;
		if("SignUp".equalsIgnoreCase(page))
		{
			int userId = Integer.parseInt(request.getParameter("userId"));
			String password = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			long contactNo = Long.parseLong(request.getParameter("contactNo"));
			String identityId = request.getParameter("identityId");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String memberType = request.getParameter("memberType").toUpperCase();
			System.out.println(userId);
			System.out.println(firstName);
			System.out.println(lastName);
			System.out.println(contactNo);
			System.out.println(identityId);
			System.out.println(email);
			System.out.println(address);
			System.out.println(memberType);
			MemberBean memberBean = new MemberBean(userId, password, firstName, lastName, email, contactNo, address, "Member", identityId, memberType);				
			request.setAttribute("member", memberBean);
			request.getRequestDispatcher("html/RegistrationConfirm.jsp").forward(request,response);
		}
		else if("RegistrationConfirmation".equals(page))
		{
			int userId = Integer.parseInt(request.getParameter("userId"));
			String password = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			long contactNo = Long.parseLong(request.getParameter("contactNo"));
			String identityId = request.getParameter("IdentityId");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String memberType = request.getParameter("memberType").toUpperCase();
			System.out.println(userId);
			System.out.println(firstName);
			System.out.println(lastName);
			System.out.println(contactNo);
			System.out.println(identityId);
			System.out.println(email);
			System.out.println(address);
			System.out.println(memberType);
			MemberBean memberBean = new MemberBean(userId, password, firstName, lastName, email, contactNo, address, "Member", identityId, memberType);				
			MemberService memberService = new MemberService();
			result = memberService.registration(memberBean);
			System.out.println(result+"AddMember");
			if(result == 1)
			{
				response.sendRedirect("GuestHome.html");
			}
			else if(result == 3)
			{
				String confirm = "User with the same User ID exsists!";
				System.out.println(confirm);
				request.setAttribute("confirm", confirm);
				request.getRequestDispatcher("html/RegisterConfirm.jsp").forward(request,response);	
			}
			else if(result == 2)
			{
				String confirm = "User with the same Identity ID exsists!";
				System.out.println(confirm);
				request.setAttribute("confirm", confirm);
				request.getRequestDispatcher("html/RegisterConfirm.jsp").forward(request,response);	
			}
		}
		else if("CheckAvailablityForUserId".equalsIgnoreCase(page))
		{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			boolean userIdAvailable = false;
			String userId = request.getParameter("userId");
			int userIdInt=0;
			if(userId!=null)
			{
				userIdInt =Integer.parseInt(userId);
			}		
			MemberService memberService = new MemberService();
			userIdAvailable = memberService.checkAvailabilityOfUserId(userIdInt);
			if(userIdAvailable)
			{
				out.println("User ID Available");								
			}
			else
			{
				out.println("User ID already present. Please enter a different User ID");
			}	
		}

		else if("CheckAvailablityForIdentityId".equalsIgnoreCase(page))
		{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			boolean userIdAvailable = false;
			String identityId = request.getParameter("identityId");	
			MemberService memberService = new MemberService();
			userIdAvailable = memberService.checkAvailabilityOfIdentityId(identityId);
			if(userIdAvailable)
			{
				out.println("Identity ID Available");								
			}
			else
			{
				out.println("A User with the same Identity ID is already present in the system");
			}	
		}
		
	}
}
