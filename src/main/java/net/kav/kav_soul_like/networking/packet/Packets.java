package net.kav.kav_soul_like.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.kav_soul_like.Kav_soul_like;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.List;
//credit to better combat
public class Packets {

        public record TechicAnimation(int playerId,String animationName) {
            public static Identifier ID = new Identifier(Kav_soul_like.MOD_ID, "attack_animation");

            public static String StopSymbol = "!STOP!";



            public static TechicAnimation stop(int playerId) {
                return new TechicAnimation(playerId, StopSymbol);
            }

            public PacketByteBuf write() {
                PacketByteBuf buffer = PacketByteBufs.create();
                buffer.writeInt(playerId);

                buffer.writeString(animationName);

                return buffer;
            }

            public static TechicAnimation read(PacketByteBuf buffer) {
                int playerId = buffer.readInt();

                String animationName = buffer.readString();

                return new TechicAnimation(playerId,animationName);
            }
        }


    public record DashAnimation(int playerId,String animationName, int direction,int speed) {
        public static Identifier ID = new Identifier(Kav_soul_like.MOD_ID, "dash_animation");

        public static String StopSymbol = "!STOP!";



        public static DashAnimation stop(int playerId) {
            return new DashAnimation(playerId, StopSymbol,0,0);
        }

        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeInt(playerId);
            buffer.writeString(animationName);
            buffer.writeInt(direction);
            buffer.writeInt(speed);
            return buffer;
        }

        public static DashAnimation read(PacketByteBuf buffer) {
            int playerId = buffer.readInt();
            String animationName = buffer.readString();
            int direction = buffer.readInt();
            int speed=buffer.readInt();
            return new DashAnimation(playerId,animationName,direction,speed);
        }
    }
    public record Stats(int str,int health, int agility,int stamina,int defence,int level) {
        public static Identifier ID = new Identifier(Kav_soul_like.MOD_ID, "stats");
        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeInt(str);
            buffer.writeInt(health);
            buffer.writeInt(agility);
            buffer.writeInt(stamina);
            buffer.writeInt(defence);
            buffer.writeInt(level);
            return buffer;
        }

        public static Stats read(PacketByteBuf buffer) {
            int str = buffer.readInt();
            int health = buffer.readInt();
            int agility = buffer.readInt();
            int stamina=buffer.readInt();
            int defence=buffer.readInt();
            int level=buffer.readInt();
            return new Stats(str,health,agility,stamina,defence,level);
        }
    }

    public record Stats_item(String name,int str,int health, int agility,int stamina,int defence,int magic,String cat) {
        public static Identifier ID = new Identifier(Kav_soul_like.MOD_ID, "stats_item");
        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeString(name);
            buffer.writeInt(str);
            buffer.writeInt(health);
            buffer.writeInt(agility);
            buffer.writeInt(stamina);
            buffer.writeInt(defence);
            buffer.writeInt(magic);
            buffer.writeString(cat);
            return buffer;
        }

        public static Stats_item read(PacketByteBuf buffer) {
            String name= buffer.readString();
            int str = buffer.readInt();
            int health = buffer.readInt();
            int agility = buffer.readInt();
            int stamina=buffer.readInt();
            int defence=buffer.readInt();
            int magic=buffer.readInt();
            String cat=buffer.readString();
            return new Stats_item(name,str,health,agility,stamina,defence,magic,cat);
        }
    }//,,int Ka_m,int Ma_m,int Ka_s,int
    public record level_up_maths_strength(double Ks,double Ms ) {
        public static Identifier ID = new Identifier(Kav_soul_like.MOD_ID, "str");

        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();

            buffer.writeDouble(Ks);
            buffer.writeDouble(Ms);

            return buffer;
        }

        public static level_up_maths_strength read(PacketByteBuf buffer) {

            double Ks = buffer.readDouble();
            double Ms = buffer.readDouble();

            return new level_up_maths_strength(Ks, Ms);
        }

    }
    public record level_up_maths_heart(double Kh, double Mh,double Lh ) {
        public static Identifier ID = new Identifier(Kav_soul_like.MOD_ID, "heart");
        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();

            buffer.writeDouble(Kh);
            buffer.writeDouble(Mh);
            buffer.writeDouble(Lh);

            return buffer;
        }

        public static level_up_maths_heart read(PacketByteBuf buffer) {

            double Kh = buffer.readDouble();
            double Mh = buffer.readDouble();
            double Lh=buffer.readDouble();

            return new level_up_maths_heart(Kh,Mh,Lh);
        }
    }
    public record level_up_maths_agility(double Ka_m, double Ma_m,double Ka_s,double Ma_s,double La_s) {
        public static Identifier ID = new Identifier(Kav_soul_like.MOD_ID, "agi");
        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();

            buffer.writeDouble(Ka_m);
            buffer.writeDouble(Ma_m);
            buffer.writeDouble(Ka_s);
            buffer.writeDouble(Ma_s);
            buffer.writeDouble(La_s);


            return buffer;
        }

        public static level_up_maths_agility read(PacketByteBuf buffer) {

            double Ka_m = buffer.readDouble();
            double Ma_m = buffer.readDouble();
            double Ka_s=buffer.readDouble();
            double Ma_s = buffer.readDouble();
            double La_s = buffer.readDouble();

            return new level_up_maths_agility(Ka_m,Ma_m,Ka_s,Ma_s,La_s);
        }
    }

    public record level_up_maths_defence(double Kd_k, double Md_k,double Ld_k,double Kd_r,double Md_r,double Jd_r,double  Ld_r) {
        public static Identifier ID = new Identifier(Kav_soul_like.MOD_ID, "def");
        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();

            buffer.writeDouble(Kd_k);
            buffer.writeDouble(Md_k);
            buffer.writeDouble(Ld_k);
            buffer.writeDouble(Kd_r);
            buffer.writeDouble(Md_r);
            buffer.writeDouble(Jd_r);
            buffer.writeDouble(Ld_r);
            return buffer;
        }

        public static level_up_maths_defence read(PacketByteBuf buffer) {

            double Kd_k = buffer.readDouble();
            double Md_k = buffer.readDouble();
            double Ld_k=buffer.readDouble();
            double Kd_r = buffer.readDouble();
            double Md_r = buffer.readDouble();
            double Jd_r = buffer.readDouble();
            double Ld_r = buffer.readDouble();
            return new level_up_maths_defence(Kd_k,Md_k,Ld_k,Kd_r,Md_r,Jd_r,Ld_r);
        }
    }



}
