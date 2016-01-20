package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.ilp.lms.bean.CdBean1;
import com.tcs.ilp.lms.bean.MagazineBean1;
import com.tcs.ilp.lms.service.LibrarianService;
import com.tcs.ilp.lms.service.MemberService;

/**
 * Servlet implementation class LostMagServlet
 */
public class LostMagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LostMagServlet() {
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
		List<MagazineBean1> magList=new ArrayList<MagazineBean1>();
	
		String req=request.getParameter("req");
		
		int userId = 0;
		HttpSession session = request.getSession(false);
		try
		{
			userId = (Integer) session.getAttribute("userId");
		if("register".equalsIgnoreCase(req))
		{
			
			try {
			 magList=mem.viewMagazineList(userId);
			request.setAttribute("viewIssuedmagList", magList); // has taken a variable viewissuedmaglist
			 RequestDispatcher rd = request.getRequestDispatcher("jsp/viewIssuedMagazineList.jsp");
			 rd.forward(request, response);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else if("submitReport".equalsIgnoreCase(req))  //from viewIssuedmagList.jsp to submitReport.jsp
		{
			
			try {
			
			  magList=mem.viewMagazineList(userId);
			  String [] id=request.getParameterValues("radio2");
			  ArrayList<Integer> idForCost=new ArrayList<Integer>();
			  for(int i=0;i<id.length;i++)
				  idForCost.add(Integer.parseInt(id[i]));
			  /*String id1=request.getParameter("radio2");
			  boolean isBoolean=false;*/
			if (id!=null){	
			 request.setAttribute("submitReport", magList); // has taken a variable submitReport
			 request.setAttribute("submitId", idForCost);
			 RequestDispatcher rd = request.getRequestDispatcher("jsp/submitReportMag.jsp");
			 rd.forward(request, response);
			 for(Integer idCost:idForCost)
					mem.updateStatusMag(idCost);
				System.out.println("status of magazine changed");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else if("view".equalsIgnoreCase(req))  //from homepage to viewLost_Member.jsp
		{
			
			try {
			
			magList=mem.viewLostMag(userId);
			request.setAttribute("viewLostmagList", magList); // has taken a variable viewLostmagList
			 RequestDispatcher rd = request.getRequestDispatcher("jsp/viewLost_Member.jsp");
			   rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else if("payForLost".equals(req))
		{
			int id=Integer.parseInt(request.getParameter("radio1"));
			System.out.println("id to pay "+id);
			lib.statusP(id);
			
			try {
			 			
			 RequestDispatcher rd = request.getRequestDispatcher("jsp/afterPayment.jsp");
			 rd.forward(request, response);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		}
		else if("lostList".equalsIgnoreCase(req))  //from homepage to viewLost_Member.jsp
		{
			
			try {
			
			magList=mem.viewLostMag(userId);
			  int id=Integer.parseInt(request.getParameter("radio2"));
			  boolean isBoolean=false;

			request.setAttribute("viewLostmagList", magList); // has taken a variable viewLostmagList
			 RequestDispatcher rd = request.getRequestDispatcher("jsp/reportCancelled.jsp");
			   rd.forward(request, response);
			   for(MagazineBean1 bk:magList)	
				{
					if(bk.getMagazineId()==id)
					{ 
						isBoolean=true;
					
					}
				}
				if(isBoolean)
					mem.cancelReport(id);
			   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else if (("viewAll").equalsIgnoreCase(req))
		{

							
				try {
				 magList=lib.viewLostLibrarianMag();
				 request.setAttribute("viewLostReport", magList); // has taken a variable viewissuedmaglist

				 RequestDispatcher rd = request.getRequestDispatcher("jsp/viewLostReport.jsp");
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
