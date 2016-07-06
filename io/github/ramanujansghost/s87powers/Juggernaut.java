package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Player;

//Not ready atm, needs bool in database or something. Although, I think it lasts beyond reloads.
public class Juggernaut 
{
	public static void onRightClick(Player p) 
	{
			p.setMaxHealth(40);
	}

}
