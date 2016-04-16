package io.github.ramanujansghost;

import org.bukkit.event.Listener;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;


public class PowersListener implements Listener
{
    ItemStack mu = new ItemStack(Material.MUTTON,1);
    ItemStack rb = new ItemStack(Material.REDSTONE,1);
    
	
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)
	{
	    Player p = event.getPlayer();	    
	    
		if(p.getInventory().getItemInMainHand().getType() == Material.STICK && p.getInventory().containsAtLeast(mu, 1) && p.getInventory().containsAtLeast(rb, 5)){
	        {
	        	int want = 5;
	        	int have = 0;
	        	ItemStack ap = p.getInventory().getItem(p.getInventory().first(Material.MUTTON));
	        	
	        	ap.setAmount(ap.getAmount()-1);
	        	p.getInventory().setItem(p.getInventory().first(Material.MUTTON), ap);
	        	while(have < want)
	        	{
		        	ItemStack rg = p.getInventory().getItem(p.getInventory().first(Material.REDSTONE));
		        	if(rg.getAmount() < (want-have))
	        		{
		        		have += rg.getAmount();
	        			rg.setAmount(0);
	        		}
		        	else
		        	{
		        		rg.setAmount(rg.getAmount()-(want-have));
		        		have = want;
		        	}
		        	p.getInventory().setItem(p.getInventory().first(Material.REDSTONE), rg);
	        	}
	        	p.updateInventory();
	        	World test = p.getWorld();
	        	Set<Material> st = null;
	        	test.spawnEntity(p.getTargetBlock(st ,6).getLocation(), EntityType.SHEEP);
	        }
	    }
	}
}
