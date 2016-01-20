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
public class example extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public example() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
PrintWriter pw = response.getWriter();
	
	int memberID = Integer.parseInt(request.getParameter("memberID"));
	int itemID = Integer.parseInt(request.getParameter("itemID"));
	String item_type = request.getParameter("item_type");
	
	String member_type = new TransactionDAO().checkMemberType(memberID);
	
	String validMember = new TransactionDAO().validateMember(memberID);
	String validItem = new TransactionDAO().validateItem(itemID, item_type);
    String itemStatus = new TransactionDAO().checkItemStatus(itemID);
    String issueItem ="";
    
	//issueItem=	new MemberService().issueItem(item_type, member_type, memberID, itemID);
	
	
	//System.out.println("item : "+validItem);
	
	if(!itemStatus.equalsIgnoreCase("AVAILABLE") && !itemStatus.equalsIgnoreCase("R"))

	{
		
		pw.println("<HTML>");
		pw.println("<HEAD>");
		pw.println("</HEAD>");
		pw.println("<BODY>");
	//	pw.println("<h1>"+item_type +"can not be issued because its current status is : " +itemStatus+" </h1>");
		pw.println("<h1>itemID "+itemID+" can not be issued because its current status is : " +itemStatus+" </h1>");
		pw.println("</BODY>");
		pw.println("</HTML>");
		
		request.setAttribute("msg", itemID+" can not be issued because its current status is : " +itemStatus);
	
	
	
	
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
					
		request.setAttribute("msg1", "Invalid Member !!!");
	 
	
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
		request.setAttribute("msg2","Invalid Item !!!" );
	 				
	}
	
	
	
	
	
	
	
	if(validMember=="true" && validItem=="true" && (itemStatus.equalsIgnoreCase("AVAILABLE") || itemStatus.equalsIgnoreCase("R")))
	{
	pw.println("<HTML>");
	pw.println("<HEAD>");
//	pw.println("This is my project");
	pw.println("</HEAD>");
	pw.println("<BODY>");
//	pw.println("<h1> Member ID is : "+memberID+"</h1>");
//	pw.println("<h1> Item ID is : "+itemID+"</h1>");
//	pw.println("<h1> Item Type is : "+item_type+"</h1>");
//	pw.println("<h1> Member Type is : "+member_type+"</h1>");
//	pw.println("<h1> valid member is : "+validMember+"</h1>");
//	pw.println("<h1> valid item is : "+validItem+"</h1>");
//	pw.println("<h1> item status is : "+itemStatus+"</h1>");
	issueItem=	new LibrarianService().issueItem(item_type, member_type, memberID, itemID);
    pw.println("<h1> "+issueItem+" </h1>");
	pw.println("</BODY>");
	pw.println("</HTML>");

	request.setAttribute("msg3",issueItem );
  
	
	if(issueItem.equalsIgnoreCase(item_type+" issued successfully !!!"))
	{
	new TransactionDAO().changeStatus(itemID);
	}
	
	
	}
	
	
	
 	RequestDispatcher ReqDispatcher= request.getRequestDispatcher("issueByPayment.jsp");
	  
	ReqDispatcher.forward(request, response);
	 
	
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
