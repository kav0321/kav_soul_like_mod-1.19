package net.kav.kav_soul_like.util;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kav.kav_soul_like.client.gui.LevelUpGui;
import net.kav.kav_soul_like.client.gui.LevelUpScreen;
import net.kav.kav_soul_like.networking.ModMessages;
import net.minecraft.client.MinecraftClient;

public class DisplayLevel {


    public static  void Gui()
    {
        MinecraftClient.getInstance().setScreen(new LevelUpScreen(new LevelUpGui()));
    }


}
