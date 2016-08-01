package io.github.ramanujansghost.s87powers;

import java.util.Set;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;


public class Blink //182
{
	public  void onRightClick(Player p) 
	{
		//Get target
    	Block target = p.getTargetBlock((Set<Material>)null, 15);
    	GemHelper gh = new GemHelper();
    	if(target.getType() != Material.AIR)
    	{
        	//Valid target
			Block close = BlockHelper.closestToPlayer(BlockHelper.findAir(target), p);
			//If shift, blink to top of block
			if(p.isSneaking())
			{
				//Check for free target location
				if(target.getLocation().add(0,1,0).getBlock().getType()== Material.AIR)
				{
					//Extract power/do the thing
					
					if(gh.cast(p.getInventory(),(int) target.getLocation().distance(p.getLocation())/2))
					{
						p.teleport(target.getLocation().setDirection(p.getLocation().getDirection()).add(0,1,0));
					}
				}
			}
			//Check for free target location
			else if(close != null && close.getLocation().add(0,1,0).getBlock().getType() == Material.AIR)
			{
				//Extract power/do the thing
				if(gh.cast(p.getInventory(),(int) target.getLocation().distance(p.getLocation())/2))
				{
					p.teleport(close.getLocation().setDirection(p.getLocation().getDirection()));
				}
			}    				
    	}

	}

}
