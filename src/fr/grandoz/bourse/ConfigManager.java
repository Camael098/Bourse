package fr.grandoz.bourse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;


public class ConfigManager {

	private Main main;
	private ShopManager shopmanager;
	private File currents;
	private YamlConfiguration config;
	public ConfigManager() {
		main = Main.get();
		main.saveDefaultConfig();

		shopmanager = main.getShopmanager();

		currents = new File(main.getDataFolder()+File.separator+"currents.yml");
		CheckFiles();

		config = YamlConfiguration.loadConfiguration(currents);

		loadShop();
		loadVariables();

	}

	public void loadVariables() {
		int menurow = Main.get().getConfig().getInt("menu-row");
		int menucolor = Main.get().getConfig().getInt("menu-color");
		String pr = Main.get().getConfig().getString("prefix");
		pr= pr.replace("&", "§");
		Main.get().getVars().setPREFIX(pr);
		Main.get().getVars().setMenucolor(menucolor);
		Main.get().getVars().setMenurow(menurow);
	}

	public void loadShop(){
		Main.get().getShopmanager().getMenus().clear();

		if(main.getConfig().get("categories")!=null) {
			ConfigurationSection root = main.getConfig().getConfigurationSection("categories");

			for(String key : root.getKeys(false)) {
				ConfigurationSection section = main.getConfig().getConfigurationSection("categories."+key);
				String name = section.getString("name");
				name = name.replace("&", "§");
				int row = section.getInt("inv-row");
				int pos = section.getInt("pos");
				int mat_d = section.getInt("icon-id");
				int color = section.getInt("background-color");	
				Menu menu = new Menu(name, row, pos, Material.getMaterial(mat_d) , color);
				List<ShopItem> list = new ArrayList<>();
				ConfigurationSection root2 = section.getConfigurationSection("items");

				for(String key2 : root2.getKeys(false)) {
					ConfigurationSection section2 = section.getConfigurationSection("items."+key2);
					String name2 = section2.getString("name");
					name2 = name2.replace("&", "§");
					int mat_d2 = section2.getInt("material-id");
					int pos2 = section2.getInt("pos");
					int quant = section2.getInt("quantite");
					double price = section2.getDouble("initialprice");
					int stage = section2.getInt("stage");
					double changestage = section2.getDouble("multiply-factor");
					List<String> lore = new ArrayList<>();
					for (String a : section2.getStringList("lore")) {
						a = a.replace("&", "§");
						lore.add(a);
					}

					ShopItem item = new ShopItem(Material.getMaterial(mat_d2)
							, quant, price);
					item.setName(name2);
					item.setPos(pos2);
					item.setStage(stage);
					item.setChange(changestage);
					item.setLore(lore);
					item.setQuant(quant);
					list.add(item);
					menu.getByMat().put(RemoveColorCodes(item.getMat().name()), item);
				}

				menu.setItems(list);
				shopmanager.getMenus().add(menu);
				shopmanager.getByName().put(RemoveColorCodes(menu.getName()), menu);

			}
			loadCurrents();
		}


	}


	public void save() {
		config.set("currents" , null);
		for(Menu menu : main.getShopmanager().getMenus()) {

			String str = RemoveColorCodes(menu.getName());
			for(ShopItem item : menu.getItems()) {

				config.set("currents."+str+"."+item.getMat().name()+".currentprice", item.getCurrentprice());
				config.set("currents."+str+"."+item.getMat().name()+".currentstage", item.getCurrentstage());
				config.set("currents."+str+"."+item.getMat().name()+".mat", item.getMat().name());
			}

		}

		try {
			config.save(currents);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void loadCurrents() {

		if(config.get("currents")==null)return;
		ConfigurationSection root1 = config.getConfigurationSection("currents");

		for(String key1 : root1.getKeys(false)) {

			ConfigurationSection root2 = config.getConfigurationSection("currents."+key1);
			Menu menu = shopmanager.getByName().get(key1);

			for(String key2 : root2.getKeys(false)) {

				ConfigurationSection section = config.getConfigurationSection("currents."+key1+"."+key2);
				ShopItem item = menu.getByMat().get(key2);
				if(item != null) {

					double currentstage = section.getDouble("currentstage");
					double currentprice = section.getDouble("currentprice");
					item.setCurrentprice(currentprice);
					item.setCurrentstage(currentstage);

				}

			}
		}

	}


	public String RemoveColorCodes(String str ) {

		str=ChatColor.stripColor(str);
		return str;

	}

	public void CheckFiles() {

		if(!currents.exists()) {
			try {

				currents.createNewFile();

			} catch (IOException e) {			}

		}

	}



}