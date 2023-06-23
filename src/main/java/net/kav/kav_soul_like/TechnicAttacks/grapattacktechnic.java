package net.kav.kav_soul_like.TechnicAttacks;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.networking.packet.Packets;
import net.kav.kav_soul_like.util.IEntityDataSaver;
import net.kav.kav_soul_like.util.StaminaData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class grapattacktechnic extends Abstracttechnic{
    public grapattacktechnic(String name, int cooldown, int stamina, int speed) {
        super(name, cooldown, stamina, speed);
    }
    private LivingEntity grabentity;
    private Map<PlayerEntity, LivingEntity> playerToEntityMap = new HashMap<>();
    private Map<LivingEntity, PlayerEntity> entityToPlayerMap = new HashMap<>();
    private int tick=80;
    public grapattacktechnic(String name, int cooldown, int stamina) {
        super(name, cooldown, stamina, 1);
    }


    @Override
    public void ClientSideExecution()
    {
        super.ClientSideExecution();
    }
    @Override
    public void tick(PlayerEntity player)
    {

        if(tick<=0)
        {
            tick=80;
        }System.out.println(tick);
            if(entityToPlayerMap !=null)
            {
                if(!player.world.isClient()&& entityToPlayerMap.containsValue(player)&&tick!=0)
                {
                    tick--;
                    System.out.println(tick);
                    double sitOffsetY = -0.5; // Adjust the vertical offset for the sitting position
                    float desiredYaw = 0.0f; // Adjust the desired yaw rotation if needed

// Adjust the position of the entity to make it sit on the ground


                    this.forceFollowPlayer(player,getEntityForPlayer(player),1);
                    if(tick<=0)
                    {
                        System.out.println("s");
                        Vec3d playerPos = player.getPos();
                        if(getEntityForPlayer(player) instanceof PlayerEntity)
                        {

                            ServerPlayNetworking.send((ServerPlayerEntity) getEntityForPlayer(player), ModMessages.sync, new Packets.sync(playerPos.getX(),playerPos.getY(),playerPos.getZ()).write());
                        }
                        Vec3d entityPos = getEntityForPlayer(player).getPos();

                        // Calculate the direction vector from the player to the entity
                        Vec3d direction = entityPos.subtract(playerPos).normalize();

                        // Apply the push force to the entity
                        getEntityForPlayer(player).takeKnockback(5,-direction.getX(),-direction.getZ());
                       // getEntityForPlayer(player).addVelocity(direction.x * 5, direction.y * 5, direction.z * 5);

                        StatusEffectInstance effectInstance = new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 3);

                        getEntityForPlayer(player).addStatusEffect(effectInstance);
                        removeEntityPlayer(getEntityForPlayer(player));
                        removePlayerEntity(player);

                    }

                }
            }



    }
    public void removePlayerEntity(PlayerEntity player) {
        LivingEntity entity = playerToEntityMap.remove(player);
        if (entity != null) {
            entityToPlayerMap.remove(entity);
        }
    }

    // Method to remove the association between a living entity and a player
    public void removeEntityPlayer(LivingEntity entity) {
        PlayerEntity player = entityToPlayerMap.remove(entity);
        if (player != null) {
            playerToEntityMap.remove(player);
        }
    }
    @Nullable
    public LivingEntity getEntityForPlayer(PlayerEntity player) {
        return playerToEntityMap.get(player);
    }

    // Method to retrieve the player associated with a living entity
    public PlayerEntity getPlayerForEntity(LivingEntity entity) {
        return entityToPlayerMap.get(entity);
    }
    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player)
    {
        double pushDistance = 2.0;
        super.ServerSideExecution(server,player);

        double pullStrength = 1.1;
        for (LivingEntity entity : getEntitiesNearby(player.getWorld(),2,1, e -> (e instanceof LivingEntity),player))
        {
            Vec3d playerPos = player.getPos();
            Vec3d entityPos = entity.getPos();
            Vec3d direction = playerPos.subtract(entityPos).normalize();

// Apply the force to the entity
            double forceX = direction.x * pullStrength;
            double forceY = direction.y * pullStrength;
            double forceZ = direction.z * pullStrength;

            entity.addVelocity(forceX, forceY, forceZ);
            entity.velocityModified = true;
            grabentity=entity;

            playerToEntityMap.put(player,entity);
            entityToPlayerMap.put(entity,player);
            // Teleport the entity to the push position

        }

    }

    public void forceFollowPlayer(PlayerEntity player, LivingEntity entity, double followDistance) {
        // Get the position of the player
        if(entity==null)
        {
            return;
        }
        double playerX = player.getX();
        double playerY = player.getY();
        double playerZ = player.getZ();

        // Calculate the direction vector from the entity to the player
        double dx = playerX - entity.getX();
        double dy = playerY - entity.getY();
        double dz = playerZ - entity.getZ();

        // Calculate the distance between the entity and the player
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

        // Normalize the direction vector
        if (distance > 0) {
            dx /= distance;
            dy /= distance;
            dz /= distance;
        }

        // Calculate the target position for the entity to teleport to
        double targetX = playerX - dx * followDistance;
        double targetY = playerY - dy * followDistance;
        double targetZ = playerZ - dz * followDistance;

        // Teleport the entity to the target position
        entity.teleport(targetX, targetY, targetZ);
    }
}
