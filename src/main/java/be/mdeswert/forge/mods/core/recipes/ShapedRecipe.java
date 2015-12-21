package be.mdeswert.forge.mods.core.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShapedRecipe implements Recipe {
    private ItemStack output;
    private String[] shape;
    private Tuple[] itemMappings;

    private ShapedRecipe(ItemStack output, String[] shape, Tuple[] itemMappings) {
        this.output = output;
        this.shape = shape;
        this.itemMappings = itemMappings;
    }

    @Override
    public void register() {
        GameRegistry.addShapedRecipe(output, this.getRecipe());
    }

    public Object[] getRecipe() {
        if (isInvalidRecipe()) {
            throw new IllegalArgumentException("The recipe is invalid! Check whether all characters are properly mapped.\n" + this.toString());
        }

        List<Object> recipe = new ArrayList<>();

        Collections.addAll(recipe, shape);

        for (Tuple itemMapping : itemMappings) {
            recipe.add(itemMapping.getFirst());
            recipe.add(itemMapping.getSecond());
        }

        return recipe.toArray();
    }

    private boolean isInvalidRecipe() {
        return allCharactersInShapeAreMapped();
    }

    private boolean allCharactersInShapeAreMapped() {
        for (String line : shape) {
            if(notAllCharactersInLIneAreMapped(line)) {
                return false;
            }
        }

        return true;
    }

    private boolean notAllCharactersInLIneAreMapped(String line) {
        for(char character : line.toCharArray()) {
            if(itemMappingsDoNotContainCharacter(character)) {
                return true;
            }
        }

        return false;
    }

    private boolean itemMappingsDoNotContainCharacter(char character) {
        try {
            for(Tuple itemMapping : itemMappings) {
                if(character == (char) itemMapping.getFirst()) {
                    return false;
                }
            }

            return true;
        } catch(ClassCastException e) {
            throw new IllegalArgumentException("First element of item mapping should be of type char", e);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Shape:\n");

        for (String line : shape) {
            builder.append(String.format("[%s]\n", line));
        }

        builder.append("Mappings:\n");

        for (Tuple itemMapping : itemMappings) {
            builder.append(String.format("%s = %s", itemMapping.getFirst(),
                    itemMapping.getSecond().getClass().getName()));
        }

        return builder.toString();
    }

    public static final class ShapedRecipeBuilder {
        private ItemStack output;
        private String[] shape = new String[]{};
        private Tuple[] itemMappings = new Tuple[]{};

        private ShapedRecipeBuilder(ItemStack output) {
            this.output = output;
        }

        public static ShapedRecipeBuilder aShapedRecipe(ItemStack output) {
            return new ShapedRecipeBuilder(output);
        }

        public ShapedRecipeBuilder withShape(String... shape) {
            this.shape = shape;
            return this;
        }

        public ShapedRecipeBuilder withItemMappings(Tuple... itemMappings) {
            this.itemMappings = itemMappings;
            return this;
        }

        public ShapedRecipe build() {
            return new ShapedRecipe(output, shape, itemMappings);
        }
    }
}
