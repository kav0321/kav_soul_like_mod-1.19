package net.kav.kav_soul_like.config;

import com.mojang.datafixers.util.Pair;
import net.kav.kav_soul_like.Kav_soul_like;

import static java.lang.Math.pow;

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



    public static double Ka_s;
    public static double Ma_s;
    public static double La_s;
//Defence
    public static double Kd_k;
    public static double Md_k;
    public static double Ld_k;


    public static double Kd_r;
    public static double Md_r;
    public static double Jd_r;
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

        configs.addKeyValuePair(new Pair<>("matrix.Ks", 2.25007537562693), "");
        configs.addKeyValuePair(new Pair<>("matrix.Ms", 0.401337607480491), "");

        configs.addcomment("Ks*level^Ms");

        configs.addKeyValuePair(new Pair<>("matrix.Kh", -922.835251414047), "");
        configs.addKeyValuePair(new Pair<>("matrix.Mh", -0.00595021874968786), "");
        configs.addKeyValuePair(new Pair<>("matrix.Lh", 939.828724828382), "");
        configs.addcomment("Kh*(level+1)^Mh+Lh");


        configs.addKeyValuePair(new Pair<>("matrix.Ka_m", 0.000798427353378238), "");
        configs.addKeyValuePair(new Pair<>("matrix.Ma_m", 0.0992703768017564), "");
        configs.addcomment("Ka_m*(level+1)+Ma_m");

        configs.addKeyValuePair(new Pair<>("matrix.Ka_s", 0.0104010957232309), "");
        configs.addKeyValuePair(new Pair<>("matrix.Ma_s", 1.18927368957711), "");
        configs.addKeyValuePair(new Pair<>("matrix.La_s", 3.98835849955021), "");
        configs.addcomment("Ka_s*(level)^Ma_m+La_s");


        configs.addKeyValuePair(new Pair<>("matrix.Kd_k", 0.0068), "Multiplier");
        configs.addKeyValuePair(new Pair<>("matrix.Md_k", 0.5032), "Maximum value");
        configs.addKeyValuePair(new Pair<>("matrix.Ld_k", 74), "Level Cap");


        configs.addKeyValuePair(new Pair<>("matrix.Kd_r", -4000), "");
        configs.addKeyValuePair(new Pair<>("matrix.Md_r", -2), "");
        configs.addKeyValuePair(new Pair<>("matrix.Jd_r", 20), "");
        configs.addKeyValuePair(new Pair<>("matrix.Ld_r", 10), "");
        configs.addcomment("Kd_r*(level+Jd_r)^Md_r+Ld_r");
    }

    private static void assignConfigs() {

        X = CONFIG.getOrDefault("matrix.x", 20);

        Y = CONFIG.getOrDefault("matrix.y", 50);

        Ks=CONFIG.getOrDefault("matrix.Ks",2.25007537562693);
        Ms=CONFIG.getOrDefault("matrix.Ms",0.401337607480491);

        Kh=CONFIG.getOrDefault("matrix.Kh",-922.835251414047);
        Mh=CONFIG.getOrDefault("matrix.Mh",-0.00595021874968786);
        Lh=CONFIG.getOrDefault("matrix.Lh",939.828724828382);

        Ka_m=CONFIG.getOrDefault("matrix.Ka_m",0.000798427353378238);
        Ma_m=CONFIG.getOrDefault("matrix.Ma_m",0.0992703768017564);

        Ka_s=CONFIG.getOrDefault("matrix.Ka_s",0.0104010957232309);
        Ma_s=CONFIG.getOrDefault("matrix.Ma_s",1.18927368957711);
        La_s=CONFIG.getOrDefault("matrix.La_s", 3.98835849955021);

        Kd_k=CONFIG.getOrDefault("matrix.Kd_k", 0.0068);
        Md_k=CONFIG.getOrDefault("matrix.Md_k", 0.5032);
        Ld_k=CONFIG.getOrDefault("matrix.Ld_k", 74);

        Kd_r=CONFIG.getOrDefault("matrix.Kd_r", -4000);
        Md_r=CONFIG.getOrDefault("matrix.Md_r", -2);
        Jd_r=CONFIG.getOrDefault("matrix.Jd_r", 20);
        Ld_r=CONFIG.getOrDefault("matrix.Ld_r", 10);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
