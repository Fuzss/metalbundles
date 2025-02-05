package fuzs.metalbundles.data.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.client.data.v2.models.ModelLocationHelper;
import fuzs.puzzleslib.api.client.data.v2.models.ModelTemplateHelper;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.conditional.BundleHasSelectedItem;
import net.minecraft.client.renderer.item.properties.select.DisplayContext;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;

import java.util.Map;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemModels(ItemModelGenerators itemModelGenerators) {
        this.createMetalBundleItem(itemModelGenerators,
                ModRegistry.COPPER_BUNDLE_ITEMS,
                "copper_bundle_string",
                "copper_bundle_open_string");
        this.createMetalBundleItem(itemModelGenerators,
                ModRegistry.IRON_BUNDLE_ITEMS,
                "iron_bundle_string",
                "iron_bundle_open_string");
        this.createMetalBundleItem(itemModelGenerators,
                ModRegistry.GOLDEN_BUNDLE_ITEMS,
                "golden_bundle_string",
                "golden_bundle_open_string");
        this.createMetalBundleItem(itemModelGenerators,
                ModRegistry.DIAMOND_BUNDLE_ITEMS,
                "diamond_bundle_string",
                "diamond_bundle_open_string");
        this.createMetalBundleItem(itemModelGenerators,
                ModRegistry.NETHERITE_BUNDLE_ITEMS,
                "netherite_bundle_string",
                "netherite_bundle_open_string");
    }

    private void createMetalBundleItem(ItemModelGenerators builder, Map<DyeColor, Holder.Reference<Item>> bundleItems, String bundleString, String bundleOpenString) {
        for (Map.Entry<DyeColor, Holder.Reference<Item>> entry : bundleItems.entrySet()) {
            this.createMetalBundleItem(builder,
                    entry.getValue().value(),
                    entry.getKey(),
                    MetalBundles.id(bundleString),
                    MetalBundles.id(bundleOpenString));
        }
    }

    /**
     * Partially copied from {@link ItemModelGenerators#generateBundleModels(Item)}.
     */
    private void createMetalBundleItem(ItemModelGenerators itemModelGenerators, Item item, DyeColor dyeColor, ResourceLocation stringResourceLocation, ResourceLocation openStringResourceLocation) {
        Item vanillaBundleItem = BundleItem.getByColor(dyeColor);
        ResourceLocation resourceLocation = ModelTemplateHelper.generateLayeredItem(item,
                ModelLocationHelper.getItemLocation(vanillaBundleItem),
                stringResourceLocation,
                itemModelGenerators.modelOutput);
        ResourceLocation openBackResourceLocation = ModelTemplateHelper.generateFlatItem(ModelLocationHelper.getItemLocation(
                        item).withSuffix("_open_back"),
                ModelLocationHelper.getItemLocation(vanillaBundleItem).withSuffix("_open_back"),
                ModelTemplates.FLAT_ITEM,
                itemModelGenerators.modelOutput);
        ResourceLocation openFrontResourceLocation = ModelTemplateHelper.generateLayeredItem(ModelLocationHelper.getItemLocation(
                        item).withSuffix("_open_front"),
                ModelLocationHelper.getItemLocation(vanillaBundleItem).withSuffix("_open_front"),
                openStringResourceLocation,
                itemModelGenerators.modelOutput);
        ItemModel.Unbaked unbaked = ItemModelUtils.plainModel(resourceLocation);
        ItemModel.Unbaked unbaked2 = ItemModelUtils.composite(ItemModelUtils.plainModel(openBackResourceLocation),
                new net.minecraft.client.renderer.item.BundleSelectedItemSpecialRenderer.Unbaked(),
                ItemModelUtils.plainModel(openFrontResourceLocation));
        ItemModel.Unbaked unbaked3 = ItemModelUtils.conditional(new BundleHasSelectedItem(), unbaked2, unbaked);
        itemModelGenerators.itemModelOutput.accept(item,
                ItemModelUtils.select(new DisplayContext(),
                        unbaked,
                        ItemModelUtils.when(ItemDisplayContext.GUI, unbaked3)));
    }
}
