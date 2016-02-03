package ministry.minebetter.common.util.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileEntityYield extends TileEntity {

    private int i = new Random().nextInt(100);
    private int yield = i;

    public void addEntry(int i)
    {
        yield = i;
    }

    public int getEntry()
    {
        return yield;
    }
}
