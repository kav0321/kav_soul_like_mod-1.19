package net.kav.kav_soul_like.entity.AI;

import net.kav.kav_soul_like.entity.custom.melina_entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;

import java.util.EnumSet;

public class Lookaroundmelina
        extends Goal {
    private final melina_entity mob;
    private double deltaX;
    private double deltaZ;
    private int lookTime;

    public Lookaroundmelina(melina_entity mob) {
        this.mob = mob;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if(this.mob.customer!=null)
        {
            return this.mob.getRandom().nextFloat() < 0.02f;
        }
        else
        {
            return false;
        }

    }

    @Override
    public boolean shouldContinue() {
        if(this.mob.customer!=null)
        {
            return this.lookTime >= 0;
        }
        else
        {
            return false;
        }

    }

    @Override
    public void start() {
        double d = Math.PI * 2 * this.mob.getRandom().nextDouble();
        this.deltaX = Math.cos(d);
        this.deltaZ = Math.sin(d);
        this.lookTime = 20 + this.mob.getRandom().nextInt(20);
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        --this.lookTime;
        this.mob.getLookControl().lookAt(this.mob.getX() + this.deltaX, this.mob.getEyeY(), this.mob.getZ() + this.deltaZ);
    }
}
