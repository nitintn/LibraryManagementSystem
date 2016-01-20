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
import com.tcs.ilp.lms.service.LibrarianService;
import com.tcs.ilp.lms.service.MemberService;

/**
 * Servlet implementation class LostCdServlet
 */
public class LostCdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LostCdServlet() {
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
		List<CdBean1> cdList=new ArrayList<CdBean1>();
		String req=request.getParameter("req");
		
		int userId = 0;
		HttpSession session = request.getSession(false);
		try
		{
			userId = (Integer) session.getAttribute("userId");
		if("register".equalsIgnoreCase(req))
		{
			
			try {
			cdList=mem.viewCdList(userId);
			request.setAttribute("viewIssuedcdList", cdList); // has taken a variable viewissuedcdlist
			 RequestDispatcher rd = request.getRequestDispatcher("jsp/viewIssuedCdList.jsp");
			 rd.forward(request, response);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else if("submitReport".equalsIgnoreCase(req))  //from viewIssuedcdList.jsp to submitReport.jsp
		{
			
			try {
			
				cdList=mem.viewCdList(userId);
				  String [] id=request.getParameterValues("radio2");
			 // String id1=request.getParameter("radio2");
				  ArrayList<Integer> idForCost=new ArrayList<Integer>();
				  for(int i=0;i<id.length;i++)
					  idForCost.add(Integer.parseInt(id[i]));
			if (id!=null){	
			 request.setAttribute("submitReport", cdList);
			 request.setAttribute("submitId", idForCost);// has taken a variable submitReport
			 RequestDispatcher rd = request.getRequestDispatcher("jsp/submitReportCd.jsp");
			 rd.forward(request, response);
			 for(Integer idCost:idForCost)
					mem.updateStatusCd(idCost);
				System.out.println("status changed");
			}
		
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else if("view".equalsIgnoreCase(req))  //from homepage to viewLost_Member.jsp
		{			
			try 
			{			
				cdList=mem.viewLostCD(userId);
				request.setAttribute("viewLostcdList", cdList); // has taken a variable viewLostcdList
				RequestDispatcher rd = request.getRequestDispatcher("jsp/viewLost_Member.jsp");
				rd.forward(request, response);
			} 
			catch (Exception e) 
			{
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
