
package dev.fantaclient.module;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", ModuleCategory.MOVEMENT);
    }

    public static void registerTick() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Module sprint = dev.fantaclient.FantaClient.MODULE_MANAGER.getModule("Sprint");

            if (sprint != null && sprint.isEnabled()
                    && client.player != null) {
                client.player.setSprinting(true);
            }
        });
    }
}
