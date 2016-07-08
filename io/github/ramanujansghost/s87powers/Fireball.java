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
		if(GemHelper.cast(p.getInventory(), 11))
		{
			System.out.println("Cast fireball");
			Vector vel = p.getLocation().getDirection();
			Projectile ball = p.launchProjectile(LargeFireball.class);
			ball.setVelocity(vel);
		}
		
	}

}
