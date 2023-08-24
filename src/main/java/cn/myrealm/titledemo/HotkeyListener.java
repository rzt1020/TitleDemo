package cn.myrealm.titledemo;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;

/**
 * @program: TitleDemo
 * @description:
 * @author: rzt1020
 * @create: 2023/08/23
 **/
public class HotkeyListener implements Listener {
    @EventHandler
    public void onSwapHand(PlayerSwapHandItemsEvent e) {
        Player player = e.getPlayer();
        if (TitleManager.manager.playerLoaded(player)) {
            TitleManager.manager.removePlayer(player);
            e.setCancelled(true);
            return;
        }
        if (player.isSneaking()) {
            e.setCancelled(true);
            HotkeyManager.manager.PlayerPressF(player);
        }
    }

    @EventHandler
    public void onItemHeldExchange(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        if (TitleManager.manager.playerLoaded(player)) {
//            e.setCancelled(true);
            HotkeyManager.manager.PlayerSlide(player, e.getPreviousSlot(), e.getNewSlot());
        }
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if ((e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) && TitleManager.manager.playerLoaded(player)) {
            e.setCancelled(true);
//            player.sendMessage(String.valueOf(TitleManager.manager.getTarget(player)));
            BossBarManager.manager.executeBossBar(player);
            TitleManager.manager.removePlayer(player);
        }
    }

}
