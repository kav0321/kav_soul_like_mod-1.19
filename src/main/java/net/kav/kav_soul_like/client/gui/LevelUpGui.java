package net.kav.kav_soul_like.client.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.client.gui.buttons.WButtonLeft;
import net.kav.kav_soul_like.client.gui.buttons.WButtonRight;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.util.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class LevelUpGui extends LightweightGuiDescription {
    private static  int exp_needed = 0;
    private static  int amount_str= 0;
    private static  int amount_heart= 0;
    private static  int amount_agility= 0;
    private static  int amount_stamina= 0;
    private static  int amount_defence= 0;


    private static  int amount_str_c= 0;
    private static  int amount_heart_c= 0;
    private static  int amount_agility_c= 0;
    private static  int amount_stamina_c= 0;
    private static  int amount_defence_c= 0;


    private static  int level_t=0;

    private static final Identifier HEART= new Identifier(Kav_soul_like.MOD_ID,"textures/gui/heart.png");
    private static final Identifier STAMINA= new Identifier(Kav_soul_like.MOD_ID,"textures/gui/stamina.png");
    public LevelUpGui()
    {


        this.init();

        int width = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int heigth =MinecraftClient.getInstance().getWindow().getScaledHeight();
        int ux=width/2;
        int uy=heigth;
        //WGridPanel root = (WGridPanel) rootPanel;

        WGridPanel root= new WGridPanel();
        setRootPanel(root);
        root.setSize(ux+5, ux-10);
        root.setInsets(Insets.ROOT_PANEL);

        WSprite str= new WSprite((new Identifier("minecraft:textures/item/iron_sword.png")));
        WSprite heart= new WSprite(HEART);
        WSprite agility= new WSprite((new Identifier("minecraft:textures/item/bow_pulling_0.png")));
        WSprite stamina= new WSprite(STAMINA);
        WSprite defence= new WSprite((new Identifier("minecraft:textures/mob_effect/resistance.png")));

        WDynamicLabel label= new WDynamicLabel(() ->"Level NEEDED"+Integer.toString(exp_needed));
        WDynamicLabel strlabel= new WDynamicLabel(() ->Integer.toString(amount_str_c));
        WDynamicLabel heartlabel= new WDynamicLabel(() ->Integer.toString(amount_heart_c));
        WDynamicLabel agilitylabel= new WDynamicLabel(() ->Integer.toString(amount_agility_c));
        WDynamicLabel staminalabel= new WDynamicLabel(() ->Integer.toString(amount_stamina_c));
        WDynamicLabel defencelabel= new WDynamicLabel(() ->Integer.toString(amount_defence_c));
        root.add(strlabel,3,1);
        root.add(heartlabel,3,3);
        root.add(agilitylabel,3,5);
        root.add(staminalabel,3,7);
        root.add(defencelabel,3,9);
        root.add(label,2,0);
        WButtonLeft button_str= new WButtonLeft();
        WButtonLeft button_heart= new WButtonLeft();
        WButtonLeft button_agility= new WButtonLeft();
        WButtonLeft button_stamina= new WButtonLeft();
        WButtonLeft button_defence= new WButtonLeft();


        root.add(str, 2, 1, 1, 1);
        root.add(heart, 2, 3, 1, 1);
        root.add(agility, 2, 5, 1, 1);
        root.add(stamina, 2, 7, 1, 1);
        root.add(defence, 2, 9, 1, 1);

        root.add(button_str, 4, 1, 1, 1);
        root.add(button_heart, 4, 3, 1, 1);
        root.add(button_agility, 4, 5, 1, 1);
        root.add(button_stamina, 4, 7, 1, 1);
        root.add(button_defence, 4, 9, 1, 1);



        button_str.setOnClick(() -> {
            PacketByteBuf buflevels= PacketByteBufs.create();
            PacketByteBuf bufs= PacketByteBufs.create();
        if(MinecraftClient.getInstance().player.isCreative())
        {
            LevelData.increaseLevel(((IEntityDataSaver) MinecraftClient.getInstance().player),1);
            int level= LevelData.getLevel(((IEntityDataSaver) MinecraftClient.getInstance().player));
            StrengthData.increasestrength(((IEntityDataSaver) MinecraftClient.getInstance().player),1);

            buflevels.writeInt(level);
            bufs.writeInt(exp_needed);

            ClientPlayNetworking.send(ModMessages.LEVELCR,buflevels);
            ClientPlayNetworking.send(ModMessages.STRENGTH,bufs);

            exp_needed=  levelingsystem.reqcal(level);
            this.init2();
        }
        else if(MinecraftClient.getInstance().player.experienceLevel>=exp_needed)
        {


            LevelData.increaseLevel(((IEntityDataSaver) MinecraftClient.getInstance().player),1);
            int level= LevelData.getLevel(((IEntityDataSaver) MinecraftClient.getInstance().player));
            StrengthData.increasestrength(((IEntityDataSaver) MinecraftClient.getInstance().player),1);

            buflevels.writeInt(level);
            bufs.writeInt(exp_needed);

            ClientPlayNetworking.send(ModMessages.LEVEL,buflevels);
            ClientPlayNetworking.send(ModMessages.STRENGTH,bufs);

            exp_needed=  levelingsystem.reqcal(level);
            this.init2();

        }

        else
        {
            button_str.setEnabled(false);
        }

            // This code runs on the client when you click the button.
            //System.out.println("Button clicked!");
        });
        button_heart.setOnClick(() -> {
            PacketByteBuf buflevelh= PacketByteBufs.create();
            PacketByteBuf bufh= PacketByteBufs.create();
            if(MinecraftClient.getInstance().player.isCreative())
            {
                LevelData.increaseLevel(((IEntityDataSaver) MinecraftClient.getInstance().player),1);
                int level= LevelData.getLevel(((IEntityDataSaver) MinecraftClient.getInstance().player));
                HeartData.increaseHeart(((IEntityDataSaver) MinecraftClient.getInstance().player),1);

                buflevelh.writeInt(level);
                bufh.writeInt(exp_needed);

                ClientPlayNetworking.send(ModMessages.LEVELCR,buflevelh);
                ClientPlayNetworking.send(ModMessages.HEALTH,bufh);

                exp_needed=  levelingsystem.reqcal(level);
                this.init2();
            }

            else if(MinecraftClient.getInstance().player.experienceLevel>=exp_needed)
            {



                LevelData.increaseLevel(((IEntityDataSaver) MinecraftClient.getInstance().player),1);
                int level= LevelData.getLevel(((IEntityDataSaver) MinecraftClient.getInstance().player));
                HeartData.increaseHeart(((IEntityDataSaver) MinecraftClient.getInstance().player),1);

                buflevelh.writeInt(level);
                bufh.writeInt(exp_needed);

                ClientPlayNetworking.send(ModMessages.LEVEL,buflevelh);
                ClientPlayNetworking.send(ModMessages.HEALTH,bufh);

                exp_needed=  levelingsystem.reqcal(level);
                this.init2();
            }
            else
            {
                button_heart.setEnabled(false);
            }



            // This code runs on the client when you click the button.
            //System.out.println("Button clicked!");
        });
        button_agility.setOnClick(() -> {
            PacketByteBuf buflevela= PacketByteBufs.create();
            PacketByteBuf bufa= PacketByteBufs.create();
            if(MinecraftClient.getInstance().player.isCreative())
            {
                LevelData.increaseLevel(((IEntityDataSaver) MinecraftClient.getInstance().player),1);
                int level= LevelData.getLevel(((IEntityDataSaver) MinecraftClient.getInstance().player));
                AgilityData.increaseAgility(((IEntityDataSaver) MinecraftClient.getInstance().player),1);

                buflevela.writeInt(level);
                bufa.writeInt(exp_needed);

                ClientPlayNetworking.send(ModMessages.LEVELCR,buflevela);
                ClientPlayNetworking.send(ModMessages.AGILITY,bufa);
                exp_needed= levelingsystem.reqcal(level);
                this.init2();
            }
           else if(MinecraftClient.getInstance().player.experienceLevel>=exp_needed)
            {



                LevelData.increaseLevel(((IEntityDataSaver) MinecraftClient.getInstance().player),1);
                int level= LevelData.getLevel(((IEntityDataSaver) MinecraftClient.getInstance().player));
                AgilityData.increaseAgility(((IEntityDataSaver) MinecraftClient.getInstance().player),1);

                buflevela.writeInt(level);
                bufa.writeInt(exp_needed);

                ClientPlayNetworking.send(ModMessages.LEVEL,buflevela);
                ClientPlayNetworking.send(ModMessages.AGILITY,bufa);
                exp_needed= levelingsystem.reqcal(level);
                this.init2();
            }
            else
            {
                button_agility.setEnabled(false);
            }


            // This code runs on the client when you click the button.
            //System.out.println("Button clicked!");
        });
        button_stamina.setOnClick(() -> {
            PacketByteBuf buflevels= PacketByteBufs.create();
            PacketByteBuf bufS= PacketByteBufs.create();

            if(MinecraftClient.getInstance().player.isCreative())
            {
                LevelData.increaseLevel(((IEntityDataSaver) MinecraftClient.getInstance().player),1);
                int level= LevelData.getLevel(((IEntityDataSaver) MinecraftClient.getInstance().player));
                StaminaLevelData.increaseStaminaL(((IEntityDataSaver) MinecraftClient.getInstance().player),1);

                buflevels.writeInt(level);
                bufS.writeInt(exp_needed);
                ClientPlayNetworking.send(ModMessages.LEVELCR,buflevels);
                ClientPlayNetworking.send(ModMessages.STAMINAL,bufS);
                exp_needed=  levelingsystem.reqcal(level);
                this.init2();
            }
          else  if(MinecraftClient.getInstance().player.experienceLevel>=exp_needed)
            {



                LevelData.increaseLevel(((IEntityDataSaver) MinecraftClient.getInstance().player),1);
                int level= LevelData.getLevel(((IEntityDataSaver) MinecraftClient.getInstance().player));
                StaminaLevelData.increaseStaminaL(((IEntityDataSaver) MinecraftClient.getInstance().player),1);

                buflevels.writeInt(level);
                bufS.writeInt(exp_needed);
                ClientPlayNetworking.send(ModMessages.LEVEL,buflevels);
                ClientPlayNetworking.send(ModMessages.STAMINAL,bufS);
                exp_needed=  levelingsystem.reqcal(level);
                this.init2();
            }
            else
            {
                button_stamina.setEnabled(false);
            }

            // This code runs on the client when you click the button.
            //System.out.println("Button clicked!");
        });
        button_defence.setOnClick(() -> {
            PacketByteBuf buflevel= PacketByteBufs.create();
            PacketByteBuf bufD= PacketByteBufs.create();
            if(MinecraftClient.getInstance().player.isCreative())
            {
                LevelData.increaseLevel(((IEntityDataSaver) MinecraftClient.getInstance().player),1);
                int level= LevelData.getLevel(((IEntityDataSaver) MinecraftClient.getInstance().player));
                DefenceData.increaseDefence(((IEntityDataSaver) MinecraftClient.getInstance().player),1);

                buflevel.writeInt(level);
                bufD.writeInt(exp_needed);
                ClientPlayNetworking.send(ModMessages.LEVELCR,buflevel);
                ClientPlayNetworking.send(ModMessages.DEFENCE,bufD);
                exp_needed=  levelingsystem.reqcal(level);
                this.init2();
            }
           else if(MinecraftClient.getInstance().player.experienceLevel>=exp_needed)
            {



                LevelData.increaseLevel(((IEntityDataSaver) MinecraftClient.getInstance().player),1);
                int level= LevelData.getLevel(((IEntityDataSaver) MinecraftClient.getInstance().player));
                DefenceData.increaseDefence(((IEntityDataSaver) MinecraftClient.getInstance().player),1);

                buflevel.writeInt(level);
                bufD.writeInt(exp_needed);
                ClientPlayNetworking.send(ModMessages.LEVEL,buflevel);
                ClientPlayNetworking.send(ModMessages.DEFENCE,bufD);
                exp_needed=  levelingsystem.reqcal(level);
                this.init2();
            }
            else
            {
                button_defence.setEnabled(false);
            }
        // This code runs on the client when you click the button.
        //System.out.println("Button clicked!");
        });





    }

    private void init2()
    {

        amount_str_c=StrengthData.getstrength(((IEntityDataSaver) MinecraftClient.getInstance().player));
        amount_heart_c=HeartData.getHeart(((IEntityDataSaver) MinecraftClient.getInstance().player));
        amount_agility_c=AgilityData.getAgility(((IEntityDataSaver) MinecraftClient.getInstance().player));
        amount_stamina_c=StaminaLevelData.getStaminaL(((IEntityDataSaver) MinecraftClient.getInstance().player));
        amount_defence_c=DefenceData.getDefence(((IEntityDataSaver) MinecraftClient.getInstance().player));


    }

    private void init()
    {
        int level=LevelData.getLevel(((IEntityDataSaver) MinecraftClient.getInstance().player));
        exp_needed= (int) levelingsystem.reqcal(level);

        level_t=level;
        amount_str_c=StrengthData.getstrength(((IEntityDataSaver) MinecraftClient.getInstance().player));
        amount_heart_c=HeartData.getHeart(((IEntityDataSaver) MinecraftClient.getInstance().player));
        amount_agility_c=AgilityData.getAgility(((IEntityDataSaver) MinecraftClient.getInstance().player));
        amount_stamina_c=StaminaLevelData.getStaminaL(((IEntityDataSaver) MinecraftClient.getInstance().player));
        amount_defence_c=DefenceData.getDefence(((IEntityDataSaver) MinecraftClient.getInstance().player));



    }
}
