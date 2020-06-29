package fr.grandoz.bourse;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobManager implements Listener {
	private Random r = new Random();
	private HashMap<EntityType, Bonus> bonuses = new HashMap<>();

	@EventHandler

	public void OnDamage(EntityDeathEvent event) {
		if(event.getEntity().getKiller() instanceof Player && Main.get().getVars().isMobEnable()) {
			
			if(!bonuses.containsKey(event.getEntity().getType()))return;
			Bonus b =bonuses.get(event.getEntity().getType());
			
			if(hasChance(100, b.getChance())) {
				
				Player p = (Player) event.getEntity().getKiller();
				Main.get().getEon().depositPlayer(p,b.getChance() );
				
				if(!Main.get().getVars().getGainmessage().equals("none")) {
					
					p.sendMessage(Main.get().getVars().getGainmessage().replace("%gain%", ""+b.getMoney()));
				}
			}
		}
	}

	public boolean hasChance(int Origine , int Produit) {

		int a = r.nextInt(Origine);
		if(a<=Produit) {
			return true;
		}	
		return false;
	}
	public HashMap<EntityType, Bonus> getBonuses() {
		return bonuses;
	}

	public void setBonuses(HashMap<EntityType, Bonus> bonuses) {
		this.bonuses = bonuses;
	}



}
