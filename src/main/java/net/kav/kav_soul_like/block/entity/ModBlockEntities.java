package net.kav.kav_soul_like.block.entity;

import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.block.ModBlocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {

    public static BlockEntityType<statue_kav_entity> STATUE;

    public static void registerBlockEntities() {
        STATUE = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Kav_soul_like.MOD_ID, "statue_kav"),
                FabricBlockEntityTypeBuilder.create(statue_kav_entity::new,
                        ModBlocks.GOLDEN_CUP).build(null));
    }
}
