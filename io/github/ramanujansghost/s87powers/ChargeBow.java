package io.github.ramanujansghost.s87powers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

public class ChargeBow
{
	ItemStack charge = new ItemStack(Material.FIREBALL, 1);
	ItemStack arrow = new ItemStack(Material.ARROW, 1);
	
	//Constructor
	public ChargeBow()
	{

	}
	
	//Check if cooldown has elapsed
	private boolean canPlayerUseChargeBow(Player p)
	{
		if (S87Powers.timeSinceChargeBowUse.containsKey(p.getUniqueId()))
		{
			Long timeSinceEpoch = S87Powers.timeSinceChargeBowUse
					.get(p.getUniqueId());
			if ((System.currentTimeMillis() - timeSinceEpoch) > 5000) // cooldown
																		// of 5
																		// seconds
			{ return true; }
			p.sendMessage(
					"You have used charge bow too recently!  Wait "
							+ (5000 - (System.currentTimeMillis()
									- timeSinceEpoch)) / 1000
							+ " more seconds.");
			return false;
		}
		return true; // in this case, player has never shot a charge
	}
	
	//When firing a bow, if the player has the reagents, launch two fireballs
	
	public void onBowShootEvent(EntityShootBowEvent event)
	{
		Player p = (Player) event.getEntity();
		if (InventoryHelper.checkForReagents(p, charge, 1))
		{
			if (InventoryHelper.checkForReagents(p, arrow, 1))
			{
				if (canPlayerUseChargeBow(p))
				{
					event.setCancelled(true);
					S87Powers.timeSinceChargeBowUse.put(p.getUniqueId(),
							System.currentTimeMillis());
					InventoryHelper.removeReagents(p, charge, 1);
					InventoryHelper.removeReagents(p, arrow, 1);
					Projectile fireball1 = p
							.launchProjectile(SmallFireball.class);
					fireball1.setVelocity(event.getEntity().getLocation().getDirection());
					Projectile fireball2 = p
							.launchProjectile(SmallFireball.class);
					fireball2.setVelocity(event.getEntity().getLocation().getDirection());
				}
			}
			else
			{
				p.sendMessage("You tried to use Charge Bow, no arrows!");
			}
		}
		else
		{
			p.sendMessage(
					"You tried to use Charge Bow, but you had no charges to coat your arrow with!");
		}
	}
}
