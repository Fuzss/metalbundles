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
        HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
        this.add(itemLookup, ModRegistry.COPPER_BUNDLE_ITEM, ModRegistry.COPPER_BUNDLE_ITEMS);
        this.add(itemLookup, ModRegistry.IRON_BUNDLE_ITEM, ModRegistry.IRON_BUNDLE_ITEMS);
        this.add(itemLookup, ModRegistry.GOLDEN_BUNDLE_ITEM, ModRegistry.GOLDEN_BUNDLE_ITEMS);
        this.add(itemLookup, ModRegistry.DIAMOND_BUNDLE_ITEM, ModRegistry.DIAMOND_BUNDLE_ITEMS);
        this.add(itemLookup, ModRegistry.NETHERITE_BUNDLE_ITEM, ModRegistry.NETHERITE_BUNDLE_ITEMS);
    }

    public void add(HolderLookup.RegistryLookup<Item> itemLookup, Holder.Reference<Item> bundleItem, Map<DyeColor, Holder.Reference<Item>> bundleItems) {
        this.add(itemLookup, DyeColor.BROWN, bundleItem);
        for (Map.Entry<DyeColor, Holder.Reference<Item>> entry : bundleItems.entrySet()) {
            this.add(itemLookup, entry.getKey(), entry.getValue());
        }
    }

    public void add(HolderLookup.RegistryLookup<Item> items, DyeColor dyeColor, Holder.Reference<Item> itemLookup) {
        this.add(items, new MetalBundleProvider(DyeBackedColor.fromDyeColor(dyeColor)), itemLookup.value());
    }
}
