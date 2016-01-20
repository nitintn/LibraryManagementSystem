package com.tcs.ilp.lms.service;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tcs.ilp.lms.bean.RackBean;
import com.tcs.ilp.lms.dao.RackDao;
import com.tcs.ilp.lms.servlet.RackServlet;
/**
 * service class for managing rack
 * @author 739366
 *
 */
public class RackService {
	private static Logger staffLogger=Logger.getLogger("RackService.class");
	RackDao daoObject=new RackDao();
	/**
	 * addBookToRack takes rackbean object and response object
	 * its update the contents of database tables with given values
	 * @param obj
	 * @param response
	 * @throws IOException 
	 */
	public void addItemToRack(RackBean obj, HttpServletResponse response) throws IOException{
		//adding item to rack. passing to dao class update method
		staffLogger.info("RACK SERVICE addBookToRack method: ");
		daoObject=new RackDao();
		daoObject.update(obj,response);
	}


	public void populateList(String rackNo, HttpServletResponse response, int i) throws IOException {
		// TODO Auto-generated method stub

		daoObject.populateListDao(rackNo,response,i);
	}

	public List<RackBean> viewContents(String param, int startPageIndex, int numRecordsPerPage) {

		return daoObject.viewRackContents(param,startPageIndex,numRecordsPerPage);
		// TODO Auto-generated method stub

	}

	public int getRackCount(String inParameter) {
		// TODO Auto-generated method stub
		return daoObject.getRackCountDB(inParameter);
	}

	public void rackAjaxAuto(String param, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		daoObject.rackAjaxAutoDB(param,response);
	}

	public void deleteItem(int param,HttpServletResponse response) throws SQLException, IOException{
		daoObject.rackDeleteItem(param,response);
	}

	public boolean rackAjaxCategory(String userId1) {
		// TODO Auto-generated method stub
		return daoObject.rackAjaxCategoryDB(userId1);
	}


}
