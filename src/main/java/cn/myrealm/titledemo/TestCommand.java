package cn.myrealm.titledemo;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @program: TitleDemo
 * @description:
 * @author: rzt1020
 * @create: 2023/08/23
 **/
public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (strings[0].equals("1")) {
            Bukkit.getScheduler().runTaskLater(TitleDemo.instance, new Runnable() {
                @Override
                public void run() {
                    player.sendTitle("\uEE02 \uEE03 \uEE04 \uEE05 \uEE06","",0,10000,0);
                }
            }, 0);
            Bukkit.getScheduler().runTaskLater(TitleDemo.instance, new Runnable() {
                @Override
                public void run() {
                    player.sendTitle("\uEE01\uEE01\uEE02 \uEE03 \uEE04 \uEE05 \uEE01\uEE01\uEE06","",0,10000,0);
                }
            }, 5);
            Bukkit.getScheduler().runTaskLater(TitleDemo.instance, new Runnable() {
                @Override
                public void run() {
                    player.sendTitle("\uEE01\uEE01\uEE01\uEE02 \uEE03 \uEE04 \uEE05 \uEE01\uEE01\uEE01\uEE06","",0,10000,0);
                }
            }, 10);
            Bukkit.getScheduler().runTaskLater(TitleDemo.instance, new Runnable() {
                @Override
                public void run() {
                    player.sendTitle("\uEE01\uEE01\uEE01\uEE01\uEE02 \uEE03 \uEE04 \uEE05 \uEE01\uEE01\uEE01\uEE01\uEE06","",0,10000,0);
                }
            }, 15);
            Bukkit.getScheduler().runTaskLater(TitleDemo.instance, new Runnable() {
                @Override
                public void run() {
                    player.sendTitle("\uEE01\uEE01\uEE01\uEE01\uEE01\uEE02 \uEE03 \uEE04 \uEE05 \uEE01\uEE01\uEE01\uEE01\uEE01\uEE06","",0,10000,0);
                }
            }, 20);
            return true;
        }
        if (strings[0].equals("2")) {
            for (int i = 0; i<150;i++) {
                int finalI = i;
                Bukkit.getScheduler().runTaskLater(TitleDemo.instance, () -> player.sendTitle(TitleManager.manager.translate( "     <"+ finalI +">\uEE02 \uEE03 \uEE04 \uEE05 \uEE06<"+finalI+">\uEE00"),"",0,10000,0), i);

            }

            return true;
        }
        player.sendTitle(strings[0],"");
        return true;
    }
}
