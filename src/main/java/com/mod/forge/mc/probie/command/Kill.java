package com.mod.forge.mc.probie.command;

import java.util.List;
import java.util.Collections;
import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.command.CommandException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;

public class Kill extends CommandBase {
    public Kill() {
    }

    @Override
    public String getName() {
        return "kill";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "commands.kill.usage";
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings) throws CommandException {
        if (strings.length == 0) {
            iCommandSender.sendMessage(new TextComponentTranslation("The Command Was Banned"));
        } else {
            Entity entity = getEntity(minecraftServer, iCommandSender, strings[0]);
            if (!(entity instanceof EntityPlayer)) {
                entity.onKillCommand();
                notifyCommandListener(iCommandSender, this, "commands.kill.successful", entity.getDisplayName());
            } else {
                iCommandSender.sendMessage(new TextComponentTranslation("无法清除玩家" + entity.getName()));
            }
        }
    }

    @Override
    public boolean isUsernameIndex(String[] strings, int index) {
        return index == 0;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings, @Nullable BlockPos blockPos) {
        return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, minecraftServer.getOnlinePlayerNames()) : Collections.emptyList();
    }
}