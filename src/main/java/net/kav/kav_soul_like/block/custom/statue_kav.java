package net.kav.kav_soul_like.block.custom;

import net.kav.kav_soul_like.block.entity.ModBlockEntities;
import net.kav.kav_soul_like.block.entity.statue_kav_entity;
import net.kav.kav_soul_like.util.DisplayLevel;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
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
import net.minecraft.server.world.ServerWorld;
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
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class statue_kav extends BlockWithEntity implements  BlockEntityProvider  {


    public static final BooleanProperty LIT = Properties.LIT;
    private final int fireDamage=15;
    private final boolean emitsParticles;
    private static final int SCHEDULED_TICK_DELAY = 20;
    //public static final IntProperty STAGE = IntProperty.of("stage", 0, 1);
    public static final IntProperty MOOD =  IntProperty.of("stage", 0, 15);
    public static final BooleanProperty SIGNAL_FIRE = Properties.SIGNAL_FIRE;
    public static HashMap<Integer,String> mood= new HashMap<Integer,String>();
    public static HashMap<String,Text> text= new HashMap<String,Text>();

    public statue_kav(Settings settings) {

        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(MOOD, 1));

        mood.put(1,"greeting");
        mood.put(2,"someone");
        mood.put(3,"someone_2");
        mood.put(4,"help");
        mood.put(5,"help_2");

        mood.put(6,"item_needed");
        mood.put(7,"where_item");
        mood.put(8,"where_item_2");
        mood.put(9,"plead");
        mood.put(10,"stop");
        mood.put(11,"angry");
        mood.put(12,"very angry");

        text.put("greeting",Text.translatable("kav_soul_like.hello"));
        text.put("someone",Text.translatable("kav_soul_like.someone"));
        text.put("someone_2",Text.translatable("kav_soul_like.someone_2"));
        text.put("help",Text.translatable("kav_soul_like.help"));
        text.put("help_2",Text.translatable("kav_soul_like.help_2"));
        text.put("item_needed",Text.translatable("kav_soul_like.item_needed"));
        text.put("where_item",Text.translatable("kav_soul_like.where_item"));
        text.put("where_item_2",Text.translatable("kav_soul_like.where_item_2"));
        text.put("plead",Text.translatable("kav_soul_like.plead"));
        text.put("stop",Text.translatable("kav_soul_like.stop"));
        text.put("angry",Text.translatable("kav_soul_like.angry"));
        text.put("very angry",Text.translatable("kav_soul_like.very_angry"));

        emitsParticles=false;

    }
    int tick2=0;

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);

       /* if(world.isClient)
        {
            DisplayLevel.Gui();
        }*/

        if(!world.isClient)
        {
            mood(world,pos,state,state.get(MOOD),player);
        }
        return ActionResult.success(world.isClient);
    }
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world,pos,state,player);
        if(!world.isClient())
        {
            mood(world,pos,state,12,player);
        }


    }
    int tick;
    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        super.onBlockBreakStart(state,world,pos,player);
       if(!world.isClient())
       {
           //player.sendMessage(text.get(mood.get(6)),true);
           //System.out.println(tick);
           tick++;
         if(new java.util.Random().nextInt(0,2)==1)
         {
             mood(world,pos,state,9,player);
         }
         else if(new java.util.Random().nextInt(0,2)==2)
         {
             mood(world,pos,state,10,player);
         }
         else
         {
             mood(world,pos,state,11,player);
         }

       }
    }


    //custom

    public static void mood(World world, BlockPos pos, BlockState state,int moods, PlayerEntity player) {

        if(moods==0 ||moods>11)
        {
            moods=1;
        }


        player.sendMessage(text.get(mood.get(moods)),true);
        if(moods<6)
        {
            world.setBlockState(pos,((BlockState)state.with(MOOD,moods +1)), Block.NOTIFY_ALL);

        }
       else if(moods==6)
        {
            System.out.println("asa");
            world.setBlockState(pos,((BlockState)state.with(MOOD,moods+1)), Block.NOTIFY_ALL);
        }
       else if(moods==7)
        {
            world.setBlockState(pos,((BlockState)state.with(MOOD,moods+1)), Block.NOTIFY_ALL);
        }
        else if(moods==8)
        {
            world.setBlockState(pos,((BlockState)state.with(MOOD,moods-2)), Block.NOTIFY_ALL);
        }
        else if(moods>8&&moods<12)
        {
            world.setBlockState(pos,((BlockState)state.with(MOOD,moods)), Block.NOTIFY_ALL);
        }
        if(moods==12) {
            player.sendMessage(text.get(mood.get(moods)),true);
        }
       // world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }
//
    public static final DirectionProperty FACING= Properties.HORIZONTAL_FACING;


    private static VoxelShape SHAPE=Block.createCuboidShape(0,0,0,16,4,16);
    private static VoxelShape SHAPE2=Block.createCuboidShape(1,4,1,15,32,15);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return VoxelShapes.union(SHAPE,SHAPE2);

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
        builder.add(MOOD);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.createAndScheduleBlockTick(pos, this, -1, TickPriority.byIndex(0));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        //super.scheduledTick(state,world,pos,random);
        System.out.println("saaaa");
        if(!world.isClient)
        { System.out.println("saaa");
            for(ServerPlayerEntity player: world.getServer().getPlayerManager().getPlayerList())
            { System.out.println("saa");

                if(sqrt((pow(player.getPos().x-pos.getX(),2))+(pow(player.getPos().y-pos.getY(),2))+(pow(player.getPos().z-pos.getZ(),2)))<=10)
                {
                    System.out.println(state.get(MOOD));
                    if(state.get(MOOD)>=0 && state.get(MOOD)<=3)
                    {
                        mood(world,pos,state,state.get(MOOD),player);
                    }

                }
            }
        }
    }
/*BLOCK ENTITY*/
    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return  BlockRenderType.MODEL;
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new statue_kav_entity(pos,state);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        //System.out.println("hello");

        return checkType(type, ModBlockEntities.STATUE, statue_kav_entity::tick);
    }
}
