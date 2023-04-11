package net.kav.kav_soul_like.util;

import net.minecraft.nbt.NbtCompound;

public class AgilityData {
    private static int Agility;
    public static void setAgility(IEntityDataSaver player,int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Agility=amount;
        nbt.putInt("Agility",Agility);

    }
    public static int getAgility(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        Agility=nbt.getInt("Agility");
        return Agility;
    }

    public static void increaseAgility(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Agility=nbt.getInt("Agility");
        Agility=Agility+amount;
        nbt.putInt("Agility",Agility);
    }


    public static void decreaseAgility(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Agility=nbt.getInt("Agility");
        Agility=Agility-amount;
        nbt.putInt("Agility",Agility);
    }
}
