package net.kav.kav_soul_like.client.gui;

import net.kav.kav_soul_like.client.ModScreenHandlers;
import net.kav.kav_soul_like.util.IEntityDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class GemInfusingScreenHandler extends ScreenHandler {
    private  playerinventories inventory;


    public GemInfusingScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, new playerinventories(inventory.player));
    }


    public GemInfusingScreenHandler(int syncId, PlayerInventory playerInventory, playerinventories inventory2) {
        super(ModScreenHandlers.GEM_INFUSING_SCREEN_HANDLER, syncId);
        checkSize(inventory2, 3);
        this.inventory = inventory2;
        inventory.onOpen(playerInventory.player);

        // Assign slots to the respective lists
        Slot firstSlot = new Slot(inventory, 0, 12, 15);
        Slot secondSlot = new Slot(inventory, 1, 86, 15);
        Slot thirdSlot = new Slot(inventory, 2, 86, 60);

        this.addSlot(firstSlot);
        this.addSlot(secondSlot);
        this.addSlot(thirdSlot);
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

    }



    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}
