package net.kav.kav_soul_like.client.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.icon.Icon;
import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.util.DisplayLevel;
import net.kav.kav_soul_like.util.context.Context;
import net.kav.kav_soul_like.util.context.ContextManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static io.github.cottonmc.cotton.gui.client.BackgroundPainter.createLightDarkVariants;
import static io.github.cottonmc.cotton.gui.client.BackgroundPainter.createNinePatch;


public class melina_screen extends LightweightGuiDescription {
    private static final Identifier BACKGROUND= new Identifier(Kav_soul_like.MOD_ID,"textures/gui/panel_dark_trans.png");
    private int indexnext;
    WGridPanel root = (WGridPanel) rootPanel;

    private String choice1 = "";
    private String choice2 = "Next";
    private String choice3 = "";
    public int c;
    public int l;
    BackgroundPainter SOUL= createLightDarkVariants((createNinePatch(BACKGROUND)), createNinePatch(BACKGROUND));
    @Override
        public void addPainters()
        {
          if (this.root != null && !this.fullscreen) {
               this.root.setBackgroundPainter(SOUL);
           }
        }

    public melina_screen(int context, int lore)
    {
        this.text();
        int width = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int heigth =MinecraftClient.getInstance().getWindow().getScaledHeight();
        int ux=width/2;
        int uy=heigth;
        c=context;
        l=lore;
        //WGridPanel root = (WGridPanel) rootPanel;
        setRootPanel(root);
        root.setSize(ux+100, ux);
        root.setInsets(Insets.ROOT_PANEL);

//
        //
        //
        WDynamicLabel c1= new WDynamicLabel(() ->ContextManager.Contexts.get(l).gettext(c).getS1());
        WDynamicLabel c2= new WDynamicLabel(() ->ContextManager.Contexts.get(l).gettext(c).getS2());
        WDynamicLabel c3= new WDynamicLabel(() ->ContextManager.Contexts.get(l).gettext(c).getS3());
        WButton choice_1= new WButton(Text.of("    "));
        WButton choice_2= new WButton(Text.of("    "));
        WButton choice_3= new WButton(Text.of("    "));


        if(!Text.translatable(ContextManager.Contexts.get(l).gettext(c).getS2()).equals("")) {
            root.add(choice_2, 15, 9);
            root.add(c2, 17, 9);
        }
        if(!Text.translatable(ContextManager.Contexts.get(l).gettext(c).getS1()).equals("")) {
            root.add(choice_1, 15, 8);
            root.add(c1, 17, 8);
        }
        if(!Text.translatable(ContextManager.Contexts.get(l).gettext(c).getS3()).equals("")) {
            root.add(choice_3, 15, 10);
            root.add(c3, 17, 10);
        }

        choice_1.setOnClick(()->{

            if(ContextManager.Contexts.get(l).gettext(c).getS1().equals("level up"))
            {
                DisplayLevel.Gui();
            }
            else if(ContextManager.Contexts.get(l).gettext(c).getS1().equals("talk"))
            {
                if(ContextManager.Contexts.get(l).gettext(c).getChoice_node_1()!=0)
                {
                    this.c=  ContextManager.Contexts.get(l).gettext(c).getChoice_node_1();
                }
            }
            if(ContextManager.Contexts.get(l).gettext(c).getChoice_node_1()!=0)
            {
                this.c=  ContextManager.Contexts.get(l).gettext(c).getChoice_node_1();
            }
        });

        choice_2.setOnClick(()->{

            if(ContextManager.Contexts.get(l).gettext(c).getS2().equals("level up"))
            {
                DisplayLevel.Gui();
            }else if(ContextManager.Contexts.get(l).gettext(c).getS2().equals("talk"))
            {
                if(ContextManager.Contexts.get(l).gettext(c).getChoice_node_2()!=0)
                {
                    this.c=  ContextManager.Contexts.get(l).gettext(c).getChoice_node_2();
                }
            }

            if(ContextManager.Contexts.get(l).gettext(c).getChoice_node_2()!=0)
            {
                this.c=  ContextManager.Contexts.get(l).gettext(c).getChoice_node_2();
            }

        });

        choice_3.setOnClick(()->{
            if(ContextManager.Contexts.get(l).gettext(c).getChoice_node_3()!=0)
            {
                this.c=  ContextManager.Contexts.get(l).gettext(c).getChoice_node_3();
            }
            if(ContextManager.Contexts.get(l).gettext(c).getS3().equals("level up"))
            {
                DisplayLevel.Gui();
            }else if(ContextManager.Contexts.get(l).gettext(c).getS3().equals("talk"))
            {
                if(ContextManager.Contexts.get(l).gettext(c).getChoice_node_3()!=0)
                {
                    this.c=  ContextManager.Contexts.get(l).gettext(c).getChoice_node_3();
                }
            }

        });

        WDynamicLabel strlabel= new WDynamicLabel(() ->ContextManager.Contexts.get(l).gettext(c).getText().getString());


        root.add(strlabel,0,9);
    }

    private void text() {
        Context ctx= new Context();

        ctx.addtext(1,  "kav_soul_like.thx",0,2,0,"","next","",false);
        ctx.addtext(2,  "kav_soul_like.service",3,2,0,"talk","level up","",true);
        ctx.addtext(3,  "kav_soul_like.stuck",0,2,0,"","next","",false);
        ContextManager.Contexts.put(1,ctx );



    }



}
