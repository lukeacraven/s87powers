package io.github.ramanujansghost.s87powers;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TravelAgent;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PowersListener implements Listener
{
	//Checks for player use of an item.
	//Used for Bestial Transmutation, Ensnare, and Wolfpack
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
						//Also not ready
						if ((itemUsed == Material.STRING) 
								&& (actionPerformed == Action.RIGHT_CLICK_AIR
								|| actionPerformed == Action.RIGHT_CLICK_BLOCK))
						{
							Ensnare ensnare = new Ensnare();
							ensnare.deployWebbing(p);
						}
						if ((itemUsed == Material.DIAMOND || itemUsed == Material.EMERALD || itemUsed == Material.QUARTZ) && actionPerformed == Action.RIGHT_CLICK_AIR)
						{
							ItemMeta im = p.getInventory().getItemInMainHand().getItemMeta();
							if(im.hasDisplayName())
							{
								if(im.getDisplayName().equals("Soul Gem"))
								{
									Siphon.onRightClick(p, p, 1);
								}
							}
							else
							{
								GemHelper.onRightClick(event);
							}
						}
						//testing Wall
						if(itemUsed == Material.GLOWSTONE_DUST && actionPerformed == Action.RIGHT_CLICK_BLOCK )//&& p.getTargetBlock(S87Powers.empty, 3).getType() == Material.WATER)
						{
							Wall.createWall(p);
						}

						//Leap test
						if(itemUsed == Material.RABBIT_FOOT && (actionPerformed == Action.RIGHT_CLICK_BLOCK || actionPerformed == Action.RIGHT_CLICK_AIR))//&& p.getTargetBlock(S87Powers.empty, 3).getType() == Material.WATER)
						{
							Leap.onRightClick(p);
						}
						//Push test
						if(itemUsed == Material.STICK && (actionPerformed == Action.RIGHT_CLICK_BLOCK || actionPerformed == Action.RIGHT_CLICK_AIR))//&& p.getTargetBlock(S87Powers.empty, 3).getType() == Material.WATER)
						{
							Push.onRightClick(p);
						}
						if(itemUsed == Material.FEATHER && (actionPerformed == Action.RIGHT_CLICK_BLOCK || actionPerformed == Action.RIGHT_CLICK_AIR))//&& p.getTargetBlock(S87Powers.empty, 3).getType() == Material.WATER)
						{
							Levitate.onRightClick(p);
						}
						if(itemUsed == Material.BLAZE_ROD && (actionPerformed == Action.RIGHT_CLICK_BLOCK || actionPerformed == Action.RIGHT_CLICK_AIR))//&& p.getTargetBlock(S87Powers.empty, 3).getType() == Material.WATER)
						{
							Fireball.onRightClick(p);
						}
						if(itemUsed == Material.SEEDS && (actionPerformed == Action.RIGHT_CLICK_BLOCK || actionPerformed == Action.RIGHT_CLICK_AIR))//&& p.getTargetBlock(S87Powers.empty, 3).getType() == Material.WATER)
						{
							Leviosa.onRightClick(p);
						}
						if(itemUsed == Material.COAL && (actionPerformed == Action.RIGHT_CLICK_BLOCK || actionPerformed == Action.RIGHT_CLICK_AIR))//&& p.getTargetBlock(S87Powers.empty, 3).getType() == Material.WATER)
						{
							Translocation.onRightClick(p);
						}
						if(itemUsed == Material.ARROW && (actionPerformed == Action.RIGHT_CLICK_BLOCK || actionPerformed == Action.RIGHT_CLICK_AIR))//&& p.getTargetBlock(S87Powers.empty, 3).getType() == Material.WATER)
						{
							Mount.onRightClick(p);
						}
						if(itemUsed == Material.CLAY_BRICK && (actionPerformed == Action.RIGHT_CLICK_BLOCK || actionPerformed == Action.RIGHT_CLICK_AIR))//&& p.getTargetBlock(S87Powers.empty, 3).getType() == Material.WATER)
						{
							Blink.onRightClick(p);
						}
						
						if(itemUsed == Material.AIR && (actionPerformed == Action.RIGHT_CLICK_AIR || actionPerformed == Action.RIGHT_CLICK_BLOCK))
						{
							System.out.println(p.getInventory().getHeldItemSlot());
						}
						if(itemUsed == Material.NETHER_STAR && (actionPerformed == Action.RIGHT_CLICK_AIR || actionPerformed == Action.RIGHT_CLICK_BLOCK))
						{
							Possess.onRightClick(p);
						}
						if(itemUsed == Material.FLINT && (actionPerformed == Action.RIGHT_CLICK_AIR || actionPerformed == Action.RIGHT_CLICK_BLOCK))
						{
							Cloak.onRightClick(p);
						}

						if(itemUsed == Material.CLAY_BALL && (actionPerformed == Action.RIGHT_CLICK_AIR || actionPerformed == Action.RIGHT_CLICK_BLOCK))
						{
							SoulShatter.onRightClick(p);
						}

						if(itemUsed == Material.INK_SACK && (actionPerformed == Action.RIGHT_CLICK_AIR || actionPerformed == Action.RIGHT_CLICK_BLOCK))
						{
							Juggernaut.onRightClick(p);
						}
						if(itemUsed == Material.BLAZE_POWDER && (actionPerformed == Action.RIGHT_CLICK_AIR || actionPerformed == Action.RIGHT_CLICK_BLOCK))
						{
							Shell.onRightClick(p);
						}		
						if(itemUsed == Material.BOOK && (actionPerformed == Action.RIGHT_CLICK_AIR || actionPerformed == Action.RIGHT_CLICK_BLOCK))
						{
							GateBuilder.onRightClick(p);
						}

					}
					else
					{
						S87Powers.LOG.log(Level.WARNING, "Got a null player in onPlayerUse");
					}
				}
				else
				{
					S87Powers.LOG.log(Level.WARNING, "Got a null action in onPlayerUse");
				}
			}
			else
			{
				S87Powers.LOG.log(Level.WARNING, "Got a null material in onPlayerUse");
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
						S87Powers.LOG.log(Level.WARNING, "Got a null blockBroken in onPlayerBlockBreak");
					}
				}
				else
				{
					S87Powers.LOG.log(Level.WARNING, "Got a null itemUsed in onPlayerBlockBreak");
				}
			}
		}
		else
		{
			S87Powers.LOG.log(Level.WARNING, "Got a null player in onPlayerBlockBreak");
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
	//Used for Letta
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
						Letta letta = new Letta();
						letta.onArrowHitPlayer(event, damagedEntity);
					}
				}
			}
		}
	}
	
	//Check if an entity is (right?)clicked by a player
	//Used for Siphon
	@EventHandler
	 public void onEntityInteract(PlayerInteractEntityEvent event) 
	{
		if((!S87Powers.timeSinceInteract.containsKey(event.getPlayer().getUniqueId()) || System.currentTimeMillis() - S87Powers.timeSinceInteract.get(event.getPlayer().getUniqueId()) > 500))
		{	
			//Material itemUsed = event.getPlayer().getInventory().getItemInMainHand().getType();
			if(event.getRightClicked() != null && event.getPlayer() != null)
			{
				Entity clickedEntity = event.getRightClicked();
				Player p = event.getPlayer();
				ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
				if (meta.hasDisplayName() && meta.getDisplayName().equals("Soul Gem")) //&& (p.hasPermission("s87powers.siphon.toggledon")
				{
					Siphon.onRightClick(clickedEntity, p, 1);
				}
			}
			S87Powers.timeSinceInteract.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());
		}

	}
	@EventHandler
	public void onEntityPortal(EntityPortalEnterEvent e)
	{
		//System.out.println(e.getEntity() + "-" + e.getLocation());
	}
	
	@EventHandler
	public void onPlayerPortal(PlayerPortalEvent e)
	{
		System.out.print(e.getFrom().getBlock() + "vs");
		System.out.println(S87Powers.slipGates);
		Player p = e.getPlayer();
		if(S87Powers.slipGates.contains(e.getFrom().getBlock().getRelative(1, 0, 0)) || S87Powers.slipGates.contains(e.getFrom().getBlock().getRelative(-1, 0, 0)))
		{
			e.setCancelled(true);
			p.teleport(new Location(p.getWorld(), 100, 100, 100));
			System.out.println("Override");
		}
	}

}
