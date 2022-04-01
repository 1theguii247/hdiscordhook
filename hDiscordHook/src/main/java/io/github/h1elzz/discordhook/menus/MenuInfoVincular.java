package io.github.h1elzz.discordhook.menus;

import dev.slickcollections.kiwizin.Core;
import dev.slickcollections.kiwizin.libraries.menu.PlayerMenu;
import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.utils.BukkitUtils;
import dev.slickcollections.kiwizin.utils.enums.EnumSound;
import io.github.h1elzz.discordhook.menus.link.MenuLink;
import io.github.h1elzz.discordhook.menus.unlink.MenuUnlink;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class MenuInfoVincular extends PlayerMenu {


    public MenuInfoVincular(Profile profile) {
        super(profile.getPlayer(), "Informações - Vinculação", 3);

        setItem(12, BukkitUtils.putProfileOnSkull(this.player, BukkitUtils.deserializeItemStack("SKULL_ITEM:3 : 1 : nome>&aVincular sua conta : desc>&7Vincular sua conta do Minecraft\n&7para ter acesso à mais informações\n&7no Discord.\n \n&eClique aqui para se vincular. : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWY2MmZiNTAwODhiNjVlOThmY2M5MWExODE2MjNlZjlhMGQ4MmM4OGZjMGUwZjdmNTdmZWVkZWEzNDg3MWFiNSJ9fX0=")));

        setItem(14, BukkitUtils.deserializeItemStack("SKULL_ITEM:3 : 1 : nome>&cDesvincular sua conta : desc>&7Desvincule sua atua conta\n&7vinculada em nosso Discord.\n \n&eClique aqui para desvincular. : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzRjY2I1Mjc1MGU5N2U4MzBhZWJmYThhMjFkNWRhMGQzNjRkMGZkYWQ5ZmIwY2MyMjBmZTJjYTg0MTE4NDJjMyJ9fX0="));

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
                        if (evt.getSlot() == 12) {
                            EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
                            new MenuLink(profile);
                        } else if (evt.getSlot() == 14) {
                            EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
                            new MenuUnlink(profile);
                        }
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
