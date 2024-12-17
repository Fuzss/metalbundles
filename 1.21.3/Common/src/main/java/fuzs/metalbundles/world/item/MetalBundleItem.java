package fuzs.metalbundles.world.item;

import fuzs.iteminteractions.api.v1.ItemContentsHelper;
import fuzs.iteminteractions.api.v1.provider.ItemContentsBehavior;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.container.MetalBundleProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;

import java.text.DecimalFormat;
import java.util.List;

public class MetalBundleItem extends BundleItem {
    public static final String KEY_BUNDLE_CAPACITY = Items.BUNDLE.getDescriptionId() + ".capacity";

    public MetalBundleItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        return Math.min(1 + Mth.mulAndTruncate(getActualWeight(itemStack), 12), 13);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        Fraction weight = getActualWeight(itemStack);
        if (!Fraction.ZERO.equals(weight)) {
            String s = new DecimalFormat("0").format(weight.floatValue() * 100.0F);
            tooltipComponents.add(Component.translatable(KEY_BUNDLE_CAPACITY, s).withStyle(ChatFormatting.GRAY));
        }
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
