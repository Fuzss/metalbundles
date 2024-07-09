package fuzs.metalbundles;

import fuzs.iteminteractions.api.v1.ItemContainerProviderBuilder;
import fuzs.iteminteractions.api.v1.ItemContainerProviderSerializers;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.MetalBundleProvider;
import fuzs.puzzleslib.api.core.v1.ContentRegistrationFlags;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.CreativeModeTabContext;
import fuzs.puzzleslib.api.item.v2.CreativeModeTabConfigurator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetalBundles implements ModConstructor {
    public static final String MOD_ID = "metalbundles";
    public static final String MOD_NAME = "Metal Bundles";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onConstructMod() {
        ModRegistry.touch();
        registerSerializers();
    }

    private static void registerSerializers() {
        ItemContainerProviderSerializers.register(MetalBundleProvider.class, id("bundle"), jsonElement -> {
            ItemContainerProviderBuilder builder = new ItemContainerProviderBuilder(jsonElement);
            return new MetalBundleProvider(builder.capacity, builder.dyeColor);
        });
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    @Override
    public void onRegisterCreativeModeTabs(CreativeModeTabContext context) {
        context.registerCreativeModeTab(CreativeModeTabConfigurator.from(MOD_ID)
                .icon(() -> new ItemStack(ModRegistry.IRON_BUNDLE_ITEM.value()))
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(ModRegistry.LEATHER_BUNDLE_ITEM.value());
                    output.accept(ModRegistry.COPPER_BUNDLE_ITEM.value());
                    output.accept(ModRegistry.IRON_BUNDLE_ITEM.value());
                    output.accept(ModRegistry.GOLDEN_BUNDLE_ITEM.value());
                    output.accept(ModRegistry.DIAMOND_BUNDLE_ITEM.value());
                    output.accept(ModRegistry.NETHERITE_BUNDLE_ITEM.value());
                }));
    }

    @Override
    public ContentRegistrationFlags[] getContentRegistrationFlags() {
        return new ContentRegistrationFlags[]{ContentRegistrationFlags.COPY_TAG_RECIPES};
    }
}
