package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

//Pretty simple, just creates a fire-ball
public class Fireball 
{
	public static void onRightClick(Player p) 
	{
		if(GemHelper.cast(p.getInventory(), 10))
		{
			Vector vel = p.getLocation().getDirection();
			Projectile ball = p.launchProjectile(LargeFireball.class);
			ball.setVelocity(vel);
		}
		
	}

}
