package io.github.ramanujansghost.s87powers;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;


//make Lumberjack respect Factions territories
public class Lumberjack extends Power
{
	@Override
	public String getName()
	{
	    return getPowerName();
	}
	@Override
	public String getDescription()
	{
	    return getPowerDescription();
	}
	public static String getPowerName()
	{
		return "Lumberjack";
	}
	public static String getPowerDescription()
	{
		return "Allows player to rapidly cut down trees";
	}
	public static void onLogBreak(BlockBreakEvent event)
	{
		breakAdjacentBlocks(event.getBlock());
	}
	
	//make breakAdjacentBlocks method damage player's axe
	public static void breakAdjacentBlocks(Block block)
	{
		//check 1y above
		if(block.getLocation().add(0,1,0).getBlock().getType() == Material.LOG 
				|| block.getLocation().add(0,1,0).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().add(0,1,0).getBlock().getType() == Material.LEAVES
				|| block.getLocation().add(0,1,0).getBlock().getType() == Material.LEAVES_2)
		{
			block.getLocation().add(0,1,0).getBlock().breakNaturally();
			breakAdjacentBlocks(block.getLocation().add(0,1,0).getBlock());
		}
		//check 1y below
		if(block.getLocation().subtract(0,1,0).getBlock().getType() == Material.LOG 
				|| block.getLocation().subtract(0,1,0).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().subtract(0,1,0).getBlock().getType() == Material.LEAVES
				|| block.getLocation().subtract(0,1,0).getBlock().getType() == Material.LEAVES_2)
		{
			block.getLocation().subtract(0,1,0).getBlock().breakNaturally();
			breakAdjacentBlocks(block.getLocation().subtract(0,1,0).getBlock());
		}
		//check 1x in front
		if(block.getLocation().add(1,0,0).getBlock().getType() == Material.LOG 
				|| block.getLocation().add(1,0,0).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().add(1,0,0).getBlock().getType() == Material.LEAVES
				|| block.getLocation().add(1,0,0).getBlock().getType() == Material.LEAVES_2)
		{
			block.getLocation().add(1,0,0).getBlock().breakNaturally();
			breakAdjacentBlocks(block.getLocation().add(1,0,0).getBlock());
		}
		//check 1x behind
		if(block.getLocation().subtract(1,0,0).getBlock().getType() == Material.LOG 
				|| block.getLocation().subtract(1,0,0).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().subtract(1,0,0).getBlock().getType() == Material.LEAVES
				|| block.getLocation().subtract(1,0,0).getBlock().getType() == Material.LEAVES_2)
		{
			block.getLocation().subtract(1,0,0).getBlock().breakNaturally();
			breakAdjacentBlocks(block.getLocation().subtract(1,0,0).getBlock());
		}
		//check 1z in front
		if(block.getLocation().add(0,0,1).getBlock().getType() == Material.LOG 
				|| block.getLocation().add(0,0,1).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().add(0,0,1).getBlock().getType() == Material.LEAVES
				|| block.getLocation().add(0,0,1).getBlock().getType() == Material.LEAVES_2)
		{
			block.getLocation().add(0,0,1).getBlock().breakNaturally();
			breakAdjacentBlocks(block.getLocation().add(0,0,1).getBlock());
		}
		//check 1z behind
		if(block.getLocation().subtract(0,0,1).getBlock().getType() == Material.LOG 
				|| block.getLocation().subtract(0,0,1).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().subtract(0,0,1).getBlock().getType() == Material.LEAVES
				|| block.getLocation().subtract(0,0,1).getBlock().getType() == Material.LEAVES_2)
		{
			block.getLocation().subtract(0,0,1).getBlock().breakNaturally();
			breakAdjacentBlocks(block.getLocation().subtract(0,0,1).getBlock());
		}
		//failed to find additional blocks to destroy, return
		return;
	}
}
