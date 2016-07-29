package io.github.ramanujansghost.s87powers;
import java.util.ArrayList;
import java.util.UUID;

public class S87Player 
{
	private UUID id;
	private ArrayList<Power> powers = new ArrayList<Power>();
	private ArrayList<String> Stati = new ArrayList<String>();
	
	public S87Player(UUID u, ArrayList<Power> powers, ArrayList<String> Stati)
	{
		id = u;
		this.powers = powers;
		this.Stati = Stati;
	}
	
	public S87Player(UUID u)
	{
		id = u;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ArrayList<Power> getPowers() {
		return powers;
	}

	public void setPowers(ArrayList<Power> powers) {
		this.powers = powers;
	}

	public ArrayList<String> getStati() {
		return Stati;
	}

	public void setStati(ArrayList<String> stati) {
		Stati = stati;
	}
	
	public void addStatus(String e) {
		Stati.add(e);
	}
	
	public void removeStatus(String e) {
		Stati.remove(e);
	}

}
