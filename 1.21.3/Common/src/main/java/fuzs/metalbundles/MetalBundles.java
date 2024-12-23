package fuzs.metalbundles;

import fuzs.metalbundles.config.ServerConfig;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.CreativeModeTabContext;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.item.v2.CreativeModeTabConfigurator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        context.registerCreativeModeTab(CreativeModeTabConfigurator.from(MOD_ID)
                .icon(() -> new ItemStack(ModRegistry.IRON_BUNDLE_ITEM.value()))
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(ModRegistry.COPPER_BUNDLE_ITEM.value());
                    output.accept(ModRegistry.IRON_BUNDLE_ITEM.value());
                    output.accept(ModRegistry.GOLDEN_BUNDLE_ITEM.value());
                    output.accept(ModRegistry.DIAMOND_BUNDLE_ITEM.value());
                    output.accept(ModRegistry.NETHERITE_BUNDLE_ITEM.value());
                }));
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}
