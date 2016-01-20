

	package com.tcs.ilp.lms.servlet;

	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.ArrayList;
	import java.util.List;

	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	import com.tcs.ilp.lms.bean.BookBean1;
import com.tcs.ilp.lms.bean.JournalBean1;
	import com.tcs.ilp.lms.dao.BookDao;


	import com.tcs.ilp.lms.service.LibrarianService;
import com.tcs.ilp.lms.service.MemberService;

	/**
	 * Servlet implementation class LostServlet
	 */
	public class LostJournalServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public LostJournalServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doPost(request,response);
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			//PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			MemberService mem=new MemberService();
			LibrarianService lib=new LibrarianService();
			
			System.out.println(" in do post");
			List<JournalBean1> journalList=new ArrayList<JournalBean1>();
		//	BookBean f=new BookBean();
			String req=request.getParameter("req");
			
			int userId = 0;
			HttpSession session = request.getSession(false);
			try
			{
				userId = (Integer) session.getAttribute("userId");
			 //from report to view Issued book list jsp
			if("register".equalsIgnoreCase(req))
			{
				
				try {
				journalList = mem.viewJournalList(userId);
				System.out.println("sizeof journal List"+journalList.size());
				request.setAttribute("viewIssuedJournalList", journalList); // has taken a variable viewissuedBooklist
				 RequestDispatcher rd = request.getRequestDispatcher("jsp/viewIssuedJournalList.jsp");
				 rd.forward(request, response);


			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			else if("submitReport".equalsIgnoreCase(req))  //from viewIssuedBookList.jsp to submitReport.jsp
			{
				
				try {
				
				  journalList=mem.viewJournalList(userId);
				  String [] id=request.getParameterValues("radio2");
				  ArrayList<Integer> idForCost=new ArrayList<Integer>();
				  for(int i=0;i<id.length;i++)
					  idForCost.add(Integer.parseInt(id[i]));
				  
				//  boolean isBoolean=false;
				if (id!=null){	
				 request.setAttribute("submitReport", journalList); // has taken a variable submitReport
				 request.setAttribute("submitId", idForCost);
				 RequestDispatcher rd = request.getRequestDispatcher("jsp/submitReportJournal.jsp");
				 rd.forward(request, response);
				 for(Integer idCost:idForCost)
						mem.updateStatusJournal(idCost);
					System.out.println("status changed");
				}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			else if("view".equalsIgnoreCase(req))  //from homepage to viewLost_Member.jsp
			{
				
				try 
				{
					journalList=mem.viewLostJournal(userId);
					request.setAttribute("viewLostjournalList", journalList); // has taken a variable viewLostjournalList
					RequestDispatcher rd = request.getRequestDispatcher("jsp/viewLost_Member.jsp");
					rd.forward(request, response);
				} 
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
			catch(Exception e)
			{
				response.sendRedirect("GuestHome.html");
			}
	}				
}	

			
			
			
		

