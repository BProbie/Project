package com.mod.forge.mc.probie.command;

import api.Windows;
import com.mod.forge.mc.probie.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.server.MinecraftServer;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.CommandException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;

public class BanProperties extends CommandBase {
    @Override
    public String getName() {
        return "ban-properties";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "commands.ban-properties.usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings) throws CommandException {
        if (iCommandSender instanceof EntityPlayer) {
            if (iCommandSender.getName().equals(Main.getHostName())) {
                if (strings.length == 0) {
                    if (Main.getBanProperties().connection()) {
                        Windows.open(Main.getBanProperties().getFilePath());
                        iCommandSender.sendMessage(new TextComponentTranslation("正在打开配置文件(" + Main.getBanProperties().getFilePath() + ")"));
                    } else {
                        iCommandSender.sendMessage(new TextComponentTranslation("配置文件加载失败"));
                    }
                } else {
                    iCommandSender.sendMessage(new TextComponentTranslation("使用方法: /ban-properties"));
                }
            } else {
                iCommandSender.sendMessage(new TextComponentTranslation("你没有服主权限"));
            }
        } else {
            iCommandSender.sendMessage(new TextComponentTranslation("请在游戏中使用"));
        }
    }
}