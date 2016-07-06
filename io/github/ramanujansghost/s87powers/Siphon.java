package io.github.ramanujansghost.s87powers;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Siphon{
	
	//Damage entity over time, add value to soulgem
	//Scale damage and power gained by amount (in hearts/foods)
	//Amount = Power
	public static int extract(Entity target, Player p, int amount)
	{		
		
		{
			LivingEntity le = (LivingEntity)target;
			
			//If player, take food first, then life (2 food/health = 14 power)
			//Rounding up
			if(le.getType() == EntityType.PLAYER)
			{
				p = (Player)le;
				int food = p.getFoodLevel();
				if((food-(int)(Math.ceil((double)amount/7))) < 0)
				{
					p.setFoodLevel(0);	
					p.damage((int)(Math.ceil((double)amount/7))-food);
				}
				else
				{
					p.setFoodLevel(food-(int)(Math.ceil((double)amount/7)));
				}
				
				if(le == p)
				{
					//If using regen-potion, rebound
					for(PotionEffect efx : p.getActivePotionEffects())
					{
						System.out.println(efx.getType());
						if(efx.getType().getName() == PotionEffectType.REGENERATION.getName())
						{
							p.sendMessage("REBOUND!");
							p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 500, 1));
						}
					}
				}
			}
			else
			{
				le.damage((Math.ceil((double)amount/7)), p);
			}			
				return amount;
			}
	}
	
	//Empower the gem in the hand a of a player
	public static void onRightClick(Entity target, Player p, int amount)
	{	
		EntityType type = target.getType();
		if(type == EntityType.SHEEP || type == EntityType.COW || type == EntityType.WOLF || type == EntityType.PIG || type == EntityType.PLAYER || type == EntityType.RABBIT || type == EntityType.VILLAGER || type == EntityType.SQUID || type == EntityType.HORSE || type == EntityType.CHICKEN)
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
		else
		{
			p.sendMessage("You can't siphon from that!");
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
			return true;
		}
		return false;
	}

}
