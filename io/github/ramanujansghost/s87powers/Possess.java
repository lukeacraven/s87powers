package io.github.ramanujansghost.s87powers;

import org.bukkit.GameMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

//For update two, needs timers/removal stuff
public class Possess 
{
	public static void onRightClick(Player p) 
	{
		LivingEntity target = PlayerHelper.getTarget(p, 3);
		if(target != null)
		{
			p.setGameMode(GameMode.SPECTATOR);
			p.setSpectatorTarget(target);
			target.setRemoveWhenFarAway(false);
		}

	}

}
