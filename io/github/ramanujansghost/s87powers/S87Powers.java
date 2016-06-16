package io.github.ramanujansghost.s87powers;

import net.milkbowl.vault.permission.Permission;
import com.massivecraft.factions.engine.EngineMain;
import com.massivecraft.massivecore.ps.PS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class S87Powers extends JavaPlugin
{
	public static final Logger LOG = Logger.getLogger("Minecraft");
	public static final String VERSION = "Powers Version .06";
	public static final boolean DEBUGINVENTORYHELPER = true;
	public static boolean isFactionsEnabled = false;
	
	public static Permission perms = null;
	
	public static Connection connection = null;

	public static HashMap<UUID, Long> timeSinceWolfSummon = new HashMap<UUID, Long>();
	public static HashMap<UUID, Long> timeSinceChargeBowUse = new HashMap<UUID, Long>();
	public static HashMap<UUID, Long> timeTillSiphonAgain = new HashMap<UUID, Long>();
	public static HashMap<UUID, Long> timeSinceInteract = new HashMap<UUID, Long>();
	public static HashMap<Block, Long> tempBlocks = new HashMap<Block, Long>();
	public static Set<Material> empty = null;
	private static final String permStrings[] =
	{ "bestialtransmutation", "bestialtransmutation.toggledon", "lumberjack", "lumberjack.toggledon"
			, "wolfpack" , "wolfpack.toggledon", "chargebow", "chargebow.toggledon"
			, "reflexes", "reflexes.toggledon", "siphon", "siphon.toggledon" };

	
	//Constructor
	public S87Powers()
	{		
		
	}


	//Begin permissions
	private boolean setUpPermissions()
	{
		RegisteredServiceProvider<Permission> rsp = getServer()
				.getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}
	
	//Set up SqLite DB connection
	private void setUpDBConnection()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:s87powers.db");
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Set up SqLite DB structure
	private void setUpDBStructure() throws SQLException
	{
		Statement stmt = null;
		try {
			String createPlayerTable = "CREATE TABLE IF NOT EXISTS `S87Powers.PLAYERS` ("
					+ "`ID`	TEXT NOT NULL,"
					+ "PRIMARY KEY(ID))";
			stmt = connection.createStatement();
			stmt.executeUpdate(createPlayerTable);
			
			String createPowersTable = "CREATE TABLE IF NOT EXISTS `S87Powers.POWERS` ("
					+ "`POWER_ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+ "`NAME`	TEXT NOT NULL,"
					+ "`DESCRIPTION`	TEXT NOT NULL,"
					+ "`COST`	INTEGER NOT NULL)";
			stmt.executeUpdate(createPowersTable);
			
			String createPlayerPowerRelTable = "CREATE TABLE IF NOT EXISTS 'S87Powers.PLAYER_POWER_REL' ("
					+ "`PLAYER_ID`	TEXT NOT NULL,"
					+ "`POWER_ID`	INTEGER NOT NULL,"
					+ "`REL_COST`	INTEGER NOT NULL,"
					+ "PRIMARY KEY(PLAYER_ID,POWER_ID))";
			stmt.executeUpdate(createPlayerPowerRelTable);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(stmt != null) {	//Any exceptions here are thrown
				stmt.close();
			}
		}
	}
	
	//Attempt to establish factions, indicate error if massivecore/factions are missing
	private boolean checkIfFactionsIsEnabled()
	{
		if (getServer().getPluginManager().getPlugin("Factions") != null)
		{
			if (getServer().getPluginManager().getPlugin("Factions")
					.isEnabled())
			{
				if (getServer().getPluginManager()
						.getPlugin("MassiveCore") != null)
				{
					if (getServer().getPluginManager().getPlugin("MassiveCore")
							.isEnabled())
					{
						LOG.log(Level.INFO,
								"Factions and MassiveCore were successfully detected.");
						isFactionsEnabled = true;
						return true;
					}
					else
						LOG.log(Level.WARNING,
								"MassiveCore could not be detected");
				}
				else
					LOG.log(Level.WARNING, "MassiveCore could not be detected");
			}
			else
				LOG.log(Level.WARNING, "Factions could not be detected");
		}
		else
			LOG.log(Level.WARNING, "Factions could not be detected");
		return false;
	}
	
	//Check factions for permission to build at a location
	public static boolean canPlayerBuildAt(Player p, Location loc, Block block)
	{
		if (isFactionsEnabled) return EngineMain.canPlayerBuildAt(p,
				PS.valueOf(loc.getChunk()), false);
		return true;
	}
	
	//On plugin activation, run default setup
	@Override
	public void onEnable()
	{
		setUpPermissions();
		checkIfFactionsIsEnabled();
		setUpDBConnection();
		try {
			setUpDBStructure();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		getServer().getPluginManager().registerEvents(new PowersListener(),
				this);
		
		//test async task
		BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	Iterator<Map.Entry<Block,Long>> iter = tempBlocks.entrySet().iterator();
            	while (iter.hasNext()) {
            	    Entry<Block, Long> entry = iter.next();
            	    if(entry.getValue() <= System.currentTimeMillis())
            	    {
            	    	entry.getKey().setType(Material.AIR);
            	        iter.remove();
            	    }
            	}
            }
        }, 0L, 20L);
	}
	
	//Handle commands (consider moving)
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("powers"))
		{
			if (args.length == 0)
			{
				sender.sendMessage(ChatColor.DARK_AQUA + S87Powers.VERSION);
				sender.sendMessage(ChatColor.GOLD
						+ "Use the command '/powers help' to list S87 Power's commands.");
			}
			else if (args.length >= 1)
			{
				if (args[0].equalsIgnoreCase("add"))
				{
					if (args.length == 3)
					{
						if (sender.hasPermission("s87powers.add"))
						{
							sender.sendMessage("Attempting to add power "
									+ args[1] + " to " + args[2]);
							S87Powers.addPower(sender, args[1], args[2]);
						}
						else
						{
							sender.sendMessage(ChatColor.RED
									+ "Error while performing command!  User does not have permission.");
						}
					}
					else
					{
						sender.sendMessage(
								"Error while performing command!  Syntax is /powers add <power> <playername>");
					}
				}
				if (args[0].equalsIgnoreCase("toggle"))
				{
					if (args.length == 2)
					{
						if (sender.hasPermission("s87powers." + args[1]))
						{
							if (sender.hasPermission("s87powers." + args[1] + ".toggledon"))
							{
								sender.sendMessage(args[1] + "off");
								S87Powers.removePower(sender,
										args[1] + ".toggledon", sender.getName());
							}
							else
							{
								sender.sendMessage(args[1] + ": on");
								S87Powers.addPower(sender, args[1] + ".toggledon",
										sender.getName());
							}
						}
						else
						{
							sender.sendMessage(ChatColor.RED
									+ "Error while performing command!  User does not have permission.");
						}
					}
					else
					{
						sender.sendMessage(
								"Error while performing command!  Syntax is /powers toggle <power>");
					}
				}
				if (args[0].equalsIgnoreCase("help"))
				{
					sender.sendMessage(
							ChatColor.GOLD + "/powers - Shows version number");
					sender.sendMessage(
							ChatColor.GOLD + "/powers help - Lists commands");
					sender.sendMessage(
							ChatColor.GOLD + "/powers list - Lists the powers");
					sender.sendMessage(ChatColor.GOLD
							+ "/powers lookup <playername> - Looks up <playername>'s powers");
					sender.sendMessage(ChatColor.GOLD
							+ "/powers add <power> <playername> - Gives <power> to <playername>");
					sender.sendMessage(ChatColor.GOLD
							+ "/powers remove <power> <playername> - Removes <power> from <playername>");
					sender.sendMessage(ChatColor.GOLD
							+ "/powers desc <power> - Shows <power>'s description");

				}
				if (args[0].equalsIgnoreCase("list"))
				{
					/*
					sender.sendMessage("The powers are as follows: ");

					String [][] powers = PowersMapper.retrieveAllPowers();
					for (int x = 0; x < powers.length; x++) {
						sender.sendMessage(powers[x][0]);
					}			

					for (Map.Entry<String, Power> entry : powerList.entrySet()) {
						sender.sendMessage(entry.getKey());
					}
					*/			
				}
				if (args[0].equalsIgnoreCase("lookup"))
				{
					if (args.length == 2)
					{
						if (sender.hasPermission("s87powers.lookup")
								|| sender.getName().equalsIgnoreCase(args[1]))
						{
							sender.sendMessage("Attempting to look up "
									+ args[1] + "'s powers");
							S87Powers.lookupPlayer(sender, args[1]);
						}
						else
						{
							sender.sendMessage(ChatColor.RED
									+ "Error while performing command!  User does not have permission.");
						}
					}
					else
					{
						sender.sendMessage(
								"Error while performing command!  Syntax is /powers lookup <playername>");
					}
				}
				if (args[0].equalsIgnoreCase("remove"))
				{
					if (args.length == 3)
					{
						if (sender.hasPermission("s87powers.remove"))
						{
							sender.sendMessage("Attempting to remove power "
									+ args[1] + " from " + args[2]);
							S87Powers.removePower(sender, args[1], args[2]);
						}
						else
						{
							sender.sendMessage(ChatColor.RED
									+ "Error while performing command!  User does not have permission.");
						}
					}
					else
					{
						sender.sendMessage(
								"Error while performing command!  Syntax is /powers remove <power> <playername>");
					}
				}
			}
		}
		return true;
	}
	
	//On plugin disable, note
	@Override
	public void onDisable()
	{
		getLogger().info("Powers has been disabled!");
	}
	
	//Identify if a player has powers and list those powers
	public static void lookupPlayer(CommandSender sender, String playerString)
	{
		Player p = Bukkit.getServer().getPlayer(playerString);
		int powersCount = 0;
		if (p != null)
		{
			for (int i = 0; i < permStrings.length; i++)
			{
				if (perms.has(p, "s87powers." + permStrings[i]))
				{
					sender.sendMessage(p.getDisplayName() + " has the power "
							+ permStrings[i]);
					powersCount++;
				}
			}
			if (powersCount > 0)
				sender.sendMessage(
						p.getDisplayName() + " has " + powersCount + " powers");
			else
				sender.sendMessage(p.getDisplayName() + " has no powers :(");

		}
		else
		{
			sender.sendMessage(playerString + " could not be found.");
			LOG.log(Level.WARNING, sender.getName()
					+ " attempted to find an invalid player " + playerString);
		}
	}
	
	//Give a player permission to use a power
	public static boolean addPower(CommandSender sender,
			String permissionString, String playerString)
	{
		Player p = Bukkit.getServer().getPlayer(playerString);

		boolean isValidPerm = false;
		if (p != null)
		{
			for (int i = 0; i < permStrings.length; i++)
			{
				if (permStrings[i].equals(permissionString)) isValidPerm = true;
			}
			if (isValidPerm)
			{
				if (perms.playerAdd(p, "s87powers." + permissionString))
				{
					LOG.log(Level.INFO,
							sender.getName() + " is adding " + "s87powers."
									+ permissionString + " to "
									+ p.getDisplayName());
					return true;
				}
				LOG.log(Level.WARNING, "Failed to add " + "s87powers."
						+ permissionString + " from " + p.getDisplayName());
				return false;
			}
			else
			{
				sender.sendMessage(
						permissionString + " is not a valid permission.");
				LOG.log(Level.WARNING,
						sender.getName()
								+ " attempted to add invalid permission: "
								+ "s87powers." + permissionString + " to "
								+ p.getDisplayName());
				return false;
			}
		}
		else
		{
			sender.sendMessage(playerString + " could not be found.");
			LOG.log(Level.WARNING, sender.getName()
					+ " attempted to find an invalid player " + playerString);
			return false;
		}
	}

	//Remove player permission to use a power
	public static boolean removePower(CommandSender sender,
			String permissionString, String playerString)
	{
		Player p = Bukkit.getServer().getPlayer(playerString);
		boolean isValidPerm = false;

		if (p != null)
		{
			for (int i = 0; i < permStrings.length; i++)
			{
				if (permStrings[i].equals(permissionString)) isValidPerm = true;
			}
			if (isValidPerm)
			{
				if (perms.playerRemove(p, "s87powers." + permissionString))
				{
					LOG.log(Level.INFO,
							sender.getName() + " is removing " + "s87powers."
									+ permissionString + " from "
									+ p.getDisplayName());
					return true;
				}
				LOG.log(Level.WARNING, "Failed to remove " + "s87powers."
						+ permissionString + " from " + p.getDisplayName());
				return false;
			}
			else
			{
				sender.sendMessage(
						permissionString + " is not a valid permission.");
				LOG.log(Level.WARNING,
						sender.getName()
								+ " attempted to remove invalid permission: "
								+ "s87powers." + permissionString + " from "
								+ p.getDisplayName());
				return false;
			}
		}
		else
		{
			sender.sendMessage(playerString + " could not be found.");
			LOG.log(Level.WARNING, sender.getName()
					+ " attempted to find an invalid player " + playerString);
			return false;
		}
	}
}
