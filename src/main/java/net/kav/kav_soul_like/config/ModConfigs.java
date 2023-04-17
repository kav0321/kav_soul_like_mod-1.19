package net.kav.kav_soul_like.config;

import com.mojang.datafixers.util.Pair;
import net.kav.kav_soul_like.Kav_soul_like;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static String TEST;
    public static int SOME_INT;
    public static int X;
    public static int Y;



//Strength
    public static double Ks;
    public static double Ms;
//Heart
    public static double Kh;
    public static double Mh;
    public static double Lh;
//Agility
    public static double Ka_m;
    public static double Ma_m;
    public static double La_m;


    public static double Ka_s;
    public static double Ma_s;
    public static double La_s;
//Defence
    public static double Kd_k;
    public static double Md_k;
    public static double Ld_k;


    public static double Kd_r;
    public static double Md_r;
    public static double Ld_r;


    public static double SOME_DOUBLE;
    public static int MAX_DAMAGE_DOWSING_ROD;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(Kav_soul_like.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {

        configs.addKeyValuePair(new Pair<>("matrix.x", 20), "Position for x");

        configs.addKeyValuePair(new Pair<>("matrix.y", 50), "Position for y");
    }

    private static void assignConfigs() {

        X = CONFIG.getOrDefault("matrix.x", 20);

        Y = CONFIG.getOrDefault("matrix.y", 50);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
