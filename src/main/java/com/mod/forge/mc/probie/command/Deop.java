package com.mod.forge.mc.probie.command;

import com.mod.forge.mc.probie.Main;
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

public class Deop extends CommandBase {
    @Override
    public String getName() {
        return "deop";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "commands.deop.usage";
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
                        minecraftServer.getPlayerList().removeOp(entityPlayerMP.getGameProfile());
                        entityPlayerMP.sendMessage(new TextComponentTranslation(iCommandSender.getName()+"剥夺了你管理员权限"));
                        iCommandSender.sendMessage(new TextComponentTranslation("你剥夺了"+entityPlayerMP.getName()+"管理员权限"));
                    } else {
                        iCommandSender.sendMessage(new TextComponentTranslation("Can Not Find The Player"));
                    }
                } else {
                    iCommandSender.sendMessage(new TextComponentTranslation("Usage: /deop <player>"));
                }
            } else {
                iCommandSender.sendMessage(new TextComponentTranslation("Please Use The Command By Host"));
            }
        } else {
            iCommandSender.sendMessage(new TextComponentTranslation("Please Use The Command By Player"));
        }
    }
}