package be.mdeswert.forge.mods.core.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ShapelessRecipe implements Recipe {
    private final ItemStack output;
    private final Object[] input;

    public ShapelessRecipe(ItemStack output, Object... input) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void register() {
        GameRegistry.addShapelessRecipe(output, input);
    }
}
