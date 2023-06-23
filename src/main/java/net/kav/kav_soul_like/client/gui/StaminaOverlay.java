package net.kav.kav_soul_like.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.util.GlobalStamina;
import net.kav.kav_soul_like.util.IEntityDataSaver;
import net.kav.kav_soul_like.util.StaminaData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;


public class StaminaOverlay
        implements HudRenderCallback {
    /*Identifiers for the Stamina to display*/
    private static final Identifier Stamina_StartFull= new Identifier(Kav_soul_like.MOD_ID,"textures/gui/stamina/stamina_full.png");
    private static final Identifier Stamina_StartEmpty= new Identifier(Kav_soul_like.MOD_ID,"textures/gui/stamina/stamina_empty.png");

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {

        //window
        int x =0;
        int y= 0;

        MinecraftClient client =MinecraftClient.getInstance();
        if(client!=null)
        {
            int width =client.getWindow().getScaledWidth();
            int heigth =client.getWindow().getScaledHeight();
            x=width/2;
            y=heigth;
        }


        //Stamina Bar




        this.StaminaBar(matrixStack,tickDelta,client,x,y);
    }



    private void draw(Identifier identifier,MatrixStack matrixStack,int x, int y, int x1, int y1, int v)
    {


            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
            RenderSystem.setShaderTexture(0, identifier);
            DrawableHelper.drawTexture(matrixStack,x-x1,y-y1,0,0,v,42,5,42,35);


    }
    private void draw(Identifier identifier,MatrixStack matrixStack,int x, int y, int x1, int y1, int v,int width)
    {


            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, identifier);

            DrawableHelper.drawTexture(matrixStack, x - x1, y - y1, 0, 0, v, width, 5, 42, 35);

    }

    private void StaminaBar(MatrixStack matrixStack,float tickDelta,MinecraftClient client, int x, int y)
    {
        float stamina = StaminaData.addPoints(((IEntityDataSaver) MinecraftClient.getInstance().player), 0, "Stamina");
        float maximumsta =((IEntityDataSaver) client.player).getPersistentData().getFloat("MaxStamina");

        if(!(client.player.isCreative())&&!(client.player.isSpectator()) && !client.player.hasVehicle())
        {

            int number_of_bar;
            int number_of_bars;
            int numbers_of_bar_befor;
            int fixed_bar;
            int y22=0;


            number_of_bars  = (int) maximumsta %5;

            if(number_of_bars!=0)
            {
                fixed_bar=1;
            }
            else
                fixed_bar =0;
            numbers_of_bar_befor= (int) maximumsta/5;
            number_of_bar= fixed_bar+numbers_of_bar_befor;

            int initialx= GlobalStamina.X;
            int initialy=GlobalStamina.Y;

            this.draw(Stamina_StartEmpty,matrixStack,x,y,initialx,initialy,0);
            for(int i=0;i< number_of_bar - 2;i++)
            {
                y22=10+10*(i+1);
                this.draw(Stamina_StartEmpty, matrixStack, x, y, initialx-10-10*i, initialy,5);
            }

            int z;
            if(number_of_bars==1)
            {
                this.draw(Stamina_StartEmpty, matrixStack, x, y, initialx-y22, initialy,10);

            }
            else if(number_of_bars==2)
            {
                this.draw(Stamina_StartEmpty, matrixStack, x, y, initialx-y22, initialy,15);
            }
            else if(number_of_bars==3)
            {
                this.draw(Stamina_StartEmpty, matrixStack, x, y, initialx-y22, initialy,20);
            }
            else if(number_of_bars==4)
            {
                this.draw(Stamina_StartEmpty, matrixStack, x, y, initialx-y22, initialy,25);
            }
            else if(number_of_bars==0)
            {
                this.draw(Stamina_StartEmpty, matrixStack, x, y, initialx-y22, initialy,30);
            }

            //calculate Percentage for stamina
            float per=stamina/maximumsta;

            float con= ((per)*number_of_bar);



            int fl=0;
            int x23;
            fl = (int) (stamina);
            if(fl<=0)
            {
                fl=0;

            }

            x23=32+2*fl;
            if(x23>42)
            {
                x23=42;
            }

            //Draw full stamina
            if(fl!=0)
            {
                this.draw(Stamina_StartFull, matrixStack, x, y, initialx, initialy,0,x23);
            }






            for (int i = 0; i < number_of_bar - 2; i++) {


                fl =  ((int)stamina-(5+i*5));
                if(fl<0)
                {
                    fl=0;
                }

                x23=32+2*fl;
                if(x23>42)
                {
                    x23=42;
                }


                y22=10+10*(i+1);

                this.draw(Stamina_StartFull, matrixStack, x, y, initialx-10-10*i, initialy,5,x23);

            }



            fl = (int) (stamina-(maximumsta-5));
            x23=32+2*fl;
            if(fl>5)
            {
                fl=5;
            }
            if(fl<0)
            {
                fl=0;
            }
            if(x23>42)
            {
                x23=42;
            }

            //client.player.sendMessage(Text.of(Integer.toString(number_of_bars)),false);
            if (number_of_bars == 1) {
                this.draw(Stamina_StartFull, matrixStack, x, y, initialx-y22, initialy,10,x23-7);

            } else if (number_of_bars == 2) {
                this.draw(Stamina_StartFull, matrixStack, x, y, initialx-y22, initialy,15,x23-5);
            } else if (number_of_bars == 3) {
                this.draw(Stamina_StartFull, matrixStack, x, y, initialx-y22, initialy,20,x23-4);

            } else if (number_of_bars == 4) {
                this.draw(Stamina_StartFull, matrixStack, x, y, initialx-y22, initialy,25,x23-2);
            } else  {
                this.draw(Stamina_StartFull, matrixStack, x, y, initialx-y22, initialy,30,x23);
            }






        }
    }
}
