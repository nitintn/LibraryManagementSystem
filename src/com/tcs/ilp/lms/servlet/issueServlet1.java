package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcs.ilp.lms.bean.BookBean;
import com.tcs.ilp.lms.dao.TransactionDAO;
import com.tcs.ilp.lms.service.LibrarianService;

/**
 * Servlet implementation class issueServlet1
 */
public class issueServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	int itemID;
	String item_type="";
BookBean been1=null;
int memberID;
String member_type="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public issueServlet1() {
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
			
			if(request.getParameter("page").equalsIgnoreCase("searchItem"))
			{
				
				 memberID = Integer.parseInt(request.getParameter("memberID"));
				 itemID = Integer.parseInt(request.getParameter("itemID"));
			 item_type = request.getParameter("item");
				
				 member_type = new TransactionDAO().checkMemberType(memberID);
				
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

				pw.println("</BODY>");
				pw.println("</HTML>");
				
				
				
			
		 been1=	new TransactionDAO().searchItem(item_type, itemID);
			
			
			request.setAttribute("been2", been1);

			}
			

			 	RequestDispatcher ReqDispatcher= request.getRequestDispatcher("html/issueItem3.jsp");
				  
				ReqDispatcher.forward(request, response);
				
				
			}

			if(request.getParameter("page").equalsIgnoreCase("issueItem"))
			{
			
				
			String status = been1.getStatus();
				

			
				
				
				if(status.equalsIgnoreCase("AVAILABLE") || status.equalsIgnoreCase("R"))
				{
			
				String	issueItem=	new LibrarianService().issueItem(item_type, member_type, memberID, itemID);		
					
					
					if(issueItem.equalsIgnoreCase(item_type+" issued successfully !!!"))
						{
						new TransactionDAO().changeStatus(itemID);
						request.setAttribute("msg4", item_type+" issued successfully !!!" );
						}
				
					else{
						
						request.setAttribute("msg4", issueItem );
					}
					
				}
				
				
				
				else	if(!status.equalsIgnoreCase("AVAILABLE") && !status.equalsIgnoreCase("R"))
				{
			
					request.setAttribute("msg4",item_type+" cannot be issued !!!" );
				
				}
				
				else{}

				RequestDispatcher ReqDispatcher= request.getRequestDispatcher("html/LibIssueItem3.jsp");
				  
			ReqDispatcher.forward(request, response);
		}
		}
		else
		{
			PrintWriter pw = response.getWriter();
			
			if(request.getParameter("page").equalsIgnoreCase("searchItem"))
			{
				
				 memberID = Integer.parseInt(request.getParameter("memberID"));
				 itemID = Integer.parseInt(request.getParameter("itemID"));
			 item_type = request.getParameter("item");
				
				 member_type = new TransactionDAO().checkMemberType(memberID);
				
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

				pw.println("</BODY>");
				pw.println("</HTML>");
				
				
				
			
		 been1=	new TransactionDAO().searchItem(item_type, itemID);
			
			
			request.setAttribute("been2", been1);

			}
			

			 	RequestDispatcher ReqDispatcher= request.getRequestDispatcher("html/issueItem3.jsp");
				  
				ReqDispatcher.forward(request, response);
				
				
			}

			if(request.getParameter("page").equalsIgnoreCase("issueItem"))
			{
			
				
			String status = been1.getStatus();
				

			
				
				
				if(status.equalsIgnoreCase("AVAILABLE") || status.equalsIgnoreCase("R"))
				{
			
				String	issueItem=	new LibrarianService().issueItem(item_type, member_type, memberID, itemID);		
					
					
					if(issueItem.equalsIgnoreCase(item_type+" issued successfully !!!"))
						{
						new TransactionDAO().changeStatus(itemID);
						request.setAttribute("msg4", item_type+" issued successfully !!!" );
						}
				
					else{
						
						request.setAttribute("msg4", issueItem );
					}
					
				}
				
				
				
				else	if(!status.equalsIgnoreCase("AVAILABLE") && !status.equalsIgnoreCase("R"))
				{
			
					request.setAttribute("msg4",item_type+" cannot be issued !!!" );
				
				}
				
				else{}

			
			
			
				RequestDispatcher ReqDispatcher= request.getRequestDispatcher("html/issueItem3.jsp");
				  
			ReqDispatcher.forward(request, response);
		}}
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
