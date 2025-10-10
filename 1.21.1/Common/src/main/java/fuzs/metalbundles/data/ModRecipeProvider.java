package fuzs.metalbundles.data;

import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.recipes.CopyComponentsShapedRecipeBuilder;
import fuzs.puzzleslib.api.data.v2.recipes.CopyComponentsShapelessRecipeBuilder;
import fuzs.metalbundles.world.item.BundleType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Ingredient;
import fuzs.metalbundles.MetalBundles;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;


public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    // Returns the base bundle Item for the given tier
    private Item getBaseBundle(BundleType tier) {
        return switch (tier) {
            case LEATHER   -> ModRegistry.LEATHER_BUNDLE_ITEM.value();
            case COPPER    -> ModRegistry.COPPER_BUNDLE_ITEM.value();
            case IRON      -> ModRegistry.IRON_BUNDLE_ITEM.value();
            case GOLDEN    -> ModRegistry.GOLDEN_BUNDLE_ITEM.value();
            case DIAMOND   -> ModRegistry.DIAMOND_BUNDLE_ITEM.value();
            case NETHERITE -> ModRegistry.NETHERITE_BUNDLE_ITEM.value();
        };
    }

    // Returns the required ingot for upgrading from this tier
    private Ingredient getUpgradeMaterial(BundleType fromTier) {
        return switch (fromTier) {
            case LEATHER   -> Ingredient.of(Items.COPPER_INGOT);
            case COPPER    -> Ingredient.of(Items.IRON_INGOT);
            case IRON      -> Ingredient.of(Items.GOLD_INGOT);
            case GOLDEN    -> Ingredient.of(Items.DIAMOND);
            case DIAMOND   -> Ingredient.of(Items.NETHERITE_INGOT);
            default        -> Ingredient.of(Items.AIR);
        };
    }

    @SuppressWarnings("t")
    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        // 1 Base leather bundle
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, getBaseBundle(BundleType.LEATHER))
                .define('#', Items.LEATHER)
                .define('-', Items.STRING)
                .pattern("-#-")
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(recipeOutput);

        // 2 Upgrade recipes, base and dye variants
        for (BundleType fromTier : BundleType.values()) {
            BundleType toTier = switch (fromTier) {
                case LEATHER   -> BundleType.COPPER;
                case COPPER    -> BundleType.IRON;
                case IRON      -> BundleType.GOLDEN;
                case GOLDEN    -> BundleType.DIAMOND;
                case DIAMOND   -> BundleType.NETHERITE;
                default        -> null;
            };
            if (toTier == null) continue;

            Ingredient material = getUpgradeMaterial(fromTier);
            Item baseTo = getBaseBundle(toTier);
            Item baseFrom = getBaseBundle(fromTier);

            // Base upgrades
            if (fromTier != BundleType.DIAMOND) {
                CopyComponentsShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, baseTo)
                        .define('X', baseFrom)
                        .define('#', material)
                        .copyFrom(baseFrom)
                        .pattern(" # ")
                        .pattern("#X#")
                        .pattern(" # ")
                        .unlockedBy("has_" + fromTier.getSerializedName(), has(baseFrom))
                        .save(recipeOutput, MetalBundles.id(
                                fromTier.getSerializedName() + "_to_" + toTier.getSerializedName() + "_bundle"
                        ));
            } else {
                // Shapeless netherite upgrade
                CopyComponentsShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, baseTo)
                        .requires(baseFrom)
                        .requires(Items.NETHERITE_INGOT)
                        .copyFrom(baseFrom)
                        .unlockedBy("has_" + fromTier.getSerializedName(), has(baseFrom))
                        .save(recipeOutput, MetalBundles.id(
                                fromTier.getSerializedName() + "_to_" + toTier.getSerializedName() + "_bundle"
                        ));
            }

            // Dyed upgrades
            for (Holder.Reference<Item> dyedRef2 : ModRegistry.DYED_BUNDLE_ITEMS) {
                String path = dyedRef2.key().location().getPath();
                if (!path.endsWith("_" + fromTier.getSerializedName() + "_bundle")) continue;
                Item inDyed = dyedRef2.value();
                String suffix = "_" + fromTier.getSerializedName() + "_bundle";
                String color = path.substring(0, path.length() - suffix.length());
                String outId = color + "_" + toTier.getSerializedName() + "_bundle";

                var outRefOpt = ModRegistry.DYED_BUNDLE_ITEMS.stream()
                        .filter(r -> r.key().location().getPath().equals(outId))
                        .findFirst();
                if (outRefOpt.isEmpty()) continue;
                Item outDyed = outRefOpt.get().value();

                if (fromTier != BundleType.DIAMOND) {
                    CopyComponentsShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, outDyed)
                            .define('X', inDyed)
                            .define('#', material)
                            .copyFrom(inDyed)
                            .pattern(" # ")
                            .pattern("#X#")
                            .pattern(" # ")
                            .unlockedBy("has_" + path, has(inDyed))
                            .save(recipeOutput, MetalBundles.id(path + "_to_" + outId));

                } else {
                    CopyComponentsShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, outDyed)
                            .requires(inDyed)
                            .requires(Items.NETHERITE_INGOT)
                            .copyFrom(inDyed)
                            .unlockedBy("has_" + path, has(inDyed))
                            .save(recipeOutput, MetalBundles.id(path + "_to_" + outId));
                }
            }
        }

        // 3 Dyeing recipes for all tiers
        for (BundleType tier : BundleType.values()) {
            TagKey<Item> tierTag = TagKey.create(Registries.ITEM, MetalBundles.id(tier.getSerializedName() + "_bundles"));
            Ingredient tierBundles = Ingredient.of(tierTag);

            for (DyeColor dye : DyeColor.values()) {
                String color = dye.getName(); // “lime”, “black”, etc.

                String dyeId = color + "_" + tier.getSerializedName() + "_bundle";
                Holder.Reference<Item> dyedRef = ModRegistry.DYED_BUNDLE_ITEMS.stream()
                        .filter(r -> r.key().location().getPath().equals(dyeId))
                        .findFirst().orElseThrow();
                Item dyedBundle = dyedRef.value();

                Item dyeItem = switch (dye) {
                    case WHITE      -> Items.WHITE_DYE;
                    case ORANGE     -> Items.ORANGE_DYE;
                    case MAGENTA    -> Items.MAGENTA_DYE;
                    case LIGHT_BLUE -> Items.LIGHT_BLUE_DYE;
                    case YELLOW     -> Items.YELLOW_DYE;
                    case LIME       -> Items.LIME_DYE;
                    case PINK       -> Items.PINK_DYE;
                    case GRAY       -> Items.GRAY_DYE;
                    case LIGHT_GRAY -> Items.LIGHT_GRAY_DYE;
                    case CYAN       -> Items.CYAN_DYE;
                    case PURPLE     -> Items.PURPLE_DYE;
                    case BLUE       -> Items.BLUE_DYE;
                    case BROWN      -> Items.BROWN_DYE;
                    case GREEN      -> Items.GREEN_DYE;
                    case RED        -> Items.RED_DYE;
                    case BLACK      -> Items.BLACK_DYE;
                    default         -> Items.WHITE_DYE;
                };

                CopyComponentsShapelessRecipeBuilder
                        .shapeless(RecipeCategory.TOOLS, dyedBundle)
                        .requires(tierBundles)
                        .requires(dyeItem)
                        .copyFrom(tierBundles)
                        .unlockedBy("has_" + tier.getSerializedName(), has(getBaseBundle(tier)))
                        .save(recipeOutput,
                                MetalBundles.id( dyeId + "_from_dye")
                        );
            }
        }
    }
}
