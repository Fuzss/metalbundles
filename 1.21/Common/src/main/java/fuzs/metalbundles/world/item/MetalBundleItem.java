package fuzs.metalbundles.world.item;

import fuzs.iteminteractions.api.v1.ContainerItemHelper;
import fuzs.iteminteractions.api.v1.provider.BundleProvider;
import fuzs.iteminteractions.api.v1.provider.ItemContainerBehavior;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MetalBundleItem extends Item {
    private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);

    public MetalBundleItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemInHand = player.getItemInHand(usedHand);
        SimpleContainer container = ContainerItemHelper.INSTANCE.getItemContainerBehavior(itemInHand)
                .getItemContainer(itemInHand, player);
        List<ItemStack> items = container.removeAllItems();
        items.forEach(item -> player.drop(item, true));
        if (!items.isEmpty()) {
            player.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + level.getRandom().nextFloat() * 0.4F);
            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.sidedSuccess(itemInHand, level.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemInHand);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack itemStack) {
        return this.getContentWeight(itemStack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        return Math.min(1 + 12 * this.getContentWeight(itemStack) / this.getCapacity(itemStack), 13);
    }

    @Override
    public int getBarColor(ItemStack itemStack) {
        return BAR_COLOR;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(Component.translatable("item.minecraft.bundle.fullness",
                this.getContentWeight(itemStack),
                this.getCapacity(itemStack)
        ).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        SimpleContainer container = ContainerItemHelper.INSTANCE.getItemContainerBehavior(itemEntity.getItem())
                .getItemContainer(itemEntity.getItem(), null);
        Stream<ItemStack> stream = container.items.stream().filter(Predicate.not(ItemStack::isEmpty));
        ItemUtils.onContainerDestroyed(itemEntity, stream);
    }

    public int getCapacity(ItemStack itemStack) {
        ItemContainerBehavior behavior = ContainerItemHelper.INSTANCE.getItemContainerBehavior(itemStack);
        return ((BundleProvider) behavior.provider()).getCapacity();
    }

    public int getContentWeight(ItemStack itemStack) {
        ItemContainerBehavior behavior = ContainerItemHelper.INSTANCE.getItemContainerBehavior(itemStack);
        return ((BundleProvider) behavior.provider()).getContentWeight(itemStack, null);
    }
}
