package fuzs.metalbundles.data;

import fuzs.iteminteractions.api.v1.data.AbstractItemContentsProvider;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.BundleType;
import fuzs.metalbundles.world.item.container.MetalBundleProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.DataProvider;
import net.minecraft.world.item.Item;
import fuzs.iteminteractions.api.v1.DyeBackedColor;
import net.minecraft.core.Holder;
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;

public class ModItemContentsProvider extends AbstractItemContentsProvider {

    public ModItemContentsProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemProviders() {
        // Base (undyed)
        this.add(BundleType.LEATHER, ModRegistry.LEATHER_BUNDLE_ITEM.value());
        this.add(BundleType.COPPER, ModRegistry.COPPER_BUNDLE_ITEM.value());
        this.add(BundleType.IRON, ModRegistry.IRON_BUNDLE_ITEM.value());
        this.add(BundleType.GOLDEN, ModRegistry.GOLDEN_BUNDLE_ITEM.value());
        this.add(BundleType.DIAMOND, ModRegistry.DIAMOND_BUNDLE_ITEM.value());
        this.add(BundleType.NETHERITE, ModRegistry.NETHERITE_BUNDLE_ITEM.value());

        // Dyed variants
        for (Holder.Reference<Item> ref : ModRegistry.DYED_BUNDLE_ITEMS) {
            String path = ref.key().location().getPath();
            String[] parts = path.split("_");

            if (parts.length < 3 || !"bundle".equals(parts[parts.length - 1])) {
                continue;
            }
            String typePart = parts[parts.length - 2];
            String colorPart = String.join("_", Arrays.copyOfRange(parts, 0, parts.length - 2));

            try {
                DyeColor dye = DyeColor.byName(colorPart, DyeColor.WHITE);
                DyeBackedColor dyeColor = DyeBackedColor.fromDyeColor(dye);

                BundleType bundleType = Arrays.stream(BundleType.values())
                        .filter(bt -> bt.getSerializedName().equals(typePart))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Unknown bundle type: " + typePart));

                this.add(new MetalBundleProvider(bundleType, dyeColor), ref.value());
            } catch (IllegalArgumentException e) {
                DataProvider.LOGGER.warn("Skipping dyed bundle item due to parsing error: {}", path, e);
            }
        }
    }

    public void add(BundleType bundleType, Item item) {
        this.add(new MetalBundleProvider(bundleType, bundleType.fallbackColor), item);
    }
}
