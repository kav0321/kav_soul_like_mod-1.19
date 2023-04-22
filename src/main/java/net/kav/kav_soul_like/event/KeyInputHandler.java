package net.kav.kav_soul_like.event;

import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.networking.packet.Packets;
import net.kav.kav_soul_like.networking.packet.direction;
import net.kav.kav_soul_like.util.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

import static java.lang.Math.*;

public class KeyInputHandler {

    public static final String KEY_CATEGORY_KAV_SOUL ="key.category.kav_soul_like.category";


    public static final String KEY_DASH = "key.kav_soul_like.dash";
    public static final String KEY_DASH_DIRECTION_A = "key.kav_soul_like.direction_a";
    public static final String KEY_DASH_DIRECTION_D = "key.kav_soul_like.direction_d";
    public static final String KEY_DASH_DIRECTION_S = "key.kav_soul_like.direction_s";
    public static final String KEY_DASH_DIRECTION_W = "key.kav_soul_like.direction_w";
    public static KeyBinding dashingkey;

    private static int tick=20;
    private static boolean right_arm;
    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
        tick--;
        if(tick <=0)
        {
            tick=0;
        }
            if(client.player!=null)
            {


                if(dashingkey.isPressed() && ((tick==0 && StaminaData.addPoints(((IEntityDataSaver) MinecraftClient.getInstance().player), 0, "Stamina")>1))){
                if(MinecraftClient.getInstance().player.getMainArm()== Arm.RIGHT)
                {
                    right_arm=true;
                }
                    System.out.println(MinecraftClient.getInstance().player.getVelocity().getY());
                    if(pow(pow(MinecraftClient.getInstance().player.getVelocity().x,2)+pow(MinecraftClient.getInstance().player.getVelocity().y,2)+pow(MinecraftClient.getInstance().player.getVelocity().z,2),0.5)>=0.1 && !MinecraftClient.getInstance().player.isCreative())
                    {
                        StaminaData.removePoints(((IEntityDataSaver) MinecraftClient.getInstance().player), 8, "Stamina");

                    }

                   // MinecraftClient.getInstance().player.setVelocity(5,0,0);
                   // MinecraftClient.getInstance().player.setSprinting(true);
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
                    //MinecraftClient.getInstance().player.setMovementSpeed(1000);

                    //System.out.println(x+" "+z);
                    if((abs(x)<0.1&&abs(z)<0.1))
                    {
                        xdd=(arr[0]*0.2)/10000;
                        zdd=(arr[1]*0.2)/10000;
                    }

                    double alpha = 0;
                    double atan = atan(abs(xdd / zdd));
                    if(xdd>=0 && zdd>=0)
                    {
                        alpha=2*PI- atan;
                    }
                    else if(xdd<0&& zdd>=0)
                    {
                        alpha= atan;
                    }
                    else if(xdd<0&& zdd<0)
                    {
                        alpha=PI- atan;
                    }
                    else if(xdd>=0&& zdd<0)
                    {
                        alpha=PI+ atan;
                    }
                    double xfinal=2*sin(-alpha);
                    double zfinal=2*cos(-alpha);
                    
                    //System.out.println(alpha*360/(2*PI)+" alpha "+xdd+" xdd1 "+ zdd+" zdd1 "+xdd/zdd);

                    if(abs(xdd)<=0.5)
                    {
                        xfinal=xdd;
                    }
                    if(abs(zdd)<0.5)
                    {
                        zfinal=zdd;
                    }
                   // System.out.println(alpha*360/(2*PI)+" alpha "+xfinal+" xdd2 "+ zfinal+" zdd2 "+xfinal/zfinal);
                    MinecraftClient.getInstance().player.setVelocity(new Vec3d(xfinal,0,zfinal));
                    //MinecraftClient.getInstance().player.setVelocity(new Vec3d(xdd*1.5,0,zdd*1.5));



                        double angle=MinecraftClient.getInstance().player.headYaw%360;
                        
                        direction directions = null;

                    if(angle<0)
                    {
                        angle=angle+360;
                    }
                   // System.out.println(angle);
                        if(angle>270)
                        {
                            //System.out.println(angle+"s");
                            if(xdd>=0&&zdd<=0)
                            {
                                directions= direction.LEFT;

                            }

                            else
                            {
                                directions=direction.RIGHT;

                            }

                        }
                        else if(0<=angle&& angle <=90)
                        {

                            if(xdd>=0&&zdd>=0)
                            {
                                directions= direction.LEFT;
                            }
                            else
                            {
                                directions=direction.RIGHT;
                            }
                        }
                        else if(90<angle && angle<=180)
                        {
                            if(xdd<=0&&zdd>=0)
                            {
                                directions= direction.LEFT;
                            }
                            else
                            {
                                directions=direction.RIGHT;
                            }
                        }
                        else if(180<angle && angle<=270)
                        {

                            if(xdd<=0&&zdd<=0)
                            {
                                directions= direction.LEFT;
                            }
                            else
                            {
                                directions=direction.RIGHT;
                            }
                        }
                   // System.out.println(xdd*1.5+" "+zdd*1.5+" "+angle);
                       // System.out.println(sqrt(pow(xdd*1.5,2)+pow(zdd*1.5,2))+" s");
                        //ClientPlayNetworking.send(ModMessages.WAVING,);
                    //System.out.println(direction);
                    String animationss;
                        double alpha_todegree=alpha*360/(2*PI);

                    if(abs(alpha_todegree-angle)<=10)
                    {
                        System.out.println("forward");
                        directions=direction.FORWARD;

                    }
                    if(abs(abs(alpha_todegree-angle)-180)<=10)
                    {
                        System.out.println("backward");
                        directions=direction.BACKWARD;
                    }
                    if(directions==direction.LEFT)
                    {
                       // KeyframeAnimation animation = PlayerAnimationRegistry.getAnimation(new Identifier(Kav_soul_like.MOD_ID, "dashing_left"));
                        animationss="dashing_left";
                    }
                    else if(directions==direction.FORWARD)
                    {
                       // KeyframeAnimation animation = PlayerAnimationRegistry.getAnimation(new Identifier(Kav_soul_like.MOD_ID, "dashing_left"));
                        if(!right_arm)
                        {
                            animationss="dashing_forward_leftleg";
                        }
                        else
                        {
                            animationss="dashing_forward_rightleg";
                        }

                    }
                    else if(directions==direction.BACKWARD)
                    {
                        // KeyframeAnimation animation = PlayerAnimationRegistry.getAnimation(new Identifier(Kav_soul_like.MOD_ID, "dashing_left"));
                        if(!right_arm)
                        {
                            animationss="dashing_backward_leftleg";
                        }
                        else
                        {
                            animationss="dashing_backward_rightleg";
                        }

                    }
                    else
                    {
                       // KeyframeAnimation animation = PlayerAnimationRegistry.getAnimation(new Identifier(Kav_soul_like.MOD_ID, "dashing_right"));
                        animationss="dashing_right";
                    }


                    if(MinecraftClient.getInstance().player.getVelocity().getX()!=0)
                    {
                        System.out.println(directions.getint());
                        ClientPlayNetworking.send(
                                Packets.DashAnimation.ID,
                                new Packets.DashAnimation(MinecraftClient.getInstance().player.getId(), animationss, directions.getint(),1).write());

                    }

                    System.out.println(angle+" angle "+xfinal+" xdd2 "+ zfinal+" zdd2 "+xfinal/zfinal);


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