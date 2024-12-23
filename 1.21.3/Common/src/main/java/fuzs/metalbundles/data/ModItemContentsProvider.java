package fuzs.metalbundles.data;

import fuzs.iteminteractions.api.v1.data.AbstractItemContentsProvider;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.BundleType;
import fuzs.metalbundles.world.item.container.MetalBundleProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public class ModItemContentsProvider extends AbstractItemContentsProvider {

    public ModItemContentsProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemProviders(HolderLookup.Provider registries) {
        HolderLookup.RegistryLookup<Item> items = registries.lookupOrThrow(Registries.ITEM);
        this.add(items, BundleType.COPPER, ModRegistry.COPPER_BUNDLE_ITEM.value());
        this.add(items, BundleType.IRON, ModRegistry.IRON_BUNDLE_ITEM.value());
        this.add(items, BundleType.GOLDEN, ModRegistry.GOLDEN_BUNDLE_ITEM.value());
        this.add(items, BundleType.DIAMOND, ModRegistry.DIAMOND_BUNDLE_ITEM.value());
        this.add(items, BundleType.NETHERITE, ModRegistry.NETHERITE_BUNDLE_ITEM.value());
    }

    public void add(HolderLookup.RegistryLookup<Item> items, BundleType bundleType, Item item) {
        this.add(items, new MetalBundleProvider(bundleType, bundleType.fallbackColor), item);
    }
}
