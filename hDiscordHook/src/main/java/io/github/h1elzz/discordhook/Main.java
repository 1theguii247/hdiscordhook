package io.github.h1elzz.discordhook;

import io.github.h1elzz.discordhook.commands.ComandoDiscord;
import io.github.h1elzz.discordhook.commands.ComandoDesassociar;
import io.github.h1elzz.discordhook.commands.ComandoAssociar;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;
    public static boolean DiscordSRV;


    @Override
    public void onLoad() {
       DiscordSRV = Bukkit.getPluginManager().getPlugin("DiscordSRV") != null;
       // puxando o plugin....
    }

    @Override
    public void onEnable() {
        this.getCommand("discord").setExecutor(new ComandoDiscord());
        this.getCommand("discord associar").setExecutor(new ComandoAssociar());
        this.getCommand("discord desassociar").setExecutor(new ComandoDesassociar());
        //
        Bukkit.getConsoleSender().sendMessage("§a[hDiscordHook] O plugin foi ativado.");
        Bukkit.getConsoleSender().sendMessage("§a[hDiscordHook] VERSION: Versão 1.0 (Beta)");

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§a[hDiscordHook] O plugin foi desativado.");
        Bukkit.getConsoleSender().sendMessage("§a[hDiscordHook] VERSION: Versão 1.0 (Beta)");
    }


    public static Main getInstance() {
        return instance;
    }
}
