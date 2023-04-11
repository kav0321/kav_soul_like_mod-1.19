package net.kav.kav_soul_like.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class playertechServerSide {


    public static void Dash(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {

        int[] x= new int[2];
        x= buf.readIntArray();

        double xd= x[0];
        double zd= x[1];

        xd=(xd)/100000;
        zd=(zd)/100000;

        player.setMovementSpeed(1000);
        MinecraftClient.getInstance().player.setSprinting(true);
        player.setVelocity(new Vec3d(xd*1.5,0,zd*1.5));

    }
}
