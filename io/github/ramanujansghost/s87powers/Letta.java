package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Letta
{
	//When hit, ignore damage, disappear arrow
	//You'd better be handing this a player :P
	public void onArrowHitPlayer(EntityDamageByEntityEvent event, Entity damagedEntity)
	{
		//Block if you have the magic/mana/lifeforce/whatever
		if(GemHelper.cast(((Player)damagedEntity).getInventory(), 14))
		{
			event.setCancelled(true);
			event.getDamager().remove();
			damagedEntity.sendMessage("Your magic blocked an incoming arrow!");		
		}
	}
	
}
