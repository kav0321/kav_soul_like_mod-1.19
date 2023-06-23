package net.kav.kav_soul_like.TechnicAttacks;

import java.util.ArrayList;
import java.util.List;

public class TechnicManager {

    public static List<Abstracttechnic> Techics= new ArrayList<>();

    //both client and server side
    public static void  INITTECHNIC()
    {
        Techics.add(new grapattacktechnic("grab",200,8));
        Techics.add(new smashtechnic("smash",200,8));
        Techics.add(new parrytechnic("parry",200,8));
        Techics.add(new kicktechnic("kick",200,8));
    }

}
