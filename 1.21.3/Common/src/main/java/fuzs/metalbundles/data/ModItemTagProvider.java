package fuzs.metalbundles.data;

import fuzs.metalbundles.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagAppender;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import java.util.Map;

public class ModItemTagProvider extends AbstractTagProvider<Item> {

    public ModItemTagProvider(DataProviderContext context) {
        super(Registries.ITEM, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.add(ItemTags.BUNDLES)
                .addTag(ModRegistry.COPPER_BUNDLES_ITEM_TAG_KEY)
                .addTag(ModRegistry.IRON_BUNDLES_ITEM_TAG_KEY)
                .addTag(ModRegistry.GOLDEN_BUNDLES_ITEM_TAG_KEY)
                .addTag(ModRegistry.DIAMOND_BUNDLES_ITEM_TAG_KEY)
                .addTag(ModRegistry.NETHERITE_BUNDLES_ITEM_TAG_KEY);
        this.addMetalBundleTag(ModRegistry.COPPER_BUNDLES_ITEM_TAG_KEY, ModRegistry.COPPER_BUNDLE_ITEMS);
        this.addMetalBundleTag(ModRegistry.IRON_BUNDLES_ITEM_TAG_KEY, ModRegistry.IRON_BUNDLE_ITEMS);
        this.addMetalBundleTag(ModRegistry.GOLDEN_BUNDLES_ITEM_TAG_KEY, ModRegistry.GOLDEN_BUNDLE_ITEMS);
        this.addMetalBundleTag(ModRegistry.DIAMOND_BUNDLES_ITEM_TAG_KEY, ModRegistry.DIAMOND_BUNDLE_ITEMS);
        this.addMetalBundleTag(ModRegistry.NETHERITE_BUNDLES_ITEM_TAG_KEY, ModRegistry.NETHERITE_BUNDLE_ITEMS);
    }

    private void addMetalBundleTag(TagKey<Item> tagKey, Map<DyeColor, Holder.Reference<Item>> bundleItems) {
        AbstractTagAppender<Item> tagAppender = this.add(tagKey);
        for (Holder.Reference<Item> holder : bundleItems.values()) {
            tagAppender.add(holder);
        }
    }
}
