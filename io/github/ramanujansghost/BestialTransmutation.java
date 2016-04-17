package io.github.ramanujansghost;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BestialTransmutation extends Power
{
	ItemStack rawChicken = new ItemStack(Material.RAW_CHICKEN,1);
	ItemStack mutton = new ItemStack(Material.MUTTON,1);
	ItemStack rawBeef = new ItemStack(Material.RAW_BEEF,1);
    ItemStack redstone = new ItemStack(Material.REDSTONE,1);
    
	public BestialTransmutation()
	{
		//super(name, description, reagents);
	}
	
	public void spawnEntity(Player p, EntityType entityType, Action action)
	{
		if(action == Action.LEFT_CLICK_AIR)
		{
			World tempWorld = p.getWorld();
	    	Set<Material> st = null;
	    	tempWorld.spawnEntity(p.getTargetBlock(st,1).getLocation(), entityType);
		}
    	else
    	{
    		World tempWorld = p.getWorld();
	    	Set<Material> st = null;
	    	tempWorld.spawnEntity(p.getTargetBlock(st,3).getLocation().add(0,1,0), entityType);
    	}
	}
	
	public boolean consumeResources(Player p, String type)
	{
		if(type.equalsIgnoreCase("CHICKEN"))
		{
			if(InventoryHelper.checkForReagents(p, rawChicken, 1) && InventoryHelper.checkForReagents(p, redstone, 2))
			{
				InventoryHelper.removeReagents(p, rawChicken, 1);
	    		InventoryHelper.removeReagents(p, redstone, 2);
	    		PlayerHelper.damagePlayer(p, 2);
	    		return true;
			}
			else
				return false;
		}
		
		else if(type.equalsIgnoreCase("SHEEP"))
		{
			if(InventoryHelper.checkForReagents(p, mutton, 1) && InventoryHelper.checkForReagents(p, redstone, 3))
			{
				InventoryHelper.removeReagents(p, mutton, 1);
	    		InventoryHelper.removeReagents(p, redstone, 3);
	    		PlayerHelper.damagePlayer(p, 4);
	    		return true;
			}
			else
				return false;
		}
		
		else if(type.equalsIgnoreCase("COW"))
		{
			if(InventoryHelper.checkForReagents(p, rawBeef, 1) && InventoryHelper.checkForReagents(p, redstone, 5))
			{
				InventoryHelper.removeReagents(p, rawBeef, 1);
	    		InventoryHelper.removeReagents(p, redstone, 5);
	    		PlayerHelper.damagePlayer(p, 5);
	    		return true;
			}
			else
				return false;
		}
		return false;
	}
	
	public void onRawChickenUse(PlayerInteractEvent event)
	{
		Player p = event.getPlayer();
	    
	    if(consumeResources(p,"CHICKEN"))
    		spawnEntity(p, EntityType.CHICKEN, event.getAction());
	    else
	    	p.sendMessage("You do not have the necessary reagents to summon a chicken!  Requires 1 raw chicken and 2 redstone");
	}
	
	public void onMuttonUse(PlayerInteractEvent event)
	{
		Player p = event.getPlayer();
	    
		if(consumeResources(p,"SHEEP"))
			spawnEntity(p, EntityType.SHEEP, event.getAction());
	    else
	    	p.sendMessage("You do not have the necessary reagents to summon a sheep!  Requires 1 mutton and 3 redstone.");
	}
	
	public void onRawBeefUse(PlayerInteractEvent event)
	{
		Player p = event.getPlayer();
	    
		if(consumeResources(p,"COW"))
			spawnEntity(p, EntityType.COW, event.getAction());
	    else
	    	p.sendMessage("You do not have the necessary reagents to summon a cow!  Requires 1 raw beef and 5 redstone.");
	}
}
