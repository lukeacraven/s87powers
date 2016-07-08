package io.github.ramanujansghost.s87powers;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Levitate 
{
	//Self-Levitation, cancel fall speed
	public void onRightClick(Player p) 
	{
		//check to see id they are already floating
		boolean hasEffect =false;
		for(PotionEffect efx : p.getActivePotionEffects())
		{
			if(efx.getType().getName() == PotionEffectType.LEVITATION.getName())
			{
				hasEffect = true;
			}
		}
		//If not dead, float
		if(!hasEffect && GemHelper.cast(p.getInventory(), 1))
		{
			//Levitate down
			if(p.isSneaking())
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 5, 1));
				p.setFallDistance(0);
			}
			//Levitate up
			else
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 15, 2));	
				p.setFallDistance(0);
			}
		}
	}

}
