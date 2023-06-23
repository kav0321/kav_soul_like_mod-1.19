package net.kav.kav_soul_like.damage_source;

import net.kav.kav_soul_like.entity.custom.abstract_charge_attack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import org.jetbrains.annotations.Nullable;

public class ModDamageSource {

    public static DamageSource charge_attack(abstract_charge_attack attack, @Nullable Entity attacker, String name) {

        return new ProjectileDamageSource(name, attack, attacker).setFire().setProjectile();
    }
}
