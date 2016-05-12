package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Reflexes extends Power
{

	public String getName()
	{
		return "Reflexes";
	}

	public String getType()
	{
		return "Combat";
	}

	public String getDescription()
	{
		return "Because of your incredible reflexes, you can catch or deflect incoming arrows while your main hand is empty.";
	}
	
	public void reflexOnHit(EntityDamageByEntityEvent event, Entity damagedEntity)
	{
		//Pushed
		event.setCancelled(true);
		event.getDamager().remove();
		Projectile projectile = (Projectile) event.getDamager();
		projectile.setBounce(true);
		damagedEntity.sendMessage("Your swift reflexes let you evade the arrow!");		
	}
	
	//public
}
