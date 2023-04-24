package net.kav.kav_soul_like.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.util.IEntityDataSaver;
import net.kav.kav_soul_like.util.StaminaData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;

import static net.kav.kav_soul_like.event.KeyInputHandler.dashingkey;

public class ClientStamina implements ClientTickEvents.EndWorldTick{
    int x1 = 0;
    int y = 0;

    public static int tick=0;
    //
    public static boolean bonuscon=false;
    public static boolean con=true;
    @Override
    public void onEndTick(ClientWorld world) {






        y++;
        x1++;
        if (x1 >= 10) {
            x1 = 0;

        }

           // System.out.println(!MinecraftClient.getInstance().player.isSprinting() &&!AttackOveride.getCon() &&!dashingkey.isPressed() && !MinecraftClient.getInstance().player.isBlocking() && !MinecraftClient.getInstance().player.isUsingItem());
        if ( x1==9&& !MinecraftClient.getInstance().player.isSprinting() &&!AttackOveride.getCon() &&!dashingkey.isPressed() && !MinecraftClient.getInstance().player.isBlocking() && !MinecraftClient.getInstance().player.isUsingItem()) {

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
        }

        AttackOveride.setCon(false);




    }


}
