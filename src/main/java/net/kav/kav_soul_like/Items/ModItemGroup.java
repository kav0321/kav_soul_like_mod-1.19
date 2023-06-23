package net.kav.kav_soul_like.Items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.kav.kav_soul_like.Kav_soul_like;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static final ItemGroup KAV_SOUL_MONSTER = FabricItemGroupBuilder.build(
            new Identifier(Kav_soul_like.MOD_ID, "kav_soul_monster"), () -> new ItemStack(ModItems.ECHO_STAR));
}
