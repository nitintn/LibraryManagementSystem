package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.ilp.lms.dao.AdvancedReservationDao;
import com.tcs.ilp.lms.service.AdvancedReservationService;
import com.tcs.ilp.lms.service.LibrarianService;

/**
 * Servlet implementation class issueItemReservedInAdvanceServlet1
 */
public class issueItemReservedInAdvanceServlet1 extends HttpServlet 

{static int memberID;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public issueItemReservedInAdvanceServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String pg=request.getParameter("pg");
		if("lib".equalsIgnoreCase(pg))
		{
			PrintWriter pw  = response.getWriter();
			
			
	    	HttpSession session = request.getSession(true);
	  	
			
			
			
			String page = request.getParameter("page");
			if(page.equalsIgnoreCase("login"))
			{
				
				memberID = Integer.parseInt(request.getParameter("memberID"));
				
				pw.println("<HTML>");
				pw.println("<HEAD>");
				pw.println("</HEAD>");
				pw.println("<BODY>");
					
			pw.println("<h1>"+memberID+"</h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");
				
				session.setAttribute("memberID", String.valueOf(memberID));
			
			}
			
			
			else
			{
			
		
		String[] ck = request.getParameterValues("parteek");
		
	ArrayList<Integer> al = new ArrayList<Integer>();
		
		for(int i=0;i<ck.length;i++)
		{
			
			al.add(Integer.parseInt(ck[i]));
			
		}
		
		
		

		
		pw.println("<HTML>");
		pw.println("<HEAD>");
		pw.println("</HEAD>");
		pw.println("<BODY>");
		for(int i=0;i<al.size();i++)
		{
	pw.println("<h1>"+al.get(i)+"</h1>");
		}	
	pw.println("<h1</h1>");
		pw.println("</BODY>");
		pw.println("</HTML>");
		
		
		for(int i=0;i<al.size();i++)
		{
		int status = new LibrarianService().checkStatus(al.get(i),memberID);
		
		if(status==0)
		{

			
			String item_type  = new LibrarianService().checkItem_type(al.get(i));
			String done  = new LibrarianService().deleteRowFromResTable(al.get(i), memberID, item_type);
			
		if(done.equalsIgnoreCase("deletedissued"))
		{
			pw.println("<HTML>");
			pw.println("<HEAD>");
			pw.println("</HEAD>");
			pw.println("<BODY>");
				
		pw.println("<h1>"+al.get(i)+" issued succesfully</h1>");
			pw.println("</BODY>");
			pw.println("</HTML>");
			
			request.setAttribute("msg", al.get(i)+" issued succesfully !!!");
		}
		
		else
		{
			request.setAttribute("msg","some error occured !!!");
			
		}
		
		
		
		}
			
		else
		{
			
			pw.println("<HTML>");
			pw.println("<HEAD>");
			pw.println("</HEAD>");
			pw.println("<BODY>");
				
		pw.println("<h1>"+al.get(i)+" cannot be issued</h1>");
			pw.println("</BODY>");
			pw.println("</HTML>");
			
			request.setAttribute("msg1", al.get(i)+" cannot be issued !!!");
			
		}
		
		
		
		
		}
		
			}
			RequestDispatcher ReqDispatcher= request.getRequestDispatcher("html/LibIssueItemReservedInAdvance1.jsp");
			  
			ReqDispatcher.forward(request, response);
		
		}
		else
		{
			PrintWriter pw  = response.getWriter();
			
			
	    	HttpSession session = request.getSession(true);
	  	
			
			
			
			String page = request.getParameter("page");
			if(page.equalsIgnoreCase("login"))
			{
				
				memberID = Integer.parseInt(request.getParameter("memberID"));
				
				pw.println("<HTML>");
				pw.println("<HEAD>");
				pw.println("</HEAD>");
				pw.println("<BODY>");
					
			pw.println("<h1>"+memberID+"</h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");
				
				session.setAttribute("memberID", String.valueOf(memberID));
			
			}
			
			
			else
			{
			
		
		String[] ck = request.getParameterValues("parteek");
		
	ArrayList<Integer> al = new ArrayList<Integer>();
		
		for(int i=0;i<ck.length;i++)
		{
			
			al.add(Integer.parseInt(ck[i]));
			
		}
		
		
		

		
		pw.println("<HTML>");
		pw.println("<HEAD>");
		pw.println("</HEAD>");
		pw.println("<BODY>");
		for(int i=0;i<al.size();i++)
		{
	pw.println("<h1>"+al.get(i)+"</h1>");
		}	
	pw.println("<h1</h1>");
		pw.println("</BODY>");
		pw.println("</HTML>");
		
		
		for(int i=0;i<al.size();i++)
		{
		int status = new LibrarianService().checkStatus(al.get(i),memberID);
		
		if(status==0)
		{

			
			String item_type  = new LibrarianService().checkItem_type(al.get(i));
			String done  = new LibrarianService().deleteRowFromResTable(al.get(i), memberID, item_type);
			
		if(done.equalsIgnoreCase("deletedissued"))
		{
			pw.println("<HTML>");
			pw.println("<HEAD>");
			pw.println("</HEAD>");
			pw.println("<BODY>");
				
		pw.println("<h1>"+al.get(i)+" issued succesfully</h1>");
			pw.println("</BODY>");
			pw.println("</HTML>");
			
			request.setAttribute("msg", al.get(i)+" issued succesfully !!!");
		}
		
		else
		{
			request.setAttribute("msg","some error occured !!!");
			
		}
		
		
		
		}
			
		else
		{
			
			pw.println("<HTML>");
			pw.println("<HEAD>");
			pw.println("</HEAD>");
			pw.println("<BODY>");
				
		pw.println("<h1>"+al.get(i)+" cannot be issued</h1>");
			pw.println("</BODY>");
			pw.println("</HTML>");
			
			request.setAttribute("msg1", al.get(i)+" cannot be issued !!!");
			
		}
		
		
		
		
		}
		
			}
			RequestDispatcher ReqDispatcher= request.getRequestDispatcher("html/issueItemReservedInAdvance1.jsp");
			  
			ReqDispatcher.forward(request, response);
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
