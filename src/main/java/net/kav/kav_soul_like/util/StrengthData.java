package net.kav.kav_soul_like.util;

import net.minecraft.nbt.NbtCompound;

import static java.lang.Math.pow;

public class StrengthData {


    private static int Strength;

    public static void setstrength(IEntityDataSaver player,int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Strength=amount;
        nbt.putInt("Strength",Strength);

    }
    public static int getstrength(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        Strength=nbt.getInt("Strength");
        return Strength;
    }

    public static void increasestrength(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Strength=nbt.getInt("Strength");
        Strength=Strength+amount;
        nbt.putInt("Strength",Strength);
    }


    public static void decreasestrength(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Strength=nbt.getInt("Strength");
        Strength=Strength-amount;
        nbt.putInt("Strength",Strength);
    }
    public static double strengthleveltobase(IEntityDataSaver player, int amount)
    {
        if(amount==0)
        {
            return 1;
        }
        return 2.25007537562693*pow(amount,0.401337607480491);
    }
}
