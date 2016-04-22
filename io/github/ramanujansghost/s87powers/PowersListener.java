package io.github.ramanujansghost.s87powers;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class PowersListener implements Listener
{
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)
	{
	    if(event.getItem() != null)
	    {
	    	Material itemUsed = event.getItem().getType();
    		Action actionPerformed = event.getAction();
    		Player p = event.getPlayer();
    		
    		if((itemUsed == Material.RAW_CHICKEN
    				|| itemUsed == Material.RABBIT
    				|| itemUsed == Material.MUTTON
    				|| itemUsed == Material.PORK
    				|| itemUsed == Material.RAW_BEEF)
    				&&
    				(p.hasPermission("s87powers.bestialtransmutation"))
    				&&
    				(actionPerformed == Action.LEFT_CLICK_AIR
    				|| actionPerformed == Action.LEFT_CLICK_BLOCK))
		    {
    			BestialTransmutation bestialTransmutation = new BestialTransmutation();
    			bestialTransmutation.onRawMeatUse(event);
		    }
    		if((itemUsed == Material.BONE)
    				&&
    				(actionPerformed == Action.RIGHT_CLICK_AIR
    				|| actionPerformed == Action.RIGHT_CLICK_BLOCK)
    				&& (p.hasPermission("s87powers.wolfpack")))
    		{
    			WolfPack wolfPack = new WolfPack();
    			wolfPack.onBoneRightClick(event);
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
	@EventHandler
	public void onBowShootEvent(EntityShootBowEvent event)
	{
		//S87Powers.log.log(Level.INFO, "EntityShootBowEvent");
		if(event.getEntity().getType() == EntityType.PLAYER)
		{
			//S87Powers.log.log(Level.INFO, "EntityShootBowEvent player detected");
			if(event.getEntity().hasPermission("s87powers.chargebowtoggled"))
			{
				//S87Powers.log.log(Level.INFO, "EntityShootBowEvent perms detected");
				ChargeBow chargeBow = new ChargeBow();
				chargeBow.onBowShootEvent(event);
			}
		}
	}
}
