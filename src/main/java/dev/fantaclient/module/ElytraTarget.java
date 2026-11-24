
package dev.fantaclient.module;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import java.util.Comparator;
import java.util.List;

public class ElytraTarget extends Module {

    private double range = 50.0;
    private Player target;

    public ElytraTarget() {
        super("ElytraTarget", ModuleCategory.COMBAT);
    }

    public void update() {
        Minecraft client = Minecraft.getInstance();

        if (client.player == null || client.level == null || !isEnabled()) {
            target = null;
            return;
        }

        List<Player> players = client.level.players();

        target = players.stream()
                .filter(player -> player != client.player)
                .filter(Player::isAlive)
                .filter(player -> client.player.distanceTo(player) <= range)
                .min(Comparator.comparingDouble(
                        player -> client.player.distanceTo(player)
                ))
                .orElse(null);
    }

    public Player getTarget() {
        return target;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = Math.max(5.0, Math.min(100.0, range));
    }

    public double getTargetDistance() {
        Minecraft client = Minecraft.getInstance();

        if (target == null || client.player == null) {
            return -1;
        }

        return client.player.distanceTo(target);
    }

    @Override
    public void onEnable() {
        target = null;
    }

    @Override
    public void onDisable() {
        target = null;
    }
}
