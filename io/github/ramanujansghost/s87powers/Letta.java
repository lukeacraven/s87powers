package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Letta extends Power
{
	//Constructor
	public Letta()
	{
		name = "Reflexes";
		type = "Combat";
		description = "Because of your incredible reflexes, you can catch or deflect incoming arrows while your main hand is empty.";
	}

	//When hit, ignore damage, disappear arrow
	public void onArrowHitPlayer(EntityDamageByEntityEvent event, Entity damagedEntity)
	{
		event.setCancelled(true);
		event.getDamager().remove();
		damagedEntity.sendMessage("Your swift reflexes let you evade the arrow!");		
	}
	
}
