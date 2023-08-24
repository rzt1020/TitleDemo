package cn.myrealm.titledemo;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

/**
 * @program: TitleDemo
 * @description:
 * @author: rzt1020
 * @create: 2023/08/23
 **/
public class TitleManager {
    public static TitleManager manager = new TitleManager();
    private final HashMap<Player, Integer> loadingPlayers = new HashMap<>();
    private final HashMap<Player, List<Integer>> titlePlayers = new HashMap<>();
    private final HashMap<Player, List<BukkitRunnable>> runnablePlayers = new HashMap<>();
    private static final HashMap<Integer, List<String>> fonts = new HashMap<>();
    static {
        fonts.put(0, Arrays.asList("\uEE02","\uEE07","\uEE0C"));
        fonts.put(1, Arrays.asList("\uEE03","\uEE08","\uEE0D"));
        fonts.put(2, Arrays.asList("\uEE04","\uEE09","\uEE0E"));
        fonts.put(3, Arrays.asList("\uEE05","\uEE0A","\uEE0F"));
        fonts.put(4, Arrays.asList("\uEE06","\uEE0B","\uEE10"));
    }

    public TitleManager() {
        Bukkit.getScheduler().runTaskTimer(TitleDemo.instance, new Runnable() {
            @Override
            public void run() {
                for (Player player : loadingPlayers.keySet()) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < 5; i++) {
                        builder.append(fonts.get(i).get(titlePlayers.get(player).get(i)));
                        builder.append(" ");
                    }
                    builder.deleteCharAt(builder.length() - 1);
                    player.sendTitle(builder.toString(), "左键确定 滑轮选择 'F'键取消", 0, 100000, 0);
                }
            }
        }, 0, 1);
    }
    public void addPlayer (Player player) {
        runnablePlayers.put(player, new ArrayList<>());
        titlePlayers.put(player, Arrays.asList(0, 0 , 0, 0 ,0));
        setPlayerTitle(player, 2);

    }

    public void removePlayer(Player player) {
        if (loadingPlayers.containsKey(player) && titlePlayers.containsKey(player)) {
            player.resetTitle();
            loadingPlayers.remove(player);
            titlePlayers.remove(player);
            runnablePlayers.remove(player);
        }
    }

    public void slide(Player player, boolean isLeft) {
        if (!playerLoaded(player)) {
            return;
        }
        int next;
        if (isLeft) {
            next = loadingPlayers.get(player) - 1;
            if (next < 0) next = 4;
        } else {
            next = loadingPlayers.get(player) + 1;
            if (next > 4) next = 0;
        }
        setPlayerTitle(player, next);
    }

    public void setPlayerTitle(Player player, int index) {
        loadingPlayers.put(player, index);
        for (BukkitRunnable task : runnablePlayers.get(player)) {
            task.cancel();
        }
        for (int i = 0; i < 3; i++) {
            BukkitRunnable task = new  BukkitRunnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        int k = titlePlayers.get(player).get(j);
                        if (j == index) {
                            if (k < 2) titlePlayers.get(player).set(j, k + 1);
                        } else {
                            if (k > 0) titlePlayers.get(player).set(j, k - 1);
                        }
                    }
                }
            };
            runnablePlayers.get(player).add(task);
            task.runTaskLater(TitleDemo.instance, i);
        }
//        switch (index) {
//            case 4:
//                player.sendTitle(translate("\uEE02 \uEE03 \uEE04 \uEE05 \uEE10"),"左键确定 滑轮选择 'F'键取消",0, 10000, 0);
//                break;
//            case 3:
//                player.sendTitle(translate("\uEE02 \uEE03 \uEE04 \uEE0F \uEE06"),"左键确定 滑轮选择 'F'键取消",0, 10000, 0);
//                break;
//            case 2:
//                player.sendTitle(translate("\uEE02 \uEE03 \uEE0E \uEE05 \uEE06"),"左键确定 滑轮选择 'F'键取消",0, 10000, 0);
//                break;
//            case 1:
//                player.sendTitle(translate("\uEE02 \uEE0D \uEE04 \uEE05 \uEE06"),"左键确定 滑轮选择 'F'键取消",0, 10000, 0);
//                break;
//            case 0:
//                player.sendTitle(translate("\uEE0C \uEE03 \uEE04 \uEE05 \uEE06"),"左键确定 滑轮选择 'F'键取消",0, 10000, 0);
//                break;
//
//        }
    }

    public String translate(String s) {
        String[] ss = s.split("<");
        StringBuilder builder = new StringBuilder();
        for (String s1 : ss) {
            if (s1.contains(">")) {
                String[] ss1 = s1.split(">");
                for (int i=0; i < Integer.parseInt(ss1[0]); i++) {
                    builder.append("\uEE01");
                }
                builder.append(ss1[1]);
            } else {
                builder.append(s1);
            }
        }
        return builder.toString();
    }
    public boolean playerLoaded(Player player) {
        return loadingPlayers.containsKey(player);
    }

    public int getTarget(Player player) {
        if (playerLoaded(player)) {
            return loadingPlayers.get(player);
        }
        return -1;
    }
}
