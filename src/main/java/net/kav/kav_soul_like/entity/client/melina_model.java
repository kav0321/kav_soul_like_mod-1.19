package net.kav.kav_soul_like.entity.client;

import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.entity.custom.melina_entity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class melina_model extends AnimatedGeoModel<melina_entity>  {
    @Override
    public Identifier getModelResource(melina_entity object) {
      //  System.out.println(object.getsequence()+ " c");
        if(object.getperma()!=null)
        {
            return new Identifier(Kav_soul_like.MOD_ID,"geo/melina.geo.json");
        }
        return new Identifier(Kav_soul_like.MOD_ID,"geo/melina_2.geo.json");
    }

    @Override
    public Identifier getTextureResource(melina_entity object) {

        return new Identifier(Kav_soul_like.MOD_ID, "textures/entity/melina_eyes.png");
    }

    @Override
    public Identifier getAnimationResource(melina_entity animatable) {
        return new Identifier(Kav_soul_like.MOD_ID, "animations/melina.animation.json");
    }
}
