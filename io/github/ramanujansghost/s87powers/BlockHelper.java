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
		System.out.println("Begin find air");
		S87Powers.LOG.log(Level.WARNING, "Getting air");
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
		return airs;
		
	}
	
	//returns block closest to player from a list of blocks
	public static Block closestToPlayer(ArrayList<Block> blocks, Player p)
	{
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
		return closest;
	}
	
	//given a list of block, check if air(or other light material) and then change the type
	public static void placeInEmpty(Player p, ArrayList<Block> blocks, Material toPlace)
	{
		for(Block b : blocks)
		{
			if((b.getType() == Material.AIR || b.getType() == Material.LONG_GRASS || b.getType() == Material.STATIONARY_WATER || b.getType() == Material.WATER) && S87Powers.canPlayerBuildAt(p, b.getLocation(), b))
			{
				b.setType(toPlace);
			}
		}
	}
	
	//Only works with cardinal faces
	//Gets a cross shape relative to face of block
	public static ArrayList<Block> getCrossOnFace(Block block, BlockFace face)
	{
		ArrayList<Block> square = new ArrayList<Block>();
		square.add(block);
		//Kinda hate this
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
	
	//this is weird, needs much work
	//Allows creation of walls.
	public static ArrayList<Block> getWallRelP(Block block, Player p)
	{
		BlockFace face = PlayerHelper.getPlayerFacing(p);
		ArrayList<Block> square = new ArrayList<Block>();
		square.add(block);
		 switch (face) {
	     case NORTH:
	    	 square.add(block.getRelative(BlockFace.EAST));
	    	 square.add(block.getRelative(BlockFace.WEST));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.EAST));
	    	 square.add(block.getRelative(BlockFace.WEST));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.EAST));
	    	 square.add(block.getRelative(BlockFace.WEST));
	     	break;
		 case SOUTH:
	    	 square.add(block.getRelative(BlockFace.EAST));
	    	 square.add(block.getRelative(BlockFace.WEST));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.EAST));
	    	 square.add(block.getRelative(BlockFace.WEST));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.EAST));
	    	 square.add(block.getRelative(BlockFace.WEST));
		         break;
		 case EAST:
	    	 square.add(block.getRelative(BlockFace.NORTH));
	    	 square.add(block.getRelative(BlockFace.SOUTH));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.NORTH));
	    	 square.add(block.getRelative(BlockFace.SOUTH));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.NORTH));
	    	 square.add(block.getRelative(BlockFace.SOUTH));
		 	break;
		 case WEST:
	    	 square.add(block.getRelative(BlockFace.NORTH));
	    	 square.add(block.getRelative(BlockFace.SOUTH));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.NORTH));
	    	 square.add(block.getRelative(BlockFace.SOUTH));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.NORTH));
	    	 square.add(block.getRelative(BlockFace.SOUTH));
		 	break;
		 case NORTH_WEST:
	    	 square.add(block.getRelative(BlockFace.SOUTH_WEST));
	    	 square.add(block.getRelative(BlockFace.NORTH_EAST));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.SOUTH_WEST));
	    	 square.add(block.getRelative(BlockFace.NORTH_EAST));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.SOUTH_WEST));
	    	 square.add(block.getRelative(BlockFace.NORTH_EAST));
		 	break;
		 case SOUTH_WEST:
			 square.add(block.getRelative(BlockFace.NORTH_WEST));
	    	 square.add(block.getRelative(BlockFace.SOUTH_EAST));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.NORTH_WEST));
	    	 square.add(block.getRelative(BlockFace.SOUTH_EAST));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.NORTH_WEST));
	    	 square.add(block.getRelative(BlockFace.SOUTH_EAST));
		 	break;
		 case NORTH_EAST:
			 square.add(block.getRelative(BlockFace.NORTH_WEST));
	    	 square.add(block.getRelative(BlockFace.SOUTH_EAST));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.NORTH_WEST));
	    	 square.add(block.getRelative(BlockFace.SOUTH_EAST));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.NORTH_WEST));
	    	 square.add(block.getRelative(BlockFace.SOUTH_EAST));
		 	break;
		 case SOUTH_EAST:
	    	 square.add(block.getRelative(BlockFace.SOUTH_WEST));
	    	 square.add(block.getRelative(BlockFace.NORTH_EAST));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.SOUTH_WEST));
	    	 square.add(block.getRelative(BlockFace.NORTH_EAST));
	    	 square.add(block.getRelative(BlockFace.UP));
	    	 block = block.getRelative(BlockFace.UP);
	    	 square.add(block.getRelative(BlockFace.SOUTH_WEST));
	    	 square.add(block.getRelative(BlockFace.NORTH_EAST));
		 	break;
		default:
			break;
		 }
		 return square;		
	}
	
	//returns a line of blocks in a world... face is direction probably
	//Don't think we're using this...
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
	
	//Places blocks and removes after a time
	public static ArrayList<Block> tempPlace(ArrayList<Block> blocks, long duration)
	{
		ArrayList<Block> bs = new ArrayList<Block>();
		long current = System.currentTimeMillis();
		for(Block b: blocks) 
		{
			S87Powers.tempBlocks.put(b, current + duration);
			bs.add(b);
		}
		return bs;
	}
}
