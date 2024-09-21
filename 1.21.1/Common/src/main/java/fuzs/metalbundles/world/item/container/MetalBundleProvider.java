package fuzs.metalbundles.world.item.container;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.iteminteractions.api.v1.DyeBackedColor;
import fuzs.iteminteractions.api.v1.provider.impl.BundleProvider;
import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.config.ServerConfig;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.BundleType;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.Item;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MetalBundleProvider extends BundleProvider {
    public static final MapCodec<MetalBundleProvider> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(BundleType.CODEC.fieldOf("bundle_type").forGetter(provider -> provider.bundleType),
                backgroundColorCodec(),
                disallowedItemsCodec()
        ).apply(instance, (BundleType bundleType, Optional<DyeBackedColor> dyeColor, HolderSet<Item> disallowedItems) -> {
            return new MetalBundleProvider(bundleType, dyeColor.orElse(null)).disallowedItems(disallowedItems);
        });
    });

    private final BundleType bundleType;

    public MetalBundleProvider(BundleType bundleType, @Nullable DyeBackedColor dyeColor) {
        super(dyeColor);
        this.bundleType = bundleType;
    }

    @Override
    public MetalBundleProvider disallowedItems(HolderSet<Item> disallowedItems) {
        return (MetalBundleProvider) super.disallowedItems(disallowedItems);
    }

    @Override
    public Fraction getCapacityMultiplier() {
        int capacityMultiplier = switch (this.bundleType) {
            case LEATHER -> MetalBundles.CONFIG.get(ServerConfig.class).leatherCapacityMultiplier;
            case COPPER -> MetalBundles.CONFIG.get(ServerConfig.class).copperCapacityMultiplier;
            case IRON -> MetalBundles.CONFIG.get(ServerConfig.class).ironCapacityMultiplier;
            case GOLDEN -> MetalBundles.CONFIG.get(ServerConfig.class).goldenCapacityMultiplier;
            case DIAMOND -> MetalBundles.CONFIG.get(ServerConfig.class).diamondCapacityMultiplier;
            case NETHERITE -> MetalBundles.CONFIG.get(ServerConfig.class).netheriteCapacityMultiplier;
        };
        return Fraction.getFraction(capacityMultiplier, 1);
    }

    @Override
    public Type getType() {
        return ModRegistry.METAL_BUNDLE_ITEM_CONTENTS_PROVIDER_TYPE.value();
    }
}
