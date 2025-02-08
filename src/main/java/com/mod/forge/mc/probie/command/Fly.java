package com.mod.forge.mc.probie.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.command.CommandException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class Fly extends CommandBase {
    @Override
    public String getName() {
        return "fly";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "commands.fly.usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings, @Nullable BlockPos blockPos) {
        return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, minecraftServer.getOnlinePlayerNames()) : Collections.emptyList();
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings) throws CommandException {
        if (strings.length == 0) {
            if (iCommandSender instanceof EntityPlayer) {
                EntityPlayerMP entityPlayerMP = (EntityPlayerMP) iCommandSender;
                if (entityPlayerMP.capabilities.allowEdit) {
                    entityPlayerMP.capabilities.allowFlying = false;
                    entityPlayerMP.capabilities.isFlying = entityPlayerMP.capabilities.allowFlying;
                    entityPlayerMP.sendMessage(new TextComponentTranslation("关闭了飞行"));
                } else {
                    entityPlayerMP.capabilities.allowFlying = true;
                    entityPlayerMP.capabilities.isFlying = entityPlayerMP.capabilities.allowFlying;
                    entityPlayerMP.sendMessage(new TextComponentTranslation("开启了飞行"));
                }
                entityPlayerMP.sendPlayerAbilities();
            } else {
                iCommandSender.sendMessage(new TextComponentTranslation("Please Use The Command By Player"));
            }
        } else if (strings.length == 1) {
            EntityPlayerMP entityPlayerMP = minecraftServer.getPlayerList().getPlayerByUsername(strings[0]);
            if (entityPlayerMP != null) {
                if (entityPlayerMP.capabilities.allowFlying) {
                    entityPlayerMP.capabilities.allowFlying = false;
                    entityPlayerMP.capabilities.isFlying = entityPlayerMP.capabilities.allowFlying;
                    entityPlayerMP.sendMessage(new TextComponentTranslation(iCommandSender.getName() + "关闭了你的飞行"));
                    iCommandSender.sendMessage(new TextComponentTranslation("你关闭了" + entityPlayerMP.getName() + "的飞行"));
                } else {
                    entityPlayerMP.capabilities.allowFlying = true;
                    entityPlayerMP.capabilities.isFlying = entityPlayerMP.capabilities.allowFlying;
                    entityPlayerMP.sendMessage(new TextComponentTranslation(iCommandSender.getName() + "开启了你的飞行"));
                    iCommandSender.sendMessage(new TextComponentTranslation("你开启了" + entityPlayerMP.getName() + "的飞行"));
                }
                entityPlayerMP.sendPlayerAbilities();
            } else {
                iCommandSender.sendMessage(new TextComponentTranslation("Can Not Find The Player"));
            }
        } else {
            iCommandSender.sendMessage(new TextComponentTranslation("Usage: /fly <player>"));
        }
    }
}