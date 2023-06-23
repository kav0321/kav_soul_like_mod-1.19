package net.kav.kav_soul_like.entity.client;

import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.entity.custom.golden_charge_attack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class golden_charge_attack_model extends AnimatedGeoModel<golden_charge_attack> {
    @Override
    public Identifier getModelResource(golden_charge_attack object) {
        return new Identifier(Kav_soul_like.MOD_ID,"geo/golden_charge.geo.json");
    }

    @Override
    public Identifier getTextureResource(golden_charge_attack object) {
        return new Identifier(Kav_soul_like.MOD_ID, "textures/entity/golden_charge.png");
    }

    @Override
    public Identifier getAnimationResource(golden_charge_attack animatable) {
        return new Identifier(Kav_soul_like.MOD_ID, "animations/golden.animation.json");
    }
}
