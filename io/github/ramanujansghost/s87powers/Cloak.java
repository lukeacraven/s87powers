package io.github.ramanujansghost.s87powers;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Cloak 
{
	public void onRightClick(Player p) 
	{
		GemHelper gh = new GemHelper();
		if(gh.cast(p.getInventory(), 50))
		{
			for(OfflinePlayer onP: Bukkit.getOfflinePlayers())
			{
				if(onP.getPlayer() != null)
					onP.getPlayer().hidePlayer(p);
			}

			p.sendMessage("You've vanished!");
			S87Powers.allPlayers.get(p.getUniqueId()).addStatus("Cloaked");
		}
	}
	
	public void reveal(Player p) 
	{
		for(OfflinePlayer onP: Bukkit.getOfflinePlayers())
		{
			if(onP.getPlayer() != null)
				onP.getPlayer().showPlayer(p);
		}
		if(S87Powers.allPlayers.get(p.getUniqueId()).getStati().contains("Cloaked"))
		{
			p.sendMessage("You've been revealed!");
			S87Powers.allPlayers.get(p.getUniqueId()).removeStatus("Cloaked");
		}
	}

}
