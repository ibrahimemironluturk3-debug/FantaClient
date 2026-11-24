
package dev.fantaclient.module;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.network.chat.Component;

public class HUD extends Module {

    public HUD() {
        super("HUD", ModuleCategory.RENDER);
    }

    public static void registerRender() {
        HudRenderCallback.EVENT.register((graphics, tickCounter) -> {
            Module hud = dev.fantaclient.FantaClient.MODULE_MANAGER
                    .getModule("HUD");

            if (hud == null || !hud.isEnabled()) return;

            var client = net.minecraft.client.Minecraft.getInstance();

            if (client.player != null) {
                graphics.drawString(
                        client.font,
                        Component.literal("FantaClient | "
                                + client.player.getName().getString()),
                        5,
                        5,
                        0xFFFF55FF
                );
            }
        });
    }
}
