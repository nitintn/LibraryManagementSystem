package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.ilp.lms.bean.FineBean;
import com.tcs.ilp.lms.service.FineService;

/**
 * Servlet implementation class CancelFine
 */
public class CancelFine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelFine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * This servlet saves the reason for canceling fine.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		/**
		 * Get the  reason from jsp page and sending them to service to save.
		 */
		if(request.getParameter("req").equals("admin"))
		{
		HttpSession ses=request.getSession(false);
		String reason=request.getParameter("reason");
		
		/**
		 * creating fine bean
		 */
		FineService fineservice = new FineService();
		ArrayList<FineBean> fineBeanList=(ArrayList<FineBean>)ses.getAttribute("finebeanlist");
		for(FineBean fbean:fineBeanList)
		{
			fbean.setReason(reason);
			fineservice.addFineReason(fbean);
			fineservice.cancelFine(fbean.getTransid());
			System.out.println(fbean.getFineid()+fbean.getReason());
		}
		
		
		/**
		 * calling add fine reason method to add the reason to fine reason table
		 */
	
		response.sendRedirect("jsp/Searchdefaulterslist.jsp");
		//request.getRequestDispatcher("../Searchdefaulterslist.html").forward(request, response);
		}else if(request.getParameter("req").equals("mem"))
		{
			HttpSession ses=request.getSession(false);
			String reason=request.getParameter("reason");
			
			/**
			 * creating fine bean
			 */
			FineService fineservice = new FineService();
			ArrayList<FineBean> fineBeanList=(ArrayList<FineBean>)ses.getAttribute("finebeanlist");
			for(FineBean fbean:fineBeanList)
			{
				fbean.setReason(reason);
				fineservice.addFineReason(fbean);
				fineservice.cancelFine(fbean.getTransid());
				System.out.println(fbean.getFineid()+fbean.getReason());
			}
			
			
			/**
			 * calling add fine reason method to add the reason to fine reason table
			 */
		
			response.sendRedirect("jsp/LibrarianSearchdefaulterslist.jsp");
			
		}
		
	}
	

}
