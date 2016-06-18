package io.github.ramanujansghost.s87powers;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Translocation 
{
	public static void onRightClick(Player p) 
	{
		//SWAP!
		LivingEntity target = PlayerHelper.getTarget(p, 30);
		if(target != null)
		{
			Location me = p.getLocation();
			Location you = target.getLocation();
			p.teleport(you);
			target.teleport(me);
		}
		}

}
