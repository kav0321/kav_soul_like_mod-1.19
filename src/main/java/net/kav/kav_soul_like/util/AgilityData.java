package net.kav.kav_soul_like.util;

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
        return 0.000798427353378238*(amount+1)+0.0992703768017564;
    }
    public static double Agilitylevel_base_attack(IEntityDataSaver player, int amount)
    {
        return 0.0104010957232309*pow(amount,1.18927368957711)+3.98835849955021;
    }
}
