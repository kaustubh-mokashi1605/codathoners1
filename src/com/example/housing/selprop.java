package com.example.housing;

public class selprop 
{
	private String location,owner_id,rate,area,cat;
	
	selprop(String loc,String own,String rat,String ar,String category)
	{
		location=loc;
		owner_id=own;
		rate=rat;
		area=ar;
		cat=category;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public String getOwner()
	{
		return owner_id;
	}
	
	public String getRate()
	{
		return rate;
	}
	
	public String getArea()
	{
		return area;
	}
	
	public String getCategory()
	{
		return cat;
	}
}