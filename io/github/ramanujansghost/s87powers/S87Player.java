package io.github.ramanujansghost.s87powers;
import java.util.ArrayList;
import java.util.UUID;

public class S87Player 
{
	private UUID id;
	private ArrayList<Power> powers;
	
	public S87Player(UUID u, ArrayList<Power> powers)
	{
		id = u;
		this.powers = powers;
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

}
