package net.kav.kav_soul_like.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.util.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class KeyInputHandler {

    public static final String KEY_CATEGORY_KAV_SOUL ="key.category.kav_soul_like.category";


    public static final String KEY_DASH = "key.kav_soul_like.dash";
    public static final String KEY_DASH_DIRECTION_A = "key.kav_soul_like.direction_a";
    public static final String KEY_DASH_DIRECTION_D = "key.kav_soul_like.direction_d";
    public static final String KEY_DASH_DIRECTION_S = "key.kav_soul_like.direction_s";
    public static final String KEY_DASH_DIRECTION_W = "key.kav_soul_like.direction_w";
    public static KeyBinding dashingkey;

    private static int tick=10;
    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
        tick--;
        if(tick <=0)
        {
            tick=0;
        }
            if(client.player!=null)
            {

                if(dashingkey.isPressed() && tick==0 && StaminaData.addPoints(((IEntityDataSaver) MinecraftClient.getInstance().player), 0, "Stamina")>1){

                    if(pow(pow(MinecraftClient.getInstance().player.getVelocity().x,2)+pow(MinecraftClient.getInstance().player.getVelocity().y,2)+pow(MinecraftClient.getInstance().player.getVelocity().z,2),0.5)>=0.1)
                    {
                        StaminaData.removePoints(((IEntityDataSaver) MinecraftClient.getInstance().player), 3, "Stamina");

                    }

                   // MinecraftClient.getInstance().player.setVelocity(5,0,0);
                    MinecraftClient.getInstance().player.setSprinting(true);
                    PacketByteBuf buf =PacketByteBufs.create();
                    double x= MinecraftClient.getInstance().player.getVelocity().getX();
                    double z=MinecraftClient.getInstance().player.getVelocity().getZ();

                    double x1=x*1000000;
                    double z1=z*1000000;
                    int xd= (int) x1;
                    int zd= (int) z1;

                    int[] arr=new int[2];
                    arr[0]=xd;
                    arr[1]=zd;
                    buf.writeIntArray(arr);

                    double xdd=(arr[0])/100000;
                    double zdd=(arr[1])/100000;
                    MinecraftClient.getInstance().player.setMovementSpeed(1000);

                    System.out.println(x+" "+z);
                    if((abs(x)<0.1&&abs(z)<0.1))
                    {
                        xdd=(arr[0]*0.2)/10000;
                        zdd=(arr[1]*0.2)/10000;
                    }

                    MinecraftClient.getInstance().player.setVelocity(new Vec3d(xdd*1.5,0,zdd*1.5));

                    ClientPlayNetworking.send(ModMessages.DASH,buf);
                    tick=10;

                }

            }





        });
    }



    public static void register()
    {


        dashingkey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DASH,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                KEY_CATEGORY_KAV_SOUL
        ));




        registerKeyInputs();

    }






}