package com.tcs.ilp.lms.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.ilp.lms.bean.Defaultersbean;
import com.tcs.ilp.lms.bean.FineBean;
import com.tcs.ilp.lms.bean.SearchBean;
import com.tcs.ilp.lms.service.FineService;

/**
 * @author 730783
 * @CLASS NAME PAYMENT
 * Servlet implementation class Payment
 */
public class Payment extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 private FineService fineService = new FineService();  
	 private ArrayList<Defaultersbean> defList;
	 private ArrayList<FineBean> fineBeanList = new ArrayList<FineBean>();
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public Payment() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
	if(request.getParameter("req").equals("admin"))
	{
		if((request.getParameter("button")).equals("Search"))
		{	
			
			int memberId=0;
			
			 memberId = Integer.parseInt(request.getParameter("memberId"));
			
			String category = request.getParameter("options");
			String fromDate=request.getParameter("from");
			String toDate=request.getParameter("to");
			System.out.println(memberId);
			System.out.println(category);
			System.out.println(fromDate);
			System.out.println(toDate);
			SearchBean searchBean = new SearchBean();
		
			if(memberId!=0 && category.equals("select"))
			{	
			
			searchBean.setMemid(memberId);
			searchBean.setCategory(null);
			searchBean.setFromDate(null);
			searchBean.setToDate(null);
			
			
			}
			
			else if(memberId!=0&& category!=null)
			{
				searchBean.setMemid(memberId);
				searchBean.setCategory(category);
				searchBean.setFromDate(null);
				searchBean.setToDate(null);
			}
			else{
				searchBean.setMemid(0);
				searchBean.setCategory(null);
				searchBean.setFromDate(fromDate);
				searchBean.setToDate(toDate);
				
				
			}
			 defList=fineService.searchDefaulters(searchBean);
			// defList= fineService.searchDefaulters(memberId,category);
			if(defList!=null)
			{
				request.setAttribute("defaulter",defList);
				request.getRequestDispatcher("./jsp/defaultersList.jsp").forward(request, response);
								}
			}
	
		 if((request.getParameter("button")).equals("Cancel"))
		 {
			 	 HttpSession session1=request.getSession();
			 	 double total=0;
			 	 String[] a=request.getParameterValues("check");
				 System.out.println(a.length);
				 defList=(ArrayList<Defaultersbean>)session1.getAttribute("deflist");
				 FineService fineservice = new FineService();
				 for(int i=0;i<a.length;i++)
				 {
					 fineBeanList.add(new FineBean());
				 }
				for(Defaultersbean dbean:defList)
				{	
					for(int i=0;i<a.length;i++)
					{
					if(dbean.getTransid()==Integer.parseInt(a[i]) )
					{
						
						fineBeanList.get(i).setTransid(dbean.getTransid());
						fineBeanList.get(i).setFineamount(dbean.getFineamount());
						total=total+dbean.getFineamount();
						fineservice.addFineDetails(fineBeanList.get(i));
						
						System.out.println("at"+i+a[i]);
						
						 
					}
					}
					
				}
				request.setAttribute("disp", fineBeanList);
				request.getRequestDispatcher("./jsp/cancelpage.jsp").forward(request, response);
			 
		 }
		 if((request.getParameter("button")).equals("Pay & Print Slip"))
			{	
			 request.getRequestDispatcher("./jsp/afterPayment.jsp").forward(request, response);
			 
			}


		
				if((request.getParameter("button")).equals("Pay"))
				{	
						HttpSession session1=request.getSession();
						double total=0;
						String[] a=request.getParameterValues("check");
						defList=(ArrayList<Defaultersbean>)session1.getAttribute("deflist");
						ArrayList<FineBean> fineBeanList = new ArrayList<FineBean>();
					
						for(int i=0;i<a.length;i++)
						{
							fineBeanList.add(new FineBean());
						}
						for(Defaultersbean dbean:defList)
						{	
							for(int i=0;i<a.length;i++)
							{
								if(dbean.getTransid()==Integer.parseInt(a[i]) )
								{
						
									fineBeanList.get(i).setTransid(dbean.getTransid());
									fineBeanList.get(i).setFineamount(dbean.getFineamount());
									fineBeanList.get(i).setDuedate(dbean.getDuedate());
									total=total+dbean.getFineamount();
									fineService.addFineDetails(fineBeanList.get(i));
									fineService.Payment(dbean.getTransid());
									System.out.println("at"+i+a[i]);
								}
							}
						}
						request.setAttribute("total", total);
						request.setAttribute("display", fineBeanList);
						request.getRequestDispatcher("./jsp/Payment.jsp").forward(request, response);
				
				}
				
	}else if(request.getParameter("req").equals("mem")){	
				if((request.getParameter("button1")).equals("Search")&&request.getParameter("button1")!=null)
				{	
					
					int memberId=0;
					
					 memberId = Integer.parseInt(request.getParameter("memberId"));
					
					String category = request.getParameter("options");
					String fromDate=request.getParameter("from");
					String toDate=request.getParameter("to");
					System.out.println(memberId);
					System.out.println(category);
					System.out.println(fromDate);
					System.out.println(toDate);
					SearchBean searchBean = new SearchBean();
				
					if(memberId!=0 && category.equals("select"))
					{	
					
					searchBean.setMemid(memberId);
					searchBean.setCategory(null);
					searchBean.setFromDate(null);
					searchBean.setToDate(null);
					
					
					}
					
					else if(memberId!=0&& category!=null)
					{
						searchBean.setMemid(memberId);
						searchBean.setCategory(category);
						searchBean.setFromDate(null);
						searchBean.setToDate(null);
					}
					else{
						searchBean.setMemid(0);
						searchBean.setCategory(null);
						searchBean.setFromDate(fromDate);
						searchBean.setToDate(toDate);
						
						
					}
					 defList=fineService.searchDefaulters(searchBean);
					// defList= fineService.searchDefaulters(memberId,category);
					if(defList!=null)
					{
						request.setAttribute("defaulter",defList);
						request.getRequestDispatcher("./jsp/LibrariandefaultersList.jsp").forward(request, response);
										}
					}
				if((request.getParameter("button1")).equals("Pay")&&request.getParameter("button1")!=null)
				{	
						HttpSession session1=request.getSession();
						double total=0;
						String[] a=request.getParameterValues("check");
						defList=(ArrayList<Defaultersbean>)session1.getAttribute("deflist");
						ArrayList<FineBean> fineBeanList = new ArrayList<FineBean>();
					
						for(int i=0;i<a.length;i++)
						{
							fineBeanList.add(new FineBean());
						}
						for(Defaultersbean dbean:defList)
						{	
							for(int i=0;i<a.length;i++)
							{
								if(dbean.getTransid()==Integer.parseInt(a[i]) )
								{
						
									fineBeanList.get(i).setTransid(dbean.getTransid());
									fineBeanList.get(i).setFineamount(dbean.getFineamount());
									fineBeanList.get(i).setDuedate(dbean.getDuedate());
									total=total+dbean.getFineamount();
									fineService.addFineDetails(fineBeanList.get(i));
									fineService.Payment(dbean.getTransid());
									System.out.println("at"+i+a[i]);
								}
							}
						}
						request.setAttribute("total", total);
						request.setAttribute("display", fineBeanList);
						request.getRequestDispatcher("./jsp/LibrarianPayment.jsp").forward(request, response);
				
				}
		
	if((request.getParameter("button1")).equals("Cancel Fine")&&request.getParameter("button1")!=null)
	 {
		 	 HttpSession session1=request.getSession();
		 	 double total=0;
		 	 String[] a=request.getParameterValues("check");
			 System.out.println(a.length);
			 defList=(ArrayList<Defaultersbean>)session1.getAttribute("deflist");
			 FineService fineservice = new FineService();
			 for(int i=0;i<a.length;i++)
			 {
				 fineBeanList.add(new FineBean());
			 }
			for(Defaultersbean dbean:defList)
			{	
				for(int i=0;i<a.length;i++)
				{
				if(dbean.getTransid()==Integer.parseInt(a[i]) )
				{
					
					fineBeanList.get(i).setTransid(dbean.getTransid());
					fineBeanList.get(i).setFineamount(dbean.getFineamount());
					total=total+dbean.getFineamount();
					fineservice.addFineDetails(fineBeanList.get(i));
					
					System.out.println("at"+i+a[i]);
					
					 
				}
				}
				
			}
			request.setAttribute("disp", fineBeanList);
			request.getRequestDispatcher("./jsp/Librariancancelpage.jsp").forward(request, response);
		 
	 }

	 
	}
	}
	
}
