package fuzs.metalbundles.data;

import fuzs.iteminteractions.api.v1.DyeBackedColor;
import fuzs.iteminteractions.api.v1.data.AbstractItemContentsProvider;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.container.MetalBundleProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import java.util.Map;

public class ModItemContentsProvider extends AbstractItemContentsProvider {

    public ModItemContentsProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemProviders(HolderLookup.Provider registries) {
        HolderLookup.RegistryLookup<Item> items = registries.lookupOrThrow(Registries.ITEM);
        this.add(items, ModRegistry.COPPER_BUNDLE_ITEMS);
        this.add(items, ModRegistry.IRON_BUNDLE_ITEMS);
        this.add(items, ModRegistry.GOLDEN_BUNDLE_ITEMS);
        this.add(items, ModRegistry.DIAMOND_BUNDLE_ITEMS);
        this.add(items, ModRegistry.NETHERITE_BUNDLE_ITEMS);
    }

    public void add(HolderLookup.RegistryLookup<Item> items, Map<DyeColor, Holder.Reference<Item>> bundleItems) {
        for (Map.Entry<DyeColor, Holder.Reference<Item>> entry : bundleItems.entrySet()) {
            this.add(items,
                    new MetalBundleProvider(DyeBackedColor.fromDyeColor(entry.getKey())),
                    entry.getValue().value());
        }
    }
}
