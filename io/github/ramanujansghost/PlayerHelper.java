package io.github.ramanujansghost;

import org.bukkit.entity.Player;

public class PlayerHelper
{
	public static void damagePlayer(Player p, int damageAmount)
	{
		p.damage(damageAmount);
	}
}
