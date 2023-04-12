package net.kav.kav_soul_like.util;

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
        return -922.835251414047*pow(amount+1,-0.00595021874968786)+939.828724828382;
    }
}
