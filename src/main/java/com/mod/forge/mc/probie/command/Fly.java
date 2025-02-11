package com.mod.forge.mc.probie.command;

import java.util.List;
import java.util.Collections;
import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.command.CommandException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentTranslation;

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
                if (entityPlayerMP.capabilities.allowFlying) {
                    if (flyPlayer(entityPlayerMP, false)) {
                        iCommandSender.sendMessage(new TextComponentTranslation("飞行关闭了"));
                    } else {
                        iCommandSender.sendMessage(new TextComponentTranslation("出现了未知的错误"));
                    }
                } else {
                    if (flyPlayer(entityPlayerMP, true)) {
                        iCommandSender.sendMessage(new TextComponentTranslation("飞行开启了"));
                    } else {
                        iCommandSender.sendMessage(new TextComponentTranslation("出现了未知的错误"));
                    }
                }
            } else {
                iCommandSender.sendMessage(new TextComponentTranslation("请在游戏中使用"));
            }
        } else if (strings.length == 1) {
            EntityPlayerMP entityPlayerMP = minecraftServer.getPlayerList().getPlayerByUsername(strings[0]);
            if (entityPlayerMP != null) {
                if (entityPlayerMP.capabilities.allowFlying) {
                    if (flyPlayer(entityPlayerMP, false)) {
                        entityPlayerMP.sendMessage(new TextComponentTranslation(iCommandSender.getName() + "关闭了你的飞行"));
                        iCommandSender.sendMessage(new TextComponentTranslation("你关闭了" + entityPlayerMP.getName() + "的飞行"));
                    } else {
                        iCommandSender.sendMessage(new TextComponentTranslation("出现了未知的错误"));
                    }
                } else {
                    if (flyPlayer(entityPlayerMP, true)) {
                        entityPlayerMP.sendMessage(new TextComponentTranslation(iCommandSender.getName() + "开启了你的飞行"));
                        iCommandSender.sendMessage(new TextComponentTranslation("你开启了" + entityPlayerMP.getName() + "的飞行"));
                    } else {
                        iCommandSender.sendMessage(new TextComponentTranslation("出现了未知的错误"));
                    }
                }
            } else {
                iCommandSender.sendMessage(new TextComponentTranslation("找不到玩家" + strings[0]));
            }
        } else {
            iCommandSender.sendMessage(new TextComponentTranslation("使用方法: /fly <player>"));
        }
    }

    public static boolean flyPlayer(EntityPlayerMP entityPlayerMP, boolean allowFly) {
        if (entityPlayerMP != null) {
            entityPlayerMP.capabilities.allowFlying = allowFly;
            entityPlayerMP.capabilities.isFlying = allowFly;
            entityPlayerMP.sendPlayerAbilities();
            return true;
        }
        return false;
    }
}