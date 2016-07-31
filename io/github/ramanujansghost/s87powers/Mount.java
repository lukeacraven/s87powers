package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Mount
{
	//As it seems, places the player on top of entity. Entity on top of player acts weird
	public void onRightClick(Player p) 
	{
		LivingEntity target = PlayerHelper.getTarget(p, 4);
		if(target != null && target.getType() != EntityType.PLAYER)
		{
			Entity toMount = target;
			toMount.setPassenger(p);
		}

	}

}
