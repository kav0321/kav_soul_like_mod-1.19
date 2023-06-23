package net.kav.kav_soul_like.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.kav_soul_like.TechnicAttacks.TechnicManager;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.statusEffect.ModStatusEffects;
import net.kav.kav_soul_like.util.IEntityDataSaver;
import net.kav.kav_soul_like.util.StaminaData;
import net.kav.kav_soul_like.util.playerabilities;
import net.kav.kav_soul_like.util.screennbt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

import static net.kav.kav_soul_like.event.KeyInputHandler.dashingkey;

public class ClientStamina implements ClientTickEvents.EndWorldTick{
    int x1 = 0;
    int y = 0;
    public static int getTick = 0;
    public static int tick=0;
    public static float status;
    //
    public static boolean bonuscon=false;
    public static boolean con=true;
    @Override
    public void onEndTick(ClientWorld world) {

        if(getTick>-1)
        {
            if(getTick==0)
            {
               // System.out.println(getTick);
            }

            getTick--;
            if(getTick==-1)
            {
                getTick=0;
            }

        }

        if(((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().contains("aglet") && playerabilities.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"aglet")!=-1)
        {
            TechnicManager.Techics.get(playerabilities.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"aglet")).tick(MinecraftClient.getInstance().player);
        }
        else if(((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().contains("necklace") &&playerabilities.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"necklace")!=-1)
        {
            TechnicManager.Techics.get(playerabilities.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"necklace")).tick(MinecraftClient.getInstance().player);
        } else if(((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().contains("glove") &&playerabilities.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"glove")!=-1)
        {
            TechnicManager.Techics.get(playerabilities.getability(((IEntityDataSaver) MinecraftClient.getInstance().player),"glove")).tick(MinecraftClient.getInstance().player);
        }


        y++;
        x1++;
        if (x1 >= 5) {
            x1 = 0;

        }
        if ( x1==4&& !MinecraftClient.getInstance().player.isSprinting() &&!AttackOveride.getCon() &&!dashingkey.isPressed() && !MinecraftClient.getInstance().player.isBlocking() && !MinecraftClient.getInstance().player.isUsingItem()) {

            float re=StaminaData.recoveryratetag(((IEntityDataSaver) MinecraftClient.getInstance().player));

            float x = StaminaData.addPoints(((IEntityDataSaver) MinecraftClient.getInstance().player), 0, "Stamina");
            //MinecraftClient.getInstance().player.sendMessage(Text.literal(Float.toString(x)));
            //MinecraftClient.getInstance().player.sendMessage(Text.literal(Float.toString(x)));
            if(x<1)
            {
                PacketByteBuf buff =PacketByteBufs.create();
                buff.writeBoolean(false);



                ClientPlayNetworking.send(ModMessages.LOWSTA, buff);

            }




            float x122=    StaminaData.addPoints(((IEntityDataSaver) MinecraftClient.getInstance().player), re+0.3f, "Stamina");
            status=0;




        }

        AttackOveride.setCon(false);
        if(screennbt.getscreen((IEntityDataSaver) MinecraftClient.getInstance().player)==true)
        {

            if(MinecraftClient.getInstance().currentScreen==null)
            {

                screennbt.setScreen((IEntityDataSaver) MinecraftClient.getInstance().player,false);
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeBoolean(false);
                ClientPlayNetworking.send(ModMessages.screen,buf);
                PacketByteBuf buf2 = PacketByteBufs.create();

                if(screennbt.getid((IEntityDataSaver) MinecraftClient.getInstance().player)!=0)
                {
                    buf2.writeInt(screennbt.getid((IEntityDataSaver) MinecraftClient.getInstance().player));
                    ClientPlayNetworking.send(ModMessages.id,buf2);
                }


            }
        }
           // System.out.println(!MinecraftClient.getInstance().player.isSprinting() &&!AttackOveride.getCon() &&!dashingkey.isPressed() && !MinecraftClient.getInstance().player.isBlocking() && !MinecraftClient.getInstance().player.isUsingItem());


           // System.out.println(!MinecraftClient.getInstance().player.isSprinting() &&!AttackOveride.getCon() &&!dashingkey.isPressed() && !MinecraftClient.getInstance().player.isBlocking() && !MinecraftClient.getInstance().player.isUsingItem());





    }
    public static boolean hasstamina(PlayerEntity player) {
        for (StatusEffectInstance effect : player.getStatusEffects()) {
            StatusEffect effectType = effect.getEffectType();
            if (effectType == ModStatusEffects.staminaboost) {
                return true;
            }
        }
        return false;
    }

}
