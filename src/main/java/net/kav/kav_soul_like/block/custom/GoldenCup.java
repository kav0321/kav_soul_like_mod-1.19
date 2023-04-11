package net.kav.kav_soul_like.block.custom;

import net.kav.kav_soul_like.client.gui.LevelUpGui;
import net.kav.kav_soul_like.client.gui.LevelUpScreen;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GoldenCup extends HorizontalFacingBlock {

    public static final int NO_CHARGES = 0;
    public static final int MAX_CHARGES = 1;
    public static final BooleanProperty LIT = Properties.LIT;
    private final int fireDamage=15;
    private final boolean emitsParticles;
    public static final IntProperty CHARGES = Properties.CHARGES;
    public static final BooleanProperty SIGNAL_FIRE = Properties.SIGNAL_FIRE;

    public GoldenCup(Settings settings) {

        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LIT, false));
        emitsParticles=false;

    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (hand == Hand.MAIN_HAND && !this.isChargeItem(itemStack) && this.isChargeItem(player.getStackInHand(Hand.OFF_HAND))) {
            return ActionResult.PASS;
        }
        if (this.isChargeItem(itemStack) && this.canCharge(state)) {
            this.charge(world, pos, state);
            if (!player.getAbilities().creativeMode) {

                itemStack.decrement(1);
            }
            return ActionResult.success(world.isClient);
        }
        if (state.get(CHARGES) == 0) {
            return ActionResult.PASS;
        }

        if(world.isClient)
        {
            MinecraftClient.getInstance().setScreen(new LevelUpScreen(new LevelUpGui()));
        }


        return ActionResult.success(world.isClient);
    }
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (state.get(CHARGES)>0 && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
            entity.damage(DamageSource.IN_FIRE, this.fireDamage);
        }
        super.onEntityCollision(state, world, pos, entity);
    }


    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(CHARGES)<0) {
            return;
        }
        if (random.nextInt(10) == 0) {
            world.playSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5f + random.nextFloat(), random.nextFloat() * 0.7f + 0.6f, false);
        }
        if ( random.nextInt(5) == 0) {
            for (int i = 0; i < random.nextInt(1) + 1; ++i) {
                world.addParticle(ParticleTypes.LAVA, (double)pos.getX() + 0.7, (double)pos.getY() + 0.7, (double)pos.getZ() + 0.7, random.nextFloat() / 2.0f, 5.0E-5, random.nextFloat() / 1.5f);
                if ( random.nextInt(15) == 0) {
                    world.addParticle(ParticleTypes.SONIC_BOOM, (double)pos.getX() + 0.7, (double)pos.getY() + 0.7, (double)pos.getZ() + 0.7, random.nextFloat() / 2.0f, 5.0E-5, random.nextFloat() / 1.5f);
                }

            }
        }
    }
    //custom
    private static boolean isChargeItem(ItemStack stack) {
        return stack.isOf(Items.DIAMOND);
    }
    private static boolean canCharge(BlockState state) {
        return state.get(CHARGES) <1;
    }
    public static void charge(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, (BlockState)((BlockState)state.with(CHARGES, state.get(CHARGES) + 1)).with(LIT,true), Block.NOTIFY_ALL);
       // world.setBlockState(pos, (BlockState)state.with(LIT,true), Block.NOTIFY_ALL);

        world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }
//
    public static final DirectionProperty FACING= Properties.HORIZONTAL_FACING;


    private static VoxelShape SHAPE=Block.createCuboidShape(0,0,0,16,10,16);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return SHAPE;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return RespawnAnchorBlock.getLightLevel(state, 15);
    }


    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        return this.getDefaultState().with(FACING,ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation)
    {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror)
    {
        return  state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected  void appendProperties(StateManager.Builder<Block,BlockState> builder)
    {
        builder.add(FACING);
        builder.add(CHARGES);
        builder.add(LIT);
    }


}
