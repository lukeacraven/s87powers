package io.github.ramanujansghost.s87powers;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
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
    	Location shooter = p.getLocation();
    	Block target = p.getTargetBlock(st, 6);
		if(!target.getType().equals(Material.AIR))
		{			
    	
			if(InventoryHelper.checkForReagents(p, string, 1) && InventoryHelper.checkForReagents(p, redstone, 1))
			{
				InventoryHelper inventoryHelper = new InventoryHelper();
				inventoryHelper.removeReagents(p, string, 1);
				inventoryHelper.removeReagents(p, redstone, 1);
				//target.getRelative(modX, modY, modZ)
				target.setType(Material.WEB);
				//p.get
				
			}
			else
		    	p.sendMessage("You do not have the necessary reagents generate web!  Requires 1 string and 1 redstone.");
		}

	}
}
