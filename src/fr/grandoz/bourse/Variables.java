package fr.grandoz.bourse;

public class Variables {


	private String PREFIX = "";
	public static String KEY_SHOP = "barakoabama";
	private int menurow = 3;
	private int menucolor = 7;
	private boolean mobEnable = false;
	private String gainmessage = "none";

	public int getMenurow() {
		return menurow;
	}
	public void setMenurow(int menurow) {
		this.menurow = menurow;
	}
	public int getMenucolor() {
		return menucolor;
	}
	public void setMenucolor(int menucolor) {
		this.menucolor = menucolor;
	}
	public String getPREFIX() {
		return PREFIX;
	}
	public void setPREFIX(String pREFIX) {
		PREFIX = pREFIX;
	}
	public boolean isMobEnable() {
		return mobEnable;
	}
	public void setMobEnable(boolean mobEnable) {
		this.mobEnable = mobEnable;
	}
	public String getGainmessage() {
		return gainmessage;
	}
	public void setGainmessage(String gainmessage) {
		this.gainmessage = gainmessage;
	}



}
