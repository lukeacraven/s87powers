package io.github.ramanujansghost.s87powers;

import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BestialTransmutation implements Power
{
	private static final ItemStack rawChicken = new ItemStack(Material.RAW_CHICKEN,1);
	private static final ItemStack rabbit = new ItemStack(Material.RABBIT,1);
	private static final ItemStack mutton = new ItemStack(Material.MUTTON,1);
	private static final ItemStack pork = new ItemStack(Material.PORK,1);
	private static final ItemStack rawBeef = new ItemStack(Material.RAW_BEEF,1);
	
	private static final ItemStack redstone = new ItemStack(Material.REDSTONE,1);
	
	public String getName()
	{
		return "Bestial Transmutation";
	}
	public String getType()
	{
		return "Utility";
	}
	public String getDescription()
	{
		return "Allows player to use 1 meat from a chicken, rabbit, sheep, pig, or cow, a varying amount of redstone, and life to create an animal";
	}
	public void spawnEntity(Player p, EntityType entityType, Action action)
	{
		World tempWorld = p.getWorld();
    	Set<Material> st = null;
		S87Powers.log.log(Level.INFO, "Spawning " + entityType.toString()
				+ " for " + p.getDisplayName() + " via power " + getName());
		if(action == Action.LEFT_CLICK_AIR)
		{
			
	    	/*
	    	 * If player left clicks on air, we attempt to spawn the animal closer to the
	    	 * ground, but refrain from putting them into the ground.  This still allows the
	    	 * player to "drop" their spawned animal off a cliff etc if they are intentional about it.
	    	 */
	    	if(p.getTargetBlock(st,2).getLocation().subtract(0,2,0).getBlock().getType() == Material.AIR)
	    		tempWorld.spawnEntity(p.getTargetBlock(st,2).getLocation().subtract(0,2,0), entityType);
	    	else if(p.getTargetBlock(st,2).getLocation().subtract(0,1,0).getBlock().getType() == Material.AIR)
	    		tempWorld.spawnEntity(p.getTargetBlock(st,2).getLocation().subtract(0,1,0), entityType);
	    	else
	    		tempWorld.spawnEntity(p.getTargetBlock(st,2).getLocation(), entityType);	
		}
    	else
	    	tempWorld.spawnEntity(p.getTargetBlock(st,4).getLocation().add(0,1,0), entityType);
	}
	
	public boolean consumeResources(Player p, EntityType type)
	{
		if(type == EntityType.CHICKEN)
		{
			if(InventoryHelper.checkForReagents(p, rawChicken, 1) && InventoryHelper.checkForReagents(p, redstone, 2))
			{
				InventoryHelper inventoryHelper = new InventoryHelper();
				inventoryHelper.removeReagents(p, rawChicken, 1);
				inventoryHelper.removeReagents(p, redstone, 2);
	    		PlayerHelper.damagePlayer(p, "Transmutation", 2);
	    		return true;
			}
			else
				return false;
		}	
		else if(type == EntityType.RABBIT)
		{
			if(InventoryHelper.checkForReagents(p, rabbit, 1) && InventoryHelper.checkForReagents(p, redstone, 2))
			{
				InventoryHelper inventoryHelper = new InventoryHelper();
				inventoryHelper.removeReagents(p, rabbit, 1);
				inventoryHelper.removeReagents(p, redstone, 2);
	    		PlayerHelper.damagePlayer(p, "Transmutation", 2);
	    		return true;
			}
			else
				return false;
		}	
		else if(type == EntityType.SHEEP)
		{
			if(InventoryHelper.checkForReagents(p, mutton, 1) && InventoryHelper.checkForReagents(p, redstone, 5))
			{
				InventoryHelper inventoryHelper = new InventoryHelper();
				inventoryHelper.removeReagents(p, mutton, 1);
				inventoryHelper.removeReagents(p, redstone, 5);
	    		PlayerHelper.damagePlayer(p, "Transmutation", 4);
	    		return true;
			}
			else
				return false;
		}	
		else if(type == EntityType.PIG)
		{
			if(InventoryHelper.checkForReagents(p, pork, 1) && InventoryHelper.checkForReagents(p, redstone, 3))
			{
				InventoryHelper inventoryHelper = new InventoryHelper();
				inventoryHelper.removeReagents(p, pork, 1);
				inventoryHelper.removeReagents(p, redstone, 3);
	    		PlayerHelper.damagePlayer(p, "Transmutation", 4);
	    		return true;
			}
			else
				return false;
		}	
		else if(type == EntityType.COW)
		{
			if(InventoryHelper.checkForReagents(p, rawBeef, 1) && InventoryHelper.checkForReagents(p, redstone, 6))
			{
				InventoryHelper inventoryHelper = new InventoryHelper();
				inventoryHelper.removeReagents(p, rawBeef, 1);
				inventoryHelper.removeReagents(p, redstone, 6);
	    		PlayerHelper.damagePlayer(p, "Transmutation", 5);
	    		return true;
			}
			else
				return false;
		}
		return false;
	}
	
	public void onRawMeatUse(PlayerInteractEvent event)
	{
		Player p = event.getPlayer();
		Material itemUsed = event.getItem().getType();
		
		if(itemUsed == Material.RAW_CHICKEN)
		{
			if(consumeResources(p,EntityType.CHICKEN))
	    		spawnEntity(p, EntityType.CHICKEN, event.getAction());
		    else
		    	p.sendMessage("You do not have the necessary reagents to summon a chicken!  Requires 1 raw chicken and 2 redstone.");
		}
		else if(itemUsed == Material.RABBIT)
		{
			if(consumeResources(p,EntityType.RABBIT))
	    		spawnEntity(p, EntityType.RABBIT, event.getAction());
		    else
		    	p.sendMessage("You do not have the necessary reagents to summon a rabbit!  Requires 1 rabbit and 2 redstone.");
		}
		else if(itemUsed == Material.MUTTON)
		{
			if(consumeResources(p,EntityType.SHEEP))
				spawnEntity(p, EntityType.SHEEP, event.getAction());
		    else
		    	p.sendMessage("You do not have the necessary reagents to summon a sheep!  Requires 1 raw mutton and 5 redstone.");
		}
		else if(itemUsed == Material.PORK)
		{
			if(consumeResources(p,EntityType.PIG))
				spawnEntity(p, EntityType.PIG, event.getAction());
		    else
		    	p.sendMessage("You do not have the necessary reagents to summon a pig!  Requires 1 raw porkchop and 3 redstone.");
		}
		else if(itemUsed == Material.RAW_BEEF)
		{
			if(consumeResources(p,EntityType.COW))
				spawnEntity(p, EntityType.COW, event.getAction());
		    else
		    	p.sendMessage("You do not have the necessary reagents to summon a cow!  Requires 1 raw beef and 6 redstone.");
		}
	}
}