package io.github.ramanujansghost.s87powers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Lumberjack extends Power
{
	private short blocksBroken = 0;
	private int maxBlocksToBreak = 0;
	Queue<Block> destroyQueue = new LinkedList<Block>();
	private static final int goldAxeDurability = 33;
	private static final int woodAxeDurability = 60;
	private static final int stoneAxeDurability = 132;
	private static final int ironAxeDurability = 251;
	private static final int diamondAxeDurability = 1562;

	//Constructor
	public Lumberjack()
	{
		name = "Lumberjack";
		type = "Utility";
		description = "Allows one to rapidly cut down trees.";
	}
	
	//When a log is broken, check and see if player has an axe
	//Call helper functions to break blocks and remove axe durability
	public void onLogBreak(BlockBreakEvent event)
	{
		short initialDurability = 0;
		short damageCaused = 0;

		Player p = event.getPlayer();
		if (p != null)
		{
			ItemStack axeItem = p.getEquipment().getItemInMainHand();
			if (axeItem != null)
			{
				Material axeType = axeItem.getType();

				initialDurability = axeItem.getDurability();
				blocksBroken = 0;
				maxBlocksToBreak = findMaxBlocksToBreak(axeItem);

				breakAdjacentBlocks(p, event.getBlock());
				Iterator<Block> iterator = destroyQueue.iterator();
				Block currentBlock;
				if (iterator != null)
				{
					while (iterator.hasNext())
					{
						currentBlock = iterator.next();
						if (currentBlock != null)
						{
							currentBlock.breakNaturally();
						}
						iterator.remove();
					}
				}

				int levelOfDurabilityEnchantment = axeItem
						.getEnchantmentLevel(Enchantment.DURABILITY);
				// damageCaused uses the official minecraft formula to calculate
				// damage based on durability enchant
				if (levelOfDurabilityEnchantment != 0)
					damageCaused = (short) (blocksBroken / (axeItem
							.getEnchantmentLevel(Enchantment.DURABILITY) + 1));
				else
					damageCaused = blocksBroken;

				if (S87Powers.debugLumberjack)
				{
					S87Powers.log.log(Level.INFO,
							"Initial durability: " + initialDurability);
					S87Powers.log.log(Level.INFO,
							"Damage caused: " + damageCaused + " from breaking "
									+ blocksBroken + " blocks");
				}

				axeItem.setDurability(
						(short) (initialDurability + damageCaused));
				short axeDurability = axeItem.getDurability();
				axeItem = p.getEquipment().getItemInMainHand(); // update
																// axeItem
																// because we
																// changed it by
																// modifying
																// durability

				if (axeItem != null)
				{
					if (axeType == Material.GOLD_AXE)
					{
						if (axeDurability >= goldAxeDurability)
							InventoryHelper.breakTool(p, axeItem);
					}

					else if (axeType == Material.WOOD_AXE)
					{
						if (axeDurability >= woodAxeDurability)
							InventoryHelper.breakTool(p, axeItem);
					}

					else if (axeType == Material.STONE_AXE)
					{
						if (axeDurability >= stoneAxeDurability)
							InventoryHelper.breakTool(p, axeItem);
					}

					else if (axeType == Material.IRON_AXE)
					{
						if (axeDurability >= ironAxeDurability)
							InventoryHelper.breakTool(p, axeItem);
					}

					else if (axeType == Material.DIAMOND_AXE)
					{
						if (axeDurability >= diamondAxeDurability)
							InventoryHelper.breakTool(p, axeItem);
					}
				}
			}
		}
	}
	
	//Check how many blocks can be broken with axe durability
	public int findMaxBlocksToBreak(ItemStack axe)
	{
		if (axe != null)
		{
			Material axeType = axe.getType();
			if (axeType != null)
			{
				int axeDurability = axe.getDurability();
				int levelOfDurabilityEnchantment = axe
						.getEnchantmentLevel(Enchantment.DURABILITY);
				// damageCaused uses the official minecraft formula to calculate
				// damage based on durability enchant
				if (levelOfDurabilityEnchantment != 0)
				{
					if (axeType == Material.GOLD_AXE)
					{
						return (goldAxeDurability - axeDurability)
								* ((levelOfDurabilityEnchantment + 1) / 1) + 1;
					}

					else if (axeType == Material.WOOD_AXE)
					{
						return (woodAxeDurability - axeDurability)
								* ((levelOfDurabilityEnchantment + 1) / 1) + 1;
					}

					else if (axeType == Material.STONE_AXE)
					{
						return (stoneAxeDurability - axeDurability)
								* ((levelOfDurabilityEnchantment + 1) / 1) + 1;
					}

					else if (axeType == Material.IRON_AXE)
					{
						return (ironAxeDurability - axeDurability)
								* ((levelOfDurabilityEnchantment + 1) / 1) + 1;
					}

					else if (axeType == Material.DIAMOND_AXE) { return (diamondAxeDurability
							- axeDurability)
							* ((levelOfDurabilityEnchantment + 1) / 1) + 1; }
				}
				else
				{
					if (axeType == Material.GOLD_AXE)
					{
						return goldAxeDurability - axeDurability + 1;
					}

					else if (axeType == Material.WOOD_AXE)
					{
						return woodAxeDurability - axeDurability + 1;
					}

					else if (axeType == Material.STONE_AXE)
					{
						return stoneAxeDurability - axeDurability + 1;
					}

					else if (axeType == Material.IRON_AXE)
					{
						return ironAxeDurability - axeDurability + 1;
					}

					else if (axeType == Material.DIAMOND_AXE) { return diamondAxeDurability
							- axeDurability + 1; }
				}
			}
		}
		return -1;
	}
	
	//Break connected log blocks 
	public void breakAdjacentBlocks(Player p, Block block) // use a for loop
															// here and check a
															// 3x3 cube centered
															// on the given
															// block, but do not
															// check center
															// block.
	{
		if (block != null)
		{
			if (p != null)
			{
				if (S87Powers.debugLumberjack) S87Powers.log.log(Level.INFO,
						"Received block at " + block.getX() + ", "
								+ block.getY() + ", " + block.getZ()
								+ " checking around block.");

				for (int y = -1; y < 2; y++)
				{
					for (int x = -1; x < 2; x++)
					{
						for (int z = -1; z < 2; z++)
						{
							if (!(x == 0 && y == 0 && z == 0)) // do not check
																// the original
																// block -- this
																// would cause
																// an infinite
																// loop
							{
								if (block.getLocation().add(x, y, z).getBlock()
										.getType() == Material.LOG // check if
																	// block is
																	// of type
																	// log or
																	// leaf
										|| block.getLocation().add(x, y, z)
												.getBlock()
												.getType() == Material.LOG_2
										|| block.getLocation().add(x, y, z)
												.getBlock()
												.getType() == Material.LEAVES
										|| block.getLocation().add(x, y, z)
												.getBlock()
												.getType() == Material.LEAVES_2)
								{
									if (S87Powers.canPlayerBuildAt(p,
											block.getLocation().add(x, y, z),
											block.getLocation().add(x, y, z)
													.getBlock())
											&& (blocksBroken <= maxBlocksToBreak)
											&& blocksBroken <= 150
											&& (!destroyQueue.contains(block
													.getLocation().add(x, y,
															z)
													.getBlock())))
									{
										// block.getLocation().add(x,y,z).getBlock().breakNaturally();
										destroyQueue.add(block.getLocation()
												.add(x, y, z).getBlock());
										blocksBroken++;
										breakAdjacentBlocks(p,
												block.getLocation().add(x, y, z)
														.getBlock());
									}
								}
							}
						}
					}
				}
			}
		}

		// failed to find additional blocks to destroy
		return;
	}
}
