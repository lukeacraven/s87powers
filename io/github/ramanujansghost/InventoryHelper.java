package io.github.ramanujansghost;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

// All InventoryHelper methods are to be implemented as static methods
public class InventoryHelper
{   
	
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
    		System.out.println("[S87 Powers] Removing " + amountToConsume + 
    				" " + item.getType() + " from " + p.getDisplayName());
    	
    	for(int amountConsumed = 0; amountConsumed < amountToConsume; amountConsumed++)
    	{
    		firstItemStack = inventory.getItem(inventory.first(item.getType()));
    		//firstItemStack.setAmount(firstItemStack.getAmount() - 1);
    		ItemStack remove = new ItemStack(item.getType(),1);
    		p.getInventory().removeItem(remove);
        	
    		if(S87Powers.debugInventoryHelper)
        		System.out.println("[S87 Powers] Amount is now " + firstItemStack.getAmount());
        	if(S87Powers.debugInventoryHelper)
        		System.out.println("[S87 Powers] Index of first found is: " + inventory.first(item.getType()));
        	
        	//inventory.setItem(inventory.first(item.getType()), firstItemStack);
    	}
    }			        	
}
