package ministry.minebetter.common.block;

import ministry.minebetter.api.block.IBlock;
import ministry.minebetter.api.block.blocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.item.ItemBlock;

public class BlockDenseIron extends Block implements IBlock
{

    public static final PropertyInteger YIELD = PropertyInteger.create("yield", 0, 740);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { YIELD });}

    public BlockDenseIron()
    {
        super (Material.ground);
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
}
