package net.kav.kav_soul_like.mixin;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.kav_soul_like.networking.ModMessages;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTrackerEntry.class)
public class EntityTrackerEntryMixin {
    @Shadow
    private final Entity entity;

    public EntityTrackerEntryMixin(Entity entity) {
        this.entity = entity;
    }

    @Inject(method = "startTracking", at = @At(value = "TAIL"))
    public void startTrackingMixin(ServerPlayerEntity serverPlayer, CallbackInfo info) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            for (int i = 41; i < 43; i++) {
                if (!serverPlayer.getInventory().getStack(i).isEmpty()) {
                    PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
                    data.writeIntArray(new int[] { serverPlayer.getId(), i });
                    data.writeItemStack(serverPlayer.getInventory().getStack(i));
                    ServerPlayNetworking.send((ServerPlayerEntity) player, ModMessages.MODSLOTS, data);
                }
                if (!player.getInventory().getStack(i).isEmpty()) {
                    PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
                    data.writeIntArray(new int[] { player.getId(), i });
                    data.writeItemStack(player.getInventory().getStack(i));
                    ServerPlayNetworking.send(serverPlayer, ModMessages.MODSLOTS, data);
                }
            }
        }
    }

}