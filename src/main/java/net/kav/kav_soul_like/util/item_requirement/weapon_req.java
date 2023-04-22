package net.kav.kav_soul_like.util.item_requirement;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;


public class weapon_req {

    public static final ArrayList<combat_stats_req> weapon = new ArrayList<combat_stats_req>();
    public static final ArrayList<combat_stats_req> armorList = new ArrayList<combat_stats_req>();
    public static final ArrayList<combat_stats_req> shield = new ArrayList<combat_stats_req>();


    @Nullable
    public static ArrayList<combat_stats_req> getList(String string) {
        switch (string)
        {
            case "weapon":
                return weapon;
            case "armor":
                return armorList;
            case "shield":
                return shield;
            default:
                return null;
        }


    }
    public static void clear()
    {
        weapon.clear();
        armorList.clear();
        shield.clear();
    }

    public static void add(String name_id,String category, int strength, int health,int agility, int stamina,int defence,int magic)
    {
        switch (category)
        {
            case "weapon":
                weapon.add(new combat_stats_req(name_id,category,strength,health,agility,stamina,defence,magic));
                break;
            case "armor":
                armorList.add(new combat_stats_req(name_id,category,strength,health,agility,stamina,defence,magic));
                break;
            case "shield":
                shield.add(new combat_stats_req(name_id,category,strength,health,agility,stamina,defence,magic));
                break;
            default:
                System.out.println("error_loading_list");
        }
    }


}
