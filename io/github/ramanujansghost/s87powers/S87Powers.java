package io.github.ramanujansghost.s87powers;

import com.massivecraft.factions.engine.EngineMain;
import com.massivecraft.massivecore.ps.PS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class S87Powers extends JavaPlugin
{
	public static final Logger LOG = Logger.getLogger("Minecraft");
	public static final String VERSION = "Powers Version .06";
	public static final boolean DEBUGINVENTORYHELPER = true;
	public static boolean isFactionsEnabled = false;
	
	public static Connection connection = null;
	
	public static ArrayList<Power> allPowers = new ArrayList<Power>();
	//public static HashMap <String, Power> stringToPower = new HashMap<String, Power>();
	//public static ArrayList<S87Player> allPlayers = new ArrayList<S87Player>();
	
	public static HashMap<UUID, Long> timeSinceVolleyUse = new HashMap<UUID, Long>();
	public static HashMap<UUID, S87Player> allPlayers = new HashMap<UUID, S87Player>();
	public static ArrayList<S87Player> playersOnline = new ArrayList<S87Player>();
	public static HashMap<UUID, Long> globalCD = new HashMap<UUID, Long>();
	public static HashMap<UUID, Long> timeSinceWolfSummon = new HashMap<UUID, Long>();
	public static HashMap<UUID, Long> timeSinceChargeBowUse = new HashMap<UUID, Long>();
	public static HashMap<UUID, Long> timeTillSiphonAgain = new HashMap<UUID, Long>();
	public static HashMap<UUID, Long> timeSinceInteract = new HashMap<UUID, Long>();
	public static HashMap<Block, Long> tempBlocks = new HashMap<Block, Long>();
	public static HashMap<Block, Integer> slipGateLocs = new HashMap<Block, Integer>();
	public static Set<Material> empty = null;


	
	//Constructor
	public S87Powers()
	{		
		
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
			
			String createStatiTable = "CREATE TABLE IF NOT EXISTS `S87Powers.STATI` ("
					+ "`STAT_ID`		INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+ "`NAME`			TEXT NOT NULL,"
					+ "`DESCRIPTION`	TEXT NOT NULL)";
			stmt.executeUpdate(createStatiTable);
			
			String createPlayerPowerRelTable = "CREATE TABLE IF NOT EXISTS 'S87Powers.PLAYER_POWER_REL' ("
					+ "`PLAYER_ID`	TEXT NOT NULL,"
					+ "`POWER_ID`	INTEGER NOT NULL,"
					+ "`REL_COST`	INTEGER NOT NULL,"
					+ "PRIMARY KEY(PLAYER_ID,POWER_ID))";
			stmt.executeUpdate(createPlayerPowerRelTable);
			
			String createPlayerStatRelTable = "CREATE TABLE IF NOT EXISTS 'S87Powers.PLAYER_STAT_REL' ("
					+ "`PLAYER_ID`	TEXT NOT NULL,"
					+ "`STAT_ID`	INTEGER NOT NULL,"
					+ "PRIMARY KEY(PLAYER_ID,STAT_ID))";
			stmt.executeUpdate(createPlayerStatRelTable);
			
			String createSlipGateTable = "CREATE TABLE IF NOT EXISTS `S87Powers.SLIPGATES` ("
					+ "`GATE_ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+ "`X`	DOUBLE NOT NULL,"
					+ "`Y`	DOUBLE NOT NULL,"
					+ "`Z`	DOUBLE NOT NULL,"
					+ "`WORLD`	STRING NOT NULL,"
					+ "`DIR`	INTEGER NOT NULL)";
			stmt.executeUpdate(createSlipGateTable);
			
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
	
	private void loadGates() throws SQLException
	{
		Statement stmt = null;
		try {
			String sql = "SELECT X, Y, Z, WORLD, DIR FROM 'S87Powers.SLIPGATES'";
			stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next())
		    {
		    	Location loc = new Location(Bukkit.getWorld(rs.getString(4)), rs.getDouble(1),rs.getDouble(2),rs.getDouble(3));
		    	Block portalBot = loc.getBlock();
		    	S87Powers.slipGateLocs.put(portalBot, rs.getInt(5));
		    }
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
	
	private void loadPowers() throws SQLException
	{
		Statement stmt = null;
		try {
			String sql = "SELECT * FROM 'S87Powers.POWERS'";
			stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next())
		    {
		    	allPowers.add(new Power(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4)));
		    }
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
	
	private void loadPlayers() throws SQLException
	{
		Statement stmt = null;
		try {
			String sql = "SELECT * FROM 'S87Powers.PLAYERS'";
			stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next())
		    {
		    	allPlayers.put(UUID.fromString(rs.getString(1)), new S87Player(UUID.fromString(rs.getString(1))));
		    }
		    
		    sql = "SELECT * FROM 'S87Powers.PLAYER_POWER_REL'";
		    rs = stmt.executeQuery(sql);
		    while(rs.next())
		    {
		    	allPlayers.get(UUID.fromString(rs.getString(1))).getPowers().add(allPowers.get(rs.getInt(2)-1));
		    }
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
		//setUpPermissions();
		checkIfFactionsIsEnabled();
		setUpDBConnection();
		setPowers();
		try {
			setUpDBStructure();
			loadGates();
			loadPowers();
			loadPlayers();
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
						if (sender.isOp())
						{
							sender.sendMessage("Attempting to add power "
									+ args[1] + " to " + args[2]);
							
							S87Powers.addPower(Bukkit.getPlayer(args[2]),StringToPower(args[1]));
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
				//finish later
				if (args[0].equalsIgnoreCase("select"))
				{
					if (args.length == 2)
					{
						int totalPower = 0;
						int powerNum = 0;
						if(StringToPower(args[1]) != null)
						{
							if(!S87Powers.allPlayers.get(((Player)sender).getUniqueId()).getPowers().contains(StringToPower(args[1])))
							{
								for(Power pow : S87Powers.allPlayers.get(((Player)sender).getUniqueId()).getPowers())
								{
									totalPower += pow.getCost();
									powerNum++;
								}
								if(totalPower + StringToPower(args[1]).getCost() <= 5)
								{
									S87Powers.addPower((Player) sender,StringToPower(args[1]));
									try {
										addPlayerPower((Player) sender, StringToPower(args[1]));
									} catch (SQLException e) {
										e.printStackTrace();
									}
									sender.sendMessage(ChatColor.GOLD + "Power added to slot " + (powerNum+1) + "!");
									sender.sendMessage(ChatColor.GOLD + "You have " + (5 - (totalPower + StringToPower(args[1]).getCost())) +  " power points remaining.");
								}
								else
								{
									sender.sendMessage(
											ChatColor.RED + "Not enough points!");
								}
							}
							else
							{
								sender.sendMessage(
										ChatColor.RED + "You already have this power!");
							}
						}
						else
						{
							sender.sendMessage(
									ChatColor.RED + "" + args[1] + " is not a real power.");
						}
					}
					else
					{
						sender.sendMessage(
								"Error while performing command!  Syntax is /powers select <power>");
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
							+ "/powers me - Displays your power list");
					sender.sendMessage(ChatColor.GOLD
							+ "/powers add <power> <playername> - Gives <power> to <playername>");
					sender.sendMessage(ChatColor.GOLD
							+ "/powers remove <power> <playername> - Removes <power> from <playername>");
					sender.sendMessage(ChatColor.GOLD
							+ "/powers desc <power> - Shows <power>'s description");

				}
				if (args[0].equalsIgnoreCase("me"))
				{
					lookupPlayer(sender, sender.getName());
				}
				if (args[0].equalsIgnoreCase("list"))
				{

					sender.sendMessage("The powers are as follows: ");
					for(Power p : allPowers)
					{
						sender.sendMessage(ChatColor.GOLD + p.getName() + " - " + p.getDesc() + " - " + p.getCost());
					}

				}
//				if (args[0].equalsIgnoreCase("lookup"))
//				{
//					if (args.length == 2)
//					{
//						if (sender.isOp())
//						{
//							sender.sendMessage("Attempting to look up "
//									+ args[1] + "'s powers");
//							S87Powers.lookupPlayer(sender, args[1]);
//						}
//						else
//						{
//							sender.sendMessage(ChatColor.RED
//									+ "Error while performing command!  User does not have permission.");
//						}
//					}
//					else
//					{
//						sender.sendMessage(
//								"Error while performing command!  Syntax is /powers lookup <playername>");
//					}
//	
				if (args[0].equalsIgnoreCase("remove"))
				{
					if (args.length == 3)
					{
						if (sender.isOp())
						{
							sender.sendMessage("Attempting to remove power "
									+ args[1] + " from " + args[2]);
							try {
								S87Powers.removePower(sender, args[1], args[2]);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
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
			sender.sendMessage(ChatColor.GOLD + p.getDisplayName() + " has: ");
			for (Power pow : allPlayers.get(p.getUniqueId()).getPowers())
			{
					sender.sendMessage(ChatColor.GOLD + pow.getName());
					powersCount++;
			}
			if (powersCount > 0)
			{
				//sender.sendMessage(
				//		p.getDisplayName() + " has " + powersCount + " powers");
			}
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
	public static boolean addPower(Player p, Power pow)
	{
		allPlayers.get(p.getUniqueId()).getPowers().add(pow);
		return true;		
	}

	//Remove player permission to use a power
	public static boolean removePower(CommandSender sender,
			String powerString, String playerString) throws SQLException
	{
		Player p = Bukkit.getServer().getPlayer(playerString);
		Power toRemove = StringToPower(powerString);

		if (p != null)
		{
			if(toRemove != null)
			{					
				if(allPlayers.get(p.getUniqueId()).getPowers().remove(toRemove))
				{
					removePlayerPower(p,toRemove);
					LOG.log(Level.INFO,
							sender.getName() + " is removing " + "s87powers."
									+ powerString + " from "
									+ p.getDisplayName());
					return true;
				}
				else
				{
					LOG.log(Level.WARNING, "Failed to remove "
							+ powerString + " from " + p.getDisplayName());
					return false;
				}
			}
			else
			{
				sender.sendMessage(powerString + " could not be found.");
				LOG.log(Level.WARNING, sender.getName()
						+ " attempted to remove an invalid power " + powerString);
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

	public static void setPowers()
	{
		allPowers.add(new Power(1, "BestialTransmutation", "Transmute meat into animals", 2));
		allPowers.add(new Power(2, "WolfPack", "Summon friendly wolves", 3));
		allPowers.add(new Power(3, "Ensnare", "Spin a web, any size!", 2));
		allPowers.add(new Power(4, "Siphon", "Drain life into a Soulgem", 2));
		allPowers.add(new Power(5, "Wall", "Quickly constuct a wall", 2));
		allPowers.add(new Power(6, "Leap", "Leap small buildings in a single bound!", 1));
		allPowers.add(new Power(7, "Push", "Push and pull your enemies", 2));
		allPowers.add(new Power(8, "Levitate", "Lift yourself into the air", 1));
		allPowers.add(new Power(9, "Fireball", "Launch a blazing missile", 2));
		allPowers.add(new Power(10, "Leviosa", "Lift your friends", 1));
		allPowers.add(new Power(11, "Translocation", "Swap places with another", 2));
		allPowers.add(new Power(12, "Possess", "Reside within your prey", 999));
		allPowers.add(new Power(13, "Cloak", "Hide from all", 999));
		allPowers.add(new Power(14, "SoulShatter", "Detonate a SoulGem", 2));
		allPowers.add(new Power(15, "Juggernaut", "Pure strength", 4));
		allPowers.add(new Power(16, "Shell", "Form a shell around yourself or others", 2));
		allPowers.add(new Power(17, "GateBuilder", "Construct slipgates", 2));
		allPowers.add(new Power(18, "Chargebow", "Shoot flaming arrows", 2));
		allPowers.add(new Power(19, "Letta", "Stop arrows in their path", 2));
		allPowers.add(new Power(20, "Lumberjack", "Chop down full trees", 1));
		allPowers.add(new Power(21, "Waterstrider", "Sprint in water", 1));
		allPowers.add(new Power(22, "Volley", "Fire a swarm of arrows", 2));
		allPowers.add(new Power(23, "FlameAlchemy", "Incenerate everything", 3));
		
	}
	
	public static Power StringToPower(String s)
	{
		for(Power p : allPowers)
		{
			if(p.getName().equalsIgnoreCase(s))
			{
				return p;
			}
		}
		return null;
	}

	public static void addPlayerPower(Player p, Power pow) throws SQLException
	{
		Statement stmt = null;
		try {
		String sql = "INSERT INTO 'S87Powers.PLAYER_POWER_REL' " +
                "VALUES (\"" + p.getUniqueId() +  "\"," + pow.getId() + " , " + pow.getCost() + ")";
		stmt = S87Powers.connection.createStatement();
		stmt.executeUpdate(sql);
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
	
	public static void removePlayerPower(Player p, Power pow) throws SQLException
	{
		Statement stmt = null;
		try {
		String sql = "DELETE FROM 'S87Powers.PLAYER_POWER_REL' " +
                "WHERE PLAYER_ID = \'" + p.getUniqueId() + "\' AND POWER_ID = " + pow.getId();
		stmt = S87Powers.connection.createStatement();
		stmt.executeUpdate(sql);
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
}
