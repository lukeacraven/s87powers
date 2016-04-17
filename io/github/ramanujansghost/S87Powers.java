package io.github.ramanujansghost;

import org.bukkit.plugin.java.JavaPlugin;

public class S87Powers extends JavaPlugin
{
	public static final String version = "Powers Version .02";
	public static final boolean debugInventoryHelper = true;
	public static final boolean powersDebug = true;
	
	@Override
    public void onEnable()
    {
    	getServer().getPluginManager().registerEvents(new PowersListener(), this);
    }
	
    @Override
    public void onDisable()
    {
    	getLogger().info("Powers has been disabled!");
    }
}
