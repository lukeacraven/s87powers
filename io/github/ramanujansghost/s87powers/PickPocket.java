package io.github.ramanujansghost.s87powers;

import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PickPocket 
{
	public void onRightClick(Player p)
	{
		Entity target = PlayerHelper.getTarget(p, 3);
		{
			if(target.getType() == EntityType.PLAYER)
			{
				Player ptarget = (Player) target;
				Inventory pinv = ptarget.getInventory();
				Random rand = new Random();
				if(rand.nextInt(100) < (30 - (PlayerHelper.getArmorCount(p) * 6)))
				{
					p.openInventory(pinv);
				}
				else
				{
					ptarget.sendMessage("You feel a tug on your pockets...");
					p.sendMessage("You have been discovered!");
				}

			}
			else 
			{
				p.sendMessage("You can't pickpocket that!");
			}
		}
	}

}
