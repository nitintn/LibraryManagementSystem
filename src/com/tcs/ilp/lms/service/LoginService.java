package com.tcs.ilp.lms.service;

import com.tcs.ilp.lms.bean.LoginBean;
import com.tcs.ilp.lms.dao.LoginDao;

public class LoginService 
{
	public int validate(LoginBean loginBean)
	{
		int check = 0;
		LoginDao loginDao = new LoginDao();
		check = loginDao.validate(loginBean);	
		return check;
	}
	
	public String getRole(LoginBean loginBean)
	{
		String role = "";
		LoginDao loginDao = new LoginDao();
		role = loginDao.getRole(loginBean);
		return role;
	}
	
	public int changePassword(String password, String cpassword, int userId)
	{
		int check = 0;
		if(password.equals(cpassword))
		{
			LoginDao loginDao = new LoginDao();
			check = loginDao.changePassword(password, userId);
		}
		else
		{
			check = 2;
		}
		return check;
	}
}
