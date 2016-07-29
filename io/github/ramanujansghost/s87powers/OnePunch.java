package io.github.ramanujansghost.s87powers;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class OnePunch
{
	public void onAttack(EntityDamageByEntityEvent event)
	{
		Player p = (Player)event.getDamager();
		if(p.getInventory().getItemInMainHand().getType() == Material.AIR && p.getInventory().getItemInOffHand().getType() == Material.AIR)
		{
			if(PlayerHelper.getArmorCount(p) == 0)
			{
				if(p.getActivePotionEffects().isEmpty())
				{
					Entity target = event.getEntity();
					Vector push = p.getLocation().getDirection();
					target.leaveVehicle();
					target.setPassenger(null);
					target.setVelocity(new Vector(push.getX()*(50), (push.getY()*50), push.getZ()*50));
					event.setDamage(1000);
					p.sendMessage("ONE PUUUUUNNNNNNNNNNCHHHHHHHHHHHH!!!!");
					p.spawnParticle(Particle.FLAME, target.getLocation(), 10);
					p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 100, 1);
				}
			}
		}	
	}


}
