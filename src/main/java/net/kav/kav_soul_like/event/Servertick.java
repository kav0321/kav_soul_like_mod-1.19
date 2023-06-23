package net.kav.kav_soul_like.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.kav.kav_soul_like.TechnicAttacks.TechnicManager;
import net.kav.kav_soul_like.util.IEntityDataSaver;
import net.kav.kav_soul_like.util.Negative;
import net.kav.kav_soul_like.util.playerabilities;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class Servertick implements ServerTickEvents.EndWorldTick{

    @Override
    public void onEndTick(ServerWorld world) {


        for(ServerPlayerEntity player: world.getServer().getPlayerManager().getPlayerList())
        {
            if(((IEntityDataSaver) player).getPersistentData().contains("aglet") &&playerabilities.getability(((IEntityDataSaver) player),"aglet")!=-1)
            {
                TechnicManager.Techics.get(playerabilities.getability(((IEntityDataSaver) player),"aglet")).tick(player);
            }
            else if(((IEntityDataSaver) player).getPersistentData().contains("necklace") &&playerabilities.getability(((IEntityDataSaver) player),"necklace")!=-1)
            {
                TechnicManager.Techics.get(playerabilities.getability(((IEntityDataSaver) player),"necklace")).tick(player);
            } else if(((IEntityDataSaver) player).getPersistentData().contains("glove") &&playerabilities.getability(((IEntityDataSaver) player),"glove")!=-1)
            {
                TechnicManager.Techics.get(playerabilities.getability(((IEntityDataSaver) player),"glove")).tick(player);
            }

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
