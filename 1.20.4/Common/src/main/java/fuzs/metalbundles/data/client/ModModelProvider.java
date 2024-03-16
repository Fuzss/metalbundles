package fuzs.metalbundles.data.client;

import com.google.gson.JsonElement;
import fuzs.metalbundles.client.MetalBundlesClient;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.client.data.v2.ItemModelProperties;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemModels(ItemModelGenerators builder) {
        createMetalBundleItem(builder, ModRegistry.LEATHER_BUNDLE_ITEM.value(), Items.BUNDLE);
        createMetalBundleItem(builder, ModRegistry.COPPER_BUNDLE_ITEM.value());
        createMetalBundleItem(builder, ModRegistry.IRON_BUNDLE_ITEM.value());
        createMetalBundleItem(builder, ModRegistry.GOLDEN_BUNDLE_ITEM.value());
        createMetalBundleItem(builder, ModRegistry.DIAMOND_BUNDLE_ITEM.value());
        createMetalBundleItem(builder, ModRegistry.NETHERITE_BUNDLE_ITEM.value());
    }

    private static void createMetalBundleItem(ItemModelGenerators builder, Item item) {
        createMetalBundleItem(builder, item, item);
    }

    private static void createMetalBundleItem(ItemModelGenerators builder, Item item, Item modelItem) {
        ResourceLocation modelLocation = generateFlatItem(getLocation(item, "_filled"),
                getLocation(modelItem, "_filled"),
                ModelTemplates.FLAT_ITEM,
                builder.output
        );
        ItemModelProperties itemModelProperties = ItemModelProperties.singleOverride(modelLocation,
                MetalBundlesClient.ITEM_MODEL_PROPERTY_FILLED,
                1.0F
        );
        generateFlatItem(item,
                modelItem,
                ModelTemplates.FLAT_ITEM,
                builder.output,
                ItemModelProperties.overridesFactory(ModelTemplates.FLAT_ITEM, itemModelProperties)
        );
    }

    public static ResourceLocation getLocation(Block block, String modelLocationSuffix) {
        return getLocation(block).withSuffix(modelLocationSuffix);
    }

    public static ResourceLocation getLocation(Item item, String modelLocationSuffix) {
        return getLocation(item).withSuffix(modelLocationSuffix);
    }

    public static ResourceLocation generateFlatItem(ResourceLocation resourceLocation, ResourceLocation modelLocation, ModelTemplate modelTemplate, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput) {
        return modelTemplate.create(decorateItemModelLocation(resourceLocation),
                TextureMapping.layer0(decorateItemModelLocation(modelLocation)),
                modelOutput
        );
    }

    public static ResourceLocation generateFlatItem(Item item, Item modelItem, ModelTemplate modelTemplate, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput, ModelTemplate.JsonFactory factory) {
        return generateFlatItem(item, TextureMapping.getItemTexture(modelItem), modelTemplate, modelOutput, factory);
    }

    public static ResourceLocation generateFlatItem(Item item, ResourceLocation modelLocation, ModelTemplate modelTemplate, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput, ModelTemplate.JsonFactory factory) {
        return modelTemplate.create(ModelLocationUtils.getModelLocation(item),
                TextureMapping.layer0(modelLocation),
                modelOutput,
                factory
        );
    }
}
