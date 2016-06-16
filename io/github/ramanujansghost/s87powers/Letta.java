package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Letta
{
	//Constructor
	public Letta()
	{

	}

	//When hit, ignore damage, disappear arrow
	public void onArrowHitPlayer(EntityDamageByEntityEvent event, Entity damagedEntity)
	{
		event.setCancelled(true);
		event.getDamager().remove();
		damagedEntity.sendMessage("Your swift reflexes let you evade the arrow!");		
	}
	
}
