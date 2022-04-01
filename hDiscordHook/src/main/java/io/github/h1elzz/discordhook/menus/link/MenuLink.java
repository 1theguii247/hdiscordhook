package io.github.h1elzz.discordhook.menus.link;

import dev.slickcollections.kiwizin.Core;
import dev.slickcollections.kiwizin.libraries.menu.PlayerMenu;
import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.utils.BukkitUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class MenuLink extends PlayerMenu {


    public MenuLink(Profile profile) {
        super(profile.getPlayer(), "Vincular - Confirmação", 3);

        setItem(11, BukkitUtils.deserializeItemStack("35:13 : 1 : nome>&aSim"));

        setItem(15, BukkitUtils.deserializeItemStack("35:14 : 1 : nome>&cCancelar"));

        this.open();
        this.register(Core.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent evt) {
        if (evt.getInventory().equals(this.getInventory())) {
            evt.setCancelled(true);

            if (evt.getWhoClicked().equals(this.player)) {
                Profile profile = Profile.getProfile(this.player.getName());
                if (profile == null) {
                    player.closeInventory();
                    return;
                }

                if (evt.getClickedInventory() != null && evt.getClickedInventory().equals(this.getInventory())) {
                    ItemStack item = evt.getCurrentItem();

                    if (item != null && item.getType() != Material.AIR) {
                        } if (evt.getSlot() == 11) {
                        player.chat("/discord link");
                        this.player.closeInventory();
                        } else if(evt.getSlot() == 15) {
                        this.player.closeInventory();
                            //   new MenuLanguage(profile);
                       /* } else if(evt.getSlot() == 32) {
                            player.sendMessage("");
                            player.sendMessage("§eClique §e§lAQUI§r§e para acessar a loja.");
                            player.sendMessage("");
                        } else if(evt.getSlot() == 33) {
                            player.chat("/skin");

                        */
                        }
                    }
                }
            }
        }

    public void cancel() {
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent evt) {
        if (evt.getPlayer().equals(this.player)) {
            this.cancel();
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent evt) {
        if (evt.getPlayer().equals(this.player) && evt.getInventory().equals(this.getInventory())) {
            this.cancel();
        }
    }
}
