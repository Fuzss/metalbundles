package fuzs.metalbundles.data.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.init.ModRegistry;
import fuzs.metalbundles.world.item.MetalBundleItem;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

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

        for (Holder.Reference<Item> ref : ModRegistry.DYED_BUNDLE_ITEMS) {
            Item item = ref.value();
            String path = ref.key().location().getPath(); // e.g. "light_blue_diamond_bundle"
            String[] parts = path.split("_");
            if (parts.length < 3) {
                continue;
            }
            // Build human-readable name from parts
            String displayName = Arrays.stream(parts)
                    .map(part -> {
                        String lower = part.toLowerCase(Locale.ROOT);
                        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
                    })
                    .collect(Collectors.joining(" "));
            builder.add(item, displayName);
        }

        builder.add(MetalBundleItem.KEY_BUNDLE_CAPACITY, "Capacity: %s%%");
    }
}
