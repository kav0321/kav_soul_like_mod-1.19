package net.kav.kav_soul_like.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class entityattack implements ServerLivingEntityEvents.AllowDamage {

    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        if(entity instanceof PlayerEntity)
        {
            if(entity.isBlocking())
            {
                ServerPlayNetworking.send(((ServerPlayerEntity) entity),ModMessages.SHIELD,PacketByteBufs.empty());
            }
        }
        return true;
    }
}
