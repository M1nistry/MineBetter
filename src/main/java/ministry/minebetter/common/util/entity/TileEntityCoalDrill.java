package ministry.minebetter.common.util.entity;

import ministry.minebetter.common.block.BlockCoalDrill;
import ministry.minebetter.common.inventory.ContainerDrill;
import ministry.minebetter.common.inventory.SlotFuel;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCoalDrill extends TileEntityLockable implements ITickable, ISidedInventory {

    private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};

    private ItemStack[] drillItemStacks = new ItemStack[3];

    private int drillActiveTime; //Number of ticks that the drill will keep drilling
    private int currentDrillTime; //Number of ticks a single copy of the burning item will remain active for;
    private int totalDrillTime;

    private String drillCustomName;

    public int getSizeInventory() { return this.drillItemStacks.length; }

    public ItemStack getStackInSlot(int index) { return this.drillItemStacks[index]; }

    public boolean isDrilling() { return this.drillActiveTime > 0; }
    @SideOnly(Side.CLIENT)
    public static boolean isDrilling(IInventory p_174903_0_)
    {
        return p_174903_0_.getField(0) > 0;
    }

    private boolean canDrill()
    {
//        if (this.drillItemStacks[0] == null)
//        {
//            return false;
//        }
//        else
//        {
//            if (itemstack == null) return false;
//            if (this.furnaceItemStacks[2] == null) return true;
//            if (!this.furnaceItemStacks[2].isItemEqual(itemstack)) return false;
//            int result = furnaceItemStacks[2].stackSize + itemstack.stackSize;
//            return result <= getInventoryStackLimit() && result <= this.furnaceItemStacks[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
//        }
        return true;
    }

    public void update() {
        boolean flag = this.isDrilling();
        boolean flag1 = false;

        if (this.isDrilling()) {
            --this.drillActiveTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.isDrilling() || this.drillItemStacks[1] != null && this.drillItemStacks[0] != null) {
                if (!this.isDrilling() && this.canDrill()) {
                    this.currentDrillTime = this.drillActiveTime = getDrillTime(this.drillItemStacks[1]);

                    if (this.isDrilling()) {
                        flag1 = true;

                        if (this.drillItemStacks[1] != null) {
                            --this.drillItemStacks[1].stackSize;

                            if (this.drillItemStacks[1].stackSize == 0) {
                                this.drillItemStacks[1] = drillItemStacks[1].getItem().getContainerItem(drillItemStacks[1]);
                            }
                        }
                    }
                }

                /* if (this.isDrilling() && this.canDrill())
                {
                    //++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.furnaceItemStacks[0]);
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
            } */

                if (flag != this.isDrilling()) {
                    flag1 = true;
                    BlockCoalDrill.setState(this.isDrilling(), this.worldObj, this.pos);
                }
            }

            if (flag1) {
                this.markDirty();
            }
        }
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return index == 2 ? false : (index != 1 ? true : isItemFuel(stack) || SlotFuel.isBucket(stack));
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if ( /*direction == EnumFacing.DOWN && */ index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.water_bucket && item != Items.bucket)
            {
                return false;
            }
        }

        return true;
    }

    public boolean hasCustomName()
    {
        return this.drillCustomName != null && this.drillCustomName.length() > 0;
    }

    public void setCustomInventoryName(String name)
    {
        this.drillCustomName = name;
    }

    public ItemStack decrStackSize(int index, int count)
    {
        if (this.drillItemStacks[index] != null)
        {
            if (this.drillItemStacks[index].stackSize <= count)
            {
                ItemStack itemstack1 = this.drillItemStacks[index];
                this.drillItemStacks[index] = null;
                return itemstack1;
            }
            else
            {
                ItemStack itemstack = this.drillItemStacks[index].splitStack(count);

                if (this.drillItemStacks[index].stackSize == 0)
                {
                    this.drillItemStacks[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack removeStackFromSlot(int index)
    {
        if (this.drillItemStacks[index] != null)
        {
            ItemStack itemstack = this.drillItemStacks[index];
            this.drillItemStacks[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int index, ItemStack stack)
    {
        boolean flag = stack != null && stack.isItemEqual(this.drillItemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.drillItemStacks[index]);
        this.drillItemStacks[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index == 0 && !flag)
        {
            this.totalDrillTime = this.getDrillTime(stack);
            //this. = 0;
            this.markDirty();
        }
    }

    public static int getDrillTime(ItemStack itemStack)
    {
        if (itemStack == null) return 0;

        Item item = itemStack.getItem();

        if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
        {
            Block block = Block.getBlockFromItem(item);
            if (block == Blocks.coal_block) return 16000;
        }
        return net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(itemStack);
    }

    public String getName()
    {
        return this.hasCustomName() ? this.drillCustomName : "container.coal_drill";
    }

    public static boolean isItemFuel(ItemStack itemStack)
    {
        return getDrillTime(itemStack) > 0;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.drillItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot");

            if (j >= 0 && j < this.drillItemStacks.length)
            {
                this.drillItemStacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }

        this.drillActiveTime = compound.getShort("DrillTime");
        //this.cookTime = compound.getShort("CookTime");
        this.totalDrillTime = compound.getShort("DrillTimeTotal");
        //this.currentDrillTime = getItemDrillTime(this.drillItemStacks[1]);

    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("DrillTime", (short)this.drillActiveTime);
        //compound.setShort("CookTime", (short)this.cookTime);
        compound.setShort("DrillTimeTotal", (short)this.totalDrillTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.drillItemStacks.length; ++i)
        {
            if (this.drillItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                this.drillItemStacks[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        compound.setTag("Items", nbttaglist);
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public void openInventory(EntityPlayer player)
    {
    }

    public void closeInventory(EntityPlayer player)
    {
    }

    public String getGuiID()
    {
        return "mb:coal_drill";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerDrill(playerInventory, this);
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.drillActiveTime;
            case 1:
                return this.currentDrillTime;
             /* case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime; */
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.drillActiveTime = value;
                break;
            case 1:
                this.currentDrillTime = value;
                break;
            /* case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;*/
        }
    }

    public int getFieldCount()
    {
        return 2;
    }

    public void clear()
    {
        for (int i = 0; i < this.drillItemStacks.length; ++i)
        {
            this.drillItemStacks[i] = null;
        }
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }

}
