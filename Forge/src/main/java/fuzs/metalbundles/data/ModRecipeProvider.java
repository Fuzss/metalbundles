package fuzs.metalbundles.data;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.data.v1.AbstractRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.LEATHER_BUNDLE_ITEM.get())
                .define('#', Items.LEATHER)
                .define('-', Items.STRING)
                .pattern("-#-")
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(exporter);
        netheriteSmithing(exporter, ModRegistry.LEATHER_BUNDLE_ITEM.get(), Items.COPPER_INGOT, RecipeCategory.TOOLS, ModRegistry.COPPER_BUNDLE_ITEM.get());
        netheriteSmithing(exporter, ModRegistry.COPPER_BUNDLE_ITEM.get(), Items.IRON_INGOT, RecipeCategory.TOOLS, ModRegistry.IRON_BUNDLE_ITEM.get());
        netheriteSmithing(exporter, ModRegistry.IRON_BUNDLE_ITEM.get(), Items.GOLD_INGOT, RecipeCategory.TOOLS, ModRegistry.GOLDEN_BUNDLE_ITEM.get());
        netheriteSmithing(exporter, ModRegistry.GOLDEN_BUNDLE_ITEM.get(), Items.DIAMOND, RecipeCategory.TOOLS, ModRegistry.DIAMOND_BUNDLE_ITEM.get());
        netheriteSmithing(exporter, ModRegistry.DIAMOND_BUNDLE_ITEM.get(), Items.NETHERITE_INGOT, RecipeCategory.TOOLS, ModRegistry.NETHERITE_BUNDLE_ITEM.get());
    }

    protected static void netheriteSmithing(Consumer<FinishedRecipe> finishedRecipeConsumer, Item base, Item addition, RecipeCategory category, Item resultItem) {
        UpgradeRecipeBuilder.smithing(Ingredient.of(base), Ingredient.of(addition), category, resultItem).unlocks(getHasName(addition), has(addition)).save(finishedRecipeConsumer, MetalBundles.id(getItemName(resultItem) + "_smithing"));
    }
}
