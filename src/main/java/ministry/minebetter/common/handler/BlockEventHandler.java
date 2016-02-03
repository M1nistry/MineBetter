package ministry.minebetter.common.handler;

import com.google.common.collect.ImmutableMap;
import ministry.minebetter.api.block.blocks;
import ministry.minebetter.common.block.BlockDenseIron;
import ministry.minebetter.common.util.entity.TileEntityYield;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by M1nistry on 24/01/2016.
 */
public class BlockEventHandler {

    @SubscribeEvent
    public void BreakBlock(BlockEvent.BreakEvent e)
    {
        if (e.state.getBlock() == blocks.dense_iron)
        {
            if (!e.state.getValue(BlockDenseIron.EXHAUSTED))
            {
                TileEntity tileentity = e.world.getTileEntity(e.pos);
                if (tileentity instanceof TileEntityYield)
                {
                    TileEntityYield tileEntityYield = ((TileEntityYield) tileentity);
                    int yield = tileEntityYield.getEntry();
                    tileEntityYield.addEntry(yield - 1);
                    if (yield - 1 == 0) e.world.setBlockState(e.pos, e.state.withProperty(BlockDenseIron.EXHAUSTED, true));
                }

                e.world.spawnEntityInWorld(new EntityItem(e.world, e.pos.getX(), e.pos.getY()+1.0f, e.pos.getZ(), new ItemStack(Item.getItemFromBlock(Blocks.iron_ore), 1)));
                e.setCanceled(true);
                return;
            }
            else
            {
                e.world.spawnEntityInWorld(new EntityItem(e.world, e.pos.getX(), e.pos.getY()+1.0f, e.pos.getZ(), new ItemStack(Item.getItemFromBlock(Blocks.cobblestone), 1)));
                e.setCanceled(false);
            }
        }
    }
}
