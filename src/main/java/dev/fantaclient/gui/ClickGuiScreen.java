
package dev.fantaclient.gui;

import dev.fantaclient.module.Module;
import dev.fantaclient.module.ModuleManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public class ClickGuiScreen extends Screen {

    private final ModuleManager manager;

    public ClickGuiScreen(ModuleManager manager) {
        super(Component.literal("FantaClient"));
        this.manager = manager;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        // Bulanıklık yerine yarı saydam arka plan
        graphics.fill(0, 0, this.width, this.height, 0xCC101018);

        graphics.drawCenteredString(
                this.font,
                "FantaClient",
                this.width / 2,
                20,
                0xFFFF55FF
        );

        int y = 50;

        for (Module module : manager.getModules()) {
            int color = module.isEnabled() ? 0xFF55FF55 : 0xFFFFFFFF;

            graphics.drawString(
                    this.font,
                    module.getName() + " [" + (module.isEnabled() ? "ON" : "OFF") + "]",
                    this.width / 2 - 70,
                    y,
                    color
            );

            y += 20;
        }

        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        int y = 50;

        for (Module module : manager.getModules()) {
            if (event.x() >= this.width / 2 - 70
                    && event.x() <= this.width / 2 + 100
                    && event.y() >= y - 3
                    && event.y() <= y + 15) {

                module.toggle();
                return true;
            }

            y += 20;
        }

        return super.mouseClicked(event, doubleClick);
    }
}
