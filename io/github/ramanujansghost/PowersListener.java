package io.github.ramanujansghost;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;


public class PowersListener implements Listener
{
	BestialTransmutation bt = new BestialTransmutation();
	
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
    					if(sender.isOp())
    					{
    						sender.sendMessage("Adding power " + args[1] + " to " + args[2]);
    					}
    					else
    					{
    						sender.sendMessage("Error while performing command!  User is not opped");
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
    			}
    			if(args[0].equalsIgnoreCase("lookup"))
    			{
    				if(args.length == 2)
    				{
    					if(sender.isOp())
    					{
    						sender.sendMessage("Looking up " + args[1] + "'s powers");
    					}
    					else
    					{
    						sender.sendMessage("Error while performing command!  User is not opped");
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
    					if(sender.isOp())
    					{
    						sender.sendMessage("Removing power " + args[1] + " from " + args[2]);
    					}
    					else
    					{
    						sender.sendMessage("Error while performing command!  User is not opped");
    					}
    				}
    				else
    				{
    					sender.sendMessage("Error while performing command!  Syntax is /powers remove <power> <playername>");
    				}
    			}
    		}
    	}	
        return false;
    }
    
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)
	{
	    if(event.getItem() != null)
	    {
		    if(event.getItem().getType() == Material.RAW_CHICKEN && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
		    {
		    	bt.onRawChickenUse(event);
		    }
		    if(event.getItem().getType() == Material.MUTTON && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
		    {
		    	bt.onMuttonUse(event);
		    }
		    if(event.getItem().getType() == Material.PORK && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
		    {
		    	bt.onPorkUse(event);
		    }
		    if(event.getItem().getType() == Material.RABBIT && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
		    {
		    	bt.onRabbitUse(event);
		    }
		    if(event.getItem().getType() == Material.RAW_BEEF && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
		    {
		    	bt.onRawBeefUse(event);
		    }
		}
	}
}
