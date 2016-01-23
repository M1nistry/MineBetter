package ministry.minebetter.common.item;

/**
 * Created by M1nistry on 23/01/2016.
 */

import com.google.common.collect.ImmutableSet;
import ministry.minebetter.api.block.IBlock;
import ministry.minebetter.common.util.block.BlockStateUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.minecraft.block.Block;

import java.util.List;

public class ItemMineBlock extends ItemBlock {

    public IBlock mineBlock;

    public ItemMineBlock(Block block)
    {
        super(block);
        if (block instanceof IBlock)
        {
            this.mineBlock = (IBlock)block;
        }
        else
        {
            throw new IllegalArgumentException("ItemMineBlock must be created with a block implementing IMineBlock");
        }
        this.setHasSubtypes(true);
    }

    // define the items which will appear in the creative tab (called by ItemBlock class)
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        ImmutableSet<IBlockState> presets = BlockStateUtils.getBlockPresets(this.block);
        if (presets.isEmpty())
        {
            subItems.add(new ItemStack(this.block, 1, 0));
        }
        else
        {
            for (IBlockState state : presets)
            {
                subItems.add(new ItemStack(this.block, 1, this.block.getMetaFromState(state)));
            }
        }
    }


    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack stack, int tintIndex)
    {
        return this.mineBlock.getItemRenderColor(this.block.getStateFromMeta(stack.getMetadata()), tintIndex);
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        ImmutableSet<IBlockState> presets = BlockStateUtils.getBlockPresets(this.block);
        if (presets.isEmpty())
        {
            return super.getUnlocalizedName();
        }
        else
        {
            int meta = stack.getMetadata();
            IBlockState oldState = block.getStateFromMeta(meta);
            IBlockState newState = BlockStateUtils.getPresetState(oldState);

            return super.getUnlocalizedName() + "." + mineBlock.getStateName(newState);
        }
    }

}
