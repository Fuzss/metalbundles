package fuzs.metalbundles.data.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import java.util.Map;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemModels(ItemModelGenerators builder) {
        this.createMetalBundleItem(builder,
                ModRegistry.COPPER_BUNDLE_ITEMS,
                "copper_bundle_string",
                "copper_bundle_open_string");
        this.createMetalBundleItem(builder,
                ModRegistry.IRON_BUNDLE_ITEMS,
                "iron_bundle_string",
                "iron_bundle_open_string");
        this.createMetalBundleItem(builder,
                ModRegistry.GOLDEN_BUNDLE_ITEMS,
                "golden_bundle_string",
                "golden_bundle_open_string");
        this.createMetalBundleItem(builder,
                ModRegistry.DIAMOND_BUNDLE_ITEMS,
                "diamond_bundle_string",
                "diamond_bundle_open_string");
        this.createMetalBundleItem(builder,
                ModRegistry.NETHERITE_BUNDLE_ITEMS,
                "netherite_bundle_string",
                "netherite_bundle_open_string");
    }

    private void createMetalBundleItem(ItemModelGenerators builder, Map<DyeColor, Holder.Reference<Item>> bundleItems, String bundleString, String bundleOpenString) {
        for (Map.Entry<DyeColor, Holder.Reference<Item>> entry : bundleItems.entrySet()) {
            this.createMetalBundleItem(builder,
                    (BundleItem) entry.getValue().value(),
                    entry.getKey(),
                    MetalBundles.id(bundleString),
                    MetalBundles.id(bundleOpenString));
        }
    }

    private void createMetalBundleItem(ItemModelGenerators builder, BundleItem item, DyeColor dyeColor, ResourceLocation bundleStringLocation, ResourceLocation bundleOpenStringLocation) {
        BundleItem bundleItem = (BundleItem) BundleItem.getByColor(dyeColor);
        ModelTemplateHelper.generateLayeredItem(item,
                ModelLocationHelper.getItemLocation(bundleItem),
                bundleStringLocation,
                builder.output);
        generateFlatItem(item.openBackModel(), bundleItem.openBackModel(), ModelTemplates.FLAT_ITEM, builder.output);
        ModelTemplateHelper.generateLayeredItem(item.openFrontModel(),
                bundleItem.openFrontModel(),
                bundleOpenStringLocation,
                builder.output);
    }
}
