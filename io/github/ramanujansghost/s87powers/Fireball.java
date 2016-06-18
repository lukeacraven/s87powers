package io.github.ramanujansghost.s87powers;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SmallFireball;
import org.bukkit.util.Vector;

public class Fireball 
{
	@SuppressWarnings("null")
	public static void onRightClick(Player p) 
	{
		Vector vel = p.getLocation().getDirection();
		Projectile ball = p.launchProjectile(LargeFireball.class);
		ball.setVelocity(vel);

		
	}

}
