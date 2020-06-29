package fr.granoz.bourse.interfaces;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.grandoz.bourse.Main;
import fr.grandoz.bourse.Menu;
import fr.grandoz.bourse.ShopItem;
import fr.grandoz.bourse.Variables;
import fr.grandoz.bourse.inventories.ClickableItem;
import fr.grandoz.bourse.inventories.Inventory;
import fr.grandoz.bourse.inventories.InventoryProvider;
import fr.grandoz.bourse.inventories.ItemBuilder;

public class ShopMenu implements InventoryProvider{
	private DecimalFormat df = new DecimalFormat("#.##");
	private String pr = Main.get().getVars().getPREFIX();
	@Override
	public String title(Inventory inv) {
		Menu menu = (Menu) inv.get(Variables.KEY_SHOP);
		return menu.getName();
	}

	@Override
	public int rows(Inventory inv) {
		Menu menu = (Menu) inv.get(Variables.KEY_SHOP);
		return menu.getSize();
	}

	@Override
	public void init(Inventory inv) {
		Menu menu = (Menu) inv.get(Variables.KEY_SHOP);
		inv.fill(ClickableItem.of(ItemBuilder.of(Material.STAINED_GLASS_PANE, 1, menu.getBackcolor()).name(" ").build()));

		this.updateItems(menu , inv);




	}

	@Override
	public void update(Inventory inv) {
		// TODO Auto-generated method stub

	}

	public void updateItems(Menu menu , Inventory inv) {
		for(ShopItem item : menu.getItems()) {
			List<String> list = new ArrayList<>();
			for(String str : item.getLore()) {
				str = str.replace("%sell%",""+item.getCurrentprice());
				list.add(str);
			}

			inv.set(item.getPos(), ClickableItem.of(ItemBuilder.of(item.getMat() , item.getQuant()).name(item.getName()).lore(list).build() , e->{
				Player p = (Player) e.getWhoClicked();
				if(e.getClick() == ClickType.MIDDLE) {
					if(p.getInventory().containsAtLeast(new ItemStack(item.getMat()), item.getQuant())) {
						double allprice = 0;
						int allquant = 0;
						while(p.getInventory().containsAtLeast(new ItemStack(item.getMat()), item.getQuant())) {
							item.addCurrentStage(item.getQuant());			
							if(item.getCurrentstage()>=item.getStage()) {
								item.setCurrentstage(0);
								this.changeMenuValues(menu,inv ,item);
							}

							p.getInventory().removeItem(new ItemStack(item.getMat() , item.getQuant()));
							allprice = allprice+item.getCurrentprice();
							allprice = Double.valueOf(df.format(allprice).replace(",", "."));
							allquant = allquant + item.getQuant();
						}
						Main.get().getEon().depositPlayer(p, allprice);

						p.sendMessage(pr+"§eVous venez de vendre §f"+allquant+" §e"+ item.getName() +" §epour §f"+allprice+"§e$");
						
					}else {
						
						p.sendMessage(pr+"§eVous n'avez pas assez d'items à vendre");
					}
					
				}else {

					if(p.getInventory().containsAtLeast(new ItemStack(item.getMat()), item.getQuant())) {
						item.addCurrentStage(item.getQuant());
						if(item.getCurrentstage()>=item.getStage()) {
							item.setCurrentstage(0);
							this.changeMenuValues(menu,inv , item);
						}
						p.getInventory().removeItem(new ItemStack(item.getMat() , item.getQuant()));
						Main.get().getEon().depositPlayer(p, item.getCurrentprice());
						p.sendMessage(pr+"§eVous venez de vendre §f"+ item.getQuant() + " §e"+item.getName()+" §epour §f"+item.getCurrentprice()+"§e$");
					}else {
						p.sendMessage(pr+"§eVous n'avez pas assez d'items à vendre");

					}


				}


			}));
		}
	}


	public void changeMenuValues(Menu menu , Inventory inv ,ShopItem item) {
		double change = item.getCurrentprice()-item.getCurrentprice()*item.getChange();
		double otheritemschange = item.getCurrentprice()-change;
		change = Double.valueOf(df.format(change).replace(",", "."));
		item.setCurrentprice(change);
		if(menu.getItems().size() != 1 ) {


			double partial_change = otheritemschange/(menu.getItems().size()-1);
			partial_change = Double.valueOf(df.format(partial_change).replace(",", "."));
			for(ShopItem items  : menu.getItems()) {
				if(!items.equals(item)) {
					items.setCurrentprice(Double.valueOf(df.format(items.getCurrentprice()+partial_change).replace(",", ".")));
				}


			}
		}
		updateItems(menu, inv);

	}
}
