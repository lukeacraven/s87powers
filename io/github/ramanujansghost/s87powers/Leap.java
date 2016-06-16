package io.github.ramanujansghost.s87powers;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Leap 
{

	public static void onRightClick(Player p) 
	{
		if(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR && p.getFoodLevel() > 6)
		{
			Vector vel = p.getLocation().getDirection();
			p.setVelocity(new Vector(vel.getX()*4, vel.getY()*2, vel.getZ()*4));
			p.setFoodLevel(p.getFoodLevel()-1);
		}
		
	}

}
