package io.github.ramanujansghost.s87powers;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Wolf;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WolfPack extends Power
{
	ItemStack bone = new ItemStack(Material.BONE, 1);

	public String getName()
	{
		return "WolfPack";
	}

	public String getType()
	{
		return "Combat";
	}

	public String getDescription()
	{
		return "When a player with this power right clicks, they will summon some wolves.";
	}

	private int checkForWolves(Player p)
	{
		int amountOfWolves = 0;
		World tempWorld = p.getWorld();
		UUID playerUUID = p.getUniqueId();
		if (p != null)
		{
			if (playerUUID != null)
			{
				if (tempWorld != null)
				{
					List<Entity> entityList = tempWorld.getEntities();
					if (entityList != null)
					{
						for (Entity entity : entityList)
						{
							if (entity != null)
							{
								if (entity instanceof Wolf)
								{
									if (((Tameable) entity).isTamed())
									{
										AnimalTamer animalTamer = ((Tameable) entity).getOwner();
										if (animalTamer != null)
										{
											UUID animalTamerUUID = animalTamer.getUniqueId();
											if (animalTamerUUID != null)
											{
												if (animalTamerUUID.equals(playerUUID))
												{
													amountOfWolves++;
												}
											}
											else
											{
												S87Powers.log.log(Level.WARNING,
														"Got a null animalTamerUUID in checkForWolves");
											}
										}
										else
										{
											S87Powers.log.log(Level.WARNING,
													"Got a null entityTamer in checkForWolves");
										}
									}
								}
							}
							else
							{
								S87Powers.log.log(Level.WARNING, "Got a null entity in checkForWolves");
							}
						}
					}
					else
					{
						S87Powers.log.log(Level.WARNING, "Got a null entityList in checkForWolves");
					}
				}
				else
				{
					S87Powers.log.log(Level.WARNING, "Got a null world in checkForWolves");
				}
			}
			else
			{
				S87Powers.log.log(Level.WARNING, "Got a null playerUUID in checkForWolves");
			}
		}
		else
		{
			S87Powers.log.log(Level.WARNING, "Got a null player in checkForWolves");
		}
		return amountOfWolves;
	}

	private boolean canPlayerSpawnWolves(Player p)
	{
		if (p != null)
		{
			if (S87Powers.timeSinceWolfSummon.containsKey(p.getUniqueId()))
			{
				Long timeSinceEpoch = S87Powers.timeSinceWolfSummon.get(p.getUniqueId());
				if ((System.currentTimeMillis() - timeSinceEpoch) > 300000) // cooldown of 5 minutes
					return true;
				else
				{
					p.sendMessage("You have used wolfpack too recently!  Wait "
							+ (300000 - (System.currentTimeMillis() - timeSinceEpoch)) / 1000 + " more seconds.");
					return false;
				}
			}
		}
		else
		{
			S87Powers.log.log(Level.WARNING, "Got a null player in canPlayerSpawnWolves");
		}
		return true; // in this case, player has never spawned wolves
	}

	private boolean consumeBones(Player p, int amountOfBones)
	{
		InventoryHelper inventoryHelper = new InventoryHelper();
		if (inventoryHelper != null)
		{
			if (InventoryHelper.checkForReagents(p, bone, amountOfBones))
			{
				inventoryHelper.removeReagents(p, bone, amountOfBones);
				return true;
			}
		}
		else
		{
			S87Powers.log.log(Level.WARNING, "Got a null inventoryHelper in consumeBones");
		}
		return false;
	}

	private void spawnWolf(Player p)
	{
		// S87Powers.log.log(Level.INFO, "Spawning wolf");
		Random r = new Random();
		if (r != null)
		{
			int close = 1; // minimum blocks AWAY from the player it has to spawn
			int far = 5; // max blocks away from player it can spawn
			int x = r.nextInt(far - close) + close;
			int z = r.nextInt(far - close) + close;

			if (p != null)
			{
				World tempWorld = p.getWorld();
				if (tempWorld != null)
				{
					Location location = new Location(tempWorld, x, 1, z);
					if (location != null)
					{
						for (int y = 255; y >= 0; y--)
						{
							location.setY(y);
							Block block = location.getBlock();
							if (block != null)
							{
								Material type = block.getType();
								if (type != null)
								{
									if (type.isSolid()) // find first solid block to spawn them on
									{
										location.setY(y + 1);
										break;
									}
								}
								else
								{

								}
							}
							else
							{

							}
						}
						Entity spawnedWolf = tempWorld.spawnEntity(p.getLocation(), EntityType.WOLF);
						if (spawnedWolf != null)
						{
							((Tameable) spawnedWolf).setOwner(p);
						}
						else
						{
							S87Powers.log.log(Level.WARNING, "Got a null spawnedWolf in SpawnWolf");
						}
					}
					else
					{
						S87Powers.log.log(Level.WARNING, "Got a null location in SpawnWolf");
					}
				}
				else
				{
					S87Powers.log.log(Level.WARNING, "Got a null world in SpawnWolf");
				}
			}
			else
			{
				S87Powers.log.log(Level.WARNING, "Got a null player in SpawnWolf");
			}
		}
		else
		{
			S87Powers.log.log(Level.WARNING, "Got a null random in SpawnWolf");
		}
	}

	public void onBoneRightClick(PlayerInteractEvent event)
	{
		Player p = event.getPlayer();
		// S87Powers.log.log(Level.INFO, "rightclick");

		if (canPlayerSpawnWolves(p))
		{
			int ownedWolves = checkForWolves(p);
			int spawnedWolves = 0;
			if (ownedWolves >= 3)
			{
				p.sendMessage("You have too many wolves!  You currently have " + ownedWolves + " wolves.");
			}
			else
			{
				while (checkForWolves(p) < 3)
				{
					S87Powers.log.log(Level.INFO, String.valueOf(checkForWolves(p)));
					consumeBones(p, 5);
					spawnWolf(p);
					spawnedWolves++;
				}
				if (spawnedWolves == 1)
				{
					p.sendMessage(
							"The wolves heard you howling for help.  " + spawnedWolves + " wolf has joined your side.");
				}
				else
				{
					p.sendMessage("The wolves heard you howling for help.  " + spawnedWolves
							+ " wolves have joined your side.");
				}
				S87Powers.timeSinceWolfSummon.put(p.getUniqueId(), System.currentTimeMillis());
			}
		}
	}
}
