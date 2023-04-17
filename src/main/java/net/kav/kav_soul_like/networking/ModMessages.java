package net.kav.kav_soul_like.networking;

import com.google.common.collect.Iterables;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.MirrorModifier;
import dev.kosmx.playerAnim.api.layered.modifier.SpeedModifier;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.kav_soul_like.Kav_soul_like;
import net.kav.kav_soul_like.event.ClientStamina;
import net.kav.kav_soul_like.networking.packet.Packets;
import net.kav.kav_soul_like.networking.packet.PlayerStatsC2S;
import net.kav.kav_soul_like.networking.packet.direction;
import net.kav.kav_soul_like.networking.packet.playertechServerSide;
import net.kav.kav_soul_like.util.*;

import net.kav.kav_soul_like.util.modifier.TransmissionSpeedModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModMessages
{
    public static final Identifier STAMINA2 = new Identifier(Kav_soul_like.MOD_ID, "staminaa");
    public static final Identifier HANDSWING = new Identifier(Kav_soul_like.MOD_ID, "handswing");
    public static final Identifier STAMINA = new Identifier(Kav_soul_like.MOD_ID, "stamina");
    public static final Identifier MAXSTAMINAC2S = new Identifier(Kav_soul_like.MOD_ID, "maxstamina");
    public static final Identifier INITIALIZEM = new Identifier(Kav_soul_like.MOD_ID, "initializem");

    public static final Identifier INITIALIZEMC2S = new Identifier(Kav_soul_like.MOD_ID, "initializemc2s");
    public static final Identifier INITIALIZEX = new Identifier(Kav_soul_like.MOD_ID, "initializex");
    // public static final Identifier INITIALIZEXC2S = new Identifier(Stamina.MOD_ID, "initializexc2s");
    public static final Identifier INITIALIZEY = new Identifier(Kav_soul_like.MOD_ID, "initializey");
    public static final Identifier RECOVERYRATE = new Identifier(Kav_soul_like.MOD_ID, "recovery");
    public static final Identifier RECOVERYRATETIME = new Identifier(Kav_soul_like.MOD_ID, "recoverytime");
    public static final Identifier DISCONNECT = new Identifier(Kav_soul_like.MOD_ID, "disconnect");
    public static final Identifier DISCONNECT2 = new Identifier(Kav_soul_like.MOD_ID, "disconnect2");
    public static final Identifier LOWSTA = new Identifier(Kav_soul_like.MOD_ID, "lowsta");
    public static final Identifier HEALTH = new Identifier(Kav_soul_like.MOD_ID, "health");
    public static final Identifier STRENGTH = new Identifier(Kav_soul_like.MOD_ID, "strength");
    public static final Identifier AGILITY = new Identifier(Kav_soul_like.MOD_ID, "agility");
    public static final Identifier STAMINAL = new Identifier(Kav_soul_like.MOD_ID, "staminal");
    public static final Identifier DEFENCE = new Identifier(Kav_soul_like.MOD_ID, "defence");
    public static final Identifier LEVEL = new Identifier(Kav_soul_like.MOD_ID, "level");

    public static final Identifier HEALTHC = new Identifier(Kav_soul_like.MOD_ID, "healthc");
    public static final Identifier STRENGTHC = new Identifier(Kav_soul_like.MOD_ID, "strengthc");
    public static final Identifier AGILITYC = new Identifier(Kav_soul_like.MOD_ID, "agilityc");
    public static final Identifier STAMINALC = new Identifier(Kav_soul_like.MOD_ID, "staminalc");
    public static final Identifier STAMINAM = new Identifier(Kav_soul_like.MOD_ID, "staminam");
    public static final Identifier DEFENCEC = new Identifier(Kav_soul_like.MOD_ID, "defencec");
    public static final Identifier LEVELC = new Identifier(Kav_soul_like.MOD_ID, "levelc");
    public static final Identifier MAXSTAMINA = new Identifier(Kav_soul_like.MOD_ID, "maxstaminaas");
    public static final Identifier INIT = new Identifier(Kav_soul_like.MOD_ID, "maxstaminaas");
    public static final Identifier LEVELCR = new Identifier(Kav_soul_like.MOD_ID, "levelcr");
    public static final Identifier DASH = new Identifier(Kav_soul_like.MOD_ID, "dash");
    public static final Identifier TEST = new Identifier(Kav_soul_like.MOD_ID, "test");

    public static final Identifier WAVING = new Identifier(Kav_soul_like.MOD_ID,"waving_i");

    public static Boolean t=false;
    public static Boolean t2=false;

    public static void registerC2SPackets()
        {
        ServerPlayNetworking.registerGlobalReceiver(HANDSWING, PlayerStatsC2S::sendstats);
        ServerPlayNetworking.registerGlobalReceiver(MAXSTAMINAC2S, PlayerStatsC2S::getMaxSta);
        ServerPlayNetworking.registerGlobalReceiver(STAMINA, PlayerStatsC2S::getsta);
        ServerPlayNetworking.registerGlobalReceiver(LOWSTA, PlayerStatsC2S::getlowsta);
        ServerPlayNetworking.registerGlobalReceiver(INITIALIZEMC2S, PlayerStatsC2S::INIT);
        ServerPlayNetworking.registerGlobalReceiver(DISCONNECT, PlayerStatsC2S::DISCO);
        ServerPlayNetworking.registerGlobalReceiver(DISCONNECT2, PlayerStatsC2S::DISCO2);
        ServerPlayNetworking.registerGlobalReceiver(HEALTH, PlayerStatsC2S::statshealth);
        ServerPlayNetworking.registerGlobalReceiver(STRENGTH, PlayerStatsC2S::statssttack);
        ServerPlayNetworking.registerGlobalReceiver(AGILITY, PlayerStatsC2S::statsagility);
        ServerPlayNetworking.registerGlobalReceiver(STAMINAL, PlayerStatsC2S::statsstaminal);
        ServerPlayNetworking.registerGlobalReceiver(DEFENCE, PlayerStatsC2S::statsdefence);
        ServerPlayNetworking.registerGlobalReceiver(LEVEL, PlayerStatsC2S::statslevel);
        ServerPlayNetworking.registerGlobalReceiver(INIT, PlayerStatsC2S::INITS);
        ServerPlayNetworking.registerGlobalReceiver(LEVELCR, PlayerStatsC2S::statslevelcre);
        ServerPlayNetworking.registerGlobalReceiver(DASH, playertechServerSide::Dash);
        ServerPlayNetworking.registerGlobalReceiver(TEST, PlayerStatsC2S::stattest);


            ServerPlayNetworking.registerGlobalReceiver(Packets.TechicAnimation.ID, (server, player, handler, buf, responseSender) -> {
                //System.out.println("s");
                ServerWorld world = Iterables.tryFind(server.getWorlds(), (element) -> element == player.world)
                        .orNull();

                if (world == null || world.isClient) {
                    return;
                }

                final var packet = Packets.TechicAnimation.read(buf);

                final var forwardBuffer = new Packets.TechicAnimation(player.getId(), packet.animationName());

                for(PlayerEntity player1: server.getPlayerManager().getPlayerList())
                {
                    ServerPlayNetworking.send((ServerPlayerEntity) player1, Packets.TechicAnimation.ID, forwardBuffer.write());

                }


            });

            ServerPlayNetworking.registerGlobalReceiver(Packets.DashAnimation.ID, (server, player, handler, buf, responseSender) -> {
                //System.out.println("s");
                ServerWorld world = Iterables.tryFind(server.getWorlds(), (element) -> element == player.world)
                        .orNull();

                if (world == null || world.isClient) {
                    return;
                }

                final var packet = Packets.DashAnimation.read(buf);

                final var forwardBuffer = new Packets.DashAnimation(player.getId(), packet.animationName(),packet.direction(),packet.speed());

                for(PlayerEntity player1: server.getPlayerManager().getPlayerList())
                {

                    ServerPlayNetworking.send((ServerPlayerEntity) player1, Packets.DashAnimation.ID, forwardBuffer.write());

                }


            });




    }
    public static void registerS2CPackets()
    {
        ClientPlayNetworking.registerGlobalReceiver(Packets.Stats.ID, (client, handler, buf, respondSender) ->
        {
            final var packet = Packets.Stats.read(buf);

            HeartData.setHeart(((IEntityDataSaver) client.player),packet.health());
            StrengthData.setstrength(((IEntityDataSaver) client.player),packet.str()) ;
            AgilityData.setAgility(((IEntityDataSaver) client.player),packet.agility());
            StaminaLevelData.setStaminaL(((IEntityDataSaver) client.player),packet.stamina());
            DefenceData.setDefence(((IEntityDataSaver) client.player),packet.defence());
            LevelData.setLevel(((IEntityDataSaver) client.player),packet.level());
        });


        //codes provides by better combat with some modification
        ClientPlayNetworking.registerGlobalReceiver(Packets.TechicAnimation.ID, (client, handler, buf, respondSender) ->
        {

            final var packet = Packets.TechicAnimation.read(buf);
            client.execute(() -> {
                var entity = client.world.getEntityById(packet.playerId());
                if (entity instanceof PlayerEntity) {
                    var animationContainer = ((IExampleAnimatedPlayer) entity).modid_getModAnimation();
                    KeyframeAnimation animation =  PlayerAnimationRegistry.getAnimation(new Identifier(Kav_soul_like.MOD_ID, packet.animationName()));


                    ModifierLayer base = new ModifierLayer(null);



                    var builder = animation.mutableCopy();
                    var part = builder.getPart("leftLeg");
                    part.setEnabled(false);



                    animation = builder.build();

                    SpeedModifier speedModifier= new SpeedModifier();
                    if(t==false)
                    {
                        speedModifier.speed=4;
                        t=true;
                    }

                    animationContainer.setAnimation(new KeyframeAnimationPlayer(animation));
                    animationContainer.addModifier(speedModifier,0);



                }
            });

        });
        ClientPlayNetworking.registerGlobalReceiver(Packets.DashAnimation.ID, (client, handler, buf, respondSender) ->
        {

            final var packet = Packets.DashAnimation.read(buf);
            client.execute(() -> {
                var entity = client.world.getEntityById(packet.playerId());
                if (entity instanceof PlayerEntity) {


                    if(packet.direction()== direction.LEFT.getint())
                    {
                        var animationContainer = ((IExampleAnimatedPlayer) entity).modid_getModAnimation();
                        KeyframeAnimation animationL =  PlayerAnimationRegistry.getAnimation(new Identifier(Kav_soul_like.MOD_ID, packet.animationName()));
                        ModifierLayer base = new ModifierLayer(null);



                        var builder = animationL.mutableCopy();
                        var part = builder.getPart("head");
                        part.setEnabled(false);



                        animationL = builder.build();

                        TransmissionSpeedModifier speedS= new TransmissionSpeedModifier();
                        if(t==true)
                        {
                            speedS.speed=1;
                        }

                        if(t==false)
                        {
                            speedS.speed=1.5f;
                            t=true;
                        }




                        animationContainer.setAnimation(new KeyframeAnimationPlayer(animationL));
                        animationContainer.addModifier(speedS,0);
                    }
                    else if(packet.direction()==direction.RIGHT.getint())
                    {
                        var animationContainer2 = ((IExampleAnimatedPlayer) entity).modid_getModAnimation();
                        System.out.println(packet.direction());
                        KeyframeAnimation animation =  PlayerAnimationRegistry.getAnimation(new Identifier(Kav_soul_like.MOD_ID, "dashing_right"));
                        ModifierLayer base = new ModifierLayer(null);



                        var builder = animation.mutableCopy();
                        var part = builder.getPart("head");
                        part.setEnabled(false);



                        animation = builder.build();



                        TransmissionSpeedModifier speedSS= new TransmissionSpeedModifier();
                        if(t2==true)
                        {
                            speedSS.speed=1;
                        }

                        if(t2==false)
                        {
                            speedSS.speed=1.5f;
                            t2=true;
                        }



                        animationContainer2.setAnimation(new KeyframeAnimationPlayer(animation));
                        animationContainer2.addModifier(speedSS,0);

                    }







                }
            });

        });



        ClientPlayNetworking.registerGlobalReceiver(MAXSTAMINA, (client, handler, buf, respondSender) ->
        {
            //System.out.println("s");
            StaminaData.floatsetmax(((IEntityDataSaver) client.player),buf.readFloat());



        });

        ClientPlayNetworking.registerGlobalReceiver(STAMINAM, (client, handler, buf, respondSender) ->
        {
            StaminaData.addPoints(((IEntityDataSaver) client.player),1,"MaxStamina");

        });

        ClientPlayNetworking.registerGlobalReceiver(HEALTHC, (client, handler, buf, respondSender) ->
        {
            HeartData.setHeart(((IEntityDataSaver) client.player),buf.readInt());

        });

        ClientPlayNetworking.registerGlobalReceiver(STRENGTHC, (client, handler, buf, respondSender) ->
        {
            StrengthData.setstrength(((IEntityDataSaver) client.player),buf.readInt()) ;
        });

        ClientPlayNetworking.registerGlobalReceiver(AGILITYC, (client, handler, buf, respondSender) ->
        {
            AgilityData.setAgility(((IEntityDataSaver) client.player),buf.readInt());
        });
        ClientPlayNetworking.registerGlobalReceiver(STAMINALC, (client, handler, buf, respondSender) ->
        {
            StaminaLevelData.setStaminaL(((IEntityDataSaver) client.player),buf.readInt());
        });


        ClientPlayNetworking.registerGlobalReceiver(DEFENCEC, (client, handler, buf, respondSender) ->
        {

          DefenceData.setDefence(((IEntityDataSaver) client.player),buf.readInt());
        });


        ClientPlayNetworking.registerGlobalReceiver(LEVELC, (client, handler, buf, respondSender) ->
        {
            LevelData.setLevel(((IEntityDataSaver) client.player),buf.readInt());
        });

        ClientPlayNetworking.registerGlobalReceiver(STAMINA2, (client, handler, buf, respondSender) ->
        {
           /* if(!client.isInSingleplayer())
            {
                client.player.sendMessage(Text.literal(client.player.getName().toString()+" c"),true);
                StaminaData.removePoints(((IEntityDataSaver) client.player),buf.readFloat(),"Stamina");

            }
            else
            {
                (((IEntityDataSaver) client.player)).getPersistentData().putFloat("Stamina",buf.readFloat());
            }*/
            ;
            float x= buf.readFloat();
           // client.player.sendMessage(Text.literal(String.valueOf(x)),false);
            StaminaData.removePoints(((IEntityDataSaver) client.player),x,"Stamina");
            if(StaminaData.Stamina(((IEntityDataSaver) client.player))<=1)
            {
                PacketByteBuf buff= PacketByteBufs.create();

                buff.writeString(client.player.getName().getString());

                ClientPlayNetworking.send(ModMessages.LOWSTA, buff);

            }

        });

        ClientPlayNetworking.registerGlobalReceiver(RECOVERYRATE, (client, handler, buf, respondSender) ->
        {
            (((IEntityDataSaver) client.player)).getPersistentData().putFloat("recoveryrate",buf.readFloat());



        });

        ClientPlayNetworking.registerGlobalReceiver(RECOVERYRATETIME, (client, handler, buf, respondSender) ->
        {


            int x= buf.readInt();
            ClientStamina.tick=x;

            ClientStamina.bonuscon=true;

        });

        ClientPlayNetworking.registerGlobalReceiver(INITIALIZEM, (client, handler, buf, respondSender) ->
        {
            if(client.player!=null)
            {
                (((IEntityDataSaver) client.player)).getPersistentData().putFloat("MaxStamina",buf.readFloat());
            }

        });

        ClientPlayNetworking.registerGlobalReceiver(INITIALIZEX, (client, handler, buf, respondSender) ->
        {
            if(client.player!=null)
            {
                GlobalStamina.X=buf.readInt();

            }

        });

        ClientPlayNetworking.registerGlobalReceiver(INITIALIZEY, (client, handler, buf, respondSender) ->
        {
            if(client.player!=null)
            {

                GlobalStamina.Y=buf.readInt();

            }

        });






    }
    }

