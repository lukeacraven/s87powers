package io.github.ramanujansghost.s87powers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class GemHelper {
	
	public static void formGem(PlayerInventory inv)
	{
		ItemStack gem = inv.getItemInMainHand();
		ItemMeta meta = gem.getItemMeta();
		meta.setDisplayName("Soul Gem");
		List<String> data = new ArrayList<String>();
		String lore = "";
		
		if(gem.getType() == Material.DIAMOND)
		{
			lore = "0/1000";
			//set cooldown 8 sec
		}
		else if(gem.getType() == Material.EMERALD)
		{
			lore = "0/500";
			//set cooldown 4 sec
		}
		else if(gem.getType() == Material.QUARTZ)
		{
			lore = "0/250";
			//set cooldown 2 sec
		}
		
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
	
	public static void setGemMax(PlayerInventory inv, int itemLocation, int max)
	{
		ItemStack gem = inv.getItem(itemLocation);
		ItemMeta meta = gem.getItemMeta();
		List<String> data = new ArrayList<String>();			
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
		System.out.println("Line 1");
		ItemStack gem = inv.getItem(itemLocation);
		System.out.println("2");
		String oldData = gem.getItemMeta().getLore().get(0);	
		System.out.println("3");
		int value = Integer.parseInt(oldData.substring(0,oldData.indexOf('/')));
		System.out.println("4");
		return value;
	}
	
	//Return the maximum power of a gem
	public static int getGemMaxPower(PlayerInventory inv, int itemLocation)
	{
		ItemStack gem = inv.getItem(itemLocation);
		String oldData = gem.getItemMeta().getLore().get(0);		
		int max = Integer.parseInt(oldData.substring(oldData.indexOf('/')+1,oldData.length()));	
		return max;
	}
	
	//Handle usage
	public static void onRightClick(PlayerInteractEvent event)
	{
		formGem(event.getPlayer().getInventory());			
	}
	
	public static void addPower(PlayerInventory inv, int itemLocation, int addValue)
	{
		ItemStack gem = inv.getItem(itemLocation);
		String oldData = gem.getItemMeta().getLore().get(0);
		int value = Integer.parseInt(oldData.substring(0,oldData.indexOf('/')));
		setGemPower(inv, itemLocation, value+addValue);		
	}
	
	public static void removePower(PlayerInventory inv, int itemLocation, int rmvValue)
	{
		ItemStack gem = inv.getItem(itemLocation);
		String oldData = gem.getItemMeta().getLore().get(0);
		int value = Integer.parseInt(oldData.substring(0,oldData.indexOf('/')));
		if(rmvValue > value)
		{			
			setGemPower(inv, itemLocation, 0);
			Siphon.extract((Player)inv.getHolder(), (Player)inv.getHolder(), rmvValue - value);			
		}
		else
		{
			setGemPower(inv, itemLocation, value-rmvValue);
		}
	}
	public static boolean cast(PlayerInventory inv, int val)
	{
		if(inv.getItem(40)!= null)
		{
			ItemMeta meta = inv.getItemInOffHand().getItemMeta();
			if(meta.getDisplayName().equals("Soul Gem") && Character.isDigit(meta.getLore().get(0).charAt(0)))
			{
				System.out.println(getGemPower(inv, 40));
				removePower(inv, 40, val);
			}
			else
			{
				Siphon.extract((Player)inv.getHolder(), (Player)inv.getHolder(), (val/7)+1);
			}
		}
		else
		{
			Siphon.extract((Player)inv.getHolder(), (Player)inv.getHolder(), (val/7)+1);
		}
		if(((Player)inv.getHolder()).getHealth() == 0)
		{
			System.out.println("Fail");
			return false;
		}
		else
		{
			System.out.println("Success");
			return true;
		}
			
			
	}

	

}
