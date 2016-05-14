package io.github.ramanujansghost.s87powers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class GemHelper {
	
	public void formGem(PlayerInventory inv)
	{
		ItemStack gem = inv.getItemInMainHand();
		ItemMeta meta = gem.getItemMeta();
		meta.setDisplayName("Soul Gem");
		List<String> data = new ArrayList<String>();
		String lore = "0/1000";
		data.add(lore);
		meta.setLore(data);
		gem.setItemMeta(meta);
		inv.setItemInMainHand(gem);
	}
	
	//Current thought is to store value in Lore[0] and maxValue in Lore[1]
	//This idea needs to be tested
	public static void setGemPower(PlayerInventory inv, int itemLocation, int value)
	{
		ItemStack gem = inv.getItem(itemLocation);
		ItemMeta meta = gem.getItemMeta();
		List<String> data = new ArrayList<String>();		
		String oldData = gem.getItemMeta().getLore().get(0);		
		int max = getGemMaxPower(inv, itemLocation);
		String lore;
		//If too high, set to maxValue
		if(value <= max)
		{
			lore = new Integer(value).toString() + "/" + new Integer(max).toString();
		}
		else
		{
			lore = new Integer(max).toString() + "/" + new Integer(max).toString();
		}
		
		data.add(lore);
		meta.setLore(data);
		gem.setItemMeta(meta);
		inv.setItem(itemLocation, gem);
	}
	
	public void setGemMax(PlayerInventory inv, int itemLocation, int max)
	{
		ItemStack gem = inv.getItem(itemLocation);
		ItemMeta meta = gem.getItemMeta();
		List<String> data = new ArrayList<String>();		
		String oldData = gem.getItemMeta().getLore().get(0);		
		int value = getGemPower(inv, itemLocation);
		String lore = new Integer(value).toString() + "/" + new Integer(max).toString();
		data.add(lore);
		meta.setLore(data);
		gem.setItemMeta(meta);
		inv.setItem(itemLocation, gem);
	}
	
	//Return the current power value of a gem
	public static int getGemPower(PlayerInventory inv, int itemLocation)
	{
		ItemStack gem = inv.getItem(itemLocation);
		String oldData = gem.getItemMeta().getLore().get(0);		
		int value = Integer.parseInt(oldData.substring(0,oldData.indexOf('/')));
		return value;
	}
	
	//Return the maximum power of a gem
	public static int getGemMaxPower(PlayerInventory inv, int itemLocation)
	{
		ItemStack gem = inv.getItem(itemLocation);
		String oldData = gem.getItemMeta().getLore().get(0);		
		int max = Integer.parseInt(oldData.substring(oldData.indexOf('/'),oldData.length()-1));	
		return max;
	}
	public void onRightClick(PlayerInteractEvent event)
	{
		formGem(event.getPlayer().getInventory());			
	}
	
	//Overload
	public static void setGemPower(ItemStack itemInMainHand, int i) {
		// TODO Auto-generated method stub
		
	}

}
