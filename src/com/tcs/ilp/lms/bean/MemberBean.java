package com.tcs.ilp.lms.bean;
public class MemberBean extends UserBean
{
	String memberType;
	
	public MemberBean(int userId, String password, String fName,String lName, String email, long contact, String address,String role,String identityId,String memberType)
{
		super(userId,password,fName,lName,email,contact,address,role,identityId);
		this.setMemberType(memberType);
	}
	
	public MemberBean()
	{
		super();
	}

	public String getMemberType() 
	{
		return memberType;
	}

	public void setMemberType(String memberType) 
	{
		this.memberType = memberType;
	}
}
