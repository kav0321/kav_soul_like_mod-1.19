package net.kav.kav_soul_like.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kav.kav_soul_like.util.AgilityData;
import net.kav.kav_soul_like.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class playertechServerSide {


    public static void Dash(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {

        int[] x = new int[2];
        x = buf.readIntArray();

        double xd = x[0];
        double zd = x[1];

        xd = (xd) / 100000;
        zd = (zd) / 100000;

        player.setMovementSpeed(1000);
        //MinecraftClient.getInstance().player.setSprinting(true);
        if (AgilityData.getAgility(((IEntityDataSaver) player)) < 10)
        {
            player.setVelocity(new Vec3d(xd * 1.1, 0, zd * 1.1));
        }
        else if(AgilityData.getAgility(((IEntityDataSaver) player)) >= 10 && AgilityData.getAgility(((IEntityDataSaver) player)) < 20)
        {
            player.setVelocity(new Vec3d(xd * 1.2, 0, zd * 1.2));
        }
        else if(AgilityData.getAgility(((IEntityDataSaver) player)) >= 20 && AgilityData.getAgility(((IEntityDataSaver) player)) < 30)
        {
            player.setVelocity(new Vec3d(xd * 1.3, 0, zd * 1.3));
        }
        else
        {
            player.setVelocity(new Vec3d(xd*1.4,0,zd*1.4));
        }


    }
}
