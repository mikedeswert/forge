package be.mdeswert.forge.mods.trampolines.recipes;


import be.mdeswert.forge.mods.core.recipes.Recipe;
import be.mdeswert.forge.mods.core.Registerable;
import be.mdeswert.forge.mods.core.recipes.ShapelessRecipe;
import be.mdeswert.forge.mods.trampolines.items.Cloth;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;

import static be.mdeswert.forge.mods.core.recipes.ShapedRecipe.ShapedRecipeBuilder.aShapedRecipe;

public enum AdditionalRecipe implements Registerable {
    STRING_TO_WOOL(aShapedRecipe(new ItemStack(Blocks.wool))
            .withShape("ss",
                    "ss")
            .withItemMappings(new Tuple('s', Items.string))
            .build()),
    WOOL_TO_STRING(new ShapelessRecipe(new ItemStack(Items.string, 4), Blocks.wool)),
    CLOTH_TO_STRING(new ShapelessRecipe(new ItemStack(Items.string, 9), Cloth.getInstance()));

    private final Recipe recipe;

    AdditionalRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public void register() {
        recipe.register();
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Additional recipes have no name.");
    }


}
