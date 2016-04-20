package io.github.ramanujansghost.s87powers;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Wolf;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WolfPack
{
	ItemStack bone = new ItemStack(Material.BONE,1);
	
	private int checkForWolves(Player p)
	{
		S87Powers.log.log(Level.INFO, "checkWolves");
		int amountOfWolves = 0;
		World tempWorld = p.getWorld();
		List<Entity> entities = tempWorld.getEntities();
		for (Entity entity : entities)
		{
		    if (entity instanceof Wolf)
		    {
		        if (((Tameable) entity).isTamed())
		        {
		            if (((Tameable) entity).getOwner().getUniqueId().equals(p.getUniqueId()))
		            {
		                amountOfWolves++;
		            }
		        }
		    }
		}
		S87Powers.log.log(Level.INFO, String.valueOf(amountOfWolves));
		return amountOfWolves;
	}
	
	private boolean canPlayerSpawnWolves(Player p)
	{
		if(S87Powers.timeSinceWolfSummon.containsKey(p.getUniqueId()))
		{
			Long timeSinceEpoch = S87Powers.timeSinceWolfSummon.get(p.getUniqueId());
			if((System.currentTimeMillis() - timeSinceEpoch) >  300000)	//cooldown of 5 minutes
			{
				return true;
			}
			p.sendMessage("You have used wolfpack too recently!  Wait " 
			+ (300000 - (System.currentTimeMillis() - timeSinceEpoch))/1000 + " more seconds.");
			return false;
		}
		return true;   //in this case, player has never spawned wolves
	}
	
	private boolean consumeBones(Player p, int amountOfBones)
	{
		InventoryHelper inventoryHelper = new InventoryHelper();
		if(InventoryHelper.checkForReagents(p, bone, amountOfBones))
		{
			inventoryHelper.removeReagents(p, bone, amountOfBones);
			return true;
		}
		return false;
	}
	
	private void spawnWolf(Player p)
	{
		S87Powers.log.log(Level.INFO, "Spawning wolf");
		Random r = new Random();
		World tempWorld = p.getWorld();
		int close = 2; //minimum blocks AWAY from the player it has to spawn
		int far = 5; //max blocks away from player it has to spawn;

		int x = r.nextInt(far - close) + close; 
		int z = r.nextInt(far - close) + close;
		Location location = new Location(tempWorld, x, 1, z);
		for (int y = 255; y >= 0; y --)
		{
            location.setY(y);
            
            if (location.getBlock().getType().isSolid()) //find first solid block to spawn them on
            {
                location.setY(y + 1);
                break;
            }
        }
		Entity spawnedWolf = tempWorld.spawnEntity(p.getLocation(), EntityType.WOLF);
		
		((Tameable)spawnedWolf).setOwner(p);
		S87Powers.log.log(Level.INFO, "Spawning wolf2");
	}
	
	public void onBoneRightClick(PlayerInteractEvent event)
	{
		Player p = event.getPlayer();
		S87Powers.log.log(Level.INFO, "rightclick");
		
		if(canPlayerSpawnWolves(p))
		{
			int ownedWolves = checkForWolves(p);
			int spawnedWolves = 0;
			if(ownedWolves >= 3)
			{
				p.sendMessage("You have too many wolves!  You currently have " + ownedWolves + " wolves.");
			}
			else
			{
				while(checkForWolves(p) < 3)
				{
					S87Powers.log.log(Level.INFO, String.valueOf(checkForWolves(p)));
					consumeBones(p, 5);
					spawnWolf(p);
					spawnedWolves++;
				}
				p.sendMessage("The wolves heard you howling for help. " + spawnedWolves + " wolves have joined your side.");
				S87Powers.timeSinceWolfSummon.put(p.getUniqueId(), System.currentTimeMillis());
			}	
		}
	}
}
