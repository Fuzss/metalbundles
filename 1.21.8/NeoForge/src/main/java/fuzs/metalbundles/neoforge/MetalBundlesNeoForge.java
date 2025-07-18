package fuzs.metalbundles.neoforge;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.data.ModItemContentsProvider;
import fuzs.metalbundles.data.ModItemTagProvider;
import fuzs.metalbundles.data.ModRecipeProvider;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.fml.common.Mod;

@Mod(MetalBundles.MOD_ID)
public class MetalBundlesNeoForge {

    public MetalBundlesNeoForge() {
        ModConstructor.construct(MetalBundles.MOD_ID, MetalBundles::new);
        DataProviderHelper.registerDataProviders(MetalBundles.MOD_ID,
                ModItemContentsProvider::new,
                ModRecipeProvider::new,
                ModItemTagProvider::new);
    }
}
