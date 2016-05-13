package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Reflexes extends Power
{
	//Constructor
	public Reflexes()
	{
		name = "Reflexes";
		type = "Combat";
		description = "Because of your incredible reflexes, you can catch or deflect incoming arrows while your main hand is empty.";
	}

	//When hit, ignore damage, disappear arrow
	public void reflexOnHit(EntityDamageByEntityEvent event, Entity damagedEntity)
	{
		//Pushed
		event.setCancelled(true);
		event.getDamager().remove();
		Projectile projectile = (Projectile) event.getDamager();
		projectile.setBounce(true);
		damagedEntity.sendMessage("Your swift reflexes let you evade the arrow!");		
	}
	
}
