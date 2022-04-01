package io.github.h1elzz.discordhook.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoDesassociar implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!command.getName().equalsIgnoreCase("discord desassociar") && (commandSender instanceof Player)) {
            Player player = (Player) commandSender;
            player.chat("/discord unlink");

        }
        return false;
    }
}
