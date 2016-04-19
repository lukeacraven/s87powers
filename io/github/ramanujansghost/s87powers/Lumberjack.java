package io.github.ramanujansghost.s87powers;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;


//make Lumberjack respect Factions territories
public class Lumberjack extends Power
{
	private static short blocksBroken = 0;
	
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
		short initialDurability = 0;
		short damageCaused = 0;
		
		if(event.getPlayer().getEquipment().getItemInMainHand() != null)
		{
			initialDurability = event.getPlayer().getEquipment().getItemInMainHand().getDurability();
		}
		else
		{
			S87Powers.log.log(Level.WARNING, event.getPlayer() + "'s axe is missing..  perhaps it broke? (Was checking durability)");
			return;
		}
			
		blocksBroken = 0;
		breakAdjacentBlocks(event.getPlayer(), event.getBlock());
		damageCaused = (short) (blocksBroken + 1);
		
		if(S87Powers.debugPowers)
		{
			S87Powers.log.log(Level.INFO, "Initial durability: " + initialDurability);
			S87Powers.log.log(Level.INFO, "Damage caused: " + damageCaused);
		}
		/*
		Bukkit.getServer().getPluginManager().callEvent(new PlayerItemDamageEvent(event.getPlayer()
				, event.getPlayer().getEquipment().getItemInMainHand()
				, 1));
		*/
		
		if(event.getPlayer().getEquipment().getItemInMainHand() != null)
		{
			event.getPlayer().getEquipment().getItemInMainHand().setDurability((short) (initialDurability + damageCaused));
		}
		else
		{
			S87Powers.log.log(Level.WARNING, event.getPlayer() + "'s axe is missing..  perhaps it broke? (Was setting durability)");
			return;
		}	
	}
	
	//make breakAdjacentBlocks method damage player's axe
	public static void breakAdjacentBlocks(Player p, Block block)	//use a for loop here and check a 3x3 cube centered on the given block, but do not check center block.
	{
		if(S87Powers.debugPowers)
			S87Powers.log.log(Level.INFO, "Received block" + block.getX() + ", " + block.getY() + ", " + block.getZ());
			
		//check +1y
		if(block.getLocation().add(0,1,0).getBlock().getType() == Material.LOG 
				|| block.getLocation().add(0,1,0).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().add(0,1,0).getBlock().getType() == Material.LEAVES
				|| block.getLocation().add(0,1,0).getBlock().getType() == Material.LEAVES_2)
		{
			if(S87Powers.canPlayerBuildAt(p, block.getLocation().add(0,1,0), block.getLocation().add(0,1,0).getBlock()))
			{
				block.getLocation().add(0,1,0).getBlock().breakNaturally();
				blocksBroken++;
				breakAdjacentBlocks(p,block.getLocation().add(0,1,0).getBlock());
			}	
		}
		//check -1y
		if(block.getLocation().subtract(0,1,0).getBlock().getType() == Material.LOG 
				|| block.getLocation().subtract(0,1,0).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().subtract(0,1,0).getBlock().getType() == Material.LEAVES
				|| block.getLocation().subtract(0,1,0).getBlock().getType() == Material.LEAVES_2)
		{
			if(S87Powers.canPlayerBuildAt(p, block.getLocation().subtract(0,1,0), block.getLocation().subtract(0,1,0).getBlock()))
			{
				blocksBroken++;
				breakAdjacentBlocks(p,block.getLocation().subtract(0,1,0).getBlock());
			}	
		}
		//check +1x
		if(block.getLocation().add(1,0,0).getBlock().getType() == Material.LOG 
				|| block.getLocation().add(1,0,0).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().add(1,0,0).getBlock().getType() == Material.LEAVES
				|| block.getLocation().add(1,0,0).getBlock().getType() == Material.LEAVES_2)
		{
			if(S87Powers.canPlayerBuildAt(p, block.getLocation().add(1,0,0), block.getLocation().add(1,0,0).getBlock()))
			{
				block.getLocation().add(1,0,0).getBlock().breakNaturally();
				blocksBroken++;
				breakAdjacentBlocks(p,block.getLocation().add(1,0,0).getBlock());
			}
		}
		//check -1x
		if(block.getLocation().subtract(1,0,0).getBlock().getType() == Material.LOG 
				|| block.getLocation().subtract(1,0,0).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().subtract(1,0,0).getBlock().getType() == Material.LEAVES
				|| block.getLocation().subtract(1,0,0).getBlock().getType() == Material.LEAVES_2)
		{
			if(S87Powers.canPlayerBuildAt(p, block.getLocation().subtract(1,0,0), block.getLocation().subtract(1,0,0).getBlock()))
			{
				block.getLocation().subtract(1,0,0).getBlock().breakNaturally();
				blocksBroken++;
				breakAdjacentBlocks(p,block.getLocation().subtract(1,0,0).getBlock());
			}	
		}
		//check +1z
		if(block.getLocation().add(0,0,1).getBlock().getType() == Material.LOG 
				|| block.getLocation().add(0,0,1).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().add(0,0,1).getBlock().getType() == Material.LEAVES
				|| block.getLocation().add(0,0,1).getBlock().getType() == Material.LEAVES_2)
		{
			if(S87Powers.canPlayerBuildAt(p, block.getLocation().add(0,0,1), block.getLocation().add(0,0,1).getBlock()))
			{
				block.getLocation().add(0,0,1).getBlock().breakNaturally();
				blocksBroken++;
				breakAdjacentBlocks(p, block.getLocation().add(0,0,1).getBlock());
			}	
		}
		//check -1z
		if(block.getLocation().subtract(0,0,1).getBlock().getType() == Material.LOG 
				|| block.getLocation().subtract(0,0,1).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().subtract(0,0,1).getBlock().getType() == Material.LEAVES
				|| block.getLocation().subtract(0,0,1).getBlock().getType() == Material.LEAVES_2)
		{
			if(S87Powers.canPlayerBuildAt(p, block.getLocation().subtract(0,0,1), block.getLocation().subtract(0,0,1).getBlock()))
			{
				block.getLocation().subtract(0,0,1).getBlock().breakNaturally();
				blocksBroken++;
				breakAdjacentBlocks(p,block.getLocation().subtract(0,0,1).getBlock());
			}
		}
		//check +1x +1z
		if(block.getLocation().add(1,0,1).getBlock().getType() == Material.LOG 
				|| block.getLocation().add(1,0,1).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().add(1,0,1).getBlock().getType() == Material.LEAVES
				|| block.getLocation().add(1,0,1).getBlock().getType() == Material.LEAVES_2)
		{
			if(S87Powers.canPlayerBuildAt(p, block.getLocation().subtract(0,0,1), block.getLocation().subtract(0,0,1).getBlock()))
			{
				block.getLocation().subtract(0,0,1).getBlock().breakNaturally();
				blocksBroken++;
				breakAdjacentBlocks(p,block.getLocation().subtract(0,0,1).getBlock());
			}
		}
		//check +1x -1z
		if(block.getLocation().add(1,0,-1).getBlock().getType() == Material.LOG 
				|| block.getLocation().add(1,0,-1).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().add(1,0,-1).getBlock().getType() == Material.LEAVES
				|| block.getLocation().add(1,0,-1).getBlock().getType() == Material.LEAVES_2)
		{
			if(S87Powers.canPlayerBuildAt(p, block.getLocation().add(1,0,-1), block.getLocation().add(1,0,-1).getBlock()))
			{
				block.getLocation().add(1,0,-1).getBlock().breakNaturally();
				blocksBroken++;
				breakAdjacentBlocks(p,block.getLocation().add(1,0,-1).getBlock());
			}	
		}
		//check -1x +1z
		if(block.getLocation().add(-1,0,1).getBlock().getType() == Material.LOG 
				|| block.getLocation().add(-1,0,1).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().add(-1,0,1).getBlock().getType() == Material.LEAVES
				|| block.getLocation().add(-1,0,1).getBlock().getType() == Material.LEAVES_2)
		{
			if(S87Powers.canPlayerBuildAt(p, block.getLocation().add(-1,0,1), block.getLocation().add(-1,0,1).getBlock()))
			{
				block.getLocation().add(-1,0,1).getBlock().breakNaturally();
				blocksBroken++;
				breakAdjacentBlocks(p,block.getLocation().add(-1,0,1).getBlock());
			}
		}
		//check -1x -1z
		if(block.getLocation().add(-1,0,-1).getBlock().getType() == Material.LOG 
				|| block.getLocation().add(-1,0,-1).getBlock().getType() == Material.LOG_2 
				|| block.getLocation().add(-1,0,-1).getBlock().getType() == Material.LEAVES
				|| block.getLocation().add(-1,0,-1).getBlock().getType() == Material.LEAVES_2)
		{
			if(S87Powers.canPlayerBuildAt(p, block.getLocation().add(-1,0,-1), block.getLocation().add(-1,0,-1).getBlock()))
			{
				block.getLocation().add(-1,0,-1).getBlock().breakNaturally();
				blocksBroken++;
				breakAdjacentBlocks(p,block.getLocation().add(-1,0,-1).getBlock());
			}
		}
		//failed to find additional blocks to destroy, return
		return;
	}
}
