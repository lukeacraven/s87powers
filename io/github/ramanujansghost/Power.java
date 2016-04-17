package io.github.ramanujansghost;

public class Power
{
	public String name;
	public String description;
	public String reagents;
	
	public Power()	// Do not use this constructor -- use the constructor with a parameter list
	{
		name = "EMPTY";
		description = "EMPTY";
		reagents = "EMPTY";
	}
	
	public Power(String name, String description, String reagents)
	{
		this.name = name;
		this.description = description;
		this.reagents = reagents;
	}
	
	/*	These setters are commented out because they shouldn't be necessary
	public void setName(String str)
	{
		name = str;
	}
	public void setDescription(String str)
	{
		description = str;
	}
	public void setReagents(String str)
	{
		reagents = str;
	}
	*/
	
	public String getName()
	{
		return name;
	}
	public String getDescription()
	{
		return description;
	}
	public String getReagents()
	{
		return reagents;
	}
}
