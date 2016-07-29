package io.github.ramanujansghost.s87powers;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UnlimitedBladeworks 
{
	public void onRightClick(Player p)
	{
		World w = p.getWorld();
		ItemStack inHand = p.getInventory().getItemInMainHand();
		ItemMeta meta = inHand.getItemMeta();
		GemHelper gh = new GemHelper();
		if(!meta.hasDisplayName() || !meta.getDisplayName().equals("\u221E"))
		{			
			if(gh.cast(p.getInventory(), 20))
			{
				w.dropItem(p.getLocation(), inHand);
				p.getInventory().setItemInMainHand(new ItemStack(Material.GOLD_SWORD,1));
				meta = inHand.getItemMeta();
				meta.setDisplayName("\u221E");
				inHand.setItemMeta(meta);
				inHand.addEnchantment(Enchantment.DAMAGE_ALL, 3);
				p.sendMessage("I am the bone of my sword");
			}
		}
		else if(meta.getDisplayName().equals("\u221E"))
		{
			if(inHand.getDurability() > 0 && gh.cast(p.getInventory(), 5))
			{
				inHand.setDurability((short) 0);
				p.sendMessage("Trace, on!");				
			}
		}
	}
	
	public void onRemove()
	{
		
	}

}
