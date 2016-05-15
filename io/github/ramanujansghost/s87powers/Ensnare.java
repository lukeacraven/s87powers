package io.github.ramanujansghost.s87powers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Ensnare extends Power{
	
	//Constructor
	public Ensnare()
	{
		name = "Ensnare";
		type = "Alchemy";
		description = "Ensnares your opponent with webbing, costs string and redstone.";
	}
	
	private static final ItemStack redstone = new ItemStack(Material.REDSTONE,1);
	private static final ItemStack string = new ItemStack(Material.STRING,1);
	
	//This is not ready :p
	//Creates 1 web at target location
	public void deployWebbing(Player p)
	{
		World tempWorld = p.getWorld();
    	Set<Material> st = null;
    	Block target = p.getTargetBlock(st, 8);

		if(target.getType() != Material.AIR){			
    	
			if(InventoryHelper.checkForReagents(p, string, 1) && InventoryHelper.checkForReagents(p, redstone, 1))
			{
				InventoryHelper.removeReagents(p, string, 2);
				InventoryHelper.removeReagents(p, redstone, 2);
								
				BlockHelper.placeInEmpty(BlockHelper.getCrossOnFace(BlockHelper.closestToPlayer(BlockHelper.findAir(target), p), target.getFace(BlockHelper.closestToPlayer(BlockHelper.findAir(target), p))), Material.WEB);
				
			}
			else
		    	p.sendMessage("You do not have the necessary reagents generate web!  Requires 1 string and 1 redstone.");
		}
		else
		{
			if(InventoryHelper.checkForReagents(p, string, 1) && InventoryHelper.checkForReagents(p, redstone, 1))
			{
				InventoryHelper.removeReagents(p, string, 2);
				InventoryHelper.removeReagents(p, redstone, 2);
				ArrayList<Block> line = new ArrayList<Block>();
				line.add(p.getTargetBlock(st,1));
				line.add(p.getTargetBlock(st,2));
				line.add(p.getTargetBlock(st,3));
				
				System.out.println("Player facing:" + PlayerHelper.getPlayerFacing(p));
				System.out.println("Player facing:" + PlayerHelper.getPlayerFacing(p));
				BlockHelper.placeInEmpty(BlockHelper.getLine(tempWorld, p.getEyeLocation(), 4, PlayerHelper.getPlayerFacing(p)), Material.WEB);
				
			}
			else
		    	p.sendMessage("You do not have the necessary reagents generate web!  Requires 1 string and 1 redstone.");
			
		}

	}
	

}
