package io.github.ramanujansghost.s87powers;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

public class Peltast 
{
	public void onRightClick(Player p)
	{
		if(p.getInventory().getItemInMainHand().getType() == Material.ARROW)
		{
			if(p.getInventory().getItemInMainHand().getAmount() > 1)
			{
				p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
			}
			else if(p.getInventory().getItemInMainHand().getAmount() == 1)
			{
				p.getInventory().setItemInMainHand(null);
				p.updateInventory();
			}
			Vector vel = p.getLocation().getDirection();
			Projectile arrow = p.launchProjectile(Arrow.class);
			arrow.setVelocity(vel);
		}
	}

}
