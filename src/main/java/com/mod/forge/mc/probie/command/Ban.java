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

public class Ban extends CommandBase {
    @Override
    public String getName() {
        return "ban";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "commands.ban.usage";
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
                if (strings.length == 1 || strings.length == 2) {
                    if (!strings[0].equals(iCommandSender.getName())) {
                        if (Main.getBanProperties().connection()) {
                            EntityPlayerMP entityPlayerMP = minecraftServer.getPlayerList().getPlayerByUsername(strings[0]);
                            String reason = iCommandSender.getName() + "将你拉入了黑名单";
                            if (strings.length == 2) {
                                reason = reason + ": " + strings[1];
                            }
                            if (entityPlayerMP != null) {
                                entityPlayerMP.connection.disconnect(new TextComponentTranslation(reason));
                            }
                            Main.getBanProperties().addValue(strings[0], reason);
                            iCommandSender.sendMessage(new TextComponentTranslation("你将" + strings[0] + "拉入了黑名单"));
                        } else {
                            iCommandSender.sendMessage(new TextComponentTranslation("配置文件加载失败"));
                        }
                    } else {
                        iCommandSender.sendMessage(new TextComponentTranslation("Can Not Ban Yourself"));
                    }
                } else {
                    iCommandSender.sendMessage(new TextComponentTranslation("Usage: /ban <player> <reason>"));
                }
            } else {
                iCommandSender.sendMessage(new TextComponentTranslation("Please Use The Command By Host"));
            }
        } else {
            iCommandSender.sendMessage(new TextComponentTranslation("Please Use The Command By Player"));
        }
    }
}