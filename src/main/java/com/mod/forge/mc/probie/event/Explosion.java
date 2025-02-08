package com.mod.forge.mc.probie.event;

import com.mod.forge.mc.probie.Main;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Main.modId)
public class Explosion {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void explosion(ExplosionEvent explosionEvent) {
        explosionEvent.setCanceled(true);
    }
}