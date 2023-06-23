package net.kav.kav_soul_like.util.context;

import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class Context {




    private  HashMap<Integer, Texts> text= new HashMap<Integer,Texts>();

    public Context()
    {

    }

    public void addtext(int indexkey,String stringtranslatable,int ctx, int ctx2, int ctx3,String s1,String s2, String s3,boolean isend)
    {

        Texts texts= new Texts(Text.translatable(stringtranslatable), ctx,  ctx2,  ctx3, s1, s2,  s3, isend);
        text.put(indexkey,texts);


    }
    public Texts gettext(int indexkey)
    {
        return text.get(indexkey);
    }







}
