/*
 * @Mod: Guardian游戏守卫
 * @Author: Probie(菜鸟)
 * @McVersion: 1.12.2
 * @JDK: 1.8
 */

package com.mod.forge.mc.probie;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.mod.forge.mc.probie.command.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import com.programe.probie.ProgrameTool.Computer.Windows;
import com.programe.probie.ProgrameTool.Datasql.Properties;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Main.modId, name = Main.name, version = Main.version, useMetadata = true)
public class Main {

    public static final String modId = "guardian";
    public static final String name = "guardian";
    public static final String version = "2.1.0";

    public static final String mcversion = "1.12.2";
    public static final String Author = "Probie";

    public static final String github = "https://github.com/BProbie/project/tree/guardian";
    public static final String renew = "https://raw.githubusercontent.com/BProbie/project/refs/heads/guardian/guardian.renew";
    public static final String renewFile = "https://raw.githubusercontent.com/BProbie/project/refs/heads/guardian/guardian.jar";

    private static final Properties banProperties = new Properties();

    public static Properties getBanProperties() {
        return banProperties;
    }

    protected static final MinecraftServer minecraftServer = FMLCommonHandler.instance().getMinecraftServerInstance();

    public static MinecraftServer getMinecraftServer() {
        return minecraftServer;
    }

    protected static String hostName;

    public static void setHostName(String hostName) {
        Main.hostName = hostName;
    }

    public static String getHostName() {
        return hostName;
    }

    @Mod.EventHandler
    public void fmlInitializationEvent(FMLInitializationEvent fmlInitializationEvent) {
        banProperties.setPath(Windows.getHere());
        banProperties.setFileName("Guardian-Ban.Properties");
        banProperties.setComment("The Properties To Store The Guardian Ban Data");
    }

    @Mod.EventHandler
    public void fmlServerStartingEvent(FMLServerStartingEvent fmlServerStartingEvent) {
        fmlServerStartingEvent.registerServerCommand(new Kill());
        fmlServerStartingEvent.registerServerCommand(new Fly());
        fmlServerStartingEvent.registerServerCommand(new Op());
        fmlServerStartingEvent.registerServerCommand(new Deop());
        fmlServerStartingEvent.registerServerCommand(new Kick());
        fmlServerStartingEvent.registerServerCommand(new Ban());
        fmlServerStartingEvent.registerServerCommand(new Deban());
        fmlServerStartingEvent.registerServerCommand(new BanIP());
        fmlServerStartingEvent.registerServerCommand(new DebanIP());
        fmlServerStartingEvent.registerServerCommand(new Fps());
        fmlServerStartingEvent.registerServerCommand(new BanProperties());
    }

    public static void main(String[] args) {
        //TODO
    }

    public static void renew() {
        //TODO
    }

    public static String getComputerUUID() {
        String computerUUID = "null";
        try {
            Process process = Runtime.getRuntime().exec("wmic csproduct get uuid");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            reader.readLine();
            if ((line = reader.readLine()) != null) {
                computerUUID = line.trim();
            }
            reader.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return computerUUID;
    }
}