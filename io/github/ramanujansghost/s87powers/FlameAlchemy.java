package io.github.ramanujansghost.s87powers;

import java.util.ArrayList;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;



public class FlameAlchemy 
{
	private final ItemStack redstone = new ItemStack(Material.REDSTONE,1);
	public void onRightClick(Player p)
	{		
		if(p.isSneaking())
		{
			if(InventoryHelper.checkForReagents(p, redstone, 10))
			{
				InventoryHelper.removeReagents(p, redstone, 10);
				Set<Material> st = null;
		    	Block target = p.getTargetBlock(st, 8);
		    	
				World s = p.getWorld();
				int bsize = (2);
		        double bpow = Math.pow(bsize + .5, 2);
		        double zpow;
		        double xpow;
		        int bx = (int)target.getX();
		        int by = (int)target.getY();
		        int bz =(int) target.getZ();
		
			      ArrayList<Block> fire = new ArrayList<Block>();
			      for (int z = 0; z <= bsize; z++) {
			            zpow = Math.pow(z, 2);
			            for (int x = 0; x <= bsize; x++) {
			                xpow = Math.pow(x, 2);
			                for (int y = 0; y <= bsize; y++) {
			                    if ((xpow + Math.pow(y, 2) + zpow) <= bpow) { 
			                    	fire.add(s.getBlockAt(bx + x, by + y, bz + z));
			                    	fire.add(s.getBlockAt(bx + x, by + y, bz - z));
			                    	fire.add(s.getBlockAt(bx - x, by + y, bz + z));
			                    	fire.add(s.getBlockAt(bx - x, by + y, bz - z));
			                    	fire.add(s.getBlockAt(bx + x, by - y, bz + z));
			                    	fire.add(s.getBlockAt(bx + x, by - y, bz - z));
			                    	fire.add(s.getBlockAt(bx - x, by - y, bz + z));
			                    	fire.add(s.getBlockAt(bx - x, by - y, bz - z));
			                    }
			                }
			            }
			        }
			      for(Block b : fire)
			      {
			    	 if(S87Powers.canPlayerBuildAt(p, b.getLocation(), b))
			    	 {
			    		 if(b.getType() == Material.AIR)
			    		 {
			    			 b.setType(Material.FIRE);
			    		 }
			    	 }
			      }
			      for(Entity e : s.getNearbyEntities(target.getLocation(), 3, 3, 3))
			      {
			    	  e.setFireTicks(40);
			      }
			}
			else
			{
				p.sendMessage("You do not have the necessary reagents generate fire!  Requires 10 redstone!");
			}
		}
		else
		{
			if(InventoryHelper.checkForReagents(p, redstone, 8))
			{
				InventoryHelper.removeReagents(p, redstone, 8);
				Set<Material> st = null;
		    	ArrayList<Block> fire = new ArrayList<Block>();
		    	for(int x = 2; x < 11; x++)
		    	{
		    		fire.add(p.getTargetBlock(st, x));
		    		fire.add(p.getTargetBlock(st, x).getRelative(0, 1, 0));
		    		fire.add(p.getTargetBlock(st, x).getRelative(0, -1, 0));
		    		fire.add(p.getTargetBlock(st, x).getRelative(0, 2, 0));
		    		fire.add(p.getTargetBlock(st, x).getRelative(0, -2, 0));
		    	}
			      for(Block b : fire)
			      {
			    	 if(S87Powers.canPlayerBuildAt(p, b.getLocation(), b))
			    	 {
			    		 if(b.getType() == Material.AIR)
			    		 {
			    			 b.setType(Material.FIRE);
			    		 }
			    	 }
			      }
			}
			else
			{
				p.sendMessage("You do not have the necessary reagents generate fire!  Requires 8 redstone!");
			}
		}
	}

}
