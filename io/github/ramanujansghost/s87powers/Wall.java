package io.github.ramanujansghost.s87powers;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Wall
{
	
	private static final ItemStack redstone = new ItemStack(Material.REDSTONE,1);
	private static final ItemStack stone = new ItemStack(Material.STONE,1);
	public static void createWall(Player p)
	{
		Set<Material> st = null;
    	Block target = p.getTargetBlock(st, 8);

		if(target.getType() != Material.AIR){			
    	
			if(InventoryHelper.checkForReagents(p, stone, 1) && InventoryHelper.checkForReagents(p, redstone, 1))
			{
				Block close = BlockHelper.closestToPlayer(BlockHelper.findAir(target), p);
				if(close != null)
				{
					InventoryHelper.removeReagents(p, stone, 9);
					InventoryHelper.removeReagents(p, redstone, 2);
					BlockHelper.placeInEmpty(p, BlockHelper.getWallRelP(close, p), Material.STONE);
				}
		
			}

		}
	}
}
