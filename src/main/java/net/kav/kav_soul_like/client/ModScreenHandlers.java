package net.kav.kav_soul_like.client;

import net.kav.kav_soul_like.client.gui.GemInfusingScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static ScreenHandlerType<GemInfusingScreenHandler> GEM_INFUSING_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        GEM_INFUSING_SCREEN_HANDLER = new ScreenHandlerType<>(GemInfusingScreenHandler::new);
    }
}