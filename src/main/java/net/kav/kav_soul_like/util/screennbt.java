package net.kav.kav_soul_like.util;

import net.minecraft.nbt.NbtCompound;

public class screennbt {
    private static boolean screen;
    private static int id;
    public static void setScreen(IEntityDataSaver player,boolean amount)
    {
        NbtCompound nbt = player.getPersistentData();
        screen=amount;
        nbt.putBoolean("screen",screen);

    }
    public static boolean getscreen(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();

        screen=nbt.getBoolean("screen");

        return screen;
    }
    public static void setid(IEntityDataSaver player,int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        id=amount;
        nbt.putInt("id",id);

    }
    public static int getid(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();

        id=nbt.getInt("id");

        return id;
    }
}
