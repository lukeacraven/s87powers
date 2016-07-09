package io.github.ramanujansghost.s87powers;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Volley 
{
	ItemStack arrow = new ItemStack(Material.ARROW, 1);
	
	//Check if cooldown has elapsed
	private boolean canPlayerUseVolley(Player p)
	{
		if (S87Powers.timeSinceVolleyUse.containsKey(p.getUniqueId()))
		{
			Long timeSinceEpoch = S87Powers.timeSinceVolleyUse
					.get(p.getUniqueId());
			if ((System.currentTimeMillis() - timeSinceEpoch) > 5000) // cooldown
																		// of 5
																		// seconds
			{ return true; }
			p.sendMessage(
					"You have used volley too recently!  Wait "
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
			if (InventoryHelper.checkForReagents(p, arrow, 7))
			{
				if (canPlayerUseVolley(p))
				{
					Vector velocity = event.getProjectile().getVelocity();
					double speed = velocity.length();
					Vector direction = new Vector(velocity.getX() / speed, velocity.getY() / speed, velocity.getZ() / speed);
					// you can tune the following value for different spray. Higher number means less spray.
					double spray = 4.5D;
					event.setCancelled(false);
					S87Powers.timeSinceVolleyUse.put(p.getUniqueId(),
							System.currentTimeMillis());
					InventoryHelper.removeReagents(p, arrow, 7);
					for(int x = 0; x < 7; x ++)
					{
						Projectile arrow1 = p
								.launchProjectile(Arrow.class);
						arrow1.setVelocity(new Vector(direction.getX() + (Math.random() - 0.5) / spray, direction.getY() + (Math.random() - 0.5) / spray, direction.getZ() + (Math.random() - 0.5) / spray).normalize().multiply(speed));
					}

				}
			}
			else
			{
				p.sendMessage("You tried to use Volley, not enough arrows!");
			}
	}

}
