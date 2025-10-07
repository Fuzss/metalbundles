package fuzs.metalbundles.neoforge.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.client.MetalBundlesClient;
import fuzs.metalbundles.data.client.ModLanguageProvider;
import fuzs.metalbundles.data.client.ModModelProvider;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = MetalBundles.MOD_ID, dist = Dist.CLIENT)
public class MetalBundlesNeoForgeClient {

    public MetalBundlesNeoForgeClient() {
        ClientModConstructor.construct(MetalBundles.MOD_ID, MetalBundlesClient::new);
        DataProviderHelper.registerDataProviders(MetalBundles.MOD_ID, ModLanguageProvider::new, ModModelProvider::new);
    }
}
