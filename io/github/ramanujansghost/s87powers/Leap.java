package io.github.ramanujansghost.s87powers;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Leap 
{
	//Initiate jumping in the direction you are pointing
	public static void onRightClick(Player p) 
	{
		Material below = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
		//If you're on the ground and have more than 6 food, jump
		if(below != Material.AIR && below != Material.STATIONARY_WATER && p.getFoodLevel() > 6)
		{
			if(p.getLocation().getBlock().getType() == Material.STATIONARY_WATER || p.getLocation().getBlock().getType() == Material.WATER)
			{
				Vector vel = p.getLocation().getDirection();
				p.setVelocity(new Vector(vel.getX()*1.5, vel.getY()*1.1, vel.getZ()*1.5));
				p.setFoodLevel(p.getFoodLevel()-1);
			}
			else
			{
				Vector vel = p.getLocation().getDirection();
				p.setVelocity(new Vector(vel.getX()*2, vel.getY()*2, vel.getZ()*2));
				p.setFoodLevel(p.getFoodLevel()-1);
			}
		}		
	}

}
