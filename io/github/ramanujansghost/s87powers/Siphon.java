package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public class Siphon extends Power{

	public String getName()
	{
		return "Siphon";
	}

	public String getType()
	{
		return "Utility";
	}

	public String getDescription()
	{
		//meh
		return "When a player right clicks a mob with a gem, they drain its life into the gem.";
	}
	
	public void extract(Entity target, ItemStack gem)
	{
		GemHelper.setGemPower(gem, 13);
		
	}
}
