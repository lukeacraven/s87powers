package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Damageable;
import org.bukkit.inventory.ItemStack;

public class Siphon extends Power{

	
	public Siphon()
	{
		name = "Siphon";
		type = "Lifeforce";
		description = "Drain the lifeforce of a being."; //meh
	}
	
	public void extract(Entity target, ItemStack gem)
	{
		GemHelper.setGemPower(gem, 13);
		target.setGlowing(true);
		//damage entity
	}
}
