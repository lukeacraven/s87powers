package io.github.ramanujansghost;

import org.bukkit.entity.Player;

public class PlayerHelper
{
	public static void damagePlayer(Player p, String damageSource, int damageAmount)
	{
		if(p.getHealth() <= damageAmount)
		{
			// Do stuff here if player is killed by damage!!
			p.damage(damageAmount);
		}
		else
			p.damage(damageAmount);
	}
}
