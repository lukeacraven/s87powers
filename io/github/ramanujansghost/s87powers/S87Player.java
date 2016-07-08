package io.github.ramanujansghost.s87powers;
import java.util.ArrayList;
import java.util.UUID;

public class S87Player 
{
	private UUID id;
	private ArrayList<Power> powers = new ArrayList<Power>();
	private ArrayList<StatusEffect> Stati = new ArrayList<StatusEffect>();
	
	public S87Player(UUID u, ArrayList<Power> powers, ArrayList<StatusEffect> Stati)
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

	public ArrayList<StatusEffect> getStati() {
		return Stati;
	}

	public void setStati(ArrayList<StatusEffect> stati) {
		Stati = stati;
	}

}
