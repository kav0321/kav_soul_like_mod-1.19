package net.kav.kav_soul_like.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.networking.packet.Packets;
import net.kav.kav_soul_like.util.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collection;
import java.util.Iterator;
//code belongs to https://github.com/Globox1997/LevelZ/blob/1.19/src/main/java/net/levelz/init/CommandInit.java
public class Levelsetcommand {
    public static void init()
    {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, environment) -> {
            dispatcher.register((CommandManager.literal("Player_Points").requires((serverCommandSource) -> {
                return serverCommandSource.hasPermissionLevel(2);
            })).then(CommandManager.argument("targets", EntityArgumentType.players())
                    // Add values
                    .then(CommandManager.literal("add").then(CommandManager.literal("health").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return addamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "health",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))).then(CommandManager.literal("strength").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return addamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "strength",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))).then(CommandManager.literal("agility").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return addamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "agility",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))).then(CommandManager.literal("defense").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return addamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "defense",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))).then(CommandManager.literal("stamina").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return addamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "stamina",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))))
                    // Remove values
                    .then(CommandManager.literal("remove").then(CommandManager.literal("health").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return removeamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "health",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))).then(CommandManager.literal("strength").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return removeamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "strength",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))).then(CommandManager.literal("agility").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return removeamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "agility",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))).then(CommandManager.literal("defense").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return removeamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "defense",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))).then(CommandManager.literal("stamina").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return removeamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "stamina",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))))
                    // Set values
                    .then(CommandManager.literal("set").then(CommandManager.literal("health").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return setamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "health",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))).then(CommandManager.literal("strength").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return setamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "strength",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))).then(CommandManager.literal("agility").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return setamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "agility",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))).then(CommandManager.literal("defense").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return setamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "defense",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))).then(CommandManager.literal("stamina").then(CommandManager.argument("level", IntegerArgumentType.integer()).executes((commandContext) -> {
                        return setamount(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"), "stamina",
                                IntegerArgumentType.getInteger(commandContext, "level"));
                    }))))
                    .then(CommandManager.literal("clear").executes((commandContext) -> {
                        return reset(commandContext.getSource(), EntityArgumentType.getPlayers(commandContext, "targets"));
                    }))

                   ));
        });
    }

    public static int reset(ServerCommandSource source, Collection<ServerPlayerEntity> targets)
    {
    PacketByteBuf buf = PacketByteBufs.create();
        Iterator<ServerPlayerEntity> playerIterator = targets.iterator();
        while (playerIterator.hasNext()) {

            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerIterator.next();

            StrengthData.setstrength(((IEntityDataSaver) serverPlayerEntity),0);
            HeartData.setHeart(((IEntityDataSaver) serverPlayerEntity),0);
            AgilityData.setAgility(((IEntityDataSaver) serverPlayerEntity),0);
            StaminaLevelData.setStaminaL(((IEntityDataSaver) serverPlayerEntity),0);
            DefenceData.setDefence(((IEntityDataSaver) serverPlayerEntity),0);
            LevelData.setLevel(((IEntityDataSaver) serverPlayerEntity),0);

           double str1 = StrengthData.strengthleveltobase(((IEntityDataSaver) serverPlayerEntity),0);
           double heart = HeartData.heartlevelbase(((IEntityDataSaver) serverPlayerEntity),0);
           double agility_1 = AgilityData.Agilitylevel_base_move(((IEntityDataSaver) serverPlayerEntity),0);
           double agility_2 = AgilityData.Agilitylevel_base_attack(((IEntityDataSaver) serverPlayerEntity),0);
           double def_1 =DefenceData.DefenceLevelArmor(((IEntityDataSaver) serverPlayerEntity),0);
           double def_2 = DefenceData.DefenceLevelKnockbackResistance(((IEntityDataSaver) serverPlayerEntity),0);


           serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(def_2);
           serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(def_1);
           serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).setBaseValue(agility_2);
           serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(agility_1);
           serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(heart);
           serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(str1);
           float x=StaminaData.addPoints(((IEntityDataSaver) serverPlayerEntity),0,"MaxStamina");

           buf.writeFloat(StaminaData.addPoints(((IEntityDataSaver) serverPlayerEntity),-(x-20),"MaxStamina"));
            DefenceData.DefenceLevelKnockbackResistance(((IEntityDataSaver) serverPlayerEntity),0);
            DefenceData.DefenceLevelArmor(((IEntityDataSaver) serverPlayerEntity),0);
            ServerPlayNetworking.send(serverPlayerEntity,
                    Packets.Stats.ID,
                    new Packets.Stats(0,0,0,0,0,0).write());
            ServerPlayNetworking.send(serverPlayerEntity,ModMessages.MAXSTAMINA,buf);
        }
        return 1;
    }
    public static int setamount(ServerCommandSource source, Collection<ServerPlayerEntity> targets, String stats, int amount)
    {
        PacketByteBuf buf1 = PacketByteBufs.create();
        Iterator<ServerPlayerEntity> playerIterator = targets.iterator();
        while (playerIterator.hasNext()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerIterator.next();
            switch (stats){
                case "strength":
                    StrengthData.setstrength(((IEntityDataSaver) serverPlayerEntity),amount);
                    double str1 = StrengthData.strengthleveltobase(((IEntityDataSaver) serverPlayerEntity),StrengthData.getstrength(((IEntityDataSaver) serverPlayerEntity)));
                    buf1.writeInt( StrengthData.getstrength(((IEntityDataSaver) serverPlayerEntity)));
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(str1);
                    ServerPlayNetworking.send(serverPlayerEntity,ModMessages.STRENGTHC,buf1);
                    break;
                case "health":
                    HeartData.setHeart(((IEntityDataSaver) serverPlayerEntity),amount);
                    double heart = HeartData.heartlevelbase(((IEntityDataSaver) serverPlayerEntity),HeartData.getHeart(((IEntityDataSaver) serverPlayerEntity)));
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(heart);
                    buf1.writeInt( HeartData.getHeart(((IEntityDataSaver) serverPlayerEntity)));
                    ServerPlayNetworking.send(serverPlayerEntity,ModMessages.HEALTHC,buf1);
                    break;
                case "agility":
                    AgilityData.setAgility(((IEntityDataSaver) serverPlayerEntity),amount);
                    double agility_1 = AgilityData.Agilitylevel_base_move(((IEntityDataSaver) serverPlayerEntity),AgilityData.getAgility(((IEntityDataSaver) serverPlayerEntity)));
                    double agility_2 = AgilityData.Agilitylevel_base_attack(((IEntityDataSaver) serverPlayerEntity),AgilityData.getAgility(((IEntityDataSaver) serverPlayerEntity)));
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).setBaseValue(agility_2);
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(agility_1);
                    buf1.writeInt(AgilityData.getAgility(((IEntityDataSaver) serverPlayerEntity)));
                    ServerPlayNetworking.send(serverPlayerEntity,ModMessages.AGILITYC,buf1);
                    break;
                case "stamina":
                    PacketByteBuf buf12 = PacketByteBufs.create();
                    StaminaLevelData.setStaminaL(((IEntityDataSaver) serverPlayerEntity),amount);
                    float x=StaminaData.addPoints(((IEntityDataSaver) serverPlayerEntity),0,"MaxStamina");
                    StaminaData.addPoints(((IEntityDataSaver) serverPlayerEntity),-(x-20),"MaxStamina");
                    StaminaData.addPoints(((IEntityDataSaver) serverPlayerEntity),amount,"MaxStamina");
                    buf12.writeFloat(StaminaData.addPoints(((IEntityDataSaver) serverPlayerEntity),amount,"MaxStamina"));
                    buf1.writeInt(StaminaLevelData.getStaminaL(((IEntityDataSaver) serverPlayerEntity)));
                    ServerPlayNetworking.send(serverPlayerEntity,ModMessages.STAMINALC,buf1);
                    ServerPlayNetworking.send(serverPlayerEntity,ModMessages.MAXSTAMINA,buf12);
                    break;
                case "defense":
                    DefenceData.setDefence(((IEntityDataSaver) serverPlayerEntity),amount);
                    double def_1 =DefenceData.DefenceLevelArmor(((IEntityDataSaver) serverPlayerEntity),DefenceData.getDefence(((IEntityDataSaver) serverPlayerEntity)));
                    double def_2 = DefenceData.DefenceLevelKnockbackResistance(((IEntityDataSaver) serverPlayerEntity),DefenceData.getDefence(((IEntityDataSaver) serverPlayerEntity)));
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(def_2);
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(def_1);
                    buf1.writeInt(DefenceData.getDefence(((IEntityDataSaver) serverPlayerEntity)));
                    ServerPlayNetworking.send(serverPlayerEntity,ModMessages.DEFENCEC,buf1);
                    break;
                default:


            }
            LevelData.increaseLevel(((IEntityDataSaver) serverPlayerEntity),amount);
        }
    return 1;
    }

    public static int removeamount(ServerCommandSource source, Collection<ServerPlayerEntity> targets, String stats, int amount)
    {
        PacketByteBuf buf2 = PacketByteBufs.create();
        Iterator<ServerPlayerEntity> playerIterator = targets.iterator();
        while (playerIterator.hasNext()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerIterator.next();
            switch (stats){
                case "strength":
                    StrengthData.decreasestrength(((IEntityDataSaver) serverPlayerEntity),amount);
                    double str1 = StrengthData.strengthleveltobase(((IEntityDataSaver) serverPlayerEntity),StrengthData.getstrength(((IEntityDataSaver) serverPlayerEntity)));
                    buf2.writeInt( StrengthData.getstrength(((IEntityDataSaver) serverPlayerEntity)));
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(str1);
                    ServerPlayNetworking.send(serverPlayerEntity,ModMessages.STRENGTHC,buf2);
                    break;
                case "health":
                    HeartData.decreaseHeart(((IEntityDataSaver) serverPlayerEntity),amount);
                    double heart = HeartData.heartlevelbase(((IEntityDataSaver) serverPlayerEntity),HeartData.getHeart(((IEntityDataSaver) serverPlayerEntity)));
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(heart);
                    buf2.writeInt( HeartData.getHeart(((IEntityDataSaver) serverPlayerEntity)));
                    ServerPlayNetworking.send(serverPlayerEntity,ModMessages.HEALTHC,buf2);
                    break;
                case "agility":
                    AgilityData.decreaseAgility(((IEntityDataSaver) serverPlayerEntity),amount);
                    double agility_1 = AgilityData.Agilitylevel_base_move(((IEntityDataSaver) serverPlayerEntity),AgilityData.getAgility(((IEntityDataSaver) serverPlayerEntity)));
                    double agility_2 = AgilityData.Agilitylevel_base_attack(((IEntityDataSaver) serverPlayerEntity),AgilityData.getAgility(((IEntityDataSaver) serverPlayerEntity)));
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).setBaseValue(agility_2);
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(agility_1);
                    buf2.writeInt(AgilityData.getAgility(((IEntityDataSaver) serverPlayerEntity)));
                    ServerPlayNetworking.send(serverPlayerEntity,ModMessages.AGILITYC,buf2);
                    break;
                case "stamina":
                    PacketByteBuf buf12 = PacketByteBufs.create();
                    StaminaLevelData.decreaseStaminaL(((IEntityDataSaver) serverPlayerEntity),amount);
                    StaminaData.addPoints(((IEntityDataSaver) serverPlayerEntity),-amount,"MaxStamina");
                    buf12.writeFloat(StaminaData.addPoints(((IEntityDataSaver) serverPlayerEntity),amount,"MaxStamina"));
                    buf2.writeInt(StaminaLevelData.getStaminaL(((IEntityDataSaver) serverPlayerEntity)));
                    ServerPlayNetworking.send(serverPlayerEntity,ModMessages.STAMINALC,buf2);
                    ServerPlayNetworking.send(serverPlayerEntity,ModMessages.MAXSTAMINA,buf12);
                    break;
                case "defense":
                    DefenceData.decreaseDefence(((IEntityDataSaver) serverPlayerEntity),amount);
                    double def_1 =DefenceData.DefenceLevelArmor(((IEntityDataSaver) serverPlayerEntity),DefenceData.getDefence(((IEntityDataSaver) serverPlayerEntity)));
                    double def_2 = DefenceData.DefenceLevelKnockbackResistance(((IEntityDataSaver) serverPlayerEntity),DefenceData.getDefence(((IEntityDataSaver) serverPlayerEntity)));
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(def_2);
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(def_1);
                    buf2.writeInt(DefenceData.getDefence(((IEntityDataSaver) serverPlayerEntity)));
                    ServerPlayNetworking.send(serverPlayerEntity,ModMessages.DEFENCEC,buf2);
                    break;
                default:

            }
            LevelData.decreaseLevel(((IEntityDataSaver) serverPlayerEntity),amount);
        }

        return 1;
    }

    public static int addamount(ServerCommandSource source, Collection<ServerPlayerEntity> targets, String stats, int amount) {


        PacketByteBuf buf2 = PacketByteBufs.create();
        Iterator<ServerPlayerEntity> playerIterator = targets.iterator();
        while (playerIterator.hasNext()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerIterator.next();
            switch (stats) {
                case "strength":
                    StrengthData.increasestrength(((IEntityDataSaver) serverPlayerEntity), amount);
                    double str1 = StrengthData.strengthleveltobase(((IEntityDataSaver) serverPlayerEntity), StrengthData.getstrength(((IEntityDataSaver) serverPlayerEntity)));
                    buf2.writeInt(StrengthData.getstrength(((IEntityDataSaver) serverPlayerEntity)));
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(str1);
                    ServerPlayNetworking.send(serverPlayerEntity, ModMessages.STRENGTHC, buf2);
                    break;
                case "health":
                    HeartData.increaseHeart(((IEntityDataSaver) serverPlayerEntity), amount);
                    double heart = HeartData.heartlevelbase(((IEntityDataSaver) serverPlayerEntity), HeartData.getHeart(((IEntityDataSaver) serverPlayerEntity)));
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(heart);
                    buf2.writeInt(HeartData.getHeart(((IEntityDataSaver) serverPlayerEntity)));
                    ServerPlayNetworking.send(serverPlayerEntity, ModMessages.HEALTHC, buf2);
                    break;
                case "agility":
                    AgilityData.increaseAgility(((IEntityDataSaver) serverPlayerEntity), amount);
                    double agility_1 = AgilityData.Agilitylevel_base_move(((IEntityDataSaver) serverPlayerEntity), AgilityData.getAgility(((IEntityDataSaver) serverPlayerEntity)));
                    double agility_2 = AgilityData.Agilitylevel_base_attack(((IEntityDataSaver) serverPlayerEntity), AgilityData.getAgility(((IEntityDataSaver) serverPlayerEntity)));
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).setBaseValue(agility_2);
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(agility_1);
                    buf2.writeInt(AgilityData.getAgility(((IEntityDataSaver) serverPlayerEntity)));
                    ServerPlayNetworking.send(serverPlayerEntity, ModMessages.AGILITYC, buf2);
                    break;
                case "stamina":
                    PacketByteBuf buf12 = PacketByteBufs.create();
                    StaminaLevelData.increaseStaminaL(((IEntityDataSaver) serverPlayerEntity), amount);
                    StaminaData.addPoints(((IEntityDataSaver) serverPlayerEntity), amount, "MaxStamina");
                    buf12.writeFloat(StaminaData.addPoints(((IEntityDataSaver) serverPlayerEntity), amount, "MaxStamina"));
                    buf2.writeInt(StaminaLevelData.getStaminaL(((IEntityDataSaver) serverPlayerEntity)));
                    ServerPlayNetworking.send(serverPlayerEntity, ModMessages.STAMINALC, buf2);
                    ServerPlayNetworking.send(serverPlayerEntity, ModMessages.MAXSTAMINA, buf12);
                    break;
                case "defense":
                    DefenceData.increaseDefence(((IEntityDataSaver) serverPlayerEntity), amount);
                    double def_1 = DefenceData.DefenceLevelArmor(((IEntityDataSaver) serverPlayerEntity), DefenceData.getDefence(((IEntityDataSaver) serverPlayerEntity)));
                    double def_2 = DefenceData.DefenceLevelKnockbackResistance(((IEntityDataSaver) serverPlayerEntity), DefenceData.getDefence(((IEntityDataSaver) serverPlayerEntity)));
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(def_2);
                    serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(def_1);
                    buf2.writeInt(DefenceData.getDefence(((IEntityDataSaver) serverPlayerEntity)));
                    ServerPlayNetworking.send(serverPlayerEntity, ModMessages.DEFENCEC, buf2);
                    break;
                default:

            }
            LevelData.increaseLevel(((IEntityDataSaver) serverPlayerEntity),amount);
        }
        return 1;
    }



}
