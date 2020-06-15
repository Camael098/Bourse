package fr.grandoz.bourse;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public class ShopItem {
	private String name;
	private double initialprice;
	private double currentprice;
	private double stage;
	private double currentstage;
	private double change;
	private int pos;
	private Material mat;
	private int quant	;
	private List<String> lore;



	public ShopItem(Material mat , int quant , double initialprice ) {
		this.mat = mat;
		this.quant = quant;
		this.initialprice = initialprice;
		this.currentprice = initialprice;
		this.currentstage = 0;
		lore = new ArrayList<String>();
	}






	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getInitialprice() {
		return initialprice;
	}
	public void setInitialprice(double initialprice) {
		this.initialprice = initialprice;
	}
	public double getStage() {
		return stage;
	}
	public void setStage(double stage) {
		this.stage = stage;
	}
	public double getCurrentstage() {
		return currentstage;
	}
	public void setCurrentstage(double currentstage) {
		this.currentstage = currentstage;
	}
	public double getChange() {
		return change;
	}
	public void setChange(double change) {
		this.change = change;
	}
	public List<String> getLore() {
		return lore;
	}
	public void setLore(List<String> lore) {
		this.lore = lore;
	}

	public void addCurrentStage(double value) {
		this.currentstage = currentstage+value;
	}




	public int getPos() {
		return pos;
	}






	public void setPos(int pos) {
		this.pos = pos;
	}






	public Material getMat() {
		return mat;
	}






	public void setMat(Material mat) {
		this.mat = mat;
	}






	public int getQuant() {
		return quant;
	}






	public void setQuant(int quant) {
		this.quant = quant;
	}






	public double getCurrentprice() {
		return currentprice;
	}






	public void setCurrentprice(double currentprice) {
		this.currentprice = currentprice;
	}




}
