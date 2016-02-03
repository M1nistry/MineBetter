package ministry.minebetter.common.util.entity;

import net.minecraft.tileentity.TileEntity;
import java.util.Random;

public class TileEntityYield extends TileEntity {

    //private int i = new Random().nextInt(500 - 250) + 250;
    private int i = new Random().nextInt(10);
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
