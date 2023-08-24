package cn.myrealm.titledemo;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: TitleDemo
 * @description:
 * @author: rzt1020
 * @create: 2023/08/23
 **/
public class HotkeyManager {
    public static HotkeyManager manager = new HotkeyManager();
    private Set<Player> singlePressSet = new HashSet<>();
    public void PlayerPressF(Player player) {
        if (singlePressSet.contains(player)) {
            TitleManager.manager.addPlayer(player);
        } else {
            singlePressSet.add(player);
            Bukkit.getScheduler().runTaskLater(TitleDemo.instance, new Runnable() {
                @Override
                public void run() {
                    if (singlePressSet.contains(player)) {
                        singlePressSet.remove(player);
                    }
                }
            }, 10);
        }
    }

    public void PlayerSlide(Player player, int prev, int next) {
//        if (Math.abs(prev - next) % 7 != 1) return;
        TitleManager.manager.slide(player, prev > next || (prev == 0 && next == 8));
    }

}
