package io.github.ramanujansghost.s87powers;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map.Entry;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class GateBuilder 
{
	public void onRightClick(Player p) throws SQLException
	{
		//On center of gate
		Block center = p.getTargetBlock((Set<Material>)null, 4);
		Integer dir = checkDir(center);
		//Check the shape
		if(center != null  && center.getType() == Material.OBSIDIAN && !checkShape(center, dir).isEmpty())
		{
			Block portalBot = center.getRelative(0, 1, 0);
			Block portalTop = center.getRelative(0, 2, 0);
			
			//If clear, create gate, add to DB
			if(portalBot.getType() == Material.AIR && portalTop.getType() == Material.AIR)
			{
				S87Powers.slipGateLocs.put(portalBot, dir);
				addGateToDB(portalBot, dir);
				portalBot.setType(Material.END_GATEWAY);
				portalTop.setType(Material.END_GATEWAY);
			}
			
		}
	}
	
	//Check the direction of a gate and returns a corresponding number (1-4)
	public Integer checkDir(Block start)
	{
		Block dirCheck = start.getRelative(BlockFace.UP);
		if(dirCheck.getRelative(BlockFace.NORTH).getType() == Material.AIR)
		{
			//Facing South, +z
			return 1;
			
		}
		else if((dirCheck.getRelative(BlockFace.SOUTH).getType() == Material.AIR))
		{
			//Facing North, -z
			return 2;
		}
		else if((dirCheck.getRelative(BlockFace.EAST).getType() == Material.AIR))
		{
			//Facing West, -x
			return 3;
		}
		else if((dirCheck.getRelative(BlockFace.WEST).getType() == Material.AIR))
		{
			//Facing East, +x
			return 4;
		}
		
		
		return 0;
	}
	
	//Adds gate to DB 
	public void addGateToDB(Block key, int dir) throws SQLException
	{
		Statement stmt = null;
		try {
		String sql = "INSERT INTO 'S87Powers.SLIPGATES' " +
                "VALUES (NULL, " + key.getX() + " , " + key.getY() + " , " + key.getZ() + " , \'" + key.getWorld().getName() +  "\' , " + dir + ")";
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
	
	//Removes gate from DB
	public void removeGateFromDB(Block key, int dir) throws SQLException
	{
		Statement stmt = null;
		try {
		String sql = "DELETE FROM 'S87Powers.SLIPGATES' " +
                "WHERE X = " + key.getX() + " AND Y = " + key.getY() + " AND Z = " + key.getZ() + " AND WORLD = \'" + key.getWorld().getName() + "\'";
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
	
	//Check the shape of gate, return empty list if false
	public ArrayList<Block> checkShape(Block start, Integer dir)
	{
		
		ArrayList<Block> portalBlocks = new ArrayList<Block>();
		//Z
		if(dir == 1 || dir == 2)
		{
			portalBlocks.add(start.getRelative(-1, 0, 0));
			portalBlocks.add(start.getRelative(1, 0, 0));
			portalBlocks.add(start.getRelative(-1, 1, 0));
			portalBlocks.add(start.getRelative(1, 1, 0));
			portalBlocks.add(start.getRelative(-1, 2, 0));
			portalBlocks.add(start.getRelative(1, 2, 0));
			portalBlocks.add(start.getRelative(-1, 3, 0));
			portalBlocks.add(start.getRelative(1, 3, 0));
			portalBlocks.add(start.getRelative(0, 3, 0));
			if(dir == 1)
			{
				portalBlocks.add(start.getRelative(0, 1, 1));
				portalBlocks.add(start.getRelative(0, 2, 1));
			}
			else
			{
				portalBlocks.add(start.getRelative(0, 1, -1));
				portalBlocks.add(start.getRelative(0, 2, -1));				
			}
		}
		//X
		else if(dir == 3 || dir == 4)
		{
			portalBlocks.add(start.getRelative(0, 0, -1));
			portalBlocks.add(start.getRelative(0, 0, 1));
			portalBlocks.add(start.getRelative(0, 1, -1));
			portalBlocks.add(start.getRelative(0, 1, 1));
			portalBlocks.add(start.getRelative(0, 2, -1));
			portalBlocks.add(start.getRelative(0, 2, 1));
			portalBlocks.add(start.getRelative(0, 3, -1));
			portalBlocks.add(start.getRelative(0, 3, 1));
			portalBlocks.add(start.getRelative(0, 3, 0));
			if(dir == 3)
			{
				portalBlocks.add(start.getRelative(-1, 1, 0));
				portalBlocks.add(start.getRelative(-1, 2, 0));
			}
			else
			{
				portalBlocks.add(start.getRelative(1, 1, 0));
				portalBlocks.add(start.getRelative(1, 2, 0));	
			}
			
		}
		else
		{
			return new ArrayList<Block>();
		}
		
		for(Block b: portalBlocks)
		{
			if(b.getType() != Material.OBSIDIAN)
			{
				return new ArrayList<Block>();
			}
		}
		
		
		return portalBlocks;
		
	}
	
	//Checks to see if a player is entering a gate block.
	//Seems inefficient, but is only being called every time a player changes blocks
	//^^Still may be inefficient
	public boolean PlayerMove(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		//See if block is in a gate
		if(S87Powers.slipGateLocs.containsKey(e.getTo().getBlock()))
		{
			//If so, get the block
			Integer dir = S87Powers.slipGateLocs.get(e.getTo().getBlock());
			ArrayList<Location> gates = new ArrayList<Location>();
			
			//Make sure it wasn't deleted (?)
			if(!S87Powers.slipGateLocs.isEmpty())
			{
				//Check for connecting gate(s)
				//Could probably be made into sub-function
				for(Entry<Block, Integer> entry : S87Powers.slipGateLocs.entrySet())
				{
				    Block key = entry.getKey();
				    Integer value = entry.getValue();
				    if((value == 1 && dir == 2)||(value == 2 && dir == 1))
				    {
					    if(key.getY() == e.getTo().getBlockY())
					    {
						    if(key.getX() == e.getTo().getBlockX())
						    {
						    	if(dir == 1)
						    	{
						    		gates.add(key.getLocation().add(0, 0, 1));
						    	}
						    	else
						    	{
						    		gates.add(key.getLocation().add(0, 0, -1));
						    	}
						    }
					    }
				    }
				    else if((value == 3 && dir == 4)||(value == 4 && dir == 3))
				    {
					    if(key.getY() == e.getTo().getBlockY())
					    {
						    if(key.getZ() == e.getTo().getBlockZ())
						    {
	
						    	if(dir == 3)
						    	{
						    		gates.add(key.getLocation().add(-1, 0, 0));
						    	}
						    	else
						    	{
						    		gates.add(key.getLocation().add(1, 0, 0));
						    	}
						    }
					    } 	
				    }
				}
				
				//If found, find closest gate
				if(!gates.isEmpty())
				{
					Location close = gates.get(0); 
					for(Location l : gates)
					{
						double dist = l.distanceSquared(p.getLocation());
						if(dist < close.distanceSquared(p.getLocation()))
						{
							close = l;
						}
					}
					
					//If within 500 blocks, teleport and return true
					if(close.distanceSquared(p.getLocation())< 250000)
					{
						//Teleport to center of block outside of gate facing original direction
						p.teleport((close.setDirection(p.getLocation().getDirection())).add(new Vector(.5,0,.5)));
						return true;
					}
				}
			}
		}
		return false;		
	}


}
