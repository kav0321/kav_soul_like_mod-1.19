package net.kav.kav_soul_like.Items.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class Echo_StarItem extends Item {
    public Echo_StarItem(Settings settings) {
        super( settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        itemStack.damage(1, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));

        user.getItemCooldownManager().set(this, 20);
            if(!user.isSneaking())
            {
                world.addParticle(ParticleTypes.SONIC_BOOM, user.getPos().getX(), user.getPos().getY()+0.7f, user.getPos().getZ(),2  , 2, 2 );
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,600,10));
                for(LivingEntity entity: getEntitiesNearby(50,e-> e!=user,world,user))
                {



                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING,600));
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,600,2));
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS,600));
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,200));
                }}
            else
            {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,600,10));
                for(LivingEntity entity: getEntitiesNearby(5,e-> e!=user,world,user))
                {


                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,600));
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE,600,2));
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING,600));
                }
            }






        return TypedActionResult.success(itemStack, world.isClient());
    }

    public List<LivingEntity> getEntitiesNearby(double radius, Predicate<LivingEntity> filter, World world, PlayerEntity user)
    {
        return world.getEntitiesByClass(LivingEntity.class,user.getBoundingBox().expand(radius,radius,radius),filter.and(e -> e != user));
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

}
