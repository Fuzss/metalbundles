package fuzs.metalbundles.fabric;

import fuzs.metalbundles.MetalBundles;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class MetalBundlesFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(MetalBundles.MOD_ID, MetalBundles::new);
    }
}
