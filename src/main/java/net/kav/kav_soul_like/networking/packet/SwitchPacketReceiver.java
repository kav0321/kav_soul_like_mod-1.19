package net.kav.kav_soul_like.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class SwitchPacketReceiver implements ServerPlayNetworking.PlayChannelHandler {

    @Override
    public void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender responseSender) {
        // target slot
        int slot = buffer.readInt();

        // player inventory and selected slot
        PlayerInventory playerInventory = player.getInventory();
        int selectedSlot = playerInventory.selectedSlot;

        // think in a way of pulling out case (although it can be just a putting back)
        int slotToPullOutFrom = slot;
        int slotToPullOutTo = selectedSlot;

        // pull out to offhand if pulling out from belt slot and offhand switch is on,
        // aka belt slot always goes with offhand


        // shield in the back would always be pulled out to offhand


        // fallback early to offhand if failure in main hand is expected


        // check items in both slots
        ItemStack stackInSlotToPullOutFrom = playerInventory.getStack(slotToPullOutFrom);
        ItemStack stackInSlotToPullOutTo = playerInventory.getStack(slotToPullOutTo);

        // switching is meaningless if both slots are empty
        boolean bothSlotsAreEmpty = stackInSlotToPullOutFrom.isEmpty() && stackInSlotToPullOutTo.isEmpty();
        if (bothSlotsAreEmpty) {
            return;
        }

        // slot and stack information for putting back
        int slotToPutBackTo = slotToPullOutFrom;
        ItemStack stackToPutBack = stackInSlotToPullOutTo;

        // check conditions
        boolean doneSwitching = false;
        boolean canPutBack = isItemAllowed(stackToPutBack, slotToPutBackTo);


        // play sound if done switching




    }
    public static boolean isItemAllowed (ItemStack stack,int slot){
        // BeltSlot
        return true;
    }
}