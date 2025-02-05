package fuzs.metalbundles.init;

import com.google.common.collect.Maps;
import fuzs.iteminteractions.api.v1.provider.ItemContentsProvider;
import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.world.item.MetalBundleItem;
import fuzs.metalbundles.world.item.container.MetalBundleProvider;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.BundleContents;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(MetalBundles.MOD_ID);
    public static final Map<DyeColor, Holder.Reference<Item>> COPPER_BUNDLE_ITEMS = registerMetalBundleItems(
            "copper_bundle");
    public static final Map<DyeColor, Holder.Reference<Item>> IRON_BUNDLE_ITEMS = registerMetalBundleItems("iron_bundle");
    public static final Map<DyeColor, Holder.Reference<Item>> GOLDEN_BUNDLE_ITEMS = registerMetalBundleItems(
            "golden_bundle");
    public static final Map<DyeColor, Holder.Reference<Item>> DIAMOND_BUNDLE_ITEMS = registerMetalBundleItems(
            "diamond_bundle");
    public static final Map<DyeColor, Holder.Reference<Item>> NETHERITE_BUNDLE_ITEMS = registerMetalBundleItems(
            "netherite_bundle",
            () -> new Item.Properties().fireResistant());
    public static final Holder.Reference<ItemContentsProvider.Type> METAL_BUNDLE_ITEM_CONTENTS_PROVIDER_TYPE = REGISTRIES.register(
            ItemContentsProvider.REGISTRY_KEY,
            "metal_bundle",
            () -> new ItemContentsProvider.Type(MetalBundleProvider.CODEC));
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(
            GOLDEN_BUNDLE_ITEMS.get(DyeColor.RED));

    static final TagFactory TAGS = TagFactory.make(MetalBundles.MOD_ID);
    public static final TagKey<Item> COPPER_BUNDLES_ITEM_TAG_KEY = TAGS.registerItemTag("copper_bundles");
    public static final TagKey<Item> IRON_BUNDLES_ITEM_TAG_KEY = TAGS.registerItemTag("iron_bundles");
    public static final TagKey<Item> GOLDEN_BUNDLES_ITEM_TAG_KEY = TAGS.registerItemTag("golden_bundles");
    public static final TagKey<Item> DIAMOND_BUNDLES_ITEM_TAG_KEY = TAGS.registerItemTag("diamond_bundles");
    public static final TagKey<Item> NETHERITE_BUNDLES_ITEM_TAG_KEY = TAGS.registerItemTag("netherite_bundles");

    public static void bootstrap() {
        // NO-OP
    }

    private static Map<DyeColor, Holder.Reference<Item>> registerMetalBundleItems(String path) {
        return registerMetalBundleItems(path, Item.Properties::new);
    }

    private static Map<DyeColor, Holder.Reference<Item>> registerMetalBundleItems(String path, Supplier<Item.Properties> itemPropertiesSupplier) {
        EnumMap<DyeColor, Holder.Reference<Item>> bundleItems = new EnumMap<>(DyeColor.class);
        for (DyeColor dyeColor : DyeColor.values()) {
            String s = dyeColor.getName() + "_" + path;
            bundleItems.put(dyeColor,
                    REGISTRIES.registerItem(s,
                            MetalBundleItem::new,
                            () -> itemPropertiesSupplier.get()
                                    .stacksTo(1)
                                    .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY)));
        }
        return Maps.immutableEnumMap(bundleItems);
    }
}
