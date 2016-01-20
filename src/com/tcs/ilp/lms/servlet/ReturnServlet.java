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

import com.tcs.ilp.lms.bean.BookBean1;
import com.tcs.ilp.lms.bean.CdBean1;
import com.tcs.ilp.lms.bean.Defaultersbean;
import com.tcs.ilp.lms.bean.FineBean;
import com.tcs.ilp.lms.bean.JournalBean1;
import com.tcs.ilp.lms.bean.MagazineBean1;
import com.tcs.ilp.lms.bean.MemberBean1;
import com.tcs.ilp.lms.bean.TransactionBean;
import com.tcs.ilp.lms.service.FineService;
import com.tcs.ilp.lms.service.LibrarianService;

/**
* Servlet implementation class ReturnServlet.
* @author    738101  
* @version      
* @Class     ReturnServlet
* @Creation  28/01/2014
*/
public class ReturnServlet extends HttpServlet 
{
   	private static final long serialVersionUID = 1L;
       
    /**
     * constructor
     * @see HttpServlet#HttpServlet()
     */
    public ReturnServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
		doPost(request,response);
	}

	/**
	 * This method overrides doPost method of HttpServlet interface
	 * @param		<type> <Description>
	 * @return		<type> <Description>
	 * @exception 	Throws ServletException and IOException
	 * @since	    
	 * @see			HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	
	   	response.setContentType("text/html");
		LibrarianService librarianService= new LibrarianService();
		List<BookBean1> bookList=new ArrayList<BookBean1>();
		String req=request.getParameter("req");
		 
		int userId = 0;
		HttpSession session = request.getSession(false);
		try
		{
		   	userId = (Integer) session.getAttribute("userId");
		   	HttpSession session1=request.getSession(true);
		   	if("sendmemid".equalsIgnoreCase(req))
		   	{
		   	   	boolean flag=false;
		   		String memId=request.getParameter("memId");
		   		int membId=Integer.parseInt(memId);
		   		flag=librarianService.checkMemId(membId);
		   		if(flag)
		   		{
		   			session1.setAttribute("MemId", memId);
		   			request.setAttribute("memid", memId);
		   			RequestDispatcher rd = request.getRequestDispatcher("html/ReturnOptions.jsp");
		   			rd.forward(request, response);
		   		}
		   		else
		   		{
	        	    RequestDispatcher rd = request.getRequestDispatcher("jsp/ReturnItem1.jsp");
	        	    rd.forward(request, response);
		   		}
		   	}
		
		   	else if("issuedbook".equalsIgnoreCase(req))
		   	{
		   		int id=Integer.parseInt((String) session1.getAttribute("MemId"));
		   		try 
		   		{
		   			bookList=librarianService.viewBookList(id);
		   			request.setAttribute("viewBookList", bookList); 
		   			RequestDispatcher rd = request.getRequestDispatcher("jsp/viewBookList.jsp");
		   			rd.forward(request, response);
			


		   		} 	
		   		catch (Exception e) 
		   		{
		   			e.printStackTrace();
		   		}
		   	}
		   	else if("issuedcd".equalsIgnoreCase(req))
		   	{
		   		int id=Integer.parseInt((String) session1.getAttribute("MemId"));
		   		try 
		   		{
		   		   	List<CdBean1> cdList=new ArrayList<CdBean1>();
		   		   	cdList=librarianService.viewCdList(id);
		   		   	request.setAttribute("viewCdList", cdList); 
		   		   	RequestDispatcher rd = request.getRequestDispatcher("html/viewCdList.jsp");
		   		   	rd.forward(request, response);
		   		} 
		   		catch (Exception e) 
		   		{
		   			e.printStackTrace();
		   		}
		   	}

		   	else if("issuedmagazine".equalsIgnoreCase(req))
		   	{	
		   		int id=Integer.parseInt((String) session1.getAttribute("MemId"));
		   		try 
		   		{
		   			List<MagazineBean1> magList=new ArrayList<MagazineBean1>();
			magList=librarianService.viewMagazineList(id);
			 request.setAttribute("viewMagazineList", magList); 

			 RequestDispatcher rd = request.getRequestDispatcher("html/viewMagazineList.jsp");
			 rd.forward(request, response);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	
		else if("issuedjournal".equalsIgnoreCase(req))
		{	System.out.println("in journal servlet");
		  
			int id=Integer.parseInt((String) session1.getAttribute("MemId"));
			  System.out.println(session.getAttribute("MemId")); 
		try {
			List<JournalBean1> journalList=new ArrayList<JournalBean1>();
			journalList=librarianService.viewJournalList(id);
			 request.setAttribute("viewJournalList", journalList); 

			 RequestDispatcher rd = request.getRequestDispatcher("html/viewJournalList.jsp");
			 rd.forward(request, response);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

		else if("issuedlist".equalsIgnoreCase(req))
		{	
		  
			int id=Integer.parseInt((String) session1.getAttribute("MemId"));
			  System.out.println(session.getAttribute("MemId")); 
		try {
			List<TransactionBean> transList=new ArrayList<TransactionBean>();
			MemberBean1 memBean=new MemberBean1();
			memBean.setMemId(id);
			transList=librarianService.viewList(memBean);
			 request.setAttribute("viewList", transList); 

			 RequestDispatcher rd = request.getRequestDispatcher("jsp/ViewList.jsp");
			 rd.forward(request, response);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else if("liststatuschange".equalsIgnoreCase(req))
		{
			ArrayList<Defaultersbean> defaulterlist1=new ArrayList<Defaultersbean>();
			System.out.println("in list status change");
			
			try {
				String [] id=request.getParameterValues("radio1"); 
				ArrayList<Integer> transIds=new ArrayList<Integer>();
				  for(int i=0;i<id.length;i++)
					  transIds.add(Integer.parseInt(id[i]));
				  FineService fineService= new FineService();
				
			
				  defaulterlist1= fineService.searchDefaulters2(Integer.parseInt((String) session1.getAttribute("MemId")),transIds);
				
				
				System.out.println("defaulter list size is "+defaulterlist1.size());
					if(defaulterlist1.size()==0)
					{		
						LibrarianService libService=new LibrarianService();
						for(Integer id1:transIds)
						{
						libService.updateStatus(id1);
						}
						 RequestDispatcher rd = request.getRequestDispatcher("html/ReturnOptions1.jsp");
						 rd.forward(request, response);
					}else{
						request.setAttribute("defaulter",defaulterlist1);
						
						request.getRequestDispatcher("./jsp/DefaulterPage.jsp").forward(request, response);
						
					}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	
	
		
		
		else if("statuschange".equalsIgnoreCase(req))
		{	
			ArrayList<Defaultersbean> defaulterlist1=new ArrayList<Defaultersbean>();
			System.out.println("in book status change");
			
			try {
				String [] id=request.getParameterValues("radio1"); 
				ArrayList<Integer> transIds=new ArrayList<Integer>();
				  for(int i=0;i<id.length;i++)
					  transIds.add(Integer.parseInt(id[i]));
				  FineService fineService= new FineService();
				
			
				  defaulterlist1= fineService.searchDefaulters1(Integer.parseInt((String) session1.getAttribute("MemId")), "book",transIds);
				
				
				System.out.println("defaulter list size is "+defaulterlist1.size());
					if(defaulterlist1.size()==0)
					{		
						LibrarianService libService=new LibrarianService();
						for(Integer id1:transIds)
						{
						libService.updateStatus(id1);
						}
						 RequestDispatcher rd = request.getRequestDispatcher("html/ReturnOptions1.jsp");
						 rd.forward(request, response);
					}else{
						request.setAttribute("defaulter",defaulterlist1);
						
						request.getRequestDispatcher("./jsp/DefaulterPage.jsp").forward(request, response);
						
					}
				
			}
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	
	
	else if("cdstatuschange".equalsIgnoreCase(req))
	{
		ArrayList<Defaultersbean> defaulterlist1=new ArrayList<Defaultersbean>();
	System.out.println("in cd status change");
	
	try {
		String [] id=request.getParameterValues("radio1"); 
		ArrayList<Integer> transIds=new ArrayList<Integer>();
		  for(int i=0;i<id.length;i++)
			  transIds.add(Integer.parseInt(id[i]));
		  FineService fineService= new FineService();
		
	
		  defaulterlist1= fineService.searchDefaulters1(Integer.parseInt((String) session1.getAttribute("MemId")), "cd",transIds);
		
		
		System.out.println("defaulter list size is "+defaulterlist1.size());
			if(defaulterlist1.size()==0)
			{		
				LibrarianService libService=new LibrarianService();
				for(Integer id1:transIds)
				{
				libService.updateStatus(id1);
				}
				 RequestDispatcher rd = request.getRequestDispatcher("html/ReturnOptions1.jsp");
				 rd.forward(request, response);
			}else{
				request.setAttribute("defaulter",defaulterlist1);
				
				request.getRequestDispatcher("./jsp/DefaulterPage.jsp").forward(request, response);
				
			}
		

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	else if("magstatuschange".equalsIgnoreCase(req))
	{ArrayList<Defaultersbean> defaulterlist1=new ArrayList<Defaultersbean>();
	System.out.println("in magazine status change");
	
	try {
		String [] id=request.getParameterValues("radio1"); 
		ArrayList<Integer> transIds=new ArrayList<Integer>();
		  for(int i=0;i<id.length;i++)
			  transIds.add(Integer.parseInt(id[i]));
		  FineService fineService= new FineService();
		
	
		  defaulterlist1= fineService.searchDefaulters1(Integer.parseInt((String) session1.getAttribute("MemId")), "magazine",transIds);
		
		
		System.out.println("defaulter list size is "+defaulterlist1.size());
			if(defaulterlist1.size()==0)
			{		
				LibrarianService libService=new LibrarianService();
				for(Integer id1:transIds)
				{
				libService.updateStatus(id1);
				}
				 RequestDispatcher rd = request.getRequestDispatcher("html/ReturnOptions1.jsp");
				 rd.forward(request, response);
			}else{
				request.setAttribute("defaulter",defaulterlist1);
				
				request.getRequestDispatcher("./jsp/DefaulterPage.jsp").forward(request, response);
				
			}
		

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	else if("jourstatuschange".equalsIgnoreCase(req))
	{
		ArrayList<Defaultersbean> defaulterlist1=new ArrayList<Defaultersbean>();
		System.out.println("in journal status change");
		
		try {
			String [] id=request.getParameterValues("radio1"); 
			ArrayList<Integer> transIds=new ArrayList<Integer>();
			  for(int i=0;i<id.length;i++)
				  transIds.add(Integer.parseInt(id[i]));
			  FineService fineService= new FineService();
			
		
			  defaulterlist1= fineService.searchDefaulters1(Integer.parseInt((String) session1.getAttribute("MemId")), "journal",transIds);
			
			
			System.out.println("defaulter list size is "+defaulterlist1.size());
				if(defaulterlist1.size()==0)
				{		
					LibrarianService libService=new LibrarianService();
					for(Integer id1:transIds)
					{
					libService.updateStatus(id1);
					}
					 RequestDispatcher rd = request.getRequestDispatcher("html/ReturnOptions1.jsp");
					 rd.forward(request, response);
				}else{
					request.setAttribute("defaulter",defaulterlist1);
					
					request.getRequestDispatcher("./jsp/DefaulterPage.jsp").forward(request, response);
					
				}
			

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
		   	if(request.getParameter("req").equals("admin"))
			{
		   	 if((request.getParameter("button")).equals("Pay"))
			 {
				
		   		ArrayList<Defaultersbean> defList=new ArrayList<Defaultersbean>();
					HttpSession sessiond=request.getSession();
					double total=0;
					String[] a=request.getParameterValues("check");
					defList=(ArrayList<Defaultersbean>)session1.getAttribute("deflist");
					ArrayList<FineBean> fineBeanList = new ArrayList<FineBean>();
					FineService fineService=new FineService();
				
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
					request.getRequestDispatcher("./jsp/Payment1.jsp").forward(request, response);
			
			}
		   	 if((request.getParameter("button")).equals("Cancel"))
			 {
				
		    	ArrayList<Defaultersbean> defList=new ArrayList<Defaultersbean>();
		    	ArrayList<FineBean> fineBeanList=new ArrayList<FineBean>();
				 	 HttpSession sessiond=request.getSession();
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
					request.getRequestDispatcher("./jsp/CancelPage1.jsp").forward(request, response);
				 
			 }
	}
		
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
	
		response.sendRedirect("jsp/ReturnCancelSuccessful.jsp");
		//request.getRequestDispatcher("../Searchdefaulterslist.html").forward(request, response);
		}
		}
	catch(Exception e)
	{
		response.sendRedirect("GuestHome.html");
	}
}

} //end class ReturnServlet
