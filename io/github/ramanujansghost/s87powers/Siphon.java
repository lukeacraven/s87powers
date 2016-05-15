package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
	//Scale damage and power gained by amount (in hearts/foods)
	public int extract(Entity target, Player p, int amount)
	{		
		target.setGlowing(true);
		LivingEntity le = (LivingEntity)target;
		
		//If player, take food first, then life (2 food/health = 13 power)
		if(le.getType() == EntityType.PLAYER)
		{
			p = (Player)le;
			int food = p.getFoodLevel();
			if(food-2*amount < 0)
			{
				p.setFoodLevel(0);	
				p.damage((2*amount)-food);
			}
			else
			{
				p.setFoodLevel(food-(2*amount));
			}
		}
		else
		{
			le.damage(2 * amount);
		}			
		return 13*amount;
	}
	
	//Empower the gem in the hand a of a player
	public void onRightClick(Entity target, Player p, int amount)
	{		
		PlayerInventory inv = p.getInventory();
		GemHelper.addPower(inv, inv.getHeldItemSlot(), extract(target, p, amount));		
	}

}
