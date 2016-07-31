package io.github.ramanujansghost.s87powers;

public class Power 
{
	private int id;
	private String name;
	private String desc;
	private int cost;
	private String type;
	
	public Power(int id, String name, String desc, int cost)
	{
		this.setId(id);
		this.name = name;
		this.desc = desc;
		this.cost = cost;
	}
	
	public Power(int id, String name, String desc, int cost, String type)
	{
		this.setId(id);
		this.name = name;
		this.desc = desc;
		this.cost = cost;
		this.setType(type);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
