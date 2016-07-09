package io.github.ramanujansghost.s87powers;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;


public class PowersListener implements Listener
{
	//Checks for player use of an item.
	//Used for Bestial Transmutation, Ensnare, and Wolfpack
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event) throws SQLException
	{
		if (!(event.getHand() == EquipmentSlot.HAND))
				return;
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
						if(actionPerformed == Action.RIGHT_CLICK_AIR || actionPerformed == Action.RIGHT_CLICK_BLOCK)
						{

							//if (S87Powers.globalCD.containsKey(p.getUniqueId()))
								{
									//Long timeSinceEpoch = S87Powers.globalCD.get(p.getUniqueId());
									//if ((System.currentTimeMillis() - timeSinceEpoch) > 500) // global cd
									{
		
										if(S87Powers.allPlayers.get(p.getUniqueId()).getPowers().size() > p.getInventory().getHeldItemSlot())
										{
											Power used = S87Powers.allPlayers.get(p.getUniqueId()).getPowers().get(p.getInventory().getHeldItemSlot());
											
											System.out.println(used.getId());
											switch(used.getId())
											{
											case 1:
												if ((itemUsed == Material.RAW_CHICKEN || itemUsed == Material.RABBIT
												|| itemUsed == Material.MUTTON || itemUsed == Material.PORK
												|| itemUsed == Material.RAW_BEEF))
												{
													BestialTransmutation bt = new BestialTransmutation();
													bt.onRawMeatUse(event);
													event.setCancelled(true);	
												}
												break;
											case 2:
												if (itemUsed == Material.BONE)
												{
													WolfPack wp = new WolfPack();
													wp.onBoneRightClick(event);
													event.setCancelled(true);	
												}
												break;
											case 3:
												Ensnare en = new Ensnare();
												en.deployWebbing(p);
												event.setCancelled(true);	
												break;
											case 4:
												Siphon si = new Siphon();
												si.onRightClick(p, 14);
												event.setCancelled(true);
												break;
											case 5:
												if(actionPerformed == Action.RIGHT_CLICK_BLOCK )
												{
													Wall wa = new Wall();
													wa.createWall(p);
													event.setCancelled(true);	
												}
												break;
											case 6:
												Leap lp = new Leap();
												lp.onRightClick(p);
												event.setCancelled(true);	
												break;
											case 7:
												Push ph = new Push();
												ph.onRightClick(p);
												event.setCancelled(true);	
												break;
											case 8:
												Levitate lt = new Levitate();
												lt.onRightClick(p);
												event.setCancelled(true);	
												break;
											case 9:
												Fireball fb = new Fireball();
												fb.onRightClick(p);
												event.setCancelled(true);	
												break;
											case 10:
												Leviosa l = new Leviosa();
												l.onRightClick(p);
												event.setCancelled(true);	
												break;
											case 11:
												Translocation tloc = new Translocation();
												tloc.onRightClick(p);
												event.setCancelled(true);	
												break;
											case 12:
												Possess ps = new Possess();
												ps.onRightClick(p);
												event.setCancelled(true);	
												break;
											case 13:
												Cloak clk = new Cloak();
												clk.onRightClick(p);
												event.setCancelled(true);	
												break;
											case 14:
												SoulShatter.onRightClick(p);
												event.setCancelled(true);	
												break;
											case 15:
												Juggernaut jg = new Juggernaut();
												jg.onRightClick(p);
												event.setCancelled(true);	
												break;
											case 16:
												Shell sh = new Shell();
												sh.onRightClick(p);
												event.setCancelled(true);	
												break;
											case 17:
												GateBuilder gb = new GateBuilder();
												gb.onRightClick(p);
												event.setCancelled(true);	
												break;
											case 23:
												FlameAlchemy fa = new FlameAlchemy();
												fa.onRightClick(p);
												event.setCancelled(true);	
												break;
											default:
												break;
											}
											S87Powers.globalCD.put(p.getUniqueId(), System.currentTimeMillis());
										}
										
									}
								}
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
	public void onPlayerBlockBreak(BlockBreakEvent event) throws SQLException
	{
		Player p = event.getPlayer();
		if (p != null)
		{
			if(S87Powers.allPlayers.get(p.getUniqueId()).getPowers().contains(S87Powers.allPowers.get(19)))
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
			//Might be portal
			Block b = event.getBlock();
			if(b.getType() == Material.OBSIDIAN)
			{
				System.out.println("Is Obby");
				for(Entry<Block, Integer> entry : S87Powers.slipGateLocs.entrySet())
				{
					if(b.getLocation().distanceSquared(entry.getKey().getLocation()) < 16)
					{
						System.out.println("Is <16");
						GateBuilder gb = new GateBuilder();
						for(Block gateBlock : gb.checkShape(entry.getKey().getRelative(0, -1, 0), entry.getValue()))
						{
							if(b.equals(gateBlock))
							{
								System.out.println("Is Gate");
								entry.getKey().setType(Material.AIR);
								entry.getKey().getRelative(0,1,0).setType(Material.AIR);
								gb.removeGateFromDB(entry.getKey(), entry.getValue());
								S87Powers.slipGateLocs.remove(entry.getKey());

								return;
							}
						}
					}
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
		if(event.getEntityType() == EntityType.PLAYER)
		{
			Player p = (Player) event.getEntity();
				if(S87Powers.allPlayers.get(p.getUniqueId()).getPowers().size() > p.getInventory().getHeldItemSlot())
				{
					Power used = S87Powers.allPlayers.get(p.getUniqueId()).getPowers().get(p.getInventory().getHeldItemSlot());
					if(used == S87Powers.allPowers.get(17))
					{
						ChargeBow cb = new ChargeBow();
						cb.onBowShootEvent(event);
					}
					else if(used == S87Powers.allPowers.get(21))
					{
						Volley cb = new Volley();
						cb.onBowShootEvent(event);
					}
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
					if(S87Powers.allPlayers.get(event.getEntity().getUniqueId()).getPowers().contains(S87Powers.allPowers.get(18)))
					{
						Letta ltt = new Letta();
						ltt.onArrowHitPlayer(event, damagedEntity);
					}
				}
			}
		}
	}
	
	
	@EventHandler
	public void onShift(PlayerToggleSneakEvent e)
	{
		if(S87Powers.allPlayers.get(e.getPlayer().getUniqueId()).getPowers().contains(S87Powers.allPowers.get(20)))
		{
			WaterStrider ws = new WaterStrider();
			ws.onShift(e);
		}
	}
	
	@EventHandler
	public void onPlayerConnect(PlayerJoinEvent e)
	{
		S87Powers.globalCD.put(e.getPlayer().getUniqueId(), System.currentTimeMillis());
		if(S87Powers.allPlayers.get(e.getPlayer().getUniqueId()).getPowers().isEmpty())
		{
			e.getPlayer().sendMessage(ChatColor.GOLD + "Welcome!");
			e.getPlayer().sendMessage(ChatColor.GOLD + "You have not yet selected any powers.");
			e.getPlayer().sendMessage(ChatColor.GOLD + "Please use the command '/powers list' to see avalialbe powers.");
			e.getPlayer().sendMessage(ChatColor.GOLD + "Use '/powers select <power>' to choose a power.");
		}

	
	}
	
	@EventHandler
	public void playerMove(PlayerMoveEvent e)
	{
		//Only checking movement from block to block to be efficient
		if(e.getTo() != e.getFrom())
		{
			GateBuilder gb = new GateBuilder();
			if(!gb.PlayerMove(e))
			{

			}
		}
	}
	
	@EventHandler
	public void playerLeave(PlayerQuitEvent e )
	{
		for(S87Player p87 : S87Powers.playersOnline)
		{
			if(p87.getId() == e.getPlayer().getUniqueId())
			{
				S87Powers.playersOnline.remove(p87);
			}
		}
	}
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent e ) throws SQLException
	{
		
		if(S87Powers.allPlayers.containsKey(e.getPlayer().getUniqueId()))
		{
			S87Powers.playersOnline.add(S87Powers.allPlayers.get(e.getPlayer().getUniqueId()));
		}
		else
		{
			S87Powers.allPlayers.put(e.getPlayer().getUniqueId(), new S87Player(e.getPlayer().getUniqueId()));
			S87Powers.playersOnline.add(S87Powers.allPlayers.get(e.getPlayer().getUniqueId()));
			PlayerHelper.addPlayer(S87Powers.allPlayers.get(e.getPlayer().getUniqueId()));
		}

	}

}
