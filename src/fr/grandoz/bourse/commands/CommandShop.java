package fr.grandoz.bourse.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.grandoz.bourse.Main;
import fr.granoz.bourse.interfaces.MainMenu;

public class CommandShop implements CommandExecutor {

	Main main = Main.get();


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {

		if(!(sender instanceof Player))return false;

		if(cmd.getName().equalsIgnoreCase("shop")) {
			main.getInvmanager().openInventory(new MainMenu(), (Player)sender , i->{ 

			});
		}

		return false;
	}

}
