package io.github.h1elzz.discordhook.menus;

import dev.slickcollections.kiwizin.Core;
import dev.slickcollections.kiwizin.libraries.menu.PlayerMenu;
import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.utils.BukkitUtils;
import dev.slickcollections.kiwizin.utils.enums.EnumSound;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class MenuOpen extends PlayerMenu {


    public MenuOpen(Profile profile) {
        super(profile.getPlayer(), "Menu - Discord System", 3);

        setItem(11, BukkitUtils.deserializeItemStack("379 : 1 : nome>&aVincular/Desvincular : desc>&7Veja todas as informações e\n&7todos os menus para\n&7desvincular ou vincular sua\n&7conta.\n \n&eClique aqui para visualizar."));

        setItem(15, BukkitUtils.deserializeItemStack("SKULL_ITEM:3 : 1 : nome>&aDiscord do servidor : desc>&7Receba o link de\n&7convite do Discord do\n&7servidor e fique a par \n&7de tudo!\n \n&eClique aqui para visualizar. : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg3M2MxMmJmZmI1MjUxYTBiODhkNWFlNzVjNzI0N2NiMzlhNzVmZjFhODFjYmU0YzhhMzliMzExZGRlZGEifX19"));

        setItem(13, BukkitUtils.deserializeItemStack("BOOK : 1 : nome>&aAjuda : desc>&7Veja todos os comandos\n&7e informações do plugin\n&7clicando aqui!\n \n&eClique aqui para visualizar."));

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
                        if (evt.getSlot() == 15) {
                            EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
                            this.player.sendMessage("§eDiscord: &7https://discord.rededrick.com");
                            this.player.closeInventory();
                        } else if (evt.getSlot() == 11) {
                            EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
                            new MenuInfoVincular(profile);
                        }

                        else if (evt.getSlot() == 13) {
                            this.player.sendMessage("");
                            this.player.sendMessage("§7Ajuda - Discord Vincular");
                            this.player.sendMessage("");
                            this.player.sendMessage("§e/discord associar §7- §fVincule sua conta no servidor.");
                            this.player.sendMessage("§e/discord desassociar §7- §fRetirar o vinculo de sua conta do Discord com o Minecraft.");
                            this.player.sendMessage("§e/discord §7- §fAbrir o menu com as informações do sistema.");
                            this.player.sendMessage("");


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
