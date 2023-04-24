package net.kav.kav_soul_like.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.kav.kav_soul_like.block.custom.statue_kav;
import net.kav.kav_soul_like.util.IEntityDataSaver;
import net.kav.kav_soul_like.util.Negative;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class Servertick implements ServerTickEvents.EndWorldTick{

    @Override
    public void onEndTick(ServerWorld world) {

        for(ServerPlayerEntity player: world.getServer().getPlayerManager().getPlayerList())
        {
            if(Negative.getEffect(((IEntityDataSaver) player)) && !player.isCreative())
            {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,20,3));

            }
            else if(!Negative.getEffect(((IEntityDataSaver) player)))
            {
               // System.out.println("here");
                player.removeStatusEffect(StatusEffects.SLOWNESS);
            }
        }
    }
}
