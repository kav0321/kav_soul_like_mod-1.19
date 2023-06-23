package net.kav.kav_soul_like.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class abstract_charge_attack extends ExplosiveProjectileEntity
        implements FlyingItemEntity {


    public abstract_charge_attack(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public abstract_charge_attack(EntityType<? extends ExplosiveProjectileEntity> entityType, double d, double e, double f, double g, double h, double i, World world) {
        super(entityType, d, e, f, g, h, i, world);
    }

    public abstract_charge_attack(EntityType<? extends ExplosiveProjectileEntity> entityType, LivingEntity livingEntity, double d, double e, double f, World world) {
        super(entityType, livingEntity, d, e, f, world);
    }




    @Override
    protected boolean isBurning() {
        return false;
    }
    @Override
    protected void initDataTracker() {
super.initDataTracker();
    }

    @Override
    public ItemStack getStack() {
        return null;
    }
}
