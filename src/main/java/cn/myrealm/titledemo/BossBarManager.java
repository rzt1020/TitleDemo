package cn.myrealm.titledemo;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: TitleDemo
 * @description:
 * @author: rzt1020
 * @create: 2023/08/24
 **/
public class BossBarManager {
    public static BossBarManager manager = new BossBarManager();

    public void executeBossBar(Player player) {
        List<BossBar> bars = new ArrayList<>();
        for (Iterator<KeyedBossBar> it = Bukkit.getBossBars(); it.hasNext(); ) {
            BossBar bar = it.next();
            if (bar.getPlayers().contains(player)) {
                bars.add(bar);
            }
        }
        final String[] s = {"\uEE02"};
        BossBar bar = Bukkit.createBossBar(s[0], BarColor.PURPLE, BarStyle.SEGMENTED_6);
        Bukkit.getScheduler().runTaskTimer(TitleDemo.instance, new Runnable() {
            @Override
            public void run() {
                s[0] = "\uEE01" + s[0];
                bar.setTitle(s[0]);
            }
        }, 0, 1);
        Bukkit.getScheduler().runTaskLater(TitleDemo.instance, new Runnable() {
            @Override
            public void run() {
                bar.removeAll();
            }
        }, 200);
//        bar.setVisible(false);
        bar.addPlayer(player);
    }
}
