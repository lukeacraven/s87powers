package io.github.ramanujansghost.s87powers;

import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class PlayerHelper
{	
	//Apply damage to player and kill player if damage exceeds their health
	public static void damagePlayer(Player p, String damageSource,
			int damageAmount)
	{
		if (p.getHealth() <= damageAmount)
		{
			// Do stuff here if player is killed by damage!!
			p.damage(damageAmount);
		}
		else
			p.damage(damageAmount);
	}
	
	//Borrowed/Modified code. Rotation is tedious :P
	//Returns the direction a player is facing in a BlockFace (not up and down)
	public static BlockFace getPlayerFacing(Player player) {
	    double rot = (player.getLocation().getYaw()+180) % 360;
	        if (rot < 0) {
	                rot += 360.0;
	        }
	        return getDirection(rot);
	}
	
	//See above ^
	private static BlockFace getDirection(double rot) {
	        if (0 <= rot && rot < 22.5)
	        {
	                return BlockFace.NORTH;
	        } 
	        else if (22.5 <= rot && rot < 67.5)
	        {
	                return BlockFace.NORTH_EAST;
	        } 
	        else if (67.5 <= rot && rot < 112.5)
	        {
	                return BlockFace.EAST;
	        } 
	        else if (112.5 <= rot && rot < 157.5)
	        {
	                return  BlockFace.SOUTH_EAST;
	        } 
	        else if (157.5 <= rot && rot < 202.5)
	        {
	                return  BlockFace.SOUTH;
	        } 
	        else if (202.5 <= rot && rot < 247.5) 
	        {
	                return  BlockFace.SOUTH_WEST;
	        } 
	        else if (247.5 <= rot && rot < 292.5)
	        {
	                return  BlockFace.WEST;
	        } 
	        else if (292.5 <= rot && rot < 337.5)
	        {
	                return  BlockFace.NORTH_WEST;
	        } 
	        else if (337.5 <= rot && rot < 360.0)
	        {
	                return  BlockFace.NORTH;
	        } 
	        else
	        {
	                return null;
	        }
	}
	//borrowed from S86 with permission.
	public static LivingEntity getTarget(Player user, double maxRange) {
		Location startLoc = user.getEyeLocation();
		Vector vec = user.getLocation().getDirection();
		for (int i = 0; i < maxRange; i ++) {
			Location testLoc = new Location(startLoc.getWorld(), startLoc.getX() + (vec.getX() * i), startLoc.getY() + (vec.getY() * i), startLoc.getZ() + (vec.getZ() * i));
			if (Math.abs(startLoc.distance(testLoc)) <= maxRange) {
				Block block = testLoc.getBlock();
				if (block.getType().isOccluding()) break;
				for (Entity entity : block.getChunk().getEntities()) {
					if (entity instanceof LivingEntity && (entity.getLocation().distanceSquared(testLoc) < 1.0D || ((LivingEntity) entity).getEyeLocation().distanceSquared(testLoc) < 1.0D) && entity != user) {
						return (LivingEntity) entity;
					}
					else
					{
						System.out.println(entity);
					}
					}
				}
		}
		return null;
	}

	
	public static void addPlayer(S87Player p) throws SQLException
	{
		Statement stmt = null;
		try {
		System.out.println(p.getId());
		String sql = "INSERT INTO 'S87Powers.PLAYERS' VALUES (\"" + p.getId() + "\")";
		stmt = S87Powers.connection.createStatement();
		stmt.executeUpdate(sql);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(stmt != null) {	//Any exceptions here are thrown
				stmt.close();
			}
		}
	}
}
