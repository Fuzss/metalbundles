package fuzs.metalbundles.data.client;

import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Function;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemModels(ItemModelGenerators builder) {
        this.createMetalBundleItem(builder, ModRegistry.COPPER_BUNDLE_ITEM.value(), Items.ORANGE_BUNDLE);
        this.createMetalBundleItem(builder, ModRegistry.IRON_BUNDLE_ITEM.value(), Items.LIGHT_GRAY_BUNDLE);
        this.createMetalBundleItem(builder, ModRegistry.GOLDEN_BUNDLE_ITEM.value(), Items.YELLOW_BUNDLE);
        this.createMetalBundleItem(builder, ModRegistry.DIAMOND_BUNDLE_ITEM.value(), Items.CYAN_BUNDLE);
        this.createMetalBundleItem(builder, ModRegistry.NETHERITE_BUNDLE_ITEM.value(), Items.BLACK_BUNDLE);
    }

    private void createMetalBundleItem(ItemModelGenerators builder, Item item) {
        builder.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
        generateFlatItem(((BundleItem) item).openBackModel(), ModelTemplates.FLAT_ITEM, builder.output);
        generateFlatItem(((BundleItem) item).openFrontModel(), ModelTemplates.FLAT_ITEM, builder.output);
    }

    private void createMetalBundleItem(ItemModelGenerators builder, Item item, Item itemTemplate) {
        builder.generateFlatItem(item, itemTemplate, ModelTemplates.FLAT_ITEM);
        this.generateBundleItem(item, itemTemplate, BundleItem::openBackModel, builder);
        this.generateBundleItem(item, itemTemplate, BundleItem::openFrontModel, builder);
    }

    private void generateBundleItem(Item item, Item itemTemplate, Function<BundleItem, ResourceLocation> modelGetter, ItemModelGenerators builder) {
        generateFlatItem(modelGetter.apply((BundleItem) item),
                modelGetter.apply((BundleItem) itemTemplate),
                ModelTemplates.FLAT_ITEM,
                builder.output);
    }
}
