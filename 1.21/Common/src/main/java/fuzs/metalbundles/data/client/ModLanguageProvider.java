package fuzs.metalbundles.data.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        builder.addCreativeModeTab(MetalBundles.MOD_ID, MetalBundles.MOD_NAME);
        builder.add(ModRegistry.LEATHER_BUNDLE_ITEM.value(), "Leather Bundle");
        builder.add(ModRegistry.COPPER_BUNDLE_ITEM.value(), "Copper Bundle");
        builder.add(ModRegistry.IRON_BUNDLE_ITEM.value(), "Iron Bundle");
        builder.add(ModRegistry.GOLDEN_BUNDLE_ITEM.value(), "Golden Bundle");
        builder.add(ModRegistry.DIAMOND_BUNDLE_ITEM.value(), "Diamond Bundle");
        builder.add(ModRegistry.NETHERITE_BUNDLE_ITEM.value(), "Netherite Bundle");
    }
}
