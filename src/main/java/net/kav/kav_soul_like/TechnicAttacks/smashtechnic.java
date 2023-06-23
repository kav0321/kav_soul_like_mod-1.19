package net.kav.kav_soul_like.TechnicAttacks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class smashtechnic extends  Abstracttechnic{
    public smashtechnic(String name, int cooldown, int stamina, int speed) {
        super(name, cooldown, stamina, speed);
    }
    public smashtechnic(String name, int cooldown, int stamina) {
        super(name, cooldown, stamina, 1);
    }
    @Override
    public void ClientSideExecution()
    {
        super.ClientSideExecution();
    }
    @Override
    public void tick(PlayerEntity player)
    {

    }
    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player)
    {

    }
}
