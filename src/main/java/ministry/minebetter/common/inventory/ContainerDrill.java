package ministry.minebetter.common.inventory;

import ministry.minebetter.common.util.entity.TileEntityCoalDrill;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by M1nistry on 6/02/2016.
 */
public class ContainerDrill extends Container {

    private final IInventory tileDrill;
    private int x;
    private int y;
    private int z;
    private int i;

    public ContainerDrill(InventoryPlayer playerInventory, IInventory drillInventory)
    {
        this.tileDrill = drillInventory;
        this.addSlotToContainer(new Slot(drillInventory, 0, 56, 17));
        this.addSlotToContainer(new SlotFuel(drillInventory, 1, 56, 53));
        this.addSlotToContainer(new SlotDrillOutput(playerInventory.player, drillInventory, 2, 116, 36));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    public void onCraftGuiOpened(ICrafting listener)
    {
        super.onCraftGuiOpened(listener);
        listener.sendAllWindowProperties(this, this.tileDrill);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = this.crafters.get(i);

            if (this.x != this.tileDrill.getField(2))
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileDrill.getField(2));
            }

            if (this.z != this.tileDrill.getField(0))
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileDrill.getField(0));
            }

            if (this.i != this.tileDrill.getField(1))
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileDrill.getField(1));
            }

            if (this.y != this.tileDrill.getField(3))
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileDrill.getField(3));
            }
        }

        this.x = this.tileDrill.getField(2);
        this.z = this.tileDrill.getField(0);
        this.i = this.tileDrill.getField(1);
        this.y = this.tileDrill.getField(3);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileDrill.isUseableByPlayer(playerIn);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileDrill.setField(id, data);
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {

                if (TileEntityCoalDrill.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemstack1);
        }

        return itemstack;
    }
}
