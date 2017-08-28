package com.trafalcraft.avantNoel;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.trafalcraft.avantNoel.PlayerListener;
import com.trafalcraft.avantNoel.util.Locations;
import com.trafalcraft.avantNoel.util.Timer;

public class Main extends JavaPlugin{
	private static Main plugin;
	
	public void onEnable(){
		plugin = this;
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveDefaultConfig();
		plugin.reloadConfig();
		plugin.saveConfig();
		
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		
		//lancer timer
		Timer.TestJour();
	}
	
	public void onDisable(){
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[]args){
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("can") && p.isOp()){
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("pos")){
						String loc = Locations.LocationToString(p.getLocation());
						plugin.getConfig().set("coffre.location", loc);
						plugin.saveConfig();
						p.sendMessage("§3La position du coffre est enregistré !");
						return true;
					}
					if(args[0].equalsIgnoreCase("additem")){
						plugin.getConfig().set("item.day", p.getItemInHand().getType().toString());
						plugin.saveConfig();
						p.sendMessage("§3l'item a été ajouté !");
						return true;
					}
					if(args[0].equalsIgnoreCase("reload")){
						plugin.getConfig().set("Joueurs", null);
						p.sendMessage("Joueurs reset");
						return true;
					}
					if(args[0].equalsIgnoreCase("reloadcfg")){
						plugin.reloadConfig();
						p.sendMessage("Plugin rechargé");
						return true;
					}
				}
			}
			p.sendMessage("§4Erreur...");
		}
		return false;
	}
	
	public static Main getPlugin(){
		return plugin;
	}
}