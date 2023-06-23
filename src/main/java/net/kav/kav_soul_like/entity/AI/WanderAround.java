package net.kav.kav_soul_like.entity.AI;

import net.kav.kav_soul_like.entity.custom.melina_entity;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class WanderAround
        extends Goal {
    public static final int DEFAULT_CHANCE = 120;
    protected final melina_entity mob;
    protected double targetX;
    protected double targetY;
    protected double targetZ;
    protected final double speed;
    protected int chance;
    protected boolean ignoringChance;
    private final boolean canDespawn;

    public WanderAround(melina_entity mob, double speed) {
        this(mob, speed, 120);
    }

    public WanderAround(melina_entity mob, double speed, int chance) {
        this(mob, speed, chance, false);
    }

    public WanderAround(melina_entity entity, double speed, int chance, boolean canDespawn) {
        this.mob = entity;
        this.speed = speed;
        this.chance = chance;
        this.canDespawn = false;
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if(this.mob.customer!=null)
        {
            return false;
        }
        Vec3d vec3d;
        if (this.mob.hasPassengers()) {
            return false;
        }
        if (!this.ignoringChance) {
            if (this.canDespawn && this.mob.getDespawnCounter() >= 100) {
                return false;
            }
            if (this.mob.getRandom().nextInt(net.minecraft.entity.ai.goal.WanderAroundGoal.toGoalTicks(this.chance)) != 0) {
                return false;
            }
        }
        if ((vec3d = this.getWanderTarget()) == null) {
            return false;
        }
        this.targetX = vec3d.x;
        this.targetY = vec3d.y;
        this.targetZ = vec3d.z;
        this.ignoringChance = false;
        return true;
    }

    @Nullable
    protected Vec3d getWanderTarget() {
        return NoPenaltyTargeting.find(this.mob, 10, 7);
    }

    @Override
    public boolean shouldContinue() {
        return !this.mob.getNavigation().isIdle() && !this.mob.hasPassengers();
    }

    @Override
    public void start() {
        this.mob.getNavigation().startMovingTo(this.targetX, this.targetY, this.targetZ, this.speed);
    }

    @Override
    public void stop() {
        this.mob.getNavigation().stop();
        super.stop();
    }

    public void ignoreChanceOnce() {
        this.ignoringChance = true;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }
}