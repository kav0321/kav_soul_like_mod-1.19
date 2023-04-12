package net.kav.kav_soul_like.util;

import net.minecraft.nbt.NbtCompound;

import static java.lang.Math.pow;

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
    public static double DefenceLevelKnockbackResistance(IEntityDataSaver player,int amount)
    {
        if(amount>74)
        {
            return 0.5032;
        }
        else
        {
            return 0.0068*amount;
        }

    }

    public static double DefenceLevelArmor(IEntityDataSaver player,int amount)
    {
        return -4000*pow(amount+20,-2)+10;
    }
}
