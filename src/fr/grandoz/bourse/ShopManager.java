package fr.grandoz.bourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopManager {

	private List<Menu> menus;
	private HashMap<String, Menu> byName;
	public ShopManager() {
		menus = new ArrayList<>();
		byName = new HashMap<>();
	}



	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}



	public HashMap<String, Menu> getByName() {
		return byName;
	}



	public void setByName(HashMap<String, Menu> byName) {
		this.byName = byName;
	}

}
