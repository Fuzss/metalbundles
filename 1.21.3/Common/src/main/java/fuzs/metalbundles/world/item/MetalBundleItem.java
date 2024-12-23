package fuzs.metalbundles.world.item;

import fuzs.iteminteractions.api.v1.ItemContentsHelper;
import fuzs.iteminteractions.api.v1.provider.ItemContentsBehavior;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.container.MetalBundleProvider;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;

public class MetalBundleItem extends BundleItem {
    public static final String KEY_BUNDLE_CAPACITY = Items.BUNDLE.getDescriptionId() + ".capacity";

    public MetalBundleItem(ResourceLocation openFrontModel, ResourceLocation openBackModel, Properties properties) {
        super(openFrontModel, openBackModel, properties);
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        return Math.min(1 + Mth.mulAndTruncate(getActualWeight(itemStack), 12), 13);
    }

    public static Fraction getActualWeight(ItemStack itemStack) {
        Fraction weight = itemStack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).weight();
        ItemContentsBehavior itemContentsBehavior = ItemContentsHelper.getItemContentsBehavior(itemStack);
        if (itemContentsBehavior.provider().getType() == ModRegistry.METAL_BUNDLE_ITEM_CONTENTS_PROVIDER_TYPE.value()) {
            return weight.divideBy(((MetalBundleProvider) itemContentsBehavior.provider()).getCapacityMultiplier());
        } else {
            return weight;
        }
    }
}
