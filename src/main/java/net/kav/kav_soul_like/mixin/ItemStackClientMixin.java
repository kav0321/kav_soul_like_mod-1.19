package net.kav.kav_soul_like.mixin;


import com.google.common.collect.Lists;
import net.fabricmc.api.Environment;

import net.fabricmc.api.EnvType;
import net.kav.kav_soul_like.util.*;
import net.kav.kav_soul_like.util.item_requirement.weapon_req;
import net.kav.kav_soul_like.util.search.Search;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;

import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;

import net.minecraft.util.Formatting;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;


import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
@Mixin(ItemStack.class)
public abstract class ItemStackClientMixin {
    @Shadow public abstract Stream<TagKey<Item>> streamTags();

    @Shadow private @Nullable BlockPredicatesChecker placeChecker;
    @Unique
    private int itemId;
    @Unique
    private List<Text> tooltipAddition;

    @Inject(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;appendTooltip(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Ljava/util/List;Lnet/minecraft/client/item/TooltipContext;)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void getTooltipMixin(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> info, List<Text> list) {
        if (player != null) {
            ItemStack stack = (ItemStack) (Object) this;
            int itemId = Registry.ITEM.getRawId(stack.getItem());
            if (this.itemId != itemId) {
                this.itemId = itemId;
                String name= Registry.ITEM.getId(stack.getItem()).toString();
                this.tooltipAddition = getAdditionalTooltip(player,name);
            }
            if (this.tooltipAddition != null && !this.tooltipAddition.isEmpty())
                list.addAll(this.tooltipAddition);
        }
    }

    private List<Text> getAdditionalTooltip(PlayerEntity player, String name) {
        List<Text> list = Lists.newArrayList();




        int index;

        index= Search.search(weapon_req.weapon,name);
        if(index!=-1)
        {
            if(weapon_req.weapon.get(index).STRENGTH!=0)
            {
                if(StrengthData.getstrength(((IEntityDataSaver) player))>=weapon_req.weapon.get(index).STRENGTH)
                {
                    list.add(Text.literal( "Strength "+weapon_req.weapon.get(index).STRENGTH).formatted(Formatting.DARK_PURPLE));
                }else
                {
                    list.add(Text.literal( "Strength "+weapon_req.weapon.get(index).STRENGTH).formatted(Formatting.DARK_PURPLE));
                }

            }

            if(weapon_req.weapon.get(index).HEART!=0)
            {
                if(HeartData.getHeart(((IEntityDataSaver) player))>=weapon_req.weapon.get(index).HEART)
                {
                    list.add(Text.literal( "Health "+weapon_req.weapon.get(index).HEART).formatted(Formatting.DARK_PURPLE));
                }else
                {
                    list.add(Text.literal( "Health "+weapon_req.weapon.get(index).HEART).formatted(Formatting.DARK_PURPLE));
                }

            }
            if(weapon_req.weapon.get(index).AGILITY!=0)
            {
                if(AgilityData.getAgility(((IEntityDataSaver) player))>=weapon_req.weapon.get(index).AGILITY)
                {
                    list.add(Text.literal( "Agility "+weapon_req.weapon.get(index).AGILITY).formatted(Formatting.DARK_PURPLE));
                }else
                {
                    list.add(Text.literal( "Agility "+weapon_req.weapon.get(index).AGILITY).formatted(Formatting.DARK_PURPLE));
                }

            }
            if(weapon_req.weapon.get(index).STAMINA!=0)
            {
                if(StaminaLevelData.getStaminaL(((IEntityDataSaver) player))>=weapon_req.weapon.get(index).STAMINA)
                {
                    list.add(Text.literal( "Stamina "+weapon_req.weapon.get(index).STAMINA).formatted(Formatting.DARK_PURPLE));
                }else
                {
                    list.add(Text.literal( "Stamina "+weapon_req.weapon.get(index).STAMINA).formatted(Formatting.DARK_PURPLE));
                }

            }
            if(weapon_req.weapon.get(index).DEFENCE!=0)
            {
                if(DefenceData.getDefence(((IEntityDataSaver) player))>=weapon_req.weapon.get(index).DEFENCE)
                {
                    list.add(Text.literal( "Defence "+weapon_req.weapon.get(index).DEFENCE).formatted(Formatting.DARK_PURPLE));
                }else
                {
                    list.add(Text.literal( "Defence "+weapon_req.weapon.get(index).DEFENCE).formatted(Formatting.DARK_PURPLE));
                }

            }


        }
        if(index == -1) {
             index= Search.search(weapon_req.armorList,name);
             if(index!=-1)
             {
                 if(weapon_req.armorList.get(index).STRENGTH!=0)
                 {
                     if(StrengthData.getstrength(((IEntityDataSaver) player))>=weapon_req.armorList.get(index).STRENGTH)
                     {
                         list.add(Text.literal( "Strength "+weapon_req.armorList.get(index).STRENGTH).formatted(Formatting.DARK_PURPLE));
                     }else
                     {
                         list.add(Text.literal( "Strength "+weapon_req.armorList.get(index).STRENGTH).formatted(Formatting.DARK_PURPLE));
                     }

                 }

                 if(weapon_req.armorList.get(index).HEART!=0)
                 {
                     if(HeartData.getHeart(((IEntityDataSaver) player))>=weapon_req.armorList.get(index).HEART)
                     {
                         list.add(Text.literal( "Health "+weapon_req.armorList.get(index).HEART).formatted(Formatting.DARK_PURPLE));
                     }else
                     {
                         list.add(Text.literal( "Health "+weapon_req.armorList.get(index).HEART).formatted(Formatting.DARK_PURPLE));
                     }

                 }
                 if(weapon_req.armorList.get(index).AGILITY!=0)
                 {
                     if(AgilityData.getAgility(((IEntityDataSaver) player))>=weapon_req.armorList.get(index).AGILITY)
                     {
                         list.add(Text.literal( "Agility "+weapon_req.armorList.get(index).AGILITY).formatted(Formatting.DARK_PURPLE));
                     }else
                     {
                         list.add(Text.literal( "Agility "+weapon_req.armorList.get(index).AGILITY).formatted(Formatting.DARK_PURPLE));
                     }

                 }
                 if(weapon_req.armorList.get(index).STAMINA!=0)
                 {
                     if(StaminaLevelData.getStaminaL(((IEntityDataSaver) player))>=weapon_req.armorList.get(index).STAMINA)
                     {
                         list.add(Text.literal( "Stamina "+weapon_req.armorList.get(index).STAMINA).formatted(Formatting.DARK_PURPLE));
                     }else
                     {
                         list.add(Text.literal( "Stamina "+weapon_req.armorList.get(index).STAMINA).formatted(Formatting.DARK_PURPLE));
                     }

                 }
                 if(weapon_req.armorList.get(index).DEFENCE!=0)
                 {
                     if(DefenceData.getDefence(((IEntityDataSaver) player))>=weapon_req.armorList.get(index).DEFENCE)
                     {
                         list.add(Text.literal( "Defence "+weapon_req.armorList.get(index).DEFENCE).formatted(Formatting.DARK_PURPLE));
                     }else
                     {
                         list.add(Text.literal( "Defence "+weapon_req.armorList.get(index).DEFENCE).formatted(Formatting.DARK_PURPLE));
                     }

                 }
             }
             else
             {
                 index= Search.search(weapon_req.shield,name);
                 if(index!=-1)
                 {

                     if(weapon_req.shield.get(index).STRENGTH!=0)
                     {
                         if(StrengthData.getstrength(((IEntityDataSaver) player))>=weapon_req.shield.get(index).STRENGTH)
                         {
                             list.add(Text.literal( "Strength "+weapon_req.shield.get(index).STRENGTH).formatted(Formatting.DARK_PURPLE));
                         }else
                         {
                             list.add(Text.literal( "Strength "+weapon_req.shield.get(index).STRENGTH).formatted(Formatting.DARK_PURPLE));
                         }

                     }

                     if(weapon_req.shield.get(index).HEART!=0)
                     {
                         if(HeartData.getHeart(((IEntityDataSaver) player))>=weapon_req.shield.get(index).HEART)
                         {
                             list.add(Text.literal( "Health "+weapon_req.shield.get(index).HEART).formatted(Formatting.DARK_PURPLE));
                         }else
                         {
                             list.add(Text.literal( "Health "+weapon_req.shield.get(index).HEART).formatted(Formatting.DARK_PURPLE));
                         }

                     }
                     if(weapon_req.shield.get(index).AGILITY!=0)
                     {
                         if(AgilityData.getAgility(((IEntityDataSaver) player))>=weapon_req.shield.get(index).AGILITY)
                         {
                             list.add(Text.literal( "Agility "+weapon_req.shield.get(index).AGILITY).formatted(Formatting.DARK_PURPLE));
                         }else
                         {
                             list.add(Text.literal( "Agility "+weapon_req.shield.get(index).AGILITY).formatted(Formatting.DARK_PURPLE));
                         }

                     }
                     if(weapon_req.shield.get(index).STAMINA!=0)
                     {
                         if(StaminaLevelData.getStaminaL(((IEntityDataSaver) player))>=weapon_req.shield.get(index).STAMINA)
                         {
                             list.add(Text.literal( "Stamina "+weapon_req.shield.get(index).STAMINA).formatted(Formatting.DARK_PURPLE));
                         }else
                         {
                             list.add(Text.literal( "Stamina "+weapon_req.shield.get(index).STAMINA).formatted(Formatting.DARK_PURPLE));
                         }

                     }
                     if(weapon_req.shield.get(index).DEFENCE!=0)
                     {
                         if(DefenceData.getDefence(((IEntityDataSaver) player))>=weapon_req.shield.get(index).DEFENCE)
                         {
                             list.add(Text.literal( "Defence "+weapon_req.shield.get(index).DEFENCE).formatted(Formatting.DARK_PURPLE));
                         }else
                         {
                             list.add(Text.literal( "Defence "+weapon_req.shield.get(index).DEFENCE).formatted(Formatting.DARK_PURPLE));
                         }

                     }
                 }

             }

        }

    return list;
    }

}
