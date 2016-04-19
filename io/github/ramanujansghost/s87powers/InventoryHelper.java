package io.github.ramanujansghost.s87powers;

import java.util.logging.Level;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

// All InventoryHelper methods are to be implemented as static methods
public class InventoryHelper
{   
	public static void breakTool(Player p, ItemStack item)
	{
		if(S87Powers.debugInventoryHelper)
			S87Powers.log.log(Level.INFO, "Trying to remove " + item.getType());
		p.getInventory().removeItem(item);
		p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 10, 0);
	}
	
    public static boolean checkForReagents(Player p, ItemStack item, int amount)
    {
    	if(p.getInventory().containsAtLeast(item, amount))
    		return true;
    	else
    		return false;
    }
    public static void removeReagents(Player p, ItemStack item, int amountToConsume)
    {
    	ItemStack firstItemStack;
    	PlayerInventory inventory = p.getInventory();
    	
    	if(S87Powers.debugInventoryHelper)
    		S87Powers.log.log(Level.INFO, "[S87 Powers] Removing " + amountToConsume + 
    				" " + item.getType() + " from " + p.getDisplayName());
    	
    	for(int amountConsumed = 0; amountConsumed < amountToConsume; amountConsumed++)
    	{
    		firstItemStack = inventory.getItem(inventory.first(item.getType()));
    		ItemStack remove = new ItemStack(item.getType(),1);
    		p.getInventory().removeItem(remove);
        	
    		if(S87Powers.debugInventoryHelper)
    			S87Powers.log.log(Level.INFO,  "Amount is now " + firstItemStack.getAmount());
        	if(S87Powers.debugInventoryHelper)
        		S87Powers.log.log(Level.INFO, "Index of first found is: " + inventory.first(item.getType()));
    	}
    }			        	
}
