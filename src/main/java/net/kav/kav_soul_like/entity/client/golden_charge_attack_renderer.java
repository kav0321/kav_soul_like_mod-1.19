package net.kav.kav_soul_like.entity.client;

import net.kav.kav_soul_like.entity.custom.golden_charge_attack;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import software.bernie.example.entity.RocketProjectile;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class golden_charge_attack_renderer extends GeoProjectilesRenderer<golden_charge_attack> {
    public golden_charge_attack_renderer(EntityRendererFactory.Context ctx) {
        super(ctx, new golden_charge_attack_model());
    }
    protected int getBlockLight(golden_charge_attack entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public RenderLayer getRenderType(golden_charge_attack animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
