package io.github.ramanujansghost.s87powers;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;


public class PowersListener implements Listener
{
	BestialTransmutation bt = new BestialTransmutation();
	
    
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)
	{
	    if(event.getItem() != null)
	    {
	    	if(event.getPlayer().hasPermission("s87powers.bestialtransmutation"))
	    	{
	    		if(event.getItem().getType() == Material.RAW_CHICKEN && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
			    {
			    	bt.onRawChickenUse(event);
			    }
			    if(event.getItem().getType() == Material.MUTTON && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
			    {
			    	bt.onMuttonUse(event);
			    }
			    if(event.getItem().getType() == Material.PORK && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
			    {
			    	bt.onPorkUse(event);
			    }
			    if(event.getItem().getType() == Material.RABBIT && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
			    {
			    	bt.onRabbitUse(event);
			    }
			    if(event.getItem().getType() == Material.RAW_BEEF && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
			    {
			    	bt.onRawBeefUse(event);
			    }
	    	}
		}
	}
}
