package io.github.ramanujansghost.s87powers;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Sinkhole 
{
	private final ItemStack redstone = new ItemStack(Material.REDSTONE,1);
	
	//Creates web cross at block or on a target
	public void onRightClick(Player p)
	{
    	Set<Material> st = null;
    	Block target = p.getTargetBlock(st, 8);
    	LivingEntity targetEntity = PlayerHelper.getTarget(p, 8);
    	
		//Targeting an entity
		if(targetEntity != null)
		{			
			if(InventoryHelper.checkForReagents(p, redstone, 8))
			{

				InventoryHelper.removeReagents(p, redstone, 8);
				makeHole(targetEntity.getLocation().getBlock().getRelative(BlockFace.DOWN), p);

			}
			else
		    	p.sendMessage("You do not have the necessary reagents to make a hole!  Requires 8 redstone.");
		}
    	//Targeting a block
    	else if(target.getType() != Material.AIR)
		{			
    	
    		if(InventoryHelper.checkForReagents(p, redstone, 8))
			{
				if(target != null)
				{
					InventoryHelper.removeReagents(p, redstone, 8);
					makeHole(target, p);
				}
				
			}
			else
		    	p.sendMessage("You do not have the necessary reagents generate web!  Requires 8 redstone.");
		}
			
	}
	
	private void makeHole(Block target, Player p)
	{
		ArrayList<Block> hole = new ArrayList<Block>();
		hole.add(target);
		hole.add(target.getRelative(1, 0, 0));
		hole.add(target.getRelative(0, 0, 1));
		hole.add(target.getRelative(1, 0, 1));
		hole.add(target.getRelative(1, 0, -1));
		hole.add(target.getRelative(-1, 0, 1));
		hole.add(target.getRelative(0, 0, -1));
		hole.add(target.getRelative(-1, 0, -1));
		hole.add(target.getRelative(-1, 0, 0));
		hole.add(target.getRelative(0, -1, 0));
		hole.add(target.getRelative(0, -2, 0));
		hole.add(target.getRelative(0, -3, 0));

		for(Block b : hole)
		{
			if(S87Powers.canPlayerBuildAt(p, b.getLocation(), b))
			{
				Material bType = b.getType();
				if(bType == Material.DIRT || bType == Material.SAND || bType == Material.GRAVEL || bType == Material.GRASS)
				{
					b.breakNaturally();
				}
			}
		}
		
	}

}
