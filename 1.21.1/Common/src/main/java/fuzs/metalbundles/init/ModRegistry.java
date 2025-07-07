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
import net.minecraft.world.item.DyeColor;
import java.util.ArrayList;
import java.util.List;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(MetalBundles.MOD_ID);

    // Base (undyed) metal bundles
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

    public static final List<Holder.Reference<Item>> DYED_BUNDLE_ITEMS = new ArrayList<>();

    // Dynamic registration of dyed metal bundles
    static {
        for (DyeColor dye : DyeColor.values()) {
            String color = dye.getName();

            // Leather tier
            Holder.Reference<Item> leatherRef = REGISTRIES.registerItem(color + "_leather_bundle",
                    () -> new MetalBundleItem(new Item.Properties().stacksTo(1)
                            .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY))
            );
            DYED_BUNDLE_ITEMS.add(leatherRef);

            // Copper tier
            Holder.Reference<Item> copperRef = REGISTRIES.registerItem(color + "_copper_bundle",
                    () -> new MetalBundleItem(new Item.Properties().stacksTo(1)
                            .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY))
            );
            DYED_BUNDLE_ITEMS.add(copperRef);

            // Iron tier
            Holder.Reference<Item> ironRef = REGISTRIES.registerItem(color + "_iron_bundle",
                    () -> new MetalBundleItem(new Item.Properties().stacksTo(1)
                            .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY))
            );
            DYED_BUNDLE_ITEMS.add(ironRef);

            // Golden tier
            Holder.Reference<Item> goldenRef = REGISTRIES.registerItem(color + "_golden_bundle",
                    () -> new MetalBundleItem(new Item.Properties().stacksTo(1)
                            .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY))
            );
            DYED_BUNDLE_ITEMS.add(goldenRef);

            // Diamond tier
            Holder.Reference<Item> diamondRef = REGISTRIES.registerItem(color + "_diamond_bundle",
                    () -> new MetalBundleItem(new Item.Properties().stacksTo(1)
                            .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY))
            );
            DYED_BUNDLE_ITEMS.add(diamondRef);

            // Netherite tier
            Holder.Reference<Item> netheriteRef = REGISTRIES.registerItem(color + "_netherite_bundle",
                    () -> new MetalBundleItem(new Item.Properties().stacksTo(1)
                            .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY))
            );
            DYED_BUNDLE_ITEMS.add(netheriteRef);
        }
    }

    public static final Holder.Reference<ItemContentsProvider.Type> METAL_BUNDLE_ITEM_CONTENTS_PROVIDER_TYPE = REGISTRIES.register(
            ItemContentsProvider.REGISTRY_KEY,
            "metal_bundle",
            () -> new ItemContentsProvider.Type(MetalBundleProvider.CODEC)
    );

    public static void touch() {
        // NO-OP
    }
}
