package fuzs.metalbundles.data;

import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.recipes.CopyComponentsShapedRecipeBuilder;
import fuzs.puzzleslib.api.data.v2.recipes.CopyComponentsShapelessRecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import fuzs.metalbundles.MetalBundles;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.LEATHER_BUNDLE_ITEM.value()).define('#',
                Items.LEATHER
        ).define('-', Items.STRING).pattern("-#-").pattern("# #").pattern("###").unlockedBy(getHasName(Items.STRING),
                has(Items.STRING)
        ).save(recipeOutput);
        CopyComponentsShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.COPPER_BUNDLE_ITEM.value()).define(
                'X', Ingredient.of(ModRegistry.LEATHER_BUNDLE_ITEM.value(), Items.BUNDLE)).define('#',
                Items.COPPER_INGOT
        ).copyFrom(ModRegistry.LEATHER_BUNDLE_ITEM.value()).pattern(" # ").pattern("#X#").pattern(" # ").unlockedBy(
                getHasName(ModRegistry.LEATHER_BUNDLE_ITEM.value(), Items.BUNDLE),
                has(ModRegistry.LEATHER_BUNDLE_ITEM.value(), Items.BUNDLE)
        ).save(recipeOutput);
        CopyComponentsShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.IRON_BUNDLE_ITEM.value())
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
        CopyComponentsShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.GOLDEN_BUNDLE_ITEM.value()).define(
                'X', ModRegistry.IRON_BUNDLE_ITEM.value()).define('#', Items.GOLD_INGOT).copyFrom(
                ModRegistry.IRON_BUNDLE_ITEM.value()).pattern(" # ").pattern("#X#").pattern(" # ").unlockedBy(
                getHasName(ModRegistry.IRON_BUNDLE_ITEM.value()), has(ModRegistry.IRON_BUNDLE_ITEM.value())).save(
                recipeOutput);
        CopyComponentsShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.DIAMOND_BUNDLE_ITEM.value()).define(
                'X', ModRegistry.GOLDEN_BUNDLE_ITEM.value()).define('#', Items.DIAMOND).copyFrom(
                ModRegistry.GOLDEN_BUNDLE_ITEM.value()).pattern(" # ").pattern("#X#").pattern(" # ").unlockedBy(
                getHasName(ModRegistry.GOLDEN_BUNDLE_ITEM.value()), has(ModRegistry.GOLDEN_BUNDLE_ITEM.value())).save(
                recipeOutput);
        CopyComponentsShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModRegistry.NETHERITE_BUNDLE_ITEM.value())
                .requires(ModRegistry.DIAMOND_BUNDLE_ITEM.value())
                .requires(Items.NETHERITE_INGOT)
                .copyFrom(ModRegistry.DIAMOND_BUNDLE_ITEM.value())
                .unlockedBy(getHasName(ModRegistry.DIAMOND_BUNDLE_ITEM.value()),
                        has(ModRegistry.DIAMOND_BUNDLE_ITEM.value())
                )
                .save(recipeOutput);

        // <<< Dyeing recipes for all dyed metal bundles, preserving contents >>>
        for (Holder.Reference<Item> dyedRef : ModRegistry.DYED_BUNDLE_ITEMS) {
            String dyedPath = dyedRef.key().location().getPath();       // e.g. "red_copper_bundle"
            String tierKey  = dyedPath.substring(dyedPath.indexOf('_') + 1); // "copper_bundle"

            // map dyed registry name back to base bundle reference
            Holder.Reference<Item> baseRef;
            switch (tierKey) {
                case "copper_bundle":    baseRef = ModRegistry.COPPER_BUNDLE_ITEM;    break;
                case "iron_bundle":      baseRef = ModRegistry.IRON_BUNDLE_ITEM;      break;
                case "golden_bundle":    baseRef = ModRegistry.GOLDEN_BUNDLE_ITEM;    break;
                case "diamond_bundle":   baseRef = ModRegistry.DIAMOND_BUNDLE_ITEM;   break;
                case "netherite_bundle": baseRef = ModRegistry.NETHERITE_BUNDLE_ITEM; break;
                default: continue;
            }

            Item baseBundle = baseRef.value();
            Item dyedBundle = dyedRef.value();

            // map DyeColor to the corresponding minecraft dye item
            DyeColor dye = DyeColor.byName(dyedPath.split("_")[0], DyeColor.WHITE);
            Item dyeItem = switch (dye) {
                case WHITE -> Items.WHITE_DYE;
                case ORANGE -> Items.ORANGE_DYE;
                case MAGENTA -> Items.MAGENTA_DYE;
                case LIGHT_BLUE -> Items.LIGHT_BLUE_DYE;
                case YELLOW -> Items.YELLOW_DYE;
                case LIME -> Items.LIME_DYE;
                case PINK -> Items.PINK_DYE;
                case GRAY -> Items.GRAY_DYE;
                case LIGHT_GRAY -> Items.LIGHT_GRAY_DYE;
                case CYAN -> Items.CYAN_DYE;
                case PURPLE -> Items.PURPLE_DYE;
                case BLUE -> Items.BLUE_DYE;
                case BROWN -> Items.BROWN_DYE;
                case GREEN -> Items.GREEN_DYE;
                case RED -> Items.RED_DYE;
                case BLACK -> Items.BLACK_DYE;
                default -> Items.WHITE_DYE;
            };

            CopyComponentsShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, dyedBundle)
                    .requires(baseBundle)
                    .requires(dyeItem)
                    .copyFrom(baseBundle)
                    .unlockedBy(getHasName(baseBundle), has(baseBundle))
                    .save(recipeOutput, MetalBundles.id(dyedPath + "_from_dye"));
        }
    }
}
