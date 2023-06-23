package net.kav.kav_soul_like.TechnicAttacks;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.event.ClientStamina;
import net.kav.kav_soul_like.networking.packet.Packets;
import net.kav.kav_soul_like.util.IEntityDataSaver;
import net.kav.kav_soul_like.util.StaminaData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public abstract class Abstracttechnic {

    public int cooldown;
    private int stamina;
    private String name;
    private int speed;

    public Abstracttechnic(String name,int cooldown,int stamina,int speed)
    {
        this.name=name;
        this.cooldown=cooldown;
        this.stamina=stamina;
        this.speed=speed;
    }

    public String getName()
    {
        return this.name;
    }
    public int getSpeed()
    {
        return this.speed;
    }
    public int getStamina()
    {
        return this.stamina;
    }

    public Abstracttechnic(String name,int cooldown,int stamina)
    {
        this.name=name;
        this.cooldown=cooldown;
        this.stamina=stamina;
        this.speed=1;
    }

    public void tick(PlayerEntity player)
    {

    }

    public List<LivingEntity> getEntitiesNearby(World world, double expandDistance,double attackRange , Predicate<LivingEntity> filter, LivingEntity entity)
    {
        double yaw = entity.getYaw();

        return world.getEntitiesByClass(LivingEntity.class, entity.getBoundingBox().expand(Math.sin(Math.toRadians(-yaw)) * expandDistance, 0, Math.cos(Math.toRadians(-yaw)) * expandDistance)
                .expand(attackRange, attackRange, attackRange), filter.and(e -> e != entity));
    }
    public void staminaconsume()
    {StaminaData.removePoints(((IEntityDataSaver) MinecraftClient.getInstance().player), this.getStamina(), "Stamina");}

    public void ClientSideExecution()
    {



    }
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player)
    {

    }

}
