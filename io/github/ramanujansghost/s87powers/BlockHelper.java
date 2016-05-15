package io.github.ramanujansghost.s87powers;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class BlockHelper 
{
	//Returns list of open adj. blocks
	public static ArrayList<Block> findAir(Block b)
	{
		ArrayList<Block> airs = new ArrayList<Block>();
		if(b.getRelative(BlockFace.UP).getType() != Material.AIR)
		{
			airs.add(b.getRelative(BlockFace.UP));
		}
		if(b.getRelative(BlockFace.DOWN).getType() != Material.AIR)
		{
			airs.add(b.getRelative(BlockFace.DOWN));
		}
		if(b.getRelative(BlockFace.NORTH).getType() != Material.AIR)
		{
			airs.add(b.getRelative(BlockFace.NORTH));
		}
		if(b.getRelative(BlockFace.SOUTH).getType() != Material.AIR)
		{
			airs.add(b.getRelative(BlockFace.SOUTH));
		}
		if(b.getRelative(BlockFace.EAST).getType() != Material.AIR)
		{
			airs.add(b.getRelative(BlockFace.EAST));
		}
		if(b.getRelative(BlockFace.WEST).getType() != Material.AIR)
		{
			airs.add(b.getRelative(BlockFace.WEST));
		}
		
		return airs;
	}

}
