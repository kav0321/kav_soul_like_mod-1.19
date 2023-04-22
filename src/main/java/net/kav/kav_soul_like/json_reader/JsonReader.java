package net.kav.kav_soul_like.json_reader;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.networking.packet.ItemsPacket;
import net.kav.kav_soul_like.util.item_requirement.weapon_req;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.network.ServerPlayerEntity;

public class JsonReader {
    public static void init() {
        //System.out.println("S" );
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new statsloader());

        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, serverResourceManager, success) -> {

        });
    }
}
