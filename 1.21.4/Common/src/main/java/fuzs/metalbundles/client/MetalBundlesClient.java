package fuzs.metalbundles.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.puzzleslib.api.client.core.v1.ClientAbstractions;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;

public class MetalBundlesClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        ClientAbstractions.INSTANCE.registerConfigScreenFactory(MetalBundles.MOD_ID, new String[]{"iteminteractions"});
    }
}
