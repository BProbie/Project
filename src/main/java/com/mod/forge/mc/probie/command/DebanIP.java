package com.mod.forge.mc.probie.command;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.Nullable;

import com.mod.forge.mc.probie.Main;
import net.minecraft.util.math.BlockPos;
import net.minecraft.command.CommandBase;
import net.minecraft.server.MinecraftServer;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.CommandException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;

public class DebanIP extends CommandBase {
    @Override
    public String getName() {
        return "debanip";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "commands.debanip.usage";
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
            for (String key : valueMap.keySet()) {
                arrayList.add(valueMap.get(key));
            }
        }
        return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, arrayList) : Collections.emptyList();
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings) throws CommandException {
        if (iCommandSender instanceof EntityPlayer) {
            if (iCommandSender.getName().equals(Main.getHostName())) {
                if (strings.length == 1) {
                    if (Main.getBanProperties().connection()) {
                        Main.getBanProperties().deleteValue(Main.getBanProperties().getValue(strings[0] + "-" + "IP"));
                        iCommandSender.sendMessage(new TextComponentTranslation("你将" + strings[0] + "的IP拉出了黑名单"));
                    } else {
                        iCommandSender.sendMessage(new TextComponentTranslation("配置文件加载失败"));
                    }
                } else {
                    iCommandSender.sendMessage(new TextComponentTranslation("Usage: /debanip <player>"));
                }
            } else {
                iCommandSender.sendMessage(new TextComponentTranslation("Please Use The Command By Host"));
            }
        } else {
            iCommandSender.sendMessage(new TextComponentTranslation("Please Use The Command By Player"));
        }
    }
}