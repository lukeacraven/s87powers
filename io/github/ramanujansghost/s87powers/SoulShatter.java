package io.github.ramanujansghost.s87powers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SoulShatter 
{
	public static void onRightClick(Player p) throws SQLException
	{
		System.out.println("Begin Shatter");
		World s = p.getWorld();
		List<Block> blocks = null;
		int bsize = 30;
        double bpow = Math.pow(bsize + .5, 2);
        double zpow;
        double xpow;
        int bx = (int)p.getLocation().getX();
        int by = (int)p.getLocation().getY();
        int bz =(int) p.getLocation().getZ();
		
	      for(Entity e: p.getNearbyEntities(40, 40, 40))
	      {
	    	 if(e instanceof Player)
	    	 {
	    	  ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 10));
	    	  ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 10));
	    	 }
	    	 p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 10));
	    	 p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 10));
	      }
	      //.setType(Material.AIR)
	      ArrayList<Block> destroy = new ArrayList<Block>();
	      for (int z = 0; z <= bsize; z++) {
	            zpow = Math.pow(z, 2);
	            for (int x = 0; x <= bsize; x++) {
	                xpow = Math.pow(x, 2);
	                for (int y = 0; y <= bsize; y++) {
	                    if ((xpow + Math.pow(y, 2) + zpow) <= bpow) { 
	                    	destroy.add(s.getBlockAt(bx + x, by + y, bz + z));
	                    	destroy.add(s.getBlockAt(bx + x, by + y, bz - z));
	                    	destroy.add(s.getBlockAt(bx - x, by + y, bz + z));
	                    	destroy.add(s.getBlockAt(bx - x, by + y, bz - z));
	                    	destroy.add(s.getBlockAt(bx + x, by - y, bz + z));
	                    	destroy.add(s.getBlockAt(bx + x, by - y, bz - z));
	                    	destroy.add(s.getBlockAt(bx - x, by - y, bz + z));
	                    	destroy.add(s.getBlockAt(bx - x, by - y, bz - z));
	                    }
	                }
	            }
	        }
	      for(Block b : destroy)
	      {
				if(b.getType() == Material.OBSIDIAN)
				{
					System.out.println("Is Obby");
					
					for(Iterator<Map.Entry<Block, Integer>> it = S87Powers.slipGateLocs.entrySet().iterator(); it.hasNext(); )
					{
					      Entry<Block, Integer> entry = it.next();
							if(b.getLocation().distanceSquared(entry.getKey().getLocation()) < 16)
							{
								System.out.println("Is <16");
								for(Block gateBlock : GateBuilder.checkShape(entry.getKey().getRelative(0, -1, 0), entry.getValue()))
								{
									if(b.equals(gateBlock))
									{
										System.out.println("Is Gate");
										entry.getKey().setType(Material.AIR);
										entry.getKey().getRelative(0,1,0).setType(Material.AIR);
										GateBuilder.removeGateFromDB(entry.getKey(), entry.getValue());
										it.remove();

										b.setType(Material.AIR);
									}
								}
							}
							else
							{
								b.setType(Material.AIR);
							}
					}
				}
				else
				{
					b.setType(Material.AIR);
				}
	      }
	      
	      for(Entity e: p.getNearbyEntities(40, 40, 40))
	      {
	    	 if(e instanceof LivingEntity)
	    	 {
	    	  ((LivingEntity) e).damage(1000);
	    	 }
	    	 p.damage(1000);
	      }
	      System.out.println("End Shatter");
	}
	

}
