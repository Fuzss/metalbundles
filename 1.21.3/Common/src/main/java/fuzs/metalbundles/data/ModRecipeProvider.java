package fuzs.metalbundles.data;

import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.TransmuteRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        this.bundleRecipe(recipeOutput, Items.BUNDLE, Items.COPPER_INGOT, ModRegistry.COPPER_BUNDLE_ITEM.value());
        this.bundleRecipe(recipeOutput,
                ModRegistry.COPPER_BUNDLE_ITEM.value(),
                Items.IRON_INGOT,
                ModRegistry.IRON_BUNDLE_ITEM.value());
        this.bundleRecipe(recipeOutput,
                ModRegistry.IRON_BUNDLE_ITEM.value(),
                Items.GOLD_INGOT,
                ModRegistry.GOLDEN_BUNDLE_ITEM.value());
        this.bundleRecipe(recipeOutput,
                ModRegistry.GOLDEN_BUNDLE_ITEM.value(),
                Items.DIAMOND,
                ModRegistry.DIAMOND_BUNDLE_ITEM.value());
        this.bundleRecipe(recipeOutput,
                ModRegistry.DIAMOND_BUNDLE_ITEM.value(),
                Items.NETHERITE_INGOT,
                ModRegistry.NETHERITE_BUNDLE_ITEM.value());
    }

    private void bundleRecipe(RecipeOutput recipeOutput, Item input, Item material, Item result) {
        TransmuteRecipeBuilder.transmute(RecipeCategory.TOOLS, Ingredient.of(input), Ingredient.of(material), result)
                .unlockedBy(getHasName(input), this.has(input))
                .save(recipeOutput);
    }
}
