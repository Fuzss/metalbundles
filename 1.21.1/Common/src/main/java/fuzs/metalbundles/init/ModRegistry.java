package fuzs.metalbundles.init;

import fuzs.iteminteractions.api.v1.provider.ItemContentsProvider;
import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.world.item.MetalBundleItem;
import fuzs.metalbundles.world.item.container.MetalBundleProvider;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.BundleContents;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(MetalBundles.MOD_ID);
    public static final Holder.Reference<Item> LEATHER_BUNDLE_ITEM = REGISTRIES.registerItem("leather_bundle",
            () -> new MetalBundleItem(new Item.Properties().stacksTo(1)
                    .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY))
    );
    public static final Holder.Reference<Item> COPPER_BUNDLE_ITEM = REGISTRIES.registerItem("copper_bundle",
            () -> new MetalBundleItem(new Item.Properties().stacksTo(1)
                    .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY))
    );
    public static final Holder.Reference<Item> IRON_BUNDLE_ITEM = REGISTRIES.registerItem("iron_bundle",
            () -> new MetalBundleItem(new Item.Properties().stacksTo(1)
                    .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY))
    );
    public static final Holder.Reference<Item> GOLDEN_BUNDLE_ITEM = REGISTRIES.registerItem("golden_bundle",
            () -> new MetalBundleItem(new Item.Properties().stacksTo(1)
                    .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY))
    );
    public static final Holder.Reference<Item> DIAMOND_BUNDLE_ITEM = REGISTRIES.registerItem("diamond_bundle",
            () -> new MetalBundleItem(new Item.Properties().stacksTo(1)
                    .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY))
    );
    public static final Holder.Reference<Item> NETHERITE_BUNDLE_ITEM = REGISTRIES.registerItem("netherite_bundle",
            () -> new MetalBundleItem(new Item.Properties().stacksTo(1)
                    .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY))
    );
    public static final Holder.Reference<ItemContentsProvider.Type> METAL_BUNDLE_ITEM_CONTENTS_PROVIDER_TYPE = REGISTRIES.register(
            ItemContentsProvider.REGISTRY_KEY,
            "metal_bundle",
            () -> new ItemContentsProvider.Type(MetalBundleProvider.CODEC)
    );

    public static void touch() {
        // NO-OP
    }
}
