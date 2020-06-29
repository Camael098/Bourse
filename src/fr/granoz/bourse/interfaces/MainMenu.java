package fr.granoz.bourse.interfaces;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import fr.grandoz.bourse.Main;
import fr.grandoz.bourse.Menu;
import fr.grandoz.bourse.ShopManager;
import fr.grandoz.bourse.Variables;
import fr.grandoz.bourse.inventories.ClickableItem;
import fr.grandoz.bourse.inventories.Inventory;
import fr.grandoz.bourse.inventories.InventoryProvider;
import fr.grandoz.bourse.inventories.ItemBuilder;
import fr.grandoz.bourse.inventories.ItemStackBuilder;

public class MainMenu implements InventoryProvider {

	private Main main = Main.get();
	private ItemStackBuilder buu = new ItemStackBuilder();
	private ShopManager shopmanager = main.getShopmanager();
	@Override
	public String title(Inventory inv) {

		return "§c§lShop";
	}

	@Override
	public int rows(Inventory inv) {
		return Main.get().getVars().getMenurow();
	}

	@Override
	public void init(Inventory inv) {

		inv.fill(ClickableItem.of(ItemBuilder.of(Material.STAINED_GLASS_PANE, 1, Main.get().getVars().getMenucolor()).name(" ").build()));
		for(Menu menu : shopmanager.getMenus()) {

			inv.set(menu.getPos(), ClickableItem.of(ItemBuilder.of(menu.getMat()).name(menu.getName()).build() , e->{
				main.getInvmanager().openInventory(new ShopMenu(),(Player) e.getWhoClicked() , i->{
					i.put(Variables.KEY_SHOP, menu);
				});

			}	) );
		}


	}

	@Override
	public void update(Inventory inv) {

	}






}
