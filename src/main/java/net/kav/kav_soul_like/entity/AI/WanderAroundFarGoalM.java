package net.kav.kav_soul_like.entity.AI;

import net.kav.kav_soul_like.entity.custom.melina_entity;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class WanderAroundFarGoalM
        extends WanderAround {
    public static final float CHANCE = 0.001f;
    protected  float probability;
    protected  float probability2;

    public WanderAroundFarGoalM(melina_entity pathAwareEntity, double d) {
        this(pathAwareEntity, d, 0.001f);
    }

    public WanderAroundFarGoalM(melina_entity mob, double speed, float probability) {
        super(mob, speed);
        this.probability = probability;
        this.probability2 = probability;
    }

    @Override
    @Nullable
    protected Vec3d getWanderTarget() {
        if(this.mob instanceof melina_entity)
        {
            if(((melina_entity) this.mob).customer!=null)
            {
                this.probability=0;
            }
            else
            {
                this.probability=this.probability2;
            }
        }
        if (this.mob.isInsideWaterOrBubbleColumn()) {
            Vec3d vec3d = FuzzyTargeting.find(this.mob, 15, 7);
            return vec3d == null ? super.getWanderTarget() : vec3d;
        }
        if (this.mob.getRandom().nextFloat() >= this.probability) {
            return FuzzyTargeting.find(this.mob, 10, 7);
        }
        return super.getWanderTarget();
    }
}

