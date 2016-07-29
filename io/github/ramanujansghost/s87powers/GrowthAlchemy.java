package io.github.ramanujansghost.s87powers;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class GrowthAlchemy 
{
	private final ItemStack redstone = new ItemStack(Material.REDSTONE,1);
	@SuppressWarnings("deprecation")
	public void onRightClick(Player p)
	{
		Set<Material> st = null;
		Block target = p.getTargetBlock(st, 7);
		Material type = target.getType();
		if(!p.isSneaking())
		{
			if(type == Material.DIRT)
			{
				if(S87Powers.canPlayerBuildAt(p, target.getLocation(), target) && InventoryHelper.checkForReagents(p, redstone, 1))
				{
					InventoryHelper.removeReagents(p, redstone, 1);
					target.setType(Material.GRASS);
				}
			}
			else if(type == Material.LEAVES || type == Material.LEAVES_2)
			{
	
				if(InventoryHelper.checkForReagents(p, redstone, 4))
				{
					ArrayList<Block> grow = new ArrayList<Block>();
					grow.add(target.getRelative(BlockFace.DOWN));
					grow.add(target.getRelative(BlockFace.UP));
					grow.add(target.getRelative(BlockFace.NORTH));
					grow.add(target.getRelative(BlockFace.SOUTH));
					grow.add(target.getRelative(BlockFace.EAST));
					grow.add(target.getRelative(BlockFace.WEST));
					InventoryHelper.removeReagents(p, redstone, 4);
					BlockHelper.placeInEmpty(p, grow, type, true);
				}
			}
			else if(type == Material.GRASS)
			{
				if(S87Powers.canPlayerBuildAt(p, target.getLocation(), target) && InventoryHelper.checkForReagents(p, redstone, 3))
				{
					ArrayList<Block> grow = new ArrayList<Block>();
					grow.add(target.getRelative(0,1,0));
					grow.add(target.getRelative(1,1,0));
					grow.add(target.getRelative(0,1,1));
					grow.add(target.getRelative(-1,1,0));
					grow.add(target.getRelative(0,1,-1));
					BlockHelper.placeInEmpty(p, grow, Material.LONG_GRASS, true);
					InventoryHelper.removeReagents(p, redstone, 3);
				}
			}
			else if(type == Material.LONG_GRASS)
			{
				World w = p.getWorld();
				Random r = new Random();
				if(S87Powers.canPlayerBuildAt(p, target.getLocation(), target) && InventoryHelper.checkForReagents(p, redstone, 10))
				{
					target.setType(Material.AIR);
					switch(r.nextInt(8))
					{
					case 0:
						w.generateTree(target.getLocation(), TreeType.ACACIA);
						break;
					case 1:
						w.generateTree(target.getLocation(), TreeType.REDWOOD);
						break;
					case 2:
						w.generateTree(target.getLocation(), TreeType.BIG_TREE);
						break;
					case 3:
						w.generateTree(target.getLocation(), TreeType.COCOA_TREE);
						break;
					case 4:
						w.generateTree(target.getLocation(), TreeType.BIRCH);
						break;
					case 5:
						w.generateTree(target.getLocation(), TreeType.JUNGLE);
						break;
					case 6:
						w.generateTree(target.getLocation(), TreeType.JUNGLE_BUSH);
						break;
					case 7:
						w.generateTree(target.getLocation(), TreeType.SMALL_JUNGLE);
						break;
					case 8:
						w.generateTree(target.getLocation(), TreeType.SWAMP);
						break;
					case 9:
						w.generateTree(target.getLocation(), TreeType.TREE);
						break;
					}
					InventoryHelper.removeReagents(p, redstone, 10);
				}
				
			}
			else if(type == Material.CROPS)
			{
				if(InventoryHelper.checkForReagents(p, redstone, 3))
				{
					target.setData((byte) 7);
					InventoryHelper.removeReagents(p, redstone, 3);
				}

			}
		}
		else if(S87Powers.canPlayerBuildAt(p, target.getLocation(), target) && InventoryHelper.checkForReagents(p, redstone, 3))
		{
			ArrayList<Block> grow = new ArrayList<Block>();
			grow.add(target.getRelative(1,0,0));
			grow.add(target.getRelative(-1,0,0));
			grow.add(target.getRelative(0,0,1));
			grow.add(target.getRelative(0,0,-1));
			BlockHelper.placeInEmpty(p, grow, Material.VINE, true);
			InventoryHelper.removeReagents(p, redstone, 3);
		}

	}

}
