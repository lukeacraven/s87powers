package io.github.ramanujansghost;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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
    	for(int amountConsumed = 0; amountConsumed < amountToConsume; amountConsumed++)
    	{
    		firstItemStack = inventory.getItem(inventory.first(item.getType()));
        	firstItemStack.setAmount(firstItemStack.getAmount() - 1);
        	inventory.setItem(inventory.first(item.getType()), firstItemStack);
    	}
    	p.updateInventory();
    }			        	
}
