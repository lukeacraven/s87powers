package io.github.ramanujansghost.s87powers;

public class Power 
{
	private String name;
	private String desc;
	private int cost;
	
	public Power(String name, String desc, int cost)
	{
		this.name = name;
		this.desc = desc;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
