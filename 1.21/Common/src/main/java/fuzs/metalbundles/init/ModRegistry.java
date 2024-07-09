package fuzs.metalbundles.init;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.world.item.MetalBundleItem;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;

public class ModRegistry {
    static final RegistryManager REGISTRY = RegistryManager.from(MetalBundles.MOD_ID);
    public static final Holder.Reference<Item> LEATHER_BUNDLE_ITEM = REGISTRY.registerItem("leather_bundle", () -> new MetalBundleItem(
            new Item.Properties().stacksTo(1)));
    public static final Holder.Reference<Item> COPPER_BUNDLE_ITEM = REGISTRY.registerItem("copper_bundle", () -> new MetalBundleItem(
            new Item.Properties().stacksTo(1)));
    public static final Holder.Reference<Item> IRON_BUNDLE_ITEM = REGISTRY.registerItem("iron_bundle", () -> new MetalBundleItem(
            new Item.Properties().stacksTo(1)));
    public static final Holder.Reference<Item> GOLDEN_BUNDLE_ITEM = REGISTRY.registerItem("golden_bundle", () -> new MetalBundleItem(
            new Item.Properties().stacksTo(1)));
    public static final Holder.Reference<Item> DIAMOND_BUNDLE_ITEM = REGISTRY.registerItem("diamond_bundle", () -> new MetalBundleItem(
            new Item.Properties().stacksTo(1)));
    public static final Holder.Reference<Item> NETHERITE_BUNDLE_ITEM = REGISTRY.registerItem("netherite_bundle", () -> new MetalBundleItem(
            new Item.Properties().stacksTo(1)));

    public static void touch() {

    }
}
