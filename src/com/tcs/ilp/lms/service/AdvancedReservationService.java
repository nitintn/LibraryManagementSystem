package com.tcs.ilp.lms.service;

import java.util.ArrayList;

import com.tcs.ilp.lms.bean.AdvancedReservationBean;
import com.tcs.ilp.lms.dao.AdvancedReservationDao;

public class AdvancedReservationService 
{
	
public ArrayList<AdvancedReservationBean> searchItem(AdvancedReservationBean AdvancedReservationBeanObj)
{
	AdvancedReservationDao AdvancedReservationDaoObj = new AdvancedReservationDao();
	ArrayList<AdvancedReservationBean> advancedReservationSearchList = new ArrayList<AdvancedReservationBean>();
	advancedReservationSearchList = AdvancedReservationDaoObj.searchItem(AdvancedReservationBeanObj);
	return advancedReservationSearchList;	
}

public int advancedReservation(AdvancedReservationBean AdvancedReservationBeanObj)
{
	int reservationNo=0;
	System.out.println(AdvancedReservationBeanObj.getUserId()+" ");
	AdvancedReservationDao AdvancedReservationDaoObj = new AdvancedReservationDao();
	reservationNo=AdvancedReservationDaoObj.advancedReservation(AdvancedReservationBeanObj);
	return reservationNo;
}
}