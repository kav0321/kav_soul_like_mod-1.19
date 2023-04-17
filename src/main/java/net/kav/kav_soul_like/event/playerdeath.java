package net.kav.kav_soul_like.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.util.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class playerdeath implements ServerPlayerEvents.AfterRespawn{
    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        int str;
        int health;
        int agility;
        int staminal;
        int Defence;
        float maxstamina;
        int level;


        PacketByteBuf bufstrD= PacketByteBufs.create();
        PacketByteBuf bufxheartD= PacketByteBufs.create();
        PacketByteBuf bufagilityD= PacketByteBufs.create();
        PacketByteBuf bufstaminaD= PacketByteBufs.create();
        PacketByteBuf bufDefenceD= PacketByteBufs.create();
        PacketByteBuf bufmax= PacketByteBufs.create();
        PacketByteBuf bufLevelondeeznutz = PacketByteBufs.create();

        str= StrengthData.getstrength(((IEntityDataSaver) oldPlayer));
        health= HeartData.getHeart(((IEntityDataSaver) oldPlayer));
        agility= AgilityData.getAgility(((IEntityDataSaver) oldPlayer));
        staminal= StaminaLevelData.getStaminaL(((IEntityDataSaver) oldPlayer));
        Defence=DefenceData.getDefence(((IEntityDataSaver) oldPlayer));
        maxstamina= ((IEntityDataSaver) oldPlayer).getPersistentData().getFloat("MaxStamina");
        //maxstamina=StaminaData.addPoints(((IEntityDataSaver) oldPlayer),0,"MaxStamina");
        level =LevelData.getLevel(((IEntityDataSaver) oldPlayer));


        StrengthData.setstrength(((IEntityDataSaver) newPlayer),str);
        HeartData.setHeart(((IEntityDataSaver) newPlayer),health);
        AgilityData.setAgility(((IEntityDataSaver) newPlayer),agility);
        StaminaLevelData.setStaminaL(((IEntityDataSaver) newPlayer),staminal);
        DefenceData.setDefence(((IEntityDataSaver) newPlayer),Defence);
        StaminaData.floatsetmax(((IEntityDataSaver) newPlayer),maxstamina);
        LevelData.setLevel(((IEntityDataSaver) newPlayer),level);

        bufstrD.writeInt(str);
        bufxheartD.writeInt(health);
        bufagilityD.writeInt(agility);
        bufstaminaD.writeInt(staminal);
        bufDefenceD.writeInt(Defence);
        bufmax.writeFloat(maxstamina);
        bufLevelondeeznutz.writeInt(level);


        double f=HeartData.heartlevelbase(((IEntityDataSaver) oldPlayer),health);

        newPlayer.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(f);


        double f22= StrengthData.strengthleveltobase(((IEntityDataSaver) oldPlayer),str);
        newPlayer.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(f22);


        double f1=AgilityData.Agilitylevel_base_attack(((IEntityDataSaver) oldPlayer),agility);
        double f2=AgilityData.Agilitylevel_base_move(((IEntityDataSaver) oldPlayer),agility);
        newPlayer.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).setBaseValue(f1);
        newPlayer.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(f2);
        double f122=DefenceData.DefenceLevelKnockbackResistance(((IEntityDataSaver) oldPlayer),Defence);
        double f222=DefenceData.DefenceLevelArmor(((IEntityDataSaver) oldPlayer),Defence);
        newPlayer.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(f122);
        newPlayer.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(f222);

        newPlayer.setHealth((float) newPlayer.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).getBaseValue());
        ServerPlayNetworking.send(newPlayer, ModMessages.HEALTHC,bufxheartD);
        ServerPlayNetworking.send(newPlayer,ModMessages.STRENGTHC,bufstrD);
        ServerPlayNetworking.send(newPlayer,ModMessages.AGILITYC,bufagilityD);
        ServerPlayNetworking.send(newPlayer,ModMessages.STAMINALC,bufstaminaD);
        ServerPlayNetworking.send(newPlayer,ModMessages.DEFENCEC,bufDefenceD);
        ServerPlayNetworking.send(newPlayer,ModMessages.MAXSTAMINA,bufmax);
        ServerPlayNetworking.send(newPlayer,ModMessages.LEVELC,bufLevelondeeznutz);
    }
}
