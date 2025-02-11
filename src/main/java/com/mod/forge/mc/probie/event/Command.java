package com.mod.forge.mc.probie.event;

import com.mod.forge.mc.probie.Main;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.CommandEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.command.server.CommandPublishLocalServer;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Main.modId)
public class Command {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void command(CommandEvent commandEvent) {
        if (commandEvent.getCommand() instanceof CommandPublishLocalServer ||
                commandEvent.getCommand().getName().equalsIgnoreCase("publish")) {
            commandEvent.getSender().sendMessage(new TextComponentTranslation("该指令已被禁用"));
            commandEvent.setCanceled(true);
        }
    }
}