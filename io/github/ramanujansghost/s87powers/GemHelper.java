package io.github.ramanujansghost.s87powers;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public class GemHelper {
	
	public static void formGem(ItemStack gem)
	{
		gem.getItemMeta().setDisplayName("Soul Gem");
	}
	//Current thought is to store value in Lore[0] and maxValue in Lore[1]
	//This idea needs to be tested
	public static void setGemPower(ItemStack gem, int value)
	{
		List<String> data = gem.getItemMeta().getLore();
		//If too high, set to maxValue
		if(value <= Integer.parseInt(data.get(1)))
		{
			data.set(0, new Integer(value).toString());
		}
		else
		{
			data.set(0, data.get(1));
		}
		gem.getItemMeta().setLore(data);
	}
	//Return the current power value of a gem
	public static int getGemPower(ItemStack gem)
	{
		List<String> data = gem.getItemMeta().getLore();
		return Integer.parseInt(data.get(0));
	}
	
	//Return the maximum power of a gem
	public static int getGemMaxPower(ItemStack gem)
	{
		List<String> data = gem.getItemMeta().getLore();
		return Integer.parseInt(data.get(1));
	}

}
