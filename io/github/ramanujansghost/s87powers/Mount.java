package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Mount
{
	public static void onRightClick(Player p) 
	{
		LivingEntity target = PlayerHelper.getTarget(p, 3);
		if(target != null && target.getType() != EntityType.PLAYER)
		{
			Entity toMount = target;
			toMount.setPassenger(p);
		}

	}

}
