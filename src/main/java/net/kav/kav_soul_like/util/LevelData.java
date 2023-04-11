package net.kav.kav_soul_like.util;

import net.minecraft.nbt.NbtCompound;

public class LevelData {
    private static int Level;
    public static void setLevel(IEntityDataSaver player,int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Level=amount;
        nbt.putInt("Level",Level);

    }
    public static int getLevel(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        Level=nbt.getInt("Level");
        if(Level<=0)
        {
            Level=1;
        }
        return Level;
    }

    public static void increaseLevel(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Level=nbt.getInt("Level");
        Level=Level+amount;
        nbt.putInt("Level",Level);
    }

    public static void decreaseLevel(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Level=nbt.getInt("Level");
        Level=Level-amount;
        nbt.putInt("Level",Level);
    }
}
