package net.kav.kav_soul_like.Items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kav.kav_soul_like.Items.custom.Echo_StarItem;
import net.kav.kav_soul_like.Kav_soul_like;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item ECHO_STAR= registerItem("echo_star",new Echo_StarItem(new FabricItemSettings().group(ItemGroup.MISC).maxDamage(10)));

    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registry.ITEM, new Identifier(Kav_soul_like.MOD_ID, name),item);
    }

    public static void registerModItems()
    {
        Kav_soul_like.LOGGER.debug("Rendering Mod Item for "+Kav_soul_like.MOD_ID);
    }
}
