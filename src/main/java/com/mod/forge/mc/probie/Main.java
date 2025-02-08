/*
 * @Mod: Guardian游戏守卫
 * @Author: Probie(菜鸟)
 * @McVersion: 1.12.2
 * @JDK: 1.8
 */

package com.mod.forge.mc.probie;

import com.mod.forge.mc.probie.command.*;
import net.minecraftforge.fml.common.Mod;
import com.programe.probie.ProgrameTool.Computer.Windows;
import com.programe.probie.ProgrameTool.Datasql.Properties;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Main.modId, name = Main.name, version = Main.version, useMetadata = true)
public class Main {

    public static final String modId = "guardian";
    public static final String name = "guardian";
    public static final String version = "2.0.0";

    public static final String mcversion = "1.12.2";
    public static final String Author = "Probie";

    private static final Properties banProperties = new Properties();

    public static Properties getBanProperties() {
        return banProperties;
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
        fmlServerStartingEvent.registerServerCommand(new Fly());
        fmlServerStartingEvent.registerServerCommand(new Op());
        fmlServerStartingEvent.registerServerCommand(new Deop());
        fmlServerStartingEvent.registerServerCommand(new Kick());
        fmlServerStartingEvent.registerServerCommand(new Ban());
        fmlServerStartingEvent.registerServerCommand(new Deban());
        fmlServerStartingEvent.registerServerCommand(new BanIP());
        fmlServerStartingEvent.registerServerCommand(new DebanIP());
        fmlServerStartingEvent.registerServerCommand(new Fps());
    }

}