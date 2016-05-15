package io.github.ramanujansghost.s87powers;

import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class BlockHelper 
{
	//Returns list of open adj. blocks
	public static ArrayList<Block> findAir(Block b)
	{
		S87Powers.log.log(Level.WARNING, "Getting air");
		ArrayList<Block> airs = new ArrayList<Block>();
		if(b.getRelative(BlockFace.UP).getType() == Material.AIR)
		{
			airs.add(b.getRelative(BlockFace.UP));
		}
		if(b.getRelative(BlockFace.DOWN).getType() == Material.AIR)
		{
			airs.add(b.getRelative(BlockFace.DOWN));
		}
		if(b.getRelative(BlockFace.NORTH).getType() == Material.AIR)
		{
			airs.add(b.getRelative(BlockFace.NORTH));
		}
		if(b.getRelative(BlockFace.SOUTH).getType() == Material.AIR)
		{
			airs.add(b.getRelative(BlockFace.SOUTH));
		}
		if(b.getRelative(BlockFace.EAST).getType() == Material.AIR)
		{
			airs.add(b.getRelative(BlockFace.EAST));
		}
		if(b.getRelative(BlockFace.WEST).getType() == Material.AIR)
		{
			airs.add(b.getRelative(BlockFace.WEST));
		}
		S87Powers.log.log(Level.WARNING, "Airs:" + airs);
		return airs;
	}
	
	//returns block closest to player from a list of blocks
	public static Block closestToPlayer(ArrayList<Block> blocks, Player p)
	{
		S87Powers.log.log(Level.WARNING, "Checking Closeness");
		double dist = Double.MAX_VALUE;
		Block closest = null;
		Location pLoc = p.getLocation();
		
		for(Block b : blocks){
			double newDist = b.getLocation().distance(pLoc);
		    if(newDist < dist)
		    {
		    	dist = newDist;
		    	closest = b;
		    }
		}
		S87Powers.log.log(Level.WARNING, "Close block:" + closest);
		return closest;
	}
	
	//given a list of block, check if air and then change the type
	public static void placeInEmpty(ArrayList<Block> blocks, Material toPlace)
	{
		S87Powers.log.log(Level.WARNING, "Placing Blocks");
		for(Block b : blocks)
		{
			if(b.getType() == Material.AIR)
			{
				b.setType(toPlace);
			}

		}
		
	}
	
	//Only works with cardinal faces
	//Gets a cross shape relative to face of block
	public static ArrayList<Block> getCrossOnFace(Block block, BlockFace face)
	{
		S87Powers.log.log(Level.WARNING, "Begin Cross");
		ArrayList<Block> square = new ArrayList<Block>();
		square.add(block);
		 switch (face) {
	     case UP:  
	    	 square.add(block.getRelative(BlockFace.NORTH));
	    	 square.add(block.getRelative(BlockFace.SOUTH));
	    	 square.add(block.getRelative(BlockFace.EAST));
	    	 square.add(block.getRelative(BlockFace.WEST));
	        break;
	     case DOWN:
	    	 square.add(block.getRelative(BlockFace.NORTH));
	    	 square.add(block.getRelative(BlockFace.SOUTH));
	    	 square.add(block.getRelative(BlockFace.EAST));
	    	 square.add(block.getRelative(BlockFace.WEST));
	        break;
	     case NORTH:
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 square.add(block.getRelative(BlockFace.DOWN));
	    	 square.add(block.getRelative(BlockFace.EAST));
	    	 square.add(block.getRelative(BlockFace.WEST));
	     	break;
		 case SOUTH:
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 square.add(block.getRelative(BlockFace.DOWN));
	    	 square.add(block.getRelative(BlockFace.EAST));
	    	 square.add(block.getRelative(BlockFace.WEST));
		         break;
		 case EAST:
	    	 square.add(block.getRelative(BlockFace.NORTH));
	    	 square.add(block.getRelative(BlockFace.SOUTH));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 square.add(block.getRelative(BlockFace.DOWN));
		 	break;
		 case WEST:
	    	 square.add(block.getRelative(BlockFace.NORTH));
	    	 square.add(block.getRelative(BlockFace.SOUTH));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 square.add(block.getRelative(BlockFace.DOWN));
		 	break;
		default:
			break;
		 }
		 return square;		
	}
	
	//returns a line of blocks in a world... Face is direction probably
	public static  ArrayList<Block> getLine(World w, Location start, int length, BlockFace face)
	{
		ArrayList<Block> blocks = new ArrayList<Block>();
		Block currentBlock = start.getBlock();
		
		for(int x = 1; x <= length; x++)
		{
			blocks.add(currentBlock.getRelative(face, x));
		}
		return blocks;
	}

}
