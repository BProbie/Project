package com.mod.forge.mc.probie.event;

import com.mod.forge.mc.probie.Main;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Main.modId)
public class Tick {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void tick(TickEvent.PlayerTickEvent playerTickEvent) {
        EntityPlayer player = playerTickEvent.player;
        if (player.capabilities.isFlying) {
            if (!player.capabilities.allowFlying) {
                player.capabilities.isFlying = false;
                player.sendPlayerAbilities();
                Main.getMinecraftServer().getPlayerList().sendMessage(new TextComponentTranslation("检测到玩家" + player.getName() + "疑似作弊"));
            }
        }
    }
}