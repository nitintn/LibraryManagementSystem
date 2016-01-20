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
import com.tcs.ilp.lms.bean.MemberBean;
import com.tcs.ilp.lms.service.AdminService;
import com.tcs.ilp.lms.service.LibrarianService;

/**
 * Servlet implementation class LibrarianServlet
 */
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianServlet() {
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
			if("SearchItem".equalsIgnoreCase(page))
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
					LibrarianService memberService =new LibrarianService();
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
					LibrarianService memberService =new LibrarianService();
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
					LibrarianService memberService =new LibrarianService();
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
					LibrarianService memberService =new LibrarianService();
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
					LibrarianService memberService =new LibrarianService();
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
					LibrarianService memberService =new LibrarianService();
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
					LibrarianService memberService =new LibrarianService();
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
					LibrarianService memberService =new LibrarianService();
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
					LibrarianService memberService =new LibrarianService();
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
					LibrarianService memberService =new LibrarianService();
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
			else if("changelog".equalsIgnoreCase(page))
			{
				LibrarianService librarianService = new LibrarianService();
				ArrayList<String> changeLog = new ArrayList<String>();
				changeLog = librarianService.getChangeLog();
				request.setAttribute("changes", changeLog);
				request.getRequestDispatcher("html/LibrarianHome.jsp").forward(request,response);			
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
		HttpSession session = request.getSession(false);
		try
		{
			userId = (Integer) session.getAttribute("userId");		
			if("AddBookLibrarian".equalsIgnoreCase(page))
			{
				int result = 0;
				String title = request.getParameter("Title");
				int bookId = Integer.parseInt(request.getParameter("BookId"));
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
				LibrarianService librarianService = new LibrarianService();
				result = librarianService.addBook(bookBean);
				if(result==1)
				{
					String confirm = "Book Has been successfully added!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/LibrarianConfirm.jsp").forward(request,response);
				}
				else if(result==2)
				{			
					String confirm = "A Book with the same Id already exsists!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/LibrarianConfirm.jsp").forward(request,response);
				}
			}
		
			else if("AddCdLibrarian".equalsIgnoreCase(page))
			{
				int result = 0;
				System.out.println(userId);
				String title = request.getParameter("Title");
				int cdId = Integer.parseInt(request.getParameter("CdId"));
				String subject = request.getParameter("Subject");
				String placeOfPublication = request.getParameter("PlaceOfPublication");
				String publisher = request.getParameter("Publisher");
				int yearOfPublication = Integer.parseInt(request.getParameter("PublicationYear"));
				int cost = Integer.parseInt(request.getParameter("Cost"));
				CdBean cdBean = new CdBean(cdId,title,cost,"AVAILABLE",subject,placeOfPublication,publisher,yearOfPublication);
				LibrarianService librarianService = new LibrarianService();
				result = librarianService.addCd(cdBean);
				if(result==1)
				{
					String confirm = "Cd Has been successfully added!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/LibrarianConfirm.jsp").forward(request,response);
				}	
				else if(result==2)
				{			
					String confirm = "A Cd with the same Id already exsists!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/LibrarianConfirm.jsp").forward(request,response);
				}	
			}
		
			else if("AddJournalLibrian".equalsIgnoreCase(page))
			{
				int result = 0;
				System.out.println(userId);
				String title = request.getParameter("Title");
				int volumeNo = Integer.parseInt(request.getParameter("VolumeNo"));
				int journalId = Integer.parseInt(request.getParameter("JournalId"));
				long isbn = Long.parseLong(request.getParameter("Isbn"));
				String subscriptionType = request.getParameter("SubscriptionType");
				String placeOfPublication = request.getParameter("PlaceOfPublication");
				int yearOfPublication = Integer.parseInt(request.getParameter("PublicationYear"));
				int cost = Integer.parseInt(request.getParameter("Cost"));
				JournalBean journalBean = new JournalBean(journalId,title,cost,"AVAILABLE",placeOfPublication,yearOfPublication,volumeNo,isbn,subscriptionType);
				LibrarianService librarianService = new LibrarianService();
				result = librarianService.addJournal(journalBean);
				if(result==1)
				{
					String confirm = "Journal Has been successfully added!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/LibrarianConfirm.jsp").forward(request,response);
				}	
				else if(result==2)
				{			
					String confirm = "A Journal with the same Id already exsists!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/LibrarianConfirm.jsp").forward(request,response);
				}	
			}
		
			else if("AddMagazineLibrarian".equalsIgnoreCase(page))
			{
				int result = 0;
				System.out.println(userId);
				String title = request.getParameter("Title");
				int volumeNo = Integer.parseInt(request.getParameter("VolumeNo"));
				int magazineId = Integer.parseInt(request.getParameter("MagazineId"));
				long isbn = Long.parseLong(request.getParameter("Isbn"));
				String subscriptionType = request.getParameter("SubscriptionType");
				String placeOfPublication = request.getParameter("PlaceOfPublication");
				int yearOfPublication = Integer.parseInt(request.getParameter("PublicationYear"));
				int cost = Integer.parseInt(request.getParameter("Cost"));
				MagazineBean magazineBean = new MagazineBean(magazineId,title,cost,"AVAILABLE",placeOfPublication,yearOfPublication,volumeNo,isbn,subscriptionType);
				LibrarianService librarianService = new LibrarianService();
				result = librarianService.addMagazine(magazineBean);
				if(result==1)
				{
					String confirm = "Magazine Has been successfully added!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/LibrarianConfirm.jsp").forward(request,response);
				}	
				else if(result==2)
				{			
					String confirm = "A Magazine with the same Id already exsists!";
					System.out.println(confirm);
					request.setAttribute("confirm", confirm);
					request.getRequestDispatcher("html/LibrarianConfirm.jsp").forward(request,response);
				}	
			}
		}
		catch(Exception e)
		{
			response.sendRedirect("GuestHome.html");
		}
	}
}
