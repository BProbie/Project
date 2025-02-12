package com.mod.forge.mc.probie.event;

import java.util.HashMap;

import com.mod.forge.mc.probie.Main;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Main.modId)
public class BlockPlace {
    private static HashMap<String, Long> hashMap = new HashMap<>();
    private static Long temp = 0L;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void blockPlace(BlockEvent.EntityPlaceEvent entityPlaceEvent) {
        if (entityPlaceEvent.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityPlaceEvent.getEntity();
            Long oldMS;
            Long newMS = System.currentTimeMillis();
            if ((oldMS = hashMap.get(player.getName())) != null) {
                if (newMS - oldMS <= 100) {
                    entityPlaceEvent.setCanceled(true);
                    if (newMS - temp >= 2000) {
                        temp = newMS;
                        Main.getMinecraftServer().getPlayerList().sendMessage(new TextComponentTranslation("检测到玩家" + player.getName() + "放置方块的速度过快"));
                    }
                }
            }
            hashMap.put(player.getName(), newMS);
        }
    }
}