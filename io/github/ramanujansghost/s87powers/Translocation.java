package io.github.ramanujansghost.s87powers;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Translocation 
{
	//Swap the locations of player with target entity
	public void onRightClick(Player p) 
	{
		//SWAP!
		LivingEntity target = PlayerHelper.getTarget(p, 30);
		if(target != null)
		{
			GemHelper gh = new GemHelper();
			if(gh.cast(p.getInventory(),(int) target.getLocation().distance(p.getLocation())/2))
			{
				target.leaveVehicle();
				target.setPassenger(null);
				Location me = p.getLocation();
				Location you = target.getLocation();
				p.teleport(you.setDirection(p.getLocation().getDirection()));
				target.teleport(me.setDirection(target.getLocation().getDirection()));
			}
		}
		}

}
