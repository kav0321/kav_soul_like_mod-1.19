package net.kav.kav_soul_like.util;

import net.kav.kav_soul_like.config.ModConfigs;
import net.minecraft.nbt.NbtCompound;

import static java.lang.Math.pow;

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

    public static double Agilitylevel_base_move(IEntityDataSaver player, int amount)
    {
        return ModConfigs.Ka_m *(amount+1)+ModConfigs.Ma_m;
    }
    public static double Agilitylevel_base_attack(IEntityDataSaver player, int amount)
    {
        return ModConfigs.Ka_s*pow(amount,ModConfigs.Ma_s)+ModConfigs.La_s;
    }
}
