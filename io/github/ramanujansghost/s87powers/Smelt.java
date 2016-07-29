package io.github.ramanujansghost.s87powers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class Smelt 
{
	private final ItemStack redstone = new ItemStack(Material.REDSTONE,1);
	public void onRightClick(Player p) 
	{
		ItemStack toSmelt = p.getInventory().getItemInMainHand();
		Material metal = toSmelt.getType();
		if(!toSmelt.getItemMeta().hasDisplayName() && (metal == Material.GOLD_AXE || metal == Material.GOLD_LEGGINGS || metal == Material.GOLD_BOOTS || metal == Material.GOLD_CHESTPLATE || metal == Material.GOLD_HELMET || metal == Material.GOLD_SWORD || metal == Material.GOLD_PICKAXE || metal == Material.GOLD_HOE || metal == Material.GOLD_SPADE))
		{
			if(InventoryHelper.checkForReagents(p, redstone, 2))
			{
				InventoryHelper.removeReagents(p, redstone, 2);
				p.getInventory().setItemInMainHand(new ItemStack(Material.GOLD_INGOT,1));

				p.updateInventory();
			}
			else
			{
				p.sendMessage("This requires 2 redstone to smelt.");
			}
			
		}
		else if(!toSmelt.getItemMeta().hasDisplayName() && (metal == Material.IRON_AXE || metal == Material.IRON_LEGGINGS || metal == Material.IRON_BOOTS || metal == Material.IRON_CHESTPLATE || metal == Material.IRON_HELMET || metal == Material.IRON_SWORD || metal == Material.IRON_PICKAXE || metal == Material.BUCKET || metal == Material.IRON_HOE || metal == Material.IRON_SPADE))
		{
			if(InventoryHelper.checkForReagents(p, redstone, 6))
			{
				InventoryHelper.removeReagents(p, redstone, 6);
				p.getInventory().setItemInMainHand(new ItemStack(Material.IRON_INGOT,1));
				p.updateInventory();
			}
			else
			{
				p.sendMessage("This requires 6 redstone to smelt.");
			}
			
		}
		else if(!toSmelt.getItemMeta().hasDisplayName() && (metal == Material.CHAINMAIL_BOOTS || metal == Material.CHAINMAIL_CHESTPLATE || metal == Material.CHAINMAIL_HELMET || metal == Material.CHAINMAIL_LEGGINGS))
		{
			p.getInventory().setItemInMainHand(new ItemStack(Material.REDSTONE,1));
			p.updateInventory();
		}
		else
		{
			p.sendMessage("You cannot smelt this!");
		}
		
	}

}
