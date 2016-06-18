package io.github.ramanujansghost.s87powers;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Push 
{
	public static void onRightClick(Player p) 
	{
		Vector push = p.getLocation().getDirection();
		//Pull
		if(p.isSneaking())
		{
			LivingEntity target = PlayerHelper.getTarget(p, 12);
			if(target != null)
			{
				target.setVelocity(new Vector(push.getX()*-4, (push.getY()+.4)*-2, push.getZ()*-4));
			}
		}
		//Push
		else
		{
			LivingEntity target = PlayerHelper.getTarget(p, 8);
			if(target != null)
			{
				target.setVelocity(new Vector(push.getX()*4, (push.getY()+.4)*2, push.getZ()*4));
			}
		}

	}

}
