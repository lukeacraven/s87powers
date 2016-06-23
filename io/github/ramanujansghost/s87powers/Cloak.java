package io.github.ramanujansghost.s87powers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Cloak 
{
	public static void onRightClick(Player p) 
	{
			for(Player onP: Bukkit.getOnlinePlayers())
			{
				p.hidePlayer(onP);
			}
	}

}
