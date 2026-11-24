
package dev.fantaclient;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import org.lwjgl.glfw.GLFW;

import dev.fantaclient.gui.ClickGuiScreen;
import dev.fantaclient.module.ModuleManager;
import dev.fantaclient.module.Sprint;
import dev.fantaclient.module.Fullbright;
import dev.fantaclient.module.HUD;
import dev.fantaclient.module.CrystalAura;
import dev.fantaclient.module.ElytraTarget;

public class FantaClient implements ClientModInitializer {

    public static final ModuleManager MODULE_MANAGER = new ModuleManager();

    private static KeyMapping openGuiKey;

    @Override
    public void onInitializeClient() {

        MODULE_MANAGER.register(new Sprint());
        MODULE_MANAGER.register(new Fullbright());
        MODULE_MANAGER.register(new HUD());
        MODULE_MANAGER.register(new CrystalAura());
        MODULE_MANAGER.register(new ElytraTarget());

        Sprint.registerTick();
        Fullbright.registerTick();
        HUD.registerRender();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ElytraTarget target = (ElytraTarget)
                    MODULE_MANAGER.getModule("ElytraTarget");

            if (target != null) {
                target.update();
            }

            while (openGuiKey.consumeClick()) {
                client.setScreen(new ClickGuiScreen(MODULE_MANAGER));
            }
        });

        KeyMapping.Category category = KeyMapping.Category.register(
                Identifier.fromNamespaceAndPath("fantaclient", "main")
        );

        openGuiKey = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.fantaclient.open_gui",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_RIGHT_SHIFT,
                        category
                )
        );

        System.out.println("[FantaClient] Baslatildi!");
    }
}
