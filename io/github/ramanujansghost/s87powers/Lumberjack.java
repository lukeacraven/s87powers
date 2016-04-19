package io.github.ramanujansghost.s87powers;

import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Lumberjack extends Power
{
	private static short blocksBroken = 0;
	private static final int goldAxeDurability = 33;
	private static final int woodAxeDurability = 60;
	private static final int stoneAxeDurability = 132;
	private static final int ironAxeDurability = 251;
	private static final int diamondAxeDurability = 1562;
	
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
		
		Player p = event.getPlayer();
		ItemStack axeItem = p.getEquipment().getItemInMainHand();
		Material axeType = axeItem.getType();
		
		initialDurability = axeItem.getDurability();
		blocksBroken = 0;
		
		breakAdjacentBlocks(p, event.getBlock());
		
		int levelOfDurabilityEnchantment = axeItem.getEnchantmentLevel(Enchantment.DURABILITY);
		//damageCaused uses the official minecraft formula to calculate damage based on durability enchant
		if(levelOfDurabilityEnchantment != 0)
			damageCaused = (short) (blocksBroken/(axeItem.getEnchantmentLevel(Enchantment.DURABILITY)+1));
		else
			damageCaused = blocksBroken;
		
		if(S87Powers.debugLumberjack)
		{
			S87Powers.log.log(Level.INFO, "Initial durability: " + initialDurability);
			S87Powers.log.log(Level.INFO, "Damage caused: " + damageCaused + " from breaking " + blocksBroken + " blocks");
		}

		axeItem.setDurability((short) (initialDurability + damageCaused));
		short axeDurability = axeItem.getDurability();
		axeItem = p.getEquipment().getItemInMainHand();		//update axeItem because we changed it by modifying durability
			
		if(axeType  == Material.GOLD_AXE)
		{
			if(axeDurability > goldAxeDurability)
				InventoryHelper.breakTool(p, axeItem);
		}
					
		else if(axeType  == Material.WOOD_AXE)
		{
			if(axeDurability > woodAxeDurability)
				InventoryHelper.breakTool(p, axeItem);
		}

		else if(axeType  == Material.STONE_AXE)
		{
			if(axeDurability > stoneAxeDurability)
				InventoryHelper.breakTool(p, axeItem);
		}
			
		else if(axeType  == Material.IRON_AXE)
		{
			if(axeDurability > ironAxeDurability)
				InventoryHelper.breakTool(p, axeItem);
		}
			
		else if(axeType  == Material.DIAMOND_AXE)
		{
			if(axeDurability > diamondAxeDurability)
				InventoryHelper.breakTool(p, axeItem);
		}	
	}
	
	//make breakAdjacentBlocks method damage player's axe
	public static void breakAdjacentBlocks(Player p, Block block)	//use a for loop here and check a 3x3 cube centered on the given block, but do not check center block.
	{
		if(S87Powers.debugLumberjack)
			S87Powers.log.log(Level.INFO, "Received block at " + block.getX() + ", " + block.getY() + ", " + block.getZ() + " checking around block.");
		
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
