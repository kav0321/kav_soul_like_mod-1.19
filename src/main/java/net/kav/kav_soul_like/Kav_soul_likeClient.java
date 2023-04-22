package net.kav.kav_soul_like;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.kav.kav_soul_like.block.ModBlocks;
import net.kav.kav_soul_like.block.custom.GoldenCup;
import net.kav.kav_soul_like.client.gui.LevelUpGui;
import net.kav.kav_soul_like.client.gui.LevelUpScreen;
import net.kav.kav_soul_like.client.gui.StaminaOverlay;
import net.kav.kav_soul_like.event.ClientStamina;
import net.kav.kav_soul_like.event.KeyInputHandler;
import net.kav.kav_soul_like.event.serverending;
import net.kav.kav_soul_like.event.serverjoin;
import net.kav.kav_soul_like.networking.ModMessages;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class Kav_soul_likeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ModMessages.registerS2CPackets();
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GOLDEN_CUP, RenderLayer.getCutout());

        KeyInputHandler.register();
        ClientPlayConnectionEvents.JOIN.register(new serverjoin());
        ClientPlayConnectionEvents.DISCONNECT.register(new serverending());
        ClientTickEvents.END_WORLD_TICK.register(new ClientStamina());
       // HudRenderCallback.EVENT.register(new StaminaOverlay());
    }


}
