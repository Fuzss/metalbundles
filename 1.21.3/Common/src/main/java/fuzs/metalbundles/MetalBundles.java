package fuzs.metalbundles;

import fuzs.metalbundles.config.ServerConfig;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.CreativeModeTabContext;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.item.v2.CreativeModeTabConfigurator;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MetalBundles implements ModConstructor {
    public static final String MOD_ID = "metalbundles";
    public static final String MOD_NAME = "Metal Bundles";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).server(ServerConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
    }

    @Override
    public void onRegisterCreativeModeTabs(CreativeModeTabContext context) {
        context.registerCreativeModeTab(CreativeModeTabConfigurator.from(MOD_ID).icons(() -> {
            return ModRegistry.IRON_BUNDLE_ITEMS.values()
                    .stream()
                    .map(Holder.Reference::value)
                    .map(ItemStack::new)
                    .toArray(ItemStack[]::new);
        }).displayItems((itemDisplayParameters, output) -> {
            acceptMetalBundles(output, ModRegistry.COPPER_BUNDLE_ITEMS);
            acceptMetalBundles(output, ModRegistry.IRON_BUNDLE_ITEMS);
            acceptMetalBundles(output, ModRegistry.GOLDEN_BUNDLE_ITEMS);
            acceptMetalBundles(output, ModRegistry.DIAMOND_BUNDLE_ITEMS);
            acceptMetalBundles(output, ModRegistry.NETHERITE_BUNDLE_ITEMS);
        }));
    }

    static void acceptMetalBundles(CreativeModeTab.Output output, Map<DyeColor, Holder.Reference<Item>> bundleItems) {
        for (Holder.Reference<Item> holder : bundleItems.values()) {
            output.accept(holder.value());
        }
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}
