package com.tcs.ilp.lms.bean;

public class UserBean 
{
	int userId;
	String password;
	String firstName;
	String lastName;
	long contact;
	String identityId;
	String email;
	String address;
	String role;
	
	public String getRole() 
	{
		return role;
	}

	public void setRole(String role) 
	{
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserBean(int userId, String password, String fName,String lName, String email, long contact, String address,String role,String identityId)
	{
		this.userId = userId;
		this.password = password;
		this.setFirstName(fName);
		this.setLastName(lName);
		this.setEmail(email);
		this.setContact(contact);
		this.setAddress(address);
		this.setRole(role);
		this.setIdentityId(identityId);
	}
	
	public UserBean()
	{
		
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public String getLastName() 
	{
		return lastName;
	}
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public long getContact() 
	{
		return contact;
	}
	public void setContact(long contact) 
	{
		this.contact = contact;
	}
	public String getAddress() 
	{
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	public String getIdentityId() 
	{
		return identityId;
	}
	public void setIdentityId(String identityId) 
	{
		this.identityId = identityId;
	}	
}
