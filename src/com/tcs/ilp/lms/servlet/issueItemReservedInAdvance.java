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
 * Servlet implementation class issueItemReservedInAdvance
 */
public class issueItemReservedInAdvance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public issueItemReservedInAdvance() {
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
			
			String issueItem ="";



			//if(page.equalsIgnoreCase("issueItemByPaying"))
			//{
			int itemID = Integer.parseInt(request.getParameter("itemID"));
			String	item_type = request.getParameter("item_type");

			int memberID = Integer.parseInt(request.getParameter("memberID"));


			HttpSession session1 = request.getSession(true);
			session1.setAttribute("memberID", String.valueOf(memberID));
			session1.setAttribute("itemID", String.valueOf(itemID));
			session1.setAttribute("item_type", item_type);


			String member_type = new TransactionDAO().checkMemberType(memberID);

			String validMember = new TransactionDAO().validateMember(memberID);
			String validItem = new TransactionDAO().validateItem(itemID, item_type);
			String itemStatus = new TransactionDAO().checkItemStatus(itemID);


			if(!itemStatus.equalsIgnoreCase("AR"))

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
				request.setAttribute("msg2","Invalid itemID !!!");	
			}


			if(validMember=="true" && validItem=="true" && itemStatus.equalsIgnoreCase("AR"))
			{

			pw.println("<HTML>");
			pw.println("<HEAD>");

			pw.println("</HEAD>");
			pw.println("<BODY>");

			pw.println("</BODY>");
			pw.println("</HEAD>");	
			pw.println("</HTML>");
				

			String status= new LibrarianService().issueAdvancedReservedItem(memberID, itemID);

				

			if(status.equalsIgnoreCase("true"))
			{

				pw.println("<HTML>");
				pw.println("<HEAD>");
				pw.println("</HEAD>");
				pw.println("<BODY>");
				pw.println("<h1>line 2</h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");		

				
			new TransactionDAO().issueItem(memberID, itemID, item_type)	;
			issueItem="issued";
			if(issueItem.equalsIgnoreCase("issued"))
			{
				new TransactionDAO().changeStatus(itemID);
				 pw.println("<HTML>");
					pw.println("<HEAD>");
					pw.println("</HEAD>");
					pw.println("<BODY>");
					pw.println("<h1>"+item_type+" issued successfully !!!"+" </h1>");
					pw.println("</BODY>");
					pw.println("</HTML>");	

					
					request.setAttribute("msg3",item_type+" issued successfully !!!");
					
			}
			}



			if(!issueItem.equalsIgnoreCase("issued"))
			{
				
				
				pw.println("<HTML>");
				pw.println("<HEAD>");
				pw.println("</HEAD>");
				pw.println("<BODY>");
				pw.println("<h1>"+status+"</h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");		
				
				request.setAttribute("msg4",status);
			}

			}	

				RequestDispatcher ReqDispatcher= request.getRequestDispatcher("issueItemReservedInAdvance.jsp");

			ReqDispatcher.forward(request, response);




		}
		else
		{
			PrintWriter pw = response.getWriter();
			
			String issueItem ="";



			//if(page.equalsIgnoreCase("issueItemByPaying"))
			//{
			int itemID = Integer.parseInt(request.getParameter("itemID"));
			String	item_type = request.getParameter("item_type");

			int memberID = Integer.parseInt(request.getParameter("memberID"));


			HttpSession session1 = request.getSession(true);
			session1.setAttribute("memberID", String.valueOf(memberID));
			session1.setAttribute("itemID", String.valueOf(itemID));
			session1.setAttribute("item_type", item_type);


			String member_type = new TransactionDAO().checkMemberType(memberID);

			String validMember = new TransactionDAO().validateMember(memberID);
			String validItem = new TransactionDAO().validateItem(itemID, item_type);
			String itemStatus = new TransactionDAO().checkItemStatus(itemID);


			if(!itemStatus.equalsIgnoreCase("AR"))

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
				request.setAttribute("msg2","Invalid itemID !!!");	
			}


			if(validMember=="true" && validItem=="true" && itemStatus.equalsIgnoreCase("AR"))
			{

			pw.println("<HTML>");
			pw.println("<HEAD>");

			pw.println("</HEAD>");
			pw.println("<BODY>");

			pw.println("</BODY>");
			pw.println("</HEAD>");	
			pw.println("</HTML>");
				

			String status= new LibrarianService().issueAdvancedReservedItem(memberID, itemID);

				

			if(status.equalsIgnoreCase("true"))
			{

				pw.println("<HTML>");
				pw.println("<HEAD>");
				pw.println("</HEAD>");
				pw.println("<BODY>");
				pw.println("<h1>line 2</h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");		

				
			new TransactionDAO().issueItem(memberID, itemID, item_type)	;
			issueItem="issued";
			if(issueItem.equalsIgnoreCase("issued"))
			{
				new TransactionDAO().changeStatus(itemID);
				 pw.println("<HTML>");
					pw.println("<HEAD>");
					pw.println("</HEAD>");
					pw.println("<BODY>");
					pw.println("<h1>"+item_type+" issued successfully !!!"+" </h1>");
					pw.println("</BODY>");
					pw.println("</HTML>");	

					
					request.setAttribute("msg3",item_type+" issued successfully !!!");
					
			}
			}



			if(!issueItem.equalsIgnoreCase("issued"))
			{
				
				
				pw.println("<HTML>");
				pw.println("<HEAD>");
				pw.println("</HEAD>");
				pw.println("<BODY>");
				pw.println("<h1>"+status+"</h1>");
				pw.println("</BODY>");
				pw.println("</HTML>");		
				
				request.setAttribute("msg4",status);
			}





			}	




				RequestDispatcher ReqDispatcher= request.getRequestDispatcher("issueItemReservedInAdvance.jsp");

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
