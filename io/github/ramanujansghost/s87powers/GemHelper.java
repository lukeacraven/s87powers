package io.github.ramanujansghost.s87powers;

import org.bukkit.inventory.ItemStack;

public class GemHelper {
	
	public static void formGem(ItemStack gem)
	{
		gem.getItemMeta().setDisplayName("Soul Gem");
	}
	public static void setGemPower(ItemStack gem, int value)
	{
		String check = gem.getItemMeta().getLore();
		
		gem.getItemMeta().setLore("");
	}
	
	public static int getGemPower(ItemStack gem)
	{
		
		return 0;
	}

}
