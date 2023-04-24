package net.kav.kav_soul_like.block.entity;

import net.kav.kav_soul_like.block.custom.statue_kav;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static net.kav.kav_soul_like.block.custom.statue_kav.MOOD;

public class statue_kav_entity extends BlockEntity implements ImplementedInventory {
    public static int tick_kav=0;

    public statue_kav_entity( BlockPos pos, BlockState state) {
        super(ModBlockEntities.STATUE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return null;
    }
    public static void tick(World world, BlockPos pos, BlockState state, statue_kav_entity entity) {
        tick_kav++;
        if(tick_kav>120)
        {
            tick_kav=0;
        }
        if(!world.isClient())
        {

            //System.out.println("saaaa");
            if(!world.isClient)
            { //System.out.println("saaa");
                for(ServerPlayerEntity player: world.getServer().getPlayerManager().getPlayerList())
                {// System.out.println("saa");
                    //System.out.println(tick_kav);
                   // System.out.println(state.get(MOOD));
                    if(sqrt((pow(player.getPos().x-pos.getX(),2))+(pow(player.getPos().y-pos.getY(),2))+(pow(player.getPos().z-pos.getZ(),2)))<=10 && tick_kav ==120 &&!(player.isCreative()||player.isSpectator()))
                    {
                        //  System.out.println(state.get(MOOD));
                        if(state.get(MOOD)>=0 && state.get(MOOD)<=5)
                        {
                            mood(world,pos,state,state.get(MOOD),player);
                        }
                        tick_kav=0;

                    }
                }
            }
        }
    }
    public static void mood(World world, BlockPos pos, BlockState state,int moods, PlayerEntity player) {

        if(moods==0 ||moods>11)
        {
            moods=1;
        }


        player.sendMessage(statue_kav.text.get(statue_kav.mood.get(moods)),true);
        if(moods<6)
        {
            world.setBlockState(pos,((BlockState)state.with(MOOD,moods +1)), Block.NOTIFY_ALL);

        }
        else if(moods<12)
        {
            world.setBlockState(pos,((BlockState)state.with(MOOD,moods)), Block.NOTIFY_ALL);
        }
        else {
            player.sendMessage(Text.translatable("kav_soul_like.very_angry"),true);
        }
        // world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

}
