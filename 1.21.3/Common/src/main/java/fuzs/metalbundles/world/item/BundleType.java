package fuzs.metalbundles.world.item;

import com.mojang.serialization.Codec;
import fuzs.iteminteractions.api.v1.DyeBackedColor;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;

import java.util.Locale;

public enum BundleType implements StringRepresentable {
    COPPER(MapColor.COLOR_ORANGE),
    IRON(MapColor.METAL),
    GOLDEN(DyeColor.YELLOW),
    DIAMOND(MapColor.DIAMOND),
    NETHERITE(MapColor.COLOR_GRAY);

    public static final Codec<BundleType> CODEC = StringRepresentable.fromValues(BundleType::values);

    public final DyeBackedColor fallbackColor;

    BundleType(DyeColor dyeColor) {
        this.fallbackColor = DyeBackedColor.fromDyeColor(dyeColor);
    }

    BundleType(MapColor mapColor) {
        this.fallbackColor = DyeBackedColor.fromMapColor(mapColor);
    }

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
