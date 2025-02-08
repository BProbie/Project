package com.mod.forge.mc.probie.event;

import java.util.HashMap;

import com.mod.forge.mc.probie.Main;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Main.modId)
public class BlockBreak {
    private static final HashMap<String, Long> hashMap = new HashMap<>();

    @SubscribeEvent
    public static void blockBreak(BlockEvent.BreakEvent breakEvent) {
        EntityPlayer player = breakEvent.getPlayer();
        Long oldMS;
        Long newMS = System.currentTimeMillis();
        if ((oldMS = hashMap.get(player.getName())) != null) {
            if ((newMS - oldMS) <= 100) {
                breakEvent.setCanceled(true);
                Main.getMinecraftServer().getPlayerList().sendMessage(new TextComponentTranslation("检测到玩家" + player.getName() + "破坏方块的速度过快"));
            }
        }
        hashMap.put(player.getName(), newMS);
    }
}