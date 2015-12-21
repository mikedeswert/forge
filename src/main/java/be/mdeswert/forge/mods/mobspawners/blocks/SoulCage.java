package be.mdeswert.forge.mods.mobspawners.blocks;

import be.mdeswert.forge.mods.core.blocks.BlockUtils;
import be.mdeswert.forge.mods.core.recipes.Recipe;
import be.mdeswert.forge.mods.core.Registerable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;

import static be.mdeswert.forge.mods.core.recipes.ShapedRecipe.ShapedRecipeBuilder.aShapedRecipe;

public class SoulCage extends Block implements Registerable {
    public static final String NAME = "soulcage";
    private Recipe recipe;

    public SoulCage() {
        super(Material.rock);
        setHardness(5.0F);
        setStepSound(soundTypeMetal);
        setUnlocalizedName(NAME);
        setCreativeTab(CreativeTabs.tabMaterials);
        recipe = createRecipe();
    }

    private Recipe createRecipe() {
        return aShapedRecipe(new ItemStack(this))
                .withShape("obo",
                        "b b",
                        "obo")
                .withItemMappings(new Tuple('o', Blocks.obsidian),
                        new Tuple('b', Blocks.iron_bars))
                .build();
    }

    @Override
    public void register() {
        recipe.register();
        BlockUtils.registerBlock(this, NAME);
    }
}
