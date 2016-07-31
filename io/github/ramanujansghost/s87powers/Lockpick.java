package io.github.ramanujansghost.s87powers;

import java.util.Random;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Door;

public class Lockpick 
{

	public void onRightClick(Player p)
	{
		Set<Material> st = null;
		Block target = p.getTargetBlock(st, 3);
		
		if(S87Powers.timeSinceLockPick.containsKey(p.getUniqueId()))
		{
			Long coolDown = S87Powers.timeSinceLockPick.get(p.getUniqueId());
			if (System.currentTimeMillis() > coolDown)
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.STONE_HOE)
				{
					pick(p, target, 2);
					S87Powers.timeSinceLockPick.put(p.getUniqueId(), System.currentTimeMillis()+ 5000);
					p.getInventory().setItemInMainHand(new ItemStack(Material.AIR, 1));
				}
				else if(p.getInventory().getItemInMainHand().getType() == Material.IRON_HOE)
				{
					pick(p, target, 10);
					S87Powers.timeSinceLockPick.put(p.getUniqueId(), System.currentTimeMillis()+ 5000);
					p.getInventory().setItemInMainHand(new ItemStack(Material.AIR, 1));
				}
				else if(p.getInventory().getItemInMainHand().getType() == Material.GOLD_HOE)
				{
					pick(p, target, 8);
					S87Powers.timeSinceLockPick.put(p.getUniqueId(), System.currentTimeMillis()+ 5000);
					p.getInventory().setItemInMainHand(new ItemStack(Material.AIR, 1));
				}
				else if(p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_HOE)
				{
					pick(p, target, 20);
					S87Powers.timeSinceLockPick.put(p.getUniqueId(), System.currentTimeMillis()+ 5000);
					p.getInventory().setItemInMainHand(new ItemStack(Material.AIR, 1));
				}
				else
				{
					p.sendMessage("You must use a hoe to pick locks");
				}
			}
			else
			{
				p.sendMessage("You must wait " +(coolDown - (System.currentTimeMillis())) / 1000 + " more seconds.");
			}
			
		}
		else
		{
			S87Powers.timeSinceLockPick.put(p.getUniqueId(), System.currentTimeMillis());
		}
	
	}
	
	private boolean pick(Player p, Block target, int chance)
	{
		Random rand = new Random(System.currentTimeMillis());
		int rd = rand.nextInt(100);
		if(rd <= chance)
		{
			System.out.println("Pick success");
			if(target.getType() == Material.CHEST)
			{
				Chest ch = (Chest)target.getState();
				p.openInventory(ch.getInventory());
				S87Powers.alertFactionBlock(target.getLocation(), "One of your locks has been picked!");
				return true;
			}
			
			if(target.getType() == Material.ACACIA_DOOR || target.getType() == Material.JUNGLE_DOOR || target.getType() == Material.SPRUCE_DOOR || target.getType() == Material.TRAP_DOOR ||  target.getType() == Material.IRON_DOOR ||  target.getType() == Material.IRON_DOOR_BLOCK  || target.getType() == Material.WOOD_DOOR || target.getType() == Material.DARK_OAK_DOOR || target.getType() == Material.BIRCH_DOOR || target.getType() == Material.WOODEN_DOOR)
			{
				
				BlockState bs = target.getState();
				Door dr = (Door)bs.getData();
				target = target.getRelative(BlockFace.DOWN);
				if(target.getType() == Material.ACACIA_DOOR || target.getType() == Material.JUNGLE_DOOR || target.getType() == Material.SPRUCE_DOOR || target.getType() == Material.TRAP_DOOR ||  target.getType() == Material.IRON_DOOR ||  target.getType() == Material.IRON_DOOR_BLOCK || target.getType() == Material.WOOD_DOOR || target.getType() == Material.DARK_OAK_DOOR || target.getType() == Material.BIRCH_DOOR || target.getType() == Material.WOODEN_DOOR)
				{
					bs = target.getState();
					dr = (Door)bs.getData();
					dr.setOpen(true);
					bs.update();
					S87Powers.alertFactionBlock(target.getLocation(), "One of your locks has been picked!");
					return true;
				}
				else
				{
					S87Powers.alertFactionBlock(target.getLocation(), "One of your locks has been picked!");
					dr.setOpen(true);
					bs.update();
					return true;
				}
				
			}
		}
		else
			p.sendMessage("Picking failed");
		return false;
	}

}
