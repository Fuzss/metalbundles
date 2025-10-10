package fuzs.metalbundles.data.client;

import java.util.Arrays;
import fuzs.metalbundles.client.MetalBundlesClient;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.client.data.v2.ItemModelProperties;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import fuzs.metalbundles.MetalBundles;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import fuzs.metalbundles.world.item.BundleType;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemModels(ItemModelGenerators builder) {
        // 1 Base (undyed) bundles
        for (BundleType tier : BundleType.values()) {
            Item item = getBaseBundle(tier);
            ResourceLocation resLoc = ModelLocationUtils.getModelLocation(item);

            boolean isLeather = tier == BundleType.LEATHER;

            ResourceLocation baseTex = isLeather
                    ? ResourceLocation.parse("minecraft:item/bundle")
                    : TextureMapping.getItemTexture(item);

            ResourceLocation filledTex = isLeather
                    ? ResourceLocation.parse("minecraft:item/bundle_filled")
                    : TextureMapping.getItemTexture(item).withSuffix("_filled");

            // Filled model
            ResourceLocation filledRes = resLoc.withSuffix("_filled");
            ModelTemplates.FLAT_ITEM.create(
                    filledRes,
                    TextureMapping.layer0(filledTex),
                    builder.output
            );

            // Unfilled model
            ItemModelProperties props = ItemModelProperties.singleOverride(
                    filledRes,
                    MetalBundlesClient.ITEM_MODEL_PROPERTY_FILLED,
                    1.0F
            );
            ModelTemplates.FLAT_ITEM.create(
                    resLoc,
                    TextureMapping.layer0(baseTex),
                    builder.output,
                    ItemModelProperties.overridesFactory(ModelTemplates.FLAT_ITEM, props)
            );
        }

        // 2 Dyed bundle variants
        for (Holder.Reference<Item> dyedRef : ModRegistry.DYED_BUNDLE_ITEMS) {
            Item dyed = dyedRef.value();
            ResourceLocation resLoc = ModelLocationUtils.getModelLocation(dyed);
            String[] parts = resLoc.getPath().split("_");

            String tier  = parts[parts.length - 2];
            String color = String.join("_", Arrays.copyOfRange(parts, 0, parts.length - 2));

            // Unfilled textures
            ResourceLocation unfilledTex0 = MetalBundles.id(color + "_bundle");
            TextureMapping unfilledMap = TextureMapping.layer0(unfilledTex0);
            if (!"leather".equals(tier)) {
                ResourceLocation unfilledTex1 = MetalBundles.id("item/" + tier + "_bundle_open_string");
                unfilledMap = unfilledMap.putForced(TextureSlot.LAYER1, unfilledTex1);
            }

            // Filled textures
            ResourceLocation filledRes  = resLoc.withSuffix("_filled");
            ResourceLocation filledTex0 = MetalBundles.id(color + "_bundle_filled");
            TextureMapping filledMap    = TextureMapping.layer0(filledTex0);
            if (!"leather".equals(tier)) {
                ResourceLocation filledTex1 = MetalBundles.id("item/" + tier + "_bundle_string");
                filledMap = filledMap.putForced(TextureSlot.LAYER1, filledTex1);
            }

            ModelTemplates.FLAT_ITEM.create(
                    filledRes,
                    filledMap,
                    builder.output
            );
            ItemModelProperties props = ItemModelProperties.singleOverride(
                    filledRes,
                    MetalBundlesClient.ITEM_MODEL_PROPERTY_FILLED,
                    1.0F
            );
            ModelTemplates.FLAT_ITEM.create(
                    resLoc,
                    unfilledMap,
                    builder.output,
                    ItemModelProperties.overridesFactory(ModelTemplates.FLAT_ITEM, props)
            );
        }
    }

    // Helper: map tiers to undyed bundle Items
    private static Item getBaseBundle(BundleType tier) {
        return switch (tier) {
            case LEATHER   -> ModRegistry.LEATHER_BUNDLE_ITEM.value();
            case COPPER    -> ModRegistry.COPPER_BUNDLE_ITEM.value();
            case IRON      -> ModRegistry.IRON_BUNDLE_ITEM.value();
            case GOLDEN    -> ModRegistry.GOLDEN_BUNDLE_ITEM.value();
            case DIAMOND   -> ModRegistry.DIAMOND_BUNDLE_ITEM.value();
            case NETHERITE -> ModRegistry.NETHERITE_BUNDLE_ITEM.value();
        };
    }
}
