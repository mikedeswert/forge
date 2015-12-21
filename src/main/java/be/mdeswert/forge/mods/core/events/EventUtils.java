package be.mdeswert.forge.mods.core.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.function.BiConsumer;

public class EventUtils {
    public static <T extends LivingEvent> void handlePlayerEvent(T event, BiConsumer<T, EntityPlayer> eventHandler) {
        if(!(event.entity instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.entity;

        eventHandler.accept(event, player);
    }

}
