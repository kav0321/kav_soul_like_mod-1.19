package net.kav.kav_soul_like.client;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;

public class CustomSlot extends Slot {
    private final int index;

    public CustomSlot(Inventory inventory, int slotIndex, int xPosition, int yPosition, int index) {
        super(inventory, slotIndex, xPosition, yPosition);
        this.index = index;
    }

    @Override
    public int getIndex() {
        return index;
    }
}
