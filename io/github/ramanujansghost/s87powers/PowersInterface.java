package io.github.ramanujansghost.s87powers;

public interface PowersInterface {

	  static final String[][] ALL_POWERS = {
			  {"BestialTransmutation", "Alchemy", "Allows player to use 1 meat from a chicken, rabbit, sheep, pig, or cow, a varying amount of redstone, and life to create an animal.", "1"},
			  {"Ensnare", "Alchemy", "Ensnares your opponent with webbing, costs string and redstone.", "1"},
			  {"ChargeBow", "Mortal", "This power allows the player to shoot powerful fireballs from their bow at a cost of 1 fire charge per shot.", "1"},
			  {"Lumberjack", "Mortal", "Allows one to rapidly cut down trees.", "1"},
			  {"WolfPack", "Mortal", "When a player with this power right clicks, they will summon some wolves.", "2"},
			  {"Letta", "Gramarye", "Using your knowledge of the ancient magic, you have the ability to stop incoming arrows.", "1"},
			  {"Siphon", "Gramarye", "Drain the lifeforce of a being.", "2"}
			  //{"Power", "PowerType", "PowerDescription", "PowerCost"}
	  };

		static final int POWER_NAME_POSITION = 0;
		static final int POWER_TYPE_POSITION = 1;
		static final int POWER_DESC_POSITION = 2;
		static final int POWER_COST_POSITION = 3;
}