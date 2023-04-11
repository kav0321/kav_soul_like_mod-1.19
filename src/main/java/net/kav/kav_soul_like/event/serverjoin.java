package net.kav.kav_soul_like.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kav.kav_soul_like.networking.ModMessages;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class serverjoin implements ClientPlayConnectionEvents.Join {
   /* @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {



        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {

            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeFloat(StaminaData.addPoints(((IEntityDataSaver) player),0,"MaxStamina"));
            ServerPlayNetworking.send((ServerPlayerEntity) player, ModMessages.INITIALIZEM, buf);
            PacketByteBuf bufs= PacketByteBufs.create();
            bufs.writeInt(ModConfigs.X);
            ServerPlayNetworking.send( player, ModMessages.INITIALIZEX, bufs);
            PacketByteBuf bufss= PacketByteBufs.create();
            bufss.writeInt(ModConfigs.Y);
            ServerPlayNetworking.send( player, ModMessages.INITIALIZEY, bufss);
        }

    }*/

    @Override
    public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {

        PacketByteBuf bufm = PacketByteBufs.empty();


        ClientPlayNetworking.send(ModMessages.INITIALIZEMC2S,bufm);

        PacketByteBuf bufmS = PacketByteBufs.empty();


        ClientPlayNetworking.send(ModMessages.INIT,bufmS);

    }
}