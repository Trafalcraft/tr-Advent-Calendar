package com.trafalcraft.avantNoel.util;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.trafalcraft.avantNoel.Main;

public class Timer {

	
	public static void TestJour(){
        int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            
            public void run() {
            	Date maDatedeNaissance = new java.util.Date();
            	Bukkit.getLogger().info(maDatedeNaissance+"");
            	if(!Main.getPlugin().getConfig().getString("day").equals(maDatedeNaissance.toString().split(" ")[2])){
            		
            		Bukkit.getLogger().info("Changement de jour");
            		String mDate = maDatedeNaissance.toString().split(" ")[2];
            		Main.getPlugin().getConfig().set("Joueurs", null);
            	/*	if(mDate < 10){
            			String sMDate = "0"+mDate;
                    	Bukkit.getLogger().info(sMDate+"");  
            			Main.getPlugin().getConfig().set("day", sMDate);
            			
            		}else{*/
            			Main.getPlugin().getConfig().set("day", mDate);
            			Main.getPlugin().saveConfig();
            		//}
            	}
                for(Player pl : Bukkit.getOnlinePlayers())
                {
                	if(Main.getPlugin().getConfig().getString("Joueurs."+pl.getName()) == null){
					   pl.sendMessage("§a====================================================");
					   pl.sendMessage("§6Une récompense t'attend dans l'enderchest du spawn ;)");
					   pl.sendMessage("§a====================================================");
                	}
                }
            }
        }, 0, 1200);
	}
}
