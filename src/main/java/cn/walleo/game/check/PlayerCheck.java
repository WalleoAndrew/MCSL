package cn.walleo.game.check;

import cn.walleo.secretlab;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = secretlab.MODID , bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerCheck {

    // 用于保存当前在线玩家的名字-集合
    private static final Set<String> onlinePlayers = new HashSet<>();

    // 当玩家加入游戏时触发
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        String playerName = event.getEntity().getName().getString();
        onlinePlayers.add(playerName);

        // 打印提示信息到游戏聊天栏
        event.getEntity().sendSystemMessage(Component.literal("欢迎你，" + playerName + "! 当前在线人数：" + onlinePlayers.size()));

        // 检查是否达到人数
        checkPlayerCount(onlinePlayers.size());
    }

    // 当玩家离开游戏时触发
    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        String playerName = event.getEntity().getName().getString();
        onlinePlayers.remove(playerName);

        event.getEntity().sendSystemMessage(Component.literal("玩家 " + playerName + " 离开了游戏，当前在线人数：" + onlinePlayers.size()));

        // 再次检查人数
        checkPlayerCount(onlinePlayers.size());
    }


    // 检查当前玩家人数
    private static void checkPlayerCount(int count) {
        if (count >= 10) {
            System.out.println("当前玩家人数已达到 " + count + " 人，可以触发特殊事件！");



            // 这里可以添加你的游戏逻辑，比如：
            // - 生成Boss
            // - 开启传送门
            // - 启动倒计时
        }

    }

}
