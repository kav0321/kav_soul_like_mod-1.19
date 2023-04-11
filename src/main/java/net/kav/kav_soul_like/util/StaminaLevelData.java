package net.kav.kav_soul_like.util;

import net.minecraft.nbt.NbtCompound;

public class StaminaLevelData {
    private static int Stamina;

    public static void setStaminaL(IEntityDataSaver player,int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Stamina=amount;
        nbt.putInt("StaminaL",Stamina);

    }
    public static int getStaminaL(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        Stamina=nbt.getInt("StaminaL");
        return Stamina;
    }

    public static void increaseStaminaL(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Stamina=nbt.getInt("StaminaL");
        Stamina=Stamina+amount;
        nbt.putInt("StaminaL",Stamina);
    }


    public static void decreaseStaminaL(IEntityDataSaver player, int amount)
    {
        NbtCompound nbt = player.getPersistentData();
        Stamina=nbt.getInt("StaminaL");
        Stamina=Stamina-amount;
        nbt.putInt("StaminaL",Stamina);
    }
}
