package io.github.ramanujansghost.s87powers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Cloak 
{
	//Not Ready for Release... Need to Stealthing method
	public void onRightClick(Player p) 
	{
			for(Player onP: Bukkit.getOnlinePlayers())
			{
				onP.hidePlayer(p);
			}
	}

}
