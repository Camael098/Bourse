package fr.grandoz.bourse;


public class Bonus {

	private int chance;
	private double money;
	
	public Bonus(int chance , double money) {
		this.chance = chance;
		this.money = money;
	}
	
	public int getChance() {
		return chance;
	}
	public void setChance(int chance) {
		this.chance = chance;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
}
