package net.kav.kav_soul_like.util;

import net.minecraft.nbt.NbtCompound;

public class StaminaData {



    private static float STAMINA=0;
    private static boolean JOIN=false;
    private static float MAXSTAMINA=20;
    private static float RECOVERYRATE=0.7f;

    public static float  Stamina(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getFloat("Stamina");
    }

    public static boolean  JOIN(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        JOIN=nbt.getBoolean("join");
        return JOIN;
    }
    public static boolean  SETJOIN(IEntityDataSaver player, boolean setjoin)
    {
        NbtCompound nbt = player.getPersistentData();
        JOIN=setjoin;
        nbt.putBoolean("join",JOIN);

        return JOIN;
    }
    public static void floatsetmax(IEntityDataSaver player, float amount)
    {
        NbtCompound nbt = player.getPersistentData();
        MAXSTAMINA=amount;

        nbt.putFloat("MaxStamina", MAXSTAMINA);
    }
    public static float addPoints(IEntityDataSaver player, float amount,String string)
    {
        NbtCompound nbt = player.getPersistentData();
        MAXSTAMINA=nbt.getFloat("MaxStamina");
        RECOVERYRATE=nbt.getFloat("recoveryrate");


        switch (string) {
            case "Stamina":
                if(amount!=0)
                {

                }
                if(MAXSTAMINA==0)
                {
                    MAXSTAMINA=20;
                    (player).getPersistentData().putFloat("MaxStamina",20);
                }
                if (STAMINA >= MAXSTAMINA) {

                    STAMINA = MAXSTAMINA;

                }
                else {


                    STAMINA = STAMINA + amount;

                }
                nbt.putFloat("Stamina", STAMINA);

                return STAMINA;
            case "MaxStamina":
                if(MAXSTAMINA>99)
                {
                    MAXSTAMINA=99;
                }
                else if(MAXSTAMINA==0)
                {
                    MAXSTAMINA=20;
                }
                else
                {
                    if(MAXSTAMINA%5==0 && amount!=0)
                    {

                        RECOVERYRATE=MAXSTAMINA/100+0.1f;
                    }

                    MAXSTAMINA = MAXSTAMINA + amount;}
                nbt.putFloat("MaxStamina", MAXSTAMINA);
                nbt.putFloat("recoveryrate",RECOVERYRATE);

                return MAXSTAMINA;
            //do nothing for now
            default:
                return 2;
        }
    }

    public static float recoveryratetag(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        MAXSTAMINA=nbt.getFloat("MaxStamina");
        RECOVERYRATE=nbt.getFloat("recoveryrate");

        if(RECOVERYRATE==0)
        {
            RECOVERYRATE=0.7f;
            nbt.putFloat("recoveryrate",RECOVERYRATE);
        }
        else
        {
            RECOVERYRATE=MAXSTAMINA/100+0.1f;
        }


        return RECOVERYRATE;
    }
    public static float recoveryratetagput(IEntityDataSaver player, float amount)
    {
        NbtCompound nbt = player.getPersistentData();
        RECOVERYRATE=nbt.getFloat("recoveryrate");
        RECOVERYRATE=amount;
        nbt.putFloat("recoveryrate",RECOVERYRATE);
        return RECOVERYRATE;
    }

    public static float removePoints(IEntityDataSaver player, float amount,String string)
    {
        NbtCompound nbt = player.getPersistentData();
        //STAMINA=nbt.getFloat("Stamina");

        MAXSTAMINA=nbt.getFloat("MaxStamina");

        switch (string) {
            case "Stamina":

                if (STAMINA-amount<=0) {
                    STAMINA=0;

                    //ClientPlayNetworking.send(ModMessages.COMMAND2,PacketByteBufs.empty());
                }
                else
                {STAMINA = STAMINA - amount;

                }

                //  nbt.putFloat("Stamina", Stamina);
                //  synSoul(Stamina, (ServerPlayerEntity) player);
                return STAMINA;
            // synSoul(soul, (ServerPlayerEntity) player);

            //do nothing for now
            default:
                return -1000;
        }
    }


}
