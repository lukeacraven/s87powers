package io.github.ramanujansghost.s87powers;

import java.util.List;

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
	public static void onRightClick(Player p)
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
	    	 if(e instanceof LivingEntity)
	    	 {
	    	  ((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 10));
	    	 }
	    	 p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 10));
	      }
	      
	      for (int z = 0; z <= bsize; z++) {
	            zpow = Math.pow(z, 2);
	            for (int x = 0; x <= bsize; x++) {
	                xpow = Math.pow(x, 2);
	                for (int y = 0; y <= bsize; y++) {
	                    if ((xpow + Math.pow(y, 2) + zpow) <= bpow) { 
	                        s.getBlockAt(bx + x, by + y, bz + z).setType(Material.AIR);
	                        s.getBlockAt(bx + x, by + y, bz - z).setType(Material.AIR);
	                        s.getBlockAt(bx - x, by + y, bz + z).setType(Material.AIR);
	                        s.getBlockAt(bx - x, by + y, bz - z).setType(Material.AIR);
	                        s.getBlockAt(bx + x, by - y, bz + z).setType(Material.AIR);
	                        s.getBlockAt(bx + x, by - y, bz - z).setType(Material.AIR);
	                        s.getBlockAt(bx - x, by - y, bz + z).setType(Material.AIR);
	                        s.getBlockAt(bx - x, by - y, bz - z).setType(Material.AIR);
	                    }
	                }
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
