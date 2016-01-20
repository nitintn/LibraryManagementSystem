package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.ilp.lms.bean.BookBean1;
import com.tcs.ilp.lms.bean.CdBean1;
import com.tcs.ilp.lms.bean.JournalBean1;
import com.tcs.ilp.lms.bean.MagazineBean1;
import com.tcs.ilp.lms.bean.TransactionBean;

import com.tcs.ilp.lms.service.FineService;
import com.tcs.ilp.lms.service.LibrarianService;
import com.tcs.ilp.lms.service.MemberService;

/**
 * Servlet implementation class LostServlet
 */
public class LostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LostServlet() {
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
	
		response.setContentType("text/html");
		MemberService mem=new MemberService();
		LibrarianService lib=new LibrarianService();
		
		System.out.println(" in do post");
		List<BookBean1> bookList=new ArrayList<BookBean1>();

		List<CdBean1> cdList=new ArrayList<CdBean1>();
		List<MagazineBean1> magList=new ArrayList<MagazineBean1>();
		List<JournalBean1> journalList=new ArrayList<JournalBean1>();

		 Date date = new Date();
			
		
			   java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			 
			    System.out.println("sqlDate:" + sqlDate);

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
			 bookList=mem.viewBookList(userId);
			request.setAttribute("viewIssuedBookList", bookList); // has taken a variable viewissuedBooklist
			 RequestDispatcher rd = request.getRequestDispatcher("jsp/viewIssuedBookList.jsp");
			 rd.forward(request, response);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else if("submitReport".equalsIgnoreCase(req))  //from viewIssuedBookList.jsp to submitReport.jsp
		{
			
			try {
			
			  bookList=mem.viewBookList(userId);
			  String [] id=request.getParameterValues("radio1");
			
			  ArrayList<Integer> idForCost=new ArrayList<Integer>();
			  for(int i=0;i<id.length;i++)
				  idForCost.add(Integer.parseInt(id[i]));
			
			if (id!=null){	
			 request.setAttribute("submitReport", bookList); 
			 request.setAttribute("submitId", idForCost);// has taken a variable submitReport
			 RequestDispatcher rd = request.getRequestDispatcher("jsp/submitReport.jsp");
			 rd.forward(request, response);

					for(Integer idCost:idForCost)
						mem.updateStatus(idCost);
				System.out.println("status changed");
			}
		
		}
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else if("view".equalsIgnoreCase(req))  //from homepage to viewLost_Member.jsp
		{
			System.out.println("hello");
			try {
			bookList=mem.viewLost(userId);
			cdList=mem.viewLostCD(userId);
			magList=mem.viewLostMag(userId);
			journalList=mem.viewLostJournal(userId);
		
			request.setAttribute("viewLostBookList", bookList); 
			request.setAttribute("viewLostCdList", cdList);// has taken a variable viewLostBookList
			  
			  request.setAttribute("viewLostMagazineList", magList);
			  request.setAttribute("viewLostJournalList",journalList);
			   RequestDispatcher rd1 = request.getRequestDispatcher("jsp/viewLost_Member.jsp");
			   rd1.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
		else if("lostList".equalsIgnoreCase(req))  //from homepage to reportCancelled.jsp
		{
			
			try {
				  String [] id=request.getParameterValues("radio1");
				  ArrayList<Integer> idForStatus=new ArrayList<Integer>();
				  for(int i=0;i<id.length;i++)
				   idForStatus.add(Integer.parseInt(id[i]));
						  										
			 RequestDispatcher rd = request.getRequestDispatcher("jsp/reportCancelled.jsp");
			   rd.forward(request, response);
			   for(Integer idCost:idForStatus)
			   {
					mem.cancelReport(idCost);
					mem.cancelReportCd(idCost);
					mem.cancelReportMagazine(idCost);
					mem.cancelReportJournal(idCost);
					
			   }
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else if (("viewAll").equalsIgnoreCase(req))
		{
			
				try {
				 bookList=lib.viewLostLibrarian();
				 cdList=lib.viewLostLibrarianCd();
				 magList=lib.viewLostLibrarianMag();
				 journalList=lib.viewLostLibrarianJournal();
				 int txn = 0;
				 ArrayList<Double> fineList=new ArrayList<Double>();
				 ArrayList<Double> fineList2=new ArrayList<Double>();
				 ArrayList<Double> fineList3=new ArrayList<Double>();
				 ArrayList<Double> fineList4=new ArrayList<Double>();
				 double fine = 0;
				 Date duedate = null;
				 FineService fineservice = new FineService();
				
				for(int i=0;i<bookList.size();i++)
				{				 
					txn= bookList.get(i).getTxnId();
					duedate=(bookList.get(i).getDueDate());
					fine=fineservice.calculateFine(txn, duedate);
					 fineList.add(fine);
				}		
				 			
			
				for(int i=0;i<cdList.size();i++)
				{				 
					txn= cdList.get(i).getTxnId();
					duedate=(cdList.get(i).getDueDate());
					fine=fineservice.calculateFine(txn, duedate);
					 fineList2.add(fine);
				}	
			
				for(int i=0;i<magList.size();i++)
				{				 
					txn= magList.get(i).getTxnId();
					duedate=magList.get(i).getDueDate();
					fine=fineservice.calculateFine(txn, duedate);
					 fineList3.add(fine);
				}	
				for(int i=0;i<journalList.size();i++)
				{				 
					txn=journalList.get(i).getTxnId();
					duedate=(journalList.get(i).getDueDate());
					fine=fineservice.calculateFine(txn, duedate);
					 fineList4.add(fine);
				}	
				request.setAttribute("fine", fineList);
				 request.setAttribute("fine2", fineList2);
				 request.setAttribute("fine3", fineList3);
				 request.setAttribute("fine4", fineList4);
				 request.setAttribute("viewLostReport", bookList); // has taken a variable viewissuedBooklist
				 request.setAttribute("viewLostReportCd", cdList);
				 request.setAttribute("viewLostReportMagazine", magList);
				 request.setAttribute("viewLostReportJournal", journalList);
				 RequestDispatcher rd = request.getRequestDispatcher("jsp/viewLostReport.jsp");
				 rd.forward(request, response);


			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
		else if("payForLost".equals(req))
		{
			FineService fineservice = new FineService();
			HttpSession session2=request.getSession(false);
			String [] id=request.getParameterValues("radio1");
			ArrayList<Integer> idStatus=new ArrayList<Integer>();
			 ArrayList<Double> fineList=new ArrayList<Double>();
			 ArrayList<BookBean1> tBeanList= new ArrayList<BookBean1>();
			 bookList=(ArrayList<BookBean1>)session2.getAttribute("bookList");
			cdList=(ArrayList<CdBean1>)session2.getAttribute("cdList");
			for(int i=0;i<id.length;i++)
				idStatus.add(Integer.parseInt(id[i]));
			for(Integer id1:idStatus)
			{
			lib.statusP(id1);
			}
			double	fine=0.0;
			for(BookBean1 bean:bookList)
			{
				for(Integer id1:idStatus)
					if(id1==bean.getItemId())
					{
					fine=fineservice.calculateFine(bean.getTxnId(), bean.getDueDate());
					fineList.add(fine);
					}
				
			}
			for(CdBean1 bean:cdList)
			{
				for(Integer id1:idStatus)
					if(id1==bean.getItemId())
					{
					fine=fineservice.calculateFine(bean.getTxnId(), bean.getDueDate());
					fineList.add(fine);
					}
				
			}
				tBeanList=lib.listWithFine(idStatus);
				System.out.println("size of tBeanList"+tBeanList.size());
				request.setAttribute("tBeanList", tBeanList);
			
				request.setAttribute("fineList", fineList);
					
			try {
			 			
			 RequestDispatcher rd = request.getRequestDispatcher("jsp/afterPayment.jsp");
			 rd.forward(request, response);


		} catch (Exception e) {
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

		
		
		
	

