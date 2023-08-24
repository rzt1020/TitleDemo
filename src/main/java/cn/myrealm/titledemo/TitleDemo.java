package cn.myrealm.titledemo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TitleDemo extends JavaPlugin {
    public static TitleDemo instance;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new HotkeyListener(), this);
        getCommand("test").setExecutor(new TestCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
