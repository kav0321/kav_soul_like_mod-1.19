package net.kav.kav_soul_like.entity.AI;

import net.kav.kav_soul_like.entity.custom.melina_entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class LookAtTargetGoal
        extends Goal {

    protected final PathAwareEntity livingEntity;
    public LookAtTargetGoal(PathAwareEntity livingEntity) {
        this.livingEntity = livingEntity;
        this.setControls(EnumSet.of(Goal.Control.LOOK));
    }

    @Override
    public boolean canStart() {

        return true;
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (this.livingEntity.getTarget() == null) {
            Vec3d vec3d = this.livingEntity.getVelocity();
            this.livingEntity.setYaw(-((float) MathHelper.atan2(vec3d.x, vec3d.z)) * 57.295776f);
            this.livingEntity.bodyYaw = this.livingEntity.getYaw();
        } else {
            LivingEntity livingEntity = this.livingEntity.getTarget();
            double d = 64.0;
            if (livingEntity.squaredDistanceTo(this.livingEntity) < 4096.0) {
                double e = livingEntity.getX() - this.livingEntity.getX();
                double f = livingEntity.getZ() - this.livingEntity.getZ();
                this.livingEntity.setYaw(-((float)MathHelper.atan2(e, f)) * 57.295776f);
                this.livingEntity.bodyYaw = this.livingEntity.getYaw();
            }
        }
    }
}
