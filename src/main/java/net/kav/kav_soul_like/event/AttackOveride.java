package net.kav.kav_soul_like.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.util.*;
import net.kav.kav_soul_like.util.item_requirement.weapon_req;
import net.kav.kav_soul_like.util.search.Search;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.registry.Registry;

public class AttackOveride {
    private static boolean con;

    public static void print()
    {

        if(!(MinecraftClient.getInstance().player.isCreative()||MinecraftClient.getInstance().player.isSpectator()))
        {
            con=true;
            //CLIENT TO SERVER

            ClientPlayNetworking.send(ModMessages.HANDSWING, PacketByteBufs.empty());

            String name= Registry.ITEM.getId(MinecraftClient.getInstance().player.getMainHandStack().getItem()).toString();
            //System.out.println(!is_item_req(MinecraftClient.getInstance().player,name));
           if(is_item_req(MinecraftClient.getInstance().player,name)==false)
           {

               PacketByteBuf buf =PacketByteBufs.create();
               buf.writeBoolean(true);
               ClientPlayNetworking.send(ModMessages.LOWSTA,buf);
           }
        }

    }

    public static boolean is_item_req(PlayerEntity player, String name)
    {
        boolean x=false;
        int index;

index= Search.search(weapon_req.weapon,name);

        if( index!=-1)
        {
            x=true;
        }
        if(x==true)
        {



            if(StrengthData.getstrength(((IEntityDataSaver) player))>=weapon_req.weapon.get(index).STRENGTH && HeartData.getHeart(((IEntityDataSaver) player))>=weapon_req.weapon.get(index).HEART && AgilityData.getAgility(((IEntityDataSaver) player))>=weapon_req.weapon.get(index).AGILITY && StaminaLevelData.getStaminaL(((IEntityDataSaver) player))>=weapon_req.weapon.get(index).STAMINA && DefenceData.getDefence(((IEntityDataSaver) player))>=weapon_req.weapon.get(index).DEFENCE)
            {
            //System.out.println("sasd");
                return true;
            }
        }
        if(x==false)
        {
            return true;
        }

        return false;
    }

    public static void setCon(boolean cons)
    {
        con=cons;
    }

    public static boolean getCon()
    {
        return con;
    }


}
