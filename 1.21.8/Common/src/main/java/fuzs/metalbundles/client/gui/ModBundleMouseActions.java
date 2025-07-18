package fuzs.metalbundles.client.gui;

import fuzs.metalbundles.init.ModRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.BundleMouseActions;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class ModBundleMouseActions extends BundleMouseActions {

    public ModBundleMouseActions(Minecraft minecraft) {
        super(minecraft);
    }

    @Override
    public boolean matches(Slot slot) {
        return slot.getItem().is(ModRegistry.BUNDLES_ITEM_TAG_KEY);
    }

    public static void onAfterInit(Minecraft minecraft, AbstractContainerScreen<?> screen, int screenWidth, int screenHeight, List<AbstractWidget> widgets, UnaryOperator<AbstractWidget> addWidget, Consumer<AbstractWidget> removeWidget) {
        // this is not really needed, as we use our custom item interaction handling for bundles in inventories
        // but this does fix a very minor issue where the last selected item remains shown on the bundle item after picking it up with the cursor
        screen.addItemSlotMouseAction(new ModBundleMouseActions(screen.minecraft));
    }
}
