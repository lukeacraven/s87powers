package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

//Wingardium Leviosaaa
public class Leviosa
{
	//Levitate a target entity
	public static void onRightClick(Player p) 
	{
		//UP UP and AWAY
		//8 Block range
		LivingEntity target = PlayerHelper.getTarget(p, 8);
		if(target != null && GemHelper.cast(p.getInventory(), 1))
		{
			Vector up = target.getVelocity();
			target.setVelocity(new Vector(up.getX(), (.3), up.getZ()));
			target.setFallDistance(0);
		}
	}

}
