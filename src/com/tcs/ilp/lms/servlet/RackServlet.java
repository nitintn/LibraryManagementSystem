package com.tcs.ilp.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.tcs.ilp.lms.bean.RackBean;


import com.tcs.ilp.lms.service.RackService;

/**
 * @author 739366
 * Servlet implementation class Servlet
 *
 */
public class RackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Logger for log4j
	private static Logger staffLogger=Logger.getLogger("RackServlet.class");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RackServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * service method takes request and response 
	 * whenever the form calls the servlet service method executes
	 * This service method differentiate the form requests using the hidden element in the html form.
	 */

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		staffLogger.info("RACK SERVLET service method");
		//set response content type as JSON
		response.setContentType("text/json");
		//creation of printwriter object for sent back the response to client
		PrintWriter out=response.getWriter();
		RackService serviceObj=new RackService();
		RackBean beanObj=new RackBean();
		try{
			String page = request.getParameter("page");
			if("CheckAvailablityForAdminUserId".equalsIgnoreCase(page))
			{
				response.setContentType("text/html;charset=UTF-8");
				boolean userIdAvailable = false;
				String userId1 = request.getParameter("userId");
				userIdAvailable=serviceObj.rackAjaxCategory(userId1);
				if(userIdAvailable){
					out.println("Category Available");								
				}
				else{
					out.println("Category already present");
				}	
			}
			String param=request.getParameter("q");			
			if(param!=null){
				serviceObj.rackAjaxAuto(param,response);
			}
			else
			{
				staffLogger.info("No parameter is sent from rackHomeView.jsp");
			}
			if(request.getParameter("action1")!=null){
				String inParameter=(String)request.getParameter("action1");
				String action=inParameter.substring(0, 4);
				int startPageIndex=Integer.parseInt(request.getParameter("jtStartIndex"));
				int numRecordsPerPage=Integer.parseInt(request.getParameter("jtPageSize"));
				//Get Total Record Count for Pagination
				int userCount=serviceObj.getRackCount(inParameter);
				if(action.contains("list")){
					System.out.println("this is rackhomeview.jsp page and action");
					List<RackBean> rackContent=new ArrayList<RackBean>();
					Gson gson = new Gson();
					response.setContentType("application/json");
					//Fetch Data from User Table
					rackContent=serviceObj.viewContents(inParameter,startPageIndex,numRecordsPerPage); 
					//Convert Java Object to Json    
					JsonElement element = gson.toJsonTree(rackContent, new TypeToken<List<RackBean>>() {}.getType());
					JsonArray jsonArray = element.getAsJsonArray();
					String listData=jsonArray.toString();    
					//Return Json in the format required by jTable plugin
					listData="{\"Result\":\"OK\",\"Records\":"+listData+",\"TotalRecordCount\":"+userCount+"}";   
					response.getWriter().print(listData);
				}
			}
			//this is for rackhomeView.jsp
			String action = request.getParameter("action");
			String json = request.getParameter("json");
			JSONObject jsonData = (JSONObject) JSONValue.parse(json);
			String formID=(String)jsonData.get("formID");
			if(formID.equals("rackHomeAdd")){
				System.out.println("rackHomeAdd");
				String category=(String)jsonData.get("category");
				String subCategory=(String)jsonData.get("subCategory");
				String rackNo=(String)jsonData.get("rackNo");
				String shelfNo=(String)jsonData.get("shelfNo");
				String itemNo=(String)jsonData.get("itemNo");
				String itemID=(String)jsonData.get("itemID");
				beanObj.setCategory(category);
				beanObj.setSubCategory(subCategory);
				beanObj.setRackNo(rackNo);
				beanObj.setShelfNo(shelfNo);
				beanObj.setItemNo(itemNo);
				beanObj.setItemID(itemID);
				//adding item to rack. passing to service class addbooktorack method
				serviceObj.addItemToRack(beanObj,response);
			}
			else if(formID.equals("rackHomeAddSelectShelf")){
				String rackNo=(String)jsonData.get("rackNo");
				serviceObj.populateList(rackNo,response,1);
			}
			else if(formID.equals("rackHomeAddSelectItem")){
				String rackNo=(String)jsonData.get("rackNo");
				serviceObj.populateList(rackNo,response,2);
			}
			//if none of the above buttons clicked 
			else
				staffLogger.info("RACK SERVLET there is no button is clicked so there is nothing to do");

		} catch (Exception ex) {
			//out.println("{\"message\":\"Error - caught exception in ExportServlet\"}");
		} finally {
			out.flush();
			out.close();
		}
	}
}