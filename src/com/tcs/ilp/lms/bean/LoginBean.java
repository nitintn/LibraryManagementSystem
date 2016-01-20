package com.tcs.ilp.lms.bean;

public class LoginBean 
{
	private int userName;
	private String password;
	
	public LoginBean(int userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}

	public int getUserName() 
	{
		return userName;
	}
	public void setUserName(int userName) 
	{
		this.userName = userName;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
}
