package be.mdeswert.forge.mods.trampolines.events;

import be.mdeswert.forge.mods.core.blocks.BlockUtils;
import be.mdeswert.forge.mods.trampolines.Main;
import be.mdeswert.forge.mods.trampolines.blocks.Trampoline;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.function.BiConsumer;
import java.util.function.Function;

import static be.mdeswert.forge.mods.core.events.EventUtils.handlePlayerEvent;

public class TrampolineJump {
    @SubscribeEvent
    public void handleLivingJumpEvent(LivingJumpEvent event) {
        handlePlayerEvent(event, this::jump);
    }

    @SubscribeEvent
     public void handleLivingFallEvent(LivingFallEvent event) {
        handlePlayerEvent(event, this::bounce);
    }

    @SubscribeEvent
    public void handleLivingHurtEvent(LivingHurtEvent event) {
        handlePlayerEvent(event, this::negateFallDamage);
    }

    private void jump(LivingEvent event, EntityPlayer player) {
        whenOnTrampolineBlock(event, player, (e, p) -> p.motionY *= 5);
    }

    private void bounce(LivingEvent event, EntityPlayer player) {
        whenOnTrampolineBlock(event, player, (e, p) -> p.addVelocity(0, 2, 0));
    }

    private void negateFallDamage(LivingEvent event, EntityPlayer player) {
        if(isFallDamage(event)) {
            return;
        }

        whenOnWoolBlock(event, player, this::cancelEvent);
        whenOnTrampolineBlock(event, player, this::cancelEvent);
    }

    private Boolean isFallDamage(LivingEvent event) {
        return toLivingHurtEvent(event,
                                 e -> !"fall".equals(e.source.getDamageType()),
                                 true);
    }

    private void cancelEvent(LivingEvent event, EntityPlayer player) {
        event.setCanceled(true);
    }

    private void whenOnWoolBlock(LivingEvent event, EntityPlayer player, BiConsumer<LivingEvent, EntityPlayer> eventHandler) {
        whenOnBlock(Blocks.wool, event, player, eventHandler);
    }

    private void whenOnTrampolineBlock(LivingEvent event, EntityPlayer player, BiConsumer<LivingEvent, EntityPlayer> eventHandler) {
        whenOnBlock(new Trampoline(), event, player, eventHandler);
    }

    private void whenOnBlock(Block block, LivingEvent event, EntityPlayer player, BiConsumer<LivingEvent, EntityPlayer> eventHandler) {
        if(BlockUtils.getBlockUnderneathEntity(player) != block) {
            return;
        }

        eventHandler.accept(event, player);
    }

    private <T> T toLivingHurtEvent(LivingEvent event, Function<LivingHurtEvent, T> evaluation, T defaultReturnValue) {
        if(!(event instanceof LivingHurtEvent)) {
            return defaultReturnValue;
        }

        return evaluation.apply((LivingHurtEvent) event);
    }
}
