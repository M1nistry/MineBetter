package ministry.minebetter.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDrillOutput extends Slot {

    private EntityPlayer thePlayer;
    private int _b;

    public SlotDrillOutput(EntityPlayer player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.thePlayer = player;
    }

    public boolean isItemValid(ItemStack stack) {return false;}

    public ItemStack decrStackSize(int amount)
    {
        if (this.getHasStack())
        {
            this._b += Math.min(amount, this.getStack().stackSize);
        }
        return super.decrStackSize(amount);
    }

    public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack)
    {
        super.onPickupFromSlot(playerIn, stack);
    }
}
