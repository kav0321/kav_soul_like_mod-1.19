package net.kav.kav_soul_like.util;

import net.kav.kav_soul_like.command.Levelsetcommand;

public class ModRegistries {
    public static void INIT()
    {
        command_init();
    }

    private static void command_init()
    {
        Levelsetcommand.init();
    }
}
