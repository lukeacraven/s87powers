package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Push 
{
	//Feels okay, may need twerking
	public static void onRightClick(Player p) 
	{
		LivingEntity target = PlayerHelper.getTarget(p, 10);
		if(target != null)
		{
			if(GemHelper.cast(p.getInventory(), 7))
			{
				Vector push = p.getLocation().getDirection();
				//Pull
				if(p.isSneaking())
				{
					if(target != null)
					{
						target.setVelocity(new Vector(push.getX()*(-4), (push.getY()+.1)*-2, push.getZ()*-4));
					}
				}
				//Push
				else
				{
					if(target != null)
					{
						target.setVelocity(new Vector(push.getX()*4, (push.getY()+.1)*2, push.getZ()*4));
					}
				}
			}
		}
	}

}
