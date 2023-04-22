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
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class LevelUpGui extends LightweightGuiDescription {
    int tick=0;
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

    private static DecimalFormat df = new DecimalFormat("#.000");
    private static double s= 0;
    private static double s2= 0;
    private static double s3= 0;
    private static double s4= 0;
    private static double s5= 0;
    private static double s6= 0;
    private static  int level_t=0;

    private static final Identifier HEART= new Identifier(Kav_soul_like.MOD_ID,"textures/gui/heart.png");
    private static final Identifier ARROW= new Identifier(Kav_soul_like.MOD_ID,"textures/gui/arrow_up.png");
    private static final Identifier ARROW2= new Identifier(Kav_soul_like.MOD_ID,"textures/gui/arrow_up_2.png");
    private static final Identifier FIRE= new Identifier(Kav_soul_like.MOD_ID,"textures/gui/soul_fire_1.png");
    private static final Identifier STAMINA= new Identifier(Kav_soul_like.MOD_ID,"textures/gui/stamina.png");
    public LevelUpGui()
    {

        tick++;
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
        WSprite arrow= new WSprite(ARROW);
        WSprite arrow2= new WSprite(ARROW2);
        WSprite fire= new WSprite(FIRE);
        WSprite str= new WSprite((new Identifier("minecraft:textures/item/iron_sword.png")));

        WSprite heart= new WSprite(HEART);
        WSprite agility= new WSprite((new Identifier("minecraft:textures/item/bow_pulling_0.png")));
        WSprite stamina= new WSprite(STAMINA);
        WSprite defence= new WSprite((new Identifier("minecraft:textures/mob_effect/resistance.png")));

        WDynamicLabel label= new WDynamicLabel(() ->"Level NEEDED",Color.WHITE.getRGB());
        WDynamicLabel label2= new WDynamicLabel(() ->Integer.toString(exp_needed), Color.GREEN.getRGB());

        double atk=MinecraftClient.getInstance().player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getValue();

        WDynamicLabel strlabel= new WDynamicLabel(() ->Integer.toString(amount_str_c));
        WDynamicLabel heartlabel= new WDynamicLabel(() ->Integer.toString(amount_heart_c));
        WDynamicLabel agilitylabel= new WDynamicLabel(() ->Integer.toString(amount_agility_c));
        WDynamicLabel staminalabel= new WDynamicLabel(() ->Integer.toString(amount_stamina_c));
        WDynamicLabel defencelabel= new WDynamicLabel(() ->Integer.toString(amount_defence_c));
        WDynamicLabel str_2= new WDynamicLabel(() ->"DMG: "+ s,Color.DARK_GRAY.getRGB());
        WDynamicLabel h_2= new WDynamicLabel(() ->"Hearts: "+ s2,Color.DARK_GRAY.getRGB());
        WDynamicLabel a_2= new WDynamicLabel(() ->"ATK SPD: "+ s3,Color.DARK_GRAY.getRGB());
        WDynamicLabel a_3= new WDynamicLabel(() ->"MVM SPD: "+ s4,Color.DARK_GRAY.getRGB());
        WDynamicLabel s_1= new WDynamicLabel(() ->"STM: "+Float.toString(StaminaData.addPoints(((IEntityDataSaver) MinecraftClient.getInstance().player),0,"MaxStamina")),Color.DARK_GRAY.getRGB());
        WDynamicLabel d_2_2= new WDynamicLabel(() ->"ARMOR: "+ s5,Color.DARK_GRAY.getRGB());
        WDynamicLabel d_2= new WDynamicLabel(() ->"KNB_ R: "+ s6,Color.DARK_GRAY.getRGB());






        root.add(arrow,0,0,1,1);

        root.add(strlabel,3,4);
        root.add(heartlabel,3,5);
        root.add(agilitylabel,3,6);
        root.add(staminalabel,3,7);
        root.add(defencelabel,3,8);

        root.add(str_2,7,3);
        root.add(h_2,7,4);
        root.add(a_2,7,5);
        root.add(a_3,7,6);
        root.add(s_1,7,7);
        root.add(d_2_2,7,8);
        root.add(d_2,7,9);

        root.add(label,1,0);
        root.add(label2,5,0);
        WButtonLeft button_str= new WButtonLeft();
        WButtonLeft button_heart= new WButtonLeft();
        WButtonLeft button_agility= new WButtonLeft();
        WButtonLeft button_stamina= new WButtonLeft();
        WButtonLeft button_defence= new WButtonLeft();


        root.add(str, 1, 4, 1, 1);
        root.add(heart, 1, 5, 1, 1);
        root.add(agility, 1, 6, 1, 1);
        root.add(stamina, 1, 7, 1, 1);
        root.add(defence, 1, 8, 1, 1);

        root.add(button_str, 4, 4, 1, 1);
        root.add(button_heart, 4, 5, 1, 1);
        root.add(button_agility, 4, 6, 1, 1);
        root.add(button_stamina, 4, 7, 1, 1);
        root.add(button_defence, 4, 8, 1, 1);



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

        s =Double.valueOf(df.format(StrengthData.strengthleveltobase(((IEntityDataSaver) MinecraftClient.getInstance().player),amount_str_c)));
        s2= Double.valueOf(df.format(HeartData.heartlevelbase(((IEntityDataSaver) MinecraftClient.getInstance().player),amount_heart_c)));
        s3= Double.valueOf(df.format(AgilityData.Agilitylevel_base_attack(((IEntityDataSaver) MinecraftClient.getInstance().player),amount_agility_c)));
        s4= Double.valueOf(df.format(AgilityData.Agilitylevel_base_move(((IEntityDataSaver) MinecraftClient.getInstance().player),AgilityData.getAgility((IEntityDataSaver) MinecraftClient.getInstance().player))));
        s5= Double.valueOf(df.format(DefenceData.DefenceLevelArmor(((IEntityDataSaver) MinecraftClient.getInstance().player),amount_defence_c)));
        s6= Double.valueOf(df.format(DefenceData.DefenceLevelKnockbackResistance(((IEntityDataSaver) MinecraftClient.getInstance().player),amount_defence_c)));

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

        s =Double.valueOf(df.format(StrengthData.strengthleveltobase(((IEntityDataSaver) MinecraftClient.getInstance().player),amount_str_c)));
        s2= Double.valueOf(df.format(HeartData.heartlevelbase(((IEntityDataSaver) MinecraftClient.getInstance().player),amount_heart_c)));
        s3= Double.valueOf(df.format(AgilityData.Agilitylevel_base_attack(((IEntityDataSaver) MinecraftClient.getInstance().player),amount_agility_c)));
        s4= Double.valueOf(df.format(AgilityData.Agilitylevel_base_move(((IEntityDataSaver) MinecraftClient.getInstance().player),AgilityData.getAgility((IEntityDataSaver) MinecraftClient.getInstance().player))));
        s5= Double.valueOf(df.format(DefenceData.DefenceLevelArmor(((IEntityDataSaver) MinecraftClient.getInstance().player),amount_defence_c)));
        s6= Double.valueOf(df.format(DefenceData.DefenceLevelKnockbackResistance(((IEntityDataSaver) MinecraftClient.getInstance().player),amount_defence_c)));


    }
}
