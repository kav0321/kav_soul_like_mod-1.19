package net.kav.kav_soul_like.entity.custom;

import net.kav.kav_soul_like.damage_source.ModDamageSource;
import net.kav.kav_soul_like.entity.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import software.bernie.example.registry.EntityRegistry;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class golden_charge_attack extends abstract_charge_attack implements IAnimatable {
    private int explosionPower = 1;
    private LivingEntity shooter;
    private int tick;
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public golden_charge_attack(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
        super(ModEntities.GOLD_CHARGE, world);
        tick=0;
    }
    @Override
    public void tick()
    {
        if(!this.world.isClient())
        {
            if(this.speed==0)
            {
                this.world.createExplosion(null, this.getX(), this.getY(), this.getZ(), this.explosionPower+0.9f, false, Explosion.DestructionType.NONE);
                this.discard();
            }
        }
        super.tick();
    }


    @Override
    public void setVelocity(double x, double y, double z, float speed, float divergence) {
        super.setVelocity(x, y, z, speed, divergence);

    }
    @Override
    protected void onCollision(HitResult hitResult) {
        if (!this.world.isClient) {
            boolean bl = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);

            this.world.createExplosion(null, this.getX(), this.getY(), this.getZ(), this.explosionPower+0.7f, false, Explosion.DestructionType.NONE);
            this.discard();
        }
    }
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.world.isClient) {
            return;
        }
        Entity entity = entityHitResult.getEntity();
        Entity entity2 = this.getOwner();
        entity.damage(ModDamageSource.charge_attack(this, entity2,"goldencharge"), 6.0f);
        if (entity2 instanceof LivingEntity) {
            this.applyDamageEffects((LivingEntity)entity2, entity);
        }
    }
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }



    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
