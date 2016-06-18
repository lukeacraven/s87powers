package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Levitate 
{
	public static void onRightClick(Player p) 
	{
		Vector lev = p.getVelocity();
		//Pull
		if(p.isSneaking())
		{
			p.setVelocity(new Vector(lev.getX(),-0.2,lev.getZ()));
			p.setFallDistance(0);
		}
		else
		{
			p.setVelocity(new Vector(lev.getX(),0.2,lev.getZ()));
			p.setFallDistance(0);
		}
	}

}
