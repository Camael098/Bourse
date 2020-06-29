package fr.grandoz.bourse.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.grandoz.bourse.Main;
import fr.grandoz.bourse.Menu;
import fr.grandoz.bourse.ShopItem;

public class BourseShopCommand implements CommandExecutor {
	private String pr = Main.get().getVars().getPREFIX();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

		if(sender.hasPermission("bourseshop.use")) {


			//bourseshop reload

			if(args.length >0 && args[0].equalsIgnoreCase("reload")) {
				Main.get().saveDefaultConfig();
				Main.get().reloadConfig();
				Main.get().getConfigmanager().save();
				Main.get().getShopmanager().getMenus().clear();
				Main.get().getMobmanager().getBonuses().clear();	
				Main.get().getConfigmanager().loadShop();
				Main.get().getConfigmanager().loadVariables();
				Main.get().getConfigmanager().loadBonus();
				sender.sendMessage(pr+"§cReload complete");
				return false;
			}

			if(args.length >0 && args[0].equalsIgnoreCase("reset")) {
				for(Menu menu : Main.get().getShopmanager().getMenus()) {
					for(ShopItem item : menu.getItems()) {
						item.setCurrentprice(item.getInitialprice());
						item.setCurrentstage(0);
					}
				}			
				sender.sendMessage(pr+"§eVous venez de reset tous les prix à leur valeur initial");
				return false;
			}





			//boursehop
			List<String> help = new ArrayList<>();
			help.add("§e/bourseshop reload : §7Reload la config du plugin");
			help.add("§e/bourseshop reset : §7Reset toutes les valeurs de la bourse au prix initial");
			sender.sendMessage("§c§m----------§eHelp§c§m------------");
			for(String msg : help) {
				sender.sendMessage(msg);
			}
			sender.sendMessage("§c§m----------------------");
			return false;






		}



		return false;
	}

}
