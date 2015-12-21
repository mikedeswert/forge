package be.mdeswert.forge.mods.trampolines.blocks;


import be.mdeswert.forge.mods.core.blocks.BlockUtils;
import be.mdeswert.forge.mods.core.recipes.Recipe;
import be.mdeswert.forge.mods.core.Registerable;
import be.mdeswert.forge.mods.core.recipes.ShapedRecipe;
import be.mdeswert.forge.mods.trampolines.items.Cloth;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;

import static be.mdeswert.forge.mods.core.recipes.ShapedRecipe.ShapedRecipeBuilder.aShapedRecipe;

public class Trampoline extends Block implements Registerable {
    public static final String NAME = "trampoline";
    private Recipe recipe;

    public Trampoline() {
        super(Material.wood);
        setUnlocalizedName(NAME);
        setCreativeTab(CreativeTabs.tabBlock);
        recipe = createRecipe();
    }

    @Override
    public void register() {
        recipe.register();
        BlockUtils.registerBlock(this, NAME);
    }

    private ShapedRecipe createRecipe() {
        return aShapedRecipe(new ItemStack(this, 1))
               .withShape("wcw",
                          "w w",
                          "www")
               .withItemMappings(new Tuple('c', new Cloth()),
                                 new Tuple('w', Blocks.planks))
               .build();
    }
}
