package fuzs.metalbundles.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.ItemModelPropertiesContext;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;

public class MetalBundlesClient implements ClientModConstructor {
    public static final ResourceLocation ITEM_MODEL_PROPERTY_FILLED = MetalBundles.id("filled");

    @Override
    public void onRegisterItemModelProperties(ItemModelPropertiesContext context) {
        context.registerItemProperty(ITEM_MODEL_PROPERTY_FILLED,
                (ItemStack itemStack, ClientLevel clientLevel, LivingEntity livingEntity, int i) -> {
                    return Mth.ceil(BundleItem.getFullnessDisplay(itemStack));
                },
                ModRegistry.LEATHER_BUNDLE_ITEM.value(),
                ModRegistry.COPPER_BUNDLE_ITEM.value(),
                ModRegistry.IRON_BUNDLE_ITEM.value(),
                ModRegistry.GOLDEN_BUNDLE_ITEM.value(),
                ModRegistry.DIAMOND_BUNDLE_ITEM.value(),
                ModRegistry.NETHERITE_BUNDLE_ITEM.value()
        );
    }
}
