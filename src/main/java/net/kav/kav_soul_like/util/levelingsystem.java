package net.kav.kav_soul_like.util;

import static java.lang.Math.pow;

public class levelingsystem {


    private static int req;

    public static int reqcal(int level)
    {
        req= (int) (2.401924*pow(level,0.859812));
        return req;
    }
}