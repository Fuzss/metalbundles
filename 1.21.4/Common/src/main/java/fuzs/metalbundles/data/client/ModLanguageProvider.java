package fuzs.metalbundles.data.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.StringJoiner;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        builder.addCreativeModeTab(MetalBundles.MOD_ID, MetalBundles.MOD_NAME);
        addMetalBundles(builder, ModRegistry.COPPER_BUNDLE_ITEMS, "Copper Bundle");
        addMetalBundles(builder, ModRegistry.IRON_BUNDLE_ITEMS, "Iron Bundle");
        addMetalBundles(builder, ModRegistry.GOLDEN_BUNDLE_ITEMS, "Golden Bundle");
        addMetalBundles(builder, ModRegistry.DIAMOND_BUNDLE_ITEMS, "Diamond Bundle");
        addMetalBundles(builder, ModRegistry.NETHERITE_BUNDLE_ITEMS, "Netherite Bundle");
    }

    static void addMetalBundles(TranslationBuilder builder, Map<DyeColor, Holder.Reference<Item>> bundleItems, String bundleName) {
        for (Map.Entry<DyeColor, Holder.Reference<Item>> entry : bundleItems.entrySet()) {
            String dyeColor = capitalizeFully(entry.getKey().getName());
            builder.addItem(entry.getValue(), dyeColor + " " + bundleName);
        }
    }

    static String capitalizeFully(String s) {
        s = s.replaceAll("\\W+", " ").replace('_', ' ');
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (String string : s.split("\\s+")) {
            if (!string.isEmpty()) {
                stringJoiner.add(Character.toUpperCase(string.charAt(0)) + string.substring(1));
            }
        }
        return stringJoiner.toString();
    }
}
