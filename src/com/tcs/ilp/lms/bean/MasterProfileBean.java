package com.tcs.ilp.lms.bean;

public class MasterProfileBean 
{
	private String memberType;
	private int numberOfBook;
	private int numberOfCd;
	private int numberOfMagazine;
	private int numberOfJournal;
	private float cost;
	
	public MasterProfileBean(String memberType,int noOfBooks,int noOfCds,int noOfMagazines,int noOfJournals,float cost)
	{
		this.memberType = memberType;
		this.numberOfBook = noOfBooks;
		this.numberOfCd = noOfCds;
		this.numberOfMagazine = noOfMagazines;
		this.numberOfJournal = noOfJournals;
		this.cost = cost;
	}
	
	public MasterProfileBean() {
		// TODO Auto-generated constructor stub
	}

	public String getMemberType() 
	{
		return memberType;
	}
	public void setMemberType(String memberType) 
	{
		this.memberType = memberType;
	}
	public int getNumberOfBook() 
	{
		return numberOfBook;
	}
	public void setNumberOfBook(int numberOfBook) 
	{
		this.numberOfBook = numberOfBook;
	}
	public int getNumberOfCd() 
	{
		return numberOfCd;
	}
	public void setNumberOfCd(int numberOfCd) 
	{
		this.numberOfCd = numberOfCd;
	}
	public int getNumberOfMagazine()
	{
		return numberOfMagazine;
	}
	public void setNumberOfMagazine(int numberOfMagazine) 
	{
		this.numberOfMagazine = numberOfMagazine;
	}
	public int getNumberOfJournal() 
	{
		return numberOfJournal;
	}
	public void setNumberOfJournal(int numberOfJournal) 
	{
		this.numberOfJournal = numberOfJournal;
	}

	public void setCost(float cost) 
	{
		this.cost = cost;
	}

	public float getCost() 
	{
		return cost;
	}
	
}
