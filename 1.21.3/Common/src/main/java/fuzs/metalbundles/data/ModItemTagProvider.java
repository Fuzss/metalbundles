package fuzs.metalbundles.data;

import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;

public class ModItemTagProvider extends AbstractTagProvider<Item> {

    public ModItemTagProvider(DataProviderContext context) {
        super(Registries.ITEM, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.add(ItemTags.BUNDLES)
                .add(ModRegistry.COPPER_BUNDLE_ITEM)
                .add(ModRegistry.IRON_BUNDLE_ITEM)
                .add(ModRegistry.GOLDEN_BUNDLE_ITEM)
                .add(ModRegistry.DIAMOND_BUNDLE_ITEM)
                .add(ModRegistry.NETHERITE_BUNDLE_ITEM);
    }
}
