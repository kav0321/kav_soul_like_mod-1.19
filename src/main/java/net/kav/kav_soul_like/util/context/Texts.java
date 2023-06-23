package net.kav.kav_soul_like.util.context;

import net.minecraft.text.Text;

public class Texts {
    private  int choice_node_1;
    private  int choice_node_2;
    private  int choice_node_3;
    private String s1;
    private String s2;
    private String s3;
    private boolean isend;
    private Text text;
    public Texts(Text text,int ctx, int ctx2, int ctx3,String s1,String s2, String s3,boolean isend)
    {
        this.text= text;
        this.choice_node_1=ctx;
        this.choice_node_2=ctx2;
        this.choice_node_3=ctx3;
        this.s1=s1;
        this.s2=s2;
        this.s3=s3;
        this.isend=isend;
    }

    public Text getText()
    {
        return this.text;
    }
    public int getChoice_node_1()
    {
        return this.choice_node_1;
    }
    public int getChoice_node_2()
    {
        return this.choice_node_2;
    }
    public int getChoice_node_3()
    {
        return this.choice_node_3;
    }

    public String getS1()
    {
        return this.s1;
    }
    public String getS2()
    {
        return this.s2;
    }

    public String getS3()
    {
        return this.s3;
    }
    public boolean isend()
    {
        return this.isend;
    }
    public void setend(boolean isend)
    {
        this.isend=isend;
    }
    public void setnodepointer(int ctx, int ctx2, int ctx3,String s1,String s2, String s3,boolean isend)
    {
        this.choice_node_1=ctx;
        this.choice_node_2=ctx2;
        this.choice_node_3=ctx3;
        this.s1=s1;
        this.s2=s2;
        this.s3=s3;
        this.isend=isend;
    }

}
