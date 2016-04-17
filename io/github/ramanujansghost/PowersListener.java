package io.github.ramanujansghost;

import org.bukkit.event.Listener;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class PowersListener implements Listener
{
    ItemStack mutton = new ItemStack(Material.MUTTON,1);
    ItemStack redstone = new ItemStack(Material.REDSTONE,1);
    
	
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)
	{
	    Player p = event.getPlayer();	    
	    
	    if(p.getInventory().getItemInMainHand().getType() == Material.MUTTON)
	    {
	    	if(InventoryHelper.checkForReagents(p, mutton, 1) && InventoryHelper.checkForReagents(p, redstone, 5))
	    	{
	    		InventoryHelper.removeReagents(p, mutton, 1);
				InventoryHelper.removeReagents(p, redstone, 5);
				
				World test = p.getWorld();
	        	Set<Material> st = null;
	        	//test.spawnEntity(p.getTargetBlock(st,3).getLocation(), EntityType.SHEEP);
	        	test.spawnEntity(p.getLocation(), EntityType.SHEEP);
	    	}
        	else
    		{
    			p.sendMessage("You do not have the necessary reagents!");
    		}
	    }
	    /*
		if(p.getInventory().getItemInMainHand().getType() == Material.MUTTON && p.getInventory().containsAtLeast(mutton, 1) && p.getInventory().containsAtLeast(redstone, 5))
		{
			InventoryHelper.removeReagents(p, mutton, 1);
			InventoryHelper.removeReagents(p, redstone, 5);
			
			World test = p.getWorld();
        	Set<Material> st = null;
        	test.spawnEntity(p.getTargetBlock(st,3).getLocation(), EntityType.SHEEP);
	    }
	    */	
	}
}
