package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcs.ilp.lms.dao.TransactionDAO;
import com.tcs.ilp.lms.service.LibrarianService;
import com.tcs.ilp.lms.service.MemberService;

/**
 * Servlet implementation class issueServlet
 */
public class issueServlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public issueServlt() {
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
			PrintWriter pw = response.getWriter();
			
			int memberID = Integer.parseInt(request.getParameter("memberID"));
			int itemID = Integer.parseInt(request.getParameter("itemID"));
			String item_type = request.getParameter("item_type");
			
			String member_type = new TransactionDAO().checkMemberType(memberID);
			
			String validMember = new TransactionDAO().validateMember(memberID);
			String validItem = new TransactionDAO().validateItem(itemID, item_type);
		    String itemStatus = new TransactionDAO().checkItemStatus(itemID);
		    String issueItem ="";
		    	
			if(!itemStatus.equalsIgnoreCase("AVAILABLE") && !itemStatus.equalsIgnoreCase("R"))

			{
			
				pw.println("<HTML>");
				pw.println("<HEAD>");
				pw.println("</HEAD>");
				pw.println("<BODY>");
			pw.println("<h1>"+item_type +"can not be issued because its current status is : " +itemStatus+" </h1>");
				pw.println("<h1>itemID "+itemID+" cannot be issued because its current status is : " +itemStatus+" </h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");
			
				if(itemStatus==null||itemStatus=="")
				{}
				else{
				
				request.setAttribute("msg", itemID+" itemID cannot be issued because its current status is : " +itemStatus);
				}
				
			}

			if(validMember!="true")
			{
				pw.println("<HTML>");
				pw.println("<HEAD>");
				pw.println("</HEAD>");
				pw.println("<BODY>");
				pw.println("<h1>Invalid Member Type !!!</h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");
							
				request.setAttribute("msg1","Invalid MemberID !!!");
			
			}
			
			if(validItem!="true")
			{
				pw.println("<HTML>");
				pw.println("<HEAD>");
				pw.println("</HEAD>");
				pw.println("<BODY>");
				pw.println("<h1>Invalid ItemID !!!</h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");
							
				request.setAttribute("msg2","Invalid ItemID ");
			}

			
			if(validMember=="true" && validItem=="true" && (itemStatus.equalsIgnoreCase("AVAILABLE") || itemStatus.equalsIgnoreCase("R")))
			{
			pw.println("<HTML>");
			pw.println("<HEAD>");

			pw.println("</HEAD>");
			pw.println("<BODY>");

			issueItem=	new LibrarianService().issueItem(item_type, member_type, memberID, itemID);
		    pw.println("<h1> "+issueItem+" </h1>");
			pw.println("</BODY>");
			pw.println("</HTML>");
			
			request.setAttribute("msg3",issueItem);
			
			
			if(issueItem.equalsIgnoreCase(item_type+" issued successfully !!!"))
			{
			new TransactionDAO().changeStatus(itemID);
			}
			
			
			}
			
			

		 	RequestDispatcher ReqDispatcher= request.getRequestDispatcher("html/issueItem1.jsp");
			  
			ReqDispatcher.forward(request, response);
		}
		else
		{
			PrintWriter pw = response.getWriter();
			
			int memberID = Integer.parseInt(request.getParameter("memberID"));
			int itemID = Integer.parseInt(request.getParameter("itemID"));
			String item_type = request.getParameter("item_type");
			
			String member_type = new TransactionDAO().checkMemberType(memberID);
			
			String validMember = new TransactionDAO().validateMember(memberID);
			String validItem = new TransactionDAO().validateItem(itemID, item_type);
		    String itemStatus = new TransactionDAO().checkItemStatus(itemID);
		    String issueItem ="";
		    	
			if(!itemStatus.equalsIgnoreCase("AVAILABLE") && !itemStatus.equalsIgnoreCase("R"))

			{
			
				pw.println("<HTML>");
				pw.println("<HEAD>");
				pw.println("</HEAD>");
				pw.println("<BODY>");
			pw.println("<h1>"+item_type +"can not be issued because its current status is : " +itemStatus+" </h1>");
				pw.println("<h1>itemID "+itemID+" cannot be issued because its current status is : " +itemStatus+" </h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");
			
				if(itemStatus==null||itemStatus=="")
				{}
				else{
				
				request.setAttribute("msg", itemID+" itemID cannot be issued because its current status is : " +itemStatus);
				}
				
			}

			if(validMember!="true")
			{
				pw.println("<HTML>");
				pw.println("<HEAD>");
				pw.println("</HEAD>");
				pw.println("<BODY>");
				pw.println("<h1>Invalid Member Type !!!</h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");
							
				request.setAttribute("msg1","Invalid MemberID !!!");
			
			}
			
			if(validItem!="true")
			{
				pw.println("<HTML>");
				pw.println("<HEAD>");
				pw.println("</HEAD>");
				pw.println("<BODY>");
				pw.println("<h1>Invalid ItemID !!!</h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");
							
				request.setAttribute("msg2","Invalid ItemID ");
			}

			
			if(validMember=="true" && validItem=="true" && (itemStatus.equalsIgnoreCase("AVAILABLE") || itemStatus.equalsIgnoreCase("R")))
			{
			pw.println("<HTML>");
			pw.println("<HEAD>");

			pw.println("</HEAD>");
			pw.println("<BODY>");

			issueItem=	new LibrarianService().issueItem(item_type, member_type, memberID, itemID);
		    pw.println("<h1> "+issueItem+" </h1>");
			pw.println("</BODY>");
			pw.println("</HTML>");
			
			request.setAttribute("msg3",issueItem);
			
			
			if(issueItem.equalsIgnoreCase(item_type+" issued successfully !!!"))
			{
			new TransactionDAO().changeStatus(itemID);
			}
			
			
			}
			
			

		 	RequestDispatcher ReqDispatcher= request.getRequestDispatcher("html/issueItem1.jsp");
			  
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
