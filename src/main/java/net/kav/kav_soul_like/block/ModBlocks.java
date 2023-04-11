package net.kav.kav_soul_like.block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.block.custom.GoldenCup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.ToIntFunction;

public class ModBlocks {

    public static final Block GOLDEN_CUP = registerBlock("golden_cup",
            new GoldenCup(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool().luminance(createLightLevelFromLitBlockState(15)).nonOpaque()), ItemGroup.MISC);


    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(Kav_soul_like.MOD_ID, name), block);
    }
    private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return state -> state.get(Properties.LIT) != false ? litLevel : 0;
    }

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(Kav_soul_like.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(Kav_soul_like.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        Kav_soul_like.LOGGER.debug("Registering ModBlocks for " + Kav_soul_like.MOD_ID);
    }
}
