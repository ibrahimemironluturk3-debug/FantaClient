package dev.fantaclient;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import dev.fantaclient.gui.ClickGuiScreen;
import dev.fantaclient.module.ModuleManager;

public class FantaClient implements ClientModInitializer {

    public static final ModuleManager MODULE_MANAGER = new ModuleManager();

    private static KeyMapping openGuiKey;

    @Override
    public void onInitializeClient() {
        KeyMapping.Category category = KeyMapping.Category.register(
                Identifier.fromNamespaceAndPath("fantaclient", "main")
        );

        openGuiKey = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.fantaclient.open_gui",
                        InputConstants.Type.KEYSYM,
                        InputConstants.KEY_RIGHT_SHIFT,
                        category
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.consumeClick()) {
                client.setScreen(new ClickGuiScreen(MODULE_MANAGER));
            }
        });

        System.out.println("[FantaClient] Baslatildi!");
    }
}
