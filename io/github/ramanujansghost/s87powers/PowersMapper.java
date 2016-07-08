package io.github.ramanujansghost.s87powers;

import java.util.HashMap;


public class PowersMapper implements PowersInterface {

	private static HashMap<String, String[]> powersMap = null;

	public static void initPowers() {
		
		powersMap = new HashMap<String, String[]>();
		String[][] allPowers = PowersInterface.ALL_POWERS;
		
		for (int i = 0; i < allPowers.length; i++) { 
			String[] aPower = allPowers[i];
			powersMap.put(allPowers[i][POWER_NAME_POSITION], aPower);
		}    
     }
	
	public static String[] retrievePowerByName(String powerName) {	
		return (String[]) powersMap.get(powerName);
	}
	
	public static String[][] retrieveAllPowers() {
		return PowersInterface.ALL_POWERS;
	}
	
	public static String retrievePowerDescription(String powerName) {	
		return retrievePowerByName(powerName)[POWER_DESC_POSITION];		
	}

	public static String retrievePowerType(String powerName) {	
		return retrievePowerByName(powerName)[POWER_TYPE_POSITION];		
	}
	
	public static String retrievePowerCost(String powerName) {
		return retrievePowerByName(powerName)[POWER_COST_POSITION];
	}
		
	public static boolean powerExists(String powerName) {	
		return powersMap.containsKey(powerName);						
	}
	
	
}





