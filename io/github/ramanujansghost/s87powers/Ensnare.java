package io.github.ramanujansghost.s87powers;

import java.util.ArrayList;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Ensnare{
	
	
	private final ItemStack redstone = new ItemStack(Material.REDSTONE,1);
	private final ItemStack string = new ItemStack(Material.STRING,1);
	
	//Creates web cross at block or on a target
	public void deployWebbing(Player p)
	{
    	Set<Material> st = null;
    	Block target = p.getTargetBlock(st, 8);
    	LivingEntity targetEntity = PlayerHelper.getTarget(p, 8);
    	
		//Targeting an entity
		if(targetEntity != null)
		{			
			if(InventoryHelper.checkForReagents(p, string, 6) && InventoryHelper.checkForReagents(p, redstone, 1))
			{
				InventoryHelper.removeReagents(p, string, 6);
				InventoryHelper.removeReagents(p, redstone, 2);
				ArrayList<Block> line = new ArrayList<Block>();
				line.add(targetEntity.getLocation().getBlock());
				line.add(targetEntity.getLocation().getBlock().getRelative(0, 1, 0));
				line.add(targetEntity.getLocation().getBlock().getRelative(1, 0, 0));
				line.add(targetEntity.getLocation().getBlock().getRelative(-1, 0, 0));
				line.add(targetEntity.getLocation().getBlock().getRelative(0, 0, 1));
				line.add(targetEntity.getLocation().getBlock().getRelative(0, 0, -1));
				BlockHelper.placeInEmpty(p, line, Material.WEB);
			}
			else
		    	p.sendMessage("You do not have the necessary reagents generate web!  Requires 6 string and 2 redstone.");
		}
    	//Targeting a block
    	else if(target.getType() != Material.AIR)
		{			
    	
			if(InventoryHelper.checkForReagents(p, string, 6) && InventoryHelper.checkForReagents(p, redstone, 1))
			{
				Block close = BlockHelper.closestToPlayer(BlockHelper.findAir(target), p);
				if(close != null)
				{
					InventoryHelper.removeReagents(p, string, 6);
					InventoryHelper.removeReagents(p, redstone, 2);
					BlockHelper.placeInEmpty(p, BlockHelper.getCrossOnFace(close, target.getFace(BlockHelper.closestToPlayer(BlockHelper.findAir(target), p))), Material.WEB);
				}
				
			}
			else
		    	p.sendMessage("You do not have the necessary reagents generate web!  Requires 6 string and 2 redstone.");
		}
			
	}

	

}
