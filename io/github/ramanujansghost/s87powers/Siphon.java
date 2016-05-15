package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Damageable;
import org.bukkit.inventory.Inventory;
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
	public void extract(Entity target, Player p)
	{		
		PlayerInventory inv = p.getInventory();
		GemHelper.setGemPower(inv, inv.getHeldItemSlot(), 13+ GemHelper.getGemPower(inv, inv.getHeldItemSlot()));
		target.setGlowing(true);
		LivingEntity le = (LivingEntity)target;
		le.damage(2,p);
		//damage entity
	}

}
