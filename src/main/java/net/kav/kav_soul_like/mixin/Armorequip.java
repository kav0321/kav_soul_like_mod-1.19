package net.kav.kav_soul_like.mixin;


import net.kav.kav_soul_like.util.*;
import net.kav.kav_soul_like.util.item_requirement.combat_stats_req;
import net.kav.kav_soul_like.util.item_requirement.weapon_req;
import net.kav.kav_soul_like.util.search.Search;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(LivingEntity.class)
public abstract class Armorequip {

    @Shadow protected abstract void playBlockFallSound();

    @Inject(at = @At(value = "HEAD"), method = "onEquipStack(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)V")
    public void emitGameEvent(EquipmentSlot slot, ItemStack oldStack, ItemStack newstack, CallbackInfo info)
    {
        if (slot.getType() ==EquipmentSlot.Type.ARMOR || slot.getType()==EquipmentSlot.Type.HAND) {
           String item= Registry.ITEM.getId(newstack.getItem()).toString();

           if(((Object) this) instanceof PlayerEntity)
           {

               var player =(((PlayerEntity) (Object) this));
               if(!stats(item,player,weapon_req.armorList))
               {
                   if(!player.world.isClient)
                   {

                           if(slot==EquipmentSlot.HEAD)
                           {
                             //  System.out.println("head");
                               Negative.setEffect(((IEntityDataSaver) player),true,"head");
                           }
                           if(slot==EquipmentSlot.CHEST)
                           {
                             //  System.out.println("chest");
                               Negative.setEffect(((IEntityDataSaver) player),true,"chest");
                           }
                           if(slot==EquipmentSlot.LEGS)
                           {
                              // System.out.println("legging");
                               Negative.setEffect(((IEntityDataSaver) player),true,"legging");
                           }
                           if(slot==EquipmentSlot.FEET)
                           {
                              // System.out.println("boots");
                               Negative.setEffect(((IEntityDataSaver) player),true,"boots");
                           }





                   }
               }
               else
               {
                   if(!player.world.isClient)
                   {
                       //System.out.println("release");
                           if(slot==EquipmentSlot.HEAD)
                           {
                               Negative.setEffect(((IEntityDataSaver) player),false,"head");
                           }
                           if(slot==EquipmentSlot.CHEST)
                           {
                               Negative.setEffect(((IEntityDataSaver) player),false,"chest");
                           }
                           if(slot==EquipmentSlot.LEGS)
                           {
                               Negative.setEffect(((IEntityDataSaver) player),false,"legging");
                           }
                           if(slot==EquipmentSlot.FEET)
                           {
                               Negative.setEffect(((IEntityDataSaver) player),false,"boots");
                           }



                   }

               }
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
