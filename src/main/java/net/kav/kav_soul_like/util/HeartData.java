package net.kav.kav_soul_like.util;

import net.kav.kav_soul_like.config.ModConfigs;
import net.minecraft.nbt.NbtCompound;

import static java.lang.Math.pow;

public class HeartData {
    private static int Heart;
    public static void setHeart(IEntityDataSaver player,int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Heart=amount;
        nbt.putInt("Heart",Heart);

    }
    public static int getHeart(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        Heart=nbt.getInt("Heart");
        return Heart;
    }

    public static void increaseHeart(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Heart=nbt.getInt("Heart");
        Heart=Heart+amount;
        nbt.putInt("Heart",Heart);
    }


    public static void decreaseHeart(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Heart=nbt.getInt("Heart");
        Heart=Heart-amount;
        nbt.putInt("Heart",Heart);
    }

    public static double heartlevelbase(IEntityDataSaver player, int amount)
    {
        return ModConfigs.Kh*pow(amount+1,ModConfigs.Mh)+ModConfigs.Lh;
    }
}
