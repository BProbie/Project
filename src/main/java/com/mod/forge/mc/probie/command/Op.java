package com.mod.forge.mc.probie.command;

import java.util.List;
import java.util.Collections;
import javax.annotation.Nullable;

import com.mod.forge.mc.probie.Main;
import net.minecraft.util.math.BlockPos;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.command.CommandException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentTranslation;

public class Op extends CommandBase {
    @Override
    public String getName() {
        return "op";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "commands.op.usage";
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
        if (iCommandSender instanceof EntityPlayer) {
            if (iCommandSender.getName().equals(Main.getHostName())) {
                if (strings.length == 1) {
                    EntityPlayerMP entityPlayerMP = minecraftServer.getPlayerList().getPlayerByUsername(strings[0]);
                    if (entityPlayerMP != null) {
                        if (opPlayer(minecraftServer, entityPlayerMP)) {
                            entityPlayerMP.sendMessage(new TextComponentTranslation(iCommandSender.getName() + "给予了你管理员权限"));
                            iCommandSender.sendMessage(new TextComponentTranslation("你给予了" + entityPlayerMP.getName() + "管理员权限"));
                        } else {
                            iCommandSender.sendMessage(new TextComponentTranslation("出现了未知的错误"));
                        }
                    } else {
                        iCommandSender.sendMessage(new TextComponentTranslation("找不到玩家" + strings[0]));
                    }
                } else {
                    iCommandSender.sendMessage(new TextComponentTranslation("使用方法: /op <player>"));
                }
            } else {
                iCommandSender.sendMessage(new TextComponentTranslation("你没有房主权限"));
            }
        } else {
            iCommandSender.sendMessage(new TextComponentTranslation("请在游戏中使用"));
        }
    }

    public static boolean opPlayer(MinecraftServer minecraftServer, EntityPlayerMP entityPlayerMP) {
        if (entityPlayerMP != null) {
            minecraftServer.getPlayerList().addOp(entityPlayerMP.getGameProfile());
            return true;
        }
        return false;
    }
}