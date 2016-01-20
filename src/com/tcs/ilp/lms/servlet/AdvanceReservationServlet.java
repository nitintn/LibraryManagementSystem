package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.ilp.lms.bean.*;
import com.tcs.ilp.lms.service.*;

/**
 * Servlet implementation class AdvanceReservationServlet
 */
public class AdvanceReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvanceReservationServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LibrarianService AdvancedReservationServiceObj = new LibrarianService();
		AdvancedReservationBean AdvancedReservationBeanObj= new AdvancedReservationBean();
		PrintWriter out = response.getWriter();
		String result = request.getParameter("result");
		
		
		ArrayList<AdvancedReservationBean> advancedReservationList = new ArrayList<AdvancedReservationBean>();
		String itemName=null, authorName=null, category=null, subCategory=null;
		

		//String buttonPressed=request.getParameter("btn");
		int userId = 0;
		HttpSession session = request.getSession(false);
		try
		{
			userId = (Integer) session.getAttribute("userId");
		//admin
			if(("Submit").equalsIgnoreCase(result))
			{  category = request.getParameter("category");
			session.setAttribute("category", category);
			System.out.println("category is "+category);
				if("BOOK".equalsIgnoreCase(category))
				{ 
					itemName = request.getParameter("Title");
					
					AdvancedReservationBeanObj.setItemName(itemName);
					authorName = request.getParameter("Author");
					AdvancedReservationBeanObj.setAuthorName(authorName);
					
					AdvancedReservationBeanObj.setCategory(category);
					AdvancedReservationBeanObj.setSubject(request.getParameter("Subject"));
					AdvancedReservationBeanObj.setPublication(request.getParameter("Publication"));
					
					
				}
				else if("CD".equalsIgnoreCase(category))
				{	
					itemName = request.getParameter("Title1");
					AdvancedReservationBeanObj.setItemName(itemName);
				
					AdvancedReservationBeanObj.setCategory(category);
					AdvancedReservationBeanObj.setPublication(request.getParameter("Publication1"));
					AdvancedReservationBeanObj.setSubject(request.getParameter("Subject1"));
					
					
				}
				else if("MAGAZINE".equalsIgnoreCase(category))
				{
					
					itemName = request.getParameter("Title2");
					AdvancedReservationBeanObj.setItemName(itemName);
					AdvancedReservationBeanObj.setCategory(category);
					AdvancedReservationBeanObj.setVolumeNumber(Integer.parseInt(request.getParameter("Volume2")));
				}
				else if("JOURNAL".equalsIgnoreCase(category))
				{
					itemName = request.getParameter("Title3");
					AdvancedReservationBeanObj.setItemName(itemName);
					AdvancedReservationBeanObj.setCategory(category);
					AdvancedReservationBeanObj.setVolumeNumber(Integer.parseInt(request.getParameter("Volume3")));
					
				}
				 
				System.out.println(AdvancedReservationBeanObj.getCategory());
				System.out.println(AdvancedReservationBeanObj.getItemName());
				System.out.println(AdvancedReservationBeanObj.getAuthorName());
				System.out.println(AdvancedReservationBeanObj.getPublication());
				System.out.println(AdvancedReservationBeanObj.getVolumeNumber());
				System.out.println(AdvancedReservationBeanObj.getSubject());
				
				//System.out.println(AdvancedReservationBeanObj.getSubCategory());
				
				
				ArrayList<AdvancedReservationBean> advancedReservationSearchList = new ArrayList<AdvancedReservationBean>();
				
				advancedReservationSearchList=AdvancedReservationServiceObj.searchItem(AdvancedReservationBeanObj);
				
				request.setAttribute("viewBookList", advancedReservationSearchList);
				System.out.println("SIZE IS "+advancedReservationSearchList.size());
				RequestDispatcher RequestDispatcherObject = request.getRequestDispatcher("html/reservationSearchList.jsp");
				RequestDispatcherObject.forward(request, response);
			}
		else if(("Reserve").equalsIgnoreCase(result))
		{
			System.out.println("in reserve loop of servlet");
			//userid, itemname, date
			System.out.println(Long.parseLong(request.getParameter("selectItem")));
			System.out.println((String)session.getAttribute("category"));
			AdvancedReservationBeanObj.setUserId(Integer.parseInt(request.getParameter("userId")));
			AdvancedReservationBeanObj.setIsbn(Long.parseLong(request.getParameter("selectItem")));
			AdvancedReservationBeanObj.setCategory((String)session.getAttribute("category"));
			
			int reservationNo= AdvancedReservationServiceObj.advancedReservation(AdvancedReservationBeanObj);
			if(reservationNo>1)
			{
			RequestDispatcher rd = request.getRequestDispatcher("jsp/ReservationResult.jsp");
			request.setAttribute("ReservationNumber", reservationNo);
			 rd.forward(request, response);
			}
			else{
				RequestDispatcher rd1 = request.getRequestDispatcher("jsp/InvalidReservationResult.jsp");
				 rd1.forward(request, response);
			}
		}
		else if(("CancelReservation").equalsIgnoreCase(result))
		{
			int memId=Integer.parseInt(request.getParameter("memId"));
			int resNo=Integer.parseInt(request.getParameter("resNo"));
			AdvancedReservationServiceObj.cancelReservationUpdate(memId, resNo);
			
			request.getRequestDispatcher("jsp/CancellationUpdated.jsp").forward(request, response);
			
		}
		else if(("ViewReservationList").equalsIgnoreCase(result))
		{
			int memId= Integer.parseInt(request.getParameter("memId"));
			advancedReservationList=AdvancedReservationServiceObj.viewReservationList(memId);
			//System.out.println(advancedReservationList.size());
			if(advancedReservationList.size()!=0)
			{
				try
				{
					request.setAttribute("viewResList", advancedReservationList);
					
					request.getRequestDispatcher("jsp/viewReservationList.jsp").forward(request, response);// to redirect to a new jsp page
					
				}
				catch (Exception e) 
				{
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		}
		
		//librarian
			if(("SubmitLib").equalsIgnoreCase(result))
			{  category = request.getParameter("category");
			session.setAttribute("category", category);
			System.out.println("category is "+category);
				if("BOOK".equalsIgnoreCase(category))
				{ 
					itemName = request.getParameter("Title");
					
					AdvancedReservationBeanObj.setItemName(itemName);
					authorName = request.getParameter("Author");
					AdvancedReservationBeanObj.setAuthorName(authorName);
					
					AdvancedReservationBeanObj.setCategory(category);
					AdvancedReservationBeanObj.setSubject(request.getParameter("Subject"));
					AdvancedReservationBeanObj.setPublication(request.getParameter("Publication"));
					
					
				}
				else if("CD".equalsIgnoreCase(category))
				{	
					itemName = request.getParameter("Title1");
					AdvancedReservationBeanObj.setItemName(itemName);
				
					AdvancedReservationBeanObj.setCategory(category);
					AdvancedReservationBeanObj.setPublication(request.getParameter("Publication1"));
					AdvancedReservationBeanObj.setSubject(request.getParameter("Subject1"));
					
					
				}
				else if("MAGAZINE".equalsIgnoreCase(category))
				{
					
					itemName = request.getParameter("Title2");
					AdvancedReservationBeanObj.setItemName(itemName);
					AdvancedReservationBeanObj.setCategory(category);
					AdvancedReservationBeanObj.setVolumeNumber(Integer.parseInt(request.getParameter("Volume2")));
				}
				else if("JOURNAL".equalsIgnoreCase(category))
				{
					itemName = request.getParameter("Title3");
					AdvancedReservationBeanObj.setItemName(itemName);
					AdvancedReservationBeanObj.setCategory(category);
					AdvancedReservationBeanObj.setVolumeNumber(Integer.parseInt(request.getParameter("Volume3")));
					
				}
				 
				System.out.println(AdvancedReservationBeanObj.getCategory());
				System.out.println(AdvancedReservationBeanObj.getItemName());
				System.out.println(AdvancedReservationBeanObj.getAuthorName());
				System.out.println(AdvancedReservationBeanObj.getPublication());
				System.out.println(AdvancedReservationBeanObj.getVolumeNumber());
				System.out.println(AdvancedReservationBeanObj.getSubject());
				
				//System.out.println(AdvancedReservationBeanObj.getSubCategory());
				
				
				ArrayList<AdvancedReservationBean> advancedReservationSearchList = new ArrayList<AdvancedReservationBean>();
				
				advancedReservationSearchList=AdvancedReservationServiceObj.searchItem(AdvancedReservationBeanObj);
				
				request.setAttribute("viewBookList", advancedReservationSearchList);
				System.out.println("SIZE IS "+advancedReservationSearchList.size());
				RequestDispatcher RequestDispatcherObject = request.getRequestDispatcher(" html/reservationSearchList.jsp");
				RequestDispatcherObject.forward(request, response);
			}
		else if(("ReserveLib").equalsIgnoreCase(result))
		{
			System.out.println("in reserve loop of servlet");
			//userid, itemname, date
			AdvancedReservationBeanObj.setUserId(Integer.parseInt(request.getParameter("userId")));
			AdvancedReservationBeanObj.setIsbn(Integer.parseInt(request.getParameter("selectItem")));
			AdvancedReservationBeanObj.setCategory((String)session.getAttribute("category"));
			
			System.out.println(AdvancedReservationBeanObj.getUserId()+" "+ AdvancedReservationBeanObj.getItemName()+" "+AdvancedReservationBeanObj.getReservationDate());
			//String selectItem = request.getParameter("selectItem");
			int reservationNo= AdvancedReservationServiceObj.advancedReservation(AdvancedReservationBeanObj);
			if(reservationNo>1)
			{
			RequestDispatcher rd = request.getRequestDispatcher("jsp/LibReservationResult.jsp");
			request.setAttribute("ReservationNumber", reservationNo);
			 rd.forward(request, response);
			}
			else{
				RequestDispatcher rd1 = request.getRequestDispatcher("jsp/LibInvalidReservationResult.jsp");
				 rd1.forward(request, response);
			}
		}
		else if(("CancelReservationLib").equalsIgnoreCase(result))
		{
			int memId=Integer.parseInt(request.getParameter("memId"));
			int resNo=Integer.parseInt(request.getParameter("resNo"));
			AdvancedReservationServiceObj.cancelReservationUpdate(memId, resNo);
			
			request.getRequestDispatcher("jsp/LibCancellationUpdated.jsp").forward(request, response);
			
		}
		else if(("ViewReservationListLib").equalsIgnoreCase(result))
		{
			int memId= Integer.parseInt(request.getParameter("memId"));
			advancedReservationList=AdvancedReservationServiceObj.viewReservationList(memId);
			//System.out.println(advancedReservationList.size());
			if(advancedReservationList.size()!=0)
			{
				try
				{
					request.setAttribute("viewResList", advancedReservationList);
					
					request.getRequestDispatcher("jsp/LibViewReservationList.jsp").forward(request, response);// to redirect to a new jsp page
					
				}
				catch (Exception e) 
				{
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		}
		
		//member
			if(("SubmitMem").equalsIgnoreCase(result))
			{  category = request.getParameter("category");
			session.setAttribute("category", category);
			System.out.println("category is "+category);
				if("BOOK".equalsIgnoreCase(category))
				{ 
					itemName = request.getParameter("Title");
					
					AdvancedReservationBeanObj.setItemName(itemName);
					authorName = request.getParameter("Author");
					AdvancedReservationBeanObj.setAuthorName(authorName);
					
					AdvancedReservationBeanObj.setCategory(category);
					AdvancedReservationBeanObj.setSubject(request.getParameter("Subject"));
					AdvancedReservationBeanObj.setPublication(request.getParameter("Publication"));
					
					
				}
				else if("CD".equalsIgnoreCase(category))
				{	
					itemName = request.getParameter("Title1");
					AdvancedReservationBeanObj.setItemName(itemName);
				
					AdvancedReservationBeanObj.setCategory(category);
					AdvancedReservationBeanObj.setPublication(request.getParameter("Publication1"));
					AdvancedReservationBeanObj.setSubject(request.getParameter("Subject1"));
					
					
				}
				else if("MAGAZINE".equalsIgnoreCase(category))
				{
					
					itemName = request.getParameter("Title2");
					AdvancedReservationBeanObj.setItemName(itemName);
					AdvancedReservationBeanObj.setCategory(category);
					AdvancedReservationBeanObj.setVolumeNumber(Integer.parseInt(request.getParameter("Volume2")));
				}
				else if("JOURNAL".equalsIgnoreCase(category))
				{
					itemName = request.getParameter("Title3");
					AdvancedReservationBeanObj.setItemName(itemName);
					AdvancedReservationBeanObj.setCategory(category);
					AdvancedReservationBeanObj.setVolumeNumber(Integer.parseInt(request.getParameter("Volume3")));
					
				}
				 
				System.out.println(AdvancedReservationBeanObj.getCategory());
				System.out.println(AdvancedReservationBeanObj.getItemName());
				System.out.println(AdvancedReservationBeanObj.getAuthorName());
				System.out.println(AdvancedReservationBeanObj.getPublication());
				System.out.println(AdvancedReservationBeanObj.getVolumeNumber());
				System.out.println(AdvancedReservationBeanObj.getSubject());
				
				//System.out.println(AdvancedReservationBeanObj.getSubCategory());
				
				
				ArrayList<AdvancedReservationBean> advancedReservationSearchList = new ArrayList<AdvancedReservationBean>();
				
				advancedReservationSearchList=AdvancedReservationServiceObj.searchItem(AdvancedReservationBeanObj);
				
				request.setAttribute("viewBookList", advancedReservationSearchList);
				System.out.println("SIZE IS "+advancedReservationSearchList.size());
				RequestDispatcher RequestDispatcherObject = request.getRequestDispatcher("html/MemReservationSearchList.jsp");
				RequestDispatcherObject.forward(request, response);
			}
		else if(("ReserveMem").equalsIgnoreCase(result))
		{
			System.out.println("in reserve loop of servlet");
			//userid, itemname, date
			AdvancedReservationBeanObj.setUserId(userId);
			AdvancedReservationBeanObj.setIsbn(Integer.parseInt(request.getParameter("selectItem")));
			AdvancedReservationBeanObj.setCategory((String)session.getAttribute("category"));
			
			System.out.println(AdvancedReservationBeanObj.getUserId()+" "+ AdvancedReservationBeanObj.getItemName()+" "+AdvancedReservationBeanObj.getReservationDate());
			//String selectItem = request.getParameter("selectItem");
			int reservationNo= AdvancedReservationServiceObj.advancedReservation(AdvancedReservationBeanObj);
			if(reservationNo>1)
			{
			RequestDispatcher rd = request.getRequestDispatcher("jsp/MemReservationResult.jsp");
			request.setAttribute("ReservationNumber", reservationNo);
			 rd.forward(request, response);
			}
			else{
				RequestDispatcher rd1 = request.getRequestDispatcher("jsp/MemInvalidReservationResult.jsp");
				 rd1.forward(request, response);
			}
		}
		else if(("CancelReservationMem").equalsIgnoreCase(result))
		{
			int memId=Integer.parseInt(request.getParameter("memId"));
			int resNo=Integer.parseInt(request.getParameter("resNo"));
			AdvancedReservationServiceObj.cancelReservationUpdate(memId, resNo);
			
			request.getRequestDispatcher("jsp/MemCancellationUpdated.jsp").forward(request, response);
			
		}
		else if(("ViewReservationListMem").equalsIgnoreCase(result))
		{
			int memId= Integer.parseInt(request.getParameter("memId"));
			advancedReservationList=AdvancedReservationServiceObj.viewReservationList(memId);
			//System.out.println(advancedReservationList.size());
			if(advancedReservationList.size()!=0)
			{
				try
				{
					request.setAttribute("viewResList", advancedReservationList);
					
					request.getRequestDispatcher("jsp/MemViewReservationList.jsp").forward(request, response);// to redirect to a new jsp page
					
				}
				catch (Exception e) 
				{
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		}
		}
		catch(Exception e)
		{
			response.sendRedirect("GuestHome.html");
		}
	}
}



