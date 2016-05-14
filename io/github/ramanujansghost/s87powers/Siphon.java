package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Damageable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Siphon extends Power{

	//Constructor
	public Siphon()
	{
		name = "Siphon";
		type = "Lifeforce";
		description = "Drain the lifeforce of a being."; //meh
	}
	
	//Damage entity over time, add value to soulgem
	public void extract(Entity target, PlayerInventory inv)
	{		
		GemHelper.setGemPower(inv, inv.getHeldItemSlot(), 13);
		target.setGlowing(true);
		//damage entity
	}
}
