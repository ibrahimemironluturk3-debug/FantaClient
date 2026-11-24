package dev.fantaclient;

import net.fabricmc.api.ClientModInitializer;

public class FantaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        System.out.println("[FantaClient] Basariyla baslatildi!");
    }
}
