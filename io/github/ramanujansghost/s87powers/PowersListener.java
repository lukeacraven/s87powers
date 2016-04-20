package io.github.ramanujansghost.s87powers;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class PowersListener implements Listener
{
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)
	{
	    if(event.getItem() != null)
	    {
	    	if(event.getPlayer().hasPermission("s87powers.bestialtransmutation"))
	    	{
	    		Material itemUsed = event.getItem().getType();
	    		Action actionPerformed = event.getAction();
	    		if((itemUsed == Material.RAW_CHICKEN
	    				|| itemUsed == Material.RABBIT
	    				|| itemUsed == Material.MUTTON
	    				|| itemUsed == Material.PORK
	    				|| itemUsed == Material.RAW_BEEF)
	    				&&
	    				(actionPerformed == Action.LEFT_CLICK_AIR
	    				|| actionPerformed == Action.LEFT_CLICK_BLOCK))
			    {
	    			BestialTransmutation bestialtransmutation = new BestialTransmutation();
	    			bestialtransmutation.onRawMeatUse(event);
			    }
	    	}
		}
	}
	
	@EventHandler
	public void onPlayerBlockBreak(BlockBreakEvent event)
	{
			if(event.getPlayer().hasPermission("s87powers.lumberjack"))
			{
				Material itemUsed = event.getPlayer().getEquipment().getItemInMainHand().getType();
				Material blockBroken = event.getBlock().getType();
				// Add check to see if player is wielding axe
				if((itemUsed == Material.DIAMOND_AXE 
					|| itemUsed == Material.GOLD_AXE
					|| itemUsed == Material.IRON_AXE
					|| itemUsed == Material.STONE_AXE
					|| itemUsed == Material.WOOD_AXE)
					&&
					((blockBroken == Material.LOG
					|| blockBroken == Material.LOG_2)))
				{
					Lumberjack lumberjack = new Lumberjack();
					lumberjack.onLogBreak(event);
				}		
			}
	}
}
