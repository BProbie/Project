package com.mod.forge.mc.probie.event;

import java.util.HashMap;
import java.util.Objects;

import com.mod.forge.mc.probie.Main;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Main.modId)
public class Login {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void login(PlayerEvent.PlayerLoggedInEvent playerLoggedInEvent) {
        if (Main.getHostName() == null) {
            Main.setHostName(playerLoggedInEvent.player.getName());
        }
        if (Main.getBanProperties().connection()) {
            HashMap<String, String> valueMap = Main.getBanProperties().getValueMap();
            for (String key : valueMap.keySet()) {
                EntityPlayerMP entityPlayerMP = Objects.requireNonNull(
                        FMLCommonHandler.instance().getMinecraftServerInstance().
                                getPlayerList().getPlayerByUsername(
                                        playerLoggedInEvent.player.getName()));
                if (entityPlayerMP.getName().equals(key)) {
                    entityPlayerMP.connection.disconnect(new TextComponentTranslation(valueMap.get(key)));
                } else if (entityPlayerMP.getPlayerIP().equals(key)) {
                    entityPlayerMP.connection.disconnect(new TextComponentTranslation(valueMap.get(key)));
                }
            }
        }
        //TODO
    }
}