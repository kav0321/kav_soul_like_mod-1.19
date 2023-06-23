package net.kav.kav_soul_like.client.gui.slots;

import net.kav.kav_soul_like.client.gui.playerinventories;
import net.minecraft.screen.slot.Slot;

public class slotone extends Slot {
    public final playerinventories inventorys;
    public slotone(playerinventories inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.inventorys = inventory;
    }
}
