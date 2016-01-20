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
import com.tcs.ilp.lms.service.LibrarianService;
import com.tcs.ilp.lms.service.MemberService;

/**
 * Servlet implementation class issueItemByPaymentServlet
 */
public class issueItemByPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public issueItemByPaymentServlet() {
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
			String issueItem ="";

			PrintWriter pw = response.getWriter();
			String page = request.getParameter("page");

				int itemID = Integer.parseInt(request.getParameter("itemID"));
			String	item_type = request.getParameter("item_type");
				
		    	int memberID = Integer.parseInt(request.getParameter("memberID"));
			
				
		    	HttpSession session = request.getSession(true);
		    	session.setAttribute("memberID", String.valueOf(memberID));
		    	session.setAttribute("itemID", String.valueOf(itemID));
		    	session.setAttribute("item_type", item_type);
		    	
		    	
				String member_type = new TransactionDAO().checkMemberType(memberID);
				
				String validMember = new TransactionDAO().validateMember(memberID);
				String validItem = new TransactionDAO().validateItem(itemID, item_type);
			    String itemStatus = new TransactionDAO().checkItemStatus(itemID);

				if(!itemStatus.equalsIgnoreCase("AVAILABLE") && !itemStatus.equalsIgnoreCase("R"))

				{
					
					pw.println("<HTML>");
					pw.println("<HEAD>");
					pw.println("</HEAD>");
					pw.println("<BODY>");
					pw.println("<h1>itemID "+itemID +" cannot be issued because its current status is : " +itemStatus+" </h1>");
					pw.println("</BODY>");
					pw.println("</HTML>");
					
					if(itemStatus==null||itemStatus=="")
					{}
					else{
					
				
				request.setAttribute("msg",itemID +" itemID cannot be issued because its current status is : " +itemStatus );
					}
				
				}

				if(validMember!="true")
				{
					pw.println("<HTML>");
					pw.println("<HEAD>");
					pw.println("</HEAD>");
					pw.println("<BODY>");
					pw.println("<h1>Invalid MemberID !!!</h1>");
					pw.println("</BODY>");
					pw.println("</HTML>");
								
					request.setAttribute("msg1","Invalid MemberID" );
					
					
				
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
								
				
					request.setAttribute("msg2","Invalid itemID" );

				}
				if(validMember=="true" && validItem=="true" && (itemStatus.equalsIgnoreCase("AVAILABLE")|| itemStatus.equalsIgnoreCase("R")))
				{
			
			pw.println("<HTML>");
				pw.println("<HEAD>");
			
			pw.println("</HEAD>");
			pw.println("<BODY>");

				String checkEligibility=new LibrarianService().issueItemByPay(item_type, member_type, memberID, itemID);
				
					
				if(!checkEligibility.equalsIgnoreCase(item_type+" issued successfully !!!"))
				{
				
				pw.println("<h1> "+checkEligibility+" </h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");
				
				 request.setAttribute("msg3", checkEligibility); 

				}
				
				if(checkEligibility.equalsIgnoreCase(item_type+" issued successfully !!!"))
				{	
					
					float amount=0;
					amount = new LibrarianService().checkAmount(itemID);
				    pw.println("<HTML>");
					pw.println("<HEAD>");
					pw.println("</HEAD>");
					pw.println("<BODY>");
					pw.println("<h1>You have to pay Rs "+amount+"</h1>");
			    	pw.println("<FORM action='issueItemByPaymentServlet1'>");
					pw.println("<TABLE>");
					pw.println("<TR>");
				    pw.println("<TD>");
			        pw.println("<input type='submit' value='Pay Amount'>");  
					pw.println("</TD>");
					pw.println("<TD><input type='hidden' name='page' value='amountPaid'></TD>");
					pw.println("<TD>");
			        pw.println("<a href='issueItemByPayment.html'>BACK</a>");  
					pw.println("</TD>");
					pw.println("</TR>");
					pw.println("</TABLE>");
					pw.println("</FORM>");
					pw.println("</BODY>");
				    pw.println("</HTML>");

				   request.setAttribute("msg4", amount); 

				}
			}

				
				
			 	RequestDispatcher ReqDispatcher= request.getRequestDispatcher("html/issueByPayment1.jsp");
				  
				ReqDispatcher.forward(request, response);
		}
		else
		{
			String issueItem ="";

			PrintWriter pw = response.getWriter();
			String page = request.getParameter("page");

				int itemID = Integer.parseInt(request.getParameter("itemID"));
			String	item_type = request.getParameter("item_type");
				
		    	int memberID = Integer.parseInt(request.getParameter("memberID"));
			
				
		    	HttpSession session = request.getSession(true);
		    	session.setAttribute("memberID", String.valueOf(memberID));
		    	session.setAttribute("itemID", String.valueOf(itemID));
		    	session.setAttribute("item_type", item_type);
		    	
		    	
				String member_type = new TransactionDAO().checkMemberType(memberID);
				
				String validMember = new TransactionDAO().validateMember(memberID);
				String validItem = new TransactionDAO().validateItem(itemID, item_type);
			    String itemStatus = new TransactionDAO().checkItemStatus(itemID);

				if(!itemStatus.equalsIgnoreCase("AVAILABLE") && !itemStatus.equalsIgnoreCase("R"))

				{
					
					pw.println("<HTML>");
					pw.println("<HEAD>");
					pw.println("</HEAD>");
					pw.println("<BODY>");
					pw.println("<h1>itemID "+itemID +" cannot be issued because its current status is : " +itemStatus+" </h1>");
					pw.println("</BODY>");
					pw.println("</HTML>");
					
					if(itemStatus==null||itemStatus=="")
					{}
					else{
					
				
				request.setAttribute("msg",itemID +" itemID cannot be issued because its current status is : " +itemStatus );
					}
				
				}

				if(validMember!="true")
				{
					pw.println("<HTML>");
					pw.println("<HEAD>");
					pw.println("</HEAD>");
					pw.println("<BODY>");
					pw.println("<h1>Invalid MemberID !!!</h1>");
					pw.println("</BODY>");
					pw.println("</HTML>");
								
					request.setAttribute("msg1","Invalid MemberID" );
					
					
				
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
								
				
					request.setAttribute("msg2","Invalid itemID" );

				}
				if(validMember=="true" && validItem=="true" && (itemStatus.equalsIgnoreCase("AVAILABLE")|| itemStatus.equalsIgnoreCase("R")))
				{
			
			pw.println("<HTML>");
				pw.println("<HEAD>");
			
			pw.println("</HEAD>");
			pw.println("<BODY>");

				String checkEligibility=new LibrarianService().issueItemByPay(item_type, member_type, memberID, itemID);
				
					
				if(!checkEligibility.equalsIgnoreCase(item_type+" issued successfully !!!"))
				{
				
				pw.println("<h1> "+checkEligibility+" </h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");
				
				 request.setAttribute("msg3", checkEligibility); 

				}
				
				if(checkEligibility.equalsIgnoreCase(item_type+" issued successfully !!!"))
				{	
					
					float amount=0;
					amount = new LibrarianService().checkAmount(itemID);
				    pw.println("<HTML>");
					pw.println("<HEAD>");
					pw.println("</HEAD>");
					pw.println("<BODY>");
					pw.println("<h1>You have to pay Rs "+amount+"</h1>");
			    	pw.println("<FORM action='issueItemByPaymentServlet1'>");
					pw.println("<TABLE>");
					pw.println("<TR>");
				    pw.println("<TD>");
			        pw.println("<input type='submit' value='Pay Amount'>");  
					pw.println("</TD>");
					pw.println("<TD><input type='hidden' name='page' value='amountPaid'></TD>");
					pw.println("<TD>");
			        pw.println("<a href='issueItemByPayment.html'>BACK</a>");  
					pw.println("</TD>");
					pw.println("</TR>");
					pw.println("</TABLE>");
					pw.println("</FORM>");
					pw.println("</BODY>");
				    pw.println("</HTML>");

				   request.setAttribute("msg4",amount); 

				}
			}

				
				
			 	RequestDispatcher ReqDispatcher= request.getRequestDispatcher("html/LibIssueByPayment1.jsp");
				  
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
