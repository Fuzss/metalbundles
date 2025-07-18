package fuzs.metalbundles.data;

import com.google.common.collect.Maps;
import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.TransmuteRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        this.bundleRecipes(ModRegistry.COPPER_BUNDLES_ITEM_TAG_KEY,
                Items.COPPER_INGOT,
                Items.BUNDLE.builtInRegistryHolder(),
                ModRegistry.COPPER_BUNDLE_ITEM,
                getVanillaBundleItems(),
                ModRegistry.COPPER_BUNDLE_ITEMS);
        this.bundleRecipes(ModRegistry.IRON_BUNDLES_ITEM_TAG_KEY,
                Items.IRON_INGOT,
                ModRegistry.COPPER_BUNDLE_ITEM,
                ModRegistry.IRON_BUNDLE_ITEM,
                ModRegistry.COPPER_BUNDLE_ITEMS,
                ModRegistry.IRON_BUNDLE_ITEMS);
        this.bundleRecipes(ModRegistry.GOLDEN_BUNDLES_ITEM_TAG_KEY,
                Items.GOLD_INGOT,
                ModRegistry.IRON_BUNDLE_ITEM,
                ModRegistry.GOLDEN_BUNDLE_ITEM,
                ModRegistry.IRON_BUNDLE_ITEMS,
                ModRegistry.GOLDEN_BUNDLE_ITEMS);
        this.bundleRecipes(ModRegistry.DIAMOND_BUNDLES_ITEM_TAG_KEY,
                Items.DIAMOND,
                ModRegistry.GOLDEN_BUNDLE_ITEM,
                ModRegistry.DIAMOND_BUNDLE_ITEM,
                ModRegistry.GOLDEN_BUNDLE_ITEMS,
                ModRegistry.DIAMOND_BUNDLE_ITEMS);
        this.bundleRecipes(ModRegistry.NETHERITE_BUNDLES_ITEM_TAG_KEY,
                Items.NETHERITE_INGOT,
                ModRegistry.DIAMOND_BUNDLE_ITEM,
                ModRegistry.NETHERITE_BUNDLE_ITEM,
                ModRegistry.DIAMOND_BUNDLE_ITEMS,
                ModRegistry.NETHERITE_BUNDLE_ITEMS);
    }

    static Map<DyeColor, Holder.Reference<Item>> getVanillaBundleItems() {
        return Arrays.stream(DyeColor.values())
                .collect(Maps.toImmutableEnumMap(Function.<DyeColor>identity(),
                        (DyeColor dyeColor) -> BundleItem.getByColor(dyeColor).builtInRegistryHolder()));
    }

    private void bundleRecipes(TagKey<Item> tagKey, Item ingredientItem, Holder.Reference<Item> bundleIngredientItem, Holder.Reference<Item> bundleResultItem, Map<DyeColor, Holder.Reference<Item>> bundleIngredientItems, Map<DyeColor, Holder.Reference<Item>> bundleResultItems) {
        this.bundleRecipes(tagKey.location().getPath(),
                ingredientItem,
                bundleIngredientItem,
                bundleResultItem,
                bundleIngredientItems,
                bundleResultItems);
        this.bundleDyeRecipes(tagKey, bundleResultItems);
    }

    private void bundleRecipes(String recipeGroup, Item ingredientItem, Holder.Reference<Item> bundleIngredientItem, Holder.Reference<Item> bundleResultItem, Map<DyeColor, Holder.Reference<Item>> bundleIngredientItems, Map<DyeColor, Holder.Reference<Item>> bundleResultItems) {
        this.bundleRecipe(recipeGroup, ingredientItem, bundleIngredientItem, bundleResultItem);
        for (Map.Entry<DyeColor, Holder.Reference<Item>> entry : bundleIngredientItems.entrySet()) {
            this.bundleRecipe(recipeGroup, ingredientItem, entry.getValue(), bundleResultItems.get(entry.getKey()));
        }
    }

    private void bundleRecipe(String recipeGroup, Item ingredientItem, Holder.Reference<Item> bundleIngredientItem, Holder.Reference<Item> bundleResultItem) {
        TransmuteRecipeBuilder.transmute(RecipeCategory.TOOLS,
                        Ingredient.of(bundleIngredientItem.value()),
                        Ingredient.of(ingredientItem),
                        bundleResultItem.value())
                .group(recipeGroup)
                .unlockedBy(getHasName(ingredientItem), this.has(ingredientItem))
                .save(this.output);
    }

    private void bundleDyeRecipes(TagKey<Item> tagKey, Map<DyeColor, Holder.Reference<Item>> bundleItems) {
        Ingredient ingredient = this.tag(tagKey);
        for (Map.Entry<DyeColor, Holder.Reference<Item>> entry : bundleItems.entrySet()) {
            DyeItem dyeItem = DyeItem.byColor(entry.getKey());
            TransmuteRecipeBuilder.transmute(RecipeCategory.TOOLS,
                            ingredient,
                            Ingredient.of(dyeItem),
                            entry.getValue().value())
                    .group(tagKey.location().getPath())
                    .unlockedBy(getHasName(dyeItem), this.has(dyeItem))
                    .save(this.output,
                            ResourceKey.create(Registries.RECIPE,
                                    MetalBundles.id(getItemName(entry.getValue().value()) + "_from_dying")));
        }
    }
}
