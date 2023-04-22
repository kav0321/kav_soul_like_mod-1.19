package net.kav.kav_soul_like.util.item_requirement;

public class combat_stats_req {
    public  final String name_id;
    public  final String category;
    public  final int STRENGTH;
    public  final int STAMINA;
    public  final int HEART;
    public  final int AGILITY;
    public   final int MAGIC;
    public   final int DEFENCE;

    public combat_stats_req(String name_id,String category,int STRENGTH2,int HEART,int AGILITY2,int STAMINA,int DEFENCE,int MAGIC)
    {
        this.name_id=name_id;
        this.category=category;
        this.STRENGTH=STRENGTH2;
        this.HEART=HEART;
        this.AGILITY=AGILITY2;
        this.STAMINA=STAMINA;
        this.DEFENCE=DEFENCE;
        this.MAGIC= MAGIC;

    }
}
