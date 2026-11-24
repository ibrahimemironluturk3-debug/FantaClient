
package dev.fantaclient.module;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

public class Fullbright extends Module {

    private double oldGamma;

    public Fullbright() {
        super("Fullbright", ModuleCategory.RENDER);
    }

    public static void registerTick() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Module module = dev.fantaclient.FantaClient.MODULE_MANAGER
                    .getModule("Fullbright");

            if (module == null || client.options == null) return;

            client.options.gamma().set(
                    module.isEnabled() ? 15.0 : 1.0
            );
        });
    }
}
