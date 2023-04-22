package net.kav.kav_soul_like.util;

import net.minecraft.nbt.NbtCompound;

public class Negative {
    private static boolean head;
    private static boolean chest;
    private static boolean legging;
    private static boolean boots;
    private static boolean shield;

    private static int tick;
    public static void setEffect(IEntityDataSaver player,boolean amount,String name)
    {
        NbtCompound nbt = player.getPersistentData();
        switch (name)
        {
            case "head":
                head =amount;
                nbt.putBoolean("head", head);
            break;
            case "chest":
                chest =amount;
                nbt.putBoolean("chest",chest);
            break;
            case "legging":
                legging =amount;

                nbt.putBoolean("legging", legging);
            break;
            case "boots":
                boots =amount;
                nbt.putBoolean("boots", boots);
                break;
            case "shield":
                shield =amount;
                nbt.putBoolean("shield", shield);
                break;
            default:
                System.out.println("error");



        }








    }



    public static boolean getEffect(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        if(nbt.contains("head"))
        {
            head=nbt.getBoolean("head");
        }
        else
        {
            head=false;
        }
        if(nbt.contains("chest"))
        {
            chest=nbt.getBoolean("chest");
        }
        else
        {
            chest=false;
        }

        if(nbt.contains("legging"))
        {

            legging=nbt.getBoolean("legging");
            //System.out.println(legging);
        }
        else
        {
            legging=false;
        }

        if(nbt.contains("boots"))
        {
            boots=nbt.getBoolean("boots");
        }
        else
        {
            boots=false;
        }
        if(nbt.contains("shield"))
        {
            shield=nbt.getBoolean("shield");
        }
        else
        {
            shield=false;
        }

        if(head || chest || legging || boots||shield)
        {
            return true;
        }
        else
            return false;



    }





}
