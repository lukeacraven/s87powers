package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ThickSkin 
{
	public void onHit(EntityDamageByEntityEvent e)
	{
		if(PlayerHelper.getArmorCount(((Player)e.getEntity())) == 0)
		{
			e.setDamage(e.getDamage() - 2);
		}
	}

}
