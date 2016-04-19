package io.github.ramanujansghost.s87powers;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;


//make Lumberjack respect Factions territories
public class Lumberjack extends Power
{
	private static short blocksBroken = 0;
	private final int diamondAxeDurability = 1561;
	
	@Override
	public String getName()
	{
	    return getPowerName();
	}
	@Override
	public String getDescription()
	{
	    return getPowerDescription();
	}
	public static String getPowerName()
	{
		return "Lumberjack";
	}
	public static String getPowerDescription()
	{
		return "Allows player to rapidly cut down trees";
	}
	public static void onLogBreak(BlockBreakEvent event)
	{
		short initialDurability = 0;
		short damageCaused = 0;
		
		if(event.getPlayer().getEquipment().getItemInMainHand() != null)
		{
			initialDurability = event.getPlayer().getEquipment().getItemInMainHand().getDurability();
		}
		else
		{
			S87Powers.log.log(Level.WARNING, event.getPlayer() + "'s axe is missing..  perhaps it broke? (Was checking durability)");
			return;
		}
			
		blocksBroken = 0;
		breakAdjacentBlocks(event.getPlayer(), event.getBlock());
		int durabilityEnchantment = event.getPlayer().getEquipment().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY);
		S87Powers.log.log(Level.INFO, "Durability enchantment level: " + durabilityEnchantment);
		damageCaused = (short) (blocksBroken + 1);
		
		if(S87Powers.debugPowers)
		{
			S87Powers.log.log(Level.INFO, "Initial durability: " + initialDurability);
			S87Powers.log.log(Level.INFO, "Damage caused: " + damageCaused);
		}

		/*
		if(event.getPlayer().getEquipment().getItemInMainHand() != null)
		{
			event.getPlayer().getEquipment().getItemInMainHand().setDurability((short) (initialDurability + damageCaused));
		}
		else
		{
			S87Powers.log.log(Level.WARNING, event.getPlayer() + "'s axe is missing..  perhaps it broke? (Was setting durability)");
			return;
		}
		*/
	}
	
	//make breakAdjacentBlocks method damage player's axe
	public static void breakAdjacentBlocks(Player p, Block block)	//use a for loop here and check a 3x3 cube centered on the given block, but do not check center block.
	{
		if(S87Powers.debugPowers)
			S87Powers.log.log(Level.INFO, "Received block " + block.getX() + ", " + block.getY() + ", " + block.getZ());
		for(int x = -1; x < 2; x++)
		{
			for(int y = -1; y < 2; y++)
			{
				for(int z = -1; z < 2; z++)
				{
					if(!(x == 0 && y == 0 && z == 0))	//do not check the original block -- this would cause an infinite loop
					{
						if(block.getLocation().add(x,y,z).getBlock().getType() == Material.LOG 			//check if block is of type log or leaf
								|| block.getLocation().add(x,y,z).getBlock().getType() == Material.LOG_2 
								|| block.getLocation().add(x,y,z).getBlock().getType() == Material.LEAVES
								|| block.getLocation().add(x,y,z).getBlock().getType() == Material.LEAVES_2)
						if(S87Powers.canPlayerBuildAt(p, block.getLocation().add(x,y,z), block.getLocation().add(x,y,z).getBlock()))
						{
							block.getLocation().add(x,y,z).getBlock().breakNaturally();
							blocksBroken++;
							breakAdjacentBlocks(p,block.getLocation().add(x,y,z).getBlock());
						}
					}
				}
			}
		}
		//failed to find additional blocks to destroy, return
		return;
	}
}
