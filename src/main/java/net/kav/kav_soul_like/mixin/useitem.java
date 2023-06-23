package net.kav.kav_soul_like.mixin;

import net.kav.kav_soul_like.event.AttackOveride;
import net.kav.kav_soul_like.util.*;
import net.kav.kav_soul_like.util.item_requirement.combat_stats_req;
import net.kav.kav_soul_like.util.item_requirement.weapon_req;
import net.kav.kav_soul_like.util.search.Search;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(MinecraftClient.class)
public class useitem {


    @Inject(at = @At(value = "HEAD"), method = "doItemUse()V",cancellable = true)
    public void useitemblock(CallbackInfo info)
    {
        if(!(MinecraftClient.getInstance().player.isCreative()||MinecraftClient.getInstance().player.isSpectator()))
        {
            ItemStack itemStack = MinecraftClient.getInstance().player.getOffHandStack();
            ItemStack itemStackmain = MinecraftClient.getInstance().player.getMainHandStack();
            String item= Registry.ITEM.getId(itemStack.getItem()).toString();
            String itemmain= Registry.ITEM.getId(itemStackmain.getItem()).toString();

            if(!stats(item,MinecraftClient.getInstance().player, weapon_req.shield))
            {
                // System.out.println("asd");
                StaminaData.removePoints(((IEntityDataSaver) MinecraftClient.getInstance().player),10,"Stamina");

            }
            if(!stats(itemmain,MinecraftClient.getInstance().player, weapon_req.weapon))
            {
                //System.out.println("sasasd");
                info.cancel();

            } else if (itemStackmain.getItem() instanceof BowItem || itemStackmain.getItem() instanceof CrossbowItem) {
                int index= Search.search( weapon_req.weapon,itemmain);
                StaminaData.removePoints(((IEntityDataSaver) MinecraftClient.getInstance().player), (float) (weapon_req.weapon.get(index).AGILITY+weapon_req.weapon.get(index).STRENGTH),"Stamina");
            }
        }


    }
    public boolean stats(String string, PlayerEntity player, ArrayList<combat_stats_req> arr)
    {

        int index= Search.search(arr,string);
        if(index!=-1)
        {

            if(StrengthData.getstrength(((IEntityDataSaver) player))>=arr.get(index).STRENGTH && HeartData.getHeart(((IEntityDataSaver) player))>=arr.get(index).HEART && AgilityData.getAgility(((IEntityDataSaver) player))>=arr.get(index).AGILITY && StaminaLevelData.getStaminaL(((IEntityDataSaver) player))>=arr.get(index).STAMINA && DefenceData.getDefence(((IEntityDataSaver) player))>=arr.get(index).DEFENCE)
            {
                return true;
            } else {
                return false;
            }
        }

        if(index==-1)
        {
            return true;
        }

        return false;
    }
}
