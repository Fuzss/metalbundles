package fuzs.metalbundles.data;

import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.recipes.CopyTagShapedRecipeBuilder;
import fuzs.puzzleslib.api.data.v2.recipes.CopyTagShapelessRecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.LEATHER_BUNDLE_ITEM.value())
                .define('#', Items.LEATHER)
                .define('-', Items.STRING)
                .pattern("-#-")
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(recipeOutput);
        CopyTagShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.COPPER_BUNDLE_ITEM.value())
                .define('X', ModRegistry.LEATHER_BUNDLE_ITEM.value())
                .define('#', Items.COPPER_INGOT)
                .copyFrom(ModRegistry.LEATHER_BUNDLE_ITEM.value())
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .unlockedBy(getHasName(ModRegistry.LEATHER_BUNDLE_ITEM.value()),
                        has(ModRegistry.LEATHER_BUNDLE_ITEM.value())
                )
                .save(recipeOutput);
        CopyTagShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.IRON_BUNDLE_ITEM.value())
                .define('X', ModRegistry.COPPER_BUNDLE_ITEM.value())
                .define('#', Items.IRON_INGOT)
                .copyFrom(ModRegistry.COPPER_BUNDLE_ITEM.value())
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .unlockedBy(getHasName(ModRegistry.COPPER_BUNDLE_ITEM.value()),
                        has(ModRegistry.COPPER_BUNDLE_ITEM.value())
                )
                .save(recipeOutput);
        CopyTagShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.GOLDEN_BUNDLE_ITEM.value())
                .define('X', ModRegistry.IRON_BUNDLE_ITEM.value())
                .define('#', Items.GOLD_INGOT)
                .copyFrom(ModRegistry.IRON_BUNDLE_ITEM.value())
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .unlockedBy(getHasName(ModRegistry.IRON_BUNDLE_ITEM.value()), has(ModRegistry.IRON_BUNDLE_ITEM.value()))
                .save(recipeOutput);
        CopyTagShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.DIAMOND_BUNDLE_ITEM.value())
                .define('X', ModRegistry.GOLDEN_BUNDLE_ITEM.value())
                .define('#', Items.DIAMOND)
                .copyFrom(ModRegistry.GOLDEN_BUNDLE_ITEM.value())
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .unlockedBy(getHasName(ModRegistry.GOLDEN_BUNDLE_ITEM.value()),
                        has(ModRegistry.GOLDEN_BUNDLE_ITEM.value())
                )
                .save(recipeOutput);
        CopyTagShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModRegistry.NETHERITE_BUNDLE_ITEM.value())
                .requires(ModRegistry.DIAMOND_BUNDLE_ITEM.value())
                .requires(Items.NETHERITE_INGOT)
                .copyFrom(ModRegistry.DIAMOND_BUNDLE_ITEM.value())
                .unlockedBy(getHasName(ModRegistry.DIAMOND_BUNDLE_ITEM.value()),
                        has(ModRegistry.DIAMOND_BUNDLE_ITEM.value())
                )
                .save(recipeOutput);
    }
}
