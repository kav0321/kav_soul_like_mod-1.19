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

}
