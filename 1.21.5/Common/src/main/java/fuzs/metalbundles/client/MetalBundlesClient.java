package fuzs.metalbundles.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.client.gui.ModBundleMouseActions;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.event.v1.gui.ScreenEvents;
import fuzs.puzzleslib.api.config.v3.ConfigHolder;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

public class MetalBundlesClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        ConfigHolder.registerConfigurationScreen(MetalBundles.MOD_ID, "iteminteractions");
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        ScreenEvents.afterInit(AbstractContainerScreen.class).register(ModBundleMouseActions::onAfterInit);
    }
}
