package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Levitate 
{
	//Self-Levitation, cancel fall speed
	public static void onRightClick(Player p) 
	{
		//If not dead, float
		if(GemHelper.cast(p.getInventory(), 1))
		{
			Vector lev = p.getVelocity();
			//Levitate down
			if(p.isSneaking())
			{
				p.setVelocity(new Vector(lev.getX(),-0.2,lev.getZ()));
				p.setFallDistance(0);
			}
			//Levitate up
			else
			{
				p.setVelocity(new Vector(lev.getX(),0.2,lev.getZ()));
				p.setFallDistance(0);
			}
		}
	}

}
