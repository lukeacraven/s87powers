package io.github.ramanujansghost.s87powers;

import net.milkbowl.vault.permission.Permission;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class S87Powers extends JavaPlugin
{
	public static final Logger log = Logger.getLogger("Minecraft");
	public static final String version = "Powers Version .04";
	public static final boolean debugInventoryHelper = false;
	public static final boolean debugPowers = true;
	
	private static final String permStrings[] = { "bestialtransmutation" };
	
	public static Permission perms = null;
	
	private boolean setUpPermissions()
	{
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	
	@Override
    public void onEnable()
    {	
    	setUpPermissions();
    	getServer().getPluginManager().registerEvents(new PowersListener(), this);
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
    	if(cmd.getName().equalsIgnoreCase("powers"))
    	{
    		if(args.length == 0)
    		{
    			sender.sendMessage(ChatColor.DARK_AQUA + S87Powers.version);
    			sender.sendMessage(ChatColor.GOLD + "Use the command '/powers help' to list S87 Power's commands.");
    		}
    		else if(args.length >= 1)
    		{
    			if(args[0].equalsIgnoreCase("add"))
    			{
    				if(args.length == 3)
    				{
    					if(sender.hasPermission("s87powers.add"))
    					{
    						sender.sendMessage("Attempting to add power " + args[1] + " to " + args[2]);
    						S87Powers.addPower(sender, args[1], args[2]);
    					}
    					else
    					{
    						sender.sendMessage(ChatColor.RED + "Error while performing command!  User does not have permission.");
    					}
    				}
    				else
    				{
    					sender.sendMessage("Error while performing command!  Syntax is /powers add <power> <playername>");
    				}
    			}
    			if(args[0].equalsIgnoreCase("help"))
    			{
    				sender.sendMessage(ChatColor.GOLD + "/powers - Lists commands and shows version number");
    				sender.sendMessage(ChatColor.GOLD + "/powers help - Lists commands");
    				sender.sendMessage(ChatColor.GOLD + "/powers list - Lists the powers");
    				sender.sendMessage(ChatColor.GOLD + "/powers lookup <playername> - Looks up <playername>'s powers");
    				sender.sendMessage(ChatColor.GOLD + "/powers add <power> <playername> - Gives <power> to <playername>");
    				sender.sendMessage(ChatColor.GOLD + "/powers remove <power> <playername> - Removes <power> from <playername>");
    				sender.sendMessage(ChatColor.GOLD + "/powers desc <power> - Shows <power>'s description");
    				
    			}
    			if(args[0].equalsIgnoreCase("list"))
    			{
    				sender.sendMessage("The powers are as follows: ");
    				for(int i = 0; i < permStrings.length; i++)
    				{
    					sender.sendMessage(permStrings[i]);
    				}
    			}
    			if(args[0].equalsIgnoreCase("lookup"))
    			{
    				if(args.length == 2)
    				{
    					if(sender.hasPermission("s87powers.lookup"))
    					{
    						sender.sendMessage("Attempting to look up " + args[1] + "'s powers");
    						S87Powers.lookupPlayer(sender, args[1]);
    					}
    					else
    					{
    						sender.sendMessage(ChatColor.RED + "Error while performing command!  User does not have permission.");
    					}
    				}
    				else
    				{
    					sender.sendMessage("Error while performing command!  Syntax is /powers lookup <playername>");
    				}
    			}
    			if(args[0].equalsIgnoreCase("remove"))
    			{
    				if(args.length == 3)
    				{
    					if(sender.hasPermission("s87powers.remove"))
    					{
    						sender.sendMessage("Attempting to remove power " + args[1] + " from " + args[2]);
    						S87Powers.removePower(sender, args[1], args[2]);
    					}
    					else
    					{
    						sender.sendMessage(ChatColor.RED + "Error while performing command!  User does not have permission.");
    					}
    				}
    				else
    				{
    					sender.sendMessage("Error while performing command!  Syntax is /powers remove <power> <playername>");
    				}
    			}
    		}
    	}	
        return true;
    }
	
    @Override
    public void onDisable()
    {
    	getLogger().info("Powers has been disabled!");
    }
    
    public static void lookupPlayer(CommandSender sender, String playerString)
	{
    	Player p = Bukkit.getServer().getPlayer(playerString);
    	int powersCount = 0;
    	if(p != null)
    	{
    		for(int i = 0; i < permStrings.length; i++)
        	{
        		if(perms.has(p, "s87powers." + permStrings[i]))
        		{
        			sender.sendMessage(p.getDisplayName() + " has the power " + permStrings[i]);
        			powersCount++;
        		}
        	}
    		if(powersCount > 0)
    			sender.sendMessage(p.getDisplayName() + " has " + powersCount + " powers");
    		else
    			sender.sendMessage(p.getDisplayName() + " has no powers :( ");
    		
    	}
    	else
    	{
    		sender.sendMessage(playerString + " could not be found.");
    		log.log(Level.WARNING, sender.getName() + " attempted to find an invalid player " + playerString);
    	}
	}
	public static boolean addPower(CommandSender sender, String permissionString, String playerString)
	{
		Player p = Bukkit.getServer().getPlayer(playerString);
		boolean isValidPerm = false;
		
		if(p != null)
		{
			for(int i = 0; i < permStrings.length; i++)
			{
				if(permStrings[i].equals(permissionString))
					isValidPerm = true;
			}
			if(isValidPerm)
			{
				if(perms.playerAdd(p, "s87powers." + permissionString))
		    	{
		    		log.log(Level.INFO, sender.getName() + " is adding " + "s87powers." + permissionString + " to " + p.getDisplayName());
		    		return true;
		    	}
				log.log(Level.WARNING, "Failed to add " + "s87powers." + permissionString + " from " + p.getDisplayName());
				return false;
			}
			else
			{
				sender.sendMessage(permissionString + " is not a valid permission.");
				log.log(Level.WARNING, sender.getName() + " attempted to add invalid permission: " + "s87powers." + permissionString + " to " + p.getDisplayName());
				return false;
			}
		}
		else
		{
			sender.sendMessage(playerString + " could not be found.");
			log.log(Level.WARNING, sender.getName() + " attempted to find an invalid player " + playerString);
			return false;
		}	
	}
	public static boolean removePower(CommandSender sender, String permissionString, String playerString)
	{
		Player p = Bukkit.getServer().getPlayer(playerString);
		boolean isValidPerm = false;
		
		if(p != null)
		{
			for(int i = 0; i < permStrings.length; i++)
			{
				if(permStrings[i].equals(permissionString))
					isValidPerm = true;
			}
			if(isValidPerm)
			{
				if(perms.playerRemove(p, "s87powers." + permissionString))
		    	{
		    		log.log(Level.INFO, sender.getName() + " is removing " + "s87powers." + permissionString + " from " + p.getDisplayName());
		    		return true;
		    	}
				log.log(Level.WARNING, "Failed to remove " + "s87powers." + permissionString + " from " + p.getDisplayName());
				return false;
			}
			else
			{
				sender.sendMessage(permissionString + " is not a valid permission.");
				log.log(Level.WARNING, sender.getName() + " attempted to remove invalid permission: " + "s87powers." + permissionString + " from " + p.getDisplayName());
				return false;
			}
		}
		else
		{
			sender.sendMessage(playerString + " could not be found.");
			log.log(Level.WARNING, sender.getName() + " attempted to find an invalid player " + playerString);
			return false;
		}
	}
}
