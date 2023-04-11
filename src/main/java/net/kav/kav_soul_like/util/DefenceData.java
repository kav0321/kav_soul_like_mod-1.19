package net.kav.kav_soul_like.util;

import net.minecraft.nbt.NbtCompound;

public class DefenceData {
    private static int Defence;
    public static void setDefence(IEntityDataSaver player,int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Defence=amount;
        nbt.putInt("Defence",Defence);

    }
    public static int getDefence(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        Defence=nbt.getInt("Defence");
        return Defence;
    }

    public static void increaseDefence(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Defence=nbt.getInt("Defence");
        Defence=Defence+amount;
        nbt.putInt("Defence",Defence);
    }


    public static void decreaseDefence(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Defence=nbt.getInt("Defence");
        Defence=Defence-amount;
        nbt.putInt("Defence",Defence);
    }
}
