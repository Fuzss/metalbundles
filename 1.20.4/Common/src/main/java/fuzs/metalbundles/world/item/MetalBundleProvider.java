package fuzs.metalbundles.world.item;

import fuzs.iteminteractions.api.v1.provider.BundleProvider;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class MetalBundleProvider extends BundleProvider {

    public MetalBundleProvider(int capacity, @Nullable DyeColor dyeColor) {
        super(capacity, dyeColor, "Items");
    }

    @Override
    public int getItemWeight(ItemStack itemStack) {
        if (itemStack.getItem() instanceof MetalBundleItem item) {
            return item.getCapacity(itemStack) / 16 + this.getContentWeight(itemStack, null);
        } else {
            return super.getItemWeight(itemStack);
        }
    }
}
