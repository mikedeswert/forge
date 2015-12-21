package be.mdeswert.forge.mods.trampolines.items;

import be.mdeswert.forge.mods.core.recipes.Recipe;
import be.mdeswert.forge.mods.core.Registerable;
import be.mdeswert.forge.mods.core.recipes.ShapelessRecipe;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

import static be.mdeswert.forge.mods.core.recipes.ShapedRecipe.ShapedRecipeBuilder.aShapedRecipe;

public class Cloth extends Item implements Registerable {
    public static final String NAME = "cloth";
    private List<Recipe> recipes;

    public Cloth() {
        super();
        setUnlocalizedName(NAME);
        setCreativeTab(CreativeTabs.tabMaterials);
        recipes = createRecipes();
    }

    @Override
    public void register() {
        recipes.forEach(Recipe::register);
        GameRegistry.registerItem(this, NAME);
    }

    private List<Recipe> createRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        recipes.add(new ShapelessRecipe(new ItemStack(Items.string), this));
        recipes.add(aShapedRecipe(new ItemStack(this))
                .withShape("sss",
                        "sss",
                        "sss")
                .withItemMappings(new Tuple('s', Items.string))
                .build());

        return recipes;
    }
}
