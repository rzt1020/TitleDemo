package cn.myrealm.titledemo;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: TitleDemo
 * @description:
 * @author: rzt1020
 * @create: 2023/08/23
 **/
public class TestCommand implements CommandExecutor {
    private String imageFonts = "<0>\uEE02\uEE00<1>\uEE02\uEE00<2>\uEE02\uEE00<3>\uEE02\uEE00<4>\uEE02\uEE00<5>\uEE02\uEE00<6>\uEE02\uEE00<7>\uEE02" +
            "\uEE01<15>\uEE03\uEE01\uEE00<14>\uEE03\uEE01\uEE00<13>\uEE03\uEE01\uEE00<12>\uEE03\uEE01\uEE00<11>\uEE03\uEE01\uEE00<10>\uEE03\uEE01\uEE00<9>\uEE03\uEE01\uEE00<8>\uEE03" +
            "\uEE01<16>\uEE04\uEE00<17>\uEE04\uEE00<18>\uEE04\uEE00<19>\uEE04\uEE00<20>\uEE04\uEE00<21>\uEE04\uEE00<22>\uEE04\uEE00<23>\uEE04" +
            "\uEE01<31>\uEE05\uEE01\uEE00<30>\uEE05\uEE01\uEE00<29>\uEE05\uEE01\uEE00<28>\uEE05\uEE01\uEE00<27>\uEE05\uEE01\uEE00<26>\uEE05\uEE01\uEE00<25>\uEE05\uEE01\uEE00<24>\uEE05" +
            "\uEE01<32>\uEE06\uEE00<33>\uEE06\uEE00<34>\uEE06\uEE00<35>\uEE06\uEE00<36>\uEE06\uEE00<37>\uEE06\uEE00<38>\uEE06\uEE00<39>\uEE06" +
            "\uEE01<47>\uEE07\uEE01\uEE00<46>\uEE07\uEE01\uEE00<45>\uEE07\uEE01\uEE00<44>\uEE07\uEE01\uEE00<43>\uEE07\uEE01\uEE00<42>\uEE07\uEE01\uEE00<41>\uEE07\uEE01\uEE00<40>\uEE07" +
            "\uEE01<48>\uEE08\uEE00<49>\uEE08\uEE00<50>\uEE08\uEE00<51>\uEE08\uEE00<52>\uEE08\uEE00<53>\uEE08\uEE00<54>\uEE08\uEE00<55>\uEE08" +
            "\uEE01<63>\uEE09\uEE01\uEE00<62>\uEE09\uEE01\uEE00<61>\uEE09\uEE01\uEE00<60>\uEE09\uEE01\uEE00<59>\uEE09\uEE01\uEE00<58>\uEE09\uEE01\uEE00<57>\uEE09\uEE01\uEE00<56>\uEE09";
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
        if (strings[0].equals("3")) {
            try {
                player.sendTitle(parseColor(translate(imageFonts,extractPixels(getUUIDFromUsername(strings[1])))), "", 0, 10000, 0);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        player.sendTitle(strings[0].replace("&", "§"),"",0,10000,0);
        return true;
    }

    public static List<String> extractPixels(String uuid) throws Exception {
        String  imageUrl = "https://crafatar.com/avatars/" + uuid + "?size=8";
        List<String> pixelColors = new ArrayList<>();

        // 从URL获取图片
        BufferedImage image = ImageIO.read(new URL(imageUrl));

        // 确保图片的尺寸是8x8
        if (image.getWidth() != 8 || image.getHeight() != 8) {
            throw new IllegalArgumentException("Image dimensions are not 8x8.");
        }

        // 遍历图像的每个像素
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                int rgb = image.getRGB(x, y);
                String hex = String.format("#%06X", (0xFFFFFF & rgb));  // 转换为16进制编码
                pixelColors.add(hex);
            }
        }

        return pixelColors;
    }

    public static String getUUIDFromUsername(String username) throws Exception {
        String apiUrl = "https://api.mojang.com/users/profiles/minecraft/" + username;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置请求方式为"GET"
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        String output;
        StringBuilder response = new StringBuilder();

        while ((output = br.readLine()) != null) {
            response.append(output);
        }
        connection.disconnect();
//        System.out.println(response.toString());
//        // 使用字符串操作从响应中提取UUID
//        String uuid = null;
//        String prefix = "\"id\": \"";
//        int start = response.indexOf(prefix);
//        if (start != -1) {
//            start += prefix.length();
//            int end = response.indexOf("\"", start);
//            if (end != -1) {
//                uuid = response.substring(start, end);
//            }
//        }

        return response.toString().split("\"")[3];
    }

    public String translate(String s, List<String> pixels) {
        String[] ss = s.split("<");
        StringBuilder builder = new StringBuilder();
        for (String s1 : ss) {
            if (s1.contains(">")) {
                String[] ss1 = s1.split(">");
                builder.append("<");
                builder.append(pixels.get(Integer.parseInt(ss1[0])));
                builder.append(">");
                builder.append(ss1[1]);
            } else {
                builder.append(s1);
            }
        }
        return builder.toString();
    }

    public static String parseColor(String s){
        Pattern pattern = Pattern.compile("<#[a-fA-F0-9]{6}>");
        Matcher match = pattern.matcher(s);
        while (match.find()) {
            String color = s.substring(match.start(),match.end());
            s = s.replace(color, ChatColor.of(color.replace("<","").replace(">","")) + "");
            match = pattern.matcher(s);
        }
        return s.replace("&","§").replace("§§","&");
    }
}
