package be.mdeswert.forge.mods.trampolines;

import be.mdeswert.forge.mods.trampolines.blocks.Trampoline;
import be.mdeswert.forge.mods.trampolines.events.PlayerDrop;
import be.mdeswert.forge.mods.trampolines.events.TrampolineJump;
import be.mdeswert.forge.mods.trampolines.items.Cloth;
import be.mdeswert.forge.mods.trampolines.items.Parachute;
import be.mdeswert.forge.mods.trampolines.recipes.AdditionalRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main {
    public static final String MODID = "trampolines";
    public static final String VERSION = "1.0";

    public static final PlayerDrop playerDropEvent = new PlayerDrop();
    public static final TrampolineJump trampolineJump = new TrampolineJump();


    @EventHandler
    public void init(FMLInitializationEvent initializationEvent) {
        registerItems();
        registerBlocks();
        registerRecipes();
        registerEvents();
    }

    private void registerItems() {
        new Parachute().register();
        new Cloth().register();
    }

    private void registerBlocks() {
        new Trampoline().register();
    }

    private void registerRecipes() {
        for (AdditionalRecipe additionalRecipe : AdditionalRecipe.values()) {
            additionalRecipe.register();
        }
    }

    private void registerEvents() {
        MinecraftForge.EVENT_BUS.register(playerDropEvent);
        MinecraftForge.EVENT_BUS.register(trampolineJump);
    }
}
