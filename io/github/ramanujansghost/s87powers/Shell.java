package io.github.ramanujansghost.s87powers;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Shell 
{
	private static final ItemStack redstone = new ItemStack(Material.REDSTONE,1);
	private static final ItemStack stone = new ItemStack(Material.STONE,1);
	
	//create a shell of stone around target or self
	public static void onRightClick(Player p) 
	{
		Location loc = null;
		if(InventoryHelper.checkForReagents(p, stone, 13) && InventoryHelper.checkForReagents(p, redstone, 2))
		{
			if(p.isSneaking())
			{
				InventoryHelper.removeReagents(p, stone, 9);
				InventoryHelper.removeReagents(p, redstone, 2);
				loc = p.getLocation();
				
			}
			else
			{
				LivingEntity le = PlayerHelper.getTarget(p, 8);
				if(le != null)
				{
					InventoryHelper.removeReagents(p, stone, 13);
					InventoryHelper.removeReagents(p, redstone, 2);
					le.teleport((le.getLocation().getBlock().getLocation()).add(new Vector(.5,0,.5)));
					le.setVelocity(new Vector(0,0,0));
					loc = le.getLocation();
				}
				
			}
		}
		//probably should use coords
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
