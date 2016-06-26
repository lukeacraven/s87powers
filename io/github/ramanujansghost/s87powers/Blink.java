package io.github.ramanujansghost.s87powers;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

//182
public class Blink 
{
	public static void onRightClick(Player p) 
	{
		//if(canBlink(p))
		{
			System.out.println("Blink Start");
			//Set<Material> st = null;
			System.out.println("Target");
	    	Block target = p.getTargetBlock((Set<Material>)null, 15);
	    	if(target.getType() != Material.AIR)
	    	{
	    		System.out.println("Casting");
	    		if(GemHelper.cast(p.getInventory(),(int) target.getLocation().distance(p.getLocation())/2))
		    	{
	    			System.out.println("Casting");
	    			Block close = BlockHelper.closestToPlayer(BlockHelper.findAir(target), p);
	    			System.out.println("Found closest");
	    			if(p.isSneaking())
	    			{
	    				if(target.getLocation().add(0,1,0).getBlock().getType()== Material.AIR)
	    				{
	    					p.teleport(target.getLocation().setDirection(p.getLocation().getDirection()).add(0,1,0));
	    					//S87Powers.timeTillBlinkAgain.put(p.getUniqueId(), System.currentTimeMillis()+ 1000);
	    				}
	    			}
	    			else if(close.getLocation().add(0,1,0).getBlock().getType() == Material.AIR)
	    			{
	    				p.teleport(close.getLocation().setDirection(p.getLocation().getDirection()));
	    				//S87Powers.timeTillBlinkAgain.put(p.getUniqueId(), System.currentTimeMillis()+ 1000);
	    			}
	    			System.out.println("Teleport");
	    			
		    	}	
	    	}
		}

		}
	


}
