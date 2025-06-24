package fuzs.metalbundles.world.item.container;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.iteminteractions.api.v1.DyeBackedColor;
import fuzs.iteminteractions.api.v1.provider.impl.BundleProvider;
import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.config.ServerConfig;
import fuzs.metalbundles.init.ModRegistry;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MetalBundleProvider extends BundleProvider {
    public static final MapCodec<MetalBundleProvider> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(backgroundColorCodec(), itemContentsCodec())
                .apply(instance, (Optional<DyeBackedColor> dyeColor, ItemContents itemContents) -> {
                    return new MetalBundleProvider(dyeColor.orElse(null)).itemContents(itemContents);
                });
    });

    public MetalBundleProvider(@Nullable DyeBackedColor dyeColor) {
        super(dyeColor);
    }

    @Override
    protected MetalBundleProvider itemContents(ItemContents itemContents) {
        return (MetalBundleProvider) super.itemContents(itemContents);
    }

    @Override
    public Fraction getCapacityMultiplier(ItemStack containerStack) {
        int capacityMultiplier = this.getConfigurableCapacityMultiplier(containerStack);
        if (capacityMultiplier != -1) {
            return Fraction.getFraction(capacityMultiplier, 1);
        } else {
            return super.getCapacityMultiplier(containerStack);
        }
    }

    private int getConfigurableCapacityMultiplier(ItemStack containerStack) {
        if (containerStack.is(ModRegistry.COPPER_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).copperCapacityMultiplier;
        } else if (containerStack.is(ModRegistry.IRON_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).ironCapacityMultiplier;
        } else if (containerStack.is(ModRegistry.GOLDEN_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).goldenCapacityMultiplier;
        } else if (containerStack.is(ModRegistry.DIAMOND_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).diamondCapacityMultiplier;
        } else if (containerStack.is(ModRegistry.NETHERITE_BUNDLES_ITEM_TAG_KEY)) {
            return MetalBundles.CONFIG.get(ServerConfig.class).netheriteCapacityMultiplier;
        } else {
            return -1;
        }
    }

    @Override
    public Type getType() {
        return ModRegistry.METAL_BUNDLE_ITEM_CONTENTS_PROVIDER_TYPE.value();
    }
}
