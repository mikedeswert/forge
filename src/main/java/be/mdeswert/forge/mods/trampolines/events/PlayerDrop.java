package be.mdeswert.forge.mods.trampolines.events;

import be.mdeswert.forge.mods.core.events.EventUtils;
import be.mdeswert.forge.mods.trampolines.items.Parachute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PlayerDrop {

    private Map<String, Boolean> parachutesDeployed = new HashMap<>();

    @SubscribeEvent
     public void handleLivingUpdateEvent(LivingUpdateEvent event) {
        EventUtils.handlePlayerEvent(event, this::deployParachute);
    }

    @SubscribeEvent
    public void handleLivingFallEvent(LivingFallEvent event) {
        EventUtils.handlePlayerEvent(event, this::negateFallDamage);
    }

    private void deployParachute(LivingEvent event, EntityPlayer player) {
        if (shouldNotDeployParachute(player)) {
            return;
        }

        parachutesDeployed.put(player.getName(), true);
        player.motionY = -0.1;
    }

    private void negateFallDamage(LivingEvent event, EntityPlayer player) {
        if (!isParachuteDeployed(player)) {
            return;
        }

        if(!player.isAirBorne) {
            parachutesDeployed.put(player.getName(), false);
        }
        event.setCanceled(true);
    }

    private boolean shouldNotDeployParachute(EntityPlayer player) {
        return !isParachuteDeployed(player) &&
               (player.capabilities.isCreativeMode ||
                doesNotHaveParachute(player) ||
                !player.isSneaking() ||
                player.onGround);
    }

    private boolean isParachuteDeployed(EntityPlayer player) {
        Boolean isParachuteDeployed = parachutesDeployed.get(player.getName());
        return isParachuteDeployed == null ? false : isParachuteDeployed;
    }

    private boolean doesNotHaveParachute(EntityPlayer player) {
        ItemStack[] inventory = player.inventory.mainInventory;

        return Stream.of(inventory)
                .filter(itemStack -> itemStack != null)
                .map(ItemStack::getItem)
                .filter(this::isParachute)
                .count() == 0;
    }

    private boolean isParachute(Item item) {
        return item != null && item instanceof Parachute;
    }
}
