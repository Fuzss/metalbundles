package fuzs.metalbundles.fabric.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.client.MetalBundlesClient;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class MetalBundlesFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(MetalBundles.MOD_ID, MetalBundlesClient::new);
    }
}
