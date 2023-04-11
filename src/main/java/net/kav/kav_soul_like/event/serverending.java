package net.kav.kav_soul_like.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.util.IEntityDataSaver;
import net.kav.kav_soul_like.util.StaminaData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class serverending implements ClientPlayConnectionEvents.Disconnect{



    @Override
    public void onPlayDisconnect(ClientPlayNetworkHandler handler, MinecraftClient client) {
        PacketByteBuf buf1 = PacketByteBufs.create();
        PacketByteBuf buf2 = PacketByteBufs.create();
        buf1.writeInt(ClientStamina.tick);
        buf2.writeFloat(StaminaData.recoveryratetag(((IEntityDataSaver) MinecraftClient.getInstance().player)));

        ClientPlayNetworking.send(ModMessages.DISCONNECT2,buf1);
        ClientPlayNetworking.send(ModMessages.DISCONNECT,buf2);

    }
}