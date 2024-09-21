package fuzs.metalbundles.data;

import fuzs.iteminteractions.api.v1.data.AbstractItemContentsProvider;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.BundleType;
import fuzs.metalbundles.world.item.container.MetalBundleProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.world.item.Item;

public class ModItemContentsProvider extends AbstractItemContentsProvider {

    public ModItemContentsProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemProviders() {
        this.add(BundleType.LEATHER, ModRegistry.LEATHER_BUNDLE_ITEM.value());
        this.add(BundleType.COPPER, ModRegistry.COPPER_BUNDLE_ITEM.value());
        this.add(BundleType.IRON, ModRegistry.IRON_BUNDLE_ITEM.value());
        this.add(BundleType.GOLDEN, ModRegistry.GOLDEN_BUNDLE_ITEM.value());
        this.add(BundleType.DIAMOND, ModRegistry.DIAMOND_BUNDLE_ITEM.value());
        this.add(BundleType.NETHERITE, ModRegistry.NETHERITE_BUNDLE_ITEM.value());
    }

    public void add(BundleType bundleType, Item item) {
        this.add(new MetalBundleProvider(bundleType, bundleType.fallbackColor), item);
    }
}
