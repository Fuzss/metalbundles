package fuzs.metalbundles.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.ItemModelPropertiesContext;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Holder;

import java.util.ArrayList;
import java.util.List;

public class MetalBundlesClient implements ClientModConstructor {
    public static final ResourceLocation ITEM_MODEL_PROPERTY_FILLED = MetalBundles.id("filled");

    @Override
    public void onRegisterItemModelProperties(ItemModelPropertiesContext context) {
        // Start with the six base bundles
        List<Item> allBundles = new ArrayList<>(6 + ModRegistry.DYED_BUNDLE_ITEMS.size());
        allBundles.add(ModRegistry.LEATHER_BUNDLE_ITEM.value());
        allBundles.add(ModRegistry.COPPER_BUNDLE_ITEM.value());
        allBundles.add(ModRegistry.IRON_BUNDLE_ITEM.value());
        allBundles.add(ModRegistry.GOLDEN_BUNDLE_ITEM.value());
        allBundles.add(ModRegistry.DIAMOND_BUNDLE_ITEM.value());
        allBundles.add(ModRegistry.NETHERITE_BUNDLE_ITEM.value());

        // Append every dyed bundle
        for (Holder.Reference<Item> ref : ModRegistry.DYED_BUNDLE_ITEMS) {
            allBundles.add(ref.value());
        }

        // Register the same filled‐predicate property on all of them
        context.registerItemProperty(
                ITEM_MODEL_PROPERTY_FILLED,
                (ItemStack stack, ClientLevel world, LivingEntity entity, int seed) ->
                        Mth.ceil(BundleItem.getFullnessDisplay(stack)),
                allBundles.toArray(new Item[0])
        );
    }
}
