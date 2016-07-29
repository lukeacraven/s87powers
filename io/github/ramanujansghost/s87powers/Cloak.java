package io.github.ramanujansghost.s87powers;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Cloak 
{
	//Not Ready for Release... Need to Stealthing method
	public void onRightClick(Player p) 
	{
		System.out.println("Cloaking!");
			for(OfflinePlayer onP: Bukkit.getOfflinePlayers())
			{
				if(onP.getPlayer() != null)
					onP.getPlayer().hidePlayer(p);
			}
//			for(Player onP: Bukkit.getOnlinePlayers())
//			{
//				onP.hidePlayer(p);
//			}
			p.sendMessage("You've vanished!");
			S87Powers.allPlayers.get(p.getUniqueId()).addStatus("Cloaked");
	}
	public void reveal(Player p) 
	{
		System.out.println("DeCloaking!");
		for(OfflinePlayer onP: Bukkit.getOfflinePlayers())
		{
			if(onP.getPlayer() != null)
				onP.getPlayer().showPlayer(p);
		}
		p.sendMessage("You've been revealed!");
		S87Powers.allPlayers.get(p.getUniqueId()).removeStatus("Cloaked");
//		for(Player onP: Bukkit.getOnlinePlayers())
//		{
//			onP.showPlayer(p);
//		}
	}

}
