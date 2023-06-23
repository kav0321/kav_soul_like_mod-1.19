package net.kav.kav_soul_like.entity.client;

import com.google.common.collect.Lists;
import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.entity.custom.melina_entity;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.Vec3f;
import org.jetbrains.annotations.Nullable;
import software.bernie.example.client.DefaultBipedBoneIdents;
import software.bernie.example.entity.ExtendedRendererEntity;
import software.bernie.geckolib3.ArmorRenderingRegistryImpl;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.EModelRenderCycle;
import software.bernie.geckolib3.util.RenderUtils;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

import java.util.List;

public class melina_renderer extends GeoEntityRenderer<melina_entity> {


    protected ItemStack mainHandItem, offHandItem, helmetItem, chestplateItem, leggingsItem, bootsItem;
    public melina_renderer(EntityRendererFactory.Context ctx) {
        super(ctx, new melina_model());

        this.shadowRadius = 0.4f;


    }



    public Identifier getTextureResource(melina_entity instance) {
        return new Identifier(Kav_soul_like.MOD_ID, "textures/entity/melina_eyes.png");
    }

    @Override
    public RenderLayer getRenderType(melina_entity entity, float partialticks, MatrixStack stack, VertexConsumerProvider rendertypebuffer, VertexConsumer vertexConsumer, int packedlightIn, Identifier texturelocate)
    {
        stack.scale(1.1f,1.1f,1.1f);


        return super.getRenderType(entity,partialticks,stack,rendertypebuffer,vertexConsumer,packedlightIn,texturelocate);
    }


}
