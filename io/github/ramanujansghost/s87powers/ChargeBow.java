package io.github.ramanujansghost.s87powers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

public class ChargeBow extends Power
{
	ItemStack charge = new ItemStack(Material.FIREBALL, 1);
	ItemStack arrow = new ItemStack(Material.ARROW, 1);
	
	public ChargeBow()
	{
		name = "Charge Bow";
		type = "Combat";
		description = "This power allows the player to shoot powerful fireballs from their bow at a cost of 1 fire charge per shot.";
	}

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
					InventoryHelper inventoryHelper = new InventoryHelper();
					inventoryHelper.removeReagents(p, charge, 1);
					inventoryHelper.removeReagents(p, arrow, 1);
					Projectile fireball1 = p
							.launchProjectile(SmallFireball.class);
					fireball1.setVelocity(event.getEntity().getVelocity());
					Projectile fireball2 = p
							.launchProjectile(SmallFireball.class);
					fireball2.setVelocity(event.getEntity().getVelocity());
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
