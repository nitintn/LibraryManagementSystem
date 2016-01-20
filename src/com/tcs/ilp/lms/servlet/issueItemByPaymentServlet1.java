package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.ilp.lms.dao.TransactionDAO;

/**
 * Servlet implementation class issueItemByPaymentServlet1
 */
public class issueItemByPaymentServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public issueItemByPaymentServlet1() {
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
			
			
			String page = request.getParameter("page");

			HttpSession session = request.getSession(false);
			
			String item_type = (String) session.getAttribute("item_type");
			String memberID1 =  (String) session.getAttribute("memberID");
			String itemID1 = (String) session.getAttribute("itemID");
			

				String issueItem="";
			
			int memberID=Integer.parseInt(memberID1);
			int itemID = Integer.parseInt(itemID1);

				if(page.equalsIgnoreCase("amountPaid"))
				{
			    	
		   	issueItem = new TransactionDAO().issueItem(memberID, itemID, item_type);
			    	issueItem = "issued";
			    	String item_type1=item_type;
				//issueItem=	new MemberService().issueItem(item_type, member_type, memberID, itemID)
			
			
			if(issueItem.equalsIgnoreCase("issued"))
			{
				new TransactionDAO().changeStatus(itemID);
				 pw.println("<HTML>");
					pw.println("<HEAD>");
					pw.println("</HEAD>");
					pw.println("<BODY>");
					pw.println("<h1>"+item_type1+" issued successfully !!!"+" </h1>");
					pw.println("</BODY>");
					pw.println("</HTML>");
				
					
				
					request.setAttribute("msg5",item_type1+" issued successfully !!!" );

			}
			}

			 	RequestDispatcher ReqDispatcher= request.getRequestDispatcher("html/IssueByPayment1.jsp");
				  
				ReqDispatcher.forward(request, response);
		}
		else
		{
			PrintWriter pw = response.getWriter();
			
			
			String page = request.getParameter("page");

			HttpSession session = request.getSession(false);
			
			String item_type = (String) session.getAttribute("item_type");
			String memberID1 =  (String) session.getAttribute("memberID");
			String itemID1 = (String) session.getAttribute("itemID");
			

				String issueItem="";
			
			int memberID=Integer.parseInt(memberID1);
			int itemID = Integer.parseInt(itemID1);

				if(page.equalsIgnoreCase("amountPaid"))
				{
			    	
		   	issueItem = new TransactionDAO().issueItem(memberID, itemID, item_type);
			    	issueItem = "issued";
			    	String item_type1=item_type;
				//issueItem=	new MemberService().issueItem(item_type, member_type, memberID, itemID)
			
			
			if(issueItem.equalsIgnoreCase("issued"))
			{
				new TransactionDAO().changeStatus(itemID);
				 pw.println("<HTML>");
					pw.println("<HEAD>");
					pw.println("</HEAD>");
					pw.println("<BODY>");
					pw.println("<h1>"+item_type1+" issued successfully !!!"+" </h1>");
					pw.println("</BODY>");
					pw.println("</HTML>");
				
					
				
					request.setAttribute("msg5",item_type1+" issued successfully !!!" );

			}
			}

			 	RequestDispatcher ReqDispatcher= request.getRequestDispatcher("html/issueByPayment1.jsp");
				  
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
