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
	    		if(event.getItem().getType() == Material.RAW_CHICKEN && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
			    {
	    			BestialTransmutation.onRawChickenUse(event);
			    }
			    if(event.getItem().getType() == Material.MUTTON && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
			    {
			    	BestialTransmutation.onMuttonUse(event);
			    }
			    if(event.getItem().getType() == Material.PORK && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
			    {
			    	BestialTransmutation.onPorkUse(event);
			    }
			    if(event.getItem().getType() == Material.RABBIT && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
			    {
			    	BestialTransmutation.onRabbitUse(event);
			    }
			    if(event.getItem().getType() == Material.RAW_BEEF && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
			    {
			    	BestialTransmutation.onRawBeefUse(event);
			    }
	    	}
		}
	}
	
	@EventHandler
	public void onPlayerBlockBreak(BlockBreakEvent event)
	{
		if(!event.isCancelled())
		{
			if(event.getPlayer().hasPermission("s87powers.lumberjack"));
			{
				// Add check to see if player is wielding axe
				if((event.getBlock().getType() == Material.LOG || event.getBlock().getType() == Material.LOG_2))
					Lumberjack.onLogBreak(event);
			}
		}
	}
}
