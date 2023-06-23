package net.kav.kav_soul_like.util;

import net.minecraft.nbt.NbtCompound;

public class playerabilities {

   public static int aglet;
    public static int necklace;
    public static int glove;
    public static void addability(IEntityDataSaver player,int index, String string)
    {
        NbtCompound abilitiesTag = player.getPersistentData();

        abilitiesTag.putInt(string,index);

    }

    public static int getability(IEntityDataSaver player, String string)
    {
        NbtCompound abilitiesTag = player.getPersistentData();
        int temp = abilitiesTag.getInt(string);
        return temp;

    }




}
