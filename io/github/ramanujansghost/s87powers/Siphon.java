package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.Material;
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
	public static int extract(Entity target, Player p, int amount)
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
	public static void onRightClick(Entity target, Player p, int amount)
	{		
		if(canSiphon(p))
		{
			System.out.println("Can Siphon");
			PlayerInventory inv = p.getInventory();
			GemHelper.addPower(inv, inv.getHeldItemSlot(), extract(target, p, amount));
			Material gem = inv.getItemInMainHand().getType(); 
			if(gem == Material.DIAMOND)
			{
				S87Powers.timeTillSiphonAgain.put(p.getUniqueId(), System.currentTimeMillis()+ 8000);
			}
			else if(gem == Material.EMERALD)
			{
				S87Powers.timeTillSiphonAgain.put(p.getUniqueId(), System.currentTimeMillis()+ 4000);
			}
			else if(gem == Material.QUARTZ)
			{
				S87Powers.timeTillSiphonAgain.put(p.getUniqueId(), System.currentTimeMillis()+ 2000);
			}
		}
	}
	
	private static boolean canSiphon(Player p)
	{
		if (p != null)
		{
			System.out.println("Player is not null");
			if (S87Powers.timeTillSiphonAgain.containsKey(p.getUniqueId()))
			{
				System.out.println("Unique ID Met");
				Long coolDown = S87Powers.timeTillSiphonAgain.get(p.getUniqueId());
				if (System.currentTimeMillis() > coolDown)
					return true;
				else
				{
					p.sendMessage("You have used siphon too recently!  Wait "
							+ (coolDown - (System.currentTimeMillis())) / 1000 + " more seconds.");
					return false;
				}
			}
		}
		return false;
	}

}
