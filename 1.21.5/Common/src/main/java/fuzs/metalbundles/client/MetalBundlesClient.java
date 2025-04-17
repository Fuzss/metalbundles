package fuzs.metalbundles.client;

import fuzs.metalbundles.MetalBundles;
import fuzs.metalbundles.client.gui.ModBundleMouseActions;
import fuzs.puzzleslib.api.client.core.v1.ClientAbstractions;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.event.v1.gui.ScreenEvents;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

public class MetalBundlesClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        registerEventHandlers();
        ClientAbstractions.INSTANCE.registerConfigScreenFactory(MetalBundles.MOD_ID, "iteminteractions");
    }

    private static void registerEventHandlers() {
        ScreenEvents.afterInit(AbstractContainerScreen.class).register(ModBundleMouseActions::onAfterInit);
    }
}
