package net.kav.kav_soul_like.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.entity.client.golden_charge_attack_renderer;
import net.kav.kav_soul_like.entity.client.melina_renderer;
import net.kav.kav_soul_like.entity.custom.golden_charge_attack;
import net.kav.kav_soul_like.entity.custom.melina_entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.example.client.renderer.entity.RocketRender;
import software.bernie.example.entity.RocketProjectile;
import software.bernie.example.registry.EntityRegistry;
import software.bernie.example.registry.EntityRegistryBuilder;
import software.bernie.geckolib3.GeckoLib;

import static software.bernie.example.registry.EntityRegistry.buildEntity;

public class ModEntities {

    public static final EntityType<melina_entity> DOROTHY_ENTITY_ENTITY_TYPE = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Kav_soul_like.MOD_ID, "melina"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, melina_entity::new)
                    .dimensions(EntityDimensions.fixed(1.4f, 1.5f)).build());
    public static EntityType<golden_charge_attack> GOLD_CHARGE= Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Kav_soul_like.MOD_ID, "charge_attack"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC,golden_charge_attack::new)
                    .dimensions(EntityDimensions.fixed(1.4f, 1.5f)).build());
    public static EntityType<golden_charge_attack> GOLD_CHARGE2 = buildEntity(golden_charge_attack::new, golden_charge_attack.class, 1.5F,
            2F, SpawnGroup.MISC);
    public static <T extends Entity> EntityType<T> buildEntity(EntityType.EntityFactory<T> entity, Class<T> entityClass,
                                                               float width, float height, SpawnGroup group) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            String name = entityClass.getSimpleName().toLowerCase();
            return EntityRegistryBuilder.<T>createBuilder(new Identifier(Kav_soul_like.MOD_ID, name)).entity(entity)
                    .category(group).dimensions(EntityDimensions.changing(width, height)).build();
        }
        return null;
    }
    public static void registerEntityserver()
    {
        GeckoLib.initialize();
        FabricDefaultAttributeRegistry.register(ModEntities.DOROTHY_ENTITY_ENTITY_TYPE, melina_entity.setAttributes());
    }

    public static void registerEntityclient()
    {
        EntityRendererRegistry.register(ModEntities.DOROTHY_ENTITY_ENTITY_TYPE, melina_renderer::new);

        EntityRendererRegistry.register(ModEntities.GOLD_CHARGE, golden_charge_attack_renderer::new);
    }

}
