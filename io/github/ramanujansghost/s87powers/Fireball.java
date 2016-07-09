package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

//Pretty simple, just creates a fire-ball
public class Fireball 
{
	public void onRightClick(Player p) 
	{
		GemHelper gh = new GemHelper();
		if(gh.cast(p.getInventory(), 11))
		{
			Vector vel = p.getLocation().getDirection();
			Projectile ball = p.launchProjectile(LargeFireball.class);
			ball.setVelocity(vel);
		}
		
	}

}
