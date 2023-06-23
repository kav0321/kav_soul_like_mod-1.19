package net.kav.kav_soul_like.Items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kav.kav_soul_like.Items.custom.Art_of_War;
import net.kav.kav_soul_like.Items.custom.Echo_StarItem;
import net.kav.kav_soul_like.Items.custom.Honey_Fruit_Punch;
import net.kav.kav_soul_like.Kav_soul_like;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item ECHO_STAR= registerItem("echo_star",new Echo_StarItem(new FabricItemSettings().group(ModItemGroup.KAV_SOUL_MONSTER).maxDamage(10)));
    public static final Item HONEY_FRUIT_PUNCH= registerItem("honey_fruit_punch",new Honey_Fruit_Punch(new FabricItemSettings().group(ModItemGroup.KAV_SOUL_MONSTER).food(ModFood.HONEY_FRUIT_PUNCH)));
    public static final Item GRAB_ART_OF_WAR= registerItem("grab_art_of_war",new Art_of_War(new FabricItemSettings().group(ModItemGroup.KAV_SOUL_MONSTER),0,"glove"));
    public static final Item SMASH_ART_OF_WAR= registerItem("smash_art_of_war",new Art_of_War(new FabricItemSettings().group(ModItemGroup.KAV_SOUL_MONSTER),1,"aglet"));
    public static final Item PARRY_ART_OF_WAR= registerItem("parry_art_of_war",new Art_of_War(new FabricItemSettings().group(ModItemGroup.KAV_SOUL_MONSTER),2,"necklace"));
    public static final Item KICK_ART_OF_WAR= registerItem("kick_art_of_war",new Art_of_War(new FabricItemSettings().group(ModItemGroup.KAV_SOUL_MONSTER),3,"aglet"));
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registry.ITEM, new Identifier(Kav_soul_like.MOD_ID, name),item);
    }

    public static void registerModItems()
    {
        Kav_soul_like.LOGGER.debug("Rendering Mod Item for "+Kav_soul_like.MOD_ID);
    }
}
