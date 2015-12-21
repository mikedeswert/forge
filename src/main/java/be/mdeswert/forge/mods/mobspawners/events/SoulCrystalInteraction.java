package be.mdeswert.forge.mods.mobspawners.events;

import be.mdeswert.forge.mods.mobspawners.items.SoulCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static be.mdeswert.forge.mods.core.events.EventUtils.handlePlayerEvent;

public class SoulCrystalInteraction {
    @SubscribeEvent
    public void handleEntityInteractionEvent(EntityInteractEvent event) {
        handlePlayerEvent(event, this::handleEvent);
    }

    private void handleEvent(EntityInteractEvent event, EntityPlayer player) {
        if(isInvalidTarget(event.target) || hasNotEquippedSoulCrystal(player)) {
            return;
        }

    }

    private boolean isInvalidTarget(Entity target) {
        return !(target instanceof EntityMob || target instanceof EntityAnimal);
    }

    private boolean hasNotEquippedSoulCrystal(EntityPlayer player) {
        return !(player.getCurrentEquippedItem().getItem() instanceof SoulCrystal);
    }
}
