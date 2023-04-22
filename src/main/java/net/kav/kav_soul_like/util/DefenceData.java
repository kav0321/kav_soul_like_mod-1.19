package net.kav.kav_soul_like.util;

import net.kav.kav_soul_like.config.ModConfigs;
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
        if(amount>ModConfigs.Ld_k)
        {
            return ModConfigs.Md_k;
        }
        else
        {
            return ModConfigs.Kd_k *amount;
        }

    }

    public static double DefenceLevelArmor(IEntityDataSaver player,int amount)
    {
        return ModConfigs.Kd_r*pow(amount+ModConfigs.Jd_r,ModConfigs.Md_r)+ModConfigs.Ld_r;
    }
}
