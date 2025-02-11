package com.mod.forge.mc.probie.command;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.Nullable;

import com.mod.forge.mc.probie.Main;
import net.minecraft.util.math.BlockPos;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.command.CommandException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;

public class Deban extends CommandBase {
    @Override
    public String getName() {
        return "deban";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "commands.deban.usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings, @Nullable BlockPos blockPos) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (Main.getBanProperties().connection()) {
            HashMap<String, String> valueMap = Main.getBanProperties().getValueMap();
            arrayList.addAll(valueMap.keySet());
        }
        return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, arrayList) : Collections.emptyList();
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings) throws CommandException {
        if (iCommandSender instanceof EntityPlayer) {
            if (iCommandSender.getName().equals(Main.getHostName())) {
                if (strings.length == 1) {
                    if (Main.getBanProperties().connection()) {
                        if (Main.getBanProperties().getValueMap().containsKey(strings[0])) {
                            if (debanPlayer(strings[0])) {
                                iCommandSender.sendMessage(new TextComponentTranslation("你将" + strings[0] + "拉出了黑名单"));
                            } else {
                                iCommandSender.sendMessage(new TextComponentTranslation("出现了未知的错误"));
                            }
                        } else {
                            iCommandSender.sendMessage(new TextComponentTranslation("找不到玩家" + strings[0]));
                        }
                    } else {
                        iCommandSender.sendMessage(new TextComponentTranslation("配置文件加载失败"));
                    }
                } else {
                    iCommandSender.sendMessage(new TextComponentTranslation("使用方法: /deban <player>"));
                }
            } else {
                iCommandSender.sendMessage(new TextComponentTranslation("你没有服主权限"));
            }
        } else {
            iCommandSender.sendMessage(new TextComponentTranslation("请在游戏中使用"));
        }
    }

    public static boolean debanPlayer(String name) {
        if (Main.getBanProperties().connection()) {
            if (Main.getBanProperties().getValueMap().containsKey(name)) {
                Main.getBanProperties().deleteValue(name);
                return true;
            }
        }
        return false;
    }
}