package io.github.ramanujansghost.s87powers;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityShootBowEvent;
import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PowersListener implements Listener
{
	//Checks for player use of an item.
	//Used for Bestial Transmutation and Wolfpack
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)
	{
		ItemStack item = event.getItem();
		if (item != null)
		{
			Material itemUsed = item.getType();
			if (itemUsed != null)
			{
				Action actionPerformed = event.getAction();
				if (actionPerformed != null)
				{
					Player p = event.getPlayer();

					if (p != null)
					{
						if ((itemUsed == Material.RAW_CHICKEN || itemUsed == Material.RABBIT
								|| itemUsed == Material.MUTTON || itemUsed == Material.PORK
								|| itemUsed == Material.RAW_BEEF) && (p.hasPermission("s87powers.bestialtransmutation.toggledon"))
								&& (actionPerformed == Action.LEFT_CLICK_AIR
										|| actionPerformed == Action.LEFT_CLICK_BLOCK))
						{
							BestialTransmutation bestialTransmutation = new BestialTransmutation();
							bestialTransmutation.onRawMeatUse(event);
						}
						if ((itemUsed == Material.BONE)
								&& (actionPerformed == Action.RIGHT_CLICK_AIR
										|| actionPerformed == Action.RIGHT_CLICK_BLOCK)
								&& (p.hasPermission("s87powers.wolfpack.toggledon")))
						{
							WolfPack wolfPack = new WolfPack();
							wolfPack.onBoneRightClick(event);
						}
					}
					else
					{
						S87Powers.log.log(Level.WARNING, "Got a null player in onPlayerUse");
					}
				}
				else
				{
					S87Powers.log.log(Level.WARNING, "Got a null action in onPlayerUse");
				}
			}
			else
			{
				S87Powers.log.log(Level.WARNING, "Got a null material in onPlayerUse");
			}
		}
	}
	
	//Check for breaking block
	//Used for lumberjack
	@EventHandler
	public void onPlayerBlockBreak(BlockBreakEvent event)
	{
		Player p = event.getPlayer();
		if (p != null)
		{
			if (p.hasPermission("s87powers.lumberjack.toggledon"))
			{
				Material itemUsed = event.getPlayer().getEquipment().getItemInMainHand().getType();

				if (itemUsed != null)
				{
					Material blockBroken = event.getBlock().getType();
					if (blockBroken != null)
					{
						if ((itemUsed == Material.DIAMOND_AXE || itemUsed == Material.GOLD_AXE
								|| itemUsed == Material.IRON_AXE || itemUsed == Material.STONE_AXE
								|| itemUsed == Material.WOOD_AXE)
								&& ((blockBroken == Material.LOG || blockBroken == Material.LOG_2)))
						{
							Lumberjack lumberjack = new Lumberjack();
							lumberjack.onLogBreak(event);
						}
					}
					else
					{
						S87Powers.log.log(Level.WARNING, "Got a null blockBroken in onPlayerBlockBreak");
					}
				}
				else
				{
					S87Powers.log.log(Level.WARNING, "Got a null itemUsed in onPlayerBlockBreak");
				}
			}
		}
		else
		{
			S87Powers.log.log(Level.WARNING, "Got a null player in onPlayerBlockBreak");
		}
	}
	
	//Check for shooting bow
	//Used for ChargeBow
	@EventHandler
	public void onBowShootEvent(EntityShootBowEvent event)
	{
		if (event.getEntity().getType() == EntityType.PLAYER)
		{
			if (event.getEntity().hasPermission("s87powers.chargebow.toggledon"))
			{
				ChargeBow chargeBow = new ChargeBow();
				chargeBow.onBowShootEvent(event);
			}
		}
	}
	
	//Check for entity being damaged by entity
	//Used for Reflexes
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event)
	{
		if (event != null)
		{
			Entity damagedEntity = event.getEntity();
			if(damagedEntity.getType() == EntityType.PLAYER)
			{
				if (event.getDamager().getType() == EntityType.ARROW
						|| event.getDamager().getType() == EntityType.SPECTRAL_ARROW
						|| event.getDamager().getType() == EntityType.TIPPED_ARROW)
				{
					if(damagedEntity.hasPermission("s87powers.reflexes.toggledon"))
					{
						//New
						Reflexes r = new Reflexes();
						r.reflexOnHit(event, damagedEntity);
					}
				}
			}
		}
	}
	
	//Check if an entity is (right?)clicked by a player
	//Used for Siphon
	@EventHandler
	public void PlayerInteractEntityEvent(Player p, Entity clickedEntity, EquipmentSlot hand)
	{
		Material itemUsed = p.getEquipment().getItemInMainHand().getType();
		if ((itemUsed == Material.DIAMOND || itemUsed == Material.EMERALD || itemUsed == Material.QUARTZ)
				&& (p.hasPermission("s87powers.siphon.toggledon")))
		{
			Siphon siphon = new Siphon();
			//do siphon
		}
	}
}
