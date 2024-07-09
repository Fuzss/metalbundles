package fuzs.metalbundles.data;

import fuzs.iteminteractions.api.v1.data.AbstractItemContainerProvider;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.MetalBundleProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.world.item.DyeColor;

public class ModItemContainerProvider extends AbstractItemContainerProvider {

    public ModItemContainerProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemProviders() {
        this.add(ModRegistry.LEATHER_BUNDLE_ITEM.value(), new MetalBundleProvider(64, DyeColor.BROWN));
        this.add(ModRegistry.COPPER_BUNDLE_ITEM.value(), new MetalBundleProvider(128, DyeColor.ORANGE));
        this.add(ModRegistry.IRON_BUNDLE_ITEM.value(), new MetalBundleProvider(512, DyeColor.LIGHT_GRAY));
        this.add(ModRegistry.GOLDEN_BUNDLE_ITEM.value(), new MetalBundleProvider(1024, DyeColor.YELLOW));
        this.add(ModRegistry.DIAMOND_BUNDLE_ITEM.value(), new MetalBundleProvider(2048, DyeColor.CYAN));
        this.add(ModRegistry.NETHERITE_BUNDLE_ITEM.value(), new MetalBundleProvider(4096, DyeColor.GRAY));
    }
}
