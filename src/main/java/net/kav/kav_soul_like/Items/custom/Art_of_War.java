package net.kav.kav_soul_like.Items.custom;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import dev.emi.trinkets.api.TrinketItem;
import net.kav.kav_soul_like.util.IEntityDataSaver;
import net.kav.kav_soul_like.util.playerabilities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class Art_of_War extends TrinketItem {
    private final int index;
    private final String slots;
    public Art_of_War(Settings settings,int index,String slots) {
        super(settings);
        this.index=index;
        this.slots=slots;
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        // +10% movement speed
        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, "kav_soul_like:movement_speed", 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // If the player has access to ring slots, this will give them an extra one

        SlotAttributes.addSlotModifier(modifiers, "hand/ring", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        return modifiers;
    }
    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        playerabilities.addability((IEntityDataSaver) entity,-1,this.slots);
    }
    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
     if(entity instanceof PlayerEntity)
     {
         playerabilities.addability((IEntityDataSaver) entity,this.index,this.slots);
     }
    }
    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        playerabilities.addability((IEntityDataSaver) entity,-1,this.slots);
        return TrinketEnums.DropRule.DEFAULT;
    }

}
