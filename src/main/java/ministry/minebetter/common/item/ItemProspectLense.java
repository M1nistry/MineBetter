package ministry.minebetter.common.item;

import com.google.common.collect.Sets;
import ministry.minebetter.api.block.blocks;
import ministry.minebetter.common.block.BlockDenseIron;
import ministry.minebetter.common.util.entity.TileEntityYield;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by M1nistry on 3/02/2016.
 */
public class ItemProspectLense extends Item {

    private static Set effectiveAgainst = Sets.newHashSet(new Block[] {blocks.dense_iron});

    public ItemProspectLense()
    {
        super();
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        IBlockState iblockstate = world.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (block == blocks.dense_iron)  {

            if (!world.isRemote && iblockstate.getValue(BlockDenseIron.EXHAUSTED))
            {
                player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Yield: " + EnumChatFormatting.RED + "EXHAUSTED"));
                return true;
            }
            TileEntityYield teYield = (TileEntityYield) world.getTileEntity(pos);
            if (!world.isRemote) player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Yield: " + EnumChatFormatting.WHITE + teYield.getEntry()));

                return true;
            }
            return false;
        }
}
