package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Player;

public class PlayerHelper
{	
	//Apply damage to player and kill player if damage exceeds their health
	public static void damagePlayer(Player p, String damageSource,
			int damageAmount)
	{
		if (p.getHealth() <= damageAmount)
		{
			// Do stuff here if player is killed by damage!!
			p.damage(damageAmount);
		}
		else
			p.damage(damageAmount);
	}
}
