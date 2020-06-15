package fr.grandoz.bourse;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.grandoz.bourse.commands.BourseShopCommand;
import fr.grandoz.bourse.commands.CommandShop;
import fr.grandoz.inventories.InventoryManager;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	private static Main main;
	private ShopManager shopmanager;
	private ConfigManager configmanager;
	private InventoryManager invmanager;
	private Economy econ;
	private Variables vars;



	@Override
	public void onEnable() {
		main = this;
		vars = new Variables();

		invmanager = new InventoryManager();
		invmanager.init();

		shopmanager= new ShopManager();
		configmanager= new ConfigManager();

		setupEconomy();

		getCommand("shop").setExecutor(new CommandShop());
		getCommand("bourseshop").setExecutor(new BourseShopCommand());
	}	

	@Override
	public void onDisable() {
		configmanager.save();
	}


	public InventoryManager getInvmanager() {
		return invmanager;
	}

	public static Main get() {
		return main;
	}
	public ShopManager getShopmanager() {
		return shopmanager;
	}

	public Economy getEon() {
		return this.econ;
	}

	public ConfigManager getConfigmanager() {
		return this.configmanager;
	}
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}	
		econ = rsp.getProvider();
		return econ != null;
	}

	public Variables getVars() {
		return vars;
	}

	public void setVars(Variables vars) {
		this.vars = vars;
	}

}
