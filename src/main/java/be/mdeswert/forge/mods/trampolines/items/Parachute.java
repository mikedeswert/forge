package be.mdeswert.forge.mods.trampolines.items;

import be.mdeswert.forge.mods.core.recipes.Recipe;
import be.mdeswert.forge.mods.core.Registerable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static be.mdeswert.forge.mods.core.recipes.ShapedRecipe.ShapedRecipeBuilder.aShapedRecipe;

public class Parachute extends Item implements Registerable {
    public static final String NAME = "parachute";
    private Recipe recipe;

    public Parachute() {
        super();
        this.setUnlocalizedName(NAME);
        this.setCreativeTab(CreativeTabs.tabTransport);
        recipe = createRecipe();
    }

    @Override
    public void register() {
        recipe.register();
        GameRegistry.registerItem(this, NAME);
    }

    private Recipe createRecipe() {
        return aShapedRecipe(new ItemStack(this))
                .withShape("ccc",
                        "s s",
                        " s ")
                .withItemMappings(new Tuple('c', this),
                        new Tuple('s', Items.string))
                .build();
    }
}
