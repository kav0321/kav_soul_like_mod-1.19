package net.kav.kav_soul_like.client.gui;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;

import java.util.List;

public class playerinventories extends PlayerInventory {
    public final DefaultedList<ItemStack> first = DefaultedList.ofSize(1, ItemStack.EMPTY);
    public final DefaultedList<ItemStack> second = DefaultedList.ofSize(1, ItemStack.EMPTY);
    public final DefaultedList<ItemStack> third = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private final List<DefaultedList<ItemStack>> combinedInventorys= ImmutableList.of(this.first,this.second,this.third);

    public playerinventories(PlayerEntity player) {
        super(player);
    }


    @Override
    public ItemStack getStack(int slot)
    {
        super.getStack(slot);
        DefaultedList<ItemStack> list = null;
        for (DefaultedList<ItemStack> defaultedList : this.combinedInventorys) {
            if (slot < defaultedList.size()) {
                list = defaultedList;
                break;
            }
            slot -= defaultedList.size();
        }
        return list == null ? ItemStack.EMPTY : (ItemStack)list.get(slot);
    }
    @Override
    public Text getName() {
        return Text.translatable("container.inventory");
    }

    @Override
    public NbtList writeNbt(NbtList nbtList) {
        super.writeNbt(nbtList);
        NbtCompound nbtCompound;
        int i;
        int firstInventoryOffset = 200;
        int secondInventoryOffset = 56;
        int thirdInventoryOffset = 112;

        for (i = 0; i < this.first.size(); ++i) {
            if (this.first.get(i).isEmpty()) continue;
            nbtCompound = new NbtCompound();
            nbtCompound.putByte("Slot", (byte)(i + firstInventoryOffset));
            this.first.get(i).writeNbt(nbtCompound);
            nbtList.add(nbtCompound);
        }
        for (i = 0; i < this.second.size(); ++i) {
            if (this.second.get(i).isEmpty()) continue;
            nbtCompound = new NbtCompound();
            nbtCompound.putByte("Slot", (byte)(i + secondInventoryOffset));
            this.second.get(i).writeNbt(nbtCompound);
            nbtList.add(nbtCompound);
        }
        for (i = 0; i < this.third.size(); ++i) {
            if (this.third.get(i).isEmpty()) continue;
            nbtCompound = new NbtCompound();
            nbtCompound.putByte("Slot", (byte)(i + thirdInventoryOffset));
            this.third.get(i).writeNbt(nbtCompound);
            nbtList.add(nbtCompound);
        }
        return nbtList;
    }

    @Override
    public void readNbt(NbtList nbtList) {
        super.readNbt(nbtList);
        this.first.clear();
        this.second.clear();
        this.third.clear();
        int firstInventoryOffset = 200;
        int secondInventoryOffset = 56;
        int thirdInventoryOffset = 112;

        for (int i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = nbtList.getCompound(i);
            int j = nbtCompound.getByte("Slot") & 0xFF;
            ItemStack itemStack = ItemStack.fromNbt(nbtCompound);
            if (itemStack.isEmpty()) continue;
            if (j >= firstInventoryOffset && j < this.first.size() + firstInventoryOffset) {
                this.first.set(j - firstInventoryOffset, itemStack);
                continue;
            }
            if (j >= secondInventoryOffset && j < this.second.size() + secondInventoryOffset) {
                this.second.set(j - secondInventoryOffset, itemStack);
                continue;
            }
            if (j >= thirdInventoryOffset && j < this.third.size() + thirdInventoryOffset) {
                this.third.set(j - thirdInventoryOffset, itemStack);
            }
        }
    }

    public ItemStack getfirstStack(int slot) {
        return this.first.get(slot);
    }
    public ItemStack getsecondStack(int slot) {
        return this.second.get(slot);
    }
    public ItemStack getthirdStack(int slot) {
        return this.third.get(slot);
    }

    public void dropAll() {
        for (List list : this.combinedInventorys) {
            for (int i = 0; i < list.size(); ++i) {
                ItemStack itemStack = (ItemStack)list.get(i);
                if (itemStack.isEmpty()) continue;
                this.player.dropItem(itemStack, true, false);
                list.set(i, ItemStack.EMPTY);
            }
        }
    }



    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.player.isRemoved()) {
            return false;
        }
        return !(player.squaredDistanceTo(this.player) > 64.0);
    }





    public void clone(PlayerInventory other) {
        for (int i = 0; i < this.size(); ++i) {
            this.setStack(i, other.getStack(i));
        }
        this.selectedSlot = other.selectedSlot;
    }

    @Override
    public void clear() {
        for (List list : this.combinedInventorys) {
            list.clear();
        }
    }

    public void populateRecipeFinder(RecipeMatcher finder) {
        for (ItemStack itemStack : this.main) {
            finder.addUnenchantedInput(itemStack);
        }
    }
    public int getEmptySlotfirst() {
        for (int i = 0; i < this.first.size(); ++i) {
            if (!this.first.get(i).isEmpty()) continue;
            return i;
        }
        return -1;
    }

    public int getEmptySlotsecond() {
        for (int i = 0; i < this.second.size(); ++i) {
            if (!this.second.get(i).isEmpty()) continue;
            return i;
        }
        return -1;
    }
    public int getEmptySlotthird() {
        for (int i = 0; i < this.third.size(); ++i) {
            if (!this.third.get(i).isEmpty()) continue;
            return i;
        }
        return -1;
    }
    public ItemStack dropSelectedItem(boolean entireStack) {
        ItemStack itemStack = this.getMainHandStack();
        if (itemStack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        return this.removeStack(this.selectedSlot, entireStack ? itemStack.getCount() : 1);
    }
}
