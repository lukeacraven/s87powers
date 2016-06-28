package io.github.ramanujansghost.s87powers;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class GateBuilder 
{
	public static void onRightClick(Player p)
	{
		System.out.println("Begin Gate");
		Block center = p.getTargetBlock((Set<Material>)null, 4);
		if(center != null && center.getType() == Material.OBSIDIAN )
		{
			Block portalBot = center.getRelative(0, 1, 0);
			Block portalTop = center.getRelative(0, 2, 0);
			if(portalBot.getType() == Material.AIR && portalTop.getType() == Material.AIR)
			{
				S87Powers.slipGates.add(portalBot);
				portalBot.setType(Material.ENDER_PORTAL);
				portalTop.setType(Material.ENDER_PORTAL);
				for(Block b : S87Powers.slipGates)
				{
					if(b.getX() == portalBot.getX())
					{
						if(b.getY() == portalBot.getY())
						{
							
						}
						else if(b.getZ() == portalBot.getZ())
						{
							
						}
					}
				}
			}
			
		}
	}
	public static void createGate(Location loc)
	{
		
		
	}
	
	public static boolean checkShape(Block start)
	{
		if(start.getRelative(BlockFace.NORTH).getType() == Material.ITEM_FRAME)
		{
			
		}
		else if((start.getRelative(BlockFace.SOUTH).getType() == Material.ITEM_FRAME))
		{
			
		}
		else if((start.getRelative(BlockFace.EAST).getType() == Material.ITEM_FRAME))
		{
			//Block[] frame = [start, start.getRelative(1,0,0)]
		}
		else if((start.getRelative(BlockFace.WEST).getType() == Material.ITEM_FRAME))
		{
			//Set<Block> frame = {
		}
		
		
		return false;
	}

}
