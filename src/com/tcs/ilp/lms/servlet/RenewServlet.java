package com.tcs.ilp.lms.servlet;
import com.tcs.ilp.lms.bean.MemberBean1;
import com.tcs.ilp.lms.bean.TransactionBean;
import com.tcs.ilp.lms.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RenewServlet
 */
public class RenewServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RenewServlet() 
    {
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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		MemberBean1 mB= new MemberBean1();
		
		
		List<TransactionBean> tList= new ArrayList<TransactionBean>();
		
		LibrarianService lS= new LibrarianService();
		//TransactionBean tbs= new TransactionBean();
		//tList=lS.renewBook(mB);
		PrintWriter pw= response.getWriter();
		
		String buttonPressed=request.getParameter("btn");
		System.out.println(buttonPressed);
		int userId = 0;
		HttpSession session = request.getSession(false);
		try
		{
			userId = (Integer) session.getAttribute("userId");
			
			
			//member id taken from session and renew link
			if("MemSubmit".equalsIgnoreCase(buttonPressed))
			{
				mB.setMemId(userId);
				tList=lS.renewBook(mB);
				if(tList.isEmpty())
				{
					try
					{
						RequestDispatcher rd = request.getRequestDispatcher("jsp/MemRenewUnavailable.jsp");// to redirect to a new jsp page
						rd.forward(request, response);
					}
					catch (Exception e) {
					// TODO: handle exception
						e.printStackTrace();
					}
				}
				else
				{
					try 
					{
						/*mB.setMemId(Integer.parseInt(request.getParameter("memId")));
						tList=lS.renewBook(mB);*/
						System.out.println("hello");
						request.setAttribute("viewIssuedList", tList); // has taken a variable viewissued
						RequestDispatcher rd = request.getRequestDispatcher("jsp/MemRenewalSelectList.jsp");
						rd.forward(request, response);
					} 
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			
			}
			else if("MemRENEW".equalsIgnoreCase(buttonPressed))
			{
				System.out.println("RENEWED");
				int selectedId=Integer.parseInt(request.getParameter("selectTrans"));
				//System.out.println(request.getParameter("selectTrans"));
				//tbs.setTransactionId(selectedId);
				TransactionBean tB=lS.renewUpdate(selectedId);
			
				request.setAttribute("transBeanObj", tB); 
				RequestDispatcher rd = request.getRequestDispatcher("jsp/MemRenewedDueDt.jsp");
				rd.forward(request, response);
			 
				//pw.println("Your new due dt is:"+tB.getDueDate().toString());
				System.out.println("Servlet new due dt:"+tB.getDueDate());
			}
				
			//admin
			if("SUBMIT".equalsIgnoreCase(buttonPressed))
			{
				mB.setMemId(Integer.parseInt(request.getParameter("memId")));
				tList=lS.renewBook(mB);
				if(tList.isEmpty())
				{
					try
					{
						RequestDispatcher rd = request.getRequestDispatcher("jsp/RenewUnavailable.jsp");// to redirect to a new jsp page
						rd.forward(request, response);
					}
					catch (Exception e) 
					{
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				else
				{
					try 
					{
						/*mB.setMemId(Integer.parseInt(request.getParameter("memId")));
						tList=lS.renewBook(mB);*/
						System.out.println("hello");
						request.setAttribute("viewIssuedList", tList); // has taken a variable viewissued
						request.getRequestDispatcher("jsp/RenewalSelectList.jsp").forward(request, response);
					} 
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
			else if("RENEW".equalsIgnoreCase(buttonPressed))
			{
				System.out.println("RENEWED");
				int selectedId=Integer.parseInt(request.getParameter("selectTrans"));
				//System.out.println(request.getParameter("selectTrans"));
				//tbs.setTransactionId(selectedId);
				TransactionBean tB=lS.renewUpdate(selectedId);
				
				request.setAttribute("transBeanObj", tB); 
				RequestDispatcher rd = request.getRequestDispatcher("jsp/RenewedDueDt.jsp");
				rd.forward(request, response);
					
				//pw.println("Your new due dt is:"+tB.getDueDate().toString());
				System.out.println("Servlet new due dt:"+tB.getDueDate());
			}
		
			//librarian
			if("SUBMITlib".equalsIgnoreCase(buttonPressed))
			{
				mB.setMemId(Integer.parseInt(request.getParameter("memId")));
				tList=lS.renewBook(mB);
				if(tList.isEmpty())
				{
					try
					{
						RequestDispatcher rd = request.getRequestDispatcher("jsp/LibRenewUnavailable.jsp");// to redirect to a new jsp page
						rd.forward(request, response);
					}
					catch (Exception e) 
					{
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				else
				{
					try 
					{
						/*mB.setMemId(Integer.parseInt(request.getParameter("memId")));
						tList=lS.renewBook(mB);*/
						System.out.println("hello");
						request.setAttribute("viewIssuedList", tList); // has taken a variable viewissued
						request.getRequestDispatcher("jsp/LibRenewalSelectList.jsp").forward(request, response);
					} 	
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
			else if("RENEWlib".equalsIgnoreCase(buttonPressed))
			{
				System.out.println("RENEWED");
				int selectedId=Integer.parseInt(request.getParameter("selectTrans"));
				//System.out.println(request.getParameter("selectTrans"));
				//tbs.setTransactionId(selectedId);
				TransactionBean tB=lS.renewUpdate(selectedId);
				
				request.setAttribute("transBeanObj", tB); 
				RequestDispatcher rd = request.getRequestDispatcher("jsp/LibRenewedDueDt.jsp");
				rd.forward(request, response);
			 
				//pw.println("Your new due dt is:"+tB.getDueDate().toString());
				System.out.println("Servlet new due dt:"+tB.getDueDate());
			}
			
			//to validate the enter memId as registered or unregistered
			if("checkMemId".equalsIgnoreCase(buttonPressed))
			{
				System.out.println("inside check mem id dyn");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				boolean memIdValid = false;
				int memId =Integer.parseInt(request.getParameter("memId"));
				System.out.println(memId);
				LibrarianService lS1= new LibrarianService();
				memIdValid = lS1.checkMemId(memId);
				System.out.println(memIdValid);
				if(memIdValid)
				{
					out.println("Member id is valid");								
				}
				else
				{
					out.println("Member id is not registered! Pls enter valid member id! ");
				}	
			}
		}
		catch(Exception e)
		{
			response.sendRedirect("GuestHome.html");
		}
	}
}


