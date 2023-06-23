package net.kav.kav_soul_like.util;

import net.kav.kav_soul_like.client.gui.Interaction_screen;
import net.kav.kav_soul_like.client.gui.LevelUpGui;
import net.kav.kav_soul_like.client.gui.LevelUpScreen;
import net.kav.kav_soul_like.client.gui.melina_screen;
import net.minecraft.client.MinecraftClient;

public class DisplayText {

    public static  void Gui(int context, int lore)
    {
        MinecraftClient.getInstance().setScreen(new Interaction_screen(new melina_screen(context,lore)));
    }
}
