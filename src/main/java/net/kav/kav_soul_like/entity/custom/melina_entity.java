package net.kav.kav_soul_like.entity.custom;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.kav_soul_like.client.gui.Interaction_screen;
import net.kav.kav_soul_like.client.gui.melina_screen;
import net.kav.kav_soul_like.entity.AI.*;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.networking.packet.Packets;
import net.kav.kav_soul_like.util.DisplayText;
import net.kav.kav_soul_like.util.IEntityDataSaver;
import net.kav.kav_soul_like.util.screennbt;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.ServerConfigHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

public class melina_entity extends PathAwareEntity implements IAnimatable, Angerable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    @Nullable
    private UUID angryAt;
    private int angerTime;
    protected static final TrackedData<Integer> context = DataTracker.registerData(melina_entity.class, TrackedDataHandlerRegistry.INTEGER);
    protected static final TrackedData<Integer> lore = DataTracker.registerData(melina_entity.class, TrackedDataHandlerRegistry.INTEGER);
    @Nullable
    public PlayerEntity customer;
    protected static final TrackedData<Integer> attacksequence = DataTracker.registerData(melina_entity.class, TrackedDataHandlerRegistry.INTEGER);
    protected static final TrackedData<Optional<UUID>> PERMANENTUUID = DataTracker.registerData(melina_entity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(60, 120);
    public melina_entity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        ItemStack itemStack = new ItemStack(Items.IRON_SWORD);
        this.equipStack(EquipmentSlot.MAINHAND, itemStack);

    }
    public static DefaultAttributeContainer.Builder setAttributes() {
        return PathAwareEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 180.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.1f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f);
    }
    @Override
    public ActionResult interactMob(PlayerEntity player2, Hand hand) {
        if(this.world.isClient())
        {
            if(this.getTarget()==null)
            {

                DisplayText.Gui(this.getcontext(),this.getlore());
            }

        }
      if(!this.world.isClient())
        {
            this.getLookControl().lookAt(player2, 180.0f, 180.0f);


            if(player2!=this.getTarget())
            {

                screennbt.setScreen((IEntityDataSaver) player2,true);
                PacketByteBuf buf= PacketByteBufs.create();
                buf.writeBoolean(true);
                ServerPlayNetworking.send((ServerPlayerEntity) player2,ModMessages.screen,buf);
                PacketByteBuf buf2= PacketByteBufs.create();
                buf2.writeInt(this.getId());
                screennbt.setid((IEntityDataSaver) player2,this.getId());
                ServerPlayNetworking.send((ServerPlayerEntity) player2,ModMessages.id,buf2);
            }

        }
        this.customer=player2;
        return super.interactMob(player2, hand);
    }



    @Override
    protected void initGoals()
    {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new melina_attack(this, 0.8D, false));
        this.goalSelector.add(3, new WanderAroundFarGoalM(this, 0.5f, 120));
        this.goalSelector.add(4, new Lookaroundmelina(this));
        this.goalSelector.add(7, new LookAtTargetGoal(this));
        this.targetSelector.add(4, new UniversalAngerGoal<melina_entity>(this, false));
        this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
        this.targetSelector.add(3, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, 100, true, false, this::shouldAngerAt));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, ZombieEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, MerchantEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, ChickenEntity.class, true));
    }
    /*equipment*/


    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(attacksequence, 0);
        this.dataTracker.startTracking(context, 1
        );
        this.dataTracker.startTracking(lore, 1);
        this.dataTracker.startTracking(PERMANENTUUID,Optional.empty() );
    }

    String animation;
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if(this.handSwinging)
        {
            if(animation=="attack")
            {
                if(event.getController().getAnimationState().equals(AnimationState.Stopped))
                {

                    event.getController().markNeedsReload();

                    if(getsequence()==1)
                    {
                        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.attack",ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                    }
                    else if(getsequence()==2)
                    {
                        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.attack_2",ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                    }
                    else if(getsequence()==3)
                    {
                        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.attack_3",ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                    }else if(getsequence()==4)
                    {
                        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.attack4",ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                    }else if(getsequence()==5)
                    {
                        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.attack5",ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                    }else if(getsequence()==6)
                    {
                        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.attack6",ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                    }
                    this.handSwinging = false;
                    animation="attack";

                }

            }
            else
            {
                event.getController().markNeedsReload();

                if(getsequence()==1)
                {

                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.attack",ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                }
                else if(getsequence()==2)
                {

                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.attack_2",ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                }
                else if(getsequence()==3)
                {

                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.attack_3",ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                }else if(getsequence()==4)
                {

                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.attack4",ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                }else if(getsequence()==5)
                {

                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.attack5",ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                }else if(getsequence()==6)
                {

                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.attack6",ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                }
                this.handSwinging = false;
                animation="attack";
            }
            return PlayState.CONTINUE;

        }


        if (event.isMoving()) {
            if(animation=="attack" )
            {
                if(event.getController().getAnimationState().equals(AnimationState.Stopped))
                {
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.walking", ILoopType.EDefaultLoopTypes.LOOP));
                    animation="walk";
                }
            }
            else
            {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.walking", ILoopType.EDefaultLoopTypes.LOOP));
                animation="walk";
            }

            return PlayState.CONTINUE;
        }
        else
        {   if(animation=="attack")
            {
                if(event.getController().getAnimationState().equals(AnimationState.Stopped))
                {
                    event.getController().markNeedsReload();
                    animation="idle";
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.idle", ILoopType.EDefaultLoopTypes.LOOP));
                }
            }
            else
            {
            event.getController().markNeedsReload();
            animation="idle";
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.melina.idle", ILoopType.EDefaultLoopTypes.LOOP));
            }


        }





        return PlayState.CONTINUE;
    }
    public int getsequence()
    {
       return this.dataTracker.get(attacksequence);
    }
    public int getcontext()
    {
        return this.dataTracker.get(context);
    }
    public int getlore()
    {
        return this.dataTracker.get(lore);
    }
    @Nullable
    public UUID getperma() {
        return this.dataTracker.get(PERMANENTUUID).orElse(null);
    }
    public void setperma(@Nullable UUID uuid) {
        this.dataTracker.set(PERMANENTUUID, Optional.ofNullable(uuid));
    }
    public void setsequence(int amount)
    {
         this.dataTracker.set(attacksequence,amount);
    }
    public void setContext(int index)
    {
        this.dataTracker.set(context,index);
    }
    public void setLore(int index)
    {
        this.dataTracker.set(lore,index);
    }

    private PlayState idlePredicate(AnimationEvent event)
    {


        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {

        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
        animationData.addAnimationController(new AnimationController(this, "controller_idle",
                0, this::idlePredicate));



    }
    int cooldown=0;
    boolean a;
@Override
public void tick()
{
    super.tick();
if(!this.world.isClient())
{


    if(this.getperma()!=null)
    {
        if(new Random().nextInt(0,10)==1 && this.getTarget()==null)
        {
            this.setperma(null);

        }
        for (LivingEntity entity : getEntitiesNearby(15, e -> (e instanceof PlayerEntity)))
        {
            if(entity.getUuid()==this.getperma())
            {
                if(entity instanceof PlayerEntity)
                {
                    if(!((PlayerEntity) entity).isCreative()||!entity.isSpectator())
                    {
                        this.setTarget(entity);
                    }
                }

            }
        }

    }
    if(this.customer!=null)
    {

        for (LivingEntity entity : getEntitiesNearby(55, e -> (e instanceof PlayerEntity)))
        {

            if(entity==this.customer)
            {
                return;
            }
        }
        this.customer=null;
    }


}

}

    public List<LivingEntity> getEntitiesNearby(double radius, Predicate<LivingEntity> filter)
    {
        return this.world.getEntitiesByClass(LivingEntity.class, getBoundingBox().expand(radius), filter.and(e -> e != this));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


    /*Sounds Events*/
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_DOLPHIN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_DOLPHIN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PIG_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15f, 1.0f);
    }

    /*anger*/
    @Override
    public int getAngerTime() {
        return this.angerTime;
    }

    @Override
    public void setAngerTime(int angerTime) {
            this.angerTime=angerTime;
    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return this.angryAt;
    }

    @Override
    public void setAngryAt(@Nullable UUID angryAt) {
            this.angryAt=angryAt;
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    /*nbt*/
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if(this.getperma()!=null)
        {
            nbt.putUuid("perma", this.getperma());
        }

        this.writeAngerToNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        UUID uUID;
        if (nbt.containsUuid("perma")) {
            uUID = nbt.getUuid("perma");
        }
        else {
            String string = nbt.getString("perma");
            uUID = ServerConfigHandler.getPlayerUuidByName(this.getServer(), string);
        }

        if (uUID != null) {
            try {
                this.setperma(uUID);

            } catch (Throwable throwable) {

            }
        }
        this.readAngerFromNbt(this.world, nbt);
    }
}
