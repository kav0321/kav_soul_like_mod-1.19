package net.kav.kav_soul_like.networking.packet;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.util.item_requirement.weapon_req;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;

public class ItemsPacket {





    public static void writeS2CItemsList(ServerPlayerEntity serverPlayerEntity) {

        for (int i = 0; i < weapon_req.weapon.size(); i++) {
            String listname =weapon_req.weapon.get(i).name_id;
            int str = weapon_req.weapon.get(i).STRENGTH;
            int H = weapon_req.weapon.get(i).HEART;
            int A = weapon_req.weapon.get(i).AGILITY;
            int S = weapon_req.weapon.get(i).STAMINA;
            int D = weapon_req.weapon.get(i).DEFENCE;


            ServerPlayNetworking.send(serverPlayerEntity,ModMessages.ITEM,new Packets.Stats_item(listname,str,H,A,S,D,i,"weapon").write());
        }

        for (int i = 0; i < weapon_req.armorList.size(); i++) {
            String listname =weapon_req.armorList.get(i).name_id;
            int str = weapon_req.armorList.get(i).STRENGTH;
            int H = weapon_req.armorList.get(i).HEART;
            int A = weapon_req.armorList.get(i).AGILITY;
            int S = weapon_req.armorList.get(i).STAMINA;
            int D = weapon_req.armorList.get(i).DEFENCE;


            ServerPlayNetworking.send(serverPlayerEntity,ModMessages.ITEM,new Packets.Stats_item(listname,str,H,A,S,D,i,"armor").write());
        }
        for (int i = 0; i < weapon_req.shield.size(); i++) {
            String listname =weapon_req.shield.get(i).name_id;
            int str = weapon_req.shield.get(i).STRENGTH;
            int H = weapon_req.shield.get(i).HEART;
            int A = weapon_req.shield.get(i).AGILITY;
            int S = weapon_req.shield.get(i).STAMINA;
            int D = weapon_req.shield.get(i).DEFENCE;


            ServerPlayNetworking.send(serverPlayerEntity,ModMessages.ITEM,new Packets.Stats_item(listname,str,H,A,S,D,i,"shield").write());
        }


    }
}
