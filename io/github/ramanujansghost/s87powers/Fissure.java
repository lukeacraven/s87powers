package io.github.ramanujansghost.s87powers;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Fissure 
{
	private final ItemStack redstone = new ItemStack(Material.REDSTONE,1);
	public void onRightClick(Player p)
	{
		
			if(InventoryHelper.checkForReagents(p, redstone, 12))
			{
				BlockFace face = PlayerHelper.getPlayerFacing(p);
				InventoryHelper.removeReagents(p, redstone, 12);
				Set<Material> st = null;
		    	ArrayList<Block> target = new ArrayList<Block>();
		    	Block start = p.getTargetBlock(st, 6);
		    	for(int x = 0; x < 4; x++)
		    	{
			    	target.add(start);
			    	target.add(start.getRelative(0, -1, 0));
			    	start = start.getRelative(face);
		    	}
		    	

			      for(Block b : target)
			      {
			    	 Material m = b.getType();
			    	 if(S87Powers.canPlayerBuildAt(p, b.getLocation(), b) && (m == Material.DIRT || m == Material.STONE || m == Material.GRASS || m == Material.GRAVEL || m == Material.SAND || m == Material.NETHERRACK || m == Material.SOUL_SAND))
			    	 {
			    			 b.breakNaturally();
			    	 }
			      }
			}
			else
			{
				p.sendMessage("You do not have the necessary reagents to cause a fissure!  Requires 12 redstone!");
			}
		}

}
