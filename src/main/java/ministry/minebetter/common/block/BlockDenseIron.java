package ministry.minebetter.common.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Random;

public class BlockDenseIron extends Block
{
    private Item drop;

    public BlockDenseIron()
    {
        super (Material.ground);
        this.setUnlocalizedName("dense_iron_ore");
        //this.setTickRandomly(true);
        //this.setBlockUnbreakable();
        this.setHardness(3.0f);
        this.setHarvestLevel("pickaxe", 2);

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.drop = Item.getItemFromBlock(Blocks.iron_ore);

    }


    @Override
    public Item getItemDropped(IBlockState blockstate, Random random, int fortune)
    {
        return this.drop;
    }

    @Override
    public int quantityDropped(IBlockState blockstate, int fortune, Random random)
    {
        return 1;
    }

//    public static final PropertyInteger YIELD = PropertyInteger.create("yield", 200, 800);
//
//    public IBlockState getStateFromMeta(int meta)
//    {
//        return this.getDefaultState().withProperty(YIELD, 0);
//    }
//
//    @Override
//    public int getMetaFromState(IBlockState state)
//    {
//        return (Integer)state.getValue(YIELD);
//    }
//
//    // this method isn't required if your properties only depend on the stored metadata.
//    // it is required if:
//    // 1) you are making a multiblock which stores information in other blocks eg BlockBed, BlockDoor
//    // 2) your block's state depends on other neighbours (eg BlockFence)
//    @Override
//    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
//    {
//        return state;
//    }
//
//    // necessary to define which properties your blocks use
//    // will also affect the variants listed in the blockstates model file
//    @Override
//    protected BlockState createBlockState()
//    {
//        return new BlockState(this, new IProperty[] {YIELD});
//    }
}
