package io.github.h1elzz.discordhook.commands;

import io.github.h1elzz.discordhook.menus.MenuOpen;
import dev.slickcollections.kiwizin.player.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoDiscord implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Profile profile = Profile.getProfile(player.getName());
            MenuOpen menuOpen = new MenuOpen(profile);
        }
        return false;
    }
}
