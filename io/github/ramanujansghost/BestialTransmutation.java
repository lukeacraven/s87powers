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
	public BestialTransmutation()
	{
		//super(name, description, reagents);
	}
	
	public void spawnEntity(Player p, EntityType entityType, boolean isAir)
	{
		if(isAir)
		{
			World tempWorld = p.getWorld();
	    	Set<Material> st = null;
	    	tempWorld.spawnEntity(p.getTargetBlock(st,1).getLocation(), EntityType.SHEEP);
		}
    	else
    	{
    		World tempWorld = p.getWorld();
	    	Set<Material> st = null;
	    	tempWorld.spawnEntity(p.getTargetBlock(st,3).getLocation().add(0,1,0), EntityType.SHEEP);
    	}
	}
	
	public void onMuttonUse(PlayerInteractEvent event)
	{
		Player p = event.getPlayer();
		ItemStack mutton = new ItemStack(Material.MUTTON,1);
	    ItemStack redstone = new ItemStack(Material.REDSTONE,1);
	    
	    if(InventoryHelper.checkForReagents(p, mutton, 1) && InventoryHelper.checkForReagents(p, redstone, 5))
	    {
	    	InventoryHelper.removeReagents(p, mutton, 1);
    		InventoryHelper.removeReagents(p, redstone, 5);
    		if(event.getAction() == Action.LEFT_CLICK_AIR)
    			spawnEntity(p, EntityType.SHEEP, true);
    		if(event.getAction() == Action.LEFT_CLICK_BLOCK)
    			spawnEntity(p, EntityType.SHEEP, false);
	    }
	    else
	    	p.sendMessage("You do not have the necessary reagents!");
	}
}
