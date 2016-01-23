package ministry.minebetter.common.handler;

import com.google.common.collect.ImmutableMap;
import ministry.minebetter.api.block.blocks;
import ministry.minebetter.common.block.BlockDenseIron;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
            if (!e.getPlayer().canHarvestBlock(e.state.getBlock()))
            {
                System.out.println("We shit");
                return;
            }
            else if (true)
            {
                System.out.println("We can harvest");
                e.world.spawnEntityInWorld(new EntityItem(e.world, e.pos.getX(), e.pos.getY()+1.0f, e.pos.getZ(), new ItemStack(Item.getItemFromBlock(Blocks.iron_ore), 1)));
                e.setCanceled(true);
            }
        }
    }
}
