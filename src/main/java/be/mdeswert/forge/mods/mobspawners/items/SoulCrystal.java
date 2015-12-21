package be.mdeswert.forge.mods.mobspawners.items;

import be.mdeswert.forge.mods.core.events.EventUtils;
import be.mdeswert.forge.mods.core.recipes.Recipe;
import be.mdeswert.forge.mods.core.Registerable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static be.mdeswert.forge.mods.core.events.EventUtils.handlePlayerEvent;
import static be.mdeswert.forge.mods.core.recipes.ShapedRecipe.ShapedRecipeBuilder.aShapedRecipe;

public class SoulCrystal extends Item implements Registerable {
    public static final String NAME = "soulcrystal";
    private Recipe recipe;

    public SoulCrystal() {
        super();
        setUnlocalizedName(NAME);
        setCreativeTab(CreativeTabs.tabMisc);
        recipe = createRecipe();
    }

    @Override
    public void register() {
        recipe.register();
        GameRegistry.registerItem(this, NAME);
    }

    private Recipe createRecipe() {
        return aShapedRecipe(new ItemStack(this))
                .withShape("sss",
                        "sds",
                        "sss")
                .withItemMappings(new Tuple('s', Blocks.soul_sand),
                        new Tuple('d', Items.diamond))
                .build();
    }
}
