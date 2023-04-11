package net.kav.kav_soul_like.networking.packet;

import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.kav_soul_like.config.ModConfigs;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.util.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Iterator;
import java.util.Map;

import static java.lang.Math.*;

public class PlayerStatsC2S
{
    public static void DISCO2(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {

        ((IEntityDataSaver) player).getPersistentData().putFloat("tick",buf.readInt());

    }
    public static void DISCO(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        ((IEntityDataSaver) player).getPersistentData().putFloat("recoveryrate",buf.readFloat());

    }

    public static void INITS(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        int Defence=DefenceData.getDefence(((IEntityDataSaver) player));
        PacketByteBuf bufdefences= PacketByteBufs.create();
        bufdefences.writeInt(Defence);
        if(Defence!=0)
        {
            ServerPlayNetworking.send(player, ModMessages.DEFENCEC,bufdefences);
        }

    }
    public static void INIT(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {

        PacketByteBuf bufx= PacketByteBufs.create();
        PacketByteBuf bufy= PacketByteBufs.create();
        PacketByteBuf bufr= PacketByteBufs.create();



        PacketByteBuf bufstr= PacketByteBufs.create();
        PacketByteBuf bufxheart= PacketByteBufs.create();
        PacketByteBuf bufagility= PacketByteBufs.create();
        PacketByteBuf bufstamina= PacketByteBufs.create();

        PacketByteBuf buflevel= PacketByteBufs.create();


        bufx.writeInt(ModConfigs.X);
        bufy.writeInt(ModConfigs.Y);
        bufr.writeFloat(((IEntityDataSaver) player).getPersistentData().getFloat("recoveryrate"));


        int str= StrengthData.getstrength(((IEntityDataSaver) player));
        int health= HeartData.getHeart(((IEntityDataSaver) player));
        int agility= AgilityData.getAgility(((IEntityDataSaver) player));
        int staminal= StaminaLevelData.getStaminaL(((IEntityDataSaver) player));

        int Level=LevelData.getLevel(((IEntityDataSaver) player));


        bufstr.writeInt(str);
        bufxheart.writeInt(health);
        bufagility.writeInt(agility);
        bufstamina.writeInt(staminal);

       ;

        buflevel.writeInt(Level);


        ServerPlayNetworking.send(player, ModMessages.STRENGTHC,bufstr);
        ServerPlayNetworking.send(player, ModMessages.HEALTHC,bufxheart);
        ServerPlayNetworking.send(player, ModMessages.AGILITYC,bufagility);
        ServerPlayNetworking.send(player, ModMessages.STAMINALC,bufstamina);

        ServerPlayNetworking.send(player, ModMessages.LEVELC,buflevel);


        ServerPlayNetworking.send(player,ModMessages.INITIALIZEX,bufx);
        ServerPlayNetworking.send(player,ModMessages.INITIALIZEY,bufy);
        ServerPlayNetworking.send(player,ModMessages.RECOVERYRATE,bufr);


            PacketByteBuf bufmax= PacketByteBufs.create();
            bufmax.writeFloat(((IEntityDataSaver) player).getPersistentData().getFloat("MaxStamina"));
            ServerPlayNetworking.send(player,ModMessages.MAXSTAMINA,bufmax);


    }

    public static void getlowsta(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        String xa= buf.readString();


        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,20,100));
    }
    public static void getMaxSta(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        ((IEntityDataSaver) player).getPersistentData().putFloat("MaxStamina",buf.readFloat());
    }


    public static void getsta(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {


        ((IEntityDataSaver) player).getPersistentData().putFloat("Stamina",buf.readFloat());
        // System.out.println(StaminaData.Stamina(((IEntityDataSaver) player)));

    }

    public static void removeexp(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        player.setExperiencePoints(player.totalExperience-buf.readInt());
    }

    public static void statshealth(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {


        HeartData.increaseHeart(((IEntityDataSaver) player),1);
        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).getValue()+1);

    }

    public static void statssttack(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {




        if(StrengthData.getstrength(((IEntityDataSaver) player))>=10)
        {
            player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getValue()+0.1);
        }
        else if(StrengthData.getstrength(((IEntityDataSaver) player))>=20)
        {
            player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getValue()+0.05);

        }
        else if(StrengthData.getstrength(((IEntityDataSaver) player))>=30)
        {
            player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getValue()+0.01);

        }
        else if(StrengthData.getstrength(((IEntityDataSaver) player))>=40)
        {
            player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getValue()+0.001);

        }
        else
        {
            StrengthData.increasestrength(((IEntityDataSaver) player),1);
            player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getValue()+0.5);
        }

    }

    public static void statsagility(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {

        AgilityData.increaseAgility(((IEntityDataSaver) player),1);
        player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).getValue()+0.1);
        player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).getValue()+0.01);
    }

    public static void statsstaminal(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        PacketByteBuf bufs= PacketByteBufs.empty();
        ServerPlayNetworking.send(player,ModMessages.STAMINAM,bufs);

        
        StaminaData.addPoints(((IEntityDataSaver) player),1,"MaxStamina");
        StaminaLevelData.increaseStaminaL(((IEntityDataSaver) player),1);
    }
    public static void statsdefence(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        DefenceData.increaseDefence(((IEntityDataSaver) player),1);
        player.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).getValue()+0.1f);
        player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).getValue()+1);
    }

    public static void statslevel(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        int exp_need=levelingsystem.reqcal(buf.readInt()-1);
        player.setExperienceLevel(player.experienceLevel-exp_need);

        LevelData.increaseLevel(((IEntityDataSaver) player),1);

    }
    public static void statslevelcre(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        LevelData.increaseLevel(((IEntityDataSaver) player),1);
    }

    public static void sendstats(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        float x12;
        double d = 0;
        ItemStack itemss = player.getMainHandStack();
        Item item = itemss.getItem();
        Multimap<EntityAttribute, EntityAttributeModifier> multimap = itemss.getAttributeModifiers(EquipmentSlot.MAINHAND);
        if (!multimap.isEmpty()) {
            Iterator var11 = multimap.entries().iterator();
            while(var11.hasNext()) {
                Map.Entry<EntityAttribute, EntityAttributeModifier> entry = (Map.Entry)var11.next();
                EntityAttributeModifier entityAttributeModifier = (EntityAttributeModifier)entry.getValue();
                d = entityAttributeModifier.getValue();

            }
            //EntityAttributeModifier entityAttributeModifier = (EntityAttributeModifier) item.getAttributeModifiers(EquipmentSlot.MAINHAND).get(EntityAttributes.GENERIC_ATTACK_SPEED);
            d=abs(d);
            // player.sendMessage(Text.of(Double.toString(d)),true);
        }




        //////player.sendMessage(Text.of(Float.toString(SoulData.addFloatpoint(((IEntityDataSaver) player), 0f, "Stamina"))),true);


        if(player.isCreative())
        {
            x12=0;
        }
        else
            x12= (float) (d)+3;

        if(x12>4)
        {
            x12= (float) (-1.1859*pow(x12,2)+20.286*x12-69.792);
        }


        //removed in the server side

        PacketByteBuf bufs = PacketByteBufs.create();


            bufs.writeFloat(x12-0.4f);



        //player.sendMessage(Text.literal(player.getName().toString()),true);
        ServerPlayNetworking.send(player, ModMessages.STAMINA2,bufs);
    }
}
