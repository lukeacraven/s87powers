package io.github.ramanujansghost.s87powers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Shell 
{
	@SuppressWarnings("null")
	public static void onRightClick(Player p) 
	{
		Location loc = null;
		if(p.isSneaking())
		{
			loc = p.getLocation();
			
		}
		else
		{
			LivingEntity le = PlayerHelper.getTarget(p, 8);
			if(le != null)
			{
				loc = le.getLocation();
			}
			
		}
		Block center = loc.getBlock();
		ArrayList<Block> toChange = new ArrayList<Block>();
		toChange.add(center.getRelative(BlockFace.NORTH_EAST));
		toChange.add(center.getRelative(BlockFace.NORTH_WEST));
		toChange.add(center.getRelative(BlockFace.SOUTH_EAST));
		toChange.add(center.getRelative(BlockFace.SOUTH_WEST));
		toChange.add(center.getRelative(BlockFace.EAST));
		toChange.add(center.getRelative(BlockFace.NORTH));
		toChange.add(center.getRelative(BlockFace.SOUTH));
		toChange.add(center.getRelative(BlockFace.WEST));
		toChange.add(center.getRelative(BlockFace.EAST).getRelative(0, 1, 0));
		toChange.add(center.getRelative(BlockFace.NORTH).getRelative(0, 1, 0));
		toChange.add(center.getRelative(BlockFace.SOUTH).getRelative(0, 1, 0));
		toChange.add(center.getRelative(BlockFace.WEST).getRelative(0, 1, 0));
		toChange.add(center.getRelative(0, 2, 0));
		BlockHelper.placeInEmpty(p, toChange, Material.STONE);
	}

}
