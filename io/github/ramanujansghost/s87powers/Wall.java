package io.github.ramanujansghost.s87powers;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Wall
{
	
	private final ItemStack redstone = new ItemStack(Material.REDSTONE,1);
	private final ItemStack stone = new ItemStack(Material.STONE,1);
	public void createWall(Player p)
	{
		Set<Material> st = null;
    	Block target = p.getTargetBlock(st, 8);

		if(target.getType() != Material.AIR){			
    	
			if(InventoryHelper.checkForReagents(p, stone, 9) && InventoryHelper.checkForReagents(p, redstone, 2))
			{
				Block close = BlockHelper.closestToPlayer(BlockHelper.findAir(target), p);
				if(close != null)
				{
					InventoryHelper.removeReagents(p, stone, 9);
					InventoryHelper.removeReagents(p, redstone, 2);
					BlockHelper.placeInEmpty(p, BlockHelper.getWallRelP(close, p), Material.STONE, true);
				}
		
			}
			else
			{
				p.sendMessage("You do not have the necessary reagents to summon a wall!  Requires 9 stone and 2 redstone.");
			}

		}
	}
}
