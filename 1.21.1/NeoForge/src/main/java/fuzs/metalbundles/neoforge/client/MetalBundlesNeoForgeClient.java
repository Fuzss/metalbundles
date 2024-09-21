package fuzs.metalbundles.neoforge.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.client.MetalBundlesClient;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = MetalBundles.MOD_ID, dist = Dist.CLIENT)
public class MetalBundlesNeoForgeClient {

    public MetalBundlesNeoForgeClient() {
        ClientModConstructor.construct(MetalBundles.MOD_ID, MetalBundlesClient::new);
    }
}
