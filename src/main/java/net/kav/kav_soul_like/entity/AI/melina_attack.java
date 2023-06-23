package net.kav.kav_soul_like.entity.AI;

import net.kav.kav_soul_like.entity.ModEntities;
import net.kav.kav_soul_like.entity.custom.golden_charge_attack;
import net.kav.kav_soul_like.entity.custom.melina_entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;
import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class melina_attack extends Goal {
    protected final melina_entity mob;
    private final double speed;
    private final boolean pauseWhenMobIdle;
    private Path path;
    private double targetX;
    private double targetY;
    private double targetZ;
    private int updateCountdownTicks;
    private int cooldown;
    private final int attackIntervalTicks = 20;
    private long lastUpdateTime;
    private static final long MAX_ATTACK_TIME = 20L;
    public melina_attack(melina_entity mob, double speed, boolean pauseWhenMobIdle)
    {
        this.mob = mob;
        this.speed = speed;
        this.pauseWhenMobIdle = pauseWhenMobIdle;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart() {
        long l = this.mob.world.getTime();
        if (l - this.lastUpdateTime < 20L) {
            return false;
        }
        this.lastUpdateTime = l;
        LivingEntity livingEntity = this.mob.getTarget();
        if (livingEntity == null) {
            return false;
        }
        if (!livingEntity.isAlive()) {
            return false;
        }
        this.path = this.mob.getNavigation().findPathTo(livingEntity, 0);
        if (this.path != null) {
            return true;
        }
        return this.getSquaredMaxAttackDistance(livingEntity) >= this.mob.squaredDistanceTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
    }
    @Override
    public boolean shouldContinue() {
        LivingEntity livingEntity = this.mob.getTarget();
        if (livingEntity == null) {
            return false;
        }
        if (!livingEntity.isAlive()) {
            return false;
        }
        if (!this.pauseWhenMobIdle) {
            return !this.mob.getNavigation().isIdle();
        }
        if (!this.mob.isInWalkTargetRange(livingEntity.getBlockPos())) {
            return false;
        }
        return !(livingEntity instanceof PlayerEntity) || !livingEntity.isSpectator() && !((PlayerEntity)livingEntity).isCreative();
    }
    @Override
    public void start() {
        LivingEntity livingEntity = this.mob.getTarget();
        this.updateCountdownTicks = 0;
        this.cooldown = 0;

        if(this.mob.getsequence()==0)
        {
            this.mob.setsequence(2);
        }
        Random random = new Random();
        if (this.mob.getsequence() == 2||sqrt(pow(this.mob.getX()-this.mob.getTarget().getX(),2)+pow(this.mob.getY()-this.mob.getTarget().getY(),2)+pow(this.mob.getZ()-this.mob.getTarget().getZ(),2))>10) {
        int r=random.nextInt(0,5);
            if(r==5)
            {
                this.mob.setsequence(5);
            }
            else if(r==3)
            {
                this.mob.setsequence(6);
            }
            else
            {
                this.mob.setsequence(4);
            }
        } else if (this.mob.getsequence() == 4) {
            if(random.nextInt(0,10)>=5)
            {
                this.mob.setsequence(random.nextInt(3)*2+1);
            }
            else
            {
                this.mob.setsequence(2);
            }

        } else if (this.mob.getsequence() == 1 || this.mob.getsequence() == 3) {
            if (random.nextBoolean()) {
                this.mob.setsequence( 5);
            } else {
                this.mob.setsequence(random.nextInt(2) + 1);
            }
        } else if (this.mob.getsequence() == 5) {
            this.mob.setsequence(6);
        } else if (this.mob.getsequence() == 6) {
            this.mob.setsequence(random.nextInt(4) + 1);
        }
        this.mob.getLookControl().lookAt(livingEntity, 30.0f, 30.0f);
        this.mob.setAttacking(true);
    }
    @Override
    public void stop() {
        LivingEntity livingEntity = this.mob.getTarget();
        if (!EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(livingEntity)) {
            this.mob.setTarget(null);

        }
        this.mob.setAttacking(false);

        this.mob.getNavigation().stop();
    }
    @Override
    public void tick() {

        LivingEntity livingEntity = this.mob.getTarget();
        if (livingEntity == null) {
            return;
        }
        this.mob.getLookControl().lookAt(livingEntity, 30.0f, 30.0f);
        double d = this.mob.squaredDistanceTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
        this.updateCountdownTicks = Math.max(this.updateCountdownTicks - 1, 0);
        if ((this.pauseWhenMobIdle || this.mob.getVisibilityCache().canSee(livingEntity)) && this.updateCountdownTicks <= 0 && (this.targetX == 0.0 && this.targetY == 0.0 && this.targetZ == 0.0 || livingEntity.squaredDistanceTo(this.targetX, this.targetY, this.targetZ) >= 1.0 || this.mob.getRandom().nextFloat() < 0.05f)) {
            this.targetX = livingEntity.getX();
            this.targetY = livingEntity.getY();
            this.targetZ = livingEntity.getZ();
            this.updateCountdownTicks = 4 + this.mob.getRandom().nextInt(7);
            if (d > 1024.0) {
                this.updateCountdownTicks += 10;
            } else if (d > 256.0) {
                this.updateCountdownTicks += 5;
            }

                if(this.mob.getsequence()==1||this.mob.getsequence()==3)
                {
                    if (!this.mob.getNavigation().startMovingTo(livingEntity, this.speed)) {
                        this.updateCountdownTicks += 15;
                    }
                }

            if(this.mob.getsequence()==5||this.mob.getsequence()==6)
            {
                if(this.distance(this.mob,this.mob.getTarget())>10)
                {
                    if (!this.mob.getNavigation().startMovingTo(livingEntity, this.speed+0.4)) {
                        this.updateCountdownTicks += 15;
                    }
                }
                else
                {
                    if (!this.mob.getNavigation().startMovingTo(livingEntity, this.speed)) {
                        this.updateCountdownTicks += 15;
                    }
                }

            }


            this.updateCountdownTicks = this.getTickCount(this.updateCountdownTicks);
        }
        this.cooldown = Math.max(this.cooldown - 1, 0);
        this.attack(livingEntity, d);
    }
    protected void attack(LivingEntity target, double squaredDistance) {


        double d = this.getSquaredMaxAttackDistance(target);
        if ((squaredDistance <= d && this.cooldown <= 0)||((this.mob.getsequence()==2||this.mob.getsequence()==4)&&this.cooldown <= 0)) {
            Vec3d vec3d = this.mob.getRotationVec(1.0f);
            double f = target.getX() - (mob.getX() + vec3d.x * 4.0);
            double g = target.getBodyY(1) - (0.5 + mob.getBodyY(1));
            double h = target.getZ() - (this.mob.getZ() + vec3d.z * 4.0);

            this.mob.getLookControl().lookAt(target, 30.0f, 30.0f);
            if(target instanceof PlayerEntity)
            {
                if(this.mob.getperma()!=target.getUuid())
                {
                    this.mob.setperma(target.getUuid());
                }
            }




            if(this.mob.getsequence()==2||this.mob.getsequence()==4)//
            {

                golden_charge_attack  golde_charge = ModEntities.GOLD_CHARGE.create(this.mob.world);

                golde_charge.setPosition(this.mob.getX() + vec3d.x * 4.0, this.mob.getBodyY(0.5) + 0.7, mob.getZ() + vec3d.z * 4.0);;
                golde_charge.setVelocity(f,g,h,5,1);

                this.mob.world.spawnEntity(golde_charge);

                this.resetCooldown();
                this.mob.swingHand(Hand.MAIN_HAND);
                return;
            }
            if(this.mob.getsequence()==5||this.mob.getsequence()==6)
            {
                this.mob.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_KNOCKBACK).setBaseValue(7);
                for (LivingEntity entity : this.mob.getEntitiesNearby(5, e -> (e instanceof LivingEntity)))
                {
                    this.mob.tryAttack(entity);
                    entity.setSwimming(true);
                }
            }
            else
            {
                this.mob.tryAttack(target);

            }
            this.resetCooldown();
            this.mob.swingHand(Hand.MAIN_HAND);
            this.mob.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_KNOCKBACK).setBaseValue(0.1);


        }
    }



    protected void resetCooldown() {
        this.cooldown = this.getTickCount(100);
    }
    protected double getSquaredMaxAttackDistance(LivingEntity entity) {
        return this.mob.getWidth() * 2.0f * (this.mob.getWidth() * 2.0f) + entity.getWidth();
    }
    protected double distance(LivingEntity p1, LivingEntity p2)
    {
        return sqrt(pow(p1.getX()-p2.getX(),2)+pow(p1.getY()-p2.getY(),2)+pow(p1.getZ()-p2.getZ(),2));
    }

}
