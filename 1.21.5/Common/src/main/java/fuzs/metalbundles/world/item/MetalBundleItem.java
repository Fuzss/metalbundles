package fuzs.metalbundles.world.item;

import fuzs.iteminteractions.api.v1.ItemContentsHelper;
import fuzs.iteminteractions.api.v1.provider.ItemContentsBehavior;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.container.MetalBundleProvider;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;

public class MetalBundleItem extends BundleItem {
    private static final int FULL_BAR_COLOR = ARGB.colorFromFloat(1.0F, 1.0F, 0.33F, 0.33F);
    private static final int BAR_COLOR = ARGB.colorFromFloat(1.0F, 0.44F, 0.53F, 1.0F);

    public MetalBundleItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        return Math.min(1 + Mth.mulAndTruncate(getActualWeight(itemStack), 12), 13);
    }

    @Override
    public int getBarColor(ItemStack itemStack) {
        return getActualWeight(itemStack).compareTo(Fraction.ONE) >= 0 ? FULL_BAR_COLOR : BAR_COLOR;
    }

    static Fraction getActualWeight(ItemStack itemStack) {
        Fraction weight = itemStack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).weight();
        ItemContentsBehavior itemContentsBehavior = ItemContentsHelper.getItemContentsBehavior(itemStack);
        if (itemContentsBehavior.provider().getType() == ModRegistry.METAL_BUNDLE_ITEM_CONTENTS_PROVIDER_TYPE.value()) {
            return weight.divideBy(((MetalBundleProvider) itemContentsBehavior.provider()).getCapacityMultiplier(
                    itemStack));
        } else {
            return weight;
        }
    }
}
