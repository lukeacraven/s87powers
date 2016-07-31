package io.github.ramanujansghost.s87powers;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.bukkit.CropState;
import org.bukkit.GrassSpecies;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;
import org.bukkit.material.Leaves;
import org.bukkit.material.LongGrass;
import org.bukkit.material.Tree;
import org.bukkit.material.Vine;


public class GrowthAlchemy 
{
	private final ItemStack redstone = new ItemStack(Material.REDSTONE,1);

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
				else
				{
					p.sendMessage("You cannot target claimed land");
				}
			}
			else if(type == Material.LEAVES || type == Material.LEAVES_2 || type == Material.LOG || type == Material.LOG_2)
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
					BlockHelper.placeInEmpty(p, grow, Material.LEAVES, true);
					TreeSpecies ts;
					if(type == Material.LOG || type == Material.LOG_2)
					{
						Tree treLea = (Tree) target.getState().getData();
						ts = treLea.getSpecies();
					}
					else
					{
						Leaves tarLea = (Leaves) target.getState().getData();
						ts = tarLea.getSpecies();
					}
					for(Block b : grow)
					{
						if(b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2)
						{
							BlockState bs = b.getState();
							Leaves lg = (Leaves) bs.getData();
							lg.setDecayable(false);
							lg.setSpecies(ts);
							bs.update();
						}
					}
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
					for(Block b : grow)
					{
						if(b.getType() == Material.LONG_GRASS)
						{
							BlockState bs = b.getState();
							LongGrass lg = (LongGrass) bs.getData();
							lg.setSpecies(GrassSpecies.NORMAL);
							bs.update();
						}
					}
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
			else if(type == Material.CARROT || type == Material.CROPS || type == Material.SEEDS || type == Material.POTATO || type == Material.BEETROOT_BLOCK)
			{
				if(InventoryHelper.checkForReagents(p, redstone, 3))
				{
					BlockState bs = target.getState();
					Crops crop = (Crops)bs.getData();
					crop.setState(CropState.RIPE);
					bs.update();
					InventoryHelper.removeReagents(p, redstone, 3);
				}

			}
			else if(type == Material.MELON_STEM)
			{
				target.setType(Material.MELON_BLOCK);
				InventoryHelper.removeReagents(p, redstone, 3);
			}
			else if(type == Material.PUMPKIN_STEM)
			{
				target.setType(Material.PUMPKIN);
				InventoryHelper.removeReagents(p, redstone, 3);
			}
			else if(type == Material.SUGAR_CANE_BLOCK)
			{
				if(target.getRelative(BlockFace.UP).getType() == Material.AIR)
				{
					target.getRelative(BlockFace.UP).setType(Material.SUGAR_CANE_BLOCK);
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

			for(Block g : grow)
			{
				if(g.getType() == Material.VINE)
				{
					BlockState bs = g.getState();
					Vine v = (Vine)bs.getData();
					v.putOnFace(g.getFace(target));	
					bs.update();
				}
			}
			InventoryHelper.removeReagents(p, redstone, 3);
		}

	}

}
