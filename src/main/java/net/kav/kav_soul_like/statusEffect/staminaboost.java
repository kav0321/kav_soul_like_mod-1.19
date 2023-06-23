package net.kav.kav_soul_like.statusEffect;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.kav_soul_like.entity.ModEntities;
import net.kav.kav_soul_like.networking.ModMessages;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import java.awt.*;

public class staminaboost extends StatusEffect {
    public staminaboost()
    {
        super(
                StatusEffectCategory.BENEFICIAL, Color.GREEN.getRGB()
        );
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return true;
    }
    // This method is called when it applies the status effect. We implement custom functionality here.
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            if(!entity.world.isClient())
            {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeFloat((float) (0.4*amplifier+0.1));
                ServerPlayerEntity player= (ServerPlayerEntity) entity;
                ServerPlayNetworking.send( player, ModMessages.effect_stamina,buf);
            }

        }
    }

}
