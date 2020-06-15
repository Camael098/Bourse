package fr.grandoz.bourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;

public class Menu {


	private List<ShopItem> items;
	private String name;
	private int size;
	private int pos;
	private Material mat;
	private int backcolor;
	private HashMap<String, ShopItem> byMat;

	public Menu(String name , int size , int pos , Material mat , int backcolor) {
		items = new ArrayList<ShopItem>();
		byMat = new HashMap<>();
		this.name = name;
		this.mat = mat;
		this.size = size;
		this.pos = pos;
		this.backcolor  = backcolor;
	}


	public List<ShopItem> getItems() {
		return items;
	}
	public void setItems(List<ShopItem> items) {
		this.items = items;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Material getMat() {
		return mat;
	}
	public void setMat(Material mat) {
		this.mat = mat;
	}


	public HashMap<String, ShopItem> getByMat() {
		return byMat;
	}


	public void setByName(HashMap<String, ShopItem> byMat) {
		this.byMat = byMat;
	}


	public int getBackcolor() {
		return backcolor;
	}


	public void setBackcolor(int backcolor) {
		this.backcolor = backcolor;
	}
}
