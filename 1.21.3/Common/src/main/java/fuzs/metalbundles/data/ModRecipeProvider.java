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
                getVanillaBundleItems(),
                Items.COPPER_INGOT,
                ModRegistry.COPPER_BUNDLE_ITEMS);
        this.bundleRecipes(ModRegistry.IRON_BUNDLES_ITEM_TAG_KEY,
                ModRegistry.COPPER_BUNDLE_ITEMS,
                Items.IRON_INGOT,
                ModRegistry.IRON_BUNDLE_ITEMS);
        this.bundleRecipes(ModRegistry.GOLDEN_BUNDLES_ITEM_TAG_KEY,
                ModRegistry.IRON_BUNDLE_ITEMS,
                Items.GOLD_INGOT,
                ModRegistry.GOLDEN_BUNDLE_ITEMS);
        this.bundleRecipes(ModRegistry.DIAMOND_BUNDLES_ITEM_TAG_KEY,
                ModRegistry.GOLDEN_BUNDLE_ITEMS,
                Items.DIAMOND,
                ModRegistry.DIAMOND_BUNDLE_ITEMS);
        this.bundleRecipes(ModRegistry.NETHERITE_BUNDLES_ITEM_TAG_KEY,
                ModRegistry.DIAMOND_BUNDLE_ITEMS,
                Items.NETHERITE_INGOT,
                ModRegistry.NETHERITE_BUNDLE_ITEMS);
    }

    static Map<DyeColor, Holder.Reference<Item>> getVanillaBundleItems() {
        return Arrays.stream(DyeColor.values())
                .collect(Maps.toImmutableEnumMap(Function.<DyeColor>identity(),
                        (DyeColor dyeColor) -> BundleItem.getByColor(dyeColor).builtInRegistryHolder()));
    }

    private void bundleRecipes(TagKey<Item> tagKey, Map<DyeColor, Holder.Reference<Item>> bundleIngredientItems, Item item, Map<DyeColor, Holder.Reference<Item>> bundleResultItems) {
        this.bundleRecipes(tagKey.location().getPath(), bundleIngredientItems, item, bundleResultItems);
        this.bundleDyeRecipes(tagKey, bundleResultItems);
    }

    private void bundleRecipes(String recipeGroup, Map<DyeColor, Holder.Reference<Item>> bundleIngredientItems, Item item, Map<DyeColor, Holder.Reference<Item>> bundleResultItems) {
        for (Map.Entry<DyeColor, Holder.Reference<Item>> entry : bundleIngredientItems.entrySet()) {
            TransmuteRecipeBuilder.transmute(RecipeCategory.TOOLS,
                            Ingredient.of(entry.getValue().value()),
                            Ingredient.of(item),
                            bundleResultItems.get(entry.getKey()).value())
                    .group(recipeGroup)
                    .unlockedBy(getHasName(item), this.has(item))
                    .save(this.output);
        }
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
