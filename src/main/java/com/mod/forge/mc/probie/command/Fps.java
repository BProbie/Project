package com.mod.forge.mc.probie.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.command.CommandException;
import net.minecraft.util.text.TextComponentTranslation;

public class Fps extends CommandBase {
    @Override
    public String getName() {
        return "fps";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "commands.fps.usage";
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings) throws CommandException {
        if (strings.length == 0) {
            iCommandSender.sendMessage(new TextComponentTranslation(Minecraft.getDebugFPS() + ""));
        }
    }
}