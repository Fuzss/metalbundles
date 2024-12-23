package fuzs.metalbundles.data.client;

import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemModels(ItemModelGenerators builder) {
        this.createMetalBundleItem(builder, ModRegistry.COPPER_BUNDLE_ITEM.value());
        this.createMetalBundleItem(builder, ModRegistry.IRON_BUNDLE_ITEM.value());
        this.createMetalBundleItem(builder, ModRegistry.GOLDEN_BUNDLE_ITEM.value());
        this.createMetalBundleItem(builder, ModRegistry.DIAMOND_BUNDLE_ITEM.value());
        this.createMetalBundleItem(builder, ModRegistry.NETHERITE_BUNDLE_ITEM.value());
    }

    private void createMetalBundleItem(ItemModelGenerators builder, Item item) {
        builder.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
        generateFlatItem(((BundleItem) item).openBackModel(), ModelTemplates.FLAT_ITEM, builder.output);
        generateFlatItem(((BundleItem) item).openFrontModel(), ModelTemplates.FLAT_ITEM, builder.output);
    }
}
