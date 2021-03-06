package com.trafalcraft.avantNoel;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.trafalcraft.avantNoel.util.Locations;

public class PlayerListener implements Listener{
		
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e){
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
				if(e.getPlayer().getTargetBlock((Set<Material>) null, 100).getType() == Material.ENDER_CHEST){
					if(Locations.LocationToString(e.getPlayer().getTargetBlock((Set<Material>) null, 100).getLocation()).equalsIgnoreCase(Main.getPlugin().getConfig().getString("coffre.location"))){
					if(Main.getPlugin().getConfig().getString("Joueurs."+e.getPlayer().getName()) == null){
						e.setCancelled(true);
						
						Inventory inv = Bukkit.createInventory(e.getPlayer(), 27, "§r§r§r§aCadeau de noël");
						ItemStack item = new ItemStack(Material.getMaterial(Main.getPlugin().getConfig().getString("item."+Main.getPlugin().getConfig().getString("day"))));
						ItemMeta iteM = item.getItemMeta();
						iteM.addEnchant(new Enchantment(5) {
							
							@Override
							public int getStartLevel() {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public String getName() {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public int getMaxLevel() {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public EnchantmentTarget getItemTarget() {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public boolean conflictsWith(Enchantment arg0) {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public boolean canEnchantItem(ItemStack arg0) {
								// TODO Auto-generated method stub
								return false;
							}
						}, 0, false);
						iteM.setDisplayName("§4Cadeau de l'avent J-"+(24-Integer.parseInt(Main.getPlugin().getConfig().getString("day"))));
						item.setItemMeta(iteM);
						
						item.setAmount(Main.getPlugin().getConfig().getInt("mItem."+Main.getPlugin().getConfig().getString("day")));
						for(int i = 0; i<27;i++){
							if(i!=13){
								ItemMeta itemM = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short)0).getItemMeta();
								itemM.setDisplayName(" ");
								ItemStack item2 = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short)0);
								item2.setItemMeta(itemM);
								inv.setItem(i, item2);
							}
						}
						
						inv.setItem(13, item);
						e.getPlayer().openInventory(inv);
					
					}else{
						e.setCancelled(true);
						e.getPlayer().sendMessage("§a=========================================");
						e.getPlayer().sendMessage(" §cTu a déjà récuperer cette récompense");
						e.getPlayer().sendMessage("      §cMais tu peut repasser demain ;)");
						e.getPlayer().sendMessage("§a=========================================");
					}
					}
				}
			}
	}
	
	@EventHandler
	  public void inventoryClick(InventoryClickEvent e){
		if(e.getInventory().getTitle().equals("§r§r§r§aCadeau de noël")){
			if(e.getRawSlot() == 13){
				e.getWhoClicked().getInventory().addItem(e.getInventory().getItem(13));
				e.getWhoClicked().closeInventory();
				e.getWhoClicked().sendMessage("§a=====================================");
				e.getWhoClicked().sendMessage("     §a§k!!§r §6Bravo pour ta récompense §a§k!!");
				e.getWhoClicked().sendMessage("§a=====================================");
				Main.getPlugin().getConfig().set("Joueurs."+e.getWhoClicked().getName(), true);
				Main.getPlugin().saveConfig();
				e.setCancelled(true);
				return;
			}else{
				e.setCancelled(true);
				return;
			}
		}
	}
	
}
