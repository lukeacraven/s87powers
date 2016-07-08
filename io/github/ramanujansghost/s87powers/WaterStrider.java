package io.github.ramanujansghost.s87powers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

public class WaterStrider 
{
	//Press and release shift to jet forward in water
	public void onShift(PlayerToggleSneakEvent e)
	{
		Player p = e.getPlayer();
		if(p.isSneaking() && (p.getLocation().getBlock().getType() == Material.WATER || p.getLocation().getBlock().getType() == Material.STATIONARY_WATER))
		{
			Vector vel = p.getLocation().getDirection();
			p.setVelocity(new Vector(vel.getX()*2,vel.getY()*1.3,vel.getZ()*2));
		}
	}

}
